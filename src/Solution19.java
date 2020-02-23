/**
 * @author Julia Jiang
 * @date 2020/2/17 18:31
 * @description 删除链表的倒数第N个节点
 */
public class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for(int i = 1; i <= n+1; i++){
            first = first.next;
        }

        while (first != null){
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode first = head;
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        Solution19 solution19 = new Solution19();
        ListNode ans = solution19.removeNthFromEnd(first, 2);
        System.out.println(ans);
    }
}
