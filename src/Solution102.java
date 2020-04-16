import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Julia Jiang
 * @date 2020/3/4 10:19
 * @description 二叉树的层次遍历
 */
public class Solution102 {
    List<List<Integer>> levels = new ArrayList<>();

    /**
     * 递归
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root){
        if(root == null)    return levels;
        helper(root, 0);
        return levels;
    }

    public void helper(TreeNode root, int level){
        if(levels.size() == level){
            levels.add(new ArrayList<Integer>());
        }
        levels.get(level).add(root.val);

        if(root.left != null){
            helper(root.left, level+1);
        }
        if(root.right != null){
            helper(root.right, level+1);
        }
    }

    /**
     * 迭代
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root){
        List<List<Integer>> levels = new ArrayList<>();
        if(root == null)    return levels;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            //当前层列表
            List<Integer> currentLevel = new ArrayList<>();

            int level_num = queue.size();   //队列中的元素个数，即就是当前层的节点数
            for(int i = 0; i < level_num; i++){     //level_num 必须在外面定义，因为 queue 的大小在不停的变化
                TreeNode node = queue.remove();     //移除队列第一个元素
                currentLevel.add(node.val);
                if(node.left != null)   queue.add(node.left);
                if(node.right != null)  queue.add(node.right);
            }

            levels.add(currentLevel);
        }
        return levels;
    }
}
