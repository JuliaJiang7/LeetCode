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
}
