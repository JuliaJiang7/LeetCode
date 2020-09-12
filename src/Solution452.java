import java.util.Arrays;
import java.util.Comparator;

/**
 * @author julia.jiang
 * @date 2020/9/12 11:19
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution452 {
    /**
     * https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tan-xin-suan-fa-zhi-qu-jian-tiao-du-wen-ti
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0){return 0;}
        // 按照 end 排序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        // 互不重叠的区间数量（射箭数量，选中第一个区间时，肯定会射一箭）
        int count = 1;
        int end = points[0][1];

        for(int i = 1; i < points.length; i++){
            // 注意：这里把首尾相接的视为相交，去掉等号
            if(points[i][0] > end){
                end = points[i][1];
                count++;
            }
        }
        return count;
    }
}
