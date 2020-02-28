/**
 * @author Julia Jiang
 * @date 2020/2/27 11:24
 * @description
 */
public class Solution63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid){
        int row = obstacleGrid.length;
        int column = obstacleGrid[0].length;
        //dp[i][j] = n 表示：从start 到 obstacleGrid[i][j] 的路径个数
        int[][] dp = new int[row][column];

        //base case：第一行和第一列
        if(obstacleGrid[0][0] == 1){
            return 0;   //注意这里可以直接返回
        }else{
            dp[0][0] = 1;
        }

        for(int i = 1; i < row; i++){
            if(obstacleGrid[i][0] == 1){
                dp[i][0] = 0;
            }else{
                dp[i][0] = dp[i - 1][0];    //注意与上一题区别
            }
        }
        for(int j = 1; j < column; j++){
            if(obstacleGrid[0][j] == 1){
                dp[0][j] = 0;
            }else{
                dp[0][j] = dp[0][j - 1];
            }
        }

        for(int i = 1; i < row; i++){
            for(int j = 1; j < column; j++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[row - 1][column - 1];
    }

    public static void main(String[] args) {
        int[][] nums = {{1,0}};
        Solution63 solution63 = new Solution63();
        System.out.println(solution63.uniquePathsWithObstacles(nums));
    }
}
