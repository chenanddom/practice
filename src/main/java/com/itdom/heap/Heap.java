package com.itdom.heap;

/**
 * 堆的特点，根节点最大，第一个索引位置处不放任何元素,是一颗完全二叉树
 * @param <T>
 */
public class Heap<T extends Comparable<T>>{
    //存储元素的数组
    private T[] items;
    //记录数组元素的个数
    private int N;

    public Heap(int capacity) {
        items = (T[]) new Comparable[capacity+1];
        this.N=0;
    }
    //判断堆中索引i处的元素是否小于索引j处的元素
    private boolean less(int i,int j){
        return items[i].compareTo(items[j])<0;
    }

    //交换堆中i索引和j索引处的值
    private void exchange(int i,int j){
        T tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }
    //往堆中插入一个元素
    public void insert(T t){
        //第一个
        items[++N]=t;
        swim(N);
    }

    /**
     * 使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
     * @param k
     */
    private void swim(int k) {
        while (k>1){
            if (less(k/2,k)){
                exchange(k/2,k);
            }
            k=k/2;
        }
    }

    private T delMax(){
        T max = items[1];
        exchange(1,N);
        items[N]=null;
        N--;
        //下沉处理，使得元素处于正确的位置
        sink(1);
        return max;
    }

    private void sink(int k) {
    while (2*k<=N){
        int max;
        //存在右子节点的情况
        if (2*k+1<=N) {
            //比较左子节点和右子节点的大小
            if (less(2 * k, 2 * k + 1)) {
                max = 2 * k + 1;
            } else {
                max = 2 * k;
            }
        }else {
            max=2*k;
        }
        //如果子结点的最大值都没有比当前节点大，那么就跳出循环
        if (!less(k,max)){
            break;
        }
        //将最大的子结点和当前节点交换
        exchange(k,max);
        k=max;
        }
    }

    public static void main(String[] args) {
        Heap<String> heap = new Heap<String>(20);
        heap.insert("S");
        heap.insert("G");
        heap.insert("I");
        heap.insert("E");
        heap.insert("N");
        heap.insert("H");
        heap.insert("O");
        heap.insert("A");
        heap.insert("T");
        heap.insert("P");
        heap.insert("R");
        String del;
        while((del=heap.delMax())!=null){
            System.out.print(del+",");
        }
    }
}
