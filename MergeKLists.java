/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hh;

import hh.MergeTwoLists.ListNode;
import javax.sound.midi.ShortMessage;

/**
 *
 * @author ZCG04000034
 */
public class MergeKLists {

  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    //自己琢磨的，每次都用2个listNode来合并，性能很差
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode nulls = null;
        for(int i=0;i<lists.length;i++){
            nulls=mergeTwoList(nulls, lists[i]);
        }
        return nulls;
    }
    
    public ListNode mergeTwoList(ListNode l1,ListNode l2){
        if(l1==null){
            return  l2;
        }else if(l2 == null){
            return l1;
        }else if(l1.val<l2.val){
            l1.next = mergeTwoList(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwoList(l1, l2.next);
            return l2;
        }
    }
    
    public ListNode merge(ListNode[] lists,int l,int r) {
        if(l==r){
            return lists[l];
        }
        int mid = (r+l)/2;
        return mergeTwoList(merge(lists, l, mid),merge(lists, mid+1, r));
    }
    //官方算法：分治合并
    public ListNode mergeKLists1(ListNode[] lists) {
        if(lists.length==0){
            return null;
        }
        return merge(lists, 0, lists.length-1);
    }
}
