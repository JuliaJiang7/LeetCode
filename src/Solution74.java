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
        if(matrix.length == 0 || matrix[0].length == 0){return false;}
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = m * n - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            int i = mid / n;
            int j = mid % n;
            int value = matrix[i][j];
            if(target == value){
                return true;
            }else if(target < value){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return false;
    }

}
