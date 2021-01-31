package binarysearch;


public class SquareRoot_69 {
    public static void main(String[] args) {
        double i = new SquareRoot_69().square(8);
        System.out.println(i);
        System.out.println(Math.sqrt(8));

    }
    // 执行用时：1 ms, 在所有 Java 提交中击败了 100.00% 的用户
    // 内存消耗：35.3 MB, 在所有 Java 提交中击败了 93.79% 的用户
    public int mySqrt(int x) {
        int low = 0;
        int high = x;
        int res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if ((long)mid * mid <= x) {
                low = mid + 1;
                res = mid;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }

    // 实现一个数的平方根，保留6位
    public double square(int n) {
        int count = 0;
        double low = mySqrt(n);
        double high = low + 1;
        double res = -1;
        while (low <= high) {
            double mid = (low + high) / 2;
            if (mid * mid <= n) {
                low = mid + 0.000001;
                res = mid;
            } else {
                high = mid - 0.000001;
            }
            count++;
        }
        System.out.println("共循环次数："+count);
        long a = (long)(res * 1000000);
        return (double)a/1000000;
    }
}
