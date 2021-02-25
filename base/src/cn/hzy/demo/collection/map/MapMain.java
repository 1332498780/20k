package cn.hzy.demo.collection.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapMain {

    @Test
    public void mapTest(){
        Map<String,String> map = new HashMap<>();
        map.put("123","123");
        map.put("666","666");
        map.put("234","234");

        Set<Map.Entry<String,String>> entries =  map.entrySet();
        for(Map.Entry<String,String> entry:entries){
            System.out.println(entry.getKey()+","+entry.getValue());
        }
    }

    static int MAXIMUM_CAPACITY = 10000;


    @Test
    public void numberTest(){
        int num = tableSizeFor(256);
        System.out.println(num);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
