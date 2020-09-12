import java.util.Arrays;
import java.util.Comparator;

/**
 * @author julia.jiang
 * @date 2020/9/12 10:23
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution435 {
    /**
     * 解法一：从起点的贪心算法
     * https://leetcode-cn.com/problems/non-overlapping-intervals/solution/wu-zhong-die-qu-jian-by-leetcode/
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0){return 0;}
        // 按照 start 排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        // prev 指针追踪刚刚添加到最终列表中的区间
        int prev = 0;
        // 需要移除的数量
        int count = 0;
        for(int i = 1; i < intervals.length; i++){
            if(intervals[prev][1] > intervals[i][0]){
                // case 2
                if(intervals[prev][1] > intervals[i][1]){
                    prev = i;
                }
                // case 3
                count++;
            }else{
            // case 1
                prev = i;
            }
        }
        return count;
    }

    public int eraseOverlapIntervals2(int[][] intervals) {
        if(intervals.length == 0){return 0;}
        // 按照 end 排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        // 互补重叠的区间数量（能留下的区间数量），初值为 1
        int count = 1;
        int end = intervals[0][1];
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] >= end){
                end = intervals[i][1];
                count++;
            }
        }
        return intervals.length - count;
    }
}
