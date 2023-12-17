package algorithmKotlin.leetCode;

public class ReverseString {

    public void reverString(char[] origin) {

        int start = 0;
        int end = origin.length - 1;

        while (start < end) {
            char tmp = origin[start];
            origin[start] = origin[end];
            origin[end] = tmp;

            start ++;
            end --;
        }
    }


    public static void main(String[] args) {

        char[] s = {'h','e','l','l','o'};

        ReverseString reverseString = new ReverseString();

        reverseString.reverString(s);

        System.out.println(s);
    }
}
