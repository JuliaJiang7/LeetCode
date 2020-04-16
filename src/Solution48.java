/**
 * @author Julia Jiang
 * @date 2020/3/31 12:16
 * @description 旋转图像
 */
public class Solution48 {

    /**
     * 解法一:找规律（想不到）
     * @param matrix
     * https://leetcode.wang/leetCode-48-Rotate-Image.html
     */
    public void rotate(int[][] matrix) {
        //以主对角交换元素
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j <= i; j++){
                if(i == j)  continue;
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //以最中间列为轴交换元素
        for(int i = 0, j = matrix.length - 1; i < matrix.length / 2; i++, j--){
            for(int k = 0; k < matrix.length; k++){
                int temp = matrix[k][i];
                matrix[k][i] = matrix[k][j];
                matrix[k][j] = temp;
            }
        }
    }
}
