import java.util.*;

/**
 * @author Julia Jiang
 * @date 2020/3/10 11:27
 * @description 路径总和
 * https://leetcode.wang/leetcode-112-Path-Sum.html
 */
public class Solution112 {
    /**
     * 解法一：递归
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum){
        if(root == null)    return false;
        if(root.val == sum && root.left == null && root.right == null) return true;

        boolean leftPath = false;
        if(root.left != null)
            leftPath = hasPathSum(root.left, sum - root.val);

        if(leftPath){
            return true;
        }else{
            if(root.right != null)
                return hasPathSum(root.right, sum - root.val);
            return false;
        }
    }

    /**
     * 解法一：递归 按照111题的思路
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int sum){
        if(root == null)    return false;
        if(root.val == sum && root.left == null && root.right == null)  return true;
        if(root.right == null){
            return hasPathSum2(root.left, sum - root.val);
        }
        if(root.left == null){
            return hasPathSum2(root.right, sum - root.val);
        }
        return hasPathSum2(root.left, sum - root.val) || hasPathSum2(root.right, sum - root.val);
    }

    /**
     * 解法二：BFS: 多一个队列存储当前节点到根节点已经累加的和，该队列和层次遍历队列的节点位置对应
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum3(TreeNode root, int sum){
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> queueSum = new LinkedList<>();
        if(root == null)    return false;
        queue.offer(root);
        queueSum.offer(root.val);
        while (!queue.isEmpty()){
            int levelNum = queue.size();
            for(int i = 0; i < levelNum; i++){
                TreeNode curr = queue.poll();
                Integer currSum = queueSum.poll();
                if(curr.left == null && curr.right == null && currSum == sum){
                    return true;
                }
                if(curr.left != null){
                    queue.offer(curr.left);
                    queueSum.offer(curr.left.val);
                }
                if(curr.right != null){
                    queue.offer(curr.right);
                    queueSum.offer(curr.right.val);
                }
            }
        }
        return false;
    }

    /**
     * 解法三：DFS，后序遍历的栈解法（好难 没懂）
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum4(TreeNode root, int sum){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode pre = null;
        int curSum = 0; //记录当前
        while (curr != null || !stack.isEmpty()){
            while(curr != null){
                stack.push(curr);  //添加根节点
                curSum += curr.val;
                curr = curr.left;  //添加左节点
            }
            curr = stack.peek();
            if(curSum == sum && curr.left == null && curr.right == null){
                return true;
            }
            if(curr.right == null || curr.right == pre){
                TreeNode pop = stack.pop();
                curSum -= pop.val;
                pre = curr;
                curr = null;
            }else{
                curr = curr.right;
            }
        }
        return false;
    }
}
