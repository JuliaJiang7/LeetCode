import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Julia Jiang
 * @date 2020/2/22 13:21
 * @description N皇后（回溯算法）
 */
public class Solution51 {

    public static void main(String[] args) {
        Solution51 solution51 = new Solution51();
        List<List<String>> res = solution51.solveNQueens(4);
        for (List<String> list : res) {
            for (String str : list) {
                System.out.println(str);
                System.out.println();
            }
        }
    }

    List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) return null;
        res = new LinkedList<>();

        //初始化棋盘 board 为二维字符数组
        char[][] board = new char[n][n];
        for (char[] chars : board)
            Arrays.fill(chars, '.');//向数组 chars 中填充数据

        backtrack(board, 0);
        return res;
    }

    /**
     * 路径：board中小于row的那些行都已经成功放置了皇后
     * 可选择列表: 第row行的所有列都是放置Q的选择
     * 结束条件: row超过board的最后一行
     *
     * @param board
     * @param row
     */
    private void backtrack(char[][] board, int row) {
        //触发结束条件
        if (row == board.length) {
            res.add(charToString(board));
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
        // 检查左上斜线上是否有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < rows; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        // 检查右上斜线上是否有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    private static List<String> charToString(char[][] array) {
        List<String> result = new LinkedList<>();
        for (char[] chars : array) {
            result.add(String.valueOf(chars));
        }
        return result;
    }
}
