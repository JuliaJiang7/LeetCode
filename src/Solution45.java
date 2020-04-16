/**
 * @author Julia Jiang
 * @date 2020/3/30 12:09
 * @description 跳跃游戏 II（贪心算法）
 * https://leetcode.wang/leetCode-45-Jump-Game-II.html
 */
public class Solution45 {
    /**
     * 解法一：贪心算法（每一次取得局部最优，最终达到全局最优）
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int maxPosition = 0;
        int steps = 0;
        int end = 0;
        for(int i = 0; i < nums.length - 1; i++){
            // 找到跳的最远的
            maxPosition = Math.max(maxPosition, nums[i] + i);
            // 遇到边界，更新边界，并且步数加一
            if(end == i){
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}
