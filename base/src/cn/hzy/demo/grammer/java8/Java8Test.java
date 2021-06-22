package cn.hzy.demo.grammer.java8;

import org.junit.Test;

import java.util.Comparator;

public class Java8Test {

    @Test
    public void lambda1(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable 1");
            }
        };
        runnable.run();
        System.out.println("************");

        Runnable runnable2 = () -> System.out.println("runnable 2");
        runnable2.run();
    }

    @Test
    public void lambda2(){


        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        System.out.println(comparator1.compare(11,23));
        System.out.println("************");

        Comparator<Integer> comparator2 = (o1, o2) -> Integer.compare(o1,o2);
        System.out.println(comparator2.compare(13,11));

        //方法引用
        Comparator<Integer> comparator3 = Integer::compareTo;
        System.out.println(comparator3.compare(10,9));
    }

    @Test
    public void lambda3(){
        //对于含有多个方法的接口，lambda就不好使了
//        TestInterface testInterface1 = (str) -> System.out.println("runnable 2");


    }
}
