import sun.reflect.generics.tree.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author julia.jiang
 * @date 2020/8/31 10:31
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution99 {

    /**
     * https://leetcode.wang/leetcode-99-Recover-Binary-Search-Tree.html
     * @param root
     */
    public void recoverTree(TreeNode root) {
        TreeNode cur = root;
        TreeNode pre = null, first = null, second = null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (pre != null && pre.val >= cur.val) {
                // 第一次遇到逆序对
                if (first == null) {
                    // 如果相邻的数字交换，只会出现一次逆序，因此这里 second 也要赋值
                    first = pre;
                    second = cur;
                // 第二次遇到逆序对
                } else {
                    second = cur;
                }
            }
            pre = cur;
            cur = cur.right;
        }

        // 交换
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
}
