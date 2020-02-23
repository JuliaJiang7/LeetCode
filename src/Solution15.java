import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Julia Jiang
 * @date 2020/2/17 12:36
 * @description
 */
public class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if(nums == null || len < 3) return ans;
        Arrays.sort(nums);

        for(int i = 0; i < len; i++){
            if(nums[i] > 0) break;
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int L = i+1;
            int R = len-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while(L<R && nums[L] == nums[L+1])  L++;
                    while(L<R && nums[R] == nums[R-1])  R--;
                    L++;
                    R--;
                }
                else if(sum < 0) L++;
                else if(sum > 0) R--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        Solution15 solution15 = new Solution15();
        System.out.println(solution15.threeSum(nums));
    }
}
