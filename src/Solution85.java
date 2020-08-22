import java.util.Arrays;

/**
 * @author julia.jiang
 * @date 2020/8/20 10:08
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution85 {

    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0){return 0;}
        int row = matrix.length;
        int col = matrix[0].length;
        int maxArea = 0;
        // dp[i][j] = x 表示：以 matrix[i][j] 结尾的最大可能宽度
        int[][] dp = new int[row][col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(matrix[i][j] == '1'){
                    // 更新最大最大宽度
                    dp[i][j] = (j == 0) ? 1 : dp[i][j - 1] + 1;

                    int width = dp[i][j];
                    // 计算以 matrix[i][j] 为右下角的矩形最大面积
                    for(int k = i; k >= 0; k--){
                        width = Math.min(width, dp[k][j]);
                        maxArea = Math.max(maxArea, width * (i - k + 1));
                    }
                }
            }
        }
        return maxArea;
    }

    public int maximalRectangle2(char[][] matrix) {
        if(matrix.length == 0){return 0;}
        int row = matrix.length;
        int col = matrix[0].length;

        int[] left = new int[col];
        int[] right = new int[col];
        int[] height = new int[col];
        Arrays.fill(right, col);

        int maxArea = 0;
        // 遍历每一行
        for(int i = 0; i < row; i++){
            int cur_left = 0;
            int cur_right = col - 1;

            // 高度
            // 遍历这一行的每一个元素
            for(int j = 0; j < col; j++){
                if(matrix[i][j] == '1'){
                    // 在上一行的高度上加1
                    height[j]++;
                }else{
                    height[j] = 0;
                }
            }

            // 左边
            for(int j = 0; j < col; j++){
                if(matrix[i][j] == '1'){
                    left[j] = Math.max(left[j], cur_left);
                }else{
                    left[j] = 0;
                    cur_left = j + 1;
                }
            }

            // 右边
            for(int j = col - 1; j >= 0; j--){
                if(matrix[i][j] == '1'){
                    right[j] = Math.min(right[j], cur_right);
                }else{
                    right[j] = col - 1;
                    cur_right = j - 1;
                }
            }

            // 面积
            for(int j = 0; j < col; j++){
                maxArea = Math.max(maxArea, (right[j] - left[j] + 1) * height[j]);
            }
        }
        return maxArea;
    }

}
