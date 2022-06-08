package offer;

import java.util.Arrays;

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
 */
public class AdjustArrayOrder_21 {
    public static void main(String[] args) {

        //
        int[] a = {1,2,3,4};
        int[] ints = AdjustArrayOrder_21.adjustOrderModify(a);
        Arrays.stream(ints).forEach(System.out::println);
    }

    public static int[] adjustOrderModify(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        int l=0;
        int r=array.length - 1;
        while (true) {
            // 往后移，直到遇到偶数
            while (l < r && (array[l] & 1) == 0) {
                l++;
            }
            // 往前移，直到遇到奇数
            while (r > l && (array[l] & 1) == 1) {
                r--;
            }
            if (l >= r) {
                break;
            }
            int temp = array[l];
            array[l] = array[r];
            array[r] = temp;
        }
        return array;
    }

    // 不修改原值
    public static int[] adjustOrderNotMofidy(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        int[] result = new int[array.length];
        int l=0;
        int r=array.length - 1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                result[r--] = array[i];
            } else {
                result[l++] = array[i];
            }
        }
        return result;
    }
}
