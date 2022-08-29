package offer;

/**
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 */
public class ReverseLinkedList_24 {

    public static void main(String[] args) {
        ReverseLinkedList_24 main = new ReverseLinkedList_24();
        main.testReverseList();
    }

    public void testReverseList() {
        ListNode listNode4 = new ListNode(4, null);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        printLinkNode(listNode1);
        ListNode newHead = reverseList(listNode1);
        System.out.println("-------------------------------");
        printLinkNode(newHead);
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode p1 = head;
        ListNode p2 = head.next;
        ListNode tempNode;
        while (p2 != null) {
            tempNode = p2.next;
            p2.next = p1;
            // 后移
            p1 = p2;
            p2 = tempNode;
        }
        // 此时head变为尾结点，需要清除原始头指向
        head.next = null;
        return p1;
    }

    private void printLinkNode(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
    }
}
