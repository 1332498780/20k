package cn.hzy.demo.collection.list;

import java.util.Iterator;
import java.util.List;

public class MainClass {

    public static void main(String[] args){
        MainClass mainClass = new MainClass();
        mainClass.testCopy();
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
}
