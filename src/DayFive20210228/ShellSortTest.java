package DayFive20210228;

import java.util.Arrays;

public class ShellSortTest {

    public static int[] shellSort(int[] target) {
        int gap = target.length / 2;
        while (gap > 0) {
            for (int j = gap; j < target.length; j++) {
                int i = j;
                while (i >= gap && target[i - gap] > target[i]) {
                    int temp = target[i - gap] + target[i];
                    target[i - gap] = temp - target[i - gap];
                    target[i] = temp - target[i - gap];
                    i -= gap;
                }
            }
            gap = gap / 2;
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
        System.err.print(Arrays.toString(shellSort(target)));

    }
}
