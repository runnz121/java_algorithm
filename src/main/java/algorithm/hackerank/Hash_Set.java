package algorithm.hackerank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Hash_Set {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        Set<String> setName = new HashSet<>();

        for (int i = 0; i < t; i++) {

            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            String a = st.nextToken();
            String b = st.nextToken();


            String combined = a + "#" + b;
            setName.add(combined);
            System.out.println(setName.size());
        }
    }
}
