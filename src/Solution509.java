/**
 * @author julia.jiang
 * @date 2020/7/28 9:53
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution509 {

    /**
     * 解法一：暴力递归
     * @param N
     * @return
     */
    public int fib(int N) {
        if(N == 0){
            return 0;
        }
        if(N == 1){
            return 1;
        }
        return fib(N - 1) + fib(N - 2);
    }


    /**
     * 解法二：带备忘录的递归
     * @param N
     * @return
     */
    public int fib2(int N) {
        int[] memo = new int[N + 1];
        if(N == 0){
            return 0;
        }
        // base case
        memo[1] = 1;
        return helper(memo, N);
    }

    private int helper(int[] memo, int n){
        // 递归结束条件
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        // 如果已经计算过
        if(memo[n] != 0){
            return memo[n];
        }
        memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
        return memo[n];
    }

    /**
     * 解法三：动态规划
     * @param N
     * @return
     */
    public int fib3(int N) {
        int[] dp = new int[N + 1];
        if(N == 0){
            return 0;
        }
        if(N == 1){
            return 1;
        }
        // base case
        dp[1] = 1;
        for(int i = 2; i <= N; i++){
            // 状态转移方程
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N];
    }

    /**
     * 解法三的优化：状态压缩
     * @param N
     * @return
     */
    public int fib4(int N) {
        if(N == 0){
            return 0;
        }
        if(N == 1){
            return 1;
        }
        int pre = 0, curr = 1;
        for(int i = 2; i <= N; i++){
            int sum = pre + curr;
            pre = curr;
            curr = sum;
        }
        return curr;
    }

    public static void main(String[] args) {
        Solution509 solution509 = new Solution509();
        solution509.fib2(0);
    }
}
