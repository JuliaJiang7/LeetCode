/**
 * @author julia.jiang
 * @date 2020/8/15 10:14
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution81 {
    public boolean search(int[] nums, int target) {
        int len = nums.length;
        if(len == 0){return false;}
        int left = 0, right = len - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return true;
            }

            // 左半段有序
            if(nums[left] < nums[mid]){
                // target在左半段
                if(nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                    // target在右半段
                }else {
                    left = mid + 1;
                }
            // 相等，left++
            }else if(nums[left] == nums[mid]){
                left++;

            // 右半段有序
            } else{
                // target 在右半段
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                    // target 在左半段
                }else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }
}
