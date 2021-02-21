package cn.hzy.demo.grammer;

public class StringDemo {

    public static void main(String[] args){

        final String str1 = "ab";
        final String str2 = "cd";
        String str3 = "abcd";
        String str4 = str1 + str2;

        System.out.println(str3.equals(str4));
    }
}
