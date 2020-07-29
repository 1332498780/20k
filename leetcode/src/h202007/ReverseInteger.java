package h202007;

public class ReverseInteger {
    public static void main(String[] args) {
        ReverseInteger r = new ReverseInteger();
//        System.out.println(r.reverse(1534236469));
        System.out.println(r.reverse(-2147483648));
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(-123 % 10);
    }

    // 2147483647
    // -2147483648
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int tmp = x % 10;
            x = x / 10;
            // 不能根据x值来判断
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && tmp > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && tmp < -8)) {
                return 0;
            }
            rev = 10 * rev + tmp;
        }
        return rev;
    }

}
