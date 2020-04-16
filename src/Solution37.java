/**
 * @author Julia Jiang
 * @date 2020/3/24 10:47
 * @description 解数独
 */
public class Solution37 {
    /**
     * 解法一：回溯
     * @param board
     */
    public void solveSudoku(char[][] board) {
        solver(board);
    }

    private boolean solver(char[][] board){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.'){
                    char count = '1';
                    while (count <= '9'){
                        if(isValid(i, j, board, count)){
                            board[i][j] = count;
                            if(solver(board)){
                                return true;
                            }else{
                                //下一个位置没有数字，就还原，然后当前位置尝试新的数
                                board[i][j] = '.';
                            }
                        }
                        count++;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int row, int col, char[][] board, char c){
        for(int i = 0; i < 9; i++){
            if(board[row][i] == c){
                return false;
            }
            if(board[i][col] == c){
                return false;
            }
        }

        int start_row = row / 3 * 3;
        int start_col = col / 3 * 3;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[start_row + i][start_col + j] == c)
                    return false;
            }
        }

        return true;
    }
}
