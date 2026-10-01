class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node new_head = new Node(head.val);

        Node current = head;
        Node newcurrent = new_head;

        while (current.next != null) {
            current = current.next;

            newcurrent.next = new Node(current.val);

            newcurrent = newcurrent.next;
        }

        current = head;
        newcurrent = new_head;

        while (current != null) {

            if (current.random != null) {

                Node temp = head;
                Node newtemp = new_head;

                while (temp != current.random) {
                    temp = temp.next;
                    newtemp = newtemp.next;
                }

                newcurrent.random = newtemp;
            }

            current = current.next;
            newcurrent = newcurrent.next;
        }

        return new_head;
    }
}