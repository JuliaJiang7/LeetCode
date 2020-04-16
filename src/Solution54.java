import java.util.ArrayList;
import java.util.List;

/**
 * @author Julia Jiang
 * @date 2020/4/7 12:24
 * @description 螺旋矩阵
 * https://leetcode.wang/leetCode-54-Spiral-Matrix.html
 */
public class Solution54 {

    /**
     * 解法一：模拟思路
     * direction = 0 表示向右，1 表示向下，2 表示向左，3 表示向上
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if(matrix.length == 0)  return ans;

        // 矩阵中的索引, x 表示横坐标（列），y 表示纵坐标（行）
        int x = 0, y = 0;
        // 方向
        int direction = 0;
        // 边界
        int top_border = -1, right_border = matrix[0].length, bottom_border = matrix.length, left_border = -1;
        while (true){
            // 全部遍历完
            if(ans.size() == matrix.length * matrix[0].length){
                return ans;
            }
            // 注意 y 在 x 前面
            ans.add(matrix[y][x]);
            switch (direction){
                // 当前向右
                case 0:
                    // 继续向右是否到达边界，如果到边界，改为向下，更新上边界
                    if(x + 1 == right_border){
                        direction = 1;
                        top_border++;
                        y++;
                    }else{
                        x++;
                    }
                    break;
                // 当前向下
                case 1:
                    // 继续向下是否到达边界，如果到边界，改为向左，更新右边界
                    if(y + 1 == bottom_border){
                        direction = 2;
                        right_border--;
                        x--;
                    }else{
                        y++;
                    }
                    break;
                // 当前向左
                case 2:
                    // 继续向左是否到达左边界，如果到左边界，改为向上，更新下边界
                    if(x - 1 == left_border){
                        direction = 3;
                        bottom_border--;
                        y--;
                    }else{
                        x--;
                    }
                    break;
                // 当前向上
                case 3:
                    // 继续向上是否到达上边界，如果到达上边界，改为向右，更新左边界
                    if(y - 1 == top_border){
                        direction = 0;
                        left_border++;
                        x++;
                    }else{
                        y--;
                    }
                    break;
            }
        }
    }
}
