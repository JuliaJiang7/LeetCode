import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Julia Jiang
 * @date 2020/3/22 11:36
 * @description 串联所有单词的子串
 * https://leetcode.wang/leetCode-30-Substring-with-Concatenation-of-All-Words.html
 */
public class Solution30 {
    /**
     * 解法一（想不到...）
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordsNum = words.length;
        if(wordsNum == 0)   return res;

        int wordLen = words[0].length();
        //HashMap1 存储所有单词
        HashMap<String, Integer> allWords = new HashMap<>();
        for(String word : words){
            int value = allWords.getOrDefault(word, 0);
            allWords.put(word, value + 1);
        }

        //遍历所有子串
        for(int i = 0; i < s.length() - wordsNum * wordLen + 1; i++){
            //HashMap2 存储当前扫描子串中含有的单词
            HashMap<String, Integer> hasWords = new HashMap<>();
            int num = 0;

            //判断该子串是否符合要求
            while (num < wordsNum){
                String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                //判断该单词在 HashMap1 中
                if(allWords.containsKey(word)){
                    int value = hasWords.getOrDefault(word, 0);
                    hasWords.put(word, value + 1);
                    //判断当前单词的 value 和 allWords 中的 value 大小
                    if(hasWords.get(word) > allWords.get(word)){
                        break;
                    }
                }else{
                    break;
                }
                num++;
            }
            //判断是否所有单词符合条件
            if(num == wordsNum){
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 解法二：解法一的优化（更是想不到...）
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordsNum = words.length;
        if(wordsNum == 0)   return res;

        int wordLen = words[0].length();
        //HashMap1 存储所有单词
        HashMap<String, Integer> allWords = new HashMap<>();
        for(String word : words){
            int value = allWords.getOrDefault(word, 0);
            allWords.put(word, value + 1);
        }

        //将所有移动分成 wordLen 种情况
        for(int j = 0; j < wordLen; j++){
            HashMap<String, Integer> hasWords = new HashMap<>();
            int num = 0;
            //每次移动一个单词的长度
            for(int i = j; i < s.length() - wordLen * wordsNum + 1; i += wordLen){
                //防止情况三移除后，情况一继续移除
                boolean hasRemoved = false;
                while (num < wordsNum){
                    String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                    if(allWords.containsKey(word)){
                        int value = hasWords.getOrDefault(word, 0);
                        hasWords.put(word, value + 1);
                        //出现情况三：遇到符合的单词，但是次数超了
                        if(hasWords.get(word) > allWords.get(word)){
                            hasRemoved = true;
                            int removeNum = 0;
                            //一直移除单词，直到次数符合
                            while (hasWords.get(word) > allWords.get(word)){
                                String firstWord = s.substring(i + wordLen * removeNum, i + wordLen * (removeNum + 1));
                                int v = hasWords.get(firstWord);
                                hasWords.put(firstWord, v - 1);
                                removeNum++;
                            }
                            num = num - removeNum + 1;
                            i = i + (removeNum - 1) * wordLen;
                            break;
                        }
                    }else{
                        //情况二：出现不符合的单词，直接将 i 移动到该单词的后边
                        //但其实这里只是移动到了单词出现的地方，因为外层有 for 循环，i 还要再移动一个单词
                        hasWords.clear();
                        i = i + num * wordLen;
                        num = 0;
                        break;
                    }
                    num++;
                }
                if(num == wordsNum){
                    res.add(i);
                }
                //出现情况一：子串完全匹配，我们将上一个子串的第一个单词从 hasWords 中移除
                if(num > 0 && !hasRemoved){
                    String firstWord = s.substring(i, i + wordLen);
                    int v = hasWords.get(firstWord);
                    hasWords.put(firstWord, v - 1);
                    num = num - 1;
                }
            }
        }
        return res;
    }
}
