/**
 * @author julia.jiang
 * @date 2020/7/24 9:37
 * @email julia.jiang.fan@gmail.com
 * @description 合并两个有序数组
 */
public class Solution88 {

    /**
     * 解法一：直接法
     * 在nums1中找nums2的插入位置，找到后nums1后移出位置，将nums2放入
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * 参考：https://leetcode.wang/leetCode-88-Merge-Sorted-Array.html
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0;
        // 遍历nums
        while (j < n) {
            // 判断num1是否遍历完，如果遍历完，将剩余的num2插入
            if (i == m + j) {
                while (j < n) {
                    nums1[m + j] = nums2[j];
                    j++;
                }
                return;
            }
            if (nums2[j] < nums1[i]) {
                // num1后移数组，空出位置来插入
                for (int k = m + j; k > i; k--) {
                    nums1[k] = nums1[k - 1];
                }
                nums1[i] = nums2[j];
                i++;
                j++;
            } else {
                i++;
            }
        }
    }

    /**
     * 解法二：使用归并排序的思想
     * 两个有序数组的合并，其实就是归并排序中的一个步骤。
     * 在归并排序中，是新开辟一个和 nums1 + nums2 等大的空间，然后用两个指针遍历 nums1 和 nums2，依次选择小的把它放到新的数组中。
     * 这道题中，nums1 其实就是我们最后合并好的大数组，
     * 但是如果 nums1 当作上述新开辟的空间，那原来的 nums1 的数字放到哪里呢？放到 nums1 的末尾。
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 参考：https://leetcode.wang/leetCode-88-Merge-Sorted-Array.html#%E8%A7%A3%E6%B3%95%E4%BA%8C
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        // 将nums1的数字全部移到末尾
        for (int count = 1; count <= m; count++) {
            nums1[m + n - count] = nums1[m - count];
        }

        int i = n, j = 0;
        int k = 0;

        // 遍历nums2
        while (j < n) {
            // 如果nums1遍历结束，将nums直接加入
            if (i == m + n) {
                while (j < n) {
                    nums1[k++] = nums2[j++];
                }
                return;
            }
            // 哪个数小就添加哪个数
            if (nums2[j] < nums1[i]) {
                nums1[k] = nums2[j];
                j++;
            } else {
                nums1[k] = nums1[i];
                i++;
            }
            k++;
        }
    }

    /**
     * 解法三：从后往前，由大到小
     * 神仙操作
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 参考：https://leetcode.wang/leetCode-88-Merge-Sorted-Array.html#%E8%A7%A3%E6%B3%95%E4%B8%89
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (j >= 0){
            // 判断num1是否结束
            if(i < 0){
                while (j >= 0){
                    nums1[k--] = nums2[j--];
                }
                return;
            }
            if(nums1[i] > nums2[j]){
                nums1[k--] = nums1[i--];
            }else{
                nums1[k--] = nums2[j--];
            }
        }
    }
}
