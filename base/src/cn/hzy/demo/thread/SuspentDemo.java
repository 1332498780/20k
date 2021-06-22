package cn.hzy.demo.thread;

public class SuspentDemo {

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
                Thread.currentThread().suspend();
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
        t1.resume();
        t2.resume();

        t1.join();
        t2.join();
    }
}
