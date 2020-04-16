import java.util.ArrayList;
import java.util.List;

/**
 * @author Julia Jiang
 * @date 2020/3/13 10:59
 * @description 不同的二叉搜索树2
 * https://leetcode.wang/leetCode-95-Unique-Binary-Search-TreesII.html
 */
public class Solution95 {
    /**
     * 解法一：递归（好难）
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n){
        List<TreeNode> ans = new ArrayList<>();
        if(n == 0)  return ans;
        return getAns(1, n);
    }

    private List<TreeNode> getAns(int start, int end){
        List<TreeNode> ans = new ArrayList<>();
        if(start > end){
            ans.add(null);
            return null;
        }
        if(start == end){
            TreeNode tree = new TreeNode(start);
            ans.add(tree);
            return ans;
        }
        for(int i = start; i <= end; i++){
            List<TreeNode> leftTrees = getAns(start, i - 1);
            List<TreeNode> rightTrees = getAns(i + 1, end);
            for(TreeNode leftTree : leftTrees){
                for(TreeNode rightTree : rightTrees){
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    ans.add(root);
                }
            }
        }
        return ans;
    }

    /**
     * 解法二：动态规划（根本想不到...）
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees2(int n) {
        //保存上一次所有的解
        List<TreeNode> pre = new ArrayList<TreeNode>();
        if (n == 0) {
            return pre;
        }
        pre.add(null);

        //每次增加一个数字
        for (int i = 1; i <= n; i++) {
            //保存当前所有的解
            List<TreeNode> cur = new ArrayList<TreeNode>();
            //遍历之前的所有解
            for (TreeNode root : pre) {
                //第一种情况：插入到根节点
                TreeNode insert = new TreeNode(i);
                insert.left = root;
                cur.add(insert);

                //第二种情况：插入到右孩子，右孩子的右孩子...最多找 n 次孩子
                for (int j = 0; j <= n; j++) {
                    TreeNode root_copy = treeCopy(root); //复制当前的树
                    TreeNode right = root_copy; //找到要插入右孩子的位置
                    int k = 0;
                    //遍历 j 次找右孩子
                    for (; k < j; k++) {
                        if (right == null)
                            break;
                        right = right.right;
                    }
                    //到达 null 提前结束
                    if (right == null)
                        break;
                    //保存当前右孩子的位置的子树作为插入节点的左孩子
                    TreeNode rightTree = right.right;
                    insert = new TreeNode(i);
                    right.right = insert; //右孩子是插入的节点
                    insert.left = rightTree; //插入节点的左孩子更新为插入位置之前的子树
                    //加入结果中
                    cur.add(root_copy);
                }
            }
            pre = cur;

        }
        return pre;
    }


    private TreeNode treeCopy(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = treeCopy(root.left);
        newRoot.right = treeCopy(root.right);
        return newRoot;
    }

}
