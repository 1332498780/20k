package DayFive20210228;

import java.util.Arrays;

public class HeapSortTest {
    public static void maxHeap(int target[], int size, int index) {


        int leftNode = 2 * index + 1;

        int rightNode = 2 * index + 2;

        int max = index;


        if (leftNode < size && target[leftNode] > target[max]) {
            max = leftNode;
        }

        if (rightNode < size && target[rightNode] > target[max]) {
            max = rightNode;
        }


        if (max != index) {
            int tem = target[index];
            target[index] = target[max];
            target[max] = tem;

            maxHeap(target, size, max);
        }
    }



    public static int[] heapSort(int[] target) {
        int start = (target.length - 1) / 2;
        for (int i = start; i >= 0; i--) {
            maxHeap(target, target.length, i);
        }


        for (int i = target.length - 1; i > 0; i--) {
            int temp = target[0];
            target[0] = target[i];
            target[i] = temp;

            maxHeap(target, i, 0);
        }
        return target;
    }

    public static void main(String[] args) {
        int[] target = new int[11];
        for (int i = 0; i < 11; i++) {
            target[i] = (int) (Math.random() * 100) + 1;
        }
        System.out.print(Arrays.toString(target));
        System.out.println();
        System.err.print(Arrays.toString(heapSort(target)));

    }

}
