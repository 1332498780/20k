package cn.hzy.demo.thread;

import java.util.concurrent.Semaphore;

/**
 * 允许指定个线程同时访问
 */
public class SemaphoreDemo {

    static Semaphore semaphore = new Semaphore(2,false);
    public static void main(String[] args){

        for(int i=0;i<8;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    semaphore.release();
                }
            },i+"");
            thread.start();
        }
    }
}
