package cn.hzy.demo.grammer.vm;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class FinalizeTest {

    public  static FinalizeTest obj;

    @Override
    public void finalize(){
        System.out.println("调用了finalize()方法");
        obj = this;
    }

    public static void main(String[] args) {
        obj = new FinalizeTest();

        obj = null;
        System.gc();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(obj == null){
            System.out.println("obj is dead");
        }else {
            System.out.println("obj is alieved");
        }

        obj = null;
        System.gc();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(obj == null){
            System.out.println("obj is dead");
        }else {
            System.out.println("obj is alieved");
        }

    }

}
