import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author julia.jiang
 * @date 2020/9/4 9:41
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution115 {

    /**
     * dp
     * https://leetcode.wang/leetcode-115-Distinct-Subsequences.html
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        // dp[i + 1][j + 1] = x 表示：s[0..i] 中的子序列 t[0..j] 的个数为 x
        int[][] dp = new int[sLen + 1][tLen + 1];
        // base case
        for(int i = 0; i <= sLen; i++){
            dp[i][0] = 1;
        }

        for(int j = 1; j <= tLen; j++){
            for(int i = 1; i <= sLen; i++){
                // 如果相等
                if(t.charAt(j - 1) == s.charAt(i - 1)){
                    // 有两种选择：不选择当前字母 dp[i - 1][j] 和选择当前字母 dp[i - 1][j - 1]
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                    // 如果不相等
                }else {
                    // 只有一种选择：不选当前字母
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[sLen][tLen];
    }

}
