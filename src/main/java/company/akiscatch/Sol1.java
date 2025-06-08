package company.akiscatch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Sol1 {


    private static final String DB_URL = "jdbc:sqlite:library.db";
    private static final String NEW_BOOKS_FILE = "new_books.csv";

    private static final String INSERT_BOOK_SQL =
            "INSERT INTO books (title, author, publisher, publication_year) VALUES (?, ?, ?, ?);";
    private static final String FIND_BOOK_SQL =
            "SELECT book_id, available FROM books WHERE title = ? AND publication_year = ?;";

    public static void main(String[] args) {

        insertBooks(NEW_BOOKS_FILE);

        String[][] booksToCheck = {
                {"Effective Java", "2018"},
                {"Clean Code", "2008"},
                {"Java Concurrency in Practice", "2006"},
                {"The Pragmatic Programmer", "1999"},
                {"Non-Existing Book", "2020"}
        };

        for (String[] book : booksToCheck) {
            String title = book[0];
            int year = Integer.parseInt(book[1]);
            Object availability = checkBookAvailability(title, year);
        }
    }

    /**
     * CSV 파일을 읽어 DB에 삽입합니다.
     * • 헤더는 건너뛰고, 병렬 스트림을 사용해 각 줄마다 INSERT 수행
     * • 잘못된 포맷(컬럼 개수, 숫자 파싱 오류)은 무시하고 로그만 출력
     */
    private static void insertBooks(String filePath) {
        Path csvPath = Paths.get(filePath);

        try (Stream<String> lines = Files.lines(csvPath)) {
            lines
                    .skip(1)            // 헤더 스킵
                    .parallel()         // 병렬 처리
                    .forEach(line -> {
                        String[] cols = line.split(",");
                        if (cols.length < 4) return;

                        String title = cols[0].trim();
                        String author = cols[1].trim();
                        String publisher = cols[2].trim();
                        int year;
                        try {
                            year = Integer.parseInt(cols[3].trim());
                        } catch (NumberFormatException ex) {
                            System.err.println("Invalid year in CSV: " + cols[3]);
                            return;
                        }

                        // 레코드 삽입
                        try (Connection conn = DriverManager.getConnection(DB_URL);
                             PreparedStatement ps = conn.prepareStatement(INSERT_BOOK_SQL)) {
                            ps.setString(1, title);
                            ps.setString(2, author);
                            ps.setString(3, publisher);
                            ps.setInt(4, year);
                            ps.executeUpdate();
                        } catch (SQLException sqle) {
                            System.err.println("Failed to insert: " + line);
                            sqle.printStackTrace();
                        }
                    });
        } catch (IOException ioe) {
            System.err.println("CSV file read error: " + filePath);
            ioe.printStackTrace();
        }
    }

    /**
     * 주어진 제목과 출판연도에 맞는 모든 도서를 조회하여
     * book_id → available 맵으로 반환합니다.
     * 조회 결과가 없으면 빈 맵을 반환합니다.
     */
    public static Object checkBookAvailability(String title, int publicationYear) {
        Map<Integer, Boolean> availability = new LinkedHashMap<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(FIND_BOOK_SQL)) {

            ps.setString(1, title);
            ps.setInt(2, publicationYear);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("book_id");
                    boolean avail = rs.getBoolean("available");
                    availability.put(id, avail);
                }
            }
        } catch (SQLException sqle) {
            System.err.println("Error querying availability for: "
                    + title + " (" + publicationYear + ")");
            sqle.printStackTrace();
        }

        return availability;
    }
}
