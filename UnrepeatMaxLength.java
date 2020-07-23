/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hh;

/**
 *
 * @author ZCG04000034
 */
public class UnrepeatMaxLength {
  
    int lengthOfLongestSubstring(String s) {
        if(s.length() == 0)
              return 0;
        int maxLen=1;
        int tempLen = maxLen;
        int start = 0;
        for(int i=1; i<s.length();i++){
            int existsIndex = s.substring(start, i).indexOf(s.charAt(i));
            if(existsIndex != -1){ //遇到重复字符                
                //存入最大长度；
                if(tempLen > maxLen)
                        maxLen = tempLen;
                //直接跳到重复的字符下一个字符作为子串的开头
                start = existsIndex+ start +1;
                //临时长度重新计算
                tempLen = s.substring(start, i).length()+1;
            }else{
               tempLen++; 
            }
        }
        if(tempLen>maxLen)
                return tempLen;
        return maxLen;
    }
}
