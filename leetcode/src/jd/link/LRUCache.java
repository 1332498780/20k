package jd.link;

public class LRUCache {
    private int cap;
    private volatile int length;
    private Node head;

    public LRUCache(int cap) {
        if (cap <= 0) {
            throw new IllegalArgumentException("Illegal Capacity: "+ cap);
        }
        this.cap = cap;
    }

    public void printLRUCache() {
        Node t = head;
        while (t != null) {
            System.out.println(t.key + ":" + t.value);
            t = t.next;
        }
    }

    public synchronized int getKey(int key) {
        if (head == null) {
            return -1;
        }
        if (head.key == key) {
            return head.value;
        }
        Node tail = head;
        while (tail.next != null) {
            if (tail.next.key == key) {
                Node n = tail.next;
                tail.next = n.next;
                n.next = head;
                head = n;
                return n.value;
            }
            tail = tail.next;
        }
        return -1;
    }

    public synchronized void putKey(int key, int value) {
        Node h = new Node(key, value);
        h.next = head;
        head = h;
        length++;
        if (length > cap) {
            Node tail = head;
            while (tail.next.next != null) {
                tail = tail.next;
            }
            tail.next = null;
            length--;
        }
    }

    private static class Node {
        private int key;
        private int value;
        private Node next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.putKey(1,1);
        lruCache.putKey(2,2);
        lruCache.putKey(3,3);
        lruCache.putKey(4,4);
        lruCache.getKey(2);
        lruCache.printLRUCache();

    }

}
