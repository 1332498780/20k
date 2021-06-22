package cn.hzy.demo.thread;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    static Object object = new Object();

    static class MyThread extends Thread{

        String name;
        public MyThread(String name){
            super.setName(name);
            this.name = name;
        }
        @Override
        public void run(){
            synchronized (object){
                System.out.println(name+" finished");
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new MyThread("t1");
        Thread t2 = new MyThread("t2");

        t1.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);


        t1.join();
        t2.join();
    }
}
