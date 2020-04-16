import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Julia Jiang
 * @date 2020/3/10 11:42
 * @description 二叉树的最小深度
 * 要区别于二叉树的最大深度104
 */
public class Solution111 {
    /**
     * 解法一：递归
     * @param root
     * @return
     */
    public int minDepth(TreeNode root){
        if(root == null)    return 0;
        //遇到叶子结点直接返回1，这种情况已经包含在下面两种情况中
        //if(root.left == null && root.right == null)   return 1;
        if(root.left == null)
            return minDepth(root.right) + 1;
        if(root.right == null)
            return minDepth(root.left) + 1;

        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    /**
     * BFS, 层次遍历的队列解法
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null)    return 0;
        queue.offer(root);
        int level = 1;
        while (!queue.isEmpty()){
            int levelNum = queue.size();
            for(int i = 0; i < levelNum; i++){
                TreeNode curr = queue.poll();
                if(curr.right == null && curr.left == null)
                    return level;
                if(curr.left != null){
                    queue.offer(curr.left);
                }
                if(curr.right != null){
                    queue.offer(curr.right);
                }
            }
            level++;
        }
        return level;
    }
}
