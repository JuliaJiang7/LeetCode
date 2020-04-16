import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Julia Jiang
 * @date 2020/3/6 11:58
 * @description 二叉树的锯齿形层次遍历
 */
public class Solution103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null)    return res;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int depth = 0;
        while (!queue.isEmpty()){
            List<Integer> currentLevel = new ArrayList<>();
            int count = queue.size();
            for(int i = 0; i < count; i++){
                TreeNode node = queue.poll();
                //根据奇偶层，给当前层添加数据
                if(depth % 2 == 0)
                    currentLevel.add(node.val);
                else
                    currentLevel.add(0, node.val);
                if(node.left != null)   queue.add(node.left);
                if(node.right != null)  queue.add(node.right);
            }
            res.add(currentLevel);
            depth++;
        }
        return res;
    }
}
