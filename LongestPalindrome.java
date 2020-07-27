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
public class LongestPalindrome {
    
    public static void main(String[] args) {
        System.out.println(new LongestPalindrome().longestPalindrome("abbaccba"));
    }
    //动态规划解法：递归+缓存+顺序（有小到大）
    public String longestPalindrome(String s) {
        int len = s.length();
        if(len<2)
            return s;
        boolean[][] arr = new boolean[len][len];
//        String res = s.substring(0, 1);
        int begin = 0;
        int maxLen = 0;
        //init
        char[] cs = s.toCharArray();
        for(int i=0;i<len;i++)
            arr[i][i] = true;
        for(int j=1;j<len;j++){
            for(int i=0;i<j;i++){
                if(cs[i]==cs[j]){
                    if(j-i<3){
                        arr[i][j] = true;
                    }else{
                        arr[i][j] = arr[i+1][j-1];
                    }
                }else{
                    arr[i][j] = false;
                }
                
                if(arr[i][j]&&(j-i+1>maxLen)){
                    begin = i;
//                    res = s.substring(i, j+1);
                    maxLen = j-i+1;
                }
            }
        }    
      return s.substring(begin, maxLen);
    }
}
