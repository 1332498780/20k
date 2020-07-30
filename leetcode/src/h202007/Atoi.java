package h202007;

public class Atoi {
    public static void main(String[] args) {
        Atoi atoi = new Atoi();
        int a = atoi.myAtoi("-91283472332");
        System.out.println(a);
    }
    // 0  1  2  3  4  5  6  7  8  9
    // 48 49 50 51 52 53 54 55 56 57
    // 只能有字符 ' ','-','+','0-9'
    //
    public int myAtoi(String str) {
        int res = 0;
        int sings = 1;              // 存储符号位
        boolean zf = false;         // 第一次遇到符号标识
        boolean num = false;        // 第一次遇到数字标识
        boolean nonZero = false;    // 数字开头往后遇到非0的标识
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 未遇到数字，找一位非空字符
            if (num == false) {
                if (c == ' ') {
                    continue;
                } else {
                    // 未遇到符号
                    if (zf == false) {
                        // 第一个非空字符，非符号
                        if (c != '-' && c != '+') {
                            // 非符号，也不在[0-9]区间，无法转换
                            if (c < 48 || c > 57) {
                                return 0;
                            } else {    // 第一个非空字符是数字
                                num = true;
                                // 如果数字开始为0
                                if (c-48 != 0) {
                                    nonZero = true;
                                    res = c-48;
                                }
                            }
                        } else {    // 第一个非空字符是符号
                            zf = true;
                            // 存储符号位
                            sings = c == '-' ? -1*sings : sings;
                        }
                    } else {    // 遇到符号位，第二位非空字符，只能为数字
                        if (c < 48 || c > 57) {
                            return 0;
                        } else {
                            num = true;
                            // 如果数字开始为0
                            if (c-48 != 0) {
                                nonZero = true;
                                res = c-48;
                            }
                        }
                    }
                }
            } else {    // 已经遇到数字
                // 如果再遇到非数字，则不连续，无法转换，返回之前转换好的
                if (c < 48 || c > 57) {
                    return res * sings;
                } else {    // 还是数字，接着转换
                    if (nonZero == false || (c- 48 == 0)) {     // 还没遇到第一个非0数字
                        continue;
                    } else {
                        nonZero = true;     // 已经遇到第一个非0
                        // 溢出判断 [2147483647, -2147483648]
                        if (sings > 0) {
                            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (c - 48) > 7)) {
                                return Integer.MAX_VALUE;
                            }
                        } else {
                            if (res * sings < Integer.MIN_VALUE / 10) {
                                return Integer.MIN_VALUE;
                            }
                        }
                        res = res * 10 + (c-48);
                    }
                }
            }
        }
        return res * sings;
    }
}
