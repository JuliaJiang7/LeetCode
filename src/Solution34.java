import java.util.Arrays;

/**
 * @author Julia Jiang
 * @date 2020/2/19 13:48
 * @description 在排序数组中查找元素的第一个和最后一个位置(二分查找求左右边界)
 * 参考题解：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/er-fen-cha-zhao-suan-fa-xi-jie-xiang-jie-by-labula/
 */
public class Solution34 {
    /**
     * 寻找左侧边界的二分查找
     * [1,2,2,2,3] target=2 最终 left = 1
     * [2,3,5,7] target=1 最终 left = 0
     * [2,3,5,7] target=8 最终 left = 4
     * @param nums
     * @param target
     * @return
     */
    private int left_bound(int[] nums, int target){
        if(nums.length == 0)    return -1;
        int left = 0;
        int right = nums.length;    //注意

        while (left < right){       //注意
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                right = mid;    //注意
            }else if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid;    //注意
            }
        }

        // target 比所有数都大
        if (left == nums.length) return -1;
        // 如果target 比所有数都小，则 nums[0] != target ，会返回 -1
        // 如果 nums[left] == target， 则 left 为左侧边界
        return nums[left] == target ? left : -1;
    }

    /**
     * 寻找右侧边界
     *[1,2,2,3] target=2 最终left = 3
     * [1,2,3,4] target=5 最终 left = 4
     * [1,2,3,4] target=0 最终 left =
     * @param nums
     * @param target
     * @return
     */
    private int right_bound(int[] nums, int target){
        if(nums.length == 0)    return -1;
        int left = 0;
        int right = nums.length;

        while (left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                left = mid + 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid;
            }
        }

        //target 比每个数都小
        if (left == 0) return -1;
        //如果 target 比每个数都大，则 nums[left-1] != target, 返回-1
        //如果 nums[left-1] == target, 返回 left-1 即为右侧边界
        return nums[left-1] == target ? (left-1) : -1;
    }

    public int[] searchRange(int[] nums, int target){
        int left_bound = left_bound(nums, target);
        int right_bound = right_bound(nums, target);
        int[] ans = {left_bound, right_bound};
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,9};
        Solution34 solution34 = new Solution34();
        int[] ans = solution34.searchRange(nums, 8);
        System.out.println(Arrays.toString(ans));
    }
}
