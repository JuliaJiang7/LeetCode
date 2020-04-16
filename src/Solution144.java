import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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

    public void helper(TreeNode root, List<Integer> res){
        if(root != null){
            res.add(root.val);
            if(root.left != null){
                helper(root.left, res);
            }
            if(root.right != null){
                helper(root.right, res);
            }
        }
    }

    /**
     * 队列（中序遍历是栈）
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode p = root;
        while (p != null || !deque.isEmpty()){
            while (p != null){
                res.add(p.val);
                deque.push(p);
                p = p.left;
            }
            p = deque.pop().right;
        }
        return res;
    }
}
