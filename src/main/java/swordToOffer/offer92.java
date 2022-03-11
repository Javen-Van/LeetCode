package swordToOffer;

import bean.ListNode;

/**
 * @author Javen
 * @create 2022-03-04
 * @Description 反转链表
 */
public class offer92 {

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        return null;
    }
}
