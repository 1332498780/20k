package stack;

import java.util.Stack;

/**
 * 栈实现队列，O(n)
 * 思想
 * 用两个栈，stack1做队列，stack2做辅助
 * 1、每次入栈，先把stack1依次出栈入stack2
 * 2、stack1.push(x);
 * 3、再把stack2依次出栈入stack1
 * 每个元素涉及两次出栈，两次入栈，时间复杂度4n->O(n)
 *
 * 缺点：每次新元素入栈，都需要清空栈，把新元素入栈后，再挪动所有元素入栈
 *      stack2只做push时的临时储备，其他时间都是空着的，空间利用率不高
 */
public class StackImplQueueON {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    public StackImplQueueON() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        stack1.push(x);
        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
            return stack1.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        return stack1.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stack1.empty();
    }

    public static void main(String[] args) {
        StackImplQueueON queue = new StackImplQueueON();
        System.out.println(queue.empty());
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        while (!queue.empty()) {
            System.out.println(queue.pop());
        }
    }
}
