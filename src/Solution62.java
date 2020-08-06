/**
 * @author Julia Jiang
 * @date 2020/2/26 14:21
 * @description 不同路径（动态规划）
 */
public class Solution62 {
    public int uniquePaths(int m, int n) {
        //dp[i-1][j-1] 表示：i * j 的网格上机器人的移动步数
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        for(int i = 1; i < n; i++){
            dp[0][i] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
