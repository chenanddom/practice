package com.itdom.leetcode.num448;

import java.util.ArrayList;
import java.util.List;

public class DisappearedNumbers {
    public static void main(String[] args) {
        int[] arr = {4,3,2,7,8,2,3,1};
        List<Integer> disappearedNumbers = findDisappearedNumbers(arr);
        System.out.println(disappearedNumbers);

    }
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int length = nums.length;
        for(int num:nums){
            int index = (num-1)%length;
            nums[index]=(-1*nums[index]);
        }
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<length;i++){
            if(nums[i]>0){
                list.add((i+1));
            }
        }
        return list;
    }
}
