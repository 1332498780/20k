package queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列实现栈
 * 队列：先进先出（最先排队的最先出来）
 * 栈： 先进后出（最新放进去的最先出来）
 * 思想：
 * 队列元素头尾元素依次反转即可实现栈操作
 * 两个队列 queue1 queue2
 * queue1作为栈使用，queue2辅助入队
 * 每次新元素入队，先入queue2,然后queue1依次出队后入队queue2,然后指针交换queue1和queue2
 *
 */
public class TwoQueueImplStack {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public TwoQueueImplStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue2.offer(x);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        Queue<Integer> tmp = queue1;
        queue1 = queue2;
        queue2 = tmp;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue1.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty();
    }

    public int size() {
        return queue1.size();
    }

    public static void main(String[] args) {
        TwoQueueImplStack queue = new TwoQueueImplStack();
//        System.out.println(queue.empty());
//        System.out.println(queue.top());
//        queue.pop();
//        System.out.println(queue.pop());
        queue.push(1);
        System.out.println(queue.pop());
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        int size = queue.size();
        for (int i=0; i<size; i++) {
            System.out.println(queue.pop());
        }
    }
}
