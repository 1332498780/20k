package recursion;

public class SumN {
    public static void main(String[] args) {
        int sum = new SumN().sumnLoop(6);
        System.out.println(sum);
    }

    public int sumnLoop(int n) {
        int sum =0;
        for (int i = 1; i <= n; i++) {
            sum = sum + i;
        }
        return sum;
    }
    public int sumnRecursion(int n) {
        if (n == 1) {
            return 1;
        }
        return sumnRecursion(n-1) + n;
    }
}
