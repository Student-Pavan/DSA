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
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null)
            return true;
        ListNode midnode = getmidnode(head);
        ListNode shf = reverse(midnode.next);
        ListNode fhf = head;
        while(shf != null){
            if(fhf.val != shf.val)
                return false;
            fhf = fhf.next;
            shf = shf.next;
        }
        return true;

    }
    ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    ListNode getmidnode(ListNode head){
        ListNode hare = head;
        ListNode turtle = head;

        while(hare.next != null &&  hare.next.next != null){
            turtle = turtle.next;
            hare = hare.next.next;
        }
        return turtle;
    }
    
}