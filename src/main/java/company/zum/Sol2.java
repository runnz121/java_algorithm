package company.zum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Sol2 {

    static int lastRequestTime = 0;
    static int docNum;
    static int requestTime;
    static int pageNum;
    public int[] solution(int[][] data) {
        List<Integer> listAnswer = new ArrayList<>();
        int size = data.length;
        int maxIdx = data.length;
        int[] answer = new int[size];


        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] < o2[1] ? -1 :1 ;
            }
        });

        listAnswer.add(data[0][0]);
        lastRequestTime = data[0][2];
        PriorityQueue<PrintData> pq = new PriorityQueue<>(new Comparator<PrintData>() {
            @Override
            public int compare(PrintData o1, PrintData o2) {
                // 인덱스 우선순위 지정
                if (o1.idx < o2.idx) {
                    return 1;
                }
                // 페이지수가 적은 것부터 인쇄
                if (o1.pageNum < o2.pageNum) {
                    return -1;
                }
                // 페이지 수가 같을 경우 요청시간 먼저 인쇄  인쇄
                if (o1.pageNum == o2.pageNum) {
                    if (o1.requestTime < o2.requestTime) {
                        return -1;
                    }
                }
                return 1;
            }
        });

        for (int i = 1; i < size; i++) {
            docNum = data[i][0];
            requestTime = data[i][1];
            pageNum = data[i][2];
            PrintData printData = new PrintData(docNum, requestTime, pageNum,0);
            pq.offer(printData);
        }

        while (!pq.isEmpty()) {
            PrintData pt = pq.poll();
            if (lastRequestTime == pt.requestTime) {
                listAnswer.add(pt.docNum);
                lastRequestTime += pt.pageNum;
                System.out.println("2 : " + pt );
            } else if (pt.idx == maxIdx) {
                listAnswer.add(pt.docNum);
                lastRequestTime += pt.pageNum;
                System.out.println("3 : " + pt);
            } else {
                pt.idx += 1;
                pq.add(pt);
            }
        }
        System.out.println(listAnswer);

        for (int i = 0; i < listAnswer.size(); i++) {
            answer[i] = listAnswer.get(i);
        }
        System.out.println(Arrays.toString(answer));

        return answer;
    }

    static class PrintData{
        int docNum;
        int requestTime;
        int pageNum;
        int idx;

        PrintData(int docNum, int requestTime, int pageNum, int idx) {
            this.docNum = docNum;
            this.requestTime = requestTime;
            this.pageNum = pageNum;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "문서 번호 : " + this.docNum + " " + "요청시간 : "+ this.requestTime + " " + "페이지 수 :" + this.pageNum
                + " " + "idx :" + this.idx;
        }
    }

    public static void main(String[] args) {
        Sol2 t = new Sol2();
        int[][] data1 = {{1,0,5},{2,2,2},{3,3,1},{4,4,1},{5,10,2}};
        int[][] data2 = {{1,0,3},{2,1,3},{3,3,2},{4,9,1},{5,10,2}};
        int[][] data3 = {{1,2,10},{2,5,8},{3,6,9,},{4,20,6},{5,25,2}};
        t.solution(data1);
    }
}
