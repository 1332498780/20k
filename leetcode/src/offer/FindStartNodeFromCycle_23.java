package offer;


/**
 * 如果一个链表包含环，找到环的入口节点
 */
public class FindStartNodeFromCycle_23 {

    public static void main(String[] args) {
        FindStartNodeFromCycle_23 main = new FindStartNodeFromCycle_23();
        main.testCalcCycleStartNode();
    }

    public void testFindMeetNode() {
        ListNode node4 = new ListNode(4,null);
        ListNode node3 = new ListNode(3,node4);
        ListNode node2 = new ListNode(2,node3);
        ListNode node1 = new ListNode(1,node2);
        // 有环
        node4.next = node2;
        ListNode meetNode = findMeetNode(node1);
        System.out.println(meetNode == node4);
        // 无环
        node4.next = null;
        ListNode meetNode2 = findMeetNode(node1);
        System.out.println(meetNode2 == null);
    }

    public void testCalcCycleLength() {
        ListNode node4 = new ListNode(4,null);
        ListNode node3 = new ListNode(3,node4);
        ListNode node2 = new ListNode(2,node3);
        ListNode node1 = new ListNode(1,node2);
        node4.next = node1;
        int length = this.calcCycleLength(node1);
        System.out.println(length == 4);
    }

    public void testCalcCycleStartNode() {
        ListNode node4 = new ListNode(4,null);
        ListNode node3 = new ListNode(3,node4);
        ListNode node2 = new ListNode(2,node3);
        ListNode node1 = new ListNode(1,node2);
        node4.next = node1;
        ListNode startNode = calcCycleStartNode(node1, 4);
        System.out.println(startNode == node1);
    }

    /**
     * 1、判断有无环
     * 2、计算环长度
     * 3、计算环入口
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        // 判断有无环
        ListNode meetNode = this.findMeetNode(head);
        if (meetNode == null) {
            return null;
        }
        // 计算环长度
        int cycleLength = this.calcCycleLength(meetNode);
        // 计算环入口
        return this.calcCycleStartNode(head, cycleLength);
    }

    // 判断有无环：使用快慢指针，找相遇点
    public ListNode findMeetNode(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (true) {
            if (p1 == null || p1.next == null) {
                return null;
            }
            p1 = p1.next.next;
            p2 = p2.next;
            if (p1 == p2) {
                return p1;
            }
        }
    }

    // 计算环长度(必须保证有环)
    public int calcCycleLength(ListNode cycleNode) {
        if (cycleNode == null) {
            return 0;
        }
        ListNode p = cycleNode.next;
        int length = 1;
        while (p != cycleNode) {
            p = p.next;
            length++;
        }
        return length;
    }

    // 计算环入口节点
    public ListNode calcCycleStartNode(ListNode head, int cycleLength) {
        ListNode p1 = head;
        ListNode p2 = head;
        // 先让p1走环的长度
        for (int i = 0; i < cycleLength; i++) {
            p1 = p1.next;
        }

        // p1与p2同时移动，直到相遇，相遇点即使入口点
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
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
