package test;

public class Bd {
    public static void main(String[] args) {
//        Node head = new Node(1);
//        head.next = new Node(2);
//        head.next.next = new Node(3);
//        head.next.next.next = new Node(4);
//        head.next.next.next.next = new Node(5);
//        Node n = new Bd().recurse(head);
//        while (n != null) {
//            System.out.println("n.value = " + n.value);
//            n = n.next;
//        }
        int[] buu = new Bd().insertSort(new int[]{4,5,6,1,2,3});
        for (int i : buu) {
            System.out.println("i = " + i);
        }
    }
    
    public int[] bubbleSort(int[] arrs) {
        for (int i = 0; i < arrs.length-1; i++) {
            boolean flag = false;
            for (int j = 0; j < arrs.length - i - 1; j++) {
                if (arrs[j] > arrs[j + 1]) {
                    int tmp = arrs[j+1];
                    arrs[j+1] = arrs[j];
                    arrs[j] = tmp;
                    flag = true;
                }
            }
            if (flag == false) {
                return arrs;
            }
        }
        return  arrs;
    }

    public int[] insertSort(int[] arrs) {
        int pre = 0;
        for (int i = 1; i < arrs.length; i++) {
            int tmp = arrs[i];
            int j = pre;
            while (j >= 0 && tmp < arrs[j]) {
                arrs[j+1] = arrs[j];
                j--;
            }
            arrs[j+1] = tmp;
//            if (j != pre) {
//                arrs[j+1] = tmp;
//            }
            pre++;
        }
        return arrs;
    }

    // 反转指定区间的单链表
    public Node recurseMn(Node head, int m, int n) {
        if (m == n) {
            return head;
        }
        Node node = head;
        int i=1;
        while (m != i){
            i++;
            node = node.next;
        }
        Node lNode = node;
        Node pre = null;
        while ((n+1) != i) {
            i++;
            Node next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }


        return head;
    }

    public Node recurse(Node head) {
        Node pre = null;
        while (head != null) {
            Node next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
class Node {
    int value;
    Node next;
    Node(int val) {
        this.value = val;
    }
}
