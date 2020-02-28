/**
 * @author Julia Jiang
 * @date 2020/2/26 14:21
 * @description 不同路径（动态规划）
 */
public class Solution62 {
    public int uniquePaths(int m, int n) {
        //dp[i][j] 表示：i * j 的网格上机器人的移动步数
        int[][] dp = new int[m + 1][n + 1];
        //base case
        for(int i = 1; i <= m; i++){
            dp[i][1] = 1;
        }
        for(int j = 1; j <= n; j++){

            dp[1][j] = 1;
        }

        for(int i = 2; i <= m; i++){
            for(int j = 2; j <= n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m][n];
    }
}
