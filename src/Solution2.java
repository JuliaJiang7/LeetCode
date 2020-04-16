/**
 * @author Julia Jiang
 * @date 2020/3/19 11:04
 * @description 两数相加
 */
public class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null){
            //考虑一个列表为空，另一个不为空的情况（或者两个列表长度不一样的情况）
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            curr.next = new ListNode((carry + x + y) % 10);
            carry = (carry + x + y) / 10;
            curr = curr.next;
            if(p != null)   p = p.next;
            if(q != null)   q = q.next;
        }
        //考虑两个链表为 [5] [5], while 循环之后 carry 还有值
        if(carry > 0){
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    /**
     * 扩展：给出的链表不是反序，例如[3,4,2] + [4,6,5] = [8,0,7]
     * 解法：l1 , l2 反序计算，返回列表反序
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        //反转链表
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode res = reverseList(addTwoNumbers(l1, l2));
        return res;
    }

    /**
     * 反转链表，解法一：迭代
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head){
        if(head == null)    return null;
        ListNode pre = null;
        ListNode next;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 反转链表：解法二：递归
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }



    public static void main(String[] args) {
        ListNode l1 = ListNode.arrayToLinkedList(new int[]{5});
        ListNode l2 = ListNode.arrayToLinkedList(new int[]{5});
        Solution2 solution2 = new Solution2();
        ListNode res = solution2.addTwoNumbers(l1, l2);
        res.showList();
        ListNode l3 = ListNode.arrayToLinkedList(new int[]{1});
        solution2.reverseList2(l3).showList();

        ListNode l4 = ListNode.arrayToLinkedList(new int[]{3,4,2});
        ListNode l5 = ListNode.arrayToLinkedList(new int[]{4,6,5});
        solution2.addTwoNumbers2(l4, l5).showList();
    }
}
