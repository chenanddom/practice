package com.itdom.leetcode.num234;

/**
 * 回文列表
 */
public class Palindrome {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(2);
        node2.next = node3;
        ListNode node4 = new ListNode(1);
        node3.next = node4;

        System.out.println(isPalindrome(node1));
    }


    public static boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        fast = head;
        slow = reverseList(slow);
        while (slow != null) {
            if (slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    /**
     * 链表反转
     *
     * @param slow
     * @return
     */
    private static ListNode reverseList(ListNode slow) {
        ListNode preNode = null,currentNode=slow;
        while (currentNode != null) {
            ListNode nextNode = currentNode.next;
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
        }
        return preNode;
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
