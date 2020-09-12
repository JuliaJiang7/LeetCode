import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author julia.jiang
 * @date 2020/8/24 9:08
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution90 {

    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        Arrays.sort(nums);
        backtrack(nums, track, 0);
        return res;
    }


    private void backtrack(int[] nums, LinkedList<Integer> track, int start) {
        res.add(new LinkedList<>(track));

        for(int i = start; i < nums.length; i++){
            if(i == start || nums[i] != nums[i - 1]){
                track.add(nums[i]);
                backtrack(nums, track, i + 1);
                track.removeLast();
            }
        }
    }
}
