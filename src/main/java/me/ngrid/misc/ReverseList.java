package me.ngrid.misc;


import java.util.*;

/**
 * Reverse a singly linked list.
 * @see <a href="https://leetcode.com/problems/reverse-linked-list/">l33tc0d3</a>
 */
public class ReverseList {

    public static ListNode reverseList(ListNode head) {
        if(head == null)
            return null;
        ListNode tmp = head.next;
        head.next = null;
        while(tmp != null) {
            ListNode next = tmp.next;
            tmp.next = head;
            head = tmp;
            tmp = next;
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x, ListNode next) {val = x; this.next = next;}
        @Override
        public String toString() {
            return Integer.toString(val);
        }
    }

    public static String toString(ListNode list) {
        StringBuilder sb = new StringBuilder("[");
        sb.append(list.val);
        ListNode next = list.next;
        while(next != null) {
            sb.append(", ");
            sb.append(next.val);
            next = next.next;
        }
        return sb.append("]").toString();
    }
}
