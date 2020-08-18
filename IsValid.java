/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hh;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author ZCG04000034
 */
public class IsValid {
    
    public static void main(String[] args) {
        System.out.println(new IsValid().isValid1("["));
    }
    
    //官方的栈操作
    public boolean isValid1(String s) {
        if(s.length()==0)
            return true;
        Map<Character,Character> map = new HashMap();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        
        Stack<Character> stack = new Stack();
        for(Character c:s.toCharArray()){
            if(map.containsKey(c)){
                stack.push(c);
            }else{
                if(stack.isEmpty()||!map.get(stack.pop()).equals(c)){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    
    //自己做的非栈，操作也是模式栈的的过程
    public boolean isValid(String s) {
        if(s.length()==0)
            return true;
        String lefts = "([{";
        String rights = ")]}";
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(lefts.contains(s.subSequence(i, i+1))){
                sb.append(s.subSequence(i, i+1));
            }else{
                int len = sb.toString().length();
                if(i-1<0||len==0)
                    return false;
                if(lefts.indexOf(sb.substring(len-1, len))==rights.indexOf(s.substring(i, i+1))){
                    sb.deleteCharAt(len-1);
                }else{
                    return false;
                }
            }
        }
        if(sb.toString().length()==0)
            return true;
        return false;
    }
}
