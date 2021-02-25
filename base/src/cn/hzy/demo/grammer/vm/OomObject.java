package cn.hzy.demo.grammer.vm;

import java.util.ArrayList;
import java.util.List;

public class OomObject {

    private byte[] bytes = new byte[64*1024];

    public static void construct() throws InterruptedException {
        Thread.sleep(40000);

        List<OomObject> list = new ArrayList<>();
        for(int i=0;i<1000;i++){
            Thread.sleep(50);
            list.add(new OomObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        construct();
    }
}
