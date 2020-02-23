import java.util.ArrayList;
import java.util.List;

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
    }



    public void listAdd(List<Integer> list){
        System.out.println(list);
        list.add(1);
        System.out.println(list);

    }


    public static void main(String[] args) {
        Test test = new Test();
        String str = "hello";
        test.foo(str); // str 也没有被改变
        System.out.println(str);

        /*int a = 10;
        System.out.println(a);
        change(a);
        System.out.println(a);

        int[] b = {10, 20};
        System.out.println(b[0]);
        change2(b);
        System.out.println(b[0]);*/
        /*List<Integer> list = new ArrayList<>();
        Test test = new Test();
        test.listAdd(list);*/
    }
}
