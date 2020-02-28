/**
 * @author Julia Jiang
 * @date 2020/2/27 13:09
 * @description 实现 strStr() KMP 字符串匹配(动态规划思想实现KMP)
 * https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/dong-tai-gui-hua-zhi-kmp-zi-fu-pi-pei-suan-fa
 */
public class Solution28 {
    /**
     * 根据 dp 寻找字符串
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle){
        String txt = haystack;
        String pat = needle;
        int M = pat.length();
        int N = txt.length();
        if(M == 0)  //添加特判
            return 0;

        //dp[j][c] = i 表示： 当前状态为 j，且遇到的下一个字符是 c 时, 下一个状态位 i
        int[][] dp = new int[M][256];
        KMP(pat, dp);

        //pat 的初始状态为 0
        int j = 0;
        for(int i = 0; i < N; i++){
            //计算 pat 的下一个状态
            j = dp[j][txt.charAt(i)];
            //到达终止状态，匹配成功
            if(j == M)
                return i - M + 1;
        }
        //没到达终止状态，匹配失败
        return -1;
    }

    /**
     * 计算 dp, 即就是 next
     * @param pat
     * @param dp
     */
    public void KMP(String pat, int[][] dp){
        int M = pat.length();

        //base case
        dp[0][pat.charAt(0)] = 1;
        //影子状态，初始化为 0
        int X = 0;
        //构建状态转移图
        for(int j = 1; j < M; j++){
            for(int c = 0; c < 256; c++){
                if(pat.charAt(j) == c){
                    dp[j][c] = j + 1;
                }else{
                    dp[j][c] = dp[X][c];
                }
            }
            //更新影子状态
            X = dp[X][pat.charAt(j)];
        }
    }

    public static void main(String[] args) {
        Solution28 solution28 = new Solution28();
        System.out.println(solution28.strStr("hello", "ll"));
    }

    public int strStr2(String haystack, String needle){
        return haystack.indexOf(needle);
    }

}
