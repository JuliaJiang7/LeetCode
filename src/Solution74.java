/**
 * @author julia.jiang
 * @date 2020/7/22 8:48
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution74 {

    /**
     * 解法一：二分法
     * 自己做的：看到了有序序列，啥都不用想直接二分
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // 行数
        int row = matrix.length;
        if(row == 0){
            return false;
        }
        // 列数
        int col = matrix[0].length;

        int num = row * col;
        int left = 0, right = num - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            // 求mid对应的矩阵位置
            int midRow = mid / col;
            int midCol = mid % col;
            if(matrix[midRow][midCol] == target){
                return true;
            }else if(matrix[midRow][midCol] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution74 solution74 = new Solution74();
        int[][] matrix = {{1,1}};
        solution74.searchMatrix(matrix, 2);
    }
}
