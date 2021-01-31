package offer;

import java.util.AbstractQueue;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Stack
 * 执行用时：68 ms, 在所有 Java 提交中击败了 24.86% 的用户
 * 内存消耗：46.6 MB, 在所有 Java 提交中击败了 72.38% 的用户
 *
 * LinkedList
 * 行用时：53 ms, 在所有 Java 提交中击败了 93.86% 的用户
 * 内存消耗：47.4 MB, 在所有 Java 提交中击败了 14.33% 的用户
 *
 *
 */
public class ImplQueueUserStack_09 {
    public static void main(String[] args) {
        ImplQueueUserStack_09 a = new ImplQueueUserStack_09();
        a.appendTail(1);
        a.appendTail(2);
        a.appendTail(3);
        a.appendTail(4);
        System.out.println(a.deleteHead());
        System.out.println(a.deleteHead());
        System.out.println(a.deleteHead());
        System.out.println(a.deleteHead());
    }

    public LinkedList<Integer> stack1 = new LinkedList<>();
    public LinkedList<Integer> stack2 = new LinkedList<>();

    public void appendTail(int value) {
        stack2.push(value);
    }

    public int deleteHead() {
        if (stack1.isEmpty()) {
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
        if (stack1.isEmpty()) {
            return -1;
        }
        return stack1.pop();
    }

    // 相关，队列实现栈，
    public static class ImplStackUseQueue {

        public static void main(String[] args) {
            ImplStackUseQueue stack = new ImplStackUseQueue();
            stack.push(1);
            stack.push(2);
            stack.push(3);
            System.out.println(stack.pop());
            System.out.println(stack.pop());
            System.out.println(stack.pop());
            System.out.println(stack.pop());
        }
        public LinkedList<Integer> queue = new LinkedList<>();

        public void push(int val) {
            queue.offer(val);
            for (int i = 0; i < queue.size() - 1; i++) {
                queue.offer(queue.poll());
            }
        }

        public int pop() {
            if (queue.isEmpty()) {
                return  -1;
            }
            return  queue.poll();
        }
    }
}
