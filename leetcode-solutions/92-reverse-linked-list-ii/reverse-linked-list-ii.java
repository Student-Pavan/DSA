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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevleft = dummy;

        for(int i = 1 ;i < left; i++){
            prevleft = prevleft.next;
        }

        ListNode curr = prevleft.next;
        ListNode prev = null;

        for(int i = left; i <= right; i++){
            ListNode next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        ListNode leftn = prevleft.next;

        prevleft.next = prev;
        leftn.next = curr;

        return dummy.next;
    }
}