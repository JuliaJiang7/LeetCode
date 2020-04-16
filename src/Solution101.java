/**
 * @author Julia Jiang
 * @date 2020/3/5 12:09
 * @description   对称二叉树
 */
public class Solution101 {
    public boolean isSymmetric(TreeNode root){
        if(root == null)
            return true;
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode p, TreeNode q){
        if(p == null && q == null)  return true;
        if(p == null || q == null)  return false;

        return isMirror(p.left, q.right) && isMirror(p.right, q.left);

    }
}
