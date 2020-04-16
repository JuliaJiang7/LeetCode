import java.util.HashMap;

/**
 * @author Julia Jiang
 * @date 2020/3/8 13:48
 * @description 从中序和后序遍历构造二叉树
 */
public class Solution106 {
    /**
     * 递归方法
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder){
        return helper(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    private TreeNode helper(int[] inorder, int i_start, int i_end, int[] postorder, int p_start, int p_end){
        if(p_start == p_end){
            return null;
        }

        int root_val = postorder[p_end - 1];
        TreeNode root = new TreeNode(root_val);
        //在中序遍历中找根节点
        int i_root_index = 0;
        for(int i = 0; i < inorder.length; i++){
            if(inorder[i] == postorder[p_end - 1]){
                i_root_index = i;
                break;
            }
        }

        int leftNum = i_root_index - i_start;
        root.left = helper(inorder, i_start, i_root_index, postorder, p_start, p_start + leftNum);
        root.right = helper(inorder, i_root_index + 1, i_end, postorder, p_start + leftNum, p_end - 1);
        return root;
    }

    /**
     * 对递归解法的优化
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }
        return helper2(inorder, 0, inorder.length, postorder, 0, postorder.length, map);
    }

    private TreeNode helper2(int[] inorder, int i_start, int i_end, int[] postorder, int p_start, int p_end, HashMap<Integer, Integer> map){
        if(p_start == p_end){
            return null;
        }

        int root_val = postorder[p_end - 1];
        TreeNode root = new TreeNode(root_val);
        //在中序遍历中找根节点
        int i_root_index = map.get(root_val);

        int leftNum = i_root_index - i_start;
        root.left = helper2(inorder, i_start, i_root_index, postorder, p_start, p_start + leftNum, map);
        root.right = helper2(inorder, i_root_index + 1, i_end, postorder, p_start + leftNum, p_end - 1, map);
        return root;
    }
}
