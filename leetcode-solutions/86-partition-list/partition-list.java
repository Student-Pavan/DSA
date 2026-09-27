/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {

        ListNode left = new ListNode(0);
        ListNode right = new ListNode(0);
        ListNode l = left;
        ListNode r = right;
        ListNode temp = head;

        while (temp != null) {
            if (temp.val < x) {
                l.next = temp;
                l = l.next;
            } else {
                r.next = temp;
                r = r.next;
            }
            temp = temp.next;
        }
        r.next = null;
        l.next = right.next;

        return left.next;

    }
}