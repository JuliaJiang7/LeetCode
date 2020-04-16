import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Julia Jiang
 * @date 2020/3/23 11:50
 * @description 最长有效括号
 * https://leetcode.wang/leetCode-32-Longest-Valid-Parentheses.html
 */
public class Solution32 {

    /**
     * 解法一：暴力解法（超时）
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        for(int i = 0; i < s.length(); i++){
            for(int j = i + 2; j <= s.length(); j += 2){    //j 是闭区间，这里取小于等于
                if(isValid(s.substring(i, j))){
                    maxLen = Math.max(maxLen, j - i);
                }
            }
        }
        return maxLen;
    }

    private boolean isValid(String s){
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){     //charAt 返回的字符类型，字符类型用单引号
                stack.push('(');
            }else if(!stack.isEmpty() && stack.peek() == '('){
                stack.pop();
            }else{
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 解法二（暴力破解优化）
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        int count = 0;
        int max = 0;
        for(int i = 0; i < s.length(); i++){
            count = 0;
            for(int j = i; j < s.length(); j++){
                if(s.charAt(j) == '('){
                    count++;
                }else{
                    count--;
                }
                if (count < 0){
                    break;
                }
                if (count == 0){
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    /**
     * 动态规划
     * @param s
     * @return
     */
    public int longestValidParentheses3(String s) {
        int max = 0;
        // dp[i] 表示： 以下标为 i 结尾的合法序列的最大长度，先全部初始化 0
        int dp[] = new int[s.length()];
        for(int i= 1; i < s.length(); i++){
            //如果以 '(' 结尾肯定是无效的序列，最大长度为初始化的0，因而只需判断结尾为 ')'
            if(s.charAt(i) == ')'){
                //前一个字符为左括号
                if(s.charAt(i - 1) == '('){
                    dp[i] = (i > 2 ? dp[i - 2] : 0) + 2;
                //前一个字符为右括号，并且除去前边的合法序列之后前一个字符是左括号
                }else if(i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '('){
                    dp[i] = dp[i - 1] + ((i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2);
                }
                max = Math.max(dp[i], max);
            }
        }
        return max;
    }
}
