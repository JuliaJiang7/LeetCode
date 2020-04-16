/**
 * @author Julia Jiang
 * @date 2020/2/17 18:31
 * @description
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public static ListNode getList(){
        ListNode node = new ListNode(1);
        ListNode head = node;
        for(int i = 2; i <= 4; i++){
            node.next = new ListNode(i);
            node = node.next;
        }
        node = null;
        return head;
    }

    public void showList(){
        ListNode p = this;
        if(p != null)
            System.out.print(this.val);
        while (p.next != null){
            p = p.next;
            System.out.print(" -> " + p.val);
        }
        System.out.println();
    }

    /**
     * 输入 int 数组，返回链表
     * @param array
     * @return
     */
    public static ListNode arrayToLinkedList(int[] array){
        ListNode dummyNode = new ListNode(0);
        ListNode curr = dummyNode;
        for(int i : array){
            curr.next = new ListNode(i);
            curr = curr.next;
        }
        curr.next = null;
        return dummyNode.next;
    }

    public static void main(String[] args) {
        ListNode node = arrayToLinkedList(new int[]{1,2,3});
        node.showList();
    }

}
