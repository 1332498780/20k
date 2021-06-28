package cn.hzy.demo.thread;

import java.util.concurrent.TimeUnit;

public class InterruptedTest {

    public static void main(String[] args) {

        Thread t = new Thread(()->{
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().isInterrupted());
//            while(true){
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    System.out.println("I was interrupted by someone");
//                }
//            }

        });

        t.setDaemon(true);
        t.start();
        try {
            TimeUnit.MICROSECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        t.interrupt();
        System.out.println("--> "+t.isInterrupted());
    }

}

