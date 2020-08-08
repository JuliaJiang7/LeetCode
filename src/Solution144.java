import java.util.*;

/**
 * @author Julia Jiang
 * @date 2020/3/3 12:07
 * @description 二叉树的先序遍历
 */
public class Solution144 {
    public List<Integer> preorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode node, List<Integer> res){
        if (node == null){return;}

        res.add(node.val);
        helper(node.left, res);
        helper(node.right, res);
    }

    /**
     * 解法二：迭代
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()){
            // 节点不为空，就一直访问并压栈
            while (curr != null){
                res.add(curr.val);
                stack.push(curr);
                // 考虑左子树
                curr = curr.left;
            }
            // 节点为空就出栈
            curr = stack.poll();
            // 考虑右子树
            curr = curr.right;
        }
        return res;
    }
}
