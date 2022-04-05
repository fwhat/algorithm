package com.fwhat.algorithm.other;

import java.util.Scanner;

public class SunArea {
    public static void main(String[] args) {
        maxArea();
    }

    /**
     * 题目描述
     *
     * 给航天器一侧加装长方形或正方形的太阳能板（图中的红色斜线区域），需要先安装两个支柱（图中的黑色竖条），再在支柱的中间部分固定太阳能板。
     * 但航天器不同位置的支柱长度不同，太阳能板的安装面积受限于最短一侧的那根支柱长度。
     * 如图：现提供一组整形数组的支柱高度数据，假设每根支柱间距离相等为1个单位长度，计算如何选择两根支柱可以使太阳能板的面积最大。
     * 输入描述:
     *
     * 10,9,8,7,6,5,4,3,2,1
     * 注：支柱至少有2根，最多10000根，能支持的高度范围1~10^9的整数。柱子的高度是无序的，例子中递减只是巧合。
     * 输出描述:
     *
     * 可以支持的最大太阳能板面积：（10米高支柱和5米高支柱之间）
     * 25
     * 示例1
     *
     * 输入
     *
     * 10,9,8,7,6,5,4,3,2,1
     * 输出
     * 25
     */
    public static void maxArea() {
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split(",");
        int length = s.length;
        Integer[] lens = new Integer[length];
        for (int i = 0; i < length; i++) {
            lens[i] = Integer.parseInt(s[i]);
        }

        int max = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                max = Math.max(Math.min(lens[i], lens[j]) * (j - i), max);
            }
        }

        System.out.println(max);
    }
}
