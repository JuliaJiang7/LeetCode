import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Julia Jiang
 * @date 2020/3/25 11:45
 * @description 子集（回溯）
 */
public class Solution78 {

    List<List<Integer>> res = new LinkedList<>();
    /**
     * 解法一：回溯
     * https://mp.weixin.qq.com/s/qT6WgR6Qwn7ayZkI3AineA
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track, 0);
        return res;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track, int start) {
        res.add(new LinkedList<>(track));

        for(int i = start; i < nums.length; i++){
            track.add(nums[i]);
            backtrack(nums, track, start + 1);
            track.removeLast();
        }
    }


    /**
     * 解法二：迭代
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        res.add(new LinkedList<>());

        for (int num : nums) {
            int size = res.size();
            for(int i = 0; i < size; i++){
                // 注意：这里需要 new 一个对象，不可以直接赋值
                List<Integer> cur = new LinkedList<>(res.get(i));
                cur.add(num);
                res.add(cur);
            }
        }

        return res;
    }
}
