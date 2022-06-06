package com.itdom.leetcode.num22;

/**
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * <p>
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KthFromEnd {

    public static void main(String[] args) {
//        ListNode head = new ListNode(0);
        ListNode node1 = new ListNode(1);
//        head.next = node1;
//        ListNode node2 = new ListNode(2);
//        node1.next = node2;
//        ListNode node3 = new ListNode(3);
//        node2.next = node3;
//        ListNode node4 = new ListNode(4);
//        node3.next = node4;
//        ListNode node5 = new ListNode(5);
//        node4.next = node5;
        ListNode listNode = getKthFromEnd(node1, 1);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
    public static ListNode getKthFromEnd(ListNode head, int k) {
//        if (head==null||k<=0){return null;}
//        ListNode pTemp=head,pKthNode = null;
//        for (int i = 1; i <k; i++) {
//            if (pTemp!=null){
//                pTemp = pTemp.next;
//            }
//        }
//        while (pTemp!=null){
//            if (pKthNode!=null){
//                pKthNode = pKthNode.next;
//                pTemp = pTemp.next;
//            }else {
//                pKthNode = head;
//            }
//        }
//        if (pKthNode!=null){
//            return pKthNode;
//        }
        int length=0;
        ListNode node= head;
        while (node!=null){
            length++;
            node = node.next;
        }

        for (node=head;length>k;length--){
            node = node.next;
        }
        return node;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
