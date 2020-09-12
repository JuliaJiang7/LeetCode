/**
 * @author julia.jiang
 * @date 2020/9/3 9:49
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution114 {

    /**
     * https://leetcode.wang/leetcode-114-Flatten-Binary-Tree-to-Linked-List.html
     * @param root
     */
    public void flatten(TreeNode root) {
        while (root != null){
            if(root.left == null){
                root = root.right;
            }else{
                // 找左子树最右边节点
                TreeNode pre = root.left;
                while (pre.right != null){
                    pre = pre.right;
                }
                // 将原来的右子树接到左子树的最右边
                pre.right = root.right;
                // 将左子树茶润到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }
    }
}
