package com.fwhat.algorithm.sort;

import com.fwhat.algorithm.Swap;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {4, 31, 2, 54, 6, 1, 0, 9, 5, 243, 11};

        sort(arr);

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序 在一次遍历中，找到最小的，并与当前的 i 交换
     */
    public static void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        var n = array.length;

        int minIndex;

        for (int i = 0; i < n; i++) {
            minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            Swap.swap(array, i, minIndex);
        }
    }
}
