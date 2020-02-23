import java.util.Arrays;

/**
 * @author Julia Jiang
 * @date 2020/2/19 13:48
 * @description 在排序数组中查找元素的第一个和最后一个位置
 * 参考题解：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/er-fen-cha-zhao-suan-fa-xi-jie-xiang-jie-by-labula/
 */
public class Solution34 {
    /**
     * 寻找左侧边界的二分查找
     * @param nums
     * @param target
     * @return
     */
    private int left_boung(int[] nums, int target){
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
        // 类似之前算法的处理方式
        return nums[left] == target ? left : -1;
    }

    /**
     * 寻找右侧边界
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
        if (left == 0) return -1;
        return nums[left-1] == target ? (left-1) : -1;
    }

    public int[] searchRange(int[] nums, int target){
        int left_bound = left_boung(nums, target);
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
