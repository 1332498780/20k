package cn.hzy.demo.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainClass {

    public static void main(String[] args){
        MainClass mainClass = new MainClass();
        mainClass.iteratorTest();
    }


    public void myArrayListTest(){
        List<Integer> list = new MyArrayList();
        list.add(123);
        list.add(234);
        list.add(345);
        list.add(456);
//        Integer tmp = Integer.valueOf(345);
//        list.remove(tmp);

//        list.add(1,999);
//        list.remove(2);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
//        iterator.next();
//        iterator.next();
//        iterator.remove();

//        list.forEach(item ->{
//            System.out.println(item);
//        });

//        for(int i=0;i<list.size();i++){
//            System.out.println(list.get(i));
//        }
    }

    public void testCopy(){
        List<Integer> list = new MyArrayList();
        list.add(123);
        list.add(234);
        list.add(345);
        list.add(456);
        Integer[] newArr = list.toArray(new Integer[5]);
        for(int i=0;i<newArr.length;i++){
            System.out.println(newArr[i]);
        }
    }

    public void testIterator(){
        List<Integer> list = new MyArrayList<>();
        list.add(123);
        list.add(234);
        list.add(345);
        list.add(456);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){

            int tmp = iterator.next();
            if(tmp == 345){
                list.add(999);
            }
            System.out.println(tmp);
        }
    }

    public void concurrentTest(){
        List<Integer> list = new ArrayList<>(2000);
        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=1000;i++){
                    list.add(i);
                }
            }
        });
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=2000;i<3000;i++){
                    list.add(i);
                }
            }
        });
        th1.start();
        th2.start();
        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        Iterator iterator = list.iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        System.out.println(list.size());
    }

    public void iteratorTest(){
        List<Integer> list = new MyArrayList<>();
        list.add(123);
        list.add(234);
        list.add(345);
        list.add(456);
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.remove();
//        iterator.remove();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
