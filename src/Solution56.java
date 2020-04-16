import java.util.*;

/**
 * @author Julia Jiang
 * @date 2020/4/9 11:30
 * @description 合并区间
 */
public class Solution56 {

    /**
     * 解法一：排序后合并
     * 参考 https://leetcode-cn.com/problems/merge-intervals/solution/he-bing-qu-jian-by-leetcode/ 的评论第一
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0){
            return intervals;
        }

        // 按照区间的起始值升序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 结果个数是不断变化的，所以不能直接定义为二维数组
        LinkedList<int[]> list = new LinkedList<>();
        // 第一个区间直接插入结果
        list.addLast(intervals[0]);
        // 依次考虑后面的每个区间
        for(int i = 0; i < intervals.length; i++){
            int[] curr = intervals[i];
            // 如果当前区间的左端点小于等于上一个区间的右端点，存在重合
            if(curr[0] <= list.getLast()[1]){
                int max = Math.max(curr[1], list.getLast()[1]);
                int start = list.getLast()[0];
                list.removeLast();
                list.addLast(new int[]{start, max});
            }else{
            // 不重合，直接放入结果
                list.addLast(curr);
            }
        }
        return list.toArray(new int[0][2]);     // 注意这里的转换
    }
}
