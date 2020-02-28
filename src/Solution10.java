/**
 * @author Julia Jiang
 * @date 2020/2/26 12:56
 * @description 正则表达式匹配（动态规划）
 */
public class Solution10 {
    public boolean isMatch(String s, String p){
        if(s == null || p == null)
            return false;
        int len1 = s.length();
        int len2 = p.length();

        //dp 数组中 字符串下标从 1 开始，索引 0 存储空字符
        //dp[i][j] = true 表示：s[0..(i-1)] p[0..(j-1)] 可以匹配
        //base case：dp[..][0] 初始化为 0, 不存在可以匹配的可能
        boolean[][] dp = new boolean[len1+1][len2+1];
        dp[0][0] = true;
        //base case: dp[0][..] 初始化，如果出现 * 则可以消去上一个字符，且 上上一个字符对应 dp 值为 true，则当前 dp 值为true
        for(int j = 0; j < len2; j++){
            if(p.charAt(j) == '*' && dp[0][j-1]){   //j-1
                dp[0][j+1] = true;                  //j+1
            }
        }

        for(int i = 0; i < len1; i++){
            for(int j = 0; j < len2; j++){
                //如果元素匹配或者为任意元素
                if(p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)){
                    dp[i + 1][j + 1] = dp[i][j];
                }

                if(p.charAt(j) == '*'){
                    //如果前一个元素不匹配，则消去这两个元素
                    if(p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.'){
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    }else{
                        /*
                        * 如果前一个元素匹配，存在三种情况：
                        * 重复前一个字符多次（abbbb ab*）dp[i + 1][j + 1] = dp[i][j + 1]
                        * 重复前一个字符一次（ab ab*）dp[i + 1][j + 1] = dp[i + 1][j]
                        * 重复前一个字符零次（a ab*）dp[i + 1][j + 1] = dp[i + 1][j - 1]
                         */
                        dp[i + 1][j + 1] = (dp[i][j + 1] || dp[i + 1][j] || dp[i + 1][j - 1]);
                    }
                }
            }
        }
        return dp[len1][len2];
    }

    public boolean isMatch2(String s, String p){
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;//dp[i][j] 表示 s 的前 i 个是否能被 p 的前 j 个匹配
        for (int i = 0; i < p.length(); i++) { // here's the p's length, not s's
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true; // here's y axis should be i+1
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {//如果是任意元素 或者是对于元素匹配
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {//如果前一个元素不匹配 且不为任意元素
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                            /*
                            dp[i][j] = dp[i-1][j] // 多个字符匹配的情况
                            or dp[i][j] = dp[i][j-1] // 单个字符匹配的情况
                            or dp[i][j] = dp[i][j-2] // 没有匹配的情况
                             */

                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public static void main(String[] args){
        String s = "ab";
        String p = ".*";
        Solution10 solution10 = new Solution10();
        System.out.println(solution10.isMatch(s, p));
        System.out.println(solution10.isMatch2(s, p));
    }
}
