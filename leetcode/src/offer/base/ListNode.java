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
