import java.util.LinkedList;

/**
 * @author julia.jiang
 * @date 2020/8/25 9:23
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution91 {

    /**
     * 参考：https://leetcode-cn.com/problems/decode-ways/solution/dong-tai-gui-hua-java-python-by-liweiwei1419/
     * @param s
     * @return
     */
    public int numDecodings2(String s) {
        int len = s.length();
        // dp[i] = x 表示：s[0...i] 有 x 中解码方法
        int[] dp = new int[len];

        char[] charArray = s.toCharArray();
        if (charArray[0] == '0') {
            return 0;
        }
        // base case
        dp[0] = 1;

        for (int i = 1; i < len; i++) {
            int num = 10 * (charArray[i - 1] - '0') + (charArray[i] - '0');

            // 如果是 0
            if(charArray[i] == '0'){
                // 只有和前一位组成的数字在 10 到 26 之内，dp 才会有值，否则是初始值 0
                if(10 <= num && num <= 26){
                    if(i == 1){
                        dp[i] = 1;
                    }else{
                        dp[i] = dp[i - 2];
                    }
                }
            // 如果不是 0
            }else{
                // 和前一位组成的数字在 10 到 26 之内，dp 值增大
                if(10 <= num && num <= 26){
                    if(i == 1){
                        dp[i] = dp[i - 1] + 1;
                    }else{
                        dp[i] = dp[i - 1] + dp[i - 2];
                    }
                // 不在 10 到 26 之内，dp 和 前一位相同
                }else{
                    dp[i] = dp[i - 1];
                }
            }
        }
        return dp[len - 1];
    }


}
