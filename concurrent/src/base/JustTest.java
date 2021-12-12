package base;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

public class JustTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while (true) {

                }
            }
        };
//        thread.setDaemon(true);
        t1.start();
        System.out.println(t1.getState());
        Thread.sleep(500);
        System.out.println(t1.getState());
        System.out.println("main exit");
    }

    public synchronized void f1() {
        System.out.println("f1...");
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void f2() {
        System.out.println("f1...");
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread extends Thread {
    public volatile boolean stopped = false;
    @Override
    public void run() {
        while (!stopped) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("child ...");
        }
    }
    public void stoped() {
        this.stopped = true;
    }
}
