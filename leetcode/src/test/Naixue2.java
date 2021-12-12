package test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Naixue2 {
    public static void main(String[] args) {
        ReentrantLock reLock = new ReentrantLock();
        reLock.lock();
        ConcurrentHashMap<String, String> cMap = new ConcurrentHashMap<>();
        cMap.get("");
    }
}
