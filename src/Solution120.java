import java.util.List;

/**
 * @author julia.jiang
 * @date 2020/9/10 14:45
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[][] dp = new int[size][size];
        // dp[i][j] = x 表示：triangle[i][j] 的最小路径和为 x
        dp[0][0] = triangle.get(0).get(0);

        // 遍历每一行
        for(int i = 1; i < size; i++){
            // 遍历这一行的每一个数
            for(int j = 0; j <= i; j++){
                // 这一行的最后一个数
                if(j == i){
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                }else if(j == 0){
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                }else{
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                }
            }

        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < size; i++){
            min = Math.min(min, dp[size-1][i]);
        }
        return min;
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[] dp = new int[size];
        dp[0]=triangle.get(0).get(0);

        for(int i = 1; i < size; i++){
            for(int j = i; j >= 0; j--){
                if(j == i){
                    dp[j] = dp[j - 1] + triangle.get(i).get(j);
                }else if(j == 0){
                    dp[j] = dp[j] + triangle.get(i).get(j);
                }else{
                    dp[j] = Math.min(dp[j - 1], dp[j]) + triangle.get(i).get(j);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < size; i++){
            min = Math.min(min, dp[i]);
        }

        return min;
    }
}
