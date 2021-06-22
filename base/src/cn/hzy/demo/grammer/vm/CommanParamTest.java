package cn.hzy.demo.grammer.vm;

import java.util.ArrayList;
import java.util.List;

/***
 * -Xms120m -Xmx120m
 */
public class CommanParamTest {

    private static final int kbSize = 1024 *10;

    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();

        for(int i=0;i<10000;i++){
            byte[] bytes = new byte[kbSize];
            list.add(bytes);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
