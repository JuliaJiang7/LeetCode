import java.util.LinkedList;
import java.util.Queue;

/**
 * @author julia.jiang
 * @date 2020/9/8 9:07
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution117 {

    /**
     * 解法一：层次遍历
     * 空间复杂度 O(n)
     * 参考：https://leetcode.wang/leetcode-116-Populating-Next-Right-Pointers-in-Each-Node.html
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        Queue<Node> queue = new LinkedList<>();
        // 在尾部添加元素
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Node pre = null;
            for (int i = 0; i < size; i++) {
                // 移除队列第一个元素
                Node cur = queue.poll();
                if (i > 0) {
                    pre.next = cur;
                }
                pre = cur;
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return root;
    }

    /**
     * 解法二
     * 空复 O(1)
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        Node cur = root;
        while (cur != null){
            Node dummy = new Node();
            Node tail = dummy;
            // 遍历当前层
            while (cur != null){
                if(cur.left != null){
                    tail.next = cur.left;
                    tail = tail.next;
                }
                if(cur.right != null){
                    tail.next = cur.right;
                    tail = tail.next;
                }
                cur = cur.next;
            }
            // 更新到下一层
            cur = dummy.next;
        }
        return root;
    }
}
