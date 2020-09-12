/**
 * @author julia.jiang
 * @date 2020/9/11 9:29
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution121 {

    /**
     * 解法一：自己写的，暴力破解
     * O(n^2)
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int size = prices.length;
        if(size == 0){return 0;}
        int[] dp = new int[size];
        dp[0] = 0;
        for(int i = 1; i < size; i++){
            for(int j = i - 1; j >= 0; j--){
                if(prices[j] < prices[i]){
                    dp[i] = dp[j] + (prices[i] - prices[j]);
                    break;
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < size; i++){
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 方法二：一次遍历
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/121-mai-mai-gu-piao-de-zui-jia-shi-ji-by-leetcode-/
     * 假如计划在第 i 天卖出股票，那么最大利润的差值一定是在[0, i-1] 之间选最低点买入；
     * 所以遍历数组，依次求每个卖出时机的的最大差值，再从中取最大值。
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        if(len == 0){return 0;}
        int min = prices[0];
        int res = 0;
        for(int i = 1; i < len; i++){
            if(prices[i] < min){
                min = prices[i];
            }
            if(prices[i] > min){
                res = Math.max(res, prices[i] - min);
            }
        }
        return res;
    }
}
