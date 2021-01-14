package stack;

import java.util.Stack;

/**
 * 栈实现队列，摊还算法O(1)
 * 思想
 * 用两个栈，stack1做push，stack2做pop，stack2空的话再依次stack1出栈入stack2
 * 优点：
 * stack2已经保持队列的特性（最先入栈的在栈顶），不需要每次都挪动元素到stack2，
 * 等stack2出栈完了，再依次stack1出栈入stack2
 *
 */
public class StackImplQueueApportion {
    private int front;
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    public StackImplQueueApportion() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        if (stack1.empty()) {
            front = x;
        }
        stack1.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.empty()) {
            return 0;
        }
        return stack2.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (!stack2.empty()) {
            return stack2.peek();
        }
        return front;
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stack1.empty() && stack2.empty();
    }
}
