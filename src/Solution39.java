import java.util.*;

/**
 * @author Julia Jiang
 * @date 2020/2/16 10:56
 * @description 组合数
 */
public class Solution39 {

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        Solution39 solution = new Solution39();
        List<List<Integer>> combinationSum = solution.combinationSum(candidates, target);
        System.out.println(combinationSum);
    }

    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target){
        if(candidates.length == 0){
            return res;
        }

        // 优化添加的代码1：先对数组排序，可以提前终止判断
        Arrays.sort(candidates);
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(candidates, track, target, 0);
        return res;
    }

    /**
     *
     * @param candidates
     * @param track 记录路径
     * @param residue 剩余
     * @param start 下一次从 start 开始判断是否添加到路径
     */
    private void backtrack(int[] candidates, LinkedList<Integer> track, int residue, int start){
        //触发结束条件
        if(residue == 0){
            res.add(new LinkedList<>(track));
            return;
        }

        // i 从 start 开始，剪掉小于 candidates[i] 的枝
        for(int i = start; i < candidates.length; i++){
            // 排除不合法的选择
            if(residue - candidates[i] < 0){
                break;
            }

            // 做选择
            track.add(candidates[i]);
            // 进入下一层决策树
            // residue-candidates[i] 为下一轮剩余
            // i 为下一轮的 start值，原因：为了避免重复，选择的数字只能大于等于candidates[i]
            backtrack(candidates, track, residue-candidates[i], i);
            // 撤销选择
            track.removeLast();
        }
    }
}
