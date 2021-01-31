package binarysearch;

/**
 * 四种常见二分查找变形问题
 * 1、查找第一个等于给定值的
 * 2、查找最后一个等一给定值的
 * 3、查找第一个大于等于给定值的
 * 4、查找最后一个小于等于给定值的
 */
public class MutiVersionBinary {
    public static void main(String[] args) {
        int a = new MutiVersionBinary().firstEquals(
                new int[]{1,2,2,2,2,2,4,5}, 2);
        int b = new MutiVersionBinary().lastEquals(
                new int[]{1,2,2,3}, 2);
        int c = new MutiVersionBinary().firstGreaterThanOrEquals(
                new int[]{3,4,5,5,7,10}, 5);
        int d = new MutiVersionBinary().lastLessThanOrEquals(
                new int[]{1,2,2,2,4,5}, 2);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }

    // 查找第一个等于给定值的
    public int firstEquals(int[] arr, int n) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int low = 0;
        int high = arr.length -1;
        while (low <= high) {
            int mid = low + ((high - low ) >> 1);
            if (arr[mid] == n) {
                if (mid == 0 || arr[mid - 1] != n) {
                    return mid;
                } else {
                    high = mid -1;
                }
//                while (mid >= 0 && arr[mid] == n) {
//                    mid--;
//                }
//                return mid+1;
            } else if (arr[mid] < n) {
                low = mid + 1;
            } else {
                high = mid -1;
            }
        }
        return -1;
    }

    // 查找最后一个等一给定值的
    public int lastEquals(int[] arr, int n) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int low = 0;
        int high = arr.length -1;
        while (low <= high) {
            int mid = low + ((high - low ) >> 1);
            if (arr[mid] == n) {
                if (mid == arr.length - 1 || arr[mid + 1] != n) {
                    return mid;
                } else {
                    low = mid + 1;
                }
//                while (mid <= arr.length-1 && arr[mid] == n) {
//                    mid++;
//                }
//                return mid-1;
            } else if (arr[mid] < n) {
                low = mid + 1;
            } else {
                high = mid -1;
            }
        }
        return -1;
    }

    // 查找第一个大于等于给定值的
    public int firstGreaterThanOrEquals(int[] arr, int n) {
        int low = 0;
        int high = arr.length -1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] >= n) {
                if (mid == 0 || arr[mid - 1] < n) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    // 第二种写法：查找第一个大于等于给定值的
    public int firstGreaterThanOrEquals1(int[] arr, int n) {
        int low = 0;
        int high = arr.length -1;
        int res = -1;
        while (low <= high) {
            int mid = low + ((high - low) >> 2);
            if (arr[mid] >= n) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // 查找最后一个小于等于给定值的
    public int lastLessThanOrEquals(int[] arr, int n) {
        int low = 0;
        int high = arr.length -1;
        while (low <= high) {
            int mid = low + ((high - low) >> 2);
            if (arr[mid] <= n) {
                if (mid == arr.length - 1 || arr[mid + 1] > n) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // 第二种写法：查找最后一个小于等于给定值的
    public int lastLessThanOrEquals1(int[] arr, int n) {
        int low = 0;
        int high = arr.length -1;
        int res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= n) {
                res = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }
}
