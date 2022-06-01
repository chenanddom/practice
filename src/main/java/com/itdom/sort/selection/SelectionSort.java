package com.itdom.sort.selection;

public class SelectionSort {
    public static void main(String[] args) {
        Integer[] arr = {6, 3, 2, 5, 4, 8, 9, 0, 1};
        /**
         * 此处需要注意，此处-2是为了后面元素进行比较
         */
        for (int i = 0; i <= arr.length - 2; i++) {
            int minIndex=i;
            for (int j = i + 1; j < arr.length; j++) {
                if (greater(arr[minIndex], arr[j])) {
                    minIndex=j;
                }
            }
         exchange(arr, i, minIndex);
        }
        for (Integer ele : arr) {
            System.out.println(ele);
        }
    }

    /**
     * 确认v是否大于w
     *
     * @param v
     * @param w
     * @return
     */
    public static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void exchange(Comparable[] arr, int i, int j) {
        Comparable temp = null;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
