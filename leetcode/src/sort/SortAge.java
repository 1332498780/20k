package sort;

import java.util.Objects;
import java.util.Random;

/**
 * 对年龄范围为100的数组排序
 */
public class SortAge {
    public static void main(String[] args) {
        int oldestAge = 100;
        int[] ages = new int[100000];
        for (int i = 0; i < ages.length; i++) {
            ages[i] = (int)(Math.random() * oldestAge);
        }
        SortAge sortAge = new SortAge();
        long start = System.currentTimeMillis();
        sortAge.sortAge(ages, oldestAge);
        System.out.println("总耗时："+ (System.currentTimeMillis() - start));
        for (int i = 0; i < ages.length; i++) {
            System.out.println(ages[i]);
        }
    }

    void sortAge(int[] ages, int oldestAge) {
        if (Objects.isNull(ages) || ages.length == 1) {
            return;
        }
        int[] timesOfAge = new int[oldestAge+1];
        for (int i = 0; i < ages.length; i++) {
            timesOfAge[ages[i]]++;
        }
        int index = 0;
        for (int i = 0; i < timesOfAge.length; i++) {
            for (int j = 0; j < timesOfAge[i]; j++) {
                ages[index++] = i;
            }
        }
    }
}
