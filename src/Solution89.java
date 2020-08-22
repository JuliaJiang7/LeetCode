import java.util.ArrayList;
import java.util.List;

/**
 * @author julia.jiang
 * @date 2020/8/22 9:30
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution89 {
    public List<Integer> grayCode(int n) {
        // dp 列表存储最终结果
        List<Integer> dp = new ArrayList<>();
        // base case，n = 0 时
        dp.add(0);

        // 遍历要加的数的次数，从 0 开始，到 n-1 结束
        for(int i = 0; i < n; i++){
            // 要加的数，即 2^i
            int add = 1 << i;
            // 倒序遍历，加上 add 添加到结果集中
            for(int j = dp.size() - 1; j >= 0; j--){
                dp.add(dp.get(j) + add);
            }
        }
        return dp;
    }
}
