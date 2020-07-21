/**
 * @author julia.jiang
 * @date 2020/7/14 9:00
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution66 {
    /**
     * 方法一：迭代
     * 参考：https://leetcode.wang/leetCode-66-Plus-One.html
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        //从最低位遍历
        for (int i = digits.length - 1; i >= 0; i--) {
            //小于 9 的话，直接加 1，结束循环
            if (digits[i] < 9) {
                digits[i] += 1;
                break;
            }
            //否则的话置为 0
            digits[i] = 0;
        }
        //最高位如果置为 0 了，说明最高位产生了进位
        if (digits[0] == 0) {
            int[] ans = new int[digits.length + 1];
            ans[0] = 1;
            digits = ans;
        }
        return digits;
    }

    public static void main(String[] args) {
        int[] nums = {9,7,9};
        Solution66 solution66 = new Solution66();
        solution66.plusOne(nums);
    }
}
