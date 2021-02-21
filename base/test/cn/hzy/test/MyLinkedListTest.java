package cn.hzy.test;


import cn.hzy.demo.collection.list.MyLinkedList;
import org.junit.Test;

import java.util.*;

public class MyLinkedListTest {

    @Test
    public void testContains(){

        List<Integer> list = new ArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);

        List<Integer> collection = Collections.emptyList();
        System.out.println(list.addAll(1,collection));
    }

    @Test
    public void testNull(){
        List<Integer> list = new ArrayList<>(3);
        list.add(null);
        list.add(null);
        list.add(null);

        list.forEach(item ->{
            System.out.println(item);
        });
    }

    @Test
    public void regularTest(){
        List<Integer> list = new LinkedList<>();
        list.add(123);
        list.add(234);
        list.add(345);
        list.add(456);

        list.remove(Integer.valueOf(234));

        List collection = Arrays.asList(new Integer[]{666,999});
        list.addAll(1,collection);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }


}
