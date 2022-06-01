package com.itdom.linkedlist;


import javax.print.DocFlavor;
import java.io.Serializable;

/**
 * 1. 快慢指针可以处理找中间值
 * 2. 快慢指针处判断是否有环
 * 3. 快慢指针找环的入口
 */
public class FastOrSlowPointer {

    public static void main(String[] args) {
        Node<String> first = new Node<String>("aa", null);
        Node<String> second = new Node<String>("bb", null);
        Node<String> third = new Node<String>("cc", null);
        Node<String> fourth = new Node<String>("dd", null);
        Node<String> fifth = new Node<String>("ee", null);
        Node<String> six = new Node<String>("ff", null);
        Node<String> seven = new Node<String>("gg", null);

        //完成结点之间的指向
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = six;
        six.next = seven;
        seven.next =third;
        //查找中间值
//        String mid = getMid(first);
//        System.out.println("中间值为：" + mid);
        System.out.println(isCircle(first)?"有环":"无环");
        System.out.println(getEntrance(first).item);

    }

    private static Node getEntrance(Node first){
        Node<String> slow = first;
        Node<String> fast = first;
        //定义临时的节点，在检测到环的时候就把第一个节点赋值给他，然后继续走
        Node<String> temp = null;

        while (fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if (slow.equals(fast)){
                temp=first;
                continue;
            }
            if (temp!=null){
                temp = temp.next;
                if (temp.equals(slow)){
                    return temp;
                }
            }
        }
        return null;
    }


    private static boolean isCircle(Node first){
        Node<String> slow = first;
        Node<String> fast = first;
        while (fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if (slow.item.equals(fast.item)){
                return true;
            }
        }
        return false;
    }


    private static String getMid(Node<String> first) {
        Node<String> slow = first;
        Node<String> fast = first;
        while (fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.item;
    }


    static class Node<T> implements Serializable {
        public T item;
        public Node<T> next;

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }

}
