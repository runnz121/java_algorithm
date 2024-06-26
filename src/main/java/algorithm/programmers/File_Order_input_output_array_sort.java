package algorithm.programmers;

import java.util.Arrays;
import java.util.Comparator;

public class File_Order_input_output_array_sort {

    public String[] solution(String[] files) {
        File[] file_info = new File[files.length];

        for (int i = 0; i < files.length; i++) {
            String[] split = splitFileName(files[i]);
            file_info[i] = new File(files[i], split[0], Integer.parseInt(split[1]));
        }

        Arrays.sort(file_info, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if ((o1.head).equals(o2.head)) { 	// HEAD 부분이 같다면
                    return o1.number - o2.number; 	// NUMBER 오름차순 정렬
                } else {
                    return (o1.head).compareTo(o2.head); // HEAD 부분을 기준으로 사전 순 정렬
                }
            }
        });

        String[] answer = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            answer[i] = file_info[i].file_name;
        }

        return answer;
    }

    public static String[] splitFileName(String file){
        String str[] = new String[3];
        str[0] = ""; // HEAD
        str[1] = ""; // NUMBER
        str[2] = ""; // TAIL

        int idx = 0;
        for (int i = 0; i < file.length(); i++) {
            char c = file.charAt(i);

            if(idx == 0 && !Character.isDigit(c)){
                str[idx] += c;
                continue;
            }

            if(idx == 0 && Character.isDigit(c)){
                idx ++;
            }

            if(idx == 1 && Character.isDigit(c)){
                str[idx] += c;
                continue;
            }

            if(idx == 1 && !Character.isDigit(c)){
                idx ++;
            }
            str[idx] += c;
        }

        str[0] = str[0].toLowerCase(); // 대소문자 구분 X, 소문자로 변환

        return str;
    }

    static class File {
        String file_name;
        String head;
        int number;

        public File(String file_name, String head, int number) {
            this.file_name = file_name;
            this.head = head;
            this.number = number;
        }
    }

    public static void main(String[] args) {
        File_Order_input_output_array_sort foi = new File_Order_input_output_array_sort();
        String[] inputs1 = {"img12.png"};
        String[] inputs = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        foi.solution(inputs);
    }
}
