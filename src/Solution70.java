import java.util.HashMap;
import java.util.Map;

/**
 * @author julia.jiang
 * @date 2020/7/17 9:19
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution70 {

    /**
     * 解法一：递归（斐波那契数列），超时
     * 参考：https://leetcode.wang/leetCode-70-Climbing-Stairs.html#%E8%A7%A3%E6%B3%95%E4%B8%80-%E6%9A%B4%E5%8A%9B%E8%A7%A3%E6%B3%95
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * 解法二：递归的优化，使用memorization记录每一次计算值，不需要重复计算
     * 参考：https://leetcode.wang/leetCode-70-Climbing-Stairs.html#%E8%A7%A3%E6%B3%95%E4%BA%8C-%E6%9A%B4%E5%8A%9B%E8%A7%A3%E6%B3%95%E4%BC%98%E5%8C%96
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        int memo[] = new int[n + 1];
        return climbStairsN(n, memo);
    }

    private int climbStairsN(int n, int[] memo) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int n1 = 0;
        //数组的默认值是 0
        if (memo[n - 1] == 0) {
            n1 = climbStairsN(n - 1, memo);
            memo[n - 1] = n1;
        } else {
            n1 = memo[n - 1];
        }
        int n2 = 0;
        if (memo[n - 2] == 0) {
            n2 = climbStairsN(n - 2, memo);
            memo[n - 2] = n2;

        } else {
            n2 = memo[n - 2];
        }
        return n1 + n2;
    }

    /**
     * 解法三：动态规划
     * 参考：https://leetcode.wang/leetCode-70-Climbing-Stairs.html#%E8%A7%A3%E6%B3%95%E4%B8%89-%E8%BF%AD%E4%BB%A3
     * @param n
     * @return
     */
    public int climbStairs3(int n) {
        int[] nums = new int[n + 1];
        if(n == 1){
            return 1;
        }else if(n == 2){
            return 2;
        }else{
            nums[1] = 1;
            nums[2] = 2;
            for (int i = 3; i <= n; i++){
                nums[i] = nums[i - 1] + nums[i - 2];
            }
            return nums[n];
        }
    }

    /**
     * 解法四：菲波那切数列公式法
     * 参考：https://leetcode.wang/leetCode-70-Climbing-Stairs.html#%E8%A7%A3%E6%B3%95%E4%BA%94-%E5%85%AC%E5%BC%8F%E6%B3%95
     * @param n
     * @return
     */
    public int climbStairs4(int n) {
        double sqrt5 = Math.sqrt(5);
        double fib = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int)(fib / sqrt5);
    }
}
