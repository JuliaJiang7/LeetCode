import java.util.Arrays;

/**
 * @author Julia Jiang
 * @date 2020/2/18 13:44
 * @description
 */
public class Solution31 {
    public void nextPermutation(int[] nums){
        //"小数"
        int i = nums.length - 2;
        while (i >= 0 && nums[i+1] <= nums[i])
            i--;
        if(i >= 0){
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]){
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i+1);
    }

    private void reverse(int[] nums, int start){
        int i = start;
        int j = nums.length - 1;
        while (i < j){
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Solution31 solution31 = new Solution31();
        solution31.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
