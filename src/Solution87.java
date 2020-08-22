/**
 * @author julia.jiang
 * @date 2020/8/21 9:20
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution87 {
    public boolean isScramble(String s1, String s2) {
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        int n = s1.length();

        // dp[i][j][len]dp[i][j][len] 表示从字符串 S 中 i 开始（包括 i）长度为 len 的字符串是否能变换为
        // 从字符串 T 中 j （包括 j）开始长度为 len 的字符串
        boolean[][][] dp = new boolean[n][n][n + 1];

        // base case
        // 初始化单个字符的情况
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                dp[i][j][1] = (chs1[i] == chs2[j]);
            }
        }

        // 枚举长度区间 len
        for(int len = 2; len <= n; len++){
            // 枚举 S 的起点
            for(int i = 0; i <= n - len; i++){
                // 枚举 T 的起点
                for(int j = 0; j <= n - len; j++){
                    // 枚举划分位置
                    for(int k = 1; k <= len - 1; k++){
                        // 第一种情况：不交换
                        if(dp[i][j][k] && dp[i + k][j + k][len - k]){
                            dp[i][j][len] = true;
                            break;
                        }
                        // 第二种情况：交换
                        if(dp[i][j + len - k][k] && dp[i + k][j][len - k]){
                            dp[i][j][len] = true;
                            break;
                        }
                    }

                }
            }
        }

        return dp[0][0][n];
    }

    public static void main(String[] args) {
        Solution87 solution87 = new Solution87();
        solution87.isScramble("abcd", "abcd");
    }
}
