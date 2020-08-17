public class RemoveNthFromEnd {

    //虽然没啥技术亮点，但是第一次速度超过100%用户。！！！
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode ln = head;
//        if(head.next==null)
//            return null;
        while(ln!=null){
            int temp = n+1;
            ListNode t = ln;
            while(t!=null&&temp-->0){
                t = t.next;
            }
            if(t==null){
                if(temp>0){
                    return head.next;
                }
                ln.next = ln.next.next;
                return head;
            }
            ln = ln.next;
        }
        return head;
    }

      public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
    }
}
/**
 * Definition for singly-linked list.

 */