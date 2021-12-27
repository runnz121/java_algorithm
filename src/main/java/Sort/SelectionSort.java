package Sort;

import java.util.Arrays;

public class SelectionSort {

	public static void swapElements(int[] array, int i, int j) {
		//i, j위치를 바꿈
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	//스타트로부터 최소값 위치를 찾고 배열의 마지막 위치로 간다
	public static int indexLowest(int[] array, int start){
		int lowIndex = start;
		for (int i = start; i < array.length; i++) {
			if (array[i] < array[lowIndex]) {
				lowIndex = i;
			}
		}
		return lowIndex;
	}

	//요소 정렬
	public static void selectionSort(int[] array) {
		for (int i = 0; i < array.length; i++){
			int j = indexLowest(array, i);
			swapElements(array, i, j);
		}
	}

	public static void main(String[] args) {
		int[] array = {2, 5, 6, 1, 3};
		selectionSort(array);
		System.out.println(Arrays.toString(array));
	}

}
