import java.util.ArrayList;
import java.util.List;

/**
 * @author julia.jiang
 * @date 2020/9/9 14:20
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution118 {
    /**
     * 自己写的代码，不优雅
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows == 0){return res;}
        List<Integer> tmp = new ArrayList<>();
        tmp.add(1);
        res.add(tmp);
        if(numRows == 1){return res;}
        List<Integer> tmp3 = new ArrayList<>();
        tmp3.add(1);
        tmp3.add(1);
        res.add(tmp3);
        if(numRows == 2){return res;}
        // 上一行相加
        for(int i = 2; i < numRows; i++){
            List<Integer> tmp2 = new ArrayList<>();
            tmp2.add(1);
            for(int j = 1; j < i; j++){
                tmp2.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
            }
            tmp2.add(1);
            res.add(tmp2);
        }
        return res;
    }

    /**
     * https://leetcode.wang/leetcode-118-Pascal%27s-Triangle.html
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < numRows; i++){
            List<Integer> tmp = new ArrayList<>();
            for(int j = 0; j <= i; j++){
                if(j == 0 || j == i){
                    tmp.add(1);
                }else{
                    tmp.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(tmp);
        }
        return res;
    }

}
