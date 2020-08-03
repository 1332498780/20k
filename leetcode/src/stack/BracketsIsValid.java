package stack;

import java.util.ArrayList;

public class BracketsIsValid {
    public static void main(String[] args) {
        BracketsIsValid b = new BracketsIsValid();
        System.out.println(b.isValid("]"));
    }
    public boolean isValid(String s) {
        ArrayList<Character> list = new ArrayList<>(s.length()/2);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '}':
                    if (list.size() >= 1) {
                        if (list.get(list.size() - 1) != '{') {
                            return false;
                        }
                        list.remove(list.size() - 1);
                    } else {
                        return false;
                    }

                    break;
                case ')':
                    if (list.size() >= 1) {
                        if (list.get(list.size() - 1) != '(') {
                            return false;
                        }
                        list.remove(list.size() - 1);
                    } else {
                        return false;
                    }
                    break;
                case ']':
                    if (list.size() >= 1) {
                        if (list.get(list.size() - 1) != '[') {
                            return false;
                        }
                        list.remove(list.size() - 1);
                    } else {
                        return false;
                    }
                    break;
                default:
                    list.add(c);
            }
        }
        if (list.size() > 0) {
            return false;
        }
        return true;
    }

}
