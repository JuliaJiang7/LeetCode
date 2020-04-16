/**
 * @author Julia Jiang
 * @date 2020/3/26 10:58
 * @description 缺失的第一个正数
 * https://leetcode.wang/leetCode-41-First-Missing-Positive.html
 */
public class Solution41 {

    //解法一：交换
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++){
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]){
                swap(nums, i, nums[i] - 1);
            }
        }

        for(int i = 0; i < n; i++){
            if(nums[i] != i + 1){
                return i + 1;
            }
        }

        return n + 1;
    }

    private void swap(int[] nums, int i, int j){
        if(i == j)  return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 解法二：标记
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {
        int k = positiveNum(nums);
        for (int i = 0; i < k; i++){
            int index = Math.abs(nums[i]) - 1;
            if (index + 1 <= k){
                int num = Math.abs(nums[index]);
                nums[index] = num * -1;
            }
        }

        for(int i = 0; i < k; i++){
            if(nums[i] > 0){
                return i + 1;
            }
        }
        return k + 1;
    }

    private int positiveNum(int[] nums){
        int p = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            if (nums[i] > 0){
                swap(nums, i, p);
                p++;
            }
        }
        return p;
    }
}
