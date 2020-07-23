package DayTwo20200722;

import java.util.Random;

public class AddTwoNumbers {

	//ÔÆ¸ç½âÌâ
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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

	public static int getInt() {
		Random random = new Random();
		int result = random.nextInt(10);
		if (result == 0) {
			return result + 1;
		} else {
			return result;
		}
	}

	public static void sys(ListNode result) {
		ListNode temp = result;
		while (temp != null) {
			System.out.println(temp.val);
			temp = temp.next;
		}
	}

	public static void main(String[] args) {

		ListNode listNode1 = new ListNode(1);
		for (int i = 0; i < 3; i++) {
			ListNode temp = listNode1;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = new ListNode(i);
		}
		 sys(listNode1);

		ListNode listNode2 = new ListNode(1);
		for (int i = 0; i < 3; i++) {
			ListNode temp = listNode2;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = new ListNode(i);
		}
		 sys(listNode2);

		ListNode result = addTwoNumbers(listNode1, listNode2);
		sys(result);

	}
}

class ListNode {

	int val;

	ListNode next;

	ListNode(int x) {
		val = x;
	}
}
