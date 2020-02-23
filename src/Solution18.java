import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Julia Jiang
 * @date 2020/2/17 12:40
 * @description 四数之和
 */
public class Solution18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if(nums == null || len < 4) return ans;
        Arrays.sort(nums);

        int a, b, c, d;
        for(a = 0; a <= len - 4; a++){
            if(a > 0 && nums[a] == nums[a-1])   continue;
            for(b = a+1; b <= len - 3; b++){
                if(b > a+1 && nums[b] == nums[b-1]) continue;
                c = b+1;
                d = len-1;
                while (c < d){
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if(sum < target)
                        c++;
                    else if(sum > target)
                        d--;
                    else{
                        ans.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        while (c<d && nums[c+1]==nums[c])   c++;
                        while (c<d && nums[d-1]==nums[d])   d--;
                        c++;
                        d--;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        Solution18 solution18 = new Solution18();
        System.out.println(solution18.fourSum(nums, 0));
    }
}
