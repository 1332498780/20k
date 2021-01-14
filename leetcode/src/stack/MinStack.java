package stack;

import java.util.Stack;

/**
 * 最小栈
 * getMin() —— 检索栈中的最小元素。
 * 思想：
 * 用辅助栈记录最小元素栈，不同步
 */
public class MinStack {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    public MinStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
        if (stack2.empty() || stack2.peek() >= x) {
            stack2.push(x);
        }
    }

    public void pop() {
        if (stack1.pop().equals(stack2.peek())) {
            stack2.pop();
        }
    }

    public int top() {
        return stack1.peek();
    }

    public int getMin() {
        return stack2.peek();
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(512);
        stack.push(-1024);
        stack.push(-1024);
        stack.push(512);
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());

    }
}
