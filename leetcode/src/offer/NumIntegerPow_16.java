package offer;

/**
 * 数值的整数次幂
 * 思想：
 * 1、n次幂，十进制n->二进制m，进而移位计算
 * 2、二分法，10->5*5->(2*3)*5->((1*1)*(1*2))*5
 */
// todo 未完成
public class NumIntegerPow_16 {
    public static void main(String[] args) {
        double v = new NumIntegerPow_16().myPow(-1.00000, -2147483646);
        System.out.println(v);
    }
    public double myPow(double x, int n) {
        double res = 1;
        if (n == 0 || x == 1) {
            return 1;
        } else{
            if (x == -1) {
                return n % 2 == 0 ? 1 : -1;
            }
            boolean negative = false;
            if (n < 0) {
                n = -1 * n;
                negative = true;
            }
            if (x == 2) {
                res = 2 << (n-1);
            } else {
                for (int i = 0; i < n; i++) {
                    res = res * x;
                }
            }
            if (negative) {
                if (res == 0) {
                    return 0;
                } else {
                    return 1 / res;
                }
            } else {
                return res;
            }
        }
    }
}
