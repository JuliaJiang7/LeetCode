
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author Julia Jiang
 * @date 2020/3/3 12:40
 * @description 二叉树的后序遍历
 */
public class Solution145 {
    /**
     * 解法一：递归
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    /**
     * 解法二
     * @param root
     * @param res
     */
    public void helper(TreeNode root, List<Integer> res){
        if(root != null){
            if(root.left != null){
                helper(root.left, res);
            }
            if(root.right != null){
                helper(root.right, res);
            }
            res.add(root.val);
        }
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
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode last = null;
        while (curr != null || !stack.isEmpty()){
            while(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode temp = stack.peek();
            //是否变到右子树: 判断右子树是否为空和是否是从右节点回到的根节点
            if(temp.right != null && temp.right != last){
                curr = temp.right;
            }else{
                list.add(temp.val);
                last = temp;
                stack.pop();
            }
        }
        return list;
    }

}
