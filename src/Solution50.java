/**
 * @author Julia Jiang
 * @date 2020/4/2 11:39
 * @description Pow(x, n)
 * https://leetcode.wang/leetCode-50-Pow.html
 */
public class Solution50 {
    /**
     * 解法一
     * @param x
     * @param n
     * @return
     * 时复 O(n)
     */
    public double myPow(double x, int n) {
        // 对 1 和 -1 特判。否则会出现超时
        if(x == 1.0)    return 1;
        if(x == -1.0){
            if(n % 2 == 0){
                return 1;
            }else{
                return -1;
            }
        }
        // 当 n 取值为 -2^31 时，n 的相反数还是 -2^31，因而需要特判
        if(n == -2147483648){
            if(x > -1 && x < 1){
                return Double.POSITIVE_INFINITY;
            }
            else{
                return 0;
            }
        }

        double mul = 1;
        if(n > 0){
            for(int i = 0; i < n; i++){
                mul *= x;
            }
        }else{
            n *= -1;
            for(int i = 0; i < n; i++){
                mul *= x;
            }
            mul = 1 / mul;
        }
        return mul;
    }

    /**
     * 解法二：递归
     * @param x
     * @param n
     * @return
     * 时复 O(log(n))
     */
    public double myPow2(double x, int n) {
        // 对 1 和 -1 特判, 否则会出现超时
        if(x == 1.0)    return 1;
        if(x == -1.0){
            if(n % 2 == 0){
                return 1;
            }else{
                return -1;
            }
        }
        // 当 n 取值为 -2^31 时，n 的相反数还是 -2^31，因而需要特判
        if(n == -2147483648){
            if(x > -1 && x < 1){
                return Double.POSITIVE_INFINITY;        // 并没有这个测试数据，手动输入返回的是 Inf
            }
            else{
                return 0;
            }
        }

        if(n > 0){
            return powRecursion(x, n);
        }else{
            n *= -1;
            return 1 / powRecursion(x, n);
        }
    }

    public double powRecursion(double x, int n) {
        if(n == 0)  return 1;
        if(n % 2 == 0){
            return powRecursion(x * x, n / 2);
        }
        else{
            return powRecursion(x * x, n / 2) * x;
        }
    }



    public static void main(String[] args) {
        Solution50 solution50 = new Solution50();
        System.out.println(solution50.myPow2(2.0, 10));
    }
}
