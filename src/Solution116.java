import java.util.LinkedList;
import java.util.Queue;

/**
 * @author julia.jiang
 * @date 2020/9/7 9:16
 * @email julia.jiang.fan@gmail.com
 * @description
 */
public class Solution116 {

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
     * 解法二：迭代
     * 好难... 想不到
     *
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        if (root == null) {
            return root;
        }
        Node pre = root;
        Node cur = null;
        Node start = pre;
        while (pre.left != null) {
            // 遍历到了最右边节点，要将 pre 和 cur 更新到下一层，并且记录 start
            if (cur == null) {
                // 只需要把 pre 的左孩子的 next 指向右孩子（即更新下一层第一个节点的next）
                pre.left.next = pre.right;

                pre = start.left;
                cur = start.right;
                start = pre;
                // 将下一层的 next 连起来，同时 pre next 后移
            } else {
                // 把 pre 的左孩子的 next 指向右孩子
                pre.left.next = pre.right;
                // 把 pre 的右孩子的 next 指向 cur 的左孩子
                pre.right.next = cur.left;

                pre = pre.next;
                cur = cur.next;
            }
        }
        return root;
    }
}
