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
  
    public static void main(String[] args) {
        //github:https://github.com/1332498780/20k/commit/37d052e1cb9354870e17823a663428cc9e4927de
        String str = "ianikjekfbfrllbau";
        System.out.println(new UnrepeatMaxLength().recursion(str));
    }
    
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
                if(s.length()-start < maxLen)
                        return maxLen;
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
    
    //改进第一个版本，没有用递归定位start
    int lengthOfLongestSubstring_plus(String s) {
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
                //一个美好的假想值
                int end = start + maxLen;
                if(end >= s.length())
                        return maxLen;
                //找到满足不重复的开始位置
                boolean existFlag = false;
                for(int j=end;j>i;j--){
                    int existIndex1 = s.substring(start, j).lastIndexOf(s.charAt(j));
                    if(existIndex1 != -1){
                        existFlag  = true;
                        start = start+existIndex1+1;
                        i = start;
                        break;
                    }
                }
                if(existFlag == false){
                    i = end;
                }
               tempLen = s.substring(start, i).length()+1;
            }else{
               tempLen++; 
            }
        }
        if(tempLen>maxLen)
                return tempLen;
        return maxLen;
    }
    
    /**
     * 给定字符起始位置，最大长度，然后递归找不重复子串的start，如果已经匹配到字符串尽头，就返回特殊字符-1
     * @param s 源字符串
     * @param start 子串开始下标
     * @param maxLen 当前最大子串长度
     * @param hasCheckIndex 这个字段之前的字符无需匹配，因为之前匹配过了
     * @return 
     */
    int findIndex(String s,int start,int maxLen,int hasCheckIndex){
        int end = start+maxLen;
        //到了字符串尽头，返回-1作为标识
        if(end>=s.length())
                return -1;
        boolean flag = false;
        for(int i=end;i>hasCheckIndex;i--){
            int existsIndex = s.substring(start, i).indexOf(s.charAt(i));
            if(existsIndex != -1){
                start = start+existsIndex+1;
//                hasCheckIndex = i;
                flag = true;
                break;
            }
        }
        if(flag){
            return findIndex(s,start,maxLen,start); 
        }else{
            return start;
        }
    }
    
    //改进第二版：递归找start
    int recursion(String s){
        if(s.length() < 2){
            return s.length();
        }
        int start = 0;
        int maxLen = 1;
        while(start<s.length()){
            int end = start+maxLen;
            if(end >= s.length())
                    return maxLen;
            int existsIndex = s.substring(start, end).indexOf(s.charAt(end));
            if(existsIndex != -1){
                 if(s.length()-existsIndex-1 < maxLen)
                       return maxLen;
                 start = start+existsIndex+1;
                 int lastEnd = end;
                 int startIndex = findIndex(s,start,maxLen,lastEnd);
                 if(startIndex == -1){
                     return maxLen;
                 }else{
                     start = startIndex;
                 }
             }else{
                 maxLen++;
             }
        }
        return maxLen;
    }
}
