/**
 * @author Julia Jiang
 * @date 2020/2/20 13:56
 * @description 二分查找（基本搜索：查找一个数）
 */
public class Solution35 {
    public int searchInsert(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        int mid;

        while (left <= right){
            mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }
        }

        //如果没找到，left存储数字本来的位置
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,6};
        Solution35 solution35 = new Solution35();
        System.out.println(solution35.searchInsert(nums, 7));
    }
}
