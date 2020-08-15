/**
 * @author Julia Jiang
 * @date 2020/3/5 11:08
 * @description 相同的树
 */
public class Solution100 {
    public boolean isSameTree(TreeNode p, TreeNode q){
        if(p == null && q == null){
            return true;
        }
        if(p != null && q != null && p.val == q.val){
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }
}
