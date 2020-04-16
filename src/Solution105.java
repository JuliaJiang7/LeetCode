import java.util.HashMap;

/**
 * @author Julia Jiang
 * @date 2020/3/6 12:23
 * @description 从前序和中序构造二叉树
 * 参考：https://leetcode.wang/leetcode-105-Construct-Binary-Tree-from-Preorder-and-Inorder-Traversal.html
 */
public class Solution105 {
    /**
     * 解法一：递归
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder){
        return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildTreeHelper(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end){
        //递归终止条件：如果输入的前序序列为空，直接返回null（前序和中序长度一样）
        if(p_start == p_end) return null;

        int root_val = preorder[p_start];
        TreeNode root = new TreeNode(root_val);

        //在中序遍历中找根节点root
        int i_root_index = 0;
        for(int i = i_start; i < i_end; i++){
            if(root_val == inorder[i]){
                i_root_index = i;
                break;
            }
        }

        //左子树的长度
        int leftNum = i_root_index - i_start;
        root.left = buildTreeHelper(preorder, p_start + 1, p_start + leftNum + 1, inorder, i_start, i_root_index);
        root.right = buildTreeHelper(preorder, p_start + leftNum + 1, p_end, inorder, i_root_index + 1, i_end);
        return root;
    }

    /**
     * 解法二：解法一的优化
     * 上一方法中每执行一次 buildTreeHelper 都要在中序中寻找根节点，
     * 这里使用 HashMap 存储中序序列的索引与节点值，找根节点只需要 O(1)
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }
        return buildTreeHelper2(preorder, 0, preorder.length, inorder, 0, inorder.length, map);
    }

    private TreeNode buildTreeHelper2(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end, HashMap<Integer, Integer> map){
        //递归终止条件：如果输入的前序序列为空，直接返回null（前序和中序长度一样）
        if(p_start == p_end) return null;

        int root_val = preorder[p_start];
        TreeNode root = new TreeNode(root_val);

        //在中序遍历中找根节点root
        int i_root_index = map.get(root_val);

        //左子树的长度
        int leftNum = i_root_index - i_start;
        root.left = buildTreeHelper2(preorder, p_start + 1, p_start + leftNum + 1, inorder, i_start, i_root_index, map);
        root.right = buildTreeHelper2(preorder, p_start + leftNum + 1, p_end, inorder, i_root_index + 1, i_end, map);
        return root;
    }

    /**
     * 解法三：解法二的优化，不使用 hashMap，不再从中序遍历中寻找根节点的位置（好难）
     * 而是直接把值 stop 传过去，表明当前子树的结束点
     * pre 在前序序列中遍历来建立结点；in  在中序序列中遍历，遇到stop，则表明当前子树结束，返回null
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree3(int[] preorder, int[] inorder){
        return buildTreeHelper3(preorder,  inorder, (long)Integer.MAX_VALUE + 1);
    }

    int pre = 0;
    int in = 0;
    private TreeNode buildTreeHelper3(int[] preorder, int[] inorder, long stop) {
        //到达末尾返回 null
        if(pre == preorder.length){
            return null;
        }
        //到达停止点返回 null
        //当前停止点已经用了，in 后移
        if (inorder[in] == stop) {
            in++;
            return null;
        }
        int root_val = preorder[pre++];

        TreeNode root = new TreeNode(root_val);
        //左子树的停止点是当前的根节点
        root.left = buildTreeHelper3(preorder,  inorder, root_val);
        //右子树的停止点是当前树的停止点
        root.right = buildTreeHelper3(preorder, inorder, stop);
        return root;
    }
}
