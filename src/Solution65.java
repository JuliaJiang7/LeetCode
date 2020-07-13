/**
 * @author julia.jiang
 * @date 2020/7/13 8:54
 * @email julia.jiang.fan@gmail.com
 * @description 有效数字
 */
public class Solution65 {

    /**
     * 解法一：直接发
     * 参考：https://leetcode.wang/leetCode-65-Valid-Number.html#%E8%A7%A3%E6%B3%95%E4%B8%80-%E7%9B%B4%E6%8E%A5%E6%B3%95
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        s = s.trim();

        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen) {
                    // 小数点前面是e或者小数点都返回false
                    return false;
                }
                pointSeen = true;
            } else if(s.charAt(i) == 'e') {
                if(eSeen || !numberSeen) {
                    // e前面是e或者不是数字都返回false
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && s.charAt(i-1) != 'e') {
                    // 加减号没在第一位或者e的下一位都返回false
                    return false;
                }
            } else {
                // 含有除了这些字符之外的字符，均返回false
                return false;
            }
        }
        // 最后以数字结尾
        return numberSeen && numberAfterE;
    }

    public static void main(String[] args) {
        String s = "0.5e04";
        Solution65 solution65 = new Solution65();
        System.out.println(solution65.isNumber(s));
    }
}
