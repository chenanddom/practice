package com.itdom.sort.quick;

import java.util.Arrays;

/**
 * 不稳定
 */
public class QuickSort {
    public static void main(String[] args) {
            Integer[] arr = {7,3,4,1,6,2,9,8,0,5};
            sort(arr);
        System.out.println(Arrays.asList(arr));
    }

    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }


    public static void sort(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int partition = partition(arr, lo, hi);
        sort(arr, lo, partition - 1);
        sort(arr, partition + 1, hi);
    }

    public static int partition(Comparable[] arr, int lo, int hi) {
        Comparable key = arr[lo];
        int left = lo;
        int right = hi + 1;
        while (true) {
            while (++left<=hi&&less(arr[left], key)) {
                if (left == hi) {
                    break;
                }
            }
            while (--right>=lo&&less(key, arr[right])) {
                if (right == lo) {
                    break;
                }
            }
            if (left >= right) {
                break;
            } else {
                exchange(arr, left, right);
            }
        }
        exchange(arr, lo, right);
        return right;
    }


    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exchange(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
