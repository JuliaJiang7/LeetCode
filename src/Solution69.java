/**
 * @author julia.jiang
 * @date 2020/7/15 9:22
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution69 {

    /**
     * 方法一：改进的二分法
     * 参考：https://leetcode.wang/leetCode-69-Sqrtx.html
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int L = 1, R = x;
        // 保存最后的解，初始为0，当x=0时，直接返回0
        int ans = 0;
        // 注意这里是小于等于，考虑x=1的情况
        while (L <= R){
            // 如果 x = Integer.MAX_VALUE, 会溢出
            //int mid = (L + R) / 2;
            int mid = L + (R - L) / 2;

            // 如果 x = Integer.MAX_VALUE, 平方会溢出
            // int square = mid * mid;
            // 使用除法
            int div = x / mid;
            if(div == mid){
                return mid;
            }else if(mid < div){
                // 防止没找到相等的，就直接返回
                ans = mid;
                L = mid + 1;
            }else{
                R = mid - 1;
            }
        }
        return ans;
    }
}
