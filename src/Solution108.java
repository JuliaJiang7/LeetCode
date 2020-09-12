/**
 * @author julia.jiang
 * @date 2020/9/1 9:01
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution108 {

    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);

    }

    private TreeNode helper(int[] nums, int start, int end){
        if(start > end){
            return null;
        }
        int len = end - start + 1;
        int mid = start + len / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, start , mid - 1);
        root.right = helper(nums, mid + 1, end);
        return root;
    }
}
