/**
 * @author Julia Jiang
 * @date 2020/3/18 10:46
 * @description 寻找两个有序数组的中位数
 */
public class Solution4 {
    /**
     * 解法一：简单粗暴
     * 将两个数组按大小顺序合并为一个，然后根据奇偶返回中位数
     * 时复 O(m + n)
     * 空复 O(m + n)
     * @param nums1
     * @param nums2
     * @return 注意这里返回的是 double
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] nums = new int[m + n];
        if(m == 0){
            if(n % 2 == 0){
                //必须除以 2.0 因为 5 / 2 = 2, 5 / 2.0 = 2.5
                return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
            }else{
                return nums2[n / 2];
            }
        }
        if(n == 0){
            if(m % 2 == 0){
                return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
            }else{
                return nums1[m / 2];
            }
        }
        int count = 0;
        int i = 0, j = 0;
        while (count != (m + n)){
            if(i == m){
                while (j != n){
                    nums[count++] = nums2[n++];
                }
                break;
            }
            if(j == n){
                while (i != m){
                    nums[count++] = nums1[m++];
                }
                break;
            }
            if(nums1[i] < nums2[j]){
                nums[count++] = nums1[i++];
            }else{
                nums[count++] = nums2[j++];
            }
        }
        if(count % 2 == 0){
            return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
        }else{
            return nums[count / 2] / 2.0;
        }
    }

    /**
     * 解法二：不需要真正合并两个数组，只需要找到中位数的位置即可
     * 时复 O(m + n)
     * 空复 O(1)
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2){
        int i = 0, j = 0;
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int left = -1, right = -1;
        for(int count = 0; count <= len / 2; count++){
            int curr;
            // nums1[i] < nums2[j] 这个条件必须放在最后一个，因为如果索引越界，就不会执行到最后一个条件
            if(i < m && (j >= n || nums1[i] < nums2[j])){
                curr = nums1[i];
                i++;
            }else{
                curr = nums2[j];
                j++;
            }
            //先不管 len 的奇偶性，先把左右两个数存下来
            if(count == len / 2 - 1){
                left = curr;
            }
            if(count == len / 2){
                right = curr;
            }
        }
        if(len % 2 == 0){
            return (left + right) / 2.0;
        }else{
            return right;
        }
    }

    /**
     * 解法三：当做求第 K 小数（想不到.. 好难）
     * https://leetcode.wang/leetCode-4-Median-of-Two-Sorted-Arrays.html
     * 时复 O(log(m + n))
     * 空复 O(1) 虽然我们用到了递归，但是可以看到这个递归属于尾递归，所以编译器不需要不停地堆栈，所以空间复杂度为 O（1）。
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays3(int[] nums1, int[] nums2){
        int n= nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) / 2.0;
    }

    private int getKth(int[] num1, int start1, int end1, int[] num2, int start2, int end2, int k){
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //保证 num1 的长度始终大于 num2, 这样如果数组空了，那么一定是 num1 空了
        if(len1 > len2)
            return getKth(num2, start2, end2, num1, start1, end1, k);
        if(len1 == 0)
            return num2[start2 + k - 1];

        if(k == 1)
            return Math.min(num1[start1], num2[start2]);

        int i = start1 + Math.min(k / 2, len1) - 1;
        int j = start2 + Math.min(k / 2, len2) - 1;
        if(num1[i] > num2[j]){
            return getKth(num1, start1, end1, num2, j + 1, end2, k - (j - start2 + 1));
        }else{
            return getKth(num1, i + 1, end1, num2, start2, end2, k - (i - start1 + 1));
        }
    }
}
