package cn.hzy.demo.grammer.vm;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceTest {

    static PhantomReferenceTest obj;
    static ReferenceQueue queue;

    @Override
    public void finalize(){
        System.out.println("开始执行：finalize()");
        obj = this;
    }

    static class MonitorThread extends Thread{

        @Override
        public void run(){
            while(true){
                if(queue!=null){
                    try {
                        Reference<PhantomReferenceTest> reference = queue.remove();
                        System.out.println("reference 已被jvm回收");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public static void main(String[] args) {

        Thread thread = new MonitorThread();
        thread.setDaemon(true);
        thread.start();

        obj = new PhantomReferenceTest();
        queue = new ReferenceQueue<PhantomReferenceTest>();
        PhantomReference<PhantomReferenceTest> pr = new PhantomReference<>(obj,queue);

        obj = null;
        System.gc();
        System.out.println("第一次gc...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(obj == null){
            System.out.println("obj 已死");
        }else{
            System.out.println("obj 还活着");
        }

        obj = null;
        System.gc();
        System.out.println("第二次gc...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(obj == null){
            System.out.println("obj 已死");
        }else{
            System.out.println("obj 还活着");
        }
    }
}
