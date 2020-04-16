/**
 * @author Julia Jiang
 * @date 2020/4/6 11:05
 * @description 最大子序和（DP）
 */
public class Solution53 {
    public int maxSubArray(int[] nums) {
        // dp[i] = x 表示以 nums[i] 结尾的最大子序列和
        int[] dp = new int[nums.length];

        int max = nums[0];
        dp[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
