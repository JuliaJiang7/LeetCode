import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Julia Jiang
 * @date 2020/2/28 11:36
 * @description 有效的括号（栈）
 */
public class Solution20 {
    //建立哈希表构建左右括号的关系，查询左右括号只需要 O(1) 复杂度
    private Map<Character, Character> map = new HashMap<Character, Character>(){{
        put('{', '}');
        put('[', ']');
        put('(', ')');
        put('?', '?');
    }};

    public boolean isValid(String s){
        //一开始先入栈 '?' 以防止栈为空出栈的报错
        //注意这里的 stack 使用 LinkedList 数据结构
        LinkedList<Character> stack = new LinkedList<Character>(){{
           add('?');
        }};

        for(Character c : s.toCharArray()){
            //如果是左括号
            if(map.containsKey(c)){
                stack.addLast(c);
            }else{
                Character last = stack.removeLast();
                if(map.get(last) != c)   return false;
            }
        }
        return stack.size() == 1;
    }

    public static void main(String[] args) {
        Solution20 solution20 = new Solution20();
        System.out.println(solution20.isValid("()"));
    }
}
