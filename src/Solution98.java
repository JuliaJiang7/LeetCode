import sun.reflect.generics.tree.Tree;

import java.util.Stack;

/**
 * @author Julia Jiang
 * @date 2020/3/8 14:54
 * @description 验证二叉搜索树
 */
public class Solution98 {
    /**
     * 解法一：（递归）判断根节点是否合法，即找到左子树中的最大数和右子树最小数来比较
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        // 递归终止条件
        if (root == null || (root.right == null && root.left == null)) {
            return true;
        }

        // 判断左子树 和 左子树与根节点的关系
        if (isValidBST(root.left)) {
            if (root.left != null) {
                // 获取左子树的最大值
                int maxLeft = getMaxOfBST(root.left);
                if (root.val <= maxLeft) {
                    return false;
                }
            }
        } else {
            return false;
        }

        // 判断右子树 和 右子树与根节点的关系
        if (isValidBST(root.right)) {
            if (root.right != null) {
                // 获取右子树最小值
                int minRight = getMinOfBST(root.right);
                if (root.val >= minRight) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    /**
     * 获取搜索二叉树的最大值
     *
     * @param root
     * @return
     */
    private int getMaxOfBST(TreeNode root) {
        int max = root.val;
        while (root.right != null) {
            max = root.right.val;
            root = root.right;
        }
        return max;
    }

    /**
     * 获取搜索二叉树最小值
     *
     * @param root
     * @return
     */
    private int getMinOfBST(TreeNode root) {
        int min = root.val;
        while (root.left != null) {
            min = root.left.val;
            root = root.left;
        }
        return min;
    }

    /**
     * 解法二：（递归）从根节点出发，来限制左右节点的取值范围（执行时间快）
     * 实际使用树的 DFS，也就是二叉树的先序遍历，在遍历过程中判断当前值是否在取值区间内
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        // 节点值均为 Int 型，故最大值设为 long
        long max = (long) Integer.MAX_VALUE + 1;
        long min = (long) Integer.MIN_VALUE - 1;
        return getAns(root, max, min);
    }

    private boolean getAns(TreeNode root, long max, long min) {
        if(root == null){
            return true;
        }

        if(root.val <= min || root.val >= max){
            return false;
        }

        return getAns(root.left, min, root.val) && getAns(root.right, root.val, max);
    }

    /**
     * 解法三：迭代：中序遍历的栈解法（参考94）
     *
     * @param root
     * @return
     */
    public boolean isValidBTS3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode pre = null;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            //出栈（这里出栈的值会添加到中序序列后）
            curr = stack.pop();
            if (pre != null && pre.val >= curr.val) {
                return false;
            }
            pre = curr;
            curr = curr.right;
        }
        return true;
    }
}
