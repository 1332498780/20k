/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ZCG04000034
 */
public class LetterCombinations {
    
    public static void main(String[] args) {
        System.out.println(new LetterCombinations().letterCombinations1("23"));
    }
    
    static Map<String,String> map = new HashMap();
    static {
        map.put("1","");
        map.put("2","abc");
        map.put("3","def");
        map.put("4","ghi");
        map.put("5","jkl");
        map.put("6","mno");
        map.put("7","pqrs");
        map.put("8","tuv");
        map.put("9","wxyz");
    }
    public List<String> appendElements(List<String> list,String s){
        if(s.equals("1"))
              return list;
        if(list.size()==0){
            String num = map.get(s);
            for(int i=0;i<num.length();i++){
                list.add(num.substring(i, i+1));
            }
            return list;
        }
        int size = list.size();
        for(int i=0;i<size;i++){
            String num = map.get(s);
            for(int j=0;j<num.length();j++){
                list.add(list.get(i)+num.substring(j, j+1));
            }
        }
        return list.subList(size, list.size());
    }
    public List<String> letterCombinations(String digits) {
         List<String> lists = new ArrayList();
         for(int i=0;i<digits.length();i++){
             lists = appendElements(lists, digits.substring(i,i+1));
         }
         return lists;
    }
    
    
    
    List<String> lists = new ArrayList();
    public void backTrace(String combination,String digit){
        if(digit.length()==0){
            lists.add(combination);
            return;
        }
        String nums = map.get(digit.substring(0, 1));
        for(int i=0;i<nums.length();i++){
            backTrace(combination+nums.substring(i,i+1), digit.substring(1));
        }
    }
    
    //回溯算法
    //穷举所有可能，有不符合的就回溯到上一步，继续搜索
    public List<String> letterCombinations1(String digits) {
        if(digits.length()>0)
                backTrace("", digits);
        return lists;
    }
    
    
    
}
