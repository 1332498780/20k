package DayFive20210228;

import java.util.Arrays;

public class SelectionSortTest {

    public static int[] selectionSort(int[] target) {
        for (int i = 0; i < target.length - 1; i++) {
            int min=i;
            for (int j = i+1; j <target.length; j++) {
                if (target[j] < target[min]) {
                    min=j;
                }
            }
            if(min!=i) {
                int data=target[min];
                target[min]=target[i];
                target[i]=data;
            }
        }
        return target;

    }

    public static void main(String[] args) {
        int[] target = new int [11];
        for (int i = 0; i < 11; i++) {
            target[i] = (int) (Math.random() * 100) + 1;
        }
        System.out.print(Arrays.toString(target));
        System.out.println();
        System.err.print(Arrays.toString(selectionSort(target)));

    }
}
