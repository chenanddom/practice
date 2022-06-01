package com.itdom.heap;


import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        String[] arr = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        HeapSort.sort(arr);
        System.out.println(Arrays.asList(arr));
    }

    public static void sort(Comparable[] source) {
        //1.创建一个比源数组大的堆
        Comparable[] heap = new Comparable[source.length + 1];
        //2.构造堆
        createHeap(source, heap);
        //3.堆排序
        //定义一个变量，记录最后一个索引
        int index = heap.length - 1;
        while (index > 0) {
            //将index处的值和最大值交换
            exchange(heap,1,index);
            //将间交换后最后一个索引位置处的值剔除，不参与下轮比较
            index--;
            //交换到索引1处的值可能导致堆的无序，需要进行下沉来实现堆的有序性
            sink(heap,1,index);
        }
        //将堆内存排好序的数据拷贝到源数组中
        System.arraycopy(heap,1,source,0,source.length);
    }


    /**
     * 构造堆结构
     *
     * @param source
     * @param heap
     */
    public static void createHeap(Comparable[] source, Comparable[] heap) {
        //把source中的元素拷贝到heap中
        System.arraycopy(source, 0, heap, 1, source.length);
        //从无序的堆中间的位置堆元素进行下沉，初始创建的堆是有序的
        for (int i = (heap.length - 1) / 2; i > 0; i--) {
            sink(heap, i, heap.length - 1);
        }
    }

    /**
     * 对数组内的元素进行下沉
     *
     * @param heap
     * @param target
     * @param range
     */
    public static void sink(Comparable[] heap, int target, int range) {
        if (heap == null) {
            return;
        }
        while (2 * target <= range) {
            int max = 2 * target;
            if (2 * target + 1 <= range) {
                if (less(heap, 2 * target, 2 * target + 1)) {
                    max = 2 * target + 1;
                }
            }
            if (!less(heap, target, max)) {
                break;
            }
            exchange(heap, target, max);
            target = max;
        }
    }


    private static boolean less(Comparable[] heap, int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }


    public static void exchange(Comparable[] heap, int i, int j) {
        Comparable temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

}
