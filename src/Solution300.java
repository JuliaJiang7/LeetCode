import java.util.Arrays;

/**
 * @author Julia Jiang
 * @date 2020/2/23 13:37
 * @description 最长上升子序列
 */
public class Solution300 {
    /**
     * 动态规划
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums){
        //dp[i] = n 表示：以 nums[i] 这个数字结尾的最长上升子序列的长度（想不到）
        int[] dp = new int[nums.length];
        //初始化，最长上升子序列最少为数字本身，故初始化为1
        Arrays.fill(dp, 1);

        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }

        int res = 0;
        for(int i = 0; i < nums.length; i++){
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        Solution300 solution300 = new Solution300();
        System.out.println(solution300.lengthOfLIS(nums));
        System.out.println(solution300.lengthOfLIS2(nums));
    }

    /**
     * 二分查找方法（纸牌思想）（好难）
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums){
        int[] top = new int[nums.length];
        //牌初始堆数定义为0
        int piles = 0;
        for(int i = 0; i < nums.length; i++){
            //要处理的扑克牌
            int poker = nums[i];

            /************ 二分查找：搜索左侧边界 ***************/
            int left = 0, right = piles;    //piles 表示堆数，即就是数组的长度；如果写成 right = piles + 1, 当 nums=[0] 时返回 0， 正确结果是 1
            while (left < right){
                int mid = left + (right - left) / 2;
                if(top[mid] == poker){
                    right = mid;
                }else if(top[mid] > poker){
                    right = mid;
                }else if(top[mid] < poker){
                    left = mid + 1;
                }
            }

            //牌比每一个都大，则新建一个堆
            if(left == piles)   piles++;
            //牌比每一个都小、或者找到合适位置，放入 top 数组即可
            top[left] = poker;
        }
        return piles;
    }
}
