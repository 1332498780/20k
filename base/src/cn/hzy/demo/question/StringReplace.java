package cn.hzy.demo.question;

import java.util.Arrays;

public class StringReplace {

    public static void main(String[] args) {

        System.out.println(replace1("We are family ! "));
    }

    public static String replace(String str){

        StringBuilder sb = new StringBuilder();
        char target = ' ';
        String template = "20%";
        for(char c:str.toCharArray()){
            if(c == target){
                sb.append(template);
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /***
     * 不借助额外空间完成，且时间复杂度是O(n)
     * @param str
     * @return
     */
    public static String replace1(String str){
        char[] chars = Arrays.copyOf(str.toCharArray(),50);
        //统计空格个数
        char template = ' ';
        int count = 0;
        for(char c:chars){
            if(c==template){
                count++;
            }
        }
        if(count==0){
            return str;
        }
        int pointerIndex1 = str.length() -1; //原始数组最后一个元素
        int pointerIndex2 = pointerIndex1 + count *2; //移动数组最后一个元素
        while(count-- != 0){
            while(chars[pointerIndex1] != template){
                chars[pointerIndex2--] = chars[pointerIndex1--];
            }
            chars[pointerIndex2--] = '%';
            chars[pointerIndex2--] = '0';
            chars[pointerIndex2--] = '2';
            pointerIndex1--;
        }
        return String.valueOf(chars);
    }
}
