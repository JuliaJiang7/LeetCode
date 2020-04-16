import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Julia Jiang
 * @date 2020/2/28 12:17
 * @description 简化路径
 */
public class Solution71 {

    public static void main(String[] args) {
        Solution71 solution71 = new Solution71();
        System.out.println(solution71.simplifyPath("/a//b////c/d//././/.."));
    }

    public String simplifyPath(String path) {
        //定义栈
        Deque<String> stack = new LinkedList<>();
        //使用 split 直接分离出各个路径（想不到）
        for (String item : path.split("/")) {
            if (item.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else if (!item.isEmpty() && !item.equals("."))
                //除了空字符(两个//中间会截出空字符) 和 . 遇到其他截出的字符串都入栈
                stack.push(item);
        }

        String res = "";
        //栈先进后出，要倒着拼接字符串
        for (String d : stack)
            res = "/" + d + res;

        return res.isEmpty() ? "/" : res;
    }
}
