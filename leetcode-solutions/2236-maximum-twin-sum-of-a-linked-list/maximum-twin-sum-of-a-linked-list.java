class Solution {
    public int pairSum(ListNode head) {
        if (head == null || head.next == null) {
            return 0;
        }

        ListNode hare = head;
        ListNode turtle = head;

        while (hare != null && hare.next != null) {
            hare = hare.next.next;
            turtle = turtle.next;
        }

        ListNode prev = null;
        ListNode curr = turtle;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        int ans = 0;
        ListNode first = head;
        ListNode second = prev;

        while (second != null) {
            ans = Math.max(ans, first.val + second.val);
            first = first.next;
            second = second.next;
        }

        return ans;
    }
}