/**
 * @author Julia Jiang
 * @date 2020/4/14 9:43
 * @description 螺旋矩阵 II
 */
public class Solution59 {

    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];


        // 矩阵中的索引, x 表示横坐标（列），y 表示纵坐标（行）
        int x = 0, y = 0;
        // 方向
        int direction = 0;
        // 边界
        int top_border = -1, right_border = n, bottom_border = n, left_border = -1;
        int count = 1;
        while (true){
            if(count == n * n + 1){
                break;
            }
            // 注意 y 在 x 前面
            ans[y][x] = count++;
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
        return ans;
    }
}
