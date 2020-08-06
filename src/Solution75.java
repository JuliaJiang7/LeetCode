/**
 * @author julia.jiang
 * @date 2020/7/22 9:12
 * @email julia.jiang.fan@gmail.com
 * @description 颜色分类
 */
public class Solution75 {

    /**
     * 解法一：记录0和1出现的次数
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 遍历两次数组
     * @param nums
     */
    public void sortColors(int[] nums) {
        int countZero = 0;
        int countOne = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                countZero++;
            }
            if(nums[i] == 1){
                countOne++;
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(countZero > 0){
                nums[i] = 0;
                countZero--;
            }else if(countOne > 0){
                nums[i] = 1;
                countOne--;
            }else{
                nums[i] = 2;
            }
        }
    }

    /**
     * 解法二：使用两个指针标记
     * positionZero表示在该位置之前全部为0，positionTwo在该位置之后全部为2
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 遍历一次数组
     * 参考：https://leetcode.wang/leetCode-75-Sort-Colors.html#%E8%A7%A3%E6%B3%95%E4%BA%8C
     * @param nums
     */
    public void sortColors2(int[] nums) {
        // positionZero之前都是0
        int positionZero = 0;
        // positionTwo之后都是2
        int positionTwo = nums.length - 1;
        // i 遍历到 positionTwo 之后时，整个排序已经完成
        for(int i = 0; i <= positionTwo; i++){
            if(nums[i] == 0){
                // 交换positionZero和num[i]
                int tmp = nums[positionZero];
                nums[positionZero] = 0;
                nums[i] = tmp;
                // positionZero后移一位
                positionZero++;
            }
            if(nums[i] == 2){
                // 交换positionTwo和nums[i]
                int tmp = nums[positionTwo];
                nums[positionTwo] = 2;
                nums[i] = tmp;
                // positionTwo前移一位
                positionTwo--;
                // 这里要注意，因为我们把后面的数字换到了第i个位置
                // 这个数字还没有判断，外层的for循环i++会跳过这个位置
                // 所以这里需要 i--
                // 而上面positionZero的更新不用i--,因为是从前边换过来的数字，在之前已经判断过了
                i--;
            }
        }
    }

    /**
     * 解法三：大问题化小问题
     * 参考：https://leetcode.wang/leetCode-75-Sort-Colors.html#%E8%A7%A3%E6%B3%95%E4%B8%89
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     */
    public void sortColors3(int[] nums) {
        int n0 = -1, n1 = -1, n2 = -1;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                n2++;
                nums[n2] = 2;
                n1++;
                nums[n1] = 1;
                n0++;
                nums[n0] = 0;
            } else if (nums[i] == 1) {
                n2++;
                nums[n2] = 2;
                n1++;
                nums[n1] = 1;
            } else if (nums[i] == 2) {
                n2++;
                nums[n2] = 2;
            }
        }
    }

}
