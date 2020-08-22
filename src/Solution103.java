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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        while(!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();

            int levelNum = queue.size();
            for(int i = 0; i < levelNum; i++){
                TreeNode node = queue.remove();
                // 在当前层列表中加入元素（只需要改变这里）
                if(depth % 2 == 0){
                    level.add(node.val);
                }else{
                    level.add(0, node.val);
                }
                if(node.left != null){queue.add(node.left);}
                if(node.right != null){queue.add(node.right);}
            }
            res.add(level);
            depth++;
        }
        return res;
    }
}
