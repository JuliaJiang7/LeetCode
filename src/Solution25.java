/**
 * @author Julia Jiang
 * @date 2020/3/19 10:50
 * @description K 个一组翻转列表
 */
public class Solution25 {

    /**
     * 解法一：迭代
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null)    return null;
        //K 个一组链表的头结点
        ListNode sub_head = head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //上一组链表的尾结点
        ListNode tail = dummy;
        //遍历这一组K 个节点，最终作为这一组的最后一个节点，指向null，从这里将当前子链表断开
        ListNode toNull = head;
        while (sub_head != null){
            int i = k;
            while (i - 1 > 0){
                toNull = toNull.next;
                if(toNull == null){
                    //说明剩下的节点个数不足 K 个，直接返回链表头即可
                    return dummy.next;
                }
                i--;
            }

            //存储链表的后面节点
            ListNode temp = toNull.next;
            //断开子链表，方便求反转
            toNull.next = null;
            //反转子链表
            ListNode new_sub_head = reverse(sub_head);
            //反转后的子链表和前面部分接上
            tail.next = new_sub_head;
            //更新 tail, 反转后之前链表的第一个，是现在的最后一个
            tail = sub_head;
            //下一组链表的头
            sub_head = temp;
            //下一组链表的遍历节点
            toNull = sub_head;
            //把后面断开的链表接回来
            tail.next = sub_head;
        }
        return dummy.next;
    }

    /**
     * 解法二：递归
     * 没有迭代中那么多指针，更加简洁
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup2(ListNode head, int k) {
        if(head == null)    return null;
        ListNode toNull = head;
        int i = k;
        while (i - 1 > 0){
            toNull = toNull.next;
            if(toNull == null){
                return head;
            }
            i--;
        }
        //存储下一组链表的开始
        ListNode temp = toNull.next;
        //断开子链表
        toNull.next = null;

        //反转子链表，并存储新的头结点
        ListNode new_head = reverse(head);

        //head 之前是链表头，现在是链表的尾结点，把后面的反转结果接上即可
        head.next = reverseKGroup2(temp, k);

        return new_head;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head){
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
}
