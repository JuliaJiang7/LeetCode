import java.util.HashMap;

/**
 * @author Julia Jiang
 * @date 2020/2/20 15:45
 * @description
 */
public class Solution36 {
    public boolean isValidSudoku(char[][] board){
        //初始化数据
        HashMap<Integer, Integer> [] rows = new HashMap[9];
        HashMap<Integer, Integer> [] columns = new HashMap[9];
        HashMap<Integer, Integer> [] boxes = new HashMap[9];
        //注意
        for(int i = 0; i < 9; i++){
            rows[i] = new HashMap<>();
            columns[i] = new HashMap<>();
            boxes[i] = new HashMap<>();
        }

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                char num = board[i][j];
                if(num != '.'){
                    int n = (int)num;
                    int box_index = (i / 3) * 3 + j / 3;

                    //getOrDefault(key, defaultValue): 获取map中键为key的值；如果没有符合的key值，返回defaultValue
                    rows[i].put(n,rows[i].getOrDefault(n, 0)+1);
                    columns[j].put(n, columns[j].getOrDefault(n, 0)+1);
                    boxes[box_index].put(n, boxes[box_index].getOrDefault(n,0)+1);

                    if(rows[i].get(n)>1 || columns[j].get(n)>1 || boxes[box_index].get(n)>1){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
