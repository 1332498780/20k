package offer;

public class DeleteLinkNode_18 {
    public static void main(String[] args) {
        DeleteLinkNode_18 deleteLinkNode_18 = new DeleteLinkNode_18();
        ListNode head = new ListNode(1);
        ListNode mid = new ListNode(2);
        head.next = mid;
        ListNode tail = new ListNode(9);
        mid.next = tail;
        ListNode listNode = deleteLinkNode_18.deleteNode(head, mid);
        System.out.println(listNode);
    }

    public ListNode deleteNode(ListNode head, ListNode target) {
        if (head == null) {
            return null;
        }
        if (head == target) {
            return head.next;
        }
        if (target == null) {
            return head;
        }
        ListNode p = head;
        // 尾节点
        if (target.next == null) {
            while (p.next != null) {
                if (p.next == target) {
                    p.next = null;
                    break;
                }
                p = p.next;
            }
        } else {
            target.val = target.next.val;
            target.next = target.next.next;
        }
        return head;
    }

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            return head.next;
        }
        ListNode p = head;
        while (p.next != null) {
            if (p.next.val == val) {
                p.next = p.next.next;
                break;
            }
            p = p.next;
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
