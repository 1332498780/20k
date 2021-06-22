package cn.hzy.demo.question;


import org.junit.Test;

public class StackTest {

    /***
     * 双栈声明队列测试
     */
    @Test
    public void doubleStackQueueTest(){
        DoubleStackQueue.appendTail(1);
        DoubleStackQueue.appendTail(2);
        DoubleStackQueue.appendTail(3);
        DoubleStackQueue.appendTail(4);

        System.out.println(DoubleStackQueue.deleteHead());
        System.out.println(DoubleStackQueue.deleteHead());
        DoubleStackQueue.appendTail(5);
        System.out.println(DoubleStackQueue.deleteHead());
        System.out.println(DoubleStackQueue.deleteHead());
        System.out.println(DoubleStackQueue.deleteHead());
        System.out.println(DoubleStackQueue.deleteHead());
    }
}
/***
 * 自定义栈结构
 * @param <T>
 */
class Stack<T>{
    Node<T> curr;

    public boolean isEmpty(){
        return (curr == null);
    }
    public void push(T val){
        Node node = new Node();
        node.val = val;
        if(!isEmpty()){
            node.next = curr;
        }
        curr = node;
    }
    public T pop(){
        if(isEmpty()){
            return null;
        }
        Node<T> node = curr;
        curr = curr.next;
        node.next = null;
        return node.val;
    }
}
class Node<T>{
    T val;
    Node<T> next;
}