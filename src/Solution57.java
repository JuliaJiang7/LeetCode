import java.util.LinkedList;

/**
 * @author Julia Jiang
 * @date 2020/4/10 12:32
 * @description 插入区间
 */
public class Solution57 {


    /**
     * 解法一：参考56题解法（自己写的，效率较低）
     * 参考思路 https://leetcode-cn.com/problems/insert-interval/solution/cha-ru-qu-jian-by-leetcode/
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 存储结果
        LinkedList<int[]> res = new LinkedList<>();

        // 新区间之前的全部添加到结果中
        int i;
        for(i = 0; i < intervals.length && intervals[i][0] <= newInterval[0]; i++){
            res.addLast(intervals[i]);
        }

        // 如果intervals都大于新区间或者新区间不重合，先把新区间加进去
        if(i == 0 || newInterval[0] > res.getLast()[1]){
            res.add(newInterval);
        }else{
            int start = res.getLast()[0];
            int end = Math.max(res.getLast()[1], newInterval[1]);
            res.removeLast();
            res.addLast(new int[]{start, end});
        }


        // 剩余intervals添加到结果集
        for(;i < intervals.length; i++){
            int[] curr = intervals[i];
            // 如果当前区间的左端点小于等于上一个区间的右端点，存在重合
            if(curr[0] <= res.getLast()[1]){
                int max = Math.max(curr[1], res.getLast()[1]);
                int start = res.getLast()[0];
                res.removeLast();
                res.addLast(new int[]{start, max});
            }else{
                // 不重合，直接放入结果
                res.addLast(curr);
            }
        }

        return res.toArray(new int[0][2]);
    }
}
