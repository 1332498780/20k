package cn.hzy.demo.thread;

public class SimpleTest {

    public static void main(String[] args) {
        SimpleRunnable simpleRunnable = new SimpleRunnable();
        Thread t1 = new Thread(simpleRunnable,"t1");
        Thread t2 = new Thread(simpleRunnable,"t2");
        t1.start();
        t2.start();
    }
}
class SimpleRunnable implements Runnable{
    @Override
    public void run() {
        synchronized(SimpleTest.class){
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
