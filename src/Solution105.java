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
     * 时间复杂度：O(N^2)，这里 N 是二叉树的结点个数，每调用一次递归方法创建一个结点，一共创建 N 个结点，
     *                  在中序遍历中找到根结点在中序遍历中的位置，是与 N 相关的，这里不计算递归方法占用的时间。
     * 空间复杂度：O(1)，这里不计算递归方法占用的空间。
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        // pStart 表示先序的第一位索引
        // pEnd 表示先序的最后一位索引
        // 如果输入的前序序列为空，返回 null
        if(pEnd < pStart){return null;}

        int root = preorder[pStart];
        TreeNode node = new TreeNode(root);

        // 在中序遍历中找root，即分隔点
        int point = iStart;
        for(; point <= iEnd; point++){
            if(inorder[point] == root){
                break;
            }
        }
        // 左子树长度
        int leftLen = point - iStart;

        node.left = helper(preorder, pStart + 1, pStart + leftLen, inorder, iStart, point - 1);
        node.right = helper(preorder, pStart + leftLen + 1, pEnd, inorder, point + 1, iEnd);

        return node;
    }

    /**
     * 解法二：解法一的优化
     * 上一方法中每执行一次 helper 都要在中序中寻找根节点，这里使用 HashMap 存储中序序列的索引与节点值，找根节点只需要 O(1)
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }
        return helper2(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode helper2(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd, HashMap<Integer, Integer> map) {
        if(pEnd < pStart){return null;}

        int root = preorder[pStart];
        TreeNode node = new TreeNode(root);

        // 使用map找分隔点
        int point = map.get(root);

        int leftLen = point - iStart;

        node.left = helper2(preorder, pStart + 1, pStart + leftLen, inorder, iStart, point - 1, map);
        node.right = helper2(preorder, pStart + leftLen + 1, pEnd, inorder, point + 1, iEnd, map);

        return node;
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
