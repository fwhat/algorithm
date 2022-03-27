package com.fwhat.algorithm.search;

import com.fwhat.algorithm.Assert;
import com.fwhat.algorithm.Mid;

public class Search {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 6, 7, 8, 10, 20};

        Assert.equal(searchInSortArray(arr, 7), 5);
        Assert.equal(searchInSortArray(arr, 11), -1);

        int[] arr2 = {1, 2, 3, 5, 6, 6, 6, 7, 8, 10, 20};
        Assert.equal(searchThanMostLeftInSortArray(arr2, 6), 4);
        Assert.equal(searchThanMostLeftInSortArray(arr2, 100), -1);
        Assert.equal(searchThanMostLeftInSortArray(arr2, 11), 10);

        int[] arr3 = {1, 2, 3, 5, 6, 7, 6, 7, 8, 10, 20};
        Assert.equal(searchPartMinIndex(arr3), 0);

        int[] arr4 = {3, 2, 5, 6, 7, 6, 7, 8, 10, 9};
        Assert.equal(searchPartMinIndex(arr4), 9);

        int[] arr5 = {3, 2, 5, 6, 7, 6, 7, 8, 10, 11};
        int[] find = {1, 5};
        Assert.notEqual(searchInSortArray(find, searchPartMinIndex(arr5)), -1);

        int[] arr6 = {10, 8, 7, 5, 7, 9, 12};
        Assert.equal(searchPartMinIndex(arr6), 3);

        int[] arr7 = {10, 8, 11, 5, 7, 6, 11};
        int[] find2 = {1, 3, 5};
        Assert.notEqual(searchInSortArray(find2, searchPartMinIndex(arr7)), -1);
    }

    public static int searchInSortArray(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        var left = 0;
        var right = arr.length - 1;

        int mid = right / 2;

        while (left <= right) {
            if (arr[mid] == target) {
                return mid;
            }

            if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

            mid = Mid.mid(left, right);
        }

        return -1;
    }

    public static int searchThanMostLeftInSortArray(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        var left = 0;
        var right = arr.length - 1;

        var find = -1;
        var mid = right / 2;

        while (left <= right) {
            if (arr[mid] >= target) {
                find = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }

            mid = Mid.mid(left, right);
        }

        return find;
    }

    /**
     *  在一个无序数组中，找到其中一个局部最小index; 该数组相邻两个数不相等
     *  局部最小定义
     *  1. arr[0] < arr[1] 时 arr[1] 为局部最小
     *  2. arr[len-1] < arr[len-2] 时 arr[len-1] 为局部最小
     *  3. arr[n-1] < arr[n] < arr[n+1] 时 arr[n] 为局部最小值
     */
    public static int searchPartMinIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        var len = arr.length;

        if (len == 1) {
            return 0;
        }

        if (arr[0] < arr[1]) {
            return 0;
        }

        if (arr[len - 1] < arr[len - 2]) {
            return len - 1;
        }

        var left = 0;
        var right = len - 1;

        var mid = right / 2;

        while (true) {
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return mid;
            }

            if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                right = mid - 1;
            } else if (arr[mid] < arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                left = mid + 1;
            }


            mid = Mid.mid(left, right);
        }
    }
}
