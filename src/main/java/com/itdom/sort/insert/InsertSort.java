package com.itdom.sort.insert;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        Integer[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        for (int i = 1; i < arr.length; i++) {
            //注意此处需要j>0否则会出现脚标越界异常
            for (int j = i; j > 0; j--) {
                if (!greater(arr[j - 1], arr[j])) {
                    break;
                }
                exchange(arr, j - 1, j);
            }
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
