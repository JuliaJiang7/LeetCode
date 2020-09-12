import java.util.ArrayList;
import java.util.List;

/**
 * @author julia.jiang
 * @date 2020/9/2 9:51
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution109 {
    /**
     * 解法一：将链表转为List，和108题相同
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return helper(list, 0, list.size());
    }

    private TreeNode helper(List<Integer> list, int start, int end) {
        if (start == end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = helper(list, start, mid);
        root.right = helper(list, mid + 1, end);
        return root;
    }

    ListNode cur = null;

    /**
     * 解法二：中序遍历思想
     * https://leetcode.wang/leetcode-109-Convert-Sorted-List-to-Binary-Search-Tree.html
     * @param head
     * @return
     */
    public TreeNode sortedListToBST2(ListNode head) {
        cur = head;
        int end = 0;
        while (head != null) {
            end++;
            head = head.next;
        }
        return helper2(0, end);
    }

    private TreeNode helper2(int start, int end) {
        if(start == end){
            return null;
        }
        int mid = start + (end - start) / 2;
        // 遍历左子树并且将根节点返回
        TreeNode left = helper2(start, mid);
        // 遍历当前节点
        TreeNode root = new TreeNode(cur.val);
        root.left = left;
        // 指针后移，进行下一次赋值
        cur = cur.next;
        // 遍历右子树并将根节点返回
        TreeNode right = helper2(mid + 1, end);
        root.right = right;
        return root;
    }
}
