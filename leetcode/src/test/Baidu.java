package test;

public class Baidu {
    public static void main(String[] args) {
    }

    public Nodes findNode(Nodes node1, Nodes node2) {
        Nodes cur1 = node1;
        Nodes cur2 = node2;
        boolean flag = false;
        while (true) {
            if (cur1 != null) {
                cur1 = cur1.next;
            }else if (flag == false){
                cur1 = node2;
                flag = true;
            } else {
                if (cur1.val == cur2.val) {
                    return cur1;
                }

            }

            if (cur2 != null) {
                cur2 = cur2.next;
            }else if  (flag == false) {
                cur2 = node1;
                flag = true;
            } else {
                if (cur1.val == cur2.val) {
                    return cur1;
                }
            }
        }
    }
}
class Nodes {
    int val;
    Nodes next;
}
