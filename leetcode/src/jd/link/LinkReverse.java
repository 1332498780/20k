package jd.link;

import java.sql.ClientInfoStatus;
import java.util.List;

public class LinkReverse {

    public static void main(String[] args) {
        LinkReverse l = new LinkReverse();
        ListNode head = l.build(5);
        // 翻转链表
//        ListNode reHead = l.reverseNode(head);
//        l.printNode(reHead);
        // 翻转链表m,n
//        ListNode reHeadMN = l.reverseNodeMN(head, 1, 1);
//        l.printNode(reHeadMN);
        ListNode h = l.removeNthFromEnd(head, 2);
        l.printNode(h);
    }

    // 删除倒数第n个节点，n<=length
    // 思路：快慢指针
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = head;
        ListNode slow = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    // 求链表的中间节点
    // 思路：快慢指针
    public ListNode middleNode(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 删除链表指定节点（非尾节点）
    // 思路：将该节点赋值为后继节点，然后删除后继节点
    void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    // 删除链表中指定值对应的节点
    // 思路：遍历链表，找到该节点删除
    ListNode deleteNodeVal(ListNode head, int val) {
        if (val == head.val) {
            ListNode n = head.next;
            head.next = null;
            return n;
        }
        ListNode l = head;
        while (l.next.next != null) {
            if (l.next.val == val) {
                l.next = l.next.next;
            }
            l = l.next;
        }
        return head;
    }

    // 构建N个节点的链表
    ListNode build(int n) {
        ListNode head = null;
        for (int i=n; i>0; i--) {
            ListNode cur = new ListNode(i);
            cur.next = head;
            head = cur;
        }
        return head;
    }
    // 顺序遍历
    void printNode(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
    // 逆转链表
    ListNode reverseNode(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
    // 逆转链表 m,n
    ListNode reverseNodeMN(ListNode head,int m, int n) {
        if (m == n) {
            return head;
        }
        ListNode realHead = head;
        ListNode preHead = null;
        for (int i = 1; i < m; i++) {
            preHead = head;
            head = head.next;
        }
        ListNode tailMN = head;
        ListNode pre = null;
        for (int i = m; i <= n; i++) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        tailMN.next = head;
        if (m > 1) {
            preHead.next = pre;
            return realHead;
        }
        return pre;
    }
    // 找到两个单链表相交的起始节点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pa = headA,pb = headB;
        while (pa != pb) {
            pa = pa == null ? pb : pa.next;
            pb = pb == null ? pa : pb.next;
        }
        return pa;
    }
    // 给定一个链表，判断链表中是否有环
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        if (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    // 合并两个有序链表
    public ListNode mergeTwoOrderLinkNode(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode preHead = new ListNode(Integer.MIN_VALUE);
        ListNode pre = preHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val){
                pre.next = l1;
                pre = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                pre = l2;
                l2 = l2.next;
            }
        }
        return l1;
    }
    // 合并多个有序链表
}

class ListNode {
    int val;
    ListNode next;
    // 构造函数
    ListNode(int v) {
        this.val = v;
    }

    ListNode(int v, ListNode node) {
        this.val = v;
        this.next = node;
    }
}