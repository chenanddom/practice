package com.itdom.leetcode.num21;

public class MergeTwoList {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        listNode1.next = node2;
        node2.next = new ListNode(4);

        ListNode listNode2 = new ListNode(1);
        ListNode n2 = new ListNode(3);
        listNode2.next = n2;
        n2.next = new ListNode(4);
        ListNode listNode = mergeTwoLists(listNode1, listNode2);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            if (list2 == null) {
                return null;
            }
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode retNode = new ListNode(0);
        ListNode p = retNode;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }
        if (list1 != null) {
            p.next = list1;
        }
        if (list2 != null) {
            p.next = list2;
        }
        return retNode;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
