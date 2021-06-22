package cn.hzy.demo.thread;

import java.util.Random;
import java.util.concurrent.*;

public class CountDownLatchDemo {

    static CountDownLatch countDownLatch = new CountDownLatch(10);
    public static void main(String[] args){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(new Random().nextInt(10));
                    System.out.println("xxx 完成");
                    countDownLatch.countDown();
                    System.out.println(countDownLatch.getCount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            executor.submit(runnable);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有步骤完成！");

        executor.shutdown();
    }
}
