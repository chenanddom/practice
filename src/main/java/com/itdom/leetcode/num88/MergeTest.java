package com.itdom.leetcode.num88;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
[1,2,3,0,0,0]
3
[2,5,6]
3
 */
public class MergeTest {
    public static void main(String[] args) {
        int[] n1={1,2,3,0,0,0};
        int[] n2={2,5,6};
        merge(n1,3,n2,3);
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < n1.length; i++) {
            list.add(n1[i]);
        }
        System.out.println(list);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int totalLength = m + n;
        int[] tmp = new int[totalLength];
        int index = 0, index1 = 0, index2 = 0;
        while (index < totalLength) {
            if (index1 >= m) {
                tmp[index++] = nums2[index2++];
            } else if (index2 >= n) {
                tmp[index++] = nums2[index1++];
            } else if (nums1[index1] < nums2[index2]) {
                tmp[index++] = nums1[index1++];
            } else {
                tmp[index++] = nums2[index2++];
            }
        }
        for (int i = 0; i < totalLength; i++) {
            nums1[i] = tmp[i];
        }
    }
}
