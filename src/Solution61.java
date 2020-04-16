/**
 * @author julia.jiang
 * @date 2020/4/16 10:20
 * @email julia.jiang.fan@gmail.com
 * @description 旋转链表
 */
public class Solution61 {

    /**
     * 自己的解法+优化
     * 参考：https://leetcode.wang/leetCode-61-Rotate-List.html
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0)    return head;
        ListNode node = head;
        ListNode tail = null;

        // 求链表长度，并保存尾指针
        int length = 0;
        while (node != null){
            length++;
            node = node.next;
            if(node != null)
                tail = node;
        }

        k = k % length;
        if(k == 0)
            return head;

        ListNode newLast = head;
        for(int i = 0; i < length - k - 1; i++){
            newLast = newLast.next;
        }

        ListNode newStart = newLast.next;
        newLast.next = null;
        tail.next = head;
        return newStart;
    }
}
