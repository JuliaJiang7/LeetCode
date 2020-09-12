/**
 * @author julia.jiang
 * @date 2020/9/11 10:36
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution122 {

    /**
     * 贪心算法
     * 想不到...
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/tan-xin-suan-fa-by-liweiwei1419-2/
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        int len = prices.length;
        for(int i = 0; i < len - 1; i++){
            int diff = prices[i + 1] - prices[i];
            if(diff > 0){
                res += diff;
            }
        }
        return res;
    }
}
