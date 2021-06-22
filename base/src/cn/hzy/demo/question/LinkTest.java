package cn.hzy.demo.question;

public class LinkTest {

    public static void main(String[] args) {
        LinkNode ln = LinkNode.create(new int[]{1,2,3,4,5,6});
//        LinkNode.print(ln);
        reversePrint1(ln);
    }

    /***
     * 反向打印链表内容，时间复杂度是O(n),不需要额外空间，不会改变链表的最终结果(实际上是改变了2次)
     * @param linkNode
     */
    public static void reversePrint(LinkNode linkNode){
        LinkNode rHead = null;
        LinkNode temp = null;
        while(linkNode!=null){
            temp = rHead;
            rHead = linkNode;
            linkNode = linkNode.next;
            rHead.next = temp;
        }
        while(rHead!=null){
            System.out.print(rHead.val + " -> ");
            temp = linkNode;
            linkNode = rHead;
            rHead = rHead.next;
            linkNode.next = temp;
        }
        System.out.println();
        LinkNode.print(linkNode);
    }

    /***
     * 想到用栈来实现反向打印，那么自然也就想到递归方法，但是这个方法有弊端就是如果链表过大，容易引发StackOverFlow
     * @param linkNode
     */
    public static void reversePrint1(LinkNode linkNode){
        if(linkNode!=null){
            reversePrint1(linkNode.next);
            System.out.print(linkNode.val+ " -> ");
        }
    }
}

class LinkNode{
    int val;
    LinkNode next;

    public LinkNode(int val,LinkNode next){
        this.val = val;
        this.next = next;
    }
    public static LinkNode create(int[] arr){
        LinkNode head = null;
        LinkNode temp = null;
        int index=0;
        for(int i:arr){
            LinkNode ln = new LinkNode(i,null);
            if(index++ == 0){
                head = ln;
                temp = ln;
            }else{
                temp.next = ln;
                temp = temp.next;
            }
        }
        return head;
    }
    public static void print(LinkNode ln){
        while(ln!=null){
            System.out.print(ln.val);
            if(ln.next!=null){
                System.out.print(" -> ");
            }
            ln = ln.next;
        }
    }
}
