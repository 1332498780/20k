package cn.hzy.demo.question;

import org.junit.Test;

public class QueueTest {

    @Test
    public void queueTest(){
        Queue<Integer> queue = new Queue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);

        System.out.println(queue.pop());
        System.out.println(queue.pop());
        queue.push(5);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }

    @Test
    public void doubleQueueStackTest(){
        DoubleQueueStack queueStack = new DoubleQueueStack();
        queueStack.push(1);
        queueStack.push(2);
        queueStack.push(3);
        queueStack.push(4);

        System.out.println(queueStack.pop());
        System.out.println(queueStack.pop());
        queueStack.push(5);
        System.out.println(queueStack.pop());
    }


}
class Queue<T>{
    Node<T> head;
    Node<T> tail;

    public boolean isEmpty(){
        return (head==null);
    }
    public void push(T val){
        Node<T> node = new Node<>();
        node.val = val;
        if(isEmpty()){
            head = node;
            tail = head;
        }else{
            tail.next = node;
            tail = tail.next;
        }
    }
    public T pop(){
        if(isEmpty()){
            return null;
        }
        Node<T> node = head;
        head = head.next;
        node.next = null;
        return node.val;
    }
}
