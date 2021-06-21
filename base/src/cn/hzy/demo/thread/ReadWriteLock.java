package cn.hzy.demo.thread;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {

    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    static Integer val;

    public static void main(String[] args){

        for(int i=0;i<20;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    readLock.lock();
                    try {
                        Thread.sleep(1000);
                        System.out.println(val);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        readLock.unlock();
                    }

                }
            });
            thread.start();
        }
        Random random = new Random();
        for (int i=0;i<30;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    writeLock.lock();
                    val = random.nextInt(100);
                    writeLock.unlock();
                }
            });
            thread.start();
        }
    }
}
