/**
 * @author julia.jiang
 * @date 2020/4/17 9:49
 * @email julia.jiang.fan@gmail.com
 * @description 最小路径和
 */
public class Solution64 {

    /**
     * 自己的解法：典型的动态规划
     * 每次只能向下或者向右，表示最短路径只能是取到左边和上边节点的路径最小值 + 当前节点值
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int x_len = grid[0].length;
        int y_len = grid.length;

        // dp[i][j] = x 表示：到grid[i][j]的最小路径和
        int dp[][] = new int[y_len][x_len];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < x_len; i++){
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }

        for(int i = 1; i < y_len; i++){
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }

        for(int i = 1; i < x_len; i++){
            for(int j = 1; j < y_len; j++){
                dp[j][i] = Math.min(dp[j - 1][i], dp[j][i - 1]) + grid[j][i];
            }
        }
        return dp[y_len - 1][x_len - 1];
    }
}
