import java.util.HashMap;

/**
 * @author Julia Jiang
 * @date 2020/3/14 10:54
 * @description 不同的二叉搜索树
 */
public class Solution96 {
    /**
     * 解法一：递归（思路同95）
     * @param n
     * @return
     */
    public int numTrees(int n){
        if(n == 0)  return 0;
        return getAns(1, n);
    }

    private int getAns(int start, int end){
        int ans = 0;
        if(start >= end)    return 1;
        for(int i = start; i <= end; i++){
            int numLeft = getAns(start, i - 1);
            int numRight = getAns(i + 1, end);
            ans += numLeft * numRight;
        }
        return ans;
    }

    /**
     * （解法一的优化）把参数 start 和 end 变为 n, 因为这道题只关心数量，不关心具体的范围
     * 即就是，[1,2,3] 和 [5,6,7] 能够组成的二叉搜索树的数量是相同的
     * @param n
     * @return
     */
    public int numTrees2(int n){
        if(n == 0)  return 1;
        return getAns2(n);
    }

    private int getAns2(int n){
        if(n == 0 || n == 1)  return 1;
        int ans = 0;
        for(int i = 1; i <= n; i++){
            int numLeft = getAns2(i - 1);
            int numRight = getAns2(n - i);
            ans += numLeft * numRight;
        }
        return ans;
    }

    /**
     * （解法一的优化的优化）, 递归中会导致很多重复解，所以使用 memoization 备忘录 技术, 把递归过程中求出的解保存起来，第二次使用时直接拿
     * @param n
     * @return
     */
    public int numTrees3(int n){
        if(n == 0 || n == 1)    return 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        return getAns3(n, map);
    }

    private int getAns3(int n, HashMap<Integer, Integer> map){
        if(n == 0 || n == 1) return 1;
        if(map.containsKey(n))  return map.get(n);
        int ans = 0;
        for(int i = 1; i <= n; i++){
            int numLeft = getAns3(i - 1, map);
            int numRight = getAns3(n - i, map);
            ans += numLeft * numRight;
        }
        map.put(n, ans);
        return ans;
    }

    /**
     * 解法二：动态规划（厉害啊想不到）
     * 递归是从顶向下走，压栈压栈，遇到0 或 1 就出栈
     * 这里我们使用动态规划的思想，从底向上走，求出长度为0,1,2..直到长度为 n 的解
     * @param n
     * @return
     */
    public int numTrees4(int n){
        int[] dp = new int[n + 1];
        dp[0] = 1;
        //长度从 1 到 n
        for(int len = 1; len <= n; len++){
            //在 len 长度中，每个数字依次作为根节点
            for(int root = 1; root <= len; root++){
                int left = root - 1;
                int right = len - root;
                dp[len] += dp[left] * dp[right];
            }
        }
        return dp[n];
    }

    /**
     * （解法二的优化）利用对称性，减少一些循环
     * 当 n = 4 时， 1 2 | 3 4 这里以 1 为根和以 4 为根的求出的解是相同的，所以依次做根的那个循环可以只循环到 len/2 即可, 然后给dp[len] = dp[len] * 2
     * 当 n = 5 时， 1 2 | 3 | 4 5 这里 1,2 和 4,5 对称，需要单独算 3
     * @param n
     * @return
     */
    public int numTrees5(int n){
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        // 长度为 1 到 n
        for (int len = 2; len <= n; len++) {
            // 将不同的数字作为根节点，只需要考虑到 len
            for (int root = 1; root <= len / 2; root++) {
                int left = root - 1; // 左子树的长度
                int right = len - root; // 右子树的长度
                dp[len] += dp[left] * dp[right];
            }
            dp[len] *= 2;// 利用对称性乘 2
            // 考虑奇数的情况
            if ((len & 1) == 1) {
                int root = (len >> 1) + 1;
                int left = root - 1; // 左子树的长度
                int right = len - root; // 右子树的长度
                dp[len] += dp[left] * dp[right];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution96 solution96 = new Solution96();
        System.out.println(solution96.numTrees5(5));
    }

    /**
     * 解法三：公式法（太强了）
     * 卡特兰公式
     * https://leetcode.wang/leetCode-96-Unique-Binary-Search-Trees.html
     * @param n
     * @return
     */
    public int numTrees6(int n){
        //注意：定义为 long, 防止溢出
        long ans = 1;
        int i;
        for(i = 1; i <= n; i++){
            ans =  ans * (n + i) / i;
        }
        return (int)(ans / i);
    }
}
