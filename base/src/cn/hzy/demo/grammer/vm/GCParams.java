package cn.hzy.demo.grammer.vm;


import java.util.ArrayList;
import java.util.List;

/**
 * 查看当前使用的垃圾回收器
 *
 * -XX:+PrintCommandLineFlags
 */
public class GCParams {

    public static void main(String[] args) throws InterruptedException {
        List list = new ArrayList();
        while(true){
            list.add(new byte[100]);
            Thread.sleep(1000);
        }
    }
}
