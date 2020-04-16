import java.util.*;

/**
 * @author Julia Jiang
 * @date 2020/3/17 11:05
 * @description 合并K个排序链表
 */
public class Solution23 {
    /**
     * 解法一：遍历所有的链表，将所有节点的值存储到 list 中，用快排排序，再用排好序的值构造一个新的链表返回
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        List<Integer> l = new ArrayList<>();
        //存到数组
        for (ListNode ln : lists) {
            while (ln != null) {
                l.add(ln.val);
                ln = ln.next;
            }
        }
        //数组排序
        Collections.sort(l);
        //存到链表
        //第一个是虚拟节点，最终的链表返回 head 的下一个节点
        ListNode head = new ListNode(0);
        ListNode h = head;
        for (int i : l) {
            ListNode t = new ListNode(i);
            h.next = t;
            h = h.next;
        }
        h.next = null;
        return head.next;
    }

    /**
     * 解法二：一列一列比较
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists){
        ListNode head = new ListNode(0);
        ListNode h = head;
        int minIndex = 0;
        while (true){
            int minValue = Integer.MAX_VALUE;   //不能定义在 while 外面
            boolean isBreak = true;
            //遍历一列
            for(int i = 0; i < lists.length; i++){
                if(lists[i] != null){
                    if(minValue > lists[i].val){
                        minValue = lists[i].val;
                        minIndex = i;
                    }
                    //有一个链表不为空，就继续循环
                    isBreak = false;
                }
            }
            if(isBreak) break;
            ListNode node = new ListNode(lists[minIndex].val);
            h.next = node;
            h = h.next;
            //比较这个链表的下一个节点
            lists[minIndex] = lists[minIndex].next;
        }
        h.next = null;
        return head.next;
    }

    /**
     * 解法二的优化：不用单独建链表，改变链表的 next 指向即可
     * @param lists
     * @return
     */
    public ListNode mergeKLists3(ListNode[] lists){
        ListNode head = new ListNode(0);
        ListNode h = head;
        int minIndex = 0;
        while (true){
            int minValue = Integer.MAX_VALUE;
            boolean isBreak = true;
            for(int i = 0; i < lists.length; i++){
                if(lists[i] != null){
                    if(minValue > lists[i].val){
                        minValue = lists[i].val;
                        minIndex = i;
                    }
                    isBreak = false;
                }
            }
            if(isBreak) break;
            //注意
            h.next = lists[minIndex];
            h = h.next;
            lists[minIndex] = lists[minIndex].next;
        }
        h.next = null;
        return head.next;
    }

    /**
     * 解法三：优先队列
     * 解法二中，（k 链表个数）每次都是找出最小的为 O(k)， 加入一个新的为 O(1)
     * 使用优先队列，我们定义数越小优先级越高，用堆实现，则找出最小的为 O(log k), 同时加入一个新的也为 O(log k)
     * @param lists
     * @return
     */
    public ListNode mergeKLists4(ListNode[] lists){
        //定义优先队列的比较器
        Comparator<ListNode> cmp = new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                //如果 o1.val < o2.val, 返回负数，表示false, 即就是不用调整 o1 和 o2 的位置
                return o1.val - o2.val;
            }
        };

        //建立队列
        Queue<ListNode> q = new PriorityQueue<>(cmp);
        //添加每个链表的第一个节点
        for(ListNode node : lists){
            if(node != null)
                q.add(node);
        }
        ListNode head = new ListNode(0);
        ListNode h = head;
        while (!q.isEmpty()){
            //出队列
            ListNode tmp = q.poll();
            h.next = tmp;
            h = h.next;
            //判断 tmp 是否还有下一个节点，有的话加入队列
            if(tmp.next != null){
                q.add(tmp.next);
            }
        }
        return head.next;
    }
}
