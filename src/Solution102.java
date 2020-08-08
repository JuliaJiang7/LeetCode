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


    List<List<Integer>> res = new ArrayList<>();

    /**
     * 方法一
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){return res;}
        helper(root, 0);
        return res;
    }

    private void helper(TreeNode node, Integer level){
        if(res.size() == level){
            res.add(new ArrayList<>());
        }
        res.get(level).add(node.val);
        if(node.left != null){
            helper(node.left, level + 1);
        }
        if(node.right != null){
            helper(node.right, level + 1);
        }
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            // 当前层列表
            List<Integer> level = new ArrayList<>();

            // 当前队列中的元素个数，即就是当前层的节点数
            int levelNum = queue.size();
            // levelNum 必须在外面定义，因为 queue 的大小在不停的变化
            for(int i = 0; i < levelNum; i++){
                // 移除队列第一个元素
                TreeNode node = queue.remove();
                level.add(node.val);
                if(node.left != null){queue.add(node.left);}
                if(node.right != null){queue.add(node.right);}
            }
            res.add(level);
        }
        return res;
    }
}
