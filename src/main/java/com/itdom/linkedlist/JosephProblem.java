package com.itdom.linkedlist;

import java.io.Serializable;

public class JosephProblem {
    public static void main(String[] args) {
//构建节点41个，对应41个人
        //第一个节点，代表第一个人
        Node<Integer> firstNode = null;
        //代表当前节点的上一个节点
        Node<Integer> preNode = null;

        for (int i = 1; i <= 41; i++) {
            //构建第一个节点
            if (i == 1) {
                firstNode = new Node<Integer>(i, null);
                preNode = firstNode;
                continue;
            }
            Node<Integer> node = new Node(i, null);
            preNode.next = node;
            preNode = node;
            if (i == 41) {
                preNode.next = firstNode;
            }
        }
        //计数
        int count = 0;
        //当前节点
        Node<Integer> currentNode = firstNode;
        //当前节点的上一个节点
        Node<Integer> beforeNode = null;

        while (currentNode != currentNode.next) {
            count++;
            if (count == 3) {
                beforeNode.next = currentNode.next;
                System.out.print(currentNode.item + ",");
                currentNode = currentNode.next;
                count = 0;
            } else {
                beforeNode = currentNode;
                currentNode = currentNode.next;
            }
        }
        System.out.println();
        System.out.println(currentNode.item);

    }
        static class Node<T> implements Serializable {
            public T item;
            public Node next;

            public Node(T item, Node next) {
                this.item = item;
                this.next = next;
            }
        }


}
