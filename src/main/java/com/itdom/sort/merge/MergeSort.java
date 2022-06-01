package com.itdom.sort.merge;

import java.util.Arrays;

public class MergeSort {
    private static Comparable[] assist = null;

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{8,4,2,1,3,7,9,0,5};
        sort(arr);
        System.out.println(Arrays.asList(arr));
    }

    public static void sort(Comparable[] arr) {
        assist = new Comparable[arr.length];
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(Comparable[] arr, int lo, int hi) {
        if (hi<=lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    public static void merge(Comparable[] arr, int lo, int mid, int hi) {
        int i = lo;
        int p1 = lo;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= hi) {
            if (less(arr[p1], arr[p2])) {
                assist[i++] = arr[p1++];
            } else {
                assist[i++] = arr[p2++];
            }
        }
        while (p1<=mid){
            assist[i++]=arr[p1++];
        }

        while (p2<=hi){
            assist[i++]=arr[p2++];
        }
        for (int index = 0 ;index<=hi;index++){
            arr[index]=assist[index];
        }
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
