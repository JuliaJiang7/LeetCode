import java.util.Arrays;

/**
 * @author Julia Jiang
 * @date 2020/2/23 12:36
 * @description 零钱兑换（动态规划）
 */
public class Solution322 {
    public int coinChange(int[] coins, int amount){
        // dp[i] = n 表示：当目标金额为 i 时，至少需要 n 枚硬币
        int[] dp = new int[amount+1];
        // 初始化dp数组，初始值为 amount+1, 因为dp 数组最大值取到 amount
        Arrays.fill(dp, amount+1);

        //base case
        dp[0] = 0;

        for(int j = 1; j <= amount; j++){
            // 内层for求所有子问题的最小值
            for (int coin : coins) {
                // 子问题无解，跳过
                if (j - coin < 0) {
                    continue;
                }
                dp[j] = dp[j] < dp[j - coin] + 1 ? dp[j] : dp[j - coin] + 1;
            }
        }
        return dp[amount] == amount+1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int amount = 11;
        Solution322 solution322 = new Solution322();
        System.out.println(solution322.coinChange(coins, amount));
    }

}
