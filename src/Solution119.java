import java.util.ArrayList;
import java.util.List;

/**
 * @author julia.jiang
 * @date 2020/9/9 14:54
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution119 {
    /**
     * 解法一：只保存上一层结果
     * https://leetcode.wang/leetcode-119-Pascal%27s-TriangleII.html
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        for(int i = 0; i <= rowIndex; i++){
            cur = new ArrayList<>();
            for(int j = 0; j <= i; j++){
                if(j == 0 || j == i){
                    cur.add(1);
                }else{
                    cur.add(pre.get(j - 1) + pre.get(j));
                }
            }
            pre = cur;
        }
        return cur;
    }

    /**
     * 解法二：存储前一个 pre （不推荐，不好理解）
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow2(int rowIndex) {
        int pre = 1;
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = 1; j < i; j++) {
                int temp = cur.get(j);
                cur.set(j, pre + cur.get(j));
                pre = temp;
            }
            cur.add(1);
        }
        return cur;
    }

    /**
     * 解法三：倒着相加，不存在覆盖的问题，更好理解，推荐
     * https://leetcode.wang/leetcode-119-Pascal%27s-TriangleII.html
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow3(int rowIndex) {
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for(int i = 1; i <= rowIndex; i++){
            for(int j = i - 1; j > 0; j--){
                cur.set(j, cur.get(j) + cur.get(j - 1));
            }
            // 最后一位添加 1
            cur.add(1);
        }
        return cur;
    }

    public static void main(String[] args) {
        Solution119 solution119 = new Solution119();
        solution119.getRow2(4);
    }

}
