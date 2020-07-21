/**
 * @author julia.jiang
 * @date 2020/7/17 9:44
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution83 {

    /**
     * 解法一：迭代
     * 自己写的
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode pre = head;
        ListNode curr = pre.next;


        while (curr != null){
            if(curr.val == pre.val){
                curr = curr.next;
            }else{
                ListNode tmp = curr;
                curr = curr.next;
                pre.next = tmp;
                pre = tmp;
            }
        }

        pre.next = null;
        return head;
    }

    /**
     * 解法二：递归
     * 参考：https://leetcode.wang/leetCode-83-Remove-Duplicates-from-Sorted-List.html#%E8%A7%A3%E6%B3%95%E4%B8%89-%E9%80%92%E5%BD%92
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        if(head.val == head.next.val){
            // 去掉头结点
            return deleteDuplicates2(head.next);
        }else{
            // 加上头结点
            head.next = deleteDuplicates2(head.next);
            return head;
        }
    }
}
