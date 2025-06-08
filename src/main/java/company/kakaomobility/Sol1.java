package company.kakaomobility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sol1 {

    public String solution(String S, String C) {

        String[] fullNames = S.split(", ");
        String companyPart = C.toLowerCase() + ".com";

        Map<String, Integer> emailCounts = new HashMap<>();
        List<String> result = new ArrayList<>();

        for (String fullName : fullNames) {

            String prefixEmail = createEmail(fullName);
            int count = emailCounts.getOrDefault(prefixEmail, 0) + 1;
            emailCounts.put(prefixEmail, count);

            String email = count == 1 ? prefixEmail : prefixEmail + count;
            result.add(fullName + " <" + email + "@" + companyPart + ">");
        }

        return String.join(", ", result);
    }

    private String createEmail(String fullName) {

        String[] parts = fullName.trim().split(" ");
        int totalLength = parts.length;

        String first = getParts(parts[0]).substring(0, 1);;
        String middle = totalLength== 3 ? getParts(parts[1]).substring(0, 1) : "";
        String last = getParts(parts[totalLength - 1].replace("-", ""));

        if (last.length() > 8) {
            last = last.substring(0, 8);
        }

        return first + middle + last;
    }

    private String getParts(String s) {
        return s.toLowerCase().replaceAll("[^a-z]", "");
    }


    public static void main(String[] args) {
        Sol1 sol = new Sol1();

        String S = "John Doe, Peter Parker, Mary Jane Watson-Parker, James Doe, John Elvis Doe, Jane Doe, Penny Parker";
        String C = "Example";

        String result = sol.solution(S, C);
        System.out.println(result);
    }

}
