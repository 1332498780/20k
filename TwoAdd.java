public class TwoAdd {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode header = new ListNode(0);
        ListNode temp = header;
        int adder = 0;
        while(l1!=null && l2!=null){
                if(l1.val+l2.val+adder >= 10){
                    temp.val = l1.val+l2.val+adder - 10;
                    adder = 1;
                }else{
                    temp.val = l1.val+l2.val+adder;
                    adder = 0;
                }
                l1 = l1.next;
                l2 = l2.next;
                ListNode ln = new ListNode(0);
                temp.next = ln;
                temp = temp.next;

        }
        while(l1 != null){
            if(l1.val+adder == 10){
                temp.val = 0;
                adder = 1;
            }else{
                temp.val = l1.val+adder;
                adder = 0;
            }
            l1 = l1.next;
            ListNode ln = new ListNode(0);
            temp.next = ln;
            temp = temp.next;
        }
        while(l2 != null){
            if(l2.val+adder == 10){
                temp.val = 0;
                adder = 1;
            }else{
                temp.val = l2.val+adder;
                adder = 0;
            }
            l2 = l2.next;
            ListNode ln = new ListNode(0);
            temp.next = ln;
            temp = temp.next;
        }
        if(adder != 0){
            temp.val = 1;
        }else{
            temp = header;
            while(temp.next.next!=null){
                temp = temp.next;
            }
            temp.next = null;
        }
        return header;
    }
      public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
