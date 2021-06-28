package cn.hzy.demo.thread;

public class SimpleTest {


    public static void main(String[] args) {
        new Thread(()->{
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}


