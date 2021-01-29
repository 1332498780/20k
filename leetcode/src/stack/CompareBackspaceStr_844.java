package stack;

/**
 * 比较含退格的字符串
 * 思想
 * 逆序遍历，用skip记录要跳过的字符，待到能确认不被删除的字符时，做比较
 */
public class CompareBackspaceStr_844 {
    public static void main(String[] args) {
        System.out.println(new CompareBackspaceStr_844().
                backspaceCompare("a##c","#a#c"));
//        System.out.println(new CompareBackspaceStr_844().
//                backspaceCompare("ab##","c#d#"));
    }


    public boolean backspaceCompare(String S, String T) {
        int skipS=0,skipT =0;
        int i=S.length()-1, j=T.length()-1;
        while (i >= 0 && j >= 0) {
            while (i>=0 && S.charAt(i) == '#') {
                while (i>=0 && S.charAt(i) == '#') {
                    i--;
                    skipS++;
                }
                while (skipS > 0) {
                    if (i>=0 && S.charAt(i) == '#') {
                        i--;
                        skipS++;
                        continue;
                    }
                    skipS--;
                    i--;
                }
                if (i < 0) {
                    break;
                }
            }
            while (j>=0 && T.charAt(j) == '#') {
                while (j>=0 && T.charAt(j) == '#') {
                    j--;
                    skipT++;
                }
                while (skipT > 0) {
                    if (j>=0 && T.charAt(j) == '#') {
                        j--;
                        skipT++;
                        continue;
                    }
                    skipT--;
                    j--;
                }
                if (j < 0) {
                    break;
                }
            }

            if (i < 0 || j < 0) {
                return (i>=0 || j>=0) ? false : true;
            } else {
                if (S.charAt(i) != T.charAt(j)) {
                    return false;
                }
            }
            i--;
            j--;
        }
        return true;
    }

//    // 字符串逆序
//    public String build(String s) {
//
//    }

//    // 字符串正序
//    public String build(String s) {
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) != '#') {
//                builder.append(s.charAt(i));
//            } else if (builder.length() > 0) {
//                builder.deleteCharAt(builder.length() -1);
//            }
//        }
//        return builder.toString();
//    }
//    // 栈实现
//    public boolean backspaceCompare(String S, String T) {
//        Stack<Character> s = new Stack<>();
//        Stack<Character> t = new Stack<>();
//        for (int i = 0; i < S.length(); i++) {
//            Character c = S.charAt(i);
//            if (c == '#') {
//                if (!s.empty()) {
//                    s.pop();
//                }
//            } else {
//                s.push(c);
//            }
//        }
//        for (int i = 0; i < T.length(); i++) {
//            Character c = T.charAt(i);
//            if (c == '#') {
//                if (!t.empty()) {
//                    t.pop();
//                }
//            } else {
//                t.push(c);
//            }
//        }
//        int size = s.size();
//        if (size != t.size()) {
//            return false;
//        }
//
//        for (int i = 0; i < size; i++) {
//            if (s.pop() != t.pop()) {
//                return false;
//            }
//        }
//        return  true;
//    }
}
