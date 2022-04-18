package exam.huawei;

import bean.ListNode;

import java.util.Scanner;

public class interview {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt();
        ListNode a = new ListNode(), b = new ListNode(), dummy1 = a, dummy2 = b;
        for (int i = 0; i < m; i++) {
            dummy1.val = sc.nextInt();
            if (i == m - 1) continue;
            dummy1.next = new ListNode();
            dummy1 = dummy1.next;
        }
        for (int i = 0; i < n; i++) {
            dummy2.val = sc.nextInt();
            if (i == n - 1) continue; // 最后一项没有next
            dummy2.next = new ListNode();
            dummy2 = dummy2.next;
        }
        ListNode res = method(a, b);
        while (res.next != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode method(ListNode a, ListNode b) {
        int carry = 0;
        ListNode res = new ListNode(), head = res;
        do {
            int x = 0, y = 0;
            if (a != null) {
                x = a.val;
                a = a.next;
            }
            if (b != null) {
                y = b.val;
                b = b.next;
            }
            int cur = x + y + carry;
            res.val = cur % 10;
            carry = cur / 10;
            res.next = new ListNode();
            res = res.next;
        } while (carry != 0 || a != null || b != null);
        res = null;
        return head;
    }
}
