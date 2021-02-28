package DayFive20210228;

import java.util.Arrays;

public class BubbleSortTest {

    public static int[] bubbleSort(int[] target) {
        for (int i = 0; i < target.length - 1; i++) {
            boolean falg = false;
            for (int j = target.length - 1; j > i; j--) {
                if (target[j - 1] > target[j]) {
                    int data = target[j - 1];
                    target[j - 1] = target[j];
                    target[j] = data;
                    falg = true;
                }
            }
            if (falg == false) {
                break;
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
        System.err.print(Arrays.toString(bubbleSort(target)));

    }
}
