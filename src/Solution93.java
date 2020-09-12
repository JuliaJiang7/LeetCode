import java.util.ArrayList;
import java.util.List;

/**
 * @author julia.jiang
 * @date 2020/8/27 9:34
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution93 {

    /**
     * 解法一：暴力破解
     * https://leetcode.wang/leetCode-93-Restore-IP-Addresses.html
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        // i < 4 保证第一部分不超过3位数
        // i < len - 2 保证剩余的字符串还能分成3部分
        for (int i = 1; i < 4 && i < len - 2; i++) {
            for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
                for (int k = j + 1; k < j + 4 && k < len; k++) {
                    // 保存四部分字符串
                    String s1 = s.substring(0, i), s2 = s.substring(i, j), s3 = s.substring(j, k), s4 = s.substring(k, len);
                    // 判断是否合法
                    if (isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)) {
                        res.add(s1 + "." + s2 + "." + s3 + "." + s4);
                    }
                }
            }
        }
        return res;
    }

    private boolean isValid(String s) {
        if (s.length() > 3 || s.length() == 0 || (s.charAt(0) == '0' && s.length() > 1) || Integer.parseInt(s) > 255) {
            return false;
        }
        return true;
    }


    public List<String> restoreIpAddresses2(String s) {
        List<String> res = new ArrayList<>();
        backtrack(s, 0, new StringBuilder(), res, 0);
        return res;
    }

    /**
     * @param s
     * @param start 字符串开始部分
     * @param track 已经划分部分
     * @param res   保存所有的解
     * @param count 当前已经加入了几部分
     */
    private void backtrack(String s, int start, StringBuilder track, List<String> res, int count) {
        // 如果剩余长度大于剩下部分都取3位数的长度，剪枝
        if (s.length() - start > 3 * (4 - count)) {
            return;
        }
        // 当前刚好到达末尾
        if (start == s.length()) {
            // 当前刚好是4部分，将结果加入，否则剪枝
            if (count == 4) {
                res.add(track.substring(0, track.length() - 1));
            }
            // 当前到达末尾，但不是4部分，剪枝
            return;
        }
        // 当前超过末尾，剪枝
        // 当前未到达末尾，但有4部分，剪枝
        if (start > s.length() || count == 4) {
            return;
        }
        // 保存当前的解
        StringBuilder before = new StringBuilder(track);

        // 选择：加入一位数
        track.append(s.charAt(start) + "" + '.');
        backtrack(s, start + 1, track, res, count + 1);

        // 如果开头是0，直接结束，即选择2或3位数之前可进行剪枝
        if (s.charAt(start) == '0') {
            return;
        }

        // 做出选择：加入2位数
        if (start + 1 < s.length()) {
            // 撤销选择：恢复为之前的解
            track = new StringBuilder(before);
            track.append(s.substring(start, start + 2) + "" + '.');
            backtrack(s, start + 2, track, res, count + 1);
        }

        // 做出选择：加入3位数
        if (start + 2 < s.length()) {
            // 撤销选择：恢复为之前的解
            track = new StringBuilder(before);
            // 当选择3位数时，要判断数字大小
            int num = Integer.parseInt(s.substring(start, start + 3));
            if (0 <= num && num <= 255) {
                track.append(s.substring(start, start + 3) + "" + '.');
                backtrack(s, start + 3, track, res, count + 1);
            }
        }
    }

}
