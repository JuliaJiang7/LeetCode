import java.util.*;
import java.util.function.Function;

/**
 * @author Julia Jiang
 * @date 2020/2/21 14:37
 * @description
 */
public class Test {
    public static void change(int a) {
        a = 50;
    }

    public static void change2(int[] a) {
        a[0] = 50;
    }


    void foo(String s) {
        s = "windows";
        System.out.println(s);
        s.equals("");
        s.compareTo("");
        StringBuilder s1 = new StringBuilder();
        StringBuffer s2 = new StringBuffer();
    }



    public void listAdd(List<Integer> list){
        System.out.println(list);
        list.add(1);
        System.out.println(list);

    }



    public static String upcase(String s) {
        return s.toUpperCase();
    }



    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        // base case
        dp[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int max = dp[0];
        for(int i = 0; i < nums.length; i++){
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        //dp[i-1][j-1] 表示：i * j 的网格上机器人的移动步数
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            if(obstacleGrid[i][0] == 1){
                break;
            }
            dp[i][0] = 1;
        }
        for(int i = 1; i < n; i++){
            if(obstacleGrid[0][i] == 1){
                break;
            }
            dp[0][i] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(obstacleGrid[i - 1][j] == 1 && obstacleGrid[i][j - 1] == 1){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = leftBound(nums, target);
        res[1] = rightBound(nums, target);
        return res;
    }

    /**
     * 找左侧边界
     * 用 left 保存找到的左侧边界
     * @param nums
     * @param target
     * @return
     */
    private int leftBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // 搜索区间为 [left, right]
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > target){
                // 搜索区间变为 [left, mid-1]
                right = mid - 1;
            }else if(nums[mid] < target){
                // 搜索区间变为 [mid+1, right]
                left = mid + 1;
            }else if(nums[mid] == target){
                // 不返回，收缩右侧边界，限制左侧边界left的取值范围
                right = mid - 1;
            }
        }
        if(left == nums.length || nums[left] != target){
            return -1;
        }
        return left;
    }

    /**
     * 找右侧边界
     * 用 right 保存找到的右侧边界
     * @param nums
     * @param target
     * @return
     */
    private int rightBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] == target){
                // 改变一：收缩左侧边界，限制右侧边界right的取值范围
                left = mid + 1;
            }
        }
        // 改变二：检查right的越界情况
        if(right < 0 || nums[right] != target){
            return -1;
        }
        return right;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()){
            while (curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

    public List<Integer> postorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        // 记录上一个遍历的节点
        TreeNode pre = null;
        while (curr != null || !stack.isEmpty()){
            while (curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            // 获取当前根节点
            TreeNode temp = stack.peek();
            // 是否变到右子树
            if(temp.right != null && temp.right != pre){
                curr = temp.right;
            }else{
                res.add(temp.val);
                // 记录上一个遍历的节点
                pre = temp;
                stack.pop();
            }
        }
        return res;
    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            // 当前层列表
            List<Integer> level = new ArrayList<>();

            // 当前队列中的元素个数，即就是当前层的节点数
            int levelNum = queue.size();
            // levelNum 必须在外面定义，因为 queue 的大小在不停的变化
            for(int i = 0; i < levelNum; i++){
                // 移除队列第一个元素
                TreeNode node = queue.remove();
                level.add(node.val);
                if(node.left != null){queue.add(node.left);}
                if(node.right != null){queue.add(node.right);}
            }
            res.add(level);
        }
        return res;
    }

}
