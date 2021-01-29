package stack;

import java.util.HashMap;
import java.util.Stack;

/**
 *  有效括号
 *  思想：
 *  用栈来存放左半边括号，
 *  遇到右半边括号时，与栈顶元素相对应的右半边括号比较，
 *  相同则是闭合括号，pop出队，否则为非有效字符
 *  最后检测站内是否还有括号未闭合，如果有则为非有效字符，否则为有效字符
 */
public class BracketsIsValid_20 {
    public static void main(String[] args) {
        BracketsIsValid_20 b = new BracketsIsValid_20();
        System.out.println(b.isValid("([{{}])"));
    }

    public boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (!map.containsKey(c)) {
                if (stack.empty() || map.get(stack.peek()) != c) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.empty();
    }

//    public boolean isValid(String s) {
//        ArrayList<Character> list = new ArrayList<>(s.length()/2);
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            switch (c) {
//                case '}':
//                    if (list.size() >= 1) {
//                        if (list.get(list.size() - 1) != '{') {
//                            return false;
//                        }
//                        list.remove(list.size() - 1);
//                    } else {
//                        return false;
//                    }
//
//                    break;
//                case ')':
//                    if (list.size() >= 1) {
//                        if (list.get(list.size() - 1) != '(') {
//                            return false;
//                        }
//                        list.remove(list.size() - 1);
//                    } else {
//                        return false;
//                    }
//                    break;
//                case ']':
//                    if (list.size() >= 1) {
//                        if (list.get(list.size() - 1) != '[') {
//                            return false;
//                        }
//                        list.remove(list.size() - 1);
//                    } else {
//                        return false;
//                    }
//                    break;
//                default:
//                    list.add(c);
//            }
//        }
//        if (list.size() > 0) {
//            return false;
//        }
//        return true;
//    }

}
