import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Julia Jiang
 * @date 2020/2/17 11:59
 * @description
 */
public class Solution17 {
    private Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    private List<String> res = new ArrayList<String>();

    /**
     * 回溯函数
     * @param track 记录路径，即最终的组合
     * @param next_digits 接下来选择的数字
     */
    private void backtrack(String track, String next_digits) {
        // 触发结束条件：没有需要选择的数字
        if (next_digits.length() == 0) {
            // the combination is done
            res.add(track);
            return;
        }

        String digit = next_digits.substring(0, 1);
        String letters = phone.get(digit);
        for (int i = 0; i < letters.length(); i++) {
            String letter = phone.get(digit).substring(i, i + 1);
            // 把当前的字母添加到 路径 后面
            // substring(1) 子串取下标 1 到结尾
            backtrack(track + letter, next_digits.substring(1));
        }
    }

    public List<String> letterCombinations(String digits) {
        if (digits.length() != 0)
            backtrack("", digits);
        return res;
    }

    public static void main(String[] args) {
        String str = "23";
        Solution17 solution17 = new Solution17();
        List<String> res = solution17.letterCombinations(str);
        System.out.println(res);
    }
}
