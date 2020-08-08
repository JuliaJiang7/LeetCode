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

    // 使用链表存储，在表头插入效率更高
    LinkedList<List<Integer>> res = new LinkedList<>();
    /**
     * 方法一
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root == null){return res;}
        helper(root, 0);
        return res;
    }

    private void helper(TreeNode node, Integer level){
        if(res.size() == level){
            // 在链表表头插入，得到反序序列
            res.addFirst(new ArrayList<>());
        }
        // 插入当前值，注意下标
        res.get(res.size() - level - 1).add(node.val);

        if(node.left != null){
            helper(node.left, level + 1);
        }
        if(node.right != null){
            helper(node.right, level + 1);
        }
    }

    public List<List<Integer>> levelOrderBottom3(TreeNode root) {
        // 使用链表，在表头插入效率更高
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int levelNum = queue.size();
            for(int i = 0; i < levelNum; i++){
                TreeNode node = queue.remove();
                level.add(node.val);
                if(node.left != null){queue.add(node.left);}
                if(node.right != null){queue.add(node.right);}
            }
            // 在表头插入，得到反序结果
            res.addFirst(level);
        }
        return res;
    }
}
