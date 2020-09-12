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


    public void listAdd(List<Integer> list) {
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
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int max = dp[0];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        //dp[i-1][j-1] 表示：i * j 的网格上机器人的移动步数
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i - 1][j] == 1 && obstacleGrid[i][j - 1] == 1) {
                    dp[i][j] = 0;
                } else {
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
     *
     * @param nums
     * @param target
     * @return
     */
    private int leftBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // 搜索区间为 [left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                // 搜索区间变为 [left, mid-1]
                right = mid - 1;
            } else if (nums[mid] < target) {
                // 搜索区间变为 [mid+1, right]
                left = mid + 1;
            } else if (nums[mid] == target) {
                // 不返回，收缩右侧边界，限制左侧边界left的取值范围
                right = mid - 1;
            }
        }
        if (left == nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    /**
     * 找右侧边界
     * 用 right 保存找到的右侧边界
     *
     * @param nums
     * @param target
     * @return
     */
    private int rightBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                // 改变一：收缩左侧边界，限制右侧边界right的取值范围
                left = mid + 1;
            }
        }
        // 改变二：检查right的越界情况
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        // 记录上一个遍历的节点
        TreeNode pre = null;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            // 获取当前根节点
            TreeNode temp = stack.peek();
            // 是否变到右子树
            if (temp.right != null && temp.right != pre) {
                curr = temp.right;
            } else {
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
        while (!queue.isEmpty()) {
            // 当前层列表
            List<Integer> level = new ArrayList<>();

            // 当前队列中的元素个数，即就是当前层的节点数
            int levelNum = queue.size();
            // levelNum 必须在外面定义，因为 queue 的大小在不停的变化
            for (int i = 0; i < levelNum; i++) {
                // 移除队列第一个元素
                TreeNode node = queue.remove();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }


    public int trap(int[] height) {
        int sum = 0;
        int len = height.length;
        if (len == 0) {
            return 0;
        }

        // max_left[i] = x 表示：height[0..i] 中最大值为x
        int max_left[] = new int[len];
        // max_right[i] = x 表示：height[i..(len-1)] 中最大值为x
        int max_right[] = new int[len];

        // base case
        max_left[0] = height[0];
        max_right[len - 1] = height[len - 1];

        for (int i = 1; i < len; i++) {
            max_left[i] = Math.max(height[i], max_left[i - 1]);
        }
        for (int j = len - 2; j > 0; j--) {
            max_right[j] = Math.max(height[j], max_right[j + 1]);
        }

        for (int i = 1; i < len - 1; i++) {
            sum += Math.min(max_left[i], max_right[i]) - height[i];
        }
        return sum;
    }


    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public int maxDepth(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return level;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }

    public int search(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                if (nums[right] < target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] > target) {
                if (nums[left] <= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int search2(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            // 左半段有序
            if (nums[left] <= nums[mid]) {
                // target 在左半段
                // 注意等号
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // target 在右半段
                // 注意等号
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    private boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public int longestValidParentheses(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < len; i++) {
            // 注意：substring 是左闭右开，所以这里j的取值小于等于len
            for (int j = i + 2; j <= len; j += 2) {
                if (isValid(s.substring(i, j))) {
                    max = Math.max(max, j - i);
                }
            }
        }
        return max;
    }

    public int longestValidParentheses2(String s) {
        int count = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            count = 0;
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == '(') {
                    count++;
                } else {
                    count--;
                }

                if (count < 0) {
                    break;
                }

                if (count == 0) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    public int longestValidParentheses3(String s) {
        int len = s.length();
        int max = 0;
        // dp[i]=x 表示：以s[i]结尾的子串最长有效长度为x
        int[] dp = new int[len];
        for (int i = 1; i < len; i++) {
            // 如果遇到左括号，dp[i]=0，就是初始值
            // 遇到右括号时，才会更新dp数组
            if (s.charAt(i) == ')') {
                // 右括号的前一位是左括号
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i > 2 ? dp[i - 2] : 0) + 2;

                    // 右括号前一位是右括号
                    // 并且除去前边的合法序列的前一位是左括号
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) > 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }
        return helper2(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode helper2(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd, HashMap<Integer, Integer> map) {
        if(pEnd < pStart){return null;}

        int root = preorder[pStart];
        TreeNode node = new TreeNode(root);

        // 使用map找分隔点
        int point = map.get(root);

        int leftLen = point - iStart;

        node.left = helper2(preorder, pStart + 1, pStart + leftLen, inorder, iStart, point - 1, map);
        node.right = helper2(preorder, pStart + leftLen + 1, pEnd, inorder, point + 1, iEnd, map);

        return node;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0){return false;}
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = m * n;
        while (left <= right){
            int mid = left + (right - left) / 2;
            int i = mid / n;
            int j = mid % n;
            int value = matrix[i][j];
            if(target == value){
                return true;
            }else if(target < value){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return false;
    }



    public int maxSubArray2(int[] nums) {
        int len = nums.length;

        // dp[i] = x 表示：以 nums[i] 结尾的子序列最大和
        int[] dp = new int[len];
        // base case
        dp[0] = nums[0];

        for(int i = 1; i < len; i++){
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int max = dp[0];
        for(int j = 1; j < len; j++){
            max = Math.max(max, dp[j]);
        }

        return max;
    }

    public boolean isValidBST(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)){
            return true;
        }

        if(isValidBST(root.left)){
            if(root.left != null){
                int maxLeft = getMaxOfBST(root.left);
                if(root.val <= maxLeft){
                    return false;
                }
            }
        }else{
            return false;
        }

        if(isValidBST(root.right)){
            if(root.right != null){
                int minRight = getMinOfBST(root.right);
                if(root.val >= minRight){
                    return false;
                }
            }
        }else{
            return false;
        }
        return true;
    }

    private int getMinOfBST(TreeNode root) {
        int min = root.val;
        while (root.left != null){
            min = root.left.val;
            root = root.left;
        }
        return min;
    }

    private int getMaxOfBST(TreeNode root) {
        int max = root.val;
        while (root.right != null){
            max = root.right.val;
            root = root.right;
        }
        return max;
    }


    public boolean isValidBST2(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)){
            return true;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode curr = root;
        TreeNode pre = null;
        while (!stack.isEmpty() || curr != null){
            while (curr.left != null){
                stack.push(curr.left);
                curr = curr.left;
            }
            curr = stack.pop();
            if(pre != null && curr.val <= pre.val){
                return false;
            }
            pre = curr;
            curr = curr.right;
        }
        return true;
    }

    /**
     * 解法二：（递归）从根节点出发，来限制左右节点的取值范围（执行时间快）
     * 实际使用树的 DFS，也就是二叉树的先序遍历，在遍历过程中判断当前值是否在取值区间内
     * 参考：https://leetcode.wang/leetCode-98-Validate-Binary-Search-Tree.html
     * @param root
     * @return
     */
    public boolean isValidBST3(TreeNode root) {
        // 节点值均为 Int 型，故最大值设为 long
        long max = (long) Integer.MAX_VALUE + 1;
        long min = (long) Integer.MIN_VALUE - 1;
        return getAns(root, max, min);
    }

    private boolean getAns(TreeNode root, long max, long min) {
        if(root == null){
            return true;
        }

        if(root.val <= min || root.val >= max){
            return false;
        }

        return getAns(root.left, min, root.val) && getAns(root.right, root.val, max);
    }


    public int minPathSum(int[][] grid) {
        int row = grid.length;
        if(row == 0){
            return 0;
        }
        int col = grid[0].length;

        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        for(int j = 1; j < col; j++){
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for(int i = 1; i < row; i++){
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for(int i = 1; i < row; i++){
            for(int j = 1; j < col; j++){
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ju").append("lia").toString();
        System.out.println(str2.intern() == str2);

        String str3 = new StringBuilder("ju").append("lia").toString();
        System.out.println(str3.intern() == str3);
    }


    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = inorder.length;
        if(len == 0){return null;}
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++){
            map.put(inorder[i], i);
        }
        return helper(inorder, 0, len, postorder, 0, len, map);
    }

    private TreeNode helper(int[] inorder, int iStart, int iEnd, int[] postorder, int pStart, int pEnd, Map<Integer, Integer> map) {
        if(iStart == iEnd){return null;}
        TreeNode root = new TreeNode(postorder[pEnd - 1]);

        int rootIndex = map.get(postorder[pEnd - 1]);

        int leftLen = rootIndex - iStart;
        root.left = helper(inorder, iStart, rootIndex , postorder, pStart, pStart + leftLen, map);
        root.right = helper(inorder, rootIndex + 1, iEnd, postorder, pStart + leftLen, pEnd - 1, map);
        return root;
    }

    public int intervalSchedule(int[][] intvs) {
        if(intvs.length == 0){return 0;}
        // 按 end 排序
        Arrays.sort(intvs, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });

        // 初始时，至少有一个区间不相交
        int count = 1;
        int end = intvs[0][1];

        for(int i = 1; i < intvs.length; i++){
            if(intvs[i][0] >= end){
                count++;
                end = intvs[i][1];
            }
        }

        return count;
    }

    public int calculate(String s) {
        int len = s.length();
        int x = 1, y = 0;
        for(int i = 0; i < len; i++){
            char c = s.charAt(i);
            if(c == 'A'){
                x = 2 * x + y;
            }
            if(c == 'B'){
                y = 2 * y + x;
            }
        }
        return x+y;
    }

    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        int count = 0;
        Arrays.sort(staple);
        Arrays.sort(drinks);
        for (int i = 0; i < staple.length && staple[i] < x; i++){
            for(int j = 0; j < drinks.length && drinks[j] < x; j++){
                if(staple[i] + drinks[j] <= x){
                    count++;
                }else{
                    break;
                }
            }
        }
        return count % 1000000008;
    }
}
