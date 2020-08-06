import java.util.HashMap;
import java.util.Map;

/**
 * @author julia.jiang
 * @date 2020/7/23 8:55
 * @email julia.jiang.fan@gmail.com
 * @description 最小覆盖子串
 */
public class Solution76 {

    /**
     * 解法一：滑动窗口
     * 参考：https://leetcode.wang/leetCode-76-Minimum-Window-Substring.html#%E8%A7%A3%E6%B3%95%E4%B8%80-%E6%BB%91%E5%8A%A8%E7%AA%97%E5%8F%A3
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        // 存储t中的字符与个数映射
        Map<Character, Integer> map = new HashMap<>();
        // 遍历t,初始化每个字符的此时
        for(int i = 0; i < t.length(); i++){
            char char_i = t.charAt(i);
            map.put(char_i, map.getOrDefault(char_i, 0) + 1);
        }
        // 左指针，右指针
        int left = 0, right = 0;
        // 最小窗口的左右边界
        int ans_left = 0;
        int ans_right = -1;
        // 最小窗口的长度
        int ans_len = Integer.MAX_VALUE;
        // 遍历字符串s
        while (right < s.length()){
            char char_right = s.charAt(right);
            // 判断map中是否含有该字符
            if(map.containsKey(char_right)){
                // 当前的字母次数减一
                map.put(char_right, map.get(char_right) - 1);
                // 判断map的所有value是否都为0，即s中是否包含t中的所有字符
                // 如果全部包含，进入循环
                while (match(map)){
                    // 当前窗口大小
                    int temp_len = right - left + 1;
                    // 如果当前窗口更小，则更新相应变量
                    if(temp_len < ans_len){
                        ans_len = temp_len;
                        ans_left = left;
                        ans_right = right;
                    }

                    // 左指针右移，并修改map取值
                    char key = s.charAt(left);
                    if(map.containsKey(key)){
                        map.put(key, map.get(key) + 1);
                    }
                    left++;
                }
            }
            // 右指针扩大窗口
            right++;
        }
        return s.substring(ans_left, ans_right + 1);
    }

    /**
     * 判断map的所有value是否都为0
     * @param map
     * @return
     */
    private boolean match(Map<Character, Integer> map){
        for(Integer value : map.values()){
            if(value > 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 解法二：对解法一的优化
     * 优化1：使用数组存储字符与次数映射
     * 优化2：定义计数变量count，初始化为t的长度，表示当前字符串s中不包含t中字符的个数，每匹配一个字母，count--；当count==0时，表示完全匹配
     *        这样的话，可以把之前的 match(map) 优化到 O（1）
     * @param s
     * @param t
     * @return
     */
    public String minWindow2(String s, String t) {
        // 存储数组
        int[] map = new int[128];
        for(int i = 0; i < t.length(); i++){
            char char_i = t.charAt(i);
            map[char_i]++;
        }
        int left = 0, right = 0;
        int ans_left = 0;
        int ans_right = -1;
        int ans_len = Integer.MAX_VALUE;
        // 计数变量，当前字符串s中不包含t中字符的个数，每满足一个，count--
        int count = t.length();
        // 遍历字符串s
        while (right < s.length()){
            char char_right = s.charAt(right);
            // 当前字母次数减一
            map[char_right]--;
            // 如果当前字母在t中
            if(map[char_right] >= 0){
                count--;
            }
            // 如果s中包含t中的所有字母
            while (count == 0){
                int temp_len = right - left + 1;
                if(temp_len < ans_len){
                    ans_len = temp_len;
                    ans_left = left;
                    ans_right = right;
                }
                char key = s.charAt(left);
                map[key]++;
                if(map[key] > 0){
                    count++;
                }
                left++;
            }
            // 右指针扩大窗口
            right++;
        }
        return s.substring(ans_left, ans_right + 1);
    }
}
