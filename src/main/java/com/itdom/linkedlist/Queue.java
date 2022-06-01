package com.itdom.linkedlist;

import com.itdom.tree.BinaryTree;

import java.util.Iterator;

public class Queue<T> implements Iterable<T>{
    private Node head;
    private int N;
    private Node last;

    public Queue() {
        this.head = new Node(null, null);
        this.last = null;
        this.N = 0;
    }

    public boolean isEmpty() {
        return this.N == 0;
    }

    public int size() {
        return this.N;
    }

    public void enqueue(T t) {
        this.N++;
        if (last == null) {
            Node<T> newNode = new Node<>(t, null);
            head.next = newNode;
            last = newNode;
            return;
        }
        Node currentNode = last;
        Node<T> newNode = new Node<>(t, null);
        currentNode.next = newNode;
        last = newNode;
    }

    public T dequeue() {
        if (last==null){
            return null;
        }
        Node currentNode = head.next;
        head.next = currentNode.next;
        currentNode.next=null;
        this.N--;
        //此处需要判断是否已经为空队列了，如果是空队列的就需要将最后一个元素给清空了。
        if (isEmpty()) {
            this.last=null;
        }
        return (T) currentNode.item;
    }




    @Override
    public Iterator<T> iterator() {
        return new CustomerIterater();
    }
    public class CustomerIterater implements Iterator<T>{

        Node  h=head;

        @Override
        public boolean hasNext() {
            return h.next!=null;
        }

        @Override
        public T next() {
            Node current = h.next;
            h=h.next;
            return (T) current.item;
        }
    }

    private static class Node<T> {
        T item;
        private Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
    }

}
