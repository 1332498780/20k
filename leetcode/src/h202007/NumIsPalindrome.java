package h202007;

public class NumIsPalindrome {
    public static void main(String[] args) {
        NumIsPalindrome p = new NumIsPalindrome();
        System.out.println(p.isPalindrome(10101));
    }

    // 官方解法，反转一半数字
    public boolean isPalindrome(int x) {
        // 排除特殊情况：负数&&个位数是0
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (revertedNumber < x) {
            revertedNumber = revertedNumber *10 + x % 10;
            x = x / 10;
        }
        return revertedNumber == x || revertedNumber/10 == x;
    }

    // 自己想法
    // 还是比较首位数字是否相等
    // 中间有零的情况， /tmp会自动补0
    public boolean isPalindrome2(int x) {
        // 排除特殊情况：负数&&个位数是0
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        // 循环一遍找出最大位📔
        int tmp = 1;
        while (x / tmp >= 10) {
            tmp *= 10;
        }
        while (x > 0) {
            if (x / tmp != x % 10) {
                return false;
            }
            x = x % tmp / 10;
            tmp /= 100;
        }
        return true;
    }

    // 转字符串解法
    public boolean isPalindrome3(int x) {
        if (x < 0) return false;
        String s = String.valueOf(x);
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
