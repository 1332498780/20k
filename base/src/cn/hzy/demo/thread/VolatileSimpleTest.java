package cn.hzy.demo.thread;

import java.util.concurrent.TimeUnit;

public class VolatileSimpleTest {

    static final int max = 5;
    static volatile int val =  0;
    public static void main(String[] args) {
        new Thread(()->{
            int localVal = val;
            while (localVal < max){

                if(localVal != val){
                    System.out.println(val);
                    localVal = val;
                }
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        },"reader").start();

        new Thread(()->{
            int localVal = val;
            while (localVal < max){
                System.out.println("val will be update with "+(++localVal));
                val = localVal;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"write").start();

    }
}
