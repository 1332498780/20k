package offer;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 从尾到头打印链表
 *
 */
public class ReversePrintLinkedList_06 {
    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(-1);
        ListNode<Integer> t = head;
        for (int i = 0; i < 10; i++) {
            t.next = new ListNode<>(i);
            t = t.next;
        }
        int[] r =new ReversePrintLinkedList_06().reversePrint(head);
        System.out.println(r);
    }

    public int[] reversePrint(ListNode<Integer> head) {
        ListNode<Integer> node = head;
        LinkedList<Integer> stack = new LinkedList<>();
        while (node != null) {
            stack.push(node.value);
            node = node.next;
        }
        int[] result = new int[stack.size()];
        int i=0;
        while (!stack.isEmpty()) {
            result[i++] = stack.pop();
        }
        return result;
    }

    // 栈实现 递归打印
    public void reversePrintUseStack(ListNode<Integer> head) {
        LinkedList<Integer> stack = new LinkedList<>();
        ListNode<Integer> node = head;
        while (node != null) {
            stack.add(node.value);
            node = node.next;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    // 递归实现 打印
    public void reversePrintUseRecursive(ListNode head) {
        if (head.next == null) {
            System.out.println(head.value);
        } else {
            reversePrintUseRecursive(head.next);
            System.out.println(head.value);
        }
    }

//    public int[] reversePrint(ListNode head) {
//
//        return null;
//    }

    public static class ListNode<V> {
        public V value;
        public ListNode next;
        public ListNode(V value) {
            this.value = value;
        }
    }
}
