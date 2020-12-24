package other;

import base.Single;

import java.util.Date;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        ThreadLocal<String> t = new ThreadLocal<>();
        t.set("aa");
        HashMap<String,String> mm = new HashMap<>();
        mm.put("a","a");
    }
}

class A extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

class B implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

