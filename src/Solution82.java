import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author julia.jiang
 * @date 2020/8/17 9:20
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution82 {


    /**
     * 参考：https://leetcode.wang/leetCode-82-Remove-Duplicates-from-Sorted-ListII.html
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        // 定义dummy节点
        ListNode pre = new ListNode(0);
        ListNode dummy = pre;
        pre.next = head;

        ListNode curr = head;
        while (curr != null && curr.next != null){
            boolean equal = false;
            // cur 和 cur.next 一直相等，一直后移
            while (curr.next != null && curr.val == curr.next.val){
                curr = curr.next;
                equal = true;
            }
            // 如果相等
            // pre.next 直接指向 cur.next 删除所有重复数字
            if(equal){
                pre.next = curr.next;

            // 如果不相等
            // pre 移到 cur 的地方
            }else{
                pre = curr;
            }

            // cur 后移
            curr = curr.next;
        }
        return dummy.next;
    }
}
