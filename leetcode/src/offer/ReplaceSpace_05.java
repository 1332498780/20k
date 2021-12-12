package offer;

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"
 * 思路：
 * 从前往后替换，需要往后移动的次数多，=> 换成从后往前替换
 */
public class ReplaceSpace_05 {
    public static void main(String[] args) {
        /*String s = new ReplaceSpace_05().reltion();
        System.out.println(s);*/
        String s = "We are happy.";
        String replace = s.replace(" ", "%20");
        System.out.println(replace);
    }

    // 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
    // 内存消耗：36.3 MB, 在所有 Java 提交中击败了 46.53% 的用户
    public String replaceSpace(String s) {
        int spaceCount = 0;
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == ' ') {
                spaceCount++;
            }
        }
        char[] result = new char[s.length()+2*spaceCount];
        int index=0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                result[index++] = '%';
                result[index++] = '2';
                result[index++] = '0';
            } else {
                result[index++] = s.charAt(i);
            }
        }

        return String.valueOf(result);
    }

    // 从后向前遍历
    public String reltion() {
        char[] a = new char[13];
        char[] b = new char[8];
        a[0] = 'a';
        b[0] = 'b';
        a[1] = 'c';
        b[1] = 'd';
        a[2] = 'e';
        b[2] = 'f';
        a[3] = 'g';
        b[3] = 'h';
        a[4] = 'i';
        b[4] = 'i';
        b[5] = 'j';
        b[6] = 'k';
        b[7] = 'l';
        System.out.println(a);
        System.out.println(b);
        int ai = 4;
        int bi = b.length-1;
        int ri = a.length -1;
        while (ri >= 0 && ai >= 0 && bi >=0) {
            if (a[ai] >= b[bi]) {
                a[ri] = a[ai];
                ai--;
            } else {
                a[ri] = b[bi];
                bi--;
            }
            ri--;
        }
        if (ai < 0) {
            while (bi >= 0) {
                a[ri] = b[bi];
                ri--;
                bi--;
            }
        } else {
            while (ai >= 0) {
                a[ri] = a[ai];
                ri--;
                ai--;
            }
        }
        return String.valueOf(a);
    }

    // 与题目不符
    public String replaceSpace_0(String s) {
        if (s == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            builder.append(c == ' ' ? "%20" : c);
        }
        return builder.toString();
    }
}
