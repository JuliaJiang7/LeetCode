import java.util.*;

/**
 * @author Julia Jiang
 * @date 2020/2/21 12:04
 * @description 组合数 2
 * 类比于 46、47 全排列、全排列2
 */
public class Solution40 {

    public static void main(String[] args) {
        int[] candidates = {2,5,2,1,2};
        int target = 5;
        Solution40 solution = new Solution40();
        List<List<Integer>> combinationSum = solution.combinationSum2(candidates, target);
        System.out.println(combinationSum);
    }

    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target){
        if(candidates.length == 0)  return res;

        // 优化添加的代码1：先对数组排序，可以提前终止判断
        Arrays.sort(candidates);
        LinkedList<Integer> track = new LinkedList<>();
        backtrace(candidates, track, target, 0);
        return res;
    }

    /**
     *
     * @param candidates
     * @param track 记录路径
     * @param residue 剩余
     * @param start 下一次从 start 开始判断是否添加到路径
     */
    void backtrace(int[] candidates, LinkedList<Integer> track, int residue, int start){
        //触发结束条件
        if(residue == 0){
            res.add(new LinkedList<>(track));
            return;
        }

        int pre = candidates[0] - 1;
        //i 从 start 开始，剪掉小于 candidates[i] 的枝
        for(int i = start; i < candidates.length; i++){
            //排除不合法的选择
            if(residue - candidates[i] < 0) break;

            if(candidates[i] == pre)    continue;
            //if(i > start && candidates[i] == candidates[i-1])   continue;


            //做选择
            track.add(candidates[i]);
            //进入下一层决策树，residue-candidates[i] 为下一轮剩余
            backtrace(candidates, track, residue-candidates[i], i+1);
            //撤销选择
            track.removeLast();
            pre = candidates[i];
        }
    }
}
