package binarysearch;


/**
 * 简单的二分查找
 * O(logn)
 * 数据要求：数组+有序
 */
public class Simple {

    public static void main(String[] args) {
        int resut = new Simple().binaryRecursive(
                new int[]{1,1,1,1,3,4}, 4);
        System.out.println(resut);
    }

    // loop
    public int binaryLoop(int[] a, int value) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == value) {
                return mid;
            }
            if ((a[mid] < value)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // recursive
    public int binaryRecursive(int[] a, int value) {
        int low = 0;
        int high = a.length;
        return new Simple().recursive(a, low, high, value);
    }

    public int recursive(int[] a, int low, int high, int value) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (a[mid] == value) {
            return mid;
        } else if (value < a[mid]) {
            high = mid - 1;
        } else {
            low  = mid + 1;
        }
        return recursive(a, low, high,value);
    }
}
