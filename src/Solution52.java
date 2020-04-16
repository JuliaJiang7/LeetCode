import java.util.Arrays;

/**
 * @author Julia Jiang
 * @date 2020/4/3 12:04
 * @description N 皇后2（回溯算法）
 */
public class Solution52 {

    int count = 0;

    /**
     * 解法一：51 题思路
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        if (n <= 0) return 0;

        //初始化棋盘 board 为二维字符数组
        char[][] board = new char[n][n];
        for (char[] chars : board)
            Arrays.fill(chars, '.');//向数组 chars 中填充数据

        backtrack(board, 0);
        return count;
    }

    private void backtrack(char[][] board, int row) {
        //触发结束条件
        if (row == board.length) {
            count++;
            return;
        }

        int n = board[row].length;
        for (int col = 0; col < n; col++) {
            //排除不合法选择
            if (!isValid(board, row, col))
                continue;
            //做选择
            board[row][col] = 'Q';
            //进入下一行决策树
            backtrack(board, row + 1);
            //撤销选择
            board[row][col] = '.';
        }
    }

    /**
     * 是否可以在 board[row][col] 放置皇后
     * 不需要检查 这一行，因为每一行只放一个皇后就到下一行
     * 不需要检查 左下、右下斜线，因为下面还没放元素
     * @param board
     * @param row
     * @param col
     * @return
     */
    private boolean isValid(char[][] board, int row, int col) {
        int rows = board.length;
        //检查这一列是否有皇后
        for (char[] chars : board) if (chars[col] == 'Q') return false;
        // 检查右上斜线上是否有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < rows; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        // 检查左上斜线上是否有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    /**
     * 解法二
     * @param n
     * @return
     * https://leetcode.wang/leetCode-52-N-QueensII.html
     */
    int count2 = 0;
    public int totalNQueens2(int n) {
        boolean[] cols = new boolean[n];        // 列
        boolean[] d1 = new boolean[2 * n];      // 主对角线
        boolean[] d2 = new boolean[2 * n];      // 副对角线
        return  backtrace2(0, cols, d1, d2, n);
    }

    private int backtrace2(int row, boolean[] cols, boolean[] d1, boolean[] d2, int n){
        if(row == n){
            count2++;
        }else {
            for(int col = 0; col < n; col++){
                int id1 = row - col + n;
                int id2 = row + col;
                // 这一列、主对角线、斜对角线上面有任何一个存放元素
                if(d1[id1] || d2[id2] || cols[col]){
                    continue;
                }
                cols[col] = true;
                d1[id1] = true;
                d2[id2] = true;
                backtrace2(row + 1, cols, d1, d2, n);
                cols[col] = false;
                d1[id1] = false;
                d2[id2] = false;
            }
        }
        return count2;
    }

    public static void main(String[] args) {
        Solution52 solution52 = new Solution52();
        System.out.println(solution52.totalNQueens2(4));
    }
}
