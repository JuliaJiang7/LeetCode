/**
 * @author julia.jiang
 * @date 2020/8/13 9:04
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution79 {

    public boolean exist(char[][] board, String word) {
        if(board.length == 0){return false;}
        int row = board.length;
        int col = board[0].length;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                // 找到第一个字母
                if(board[i][j] == word.charAt(0)){
                    boolean[][] visited = new boolean[row][col];
                    visited[i][j] = true;
                    // 判断从 board[i][j] 出发，后面能否全部匹配
                    if(isValid(i, j, word, 0, board, visited)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 当 board[i][j] 匹配 word[k] 时，后面是否可以全部匹配
     * @param i
     * @param j
     * @param word
     * @param k
     * @param board
     * @param visited 当前已访问的字符
     * @return
     */
    private boolean isValid(int i, int j, String word, int k, char[][] board, boolean[][] visited){
        int row = board.length;
        int col = board[0].length;
        if(k == word.length() - 1){return true;}
        // 上面
        boolean left = false;
        if(i - 1 >= 0 && !visited[i - 1][j] && board[i - 1][j] == word.charAt(k + 1)){
            visited[i - 1][j] = true;
            left = isValid(i - 1, j, word, k + 1, board, visited);
        }
        if(left){return left;}
        // 下面
        boolean right = false;
        if(i + 1 < row && !visited[i + 1][j] && board[i + 1][j] == word.charAt(k + 1)){
            visited[i + 1][j] = true;
            right = isValid(i + 1, j, word, k + 1, board, visited);
        }
        if(right){return right;}
        // 左边
        boolean up = false;
        if(j - 1 >= 0 && !visited[i][j - 1] && board[i][j - 1] == word.charAt(k + 1)){
            visited[i][j - 1] = true;
            up = isValid(i, j - 1, word, k + 1, board, visited);
        }
        if(up){return up;}
        // 右边
        boolean down = false;
        if(j + 1 < col && !visited[i][j + 1] && board[i][j + 1] == word.charAt(k + 1)){
            visited[i][j + 1] = true;
            down = isValid(i, j + 1, word, k + 1, board, visited);
        }
        if(down){return true;}
        // 如果上下左右都没有匹配，则取消这一步，回溯
        visited[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        Solution79 solution79 = new Solution79();
        System.out.println( solution79.exist(board, "ABCESEEEFS"));

    }
}
