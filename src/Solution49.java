import java.util.*;

/**
 * @author Julia Jiang
 * @date 2020/4/1 11:04
 * @description
 * https://leetcode.wang/leetCode-49-Group-Anagrams.html
 */
public class Solution49 {

    /**
     * 解法一
     * @param strs
     * @return
     * 时复 O(n^2 * K)    K 表示 字符串长度
     * 空复 O(nK)
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        int len = strs.length;
        boolean[] used = new boolean[len];
        for(int i = 0; i < len ; i++){
            if(!used[i]){
                List<String> temp = new ArrayList<>();
                temp.add(strs[i]);
                used[i] = true;
                for(int j = i + 1; j < len; j++){
                    if(!used[j] && equals(strs[i], strs[j])){
                        temp.add(strs[j]);
                        used[j] = true;
                    }
                }
                res.add(new ArrayList<>(temp));
            }
        }
        return res;
    }

    private boolean equals(String str1, String str2){
        if(str1.length() != str2.length())
            return false;

        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < str1.length(); i++){
            int value = map.getOrDefault(str1.charAt(i), 0);
            map.put(str1.charAt(i), value + 1);
        }

        for(int i = 0; i < str2.length(); i++){
            int value = map.getOrDefault(str2.charAt(i), 0);
            if(value == 0)  return false;
            map.put(str2.charAt(i), value - 1);
        }

        return true;
    }

    /**
     * 解法二：排序 + 映射
     * @param strs
     * @return
     * 时复 O(n K log(K))
     * 空复 O(n K)
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(int i = 0; i < strs.length; i++){
            char[] s_arr = strs[i].toCharArray();
            // 排序
            Arrays.sort(s_arr);
            // 映射到 key
            String key = String.valueOf(s_arr);
            if(map.containsKey(key)){
                map.get(key).add(strs[i]);
            }else {
                List<String> temp = new ArrayList<>();
                temp.add(strs[i]);
                map.put(key, temp);
            }
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 解法三;正整数的唯一分解定理
     * @param strs
     * @return
     * 时复 O(n K)
     * 空复 O(n K)
     */
    public List<List<String>> groupAnagrams3(String[] strs) {
        HashMap<Integer, List<String>> map = new HashMap<>();
        int[] prime = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
        for(int i = 0; i < strs.length; i++){
            int key = 1;
            for(int j = 0; j < strs[i].length(); j++){
                key *= prime[strs[i].charAt(j) - 'a'];
            }
            if(map.containsKey(key)){
                map.get(key).add(strs[i]);
            }else{
                List<String> temp = new ArrayList<>();
                temp.add(strs[i]);
                map.put(key, temp);
            }
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 解法四：字符串映射为 #0#1#3...
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams4(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(int i = 0; i < strs.length; i++){
            //存储每个字母出现的次数
            int[] num = new int[26];
            for(int j = 0; j < strs[i].length(); j++){
                num[strs[i].charAt(j) - 'a'] ++;
            }
            StringBuilder temp = new StringBuilder();
            for(int k = 0; k < 26; k++){
                temp.append("#").append(num[k]);
            }
            String key = temp.toString();
            if(map.containsKey(key)){
                map.get(key).add(strs[i]);
            }else{
                List<String> s_temp = new ArrayList<>();
                s_temp.add(strs[i]);
                map.put(key, s_temp);
            }
        }
        return new ArrayList<>(map.values());
    }


    public static void main(String[] args) {
        Solution49 solution49 = new Solution49();
        String[] strings = new String[]{"eat", "tea", "ant", "bat", "eta", "tan"};
        System.out.println(solution49.groupAnagrams4(strings));
    }
}
