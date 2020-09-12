import java.util.LinkedList;
import java.util.List;

/**
 * @author julia.jiang
 * @date 2020/9/3 9:26
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution113 {

    List<List<Integer>> res = new LinkedList<>();

    /**
     * 自己写的，回溯
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        LinkedList<Integer> track = new LinkedList<>();
        if (root == null) {
            return res;
        }
        track.add(root.val);
        backtrack(root, sum, track, root.val);
        return res;
    }

    private void backtrack(TreeNode root, int sum, LinkedList<Integer> track, int tmp) {
        if (tmp == sum && root.left == null && root.right == null) {
            res.add(new LinkedList<>(track));
        }

        if (root.left != null) {
            // 做出选择：左子树
            track.add(root.left.val);
            backtrack(root.left, sum, track, tmp + root.left.val);
            // 撤销选择
            track.removeLast();
        }

        if (root.right != null) {
            track.add(root.right.val);
            backtrack(root.right, sum, track, tmp + root.right.val);
            track.removeLast();
        }
    }
}
