package com.fwhat.algorithm.sort;

import com.fwhat.algorithm.Swap;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {4, 31, 2, 54, 6, 1, 0, 9, 5, 243, 11};

        sort(arr);

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序: 先保证 0 ~ n 之间有序，再将 n+1 的数插入到有序数组中，从有序数组最后一个开始比较; 交换;
     * 如果需要插入的数已经比 当前需要比较的数大时，直接退出循环，因为当前 0 ~ n+1 已经有序
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        var n = arr.length;

        /*
          0 ~ 1 有序
          0 ~ 2 有序
         */

        for (int end = 1; end < n; end++) {
            for (int newNumberIndex = end; newNumberIndex > 0; newNumberIndex--) {
                if (arr[newNumberIndex] < arr[newNumberIndex - 1]) {
                    Swap.swap(arr, newNumberIndex, newNumberIndex - 1);
                } else {
                    break;
                }
            }
        }
    }
}
