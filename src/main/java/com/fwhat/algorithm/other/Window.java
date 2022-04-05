package com.fwhat.algorithm.other;

public class Window {
    public static void main(String[] args) {
        System.out.println(maxSum(new int[]{10,20, 30, 15, 23, 12}, 3));;
    }

    /**
     * 滑动窗口最大值
     * 题目描述：
     *
     * 有一个N个整数的数组，和一个长度为M的窗口，窗口从数组内的第一个数开始滑动直到窗口不能滑动为止， 每次窗口滑动产生一个窗口和（窗口内所有数的和），求窗口滑动产生的所有窗口和的最大值。
     *
     * 输入描述：
     *
     * 第一行输入一个正整数N，表示整数个数。（0<N<100000）
     * 第二行输入N个整数，整数的取值范围为[-100,100]。
     * 第三行输入一个正整数M，M代表窗口的大小，M<=100000，且M<=N。
     * 输出描述：
     *
     * 窗口滑动产生所有窗口和的最大值。
     * 示例 1 输入输出示例仅供调试，后台判题数据一般不包含示例
     *
     * 输入
     *
     * 6
     * 10 20 30 15 23 12
     * 3
     * 输出
     *
     * 68
     */
    public static int maxSum(int[] size, int window) {
        int max = 0;
        for (int i = 0; i < window; i++) {
            max += size[i];
        }

        int len = size.length;
        int preWindow = max;
        for (int i = window; i < len; i++) {
            preWindow = preWindow - size[i-window] + size[i];
            max = Math.max(max, preWindow);
        }

        return max;
    }
}
