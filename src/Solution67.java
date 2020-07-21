/**
 * @author julia.jiang
 * @date 2020/7/15 9:02
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution67 {

    /**
     * 参考：https://leetcode.wang/leetCode-67-Add%20Binary.html
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        // 转为char数组操作，可以方便转换int和char
        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();

        // 提前申请sum空间，从后向前赋值
        // 多申请一位空间，用来存储进位
        char[] sum = new char[Math.max(a.length(), b.length()) + 1];
        int carry = 0, index = sum.length - 1;
        for(int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--){
            int numA = i < 0 ? 0 : charsA[i] - '0';
            int numB = j < 0 ? 0 : charsB[j] - '0';

            int s = numA + numB + carry;
            sum[index--] = (char)(s % 2 + '0');
            carry = s / 2;
        }
        sum[index] = (char)('0' + carry);
        return carry == 0 ? new String(sum, 1, sum.length - 1) : new String(sum);
    }
}
