import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Julia Jiang
 * @date 2020/2/22 16:49
 * @description 全排列 2
 * 注意本题与46题区别：nums 数组中存在重复的数字，这样全排列中也会存在重复序列。
 *      因此 46 题的第一种方法 track.contains 没办法确定数字是否访问过，使用第二种方法，再加上排序和pre
 */
public class Solution47 {

    public static void main(String[] args) {
        int[] nums = {1,2,1};
        Solution47 solution = new Solution47();
        List<List<Integer>> list = solution.permuteUnique(nums);
        System.out.println(list);
    }

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permuteUnique(int[] nums){
        int len = nums.length;
        if(len == 0)    return res;

        Arrays.sort(nums);  //排序；相同的数字放在一起，重复的剪枝方便
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[len];
        backtrace(nums, track, used);
        return res;
    }

    void backtrace(int[] nums, LinkedList<Integer> track, boolean[] used){
        int len = nums.length;
        if(track.size() == len){
            res.add(new LinkedList<>(track));
            return;
        }

        int pre = nums[0] - 1;   //不同于数组中所有数
        for(int i = 0; i < len; i++){
            if(used[i] || pre == nums[i])
                continue;

            track.add(nums[i]);
            used[i] = true;

            backtrace(nums, track, used);

            track.removeLast();
            used[i] = false;
            pre = nums[i];   //记录刚被撤销的数字
        }
    }
}
