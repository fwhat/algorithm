package com.fwhat.algorithm.other;

import java.util.Scanner;

public class FindFriend {

    /**
     * 在学校中，N 个小朋友站成一队，第 i 个小朋友的身高为 height[i]，第 i 个小朋友可以看到第一个比自己身高更高的小朋友j，那么 j 是 i 的好朋友 (要求：j>i) 。
     * 请重新生成一个列表，对应位置的输出是每个小朋友的好朋友的位置。
     * 如果没有看到好朋友，请在该位置用0代替。
     * 小朋友人数范围 0~40000。
     * 输入描述：
     *
     * 第一行输入 N，N 表示有N个小朋友
     * 第二行输入 N 个小朋友的身高 height[i]，都是整数
     * 输出描述：
     *
     * 输出 N 个小朋友的好朋友的位置
     * 示例：
     *
     * 输入：
     *
     * 2
     * 100 95
     * 输出：
     *
     * 0 0
     * 说明：
     *
     * 第一个小朋友身高100站在队伍末尾，向队首看，没有比他身高高的小朋友，所以输出第一个值为0，第二个小朋友站在队首前面也没有比他身高高的小朋友，所以输出第二个值为0。
     * 输入：
     *
     * 8
     * 123 124 125 121 119 122 126 123
     * 输出：
     *
     * 1 2 6 5 5 6 0 0
     * 说明：
     *
     * 123的好朋友是1位置上的124 ，124的好朋友是2位置上的125，125的好朋友是6位置上的126，依此类推
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i : find(arr)) {
            System.out.print(i + " ");
        }
    }

    public static int[] find(int[] arr) {
        int[] find = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            find[i] = 0;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    find[i] = j;
                    break;
                }
            }
        }

        return find;
    }
}
