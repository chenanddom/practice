package com.itdom.tree;

import com.itdom.linkedlist.Queue;

public class PageFoldTest {

    public static void main(String[] args) {
        print(createTree(3));
    }

    public static void print(Node tree) {
        if (tree == null) {
            return;
        }
        print(tree.left);
        System.out.print(tree.item+" ");
        print(tree.right);
    }

    public static Node createTree(int n) {
        if (n == 0) {
            return null;
        }
        Node<String> root = null;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                root = new Node<>("down", null, null);
                continue;
            } else {
                Queue<Node<String>> queue = new Queue();
                queue.enqueue(root);
                while (!queue.isEmpty()) {
                    Node<String> node = queue.dequeue();
                    if (node.left != null) {
                        queue.enqueue(node.left);
                    }
                    if (node.right != null) {
                        queue.enqueue(node.right);
                    }
                    if (node.left == null && node.right == null) {
                        node.left = new Node("down", null, null);
                        node.right = new Node("up", null, null);
                    }
                }
            }

        }
        return root;
    }


    static class Node<T> {
        public T item;
        public Node left;
        public Node right;

        public Node(T item, Node left, Node right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }


}
