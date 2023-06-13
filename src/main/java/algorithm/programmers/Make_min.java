package algorithm.programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Make_min {
    public int solution(int []A, int []B) {
        int answer = 0;
        int arrCnt = A.length;

        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        for (int i = 0; i < arrCnt; i++) {
            listA.add(A[i]);
            listB.add(B[i]);
        }

        Collections.sort(listA);
        Collections.sort(listB);

        // listA는 앞에서부터, listB는 뒤에서부터 서로 곱해줌
        for(int i=0; i<listA.size(); i++){
            answer += listA.get(i) * listB.get(listB.size()-1 - i);
        }

        return answer;
    }

    public static void main(String[] args) {
        Make_min T = new Make_min();
        int[] A = {1,4,2};
        int[] B = {5,4,4};
        System.out.println(T.solution(A, B));
    }
}
