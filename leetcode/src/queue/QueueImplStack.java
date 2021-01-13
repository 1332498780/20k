package queue;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueImplStack {
    private int cap;
    private Queue<String> first;
    private Queue<String> second;
    private QueueImplStack(int cap) {
        this.cap = cap;
        first = new ArrayBlockingQueue<>(cap);
        second = new ArrayBlockingQueue<>(cap);
    }
    private boolean push(String item) {
        if (first.size() + second.size() >= cap) {
            return false;
        }
        if (first.size() == 1) {
            second.add(first.poll());
            first.add(item);
        } else if (second.size() == 1) {
            first.add(second.poll());
            second.add(item);
        } else {
            first.add(item);
        }
        return true;
    }
    private String pop() {
        if (first.size() == 0 || second.size() == 0) {
            return "";
        }
        String ret;
        if (first.size() == 1) {
            ret = first.poll();
            for (int i=0; i<second.size()-1; i++) {
                first.add(second.poll());
            }
        } else {
            ret = second.poll();
            for (int i=0; i<first.size()-1; i++) {
                second.add(first.poll());
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        QueueImplStack stack = new QueueImplStack(3);
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
