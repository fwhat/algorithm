package com.fwhat.algorithm.other;


import java.util.Arrays;

public class AddNum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{9})));
    }

    public static int[] plusOne(int[] digits) {
        int addNum = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (addNum == 0) {
                break;
            }
            if ((digits[i] + addNum) >= 10) {
                digits[i] = 0;
            } else {
                addNum = 0;
                digits[i] ++;
            }
        }
        if (addNum == 1) {
            int[] arr = new int[digits.length + 1];
            System.arraycopy(digits, 0, arr, 1, digits.length);
            arr[0] = 1;
            return arr;
        }


        return digits;
    }
}
