import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author Julia Jiang
 * @date 2020/3/5 12:09
 * @description   对称二叉树
 */
public class Solution101 {
    public boolean isSymmetric(TreeNode root){
        if(root == null){return true;}
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode p, TreeNode q){
        if(p == null && q == null){return true;}
        if(p == null || q == null){return false;}
        if(p.val == q.val){
            return isMirror(p.left, q.right) && isMirror(p.right, q.left);
        }
        return false;
    }


    public boolean isSymmetric2(TreeNode root){
        if(root == null){return true;}
        Deque<TreeNode> leftStack = new ArrayDeque<>();
        Deque<TreeNode> rightStack = new ArrayDeque<>();
        TreeNode leftCur = root.left;
        TreeNode rightCur = root.right;
        while (leftCur != null || rightCur != null || !leftStack.isEmpty() || !rightStack.isEmpty()){
            while (leftCur != null){
                leftStack.push(leftCur);
                // 考虑左子树
                leftCur = leftCur.left;
            }
            while (rightCur != null){
                rightStack.push(rightCur);
                // 考虑右子树
                rightCur = rightCur.right;
            }
            // 长度不同，就返回
            if(leftStack.size() != rightStack.size()){
                return false;
            }
            leftCur = leftStack.poll();
            rightCur = rightStack.poll();
            if(leftCur.val != rightCur.val){
                return false;
            }
            // 考虑右子树
            leftCur = leftCur.right;
            // 考虑左子树
            rightCur = rightCur.left;
        }
        return true;
    }
}
