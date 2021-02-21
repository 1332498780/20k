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
}
