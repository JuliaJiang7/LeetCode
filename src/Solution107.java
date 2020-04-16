import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Julia Jiang
 * @date 2020/3/4 11:19
 * @description 二叉树的层次遍历2
 */
public class Solution107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> levels = new LinkedList<>();
        if(root == null)    return levels;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            //当前层列表
            List<Integer> currentLevel = new ArrayList<>();

            int level_num = queue.size();
            for(int i = 0; i < level_num; i++){
                TreeNode node = queue.remove();
                currentLevel.add(node.val);
                if(node.left != null)   queue.add(node.left);
                if(node.right != null)  queue.add(node.right);
            }

            levels.addFirst(currentLevel);  //比102题加一个反序
        }
        return levels;
    }
}
