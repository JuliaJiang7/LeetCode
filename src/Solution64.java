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
        int row = grid.length;
        int col = grid[0].length;

        // dp[i][j] = x 表示：到grid[i][j]的最小路径和为 x
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        for(int j = 1; j < col; j++){
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for(int i = 1; i < row; i++){
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for(int i = 1; i < row; i++){
            for(int j = 1; j < col; j++){
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[row - 1][col - 1];
    }
}
