/**
 * @author julia.jiang
 * @date 2020/8/18 10:27
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution86 {
    public ListNode partition(ListNode head, int x) {
        // 小于x的链表
        ListNode lowDummy = new ListNode(0);
        ListNode lowCur = lowDummy;
        // 大于等于x的链表
        ListNode highDummy = new ListNode(0);
        ListNode highCur = highDummy;

        ListNode cur = head;
        while (cur != null){
            if(cur.val < x){
                lowCur.next = cur;
                lowCur = lowCur.next;
            }else{
                highCur.next = cur;
                highCur = highCur.next;
            }
            cur = cur.next;
        }
        // 一定要注意添加这一步，不然链表会出现环
        highCur.next = null;
        // 链表拼接
        lowCur.next = highDummy.next;
        return lowDummy.next;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(4);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(2);
        node.next.next.next.next = new ListNode(5);
        node.next.next.next.next.next = new ListNode(2);
        Solution86 solution86 = new Solution86();
        System.out.println(solution86.partition2(node, 3));
    }

    public ListNode partition2(ListNode head, int x) {
        if(head == null){return head;}
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // pre 表示遍历节点cur的前一个，用于删除
        ListNode cur = head, pre = dummy;
        // pre2 表示插入位置的前一个
        ListNode pre2 = dummy;
        while (cur != null){
            int s = cur.val;
            if(cur.val < x){
                // 删除原位置元素
                pre.next = cur.next;
                pre = pre.next;
                // 插入到前面位置
                cur.next = pre2.next;
                pre2.next = cur;
                pre2 = cur;

            }
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }
}
