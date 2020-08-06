import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author julia.jiang
 * @date 2020/7/24 9:22
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution77 {

    List<List<Integer>> res = new LinkedList<>();

    /**
     * 解法一：回溯法
     * 回溯的常规解法
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(track, n, k, 1);
        return res;
    }

    private void backtrack(LinkedList<Integer> track, int n, int k, int start){
        // 如果满足条件
        if(track.size() == k){
            res.add(new LinkedList<>(track));
            return;
        }

        // 为避免重复，i 从 start 开始
        for(int i = start; i <= n; i++){
            // 做选择
            track.add(i);
            backtrack(track, n, k, i+1);
            // 撤销选择
            track.removeLast();
        }
    }

    /**
     * 解法一（回溯法）的优化
     * 如果n=5,k=3, 那么start最大取到3，后面的数字无须遍历，因为如果当start=4时，只能取到（4,5），组合中数字的个数不足
     * @param track
     * @param n
     * @param k
     * @param start
     */
    private void backtrack2(LinkedList<Integer> track, int n, int k, int start){
        if(track.size() == k){
            res.add(new LinkedList<>(track));
            return;
        }

        // (n-k)+1 表示要在n中找到k个数字组合，start必须从(n-k)+1处开始
        // 即如果n=5,k=3, 那么start最大取到3，因为如果当start=4时，组合中数字的个数不足
        // (n-k)+1 + track.size() 表示当前i取到的最大数字
        for(int i = start; i <= (n-k)+1 + track.size(); i++){
            track.add(i);
            backtrack(track, n, k, i+1);
            track.removeLast();
        }
    }
}
