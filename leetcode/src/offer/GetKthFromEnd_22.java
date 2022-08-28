package offer;

public class GetKthFromEnd_22 {

    public static void main(String[] args) {
        GetKthFromEnd_22 kth = new GetKthFromEnd_22();
        ListNode head = kth.buildNode(1);
        ListNode kNode = kth.getKthFromEnd_1(head, 1);
        System.out.println(kNode.val);
    }

    /**
     * 双指针求法,两个指针相差k-1步长
     */
    public ListNode getKthFromEnd_1(ListNode head,int k) {
        if (head == null || k < 1) {
            return null;
        }
        // p1 先走k-1步
        ListNode p1 = head;
        ListNode p2 = head;
        for (int i=0; i < k-1 && p1 != null; i++) {
            p1 = p1.next;
        }
        // k > 链表长度
        if (p1 == null) {
            return null;
        }
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    /**
     * 先求链表长度N,在算出倒数第K节点,正向对应是第几节点
     * 两次循环遍历
     */
    public ListNode getKthFromEnd_2(ListNode head,int k) {
        if (head == null || k < 1) {
            return null;
        }
        int size = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            size++;
        }
        if (k > size) {
            return null;
        }
        int n = size - k + 1;
        p = head;
        for (int i = 1; i < n; i++) {
            p = p.next;
        }
        return p;
    }


    public ListNode buildNode(int n) {
        ListNode node1 = new ListNode(n);
        for (int i = n - 1; i > 0; i--) {
            ListNode node2 = new ListNode(i);
            node2.next = node1;
            node1 = node2;
        }
        return node1;
    }
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
