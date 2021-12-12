package offer;

/**
 * 二进制中1的个数
 * 思想：
 * 不能右移目标数，如果目标数是负数，则每次右移，左边会补1，会造成循环
 * 解决办法1：
 * 每次拿1左移和每位做与操作，如果与完还是1，则证明该位是1
 * 时间复杂度O(32)/O(64)
 * 解决办法2：
 * n=(n-1)&n,目标数减一跟本身做与操作，会去掉最右边的1
 * 时间复杂度O(1的个数)
 */
public class BinaryOneCount_15 {
    public static void main(String[] args) {
        int i = new BinaryOneCount_15().hammingWeight(11);
        System.out.println(i);
    }
    public int hammingWeight(int n) {
        int count = 0;
        while (n!=0) {
            n = (n-1) & n;
            count++;
        }
        return count;
    }
    // 拿1每次左移一位,和n做与操作，如果与完还是原来数，则证明该位是1
    // 执行用时：1 ms, 在所有 Java 提交中击败了 96.90% 的用户
    // 内存消耗：35.5 MB, 在所有 Java 提交中击败了 27.42% 的用户
    public int hammingWeight1(int n) {
        int b = 1;
        int res = 0;
        while (b != 0) {
            if ((n & b) == b) {
                res++;
            }
            b = b << 1;
        }
        return res;
    }
}
