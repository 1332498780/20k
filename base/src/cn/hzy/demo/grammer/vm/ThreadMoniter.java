package cn.hzy.demo.grammer.vm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreadMoniter {

    public static void main(String[] args) throws IOException, InterruptedException {

        Thread.sleep(20000);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    ;
            }
        },"thread1");
        thread1.start();

        Object object = new Object();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"thread2");
        thread2.start();
    }
}
