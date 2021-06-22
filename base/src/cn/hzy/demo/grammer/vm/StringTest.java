package cn.hzy.demo.grammer.vm;

import com.sun.xml.internal.fastinfoset.tools.XML_SAX_StAX_FI;

public class StringTest {


    public static void main(String[] args) {
//        appendTest(); //19088
//        appendTest1(); //15

//        internTest();
        internTest1();
    }

    public static void stringTest(){
        String str1 = "hello";
        String str2 = "world";
        String str3 = "helloworld";
        String str4 = str1 + str2;
        String str5 = "hello" + "world";
        System.out.println(str4 == str3); //false
        System.out.println(str5 == str3); //true

        String str6 = new String("hello");
        String str7 = new String("hello").intern();

        System.out.println(str1 == str6); //false
        System.out.println(str1 == str7); //true
    }

    public static void appendTest(){

        long start = System.currentTimeMillis();
        String str = "";
        for(int i=0;i<100000;i++){
            str += i;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void appendTest1(){

        long start = System.currentTimeMillis();
        StringBuilder str = new StringBuilder();
        for(int i=0;i<100000;i++){
            str.append(i);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void internTest(){

        String str1 = new String("1");
        str1.intern();
        String str2 = "1";
        System.out.println(str1 == str2); //jdk6:false jdk7/8:false

        String str3 = new String("1") + new String("1");
        str3.intern();
        String str4 = "11";
        System.out.println(str3 == str4); //jdk6:false jdk7/8:true
    }

    static final int maxIndex = 1000000;
    static final String[] arrays = new String[maxIndex];
    public static void internTest1(){
        long start = System.currentTimeMillis();
        for(int i=0;i<maxIndex;i++){

            arrays[i] = new String(String.valueOf(i%10));
//            arrays[i] = new String(String.valueOf(i%10)).intern();
        }
        long end = System.currentTimeMillis();
//        System.out.println(end - start);
//        try {
//            Thread.sleep(1000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.gc();
    }
}
