package cn.hzy.test;

import java.util.Iterator;

public class TryCatchTest {

    static int xy =12;

    public void test(){
        Object c = 1;
        try {
            System.out.println("1");
            int a = 10/0;
            return;
        }catch (Exception e){
            System.out.println("2");
        }finally {
            System.out.println("3");
        }
        System.out.println("4");
    }

    public static void main(String[] args) {
        TryCatchTest test = new TryCatchTest();
        test.test();
    }
}
