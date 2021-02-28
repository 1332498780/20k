package DayFive20210228;

import java.util.Arrays;

public class QuickSortTest {

    public static int[] quickSort(int[] target, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return target;
        }
        i = low;
        j = high;

        temp = target[low];

        while (i < j) {

            while (temp <= target[j] && i < j) {
                j--;
            }

            while (temp >= target[i] && i < j) {
                i++;
            }

            if (i < j) {
                t = target[j];
                target[j] = target[i];
                target[i] = t;
            }

        }

        target[low] = target[i];
        target[i] = temp;

        quickSort(target, low, j - 1);

        quickSort(target, j + 1, high);
        return target;

    }

    public static void main(String[] args) {
        int[] target = new int[11];
        for (int i = 0; i < 11; i++) {
            target[i] = (int) (Math.random() * 100) + 1;
        }
        System.out.print(Arrays.toString(target));
        System.out.println();
        System.err.print(Arrays.toString(quickSort(target, 0, target.length - 1)));

    }
}
