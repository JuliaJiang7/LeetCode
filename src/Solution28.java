import java.util.Arrays;

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
        if(M == 0){return 0;}

        //dp[j][c] = i 表示： 当前状态为 j，且遇到的下一个字符是 c 时, 下一个状态位 i
        int[][] dp = new int[M][256];
        KMP(pat, dp);

        //pat 的初始状态为 0
        int j = 0;
        for(int i = 0; i < N; i++){
            //计算 pat 的下一个状态
            j = dp[j][txt.charAt(i)];
            //到达终止状态，匹配成功
            if(j == M){return i - M + 1;}
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
        System.out.println(solution28.strStr("ababdababc", "ababc"));
    }

    public int strStr2(String haystack, String needle){
        return haystack.indexOf(needle);
    }

    /**
     * 方法二：标准的KMP算法
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr3(String haystack, String needle) {
        if(needle.length() == 0){return 0;}
        char[] s = haystack.toCharArray();
        char[] p = needle.toCharArray();
        int[] next = getNextval(p);
        return kmp(s, p, next);
    }

    private int kmp(char[] s, char[] p, int[] next){
        int i = 0, j = 0;
        int sLen = s.length;
        int pLen = p.length;
        while (i < sLen && j < pLen){
            if(j == -1 || s[i] == p[j]){
                i++;
                j++;
            }else{
                j = next[j];
            }
        }
        if(j == pLen){
            return i - j;
        }else{
            return -1;
        }
    }

    private int[] getNextval(char[] p){
        int pLen = p.length;
        int[] next = new int[pLen];
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < pLen - 1){
            // p[k] 表示前缀；p[j] 表示后缀
            if(k == -1 || p[j] == p[k]){
                ++k;
                ++j;
                if(p[j] != p[k]){
                    next[j] = k;
                }else{
                    // 因为不能出现p[j] = p[next[j]]，所以当出现时需要继续递归，k = next[k] = next[next[k]]
                    next[j] = next[k];
                }

            }else{
                k = next[k];
            }
        }
        return next;
    }
}
