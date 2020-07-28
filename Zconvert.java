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
public class Zconvert {
    //巧妙的利用每一个行只打印一个字符，然后利用flag控制打印顺序
    public String convert(String s, int numRows) {
        if(numRows<2)
            return s;
        int flag = -1;
        List<StringBuilder> sb = new ArrayList();
        for(int i=0;i<numRows;i++)
            sb.add(new StringBuilder());
        int i = 0;
        for(char c:s.toCharArray()){
            if(i==0||i==numRows-1)
                flag = -flag;
            sb.get(i).append(c);
            i+=flag;
        }
        StringBuilder res = new StringBuilder();
        sb.forEach((ssb) -> {
            res.append(ssb);
        });
        return res.toString();
    }
}
