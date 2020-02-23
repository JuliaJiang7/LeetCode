/**
 * @author Julia Jiang
 * @date 2020/2/18 10:40
 * @description 两两交换链表中的节点
 */
public class Solution24 {
    /**
     * 递归方法
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head){
        //终止条件
        if(head == null || head.next == null)
            return head;

        ListNode firstNode = head;
        ListNode secondNode = head.next;

        //调用单元
        firstNode.next = swapPairs(secondNode.next);
        secondNode.next = firstNode;

        //返回值
        return secondNode;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.getList();
        head.showList();
        Solution24 solution24 = new Solution24();
        ListNode ans = solution24.swapPairs(head);
        ans.showList();

        ListNode head2 = ListNode.getList();
        head2.showList();
        ListNode ans2 = solution24.swapPairs2(head2);
        ans2.showList();
    }

    public ListNode swapPairs2(ListNode head){
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode preNode = dummy;

        while (head != null && head.next != null){
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            preNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            preNode = firstNode;
            head = firstNode.next;
        }

        return dummy.next;
    }
}
