/**
 * @author julia.jiang
 * @date 2020/8/19 9:41
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution84 {
    /**
     * 解法一：暴力解法(自己写的)
     * 时复o(n^2) 空复O(1)
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        int len = heights.length;
        for(int i = 0; i < len; i++){
            int cur = heights[i];
            int count = 0;
            // 左边
            // 一旦遇到 heights[j]<cur 的情况，就跳出循环
            for(int j = i; j >= 0 && heights[j] >= cur; j--){
                count++;
            }
            // 右边
            for(int k = i + 1; k < len && heights[k] >= cur; k++){
                count++;
            }
            max = Math.max(max, (cur * count));
        }
        return max;
    }

    /**
     * 解法二：想不到，很优雅
     * 参考：https://leetcode.wang/leetCode-84-Largest-Rectangle-in-Histogram.html（解法四）
     * 时复o(n) 空复O(n)
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        int len = heights.length;
        if(len == 0){return 0;}

        // 求每个柱子左边第一个小的柱子下标
        int[] leftLessMin = new int[len];
        leftLessMin[0] = -1;
        for(int i = 1; i < len; i++){
            int l = i - 1;
            // 当前柱子更小，进行左移
            while (l >= 0 && heights[l] >= heights[i]){
                l = leftLessMin[l];
            }
            leftLessMin[i] = l;
        }

        // 求每个柱子右边第一个小的柱子下标
        int rightLessMin[] = new int[len];
        rightLessMin[len - 1] = len;
        for(int i = len - 2; i >= 0; i--){
            int r = i + 1;
            while (r <= len - 1 && heights[r] >= heights[i]){
                r = leftLessMin[r];
            }
            rightLessMin[i] = r;
        }

        // 求最大面积
        int max = 0;
        for(int i = 0; i < len; i++){
            max = Math.max(max, (rightLessMin[i] - leftLessMin[i] - 1) * heights[i]);
        }
        return max;
    }
}
