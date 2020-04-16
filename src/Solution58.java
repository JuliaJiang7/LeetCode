/**
 * @author Julia Jiang
 * @date 2020/4/13 9:51
 * @description 最后一个单词的长度
 * https://leetcode.wang/leetCode-58-Length-of-Last-Word.html
 */
public class Solution58 {

    /**
     * 从后向前遍历，找空格，计数
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        int count = 0;
        int index = s.length() - 1;
        // 只去掉字符串后面的空格，trim() 是去掉前后的空格
        while (index >= 0 && s.charAt(index) == ' '){
            index--;
        }
        for(; index >= 0; index--){
            if(s.charAt(index) == ' '){
                break;
            }
            count++;
        }
        return count;
    }
}
