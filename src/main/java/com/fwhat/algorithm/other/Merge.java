package com.fwhat.algorithm.other;

import java.util.Arrays;

public class Merge {
    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 0, 0, 0, 0, 0};
        int m = 1;
        int[] nums2 = new int[]{1,2,3,5,6};
        int n = 5;
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        if (m == 0) {
            if (n >= 0) System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }

        System.arraycopy(nums1, 0, nums1, nums1.length - m, m);

        int index1 = nums1.length - m;
        int index2 = 0;
        int setIndex = 0;
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[index1] > nums2[index2]) {
                nums1[setIndex] = nums2[index2];
                index2++;
            } else {
                nums1[setIndex] = nums1[index1];
                index1++;
            }

            setIndex++;

            if (index2 > n - 1) {
                break;
            }
            if (index1 > nums1.length - 1) {
                System.arraycopy(nums2, index2, nums1, setIndex, n - index2);
                break;
            }
        }
    }
}
