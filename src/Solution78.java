import java.util.ArrayList;
import java.util.List;

/**
 * @author Julia Jiang
 * @date 2020/3/25 11:45
 * @description 子集（回溯）
 */
public class Solution78 {

    private List<List<Integer>> res = new ArrayList<>();

    /**
     * 解法一：回溯
     * https://labuladong.gitbook.io/algo/suan-fa-si-wei-xi-lie/zi-ji-pai-lie-zu-he
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> trace = new ArrayList<>();
        backtrace(nums, 0, trace);
        return res;
    }

    private void backtrace(int[] nums, int start, List<Integer> trace){
        res.add(new ArrayList<>(trace));   //注意：这里需要new一个对象把trace的值放进去，否则for中会更新trace的值
        for(int i = start; i < nums.length; i++){
            trace.add(nums[i]);
            backtrace(nums, i + 1, trace);
            trace.remove(trace.size() - 1);
        }
    }

    /**
     * 解法二：迭代
     * https://leetcode.wang/leetCode-78-Subsets.html（解法二）
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int num : nums) {
            List<List<Integer>> tmp_res = new ArrayList<>();
            for (List<Integer> oneOfRes : res) {
                List<Integer> tmp = new ArrayList<>(oneOfRes);
                tmp.add(num);
                tmp_res.add(tmp);
            }
            res.addAll(tmp_res);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution78 solution78 = new Solution78();
        System.out.println(solution78.subsets(new int[]{1,2,3}));
    }
}
