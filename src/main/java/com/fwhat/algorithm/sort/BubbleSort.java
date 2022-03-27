package com.fwhat.algorithm.sort;

import com.fwhat.algorithm.Swap;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {4, 31, 2, 54, 6, 1, 0, 9, 5, 243, 11};

        sort(arr);

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒泡排序: 将前后两个数中较大的数往后移
     * 与选择排序的区别是，每一次比较都会进行交换；选择则是一次遍历找到最值，再进行交换
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        var n = arr.length;

        /*
          0 ~ N-1
          0 ~ N-2
          0 ~ N-3
         */
        for (int end = n-1; end >= 0; end--) {
            for (int j = 1; j < end; j++) {
                if (arr[j] < arr[j - 1]) {
                    Swap.swap(arr, j, j - 1);
                }
            }
        }
    }
}
