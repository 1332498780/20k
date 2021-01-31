package offer;

/**
 * 斐波那契数
 * 递归：重复计算非常多，              时间复杂度O(n^2) 空间复杂度O(n^2)
 * 循环：不用重复计算，也不需要栈辅助    时间复杂度O(n)   空间复杂度O(1)
 *
 * 矩阵：时间复杂度logn
 */
public class Fibonacci_10 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long fibonacci = new Fibonacci_10().frogSteps(4);
        long end = System.currentTimeMillis();
        System.out.println(fibonacci);
        System.out.println("总耗时：" + (end - start));
    }

    // 青蛙跳台阶，青蛙一次可以跳一个台阶，也可以一次跳两个
    // f(1) = 1
    // f(2) = 2
    public long frogSteps(int n) {
        long n0 = 1;
        long n1 = 1;
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return n1;
        }
        long res = 0;
        for (int i = 2; i <= n; i++) {
            res = n0 + n1;
            n0 = n1;
            n1 = res;
        }
        return res;
    }

    public long loop(int n) {
        long n1 = 0;
        long n2 = 1;
        long fin = 0;
        for (int i = 2; i <= n; i++) {
            fin = n1 + n2;
            n1 = n2;
            n2 = fin;
        }
        return fin;
    }

    public long recursion(int n) {
        if (n == 1) {
            return  1;
        }
        if (n == 0) {
            return 0;
        }
        return recursion(n-1) + recursion(n-2);
    }
}
