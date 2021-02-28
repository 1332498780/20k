package DayFive20210228;

import java.util.Arrays;

public class InsertionSortTestOne {

    public static int[] instetionSort(int[] target) {
        int i = 0, j = 0;
        for (i = 2; i <target.length; i++) {
            if (target[i] < target[i - 1]) {
                target[0] = target[i];
                for (j = i - 1; target[0] < target[j]; j--) {
                    target[j + 1] = target[j];
                }
                target[j + 1] = target[0];
            }
        }
        return target;
    }

    public static void main(String[] args) {
        int[] target = new int[11];
        for (int i = 0; i < 10; i++) {
            target[i + 1] = (int) (Math.random() * 100) + 1;
        }

        System.out.print(Arrays.toString(target));
        System.out.println();
        System.err.print(Arrays.toString(instetionSort(target)));

    }
}
