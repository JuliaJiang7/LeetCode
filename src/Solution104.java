import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Julia Jiang
 * @date 2020/3/4 11:30
 * @description 二叉树的最大深度
 */
public class Solution104 {
    /**
     * 解法一：递归
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root == null)    return 0;
        int leftDepth = 0, rightDepth = 0;
        if(root.left != null){
            leftDepth = maxDepth(root.left);
        }
        if(root.right != null){
            rightDepth = maxDepth(root.right);
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 解法一的简化
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if(root == null)    return 0;
        return Math.max(maxDepth2(root.left), maxDepth2(root.right)) + 1;
    }

    /**
     * BFS，即使用队列进行层次遍历，用变量 level 存储当前深度
     * @param root
     * @return
     */
    public int maxDepth3(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null)    return 0;
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()){
            int levelNum = queue.size();
            for(int i = 0; i < levelNum; i++){
                TreeNode curr = queue.poll();
                if(curr.left != null)
                    queue.offer(curr.left);
                if(curr.right != null)
                    queue.offer(curr.right);

            }
            level++;
        }
        return level;
    }

    /**
     * 使用队列迭代，BFS
     * @param root
     * @return
     */
    public int maxDepth4(TreeNode root){
        //Pair 是 节点与当前层数的 配对
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        if(root != null)
            queue.add(new Pair<>(root, 1));

        int depth = 0;
        while (!queue.isEmpty()){
            Pair<TreeNode, Integer> current = queue.poll();
            root = current.getKey();
            int current_depth = current.getValue();
            depth = Math.max(depth, current_depth);
            if(root.left != null)
                queue.add(new Pair<>(root.left, current_depth+1));
            if(root.right != null)
                queue.add(new Pair<>(root.right, current_depth+1));
        }

        return depth;
    }
}
