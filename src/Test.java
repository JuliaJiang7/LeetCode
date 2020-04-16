import java.util.*;
import java.util.function.Function;

/**
 * @author Julia Jiang
 * @date 2020/2/21 14:37
 * @description
 */
public class Test {
    public static void change(int a) {
        a = 50;
    }

    public static void change2(int[] a) {
        a[0] = 50;
    }


    void foo(String s) {
        s = "windows";
        System.out.println(s);
        s.equals("");
        s.compareTo("");
        StringBuilder s1 = new StringBuilder();
        StringBuffer s2 = new StringBuffer();
    }



    public void listAdd(List<Integer> list){
        System.out.println(list);
        list.add(1);
        System.out.println(list);

    }



    public static String upcase(String s) {
        return s.toUpperCase();
    }
    public static void main(String[] args) {
        String s = "abc";
        System.out.println(s.indexOf("b"));
        System.out.println(s.indexOf("d"));

    }


}
