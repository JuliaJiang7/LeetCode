import java.util.ArrayList;
import java.util.List;

/**
 * @author Julia Jiang
 * @date 2020/3/15 12:57
 * @description 括号生成
 */
public class Solution22 {

    /**
     * 解法一：暴力法
     * 用递归生成所有的序列
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos+1, result);
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }

    /**
     * 解法二：深度优先遍历
     * 做减法
     * https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        if(n == 0)  return res;
        dfs("", n, n, res);
        return res;
    }
    private void dfs(String curStr, int left, int right, List<String> res){
        //递归终止时，将结果添加到结果集
        if(left == 0 && right == 0){
            res.add(curStr);
            return;
        }
        //剪枝
        if(left < right){
            return;
        }
        if(left > 0){
            dfs(curStr, left - 1, right, res);
        }
        if(right > 0){
            dfs(curStr, left, right - 1, res);
        }
    }

    /**
     * 解法三：动态规划
     * https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
     * @param n
     * @return
     */
    public List<String> generateParenthesis3(int n) {
        if(n == 0)  return new ArrayList<>();

        //dp 数组设为一个list， 方便表示
        //dp.get(i) 表示：有 i 对括号时，组成的有效括号组合
        List<List<String>> dp = new ArrayList<>();
        //dp 边界,当 n = 0 时，有效括号组合为 ""
        List<String> dp0 = new ArrayList<>();
        dp0.add("");
        dp.add(dp0);

        for(int i = 1; i <= n; i++){
            List<String> curr = new ArrayList<>();
            for(int j = 1; j < i; j++){
                List<String> list1 = dp.get(j);
                List<String> list2 = dp.get(i - 1 - j);
                for(String str1 : list1){
                    for(String str2 : list2){
                        curr.add("(" + str1 + ")" + str2);
                    }
                }
            }
        }
        return dp.get(n);
    }

    /*
     * 如果只是求生成有效括号的组合个数，利用卡特兰数
     * https://leetcode.wang/leetCode-22-Generate-Parentheses.html?q=
     */
}
