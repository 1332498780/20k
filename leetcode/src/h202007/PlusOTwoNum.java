package h202007;

/**
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class PlusOTwoNum {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(3);
        ListNode l2 = new ListNode(4);
        l1 = l1.appendNode(4).appendNode(2);
        l2 = l2.appendNode(6).appendNode(5);

        PlusOTwoNum plus = new PlusOTwoNum();
        ListNode res = plus.addTwoNumbers(l1, l2);
        while (res.next != null) {
            System.out.printf("%d -> ", res.val);
            res = res.next;
        }
        System.out.printf("%d", res.val);

    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        return new ListNode(8).appendNode(0).appendNode(7);
        ListNode res = new ListNode(0);
        ListNode l3 = res;
        while (true) {
            if (l1 != null && l2 != null) {
                int sum = l1.val + l2.val;
                if (sum < 10) {             // 小于10，不需要进位
                    if (res.next == null) {     // 该位置没有前一位的进位
                        res.next = new ListNode(sum);
                        res = res.next;
                    } else {                    // 该位置有前一位的进位
                        int curSum = res.next.val + sum;
                        if (curSum < 10) { // 该位置相加后，再加上进位（1），小于10
                            res.next = new ListNode(curSum);
                            res = res.next;
                        } else {                // 该位置相加后，再加上进位（1），大于等于10
                            int v = curSum - 10;
                            ListNode next = new ListNode(1);
                            ListNode cur = new ListNode(v);
                            cur.next = next;
                            res.next = cur;
                            res = res.next;
                        }
                    }
                } else {                       // 大于等于10，需要进位
                    int v = sum - 10;
                    ListNode next = new ListNode(1);
                    ListNode cur;
                    if (res.next == null) {    // 需要进位时，该位置没有进位
                        cur = new ListNode(v);
                    } else {                    // 需要进位时，该位置有进位
                        cur = new ListNode(v + res.next.val);
                    }
                    cur.next = next;
                    res.next = cur;
                    res = res.next;
                }
                l1 = l1.next;
                l2 = l2.next;
            } else if (l1 == null) {
                if (res.next == null) {
                    res.next = l2;
                    break;
                } else {
                    int curSum = res.next.val + l2.val;
                    if (curSum < 10) {                  // 不需要进位，计算完关闭
                        ListNode cur = new ListNode(curSum);
                        cur.next = l2.next;
                        res.next = cur;
                        break;
                    } else {                            // 需要进位
                        ListNode next = new ListNode(1);
                        int v = curSum - 10;
                        ListNode cur = new ListNode(v);
                        cur.next = next;
                        res.next = cur;
                        res = res.next;
                    }
                }
            } else if (l2 == null) {
                if (res.next == null) {
                    res.next = l1;
                    break;
                } else {
                    int curSum = res.next.val + l1.val;
                    if (curSum < 10) {                  // 不需要进位，计算完关闭
                        ListNode cur = new ListNode(curSum);
                        cur.next = l1.next;
                        res.next = cur;
                        break;
                    } else {                            // 需要进位
                        ListNode next = new ListNode(1);
                        int v = curSum - 10;
                        ListNode cur = new ListNode(v);
                        cur.next = next;
                        res.next = cur;
                        res = res.next;
                    }
                }
            } else {    // l1 == null && l2 == null
                break;
            }
        }
        // 去首
        l3 = l3.next;
        return l3;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    ListNode appendNode(int value) {
        ListNode tmp = new ListNode(value);
        tmp.next = this;
        return tmp;
    }
}
