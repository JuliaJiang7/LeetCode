/**
 * @author julia.jiang
 * @date 2020/7/21 9:08
 * @email julia.jiang.fan@gmail.com
 * @description 矩阵置零
 */
public class Solution73 {

    /**
     * 解法一：暴力解法
     * 空间复杂度 O(mn)
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] matrix_copy = new int[row][col];

        // 复制矩阵
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                matrix_copy[i][j] = matrix[i][j];
            }
        }

        // 找0的位置
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(matrix_copy[i][j] == 0){
                    // 当前列全部置为0
                    for(int k = 0; k < row; k++){
                        matrix[k][j] = 0;
                    }
                    // 当前行全部置为0
                    for(int k = 0; k < col; k++){
                        matrix[i][k] = 0;
                    }
                }
            }
        }
    }

    /**
     * 解法二：用两个数组标记当前行和列是否需要置为0
     * 空间复杂度优化为 O(m+n)
     * 参考：https://leetcode.wang/leetcode-73-Set-Matrix-Zeroes.html#%E8%A7%A3%E6%B3%95%E4%BA%8C
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        // 用两个数组标记当前行和列是否需要置为0
        boolean[] row_zero = new boolean[row];
        boolean[] col_zero = new boolean[col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(matrix[i][j] == 0){
                    row_zero[i] = true;
                    col_zero[j] = true;
                }
            }
        }

        for(int i = 0; i < row; i++){
            if(row_zero[i]){
                setRowZeroes(matrix, i);
            }
        }

        for (int j = 0; j < col; j++){
            if(col_zero[j]){
                setColZeroes(matrix, j);
            }
        }
    }

    /**
     * 解法二的优化：解法二中的置0，直接用两个函数对行和列置零，但很明显会使一些元素重复置零
     * 这里将行和列一起比较，保证不会重复置零
     * @param matrix
     */
    public void setZeroes3(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        // 用两个数组标记当前行和列是否需要置为0
        boolean[] row_zero = new boolean[row];
        boolean[] col_zero = new boolean[col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(matrix[i][j] == 0){
                    row_zero[i] = true;
                    col_zero[j] = true;
                }
            }
        }

        // 行和列一起比较，不存在重复置零的问题
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(row_zero[i] || col_zero[j]){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 第 col 列全部置为 0
     * @param matrix
     * @param col
     */
    private void setColZeroes(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][col] = 0;
        }
    }

    /**
     * 第 rol 行全部置为 0
     * @param matrix
     * @param row
     */
    private void setRowZeroes(int[][] matrix, int row) {
        for (int i = 0; i < matrix[row].length; i++) {
            matrix[row][i] = 0;
        }
    }

    /**
     * 解法三：假设一个数，矩阵中一定不存在。为了不影响后面行和列为0 的判断，先把需要变成0的位置标记为这个数，最后再统一把这个数变成0
     * 空间复杂度：O(1)
     * 存在问题：局限性强，依赖于不存在矩阵中数字的取值
     * @param matrix
     */
    public void setZeroes4(int[][] matrix) {
        // 假设这个数字不存在于矩阵中
        int MODIFIED = -1000000;
        int row = matrix.length;
        int col = matrix[0].length;

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                // 找到等于0的位置
                if(matrix[i][j] == 0){
                    // 将需要置零的行和列改为之前定义的MODIFIED
                    // 如果是0不要管，因为我们要找0的位置
                    for(int k = 0; k < col; k++){
                        if(matrix[i][k] != 0){
                            matrix[i][k] = MODIFIED;
                        }
                    }
                    for(int k = 0; k < row; k++){
                        if(matrix[k][j] != 0){
                            matrix[k][j] = MODIFIED;
                        }
                    }
                }
            }
        }

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                // 将MODIFIED变为0
                if(matrix[i][j] == MODIFIED){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 解法四：解决解法三中存在问题
     * 找出第一个出现0的位置，将该行和列作为标志
     * 参考：https://leetcode.wang/leetcode-73-Set-Matrix-Zeroes.html#%E8%A7%A3%E6%B3%95%E4%B8%89
     * @param matrix
     */
    public void setZeroes5(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        // 记录第一个出现的行和列
        int free_row = -1;
        int free_col = -1;
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 如果当前列作为标记列，跳过
                if(j == free_col){
                    continue;
                }
                if(matrix[i][j] == 0){
                    // 判断是否是第一个0
                    if(free_row == -1){
                        free_row = i;
                        free_col = j;

                        // 为了不影响其他的行列置零，如果该列中元素为0，置为1；其他置为0（置为1表示：矩阵该列应全部为0）
                        for (int k = 0; k < row; k++){
                            if(matrix[k][free_col] == 0){
                                matrix[k][free_col] = 1;
                            }else{
                                matrix[k][free_col] = 0;
                            }
                        }
                        // 为了不影响其他的行列置零，如果该行中元素为0，置为1；其他置为0（置为1表示：矩阵该行应全部为0）
                        for(int k = 0; k < col; k++){
                            if(matrix[free_row][k] == 0){
                                matrix[free_row][k] = 1;
                            }else{
                                matrix[free_row][k] = 0;
                            }
                        }
                        // 如果当前行、列出现第一个0，判断完该行该列，就结束第二层for循环，继续判断下一行
                        break;
                    }else{
                        // 如果不是第一个0, 将标志行和列对应元素置为1，表示矩阵该行、列应全部为0
                        matrix[i][free_col] = 1;
                        matrix[free_row][j] = 1;
                    }
                }
            }
        }

        // 必须把行和列分开
        if(free_row != -1){
            for(int i = 0; i < row; i++){
                if(matrix[i][free_col] == 1){
                    setRowZeroes(matrix, i);
                }
            }
            for (int j = 0; j < col; j++){
                if(matrix[free_row][j] == 1){
                    setColZeroes(matrix, j);
                }
            }
        }
    }



    public static void main(String[] args) {
        int[][] matrix = {{0,1,2,0}, {3,4,5,2}, {1,3,1,5}};
        Solution73 solution73 = new Solution73();
        solution73.setZeroes5(matrix);
    }
}
