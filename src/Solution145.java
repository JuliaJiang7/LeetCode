
import java.util.*;

/**
 * @author Julia Jiang
 * @date 2020/3/3 12:40
 * @description 二叉树的后序遍历
 */
public class Solution145 {

    public List<Integer> postorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode node, List<Integer> res){
        if (node == null){return;}

        helper(node.left, res);
        helper(node.right, res);
        res.add(node.val);
    }

    public List<Integer> postorderTraversal2(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        if(root == null) return res;
        stack.push(root);

        while (!stack.empty()){
            //根节点出栈
            TreeNode node = stack.pop();
            //左右节点入栈
            if(node.left != null){
                stack.push(node.left);
            }
            if(node.right != null){
                stack.push(node.right);
            }
            res.add(node.val);
        }
        //反向输出
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        Solution145 solution145 = new Solution145();
        //System.out.println(solution145.postorderTraversal(root));
        System.out.println(solution145.postorderTraversal2(root));
    }

    /**
     * 解法三：栈解法
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal3(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        // 记录上一个遍历的节点
        TreeNode pre = null;
        while (curr != null || !stack.isEmpty()){
            while (curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            // 获取当前根节点
            TreeNode temp = stack.peek();
            // 是否变到右子树
            if(temp.right != null && temp.right != pre){
                curr = temp.right;
            }else{
                res.add(temp.val);
                // 记录上一个遍历的节点
                pre = temp;
                stack.pop();
            }
        }
        return res;
    }

}
