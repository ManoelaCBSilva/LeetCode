import java.util.Objects;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int remainingValue = 0;
        ListNode head = new ListNode(0);
        ListNode currentNode = head;

        while (Objects.nonNull(l1) && Objects.nonNull(l2)) {

            int x = l1.val;
            int y = l2.val;

            int sum = x + y + remainingValue;
            currentNode.next = new ListNode(sum % 10);
            remainingValue = sum / 10;

            currentNode = currentNode.next;

            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode remainingList = Objects.isNull(l1) ? l2 : l1;
        while (Objects.nonNull(remainingList)) {
            int sum = remainingList.val + remainingValue;
            currentNode.next = new ListNode(sum % 10);
            remainingValue = sum / 10;
            remainingList = remainingList.next;
            currentNode = currentNode.next;
        }

        if (remainingValue > 0) {
            currentNode.next = new ListNode(remainingValue);
        }

        return head.next;
    }
}