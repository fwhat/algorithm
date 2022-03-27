package com.fwhat.algorithm;

import java.util.Arrays;

public class Swap {

    public static void main(String[] args) {
        int[] arr = {1, 3};

        swap(arr, 0, 1);

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 1. a = a ^ b
     * 2. b = (a ^ b) ^ b = a
     * 3. a = (a ^ b) ^ a = b
     */
    public static void swap(int[] arr, int indexA, int indexB) {
        arr[indexA] = arr[indexA] ^ arr[indexB];
        arr[indexB] = arr[indexA] ^ arr[indexB];
        arr[indexA] = arr[indexA] ^ arr[indexB];
    }
}
