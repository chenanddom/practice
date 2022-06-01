package com.itdom.leetcode.num83;

public class DeleteDuplicates {

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode node1 = new ListNode(1);
        head.next=node1;
        ListNode node2 = new ListNode(1);
        node1.next=node2;
        ListNode node3 = new ListNode(2);
        node2.next=node3;
        ListNode node4 = new ListNode(3);
        node3.next=node4;
        ListNode node5 = new ListNode(3);
        node4.next = node5;
        ListNode listNode = deleteDuplicates(head);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode=listNode.next;
        }

    }

    public static ListNode deleteDuplicates(ListNode head) {
       if (head==null){
           return null;
       }
        ListNode p =head;
        while (p.next!=null){
            if (p.next.val==p.val){
                p.next=p.next.next;
            }else {
                p=p.next;
            }
        }
        return head;
    }

  public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

}
