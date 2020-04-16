import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Julia Jiang
 * @date 2020/3/3 11:36
 * @description 二叉树的中序遍历
 */
public class Solution94 {
    /**
     * 解法一：递归解法
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List<Integer> res){
        if (root == null) return;

        helper(root.left, res);
        res.add(root.val);
        helper(root.right, res);
    }

    /**
     * 解法二：栈
     * 利用栈去模拟递归。递归压栈的过程就是保存现场，保存当前的变量。
     * 解法一当中当前有用的变量就是 root， 我们用栈把每一次的 root 保存起来
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.empty()){
            //节点不为空就一直压栈
            while (curr != null){
                stack.push(curr);
                curr = curr.left;   //考虑左子树
            }
            //节点为空，就出栈
            curr = stack.pop();
            res.add(curr.val);
            //考虑右子树
            curr = curr.right;
        }
        return res;
    }
}
