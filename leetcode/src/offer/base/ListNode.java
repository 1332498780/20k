package offer.base;

import offer.ReverseLinkedList_24;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }
    public ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }

    public static void main(String[] args) {
        int[] nodes = {1,2,3,4,5,6};
        ListNode head = ListNode.buildNode(nodes);
        System.out.println(head);
    }

    public static ListNode buildNode(int[] nodes) {
        if (nodes == null || nodes.length == 0) {
            return null;
        }
        ListNode preHead = new ListNode(0);
        ListNode p = preHead;
        for (int i = 0; i < nodes.length; i++) {
            p.next = new ListNode(nodes[i]);
            p = p.next;
        }
        return preHead.next;
    }


    @Override
    public String toString() {
        ListNode p = this;
        StringBuilder stringBuilder = new StringBuilder();
        while (p != null) {
            stringBuilder.append(p.val);
            p = p.next;
            if (p != null) {
                stringBuilder.append("->");
            }
        }
        return stringBuilder.toString();
    }
}
