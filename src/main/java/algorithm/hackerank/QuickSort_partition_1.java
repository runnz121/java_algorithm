package algorithm.hackerank;

import java.util.*;

public class QuickSort_partition_1 {

    public static List<Integer> quickSort(List<Integer> arr) {
        int pivot = arr.get(0);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < arr.size(); i++) {
            int target = arr.get(i);

            if (target > pivot) {
                right.add(target);
            }
            else {
                left.add(target);
            }
        }

        left.add(pivot);

        left.addAll(right);

        return left;
    }

    public static void main(String[] args) {
        List<Integer> sample = List.of(4,5,3,7,2);

        List<Integer> integers = QuickSort_partition_1.quickSort(sample);

        System.out.println(integers);
    }
}
