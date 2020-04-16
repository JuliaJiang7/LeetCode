import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Julia Jiang
 * @date 2020/4/15 9:57
 * @description 第k个排列
 * https://leetcode.wang/leetCode-60-Permutation-Sequence.html（想不到，好难啊）
 * 这道题如果挨着写出全排列知道第k个，会超时
 */
public class Solution60 {

    public static void main(String[] args) {
        Solution60 solution60 = new Solution60();
        System.out.println(solution60.getPermutation(3,3));
    }

    /**
     * 解法一
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(i + 1);
        }
        return getAns(nums, n, k);
    }

    private String getAns(List<Integer> nums, int n, int k) {
        if (n == 1) {
            // 把剩下的最后一个数字返回就可以了
            return nums.get(0) + "";
        }
        // 每组的个数
        int perGroupNum = factorial(n - 1);
        // 组数
        int groupNum = (k - 1) / perGroupNum;
        // 最高位
        int num = nums.get(groupNum);
        // 从剩下需要排列的数字中移除最高位
        nums.remove(groupNum);
        // 更新下次的 k
        k = k % perGroupNum;
        k = k == 0 ? perGroupNum : k;
        return num + getAns(nums, n - 1, k);
    }

    public int factorial(int number) {
        if (number <= 1)
            return 1;
        else
            return number * factorial(number - 1);
    }

    /**
     * 解法一的优化
     * @param n
     * @param k
     * @return
     */
    public String getPermutation2(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        // nums存储需要加进全排列中的数字
        for (int i = 0; i < n; i++) {
            nums.add(i + 1);
        }
        // 求出 n 的阶乘
        int factorial = 1;
        for(int i = 2; i <= n; i++){
            factorial *= i;
        }

        StringBuilder ans = new StringBuilder();
        k = k - 1; // k 变为 k - 1
        // 循环n次
        for (int i = 0; i < n; i++) {
            // 此时的factorial表示每组数字个数
            factorial /= (nums.size()); //更新为 n - 1 的阶乘
            // 组数，从0开始
            int groupNum = k / factorial;
            // 把最高位数字加入结果
            int num = nums.get(groupNum);
            ans.append(num);
            // 从nums中移除最高位
            nums.remove(groupNum);
            // 下一次K值
            k = k % factorial;
        }
        return ans.toString();
    }
}
