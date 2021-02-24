package cn.hzy.demo.grammer.vm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class OutOfMemoryDemo {


    public static void main(String[] args){
        int a = 1;
        OutOfMemoryDemo abc = new OutOfMemoryDemo();
        List<Integer> list = new ArrayList<>();
        list.add(123);

        if(list.size() ==  1) {
            int b = 2;
            int c = 3;
        }else{
            int d = 4;
            int e = 5;
            int f = 222;
            int g = 444;
            int h = 4;
            int i = 5;
            int j = 222;
            int k = 444;
        }
        test();
    }

    public static void test(){
        int a = 1;
        int b = 2;
        int c = 3;
    }

    /**
     * vm args: -verbose:gc -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
     */
    public void heapOutOfMemory(){
        List<OutOfMemoryDemo> list = new ArrayList<OutOfMemoryDemo>();
        while(true){
            list.add(new OutOfMemoryDemo());
        }
    }

    /**
     * vm args: -Xss128K
     *
     */
    public void stackOverflow(){
        int a = 1;
        stackOverflow();
    }

    public void threadTest(){

//        while(true){
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while(true){}
//                }
//            }).start();
//        }
    }

    /**
     * vm args: -XX:PermSize=6M -XX:MaxPermSize=6M
     */
    public void functionAreaDemo(){

        HashSet<String> set = new HashSet<String>();
        int i=0;
        while(true){
            set.add(String.valueOf(i++).intern());
        }
    }

}
