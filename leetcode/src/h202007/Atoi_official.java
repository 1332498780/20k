package h202007;

public class Atoi_official {
    public static void main(String[] args) {
        Atoi_official a = new Atoi_official();
        System.out.println(a.myAtoi("-   234"));
    }
    private int status = 0;
    private int[][] stable = {{0,1,2,3},{3,3,2,3},{3,3,2,3}};
    private int sign = 1;
    private int res = 0;
    private  boolean signFirst;
    public int getCharNum(char c) {
        if (c == ' ') return 0;
        if (c == '+' || c == '-') return 1;
        if (Character.isDigit(c)) return 2;
        return 3;
    }
    public boolean getStatus(char c) {
        status = stable[status][getCharNum(c)];
        switch (status) {
            case 1:     // 符号位
                if (signFirst == false) {
                    signFirst = true;
                    if (c == '-')  sign = -1;
                }
                break;
            case 2:     // 数字
                if (sign > 0) {
                    if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (c - 48) > 7)) {
                        res = Integer.MAX_VALUE;
                        return true;
                    }
                } else {
                    if (res * sign < Integer.MIN_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (c - 48) > 8   )) {
                        res = Integer.MIN_VALUE;
                        return true;
                    }
                }
                res = res * 10 + (c-48);
                break;
            case 3:
                return true;
        }
        return false;
    }
    // 自动机实现，之前实现太臃肿
    // 四个状态，四类符号
    // status 输入char 变为status2，下一个状态
    public int myAtoi(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (getStatus(str.charAt(i))) {
                return res * sign;
            }
        }
        return res * sign;
    }

}
