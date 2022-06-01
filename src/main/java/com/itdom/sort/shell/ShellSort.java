package com.itdom.sort.shell;

import java.util.Arrays;

public class ShellSort {


    public static void main(String[] args) {
        Integer[] arr = {4, 2, 1, 3, 5, 8, 6, 5, 9};
        //1.根据数组的长度确定增长量h
        int h = 1;
        while (h < arr.length / 2) {
            h = 2 * h + 1;
        }
        //2.希尔排序、
        while (h >= 1) {
            //2.排序
            //2.1找到待插入的元素
            for (int i = h; i < arr.length; i++) {
                //2.2把待插入元素插入到有序数列中
                for (int j = i; j >= h; j -= h) {
                    if (greater(arr[j - h], arr[j])) {
                        exchange(arr, j - h, j);
                    }
                }
            }
            h = h / 2;
        }
        System.out.println(Arrays.asList(arr));
    }


    public static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    public static void exchange(Comparable[] arr, int i, int j) {
        Comparable temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
