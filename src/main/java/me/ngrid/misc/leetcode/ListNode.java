package me.ngrid.misc.leetcode;

/**
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x, ListNode next) {val = x; this.next = next;}
    @Override
    public String toString() {
        return Integer.toString(val);
    }
}
