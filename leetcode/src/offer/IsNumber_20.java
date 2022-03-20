package offer;

import java.util.Arrays;

public class IsNumber_20 {
    public static void main(String[] args) {
        IsNumber_20 isNumber_20 = new IsNumber_20();
//        System.out.println(isNumber_20.isNumber("3.1416"));
//        String[] yesArr = {"+100", "5e2", "-123", "3.1416", "-1E-16", "0123"};
//        Arrays.stream(yesArr).forEach(str -> System.out.println(isNumber_20.isNumber(str)));
//        String[] noArr = {"12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"};
//        Arrays.stream(noArr).forEach(str -> System.out.println(isNumber_20.isNumber(str)));
//        System.out.println(isNumber_20.isNumber("+.8"));
    }

    public boolean isNumber(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return false;
        }
        // 找e|E的位置做分割，分别处理
        int eLocation = -1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'e' || ch == 'E') {
                eLocation = i;
                break;
            }
        }
        // 分两部分处理，小数和指数
        char[] chars = s.toCharArray();
        String xsStr = String.valueOf(Arrays.copyOfRange(chars, 0,
                eLocation >= 0 ? eLocation : chars.length));
        if (!validateFloat(xsStr)) {
            return false;
        }
        // 有e|E情况的验证
        if (eLocation != -1) {
            String zsStr = String.valueOf(Arrays.copyOfRange(chars, eLocation + 1, chars.length));
            if (!validateInteger(zsStr)) {
                return false;
            }
        }
        return true;
    }

    private boolean validateInteger(String str) {
        if (str.length() == 0) {
            return false;
        }
        boolean containsDigit = false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (i == 0 && (ch == '+' || ch == '-' )) {
                continue;
            }
            if (!Character.isDigit(ch)) {
                return false;
            }
            containsDigit = true;
        }
        return containsDigit;
    }

    private boolean validateFloat(String str) {
        if (str.length() == 0) {
            return false;
        }
        boolean containsDigit = false;
        int dotCount = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (i == 0 && (ch == '+' || ch == '-' )) {
                continue;
            }
            if (ch == '.') {
                dotCount++;
                continue;
            }
            if (!Character.isDigit(ch)) {
                return false;
            }
            containsDigit = true;
        }
        return containsDigit && dotCount <= 1;
    }
}
