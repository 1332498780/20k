package jd.link;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomLink {
    static String a = "a";
    public static void main(String[] args) {
        Node head = new Node(7);
        Node two = new Node(13);
        Node three = new Node(11);
        Node four = new Node(10);
        Node five = new Node(1);
        head.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        head.random = two;
        two.random = head;
        three.random = five;
        four.random = three;
        five.random = head;

//        Node newHead = new CopyRandomLink().mapSolution(head);
        Node newHead = new CopyRandomLink().insertSolution(head);
        while (newHead != null) {
            System.out.println(newHead.val);
            if (newHead.random == null) {
                System.out.println("null");
            } else {
                System.out.println(newHead.random.val);
            }
            newHead = newHead.next;
        }
    }

    public Node mapSolution(Node head) {
        Map<Node,Node> m = new HashMap<>();
        Node p = head;
        while (p != null) {
            Node newNode = new Node(p.val);
            m.put(p, newNode);
            p = p.next;
        }
        p = head;
        while (p != null) {
            Node t = m.get(p);
            t.next = m.get(p.next);
            t.random = m.get(p.random);
            p = p.next;
        }
        return m.get(head);
    }

    public Node insertSolution(Node head) {
        if (head == null) {
            return null;
        }
        Node p = head;
        while (p != null) {
            Node newNode = new Node(p.val);
            newNode.next = p.next;
            p.next = newNode;
            p = newNode.next;
        }
        p = head;
        while (p != null) {
            if (p.random != null) {
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }

        p = head;
        Node n = p.next;
        Node newHead = p.next;
        while (n.next != null) {
            p.next = p.next.next;
            n.next = n.next.next;
            p = p.next;
            n = n.next;
        }
        return newHead;
    }
}

class Node {
    int val;
    Node next;
    Node random;
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
