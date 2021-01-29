package test;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Test {
    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();
        map.get(null);
        LinkedHashMap<Object, Object> linkedMap = new LinkedHashMap<>();
        linkedMap.put(1,1);
        linkedMap.get("");
    }
}
