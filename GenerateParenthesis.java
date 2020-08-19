/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hh;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ZCG04000034
 */
public class GenerateParenthesis {
    
    public static void main(String[] args) {
        System.out.println(new GenerateParenthesis().generateParenthesis1(3));
    }
    
    //暴力解法：回溯，存在可优化的地方
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList();
        generateAll(new char[2*n], 0, res);
        return res;
    }
    public void generateAll(char[] cs,int pos,List<String> list){
        if(cs.length == pos){
            if(isValid(cs))
                list.add(new String(cs));
            return;
        }
        cs[pos] = '(';
        generateAll(cs, pos+1, list);
        cs[pos] = ')';
        generateAll(cs, pos+1, list);
    }
    public boolean isValid(char[] cs){
        int blance = 0;
        for(char c:cs){
            if(c=='(')
                blance++;
            else
                blance--;
            if(blance<0)
                return false;
        }
        return blance==0;
    }
    
    //优化解法：对于左括号：最多只有3只，3只以上可以略过；对于右括号，最多只有3只，且只左括号出现了才能出现，意思是，左括号的数量大于右括号的数量
    public List<String> generateParenthesis1(int n) {
        List<String> res = new ArrayList();
        generateAll1(new char[2*n],0,0,n,res);
        return res;
    }
    
    public void generateAll1(char[] cs,int open,int close,int max,List<String> list){
        if(cs.length == open+close){
            if(isValid(cs))
                list.add(new String(cs));
            return;
        }
        if(open < max){
            cs[open+close] = '(';
            generateAll1(cs,open+1,close,max,list);
        }
        if(close<open){
            cs[open+close] = ')';
            generateAll1(cs,open,close+1,max,list);
        }
    }
    
    
    
}
