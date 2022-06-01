package com.itdom.sort.bubble;

public class BubbleSort {
    public static void main(String[] args) {

        Integer[] arr = {5, 2, 3, 1, 4, 6};
          for (int i=arr.length-1;i>0;i--){
              for (int j=0;j<i;j++){
                  if (greater(arr[j],arr[j+1])){
                    exchange(arr,j,j+1);
                  }
              }
          }
        for (Integer integer : arr) {
            System.out.println(integer);
        }

    }

    /**
     * 确定v是否大于w
     *
     * @param v
     * @param w
     * @return
     */
    public static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    /**
     * 将i和j的位置进行交换
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void exchange(Comparable[] arr, int i, int j) {
        Comparable temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
