/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hh;

/**
 *
 * @author ZCG04000034
 */
public class MergeTwoLists {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    
    //官方的递归版本
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if(l1==null){
            return l2;
        }else if(l2==null){
            return l1;
        }else if(l1.val<l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
             return l1;
        }else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
    
    //自己的迭代版本
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode temp = head;
        
        while(l1!=null && l2!=null){
            if(l1.val < l2.val){
                temp.next = l1;
                l1 = l1.next;
            }else{
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        temp.next = l1==null?l2:l1;
        
        //这里就不用再做无用的循环了，直接把剩下的接到temp.next即可
//        while(l1!=null){
//            temp.next = l1;
//            l1 = l1.next;
//            temp = temp.next;
//        }
//        while(l2!=null){
//            temp.next = l2;
//            l2 = l2.next;
//            temp = temp.next;
//        }
        return head.next;
    }
        
        
}
