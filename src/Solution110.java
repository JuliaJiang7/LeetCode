/**
 * @author julia.jiang
 * @date 2020/9/1 9:18
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution110 {

    /**
     * 方法一：递归
     * https://leetcode.wang/leetcode-110-Balanced-Binary-Tree.html
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null){return true;}
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if(Math.abs(left - right) > 1){
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int getHeight(TreeNode root){
        if(root == null){
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    /**
     * 方法二：递归的优化
     * @param root
     * @return
     */
    public boolean isBalanced2(TreeNode root) {
        return getHeight2(root) != -1;
    }

    private int getHeight2(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = getHeight2(root.left);
        if(left == -1){
            return -1;
        }
        int right = getHeight2(root.right);
        if(right == -1){
            return -1;
        }
        if(Math.abs(left - right) > 1){
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
