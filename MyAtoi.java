/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hh;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ZCG04000034
 */
public class MyAtoi {
    public static void main(String[] args) {
        System.out.println(new MyAtoi().myAtoi("2147483646"));
        System.out.println("max:"+Integer.MAX_VALUE+",min:"+Integer.MIN_VALUE);
        
    }
//    public int myAtoi(String str) {
////        ^[^a-zA-Z]\\s*-*\\d+
//        Pattern pattern = Pattern.compile("^(-|\\s|\\+)*\\d+");
//        Matcher matcher = pattern.matcher(str);
//        while(matcher.find()){
//            String g = matcher.group();
//            if(g.trim().matches("^(\\+|-)?\\d+")){
//                Long l = Long.valueOf(g.trim());
//                if(l>Math.pow(2,31)-1)
//                    return Integer.MAX_VALUE;
//                if(l<-Math.pow(2,31))
//                    return Integer.MIN_VALUE;
//                return l.intValue();
//            }
//        }
//        return 0;
//    }
    
    public int myAtoi(String str){
        boolean positiveNum = true;
        int ans = 0;
        String currVal = "start";
//        Map<String,String[]> map = new HashMap();
//       map.put("start", new String[]{"start","signed","counting","end"});
//       map.put("signed", new String[]{"end","end","counting","end"});
//       map.put("counting", new String[]{"end","end","counting","end"});
//       map.put("end", new String[]{"end","end","end","end"});
        for(char c: str.toCharArray()){
//            int ascii = c+0;
            if(currVal.equals("start")){
                if(c >='0' && c<= '9'){
                    currVal = "counting";
                    int add = (int)c - (int)'0';
                    if(positiveNum){
                        if(ans>(Integer.MAX_VALUE-add)/10){
                            ans = Integer.MAX_VALUE;
                            break;
                        }
                    }else{
                        if(-ans < (Integer.MIN_VALUE+add)/10){
                            ans = Integer.MIN_VALUE;
                            break;
                        }
                    }
                    ans = ans*10+add;
                }else if(c == ' '){
                    
                }else if(c == '+'|| c== '-'){
                    currVal = "signed";
                    if(c == '-')
                        positiveNum = false;
                }else{
                    currVal = "end";
                }
            }else if(currVal.equals("signed")){
                if(c >='0' && c<= '9'){
                    currVal = "counting";
                    int add = (int)c - (int)'0';
                    if(positiveNum){
                        if(ans>(Integer.MAX_VALUE-add)/10){
                            ans = Integer.MAX_VALUE;
                            break;
                        }
                    }else{
                        if(-ans < (Integer.MIN_VALUE+add)/10){
                            ans = Integer.MIN_VALUE;
                            break;
                        }
                    }
                    ans = ans*10+add;
                }else{
                    currVal = "end";
                }
            }else if(currVal.equals("counting")){
                if(c >='0' && c<= '9'){
                    currVal = "counting";
                    int add = (int)c - (int)'0';
                    if(positiveNum){
                        if(ans>(Integer.MAX_VALUE-add)/10){
                            ans = Integer.MAX_VALUE;
                            break;
                        }
                    }else{
                        if(-ans < (Integer.MIN_VALUE+add)/10){
                            ans = Integer.MIN_VALUE;
                            break;
                        }
                    }
                    ans = ans*10+add;
                }else{
                    currVal = "end";
                }
            }else if(currVal.equals("end")){
                break;
            }
        }
        return positiveNum?ans:-ans;
    }
}
