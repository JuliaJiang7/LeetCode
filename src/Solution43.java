/**
 * @author Julia Jiang
 * @date 2020/3/27 11:17
 * @description 字符串相乘（大数乘法）
 */
public class Solution43 {

    /**
     * 解法一：模拟手动乘法运算
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0"))    return "0";
        int len1 = num1.length();
        int len2 = num2.length();
        int index = 0;      //表示这一位算出的结果后面加几个 0
        String result = "";
        for(int i = len2 - 1; i >= 0; i--){
            int n1 = num2.charAt(i) - '0';
            int carry = 0;
            StringBuilder ans = new StringBuilder();
            for(int j = len1 - 1; j >= 0; j--){
                int n2 = num1.charAt(j) - '0';
                int temp = (n1 * n2 + carry) % 10;
                ans.insert(0, temp);
                carry = (n1 * n2 + carry) / 10;
            }
            if(carry > 0){
                ans.insert(0, carry);
            }
            int index_tmp = index;
            while (index_tmp > 0){
                ans.append(0);
                index_tmp--;

            }
            result = add(result, ans.toString());
            index++;
        }
        return result;
    }

    /**
     * 大数相加
     * @param num1
     * @param num2
     * @return
     */
    private String add(String num1, String num2){
        int carry = 0;
        int num1_index = num1.length() - 1;
        int num2_index = num2.length() - 1;
        StringBuilder result = new StringBuilder();

        while (num1_index >= 0 || num2_index >= 0){
            int n1 = num1_index >= 0 ? num1.charAt(num1_index) - '0' : 0;
            int n2 = num2_index >= 0 ? num2.charAt(num2_index) - '0' : 0;
            int ans = (n1 + n2 + carry) % 10;
            carry = (n1 + n2 + carry) / 10;
            result.insert(0, ans);
            num1_index--;
            num2_index--;
        }

        if(carry > 0){
            result.insert(0, carry);
        }

        return result.toString();
    }

    /**
     * 解法二：竖式乘法
     * @param num1
     * @param num2
     * @return
     */
    public String multiply2(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0"))    return "0";
        int len1 = num1.length();
        int len2 = num2.length();
        int[] pos = new int[len1 + len2];
        for(int i = len2 - 1; i >= 0; i--){
            for(int j = len1 - 1; j >= 0; j--){
                int ans = (num2.charAt(i) - '0') * (num1.charAt(j) - '0');
                int ans_temp = ans + pos[i + j + 1];
                pos[i + j + 1] = ans_temp % 10;
                pos[i + j] += (ans_temp / 10);
            }
        }
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < len1 + len2; i++){
            //首位只可能出现一个 0
            if(i == 0 && pos[i] == 0){
                continue;
            }
            result.append(pos[i]);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Solution43 solution43 = new Solution43();
        System.out.println(solution43.multiply2("123", "456"));
    }
}
