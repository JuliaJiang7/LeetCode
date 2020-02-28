/**
 * @author Julia Jiang
 * @date 2020/2/25 12:25
 * @description 最长公共子序列
 */
public class Solution1143 {
    public int longestCommonSubsequence(String text1, String text2){
        int len1 = text1.length();
        int len2 = text2.length();
        // 让 text1,text2 的索引从 1 开始
        // dp[i][j] = n 表示：对于 text1[1...i] 和 text2[1...j] 的最长公共子序列为 n
        //base case 是：dp[0][..] 和 dp[..][0] 初始化为0
        int[][] dp = new int[len1+1][len2+1];

        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                //状态转移
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String text1 = "dabedc";
        String text2 = "ssss";
        Solution1143 solution1143 = new Solution1143();
        System.out.println(solution1143.longestCommonSubsequence(text1, text2));
    }
}
