/**
 * @author Julia Jiang
 * @date 2020/2/19 11:18
 * @description
 */
public class Solution33 {
    public int search(int[] nums, int target) {
        int len = nums.length;
        if(len == 0){return -1;}
        int left = 0, right = len - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return mid;
            }

            // 左半段有序
            // 等于的情况出现在：left = mid 时，即left和mid重合，此时只剩下两个元素
            // 这个等号不能去掉，因为mid求值时取整，两个元素时mid只会和left重合
            // 如果去掉等号，计算right = mid - 1时，right会直接越界
            if(nums[left] <= nums[mid]){
                // target在左半段
                if(nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                // target在右半段
                }else {
                    left = mid + 1;
                }
            // 右半段有序
            }else{
                // target 在右半段
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                // target 在左半段
                }else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public int search2(int[] nums, int target) {
        int len = nums.length;
        if(len == 0){return -1;}
        int left = 0, right = len - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return mid;
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
            // left 和 mid 重合，直接跳过left
            }else if(nums[left] == nums[mid]){
                left++;
            // 右半段有序
            }else{
                // target 在右半段
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                    // target 在左半段
                }else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution33 solution33 = new Solution33();
        solution33.search(new int[]{3,1}, 1);
    }
}
