/**
 * @author Julia Jiang
 * @date 2020/2/25 13:42
 * @description 编辑距离（动态规划）
 */
public class Solution72 {
    public int minDistance(String word1, String word2){
        int len1 = word1.length();
        int len2 = word2.length();
        //word1 和 word2 的下标从 1 开始
        //dp[i][j] = n 表示：word1[1..i] 和 word2[1..j] 的最小编辑距离为 n
        int[][] dp = new int[len1+1][len2+2];

        //base case
        for(int i = 0; i <= len1; i++){
            dp[i][0] = i;
        }
        for(int j = 0; j <= len2; j++){
            dp[0][j] = j;
        }

        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    //三种情况：插入、删除、替换
                    dp[i][j] = min(dp[i][j-1] + 1, dp[i-1][j] + 1, dp[i-1][j-1] + 1);
                }
            }
        }
        return dp[len1][len2];
    }

    private int min(int a, int b, int c){
        return Math.min(Math.min(a,b),c);
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        Solution72 solution72 = new Solution72();
        System.out.println(solution72.minDistance(word1, word2));
    }
}
