package offer;

import offer.base.ListNode;

/**
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 */
public class MergeLinkedList_25 {

    public static void main(String[] args) {
        MergeLinkedList_25 main = new MergeLinkedList_25();
        main.testMerge();
    }

    public void testMerge() {
//        ListNode node14 = new ListNode(7, null);
//        ListNode node13 = new ListNode(5, node14);
//        ListNode node12 = new ListNode(3, node13);
        ListNode node11 = new ListNode(1, null);
        System.out.println(node11);

        ListNode node24 = new ListNode(8, null);
        ListNode node23 = new ListNode(6, node24);
        ListNode node22 = new ListNode(4, node23);
        ListNode node21 = new ListNode(2, node22);
        System.out.println(node21);

        ListNode head = this.mergeTwoLists(node11, node21);
        System.out.println(head);
    }

    // 循环解决
    public ListNode mergeTwoLists(ListNode p1, ListNode p2) {
        ListNode cur = new ListNode(0);
        ListNode dum = cur;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                cur.next = p1;
                p1 = p1.next;
            } else {
                cur.next = p2;
                p2 = p2.next;
            }
            cur = cur.next;
        }
        cur.next = p1 == null ? p2 : p1;
        return dum.next;
    }
}
