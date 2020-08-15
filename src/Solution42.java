import java.util.Stack;

/**
 * @author Julia Jiang
 * @date 2020/3/2 12:43
 * @description 接雨水
 */
public class Solution42 {
    /**
     * 暴力解法
     * 参考：https://leetcode.wang/leetCode-42-Trapping-Rain-Water.html
     * @param height
     * @return
     */
    public int trap(int[] height){
        int sum = 0;

        //最两端的不用考虑，因为一定不会有雨水
        for(int i = 1; i < height.length - 1; i++){
            int max_left = 0;
            //找出 height[i] 左边最高（包括height[i]），要把最左端考虑在内
            for(int j = i; j >= 0; j--){
                max_left = Math.max(height[j], max_left);
            }

            int max_right = 0;
            //找出 height[i] 右边最高（包括height[i]），要把最右端考虑在内
            for(int j = i; j < height.length;j++){
                max_right = Math.max(height[j], max_right);
            }

            //如果自己就是最高的话 max_left = max_right = height[i]
            sum += Math.min(max_left, max_right) - height[i];
        }
        return sum;
    }

    /**
     * 动态规划解法：动态规划求解左右两端最大高度
     * @param height
     * @return
     */
    public int trap2(int[] height){
        if(height.length == 0)
            return 0;
        int len = height.length;

        //dp_left[i] = 1 表示：height[i] 的左端（包括height[i]）最大高度为1
        int[] dp_left = new int[len];
        //dp_right[i] = 1 表示：height[i] 的右端（包括height[i]）最大高度为 1
        int[] dp_right = new int[len];

        //base case：
        dp_left[0] = height[0];
        dp_right[len-1] = height[len - 1];

        for(int i = 1; i < len; i++){
            dp_left[i] = Math.max(dp_left[i - 1], height[i]);
        }
        for(int j = len - 2; j >= 0; j--){
            dp_right[j] = Math.max(dp_right[j + 1], height[j]);
        }

        int sum = 0;
        for(int i = 1; i < len - 1; i++){
            sum += Math.min(dp_left[i], dp_right[i]) - height[i];
        }
        return sum;
    }

    /**
     * 动态规划优化：双指针（好难）
     * @param height
     * @return
     */
    public int trap3(int[] height){
        if(height.length == 0)  return 0;

        int len = height.length;
        //两端滑动指针
        int left = 0, right = len - 1;
        int sum = 0;
        //表示 [0,left] 的最大值
        int max_left = height[0];
        //表示 [right, end] 的最大值
        int max_right = height[len - 1];

        while (left <= right){
            max_left = Math.max(max_left, height[left]);
            max_right = Math.max(max_right, height[right]);

            if(max_left < max_right){
                sum += max_left - height[left];
                left++;
            }else{
                sum += max_right - height[right];
                right--;
            }
        }
        return sum;
    }
}
