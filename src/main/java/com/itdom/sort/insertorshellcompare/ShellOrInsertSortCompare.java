package com.itdom.sort.insertorshellcompare;

import com.itdom.sort.insert.InsertSort;
import com.itdom.sort.merge.MergeSort;
import com.itdom.sort.quick.QuickSort;
import com.itdom.sort.shell.ShellSort;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShellOrInsertSortCompare {

    public static void main(String[] args) throws IOException {

        Comparable[] arr = readFile("./sort.txt");


        long start = System.currentTimeMillis();
//        shellSort(arr);//耗时3
//        insertSort(arr);//耗时659
//        MergeSort.sort(arr);//483
        QuickSort.sort(arr);//70
        long end = System.currentTimeMillis();
        System.out.println("排序耗时:"+(end-start));
        System.out.println(Arrays.asList(arr));


    }
    public static Comparable[] readFile(String path) throws IOException {
        FileReader reader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = null;
        List<Integer> list = new ArrayList<Integer>();

        while ((line=bufferedReader.readLine())!=null){
            list.add(Integer.parseInt(line));
        }
        if (bufferedReader!=null){
            bufferedReader.close();
        }
        if (reader!=null){
            reader.close();
        }
        return list.toArray(new Comparable[list.size()]);
    }


    public static void createFile() throws IOException {
        FileWriter writer = new FileWriter("./sort.txt");
        for (int i = 20000; i >0  ; i--) {
            writer.write(i+"\r\n");
        }
        writer.flush();
        if (writer!=null){
            writer.close();
        }
    }
    public static void shellSort(Comparable[] arr){
        int h=1;
        while (h<arr.length/2){
            h=2*h+1;
        }
        for (int i=h;i<arr.length;i++){

            for (int j=i;j>=h;j-=h){
                if (!ShellSort.greater(arr[j-h],arr[j])){
                    break;
                }
                ShellSort.exchange(arr,j-h,j);
            }
            h=h/2;
        }
    }
    public static void insertSort(Comparable[] arr){
        for (int i=1;i<arr.length;i++){
            for (int j=i;j>0;j--){
                if (!InsertSort.greater(arr[j-1],arr[j])){
                    break;
                }
                InsertSort.exchange(arr,j-1,j);
            }
        }


    }




}
