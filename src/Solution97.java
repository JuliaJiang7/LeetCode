import java.util.HashMap;
import java.util.Map;

/**
 * @author julia.jiang
 * @date 2020/8/28 9:45
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution97 {

    /**
     * 解法一：回溯
     * 参考：https://leetcode.wang/leetCode-97-Interleaving-String.html
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return backtrack(s1, s2, s3, 0, 0, 0);
    }

    private boolean backtrack(String s1, String s2, String s3, int i, int j, int k) {
        // i，j,k 全部到达末尾就返回true
        if (i == s1.length() && j == s2.length() && k == s3.length()) {
            return true;
        }
        // i 到达末尾，直接移动 j 和 k 不停比较
        if (i == s1.length()) {
            while (j < s2.length()) {
                if (s2.charAt(j) != s3.charAt(k)) {
                    return false;
                }
                j++;
                k++;
            }
            return true;
        }
        // j 到达末尾，直接移动 i 和 k 不停比较
        if (j == s2.length()) {
            while (i < s1.length()) {
                if (s1.charAt(i) != s3.charAt(k)) {
                    return false;
                }
                i++;
                k++;
            }
            return true;
        }
        // 判断 i 和 k 指向的字符是否相等
        if (s1.charAt(i) == s3.charAt(k)) {
            // 后移 i 和 k 继续判断，如果成功直接返回true
            if (backtrack(s1, s2, s3, i + 1, j, k + 1)) {
                return true;
            }
        }
        // 移动 i 和 k 失败，尝试移动 j 和 k
        if (s2.charAt(j) == s3.charAt(k)) {
            if (backtrack(s1, s2, s3, i, j + 1, k + 1)) {
                return true;
            }
        }
        // 移动 i 和 j 都失败，返回 false
        return false;
    }

    /**
     * 解法二：回溯的优化（备忘录）
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        // memo 的 key 是：i + "@" + j，用@隔开是为了防止i=1,j=22时，可能出现两种组合情况：(1,22),(12,2)
        // memo 的 value 取值：-1 表示为赋值；0 表示false；1 表示true
        // value 其实用不到三个值，因为一旦当前的 i 和 j 符合要求，直接返回 true 即可。因此 memo 中只需要存不满足情况的 i 和 j 即可
        Map<String, Integer> memo = new HashMap<>();
        return backtrack2(s1, s2, s3, 0, 0, 0, memo);
    }

    private boolean backtrack2(String s1, String s2, String s3, int i, int j, int k, Map<String, Integer> memo) {
        String key = i + "@" + j;
        if (memo.containsKey(key)) {
            return false;
        }
        if (i == s1.length() && j == s2.length() && k == s3.length()) {
            return true;
        }

        if (i == s1.length()) {
            while (j < s2.length()) {
                if (s2.charAt(j) != s3.charAt(k)) {
                    memo.put(key, 0);
                    return false;
                }
                j++;
                k++;
            }
            return true;
        }
        if (j == s2.length()) {
            while (i < s1.length()) {
                if (s1.charAt(i) != s3.charAt(k)) {
                    memo.put(key, 0);
                    return false;
                }
                i++;
                k++;
            }
            return true;
        }
        if (s1.charAt(i) == s3.charAt(k)) {
            if (backtrack2(s1, s2, s3, i + 1, j, k + 1, memo)) {
                return true;
            }
        }
        if (s2.charAt(j) == s3.charAt(k)) {
            if (backtrack2(s1, s2, s3, i, j + 1, k + 1, memo)) {
                return true;
            }
        }
        memo.put(key, 0);
        return false;
    }

    /**
     * 解法三：动态规划
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave3(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        if (s1.length() == 0 && s2.length() == 0 && s3.length() == 0) {
            return true;
        }
        int len1 = s1.length();
        int len2 = s2.length();
        // dp[i][j] = true 表示：s1[0..i) 和 s2[0..j) 组合后能构成 s3[0..i+j)
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        // base case
        dp[0][0] = true;
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                        || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[len1][len2];
    }
}
