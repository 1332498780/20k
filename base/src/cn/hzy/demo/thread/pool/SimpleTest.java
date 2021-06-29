package cn.hzy.demo.thread.pool;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SimpleTest {

    public static void main(String[] args) {

        ThreadPool threadPool = new BasicThreadPool(4,3,1,5);

        threadPool.execute(()->{
            while(true){
                System.out.println("活跃线程数："+threadPool.getActiveSize());
                System.out.println("任务队列大小："+threadPool.getQueueSize());
                System.out.println("----------------------------------------------->");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Random random = new Random();
        for(int i=0;i<4;i++){
            int finalI = i;
            threadPool.execute(()->{
                try {
                    System.out.println(finalI +" 开始进入队列");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(finalI +" 执行完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutDown();
    }
}
