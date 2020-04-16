/**
 * @author Julia Jiang
 * @date 2020/3/29 12:31
 * @description 通配符匹配
 * 参考10题解法
 */
public class Solution44 {

    public boolean isMatch(String s, String p) {
        if(s == null || p == null)
            return false;
        int len1 = s.length();
        int len2 = p.length();

        //dp 数组中 字符串下标从 1 开始，索引 0 存储空字符
        //dp[i][j] = true 表示：s[0..(i-1)] p[0..(j-1)] 可以匹配
        //base case：dp[..][0] 初始化为 0, 不存在可以匹配的可能
        boolean[][] dp = new boolean[len1+1][len2+1];
        dp[0][0] = true;
        //base case: dp[0][..] 初始化，如果出现 * 则可以视为空字符串
        for(int j = 0; j < len2; j++){
            if(p.charAt(j) == '*'){
                dp[0][j + 1] = dp[0][j];
            }
        }

        for(int i = 0; i < len1; i++){
            for(int j = 0; j < len2; j++){
                if(p.charAt(j) == '?' || s.charAt(i) == p.charAt(j)){
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if(p.charAt(j) == '*'){
                    //情况一：s = "a", p = "a*", 此时 * 被当做空字符串 dp[i + 1][j + 1] = dp[i + 1][j]
                    //情况二：s = "abc", p = "a*", 此时 * 被当做任意字符串 dp[i + 1][j + 1] = dp[i][j + 1]
                    dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
