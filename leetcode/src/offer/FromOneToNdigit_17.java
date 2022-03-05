package offer;

import java.util.Arrays;

public class FromOneToNdigit_17 {
    public static void main(String[] args) {
//        FromOneToNdigit_17 fromOneToNdigit_17 = new FromOneToNdigit_17();
//        int[] ints = fromOneToNdigit_17.printNumbers(2);
//        Arrays.stream(ints).forEach(i -> {
//            System.out.println(i);
//        });
        FromOneToNdigit_17 from = new FromOneToNdigit_17();
        from.print(10);
    }

    private void print(int n) {
        int[] max = new int[n];
        for (int i = 0; i < n; i++) {
            max[i] = 9;
        }
        int[] inc = new int[n];
        while (!intArrEquals(inc, max)) {
//            printIntArr(inc);
            increment(inc, 1);
        }
        printIntArr(inc);
    }

    /**
     * 超过最大值不再加
     */
    private void increment(int[] arr, int n) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int carry = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (i == arr.length - 1) {
                carry = ((arr[i] + n) / 10);
                arr[i] = ((arr[i] + n) % 10);
                if (carry == 0) {
                    break;
                }
            } else if (i != 0) {
                int tmp = (arr[i] + carry);
                carry = (tmp / 10);
                arr[i] = (tmp % 10);
                if (carry == 0) {
                    break;
                }
            } else {
                if (((arr[i] + carry) / 10) > 1) {
                    break;
                }
                arr[i] = arr[i] + carry;
            }
        }
    }

    private void printIntArr(int[] arr) {
        if (arr == null) {
            return;
        }
        char[] chars = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            chars[i] = Character.forDigit(arr[i], 10);
        }
        String str = String.valueOf(chars);
        System.out.println(str);
    }

    private boolean intArrEquals(int[] a1, int[] a2) {
        if (a1 == a2) {
            return true;
        }
        if (a1 == null || a2 == null){
            return false;
        }
        if (a1.length != a2.length) {
            return false;
        }
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }


    private int[] printNumbers(int n) {
        if (n < 1) {
            return new int[0];
        }
        // 10的n方
        int max = 10;
        for (int i = 0; i < n - 1; i++) {
            max = max * 10;
        }
        int[] result = new int[max - 1];
        for (int i = 1; i < max; i++) {
            result[i-1] = i;
        }
        return result;
    }
}
