/**
 * @author Julia Jiang
 * @date 2020/3/21 12:11
 * @description 两数相除
 * 计算机存储补码，对于正数求补码就是原码，对于负数求补码就是按位取反再加一
 * 计算机中，0 开头为正，1 开头为负
 * 计算机中求数 x 的相反数，就是求 -x 的补码，即对 x 按位取反再加一
 * https://leetcode.wang/leetCode-29-Divide-Two-Integers.html
 */
public class Solution29 {

    /**
     * 尝试一：存在问题：
     * 1. 当取值为  - 2147483648 即 -2^31, 取相反数还是 - 2147483648, 计算错误；比如当 model 为16时， -8 的补码还是 1000
     * 2. 超过时间限制
     * @param dividend 被除数
     * @param divisor 除数
     * @return
     */
    public int divide(int dividend, int divisor) {
        int ans = 0;
        int sign = 1;
        //把被除数转换为正数
        if (dividend < 0) {
            //因为不能使用乘法，所以取相反数
            sign = opposite(sign);
            dividend = opposite(dividend);
        }
        //把除数转换为正数
        if (divisor < 0) {
            sign = opposite(sign);
            divisor = opposite(divisor);
        }
        while (divisor <= dividend) {
            ans = ans + 1;
            dividend = dividend - divisor;
        }
        return sign > 0 ? ans : opposite(ans);
    }

    /**
     * 求 x 的相反数（x 为正）
     * 按位取反再加一
     * @param x
     * @return
     */
    private int opposite(int x) {
        return ~x + 1;
    }

    /**
     * 解法一：
     * 如果出现 -2147483648 无法转为正数，使用 long 实现
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide2(int dividend, int divisor) {
        long ans = divide2((long)dividend,(long)(divisor));
        long m = 2147483648L;
        //ans 最大值取到 m, 此时 dividend = -2147483648, divisor=-1
        if(ans == m ){
            return Integer.MAX_VALUE;
        }else{
            return (int)ans;
        }
    }
    private long divide2(long dividend, long divisor) {
        long ans = 1;
        long sign = 1;
        if (dividend < 0) {
            sign = opposite(sign);
            dividend = opposite(dividend);
        }
        if (divisor < 0) {
            sign = opposite(sign);
            divisor = opposite(divisor);
        }
        long origin_dividend = dividend;
        long origin_divisor = divisor;

        if (dividend < divisor) {
            return 0;
        }

        dividend -= divisor;
        while (divisor <= dividend) {
            ans = ans + ans;
            divisor += divisor;
            //减一次、两次、四次、八次除数
            dividend -= divisor;
        }
        long a = ans + divide2(origin_dividend - divisor, origin_divisor);
        return sign > 0 ? a : opposite(a);
    }
    private long opposite(long x) {
        return ~x + 1;
    }

    /**
     * 解法二：不使用long
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide3(int dividend, int divisor) {
        int ans = -1;
        int sign = 1;
        if (dividend > 0) {
            sign = opposite(sign);
            dividend = opposite(dividend);
        }
        if (divisor > 0) {
            sign = opposite(sign);
            divisor = opposite(divisor);
        }

        int origin_dividend = dividend;
        int origin_divisor = divisor;
        if (dividend > divisor) {
            return 0;
        }

        dividend -= divisor;
        while (divisor >= dividend) {
            ans = ans + ans;
            divisor += divisor;
            dividend -= divisor;
        }
        //此时我们传进的是两个负数，正常情况下，它就返回正数，但我们是在用负数累加，所以要取相反数
        int a = ans + opposite(divide3(origin_dividend - divisor, origin_divisor));
        if(a == Integer.MIN_VALUE){
            if( sign > 0){
                return Integer.MAX_VALUE;
            }else{
                return Integer.MIN_VALUE;
            }
        }else{
            if(sign > 0){
                return opposite(a);
            }else{
                return a;
            }
        }
    }
}

