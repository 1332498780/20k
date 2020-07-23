package h202007;

import java.util.ArrayList;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1: "abcabcbb" "abc" 3
 *
 * 示例 2： "bbbbb" "b" 1
 *
 * 示例 3： "pwwkew" "wke" 3
 */

public class MaxLengthOfSubStr {
    public static void main(String[] args) {
        MaxLengthOfSubStr str = new MaxLengthOfSubStr();
//        System.out.println(str.lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(str.lengthOfLongestSubstring("bbbbb"));
        System.out.println(str.lengthOfLongestSubstring("pwwkewaa"));
    }

    /**
     * 改进方式：
     * 1、字符串转为数组，自己写查重方法
     * 2、还可以再优化点，就是第一次遇到重复字符后，确定了首次最大长度（无重复字符长度），然后从重复字符的下标到 下标+最大长度，
     * 这个区间范围的字串“倒序”查重（此时遍历下标改为下标+最大长度），如果没重复继续按你之前逻辑下一个字符下一个字符的查重（这时最大长度在变大），
     * 如果有重复（倒序查重会比减少很多无所谓查重，相比顺序来说，更专注于找最大字串的目的），
     * 则从重复字符的后一个下标，到下标+最大长度这个区间范围查重...依次类推，这种转为循环
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int maxLen = 1;
        int tempLen = maxLen;
        int start =0;
        for (int i = 1; i < s.length(); i++) {
            int existsIndex = s.substring(start,i).indexOf(s.charAt(i));
            if (existsIndex != -1) {    // 遇到重复字符
                // 存入最大长度
                if (tempLen > maxLen) {
                    maxLen = tempLen;
                }
                // 直接跳到重复的字符下一个字符作为字串的开头
                start = existsIndex + start + 1;
                // 临时长度重新计算
                tempLen = s.substring(start, i).length() + 1;
            } else {
                tempLen++;
            }
        }
        if (tempLen > maxLen) {
            return tempLen;
        }
        return maxLen;
    }


    /**
     * 最low方式
    * @param s
     * @return
     */
//    public int lengthOfLongestSubstring(String s) {
//        int maxLen = 0;
//        char[] chars = s.toCharArray();
//        for (int i=0; i<chars.length; i++) {
//            ArrayList tmp = new ArrayList();
//            tmp.add(chars[i]);
//            for (int j = i + 1; j < chars.length; j++) {
//                if (tmp.contains(chars[j])) {
//                    break;
//                } else {
//                    tmp.add(chars[j]);
//                }
//            }
//            maxLen =  tmp.size() > maxLen ? tmp.size() : maxLen;
//        }
//        return maxLen;
//    }
}
