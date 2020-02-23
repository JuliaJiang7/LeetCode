/**
 * @author Julia Jiang
 * @date 2020/2/19 11:18
 * @description
 */
public class Solution33 {
    /**
     * 二分查找
     * @param nums 查找的数组
     * @param key 查找目标
     * @return 返回key的下标
     */
    private int binarySearch(int[] nums, int key){
        int low, high, mid;
        low = 0;
        high = nums.length - 1;
        while (low <= high){
            mid = (low + high) / 2;
            if(key == nums[mid])
                return mid;
            else if(key < nums[mid])
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }

    public int search(int[] nums, int target){
        if(nums == null || nums.length == 0)
            return -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = start + (start - end) / 2;  //这样写防止溢出
            if(nums[mid] == target)
                return mid;

            //后半部分有序
            if(nums[mid] < nums[end]){
                //target 在后半部分中
                if(nums[mid] < target && nums[end] >= target){
                    start = mid + 1;
                }else{
                    end = mid - 1;
                }
            }else{
                //target在前半部分
                if(nums[mid] > target && nums[start] <= target){
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {7,8,1,2,3,4,5,6};
        Solution33 solution33 = new Solution33();
        System.out.println(solution33.search(nums, 4));
    }
}
