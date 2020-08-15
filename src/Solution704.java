/**
 * @author julia.jiang
 * @date 2020/8/6 9:49
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution704 {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }
}
