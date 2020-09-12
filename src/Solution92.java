import java.util.List;

/**
 * @author julia.jiang
 * @date 2020/8/26 9:11
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution92 {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        int count = 1;
        ListNode dummy = new ListNode(0);
        dummy.next = null;
        ListNode cur = head;
        ListNode pre = dummy;
        while (cur != null){
            if (m == count){
                ListNode pre2 = pre;
                pre = cur;
                for(; count <= n; count++){
                    ListNode next = cur.next;
                    cur.next = pre2.next;
                    pre2.next = cur;
                    cur = next;
                }
            }else{
                ListNode tmp = cur.next;
                cur.next = pre.next;
                pre.next = cur;
                pre = cur;
                cur = tmp;
                count++;
            }

        }
        return dummy.next;
    }

}
