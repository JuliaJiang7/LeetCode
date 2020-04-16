import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Julia Jiang
 * @date 2020/2/21 12:39
 * @description 全排列（回溯算法经典例题）
 * 排除不合法选择有两种方法：
 *      第一，track.contains，适用于nums 数组中没有重复元素
 *      第二，使用 used 数组，有没有重复元素都可
 */
public class Solution46 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Solution46 solution = new Solution46();
        List<List<Integer>> list = solution.permute(nums);
        System.out.println(list);

        List<List<Integer>> list2 = solution.permute2(nums);
        System.out.println(list2);
    }

    /**
     * 第一种方法
     */
    private List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums){
        //记录路径
        LinkedList<Integer> track = new LinkedList<>();
        backtrace(nums, track);
        return res;
    }

    void backtrace(int[] nums, LinkedList<Integer> track){
        //触发结束条件
        if(track.size() == nums.length){
            res.add(new LinkedList<>(track));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            //排除不合法的选择
            if(track.contains(nums[i])){
                continue;
            }
            //做选择
            track.add(nums[i]);
            //进入下一层决策树
            backtrace(nums, track);
            //取消选择
            track.removeLast();
        }
    }

    /**
     * 第二种方法
     */
    List<List<Integer>> res2 = new LinkedList<>();
    public List<List<Integer>> permute2(int[] nums){
        int len = nums.length;
        if(len == 0)    return res;

        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[len];
        backtrace2(nums, track, used);
        return res2;
    }

    void backtrace2(int[] nums, LinkedList<Integer> track, boolean[] used){
        int len = nums.length;
        if(track.size() == len){
            res2.add(new LinkedList<>(track));
            return;
        }

        for(int i = 0; i < len; i++){
            if(used[i])
                continue;

            track.add(nums[i]);
            used[i] = true;

            backtrace2(nums, track, used);

            track.removeLast();
            used[i] = false;
        }
    }
}
