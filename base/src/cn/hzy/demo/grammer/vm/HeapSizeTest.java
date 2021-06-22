package cn.hzy.demo.grammer.vm;

public class HeapSizeTest {

    public static void main(String[] args) {


        long totalMemory = Runtime.getRuntime().totalMemory()/1024/1024;
        long maxMemory = Runtime.getRuntime().maxMemory()/1024/1024;

        System.out.println(totalMemory+","+maxMemory);

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
