/**
 * @author Julia Jiang
 * @date 2020/4/8 12:11
 * @description 跳跃游戏（参考 45 跳跃游戏2）
 * https://leetcode-cn.com/problems/jump-game/
 */
public class Solution55 {

    /**
     * 解法一
     * 思路转化：判断能否到达最后一个位置 即就是 能走的最远索引位置是否大于数组长度-1
     * 注意与 45 区别; 45 题中给出的数组一定可以到达最后一个位置
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int maxPosition = 0;
        // 边界
        int end = 0;
        for(int i = 0; i < nums.length; i++){
            if(end < i){    // 遇到0，即 nums[i - 1] = 0
                return false;
            }
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if(end == i){
                end = maxPosition;
            }
        }
        return maxPosition >= nums.length;
    }

    /**
     * 解法二：
     * 直击问题本质，与 45 解法不同，我们并不需要知道跳的过程，只要数组中没有0，无论怎么跳都会到数组末尾
     * 因而我们考虑0的情况即可：即判断 0 前边的元素能否跳过 0，用一个变量保存当前跳的最远的距离
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        // 当前跳的最远距离
        int max = 0;
        for(int i = 0; i < nums.length - 1; i++){ // 注意：这里是 nums.length - 1，当 0 是末尾元素时，不用考虑能否跳过
            // 遇到 0 元素，判断能否跳过 0 元素
            if(nums[i] == 0 && i >= max){
                return false;
            }
            max = Math.max(max, nums[i] + i);
        }
        return true;
    }

    /**
     * 解法三：（解法二的优化）甚至不用考虑 0 的位置，只要判断前面元素的最大位置能否到达当前元素i
     * @param nums
     * @return
     */
    public boolean canJump3(int[] nums) {
        int max = 0;
        for(int i = 0; i < nums.length; i++){       // 注意：这里是nums.length，因为要判断是否最终到达末尾元素
            // 一旦前面元素的最大位置不能到达元素i，直接返回false
            if(i > max){
                return false;
            }
            max = Math.max(max, nums[i] + i);
        }
        return true;
    }
}
