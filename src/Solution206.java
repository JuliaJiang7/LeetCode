/**
 * @author julia.jiang
 * @date 2020/8/26 9:50
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution206 {

    /**
     * 解法一：迭代
     * 自己写的（需添加dummy节点）
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if(head == null){return null;}
        ListNode dummy = new ListNode(0);
        dummy.next = null;

        ListNode cur = head;
        while (cur != null){
            ListNode tmp = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = tmp;
        }
        return dummy.next;
    }

    /**
     * 解法二：递归
     * 参考：https://leetcode.wang/leetcode-206-Reverse-Linked-List.html
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        // 递归出口
        if(head == null || head.next == null){
            return head;
        }
        // 大问题变为小问题：head.next作为剩余部分的头指针
        ListNode newHead = reverseList(head.next);
        // head.next 代表新链表的尾，将他的 next 置为 head，即就是把 head 加到末尾
        head.next.next = head;
        head.next = null;
        return newHead;
    }

}
