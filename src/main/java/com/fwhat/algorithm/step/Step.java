package com.fwhat.algorithm.step;

import java.util.Arrays;
import java.util.Scanner;

public class Step {

    public static void main(String[] args) {
        System.out.println(minStep("7 5 8 4 7 3 5 3 4 4 1 1 9"));

        minD();
    }

    /**
     * 找终点
     * <p>
     * 题目描述
     * 给定一个正整数数组，最大为100个成员，从第一个成员开始，走到数组最后一个成员最少的步骤数。第一步必须从第一元素开始，1<=步长<len/2,
     * 第二步开始以所在成员的数字走相应的步数，如果目标不可达返回-1，只输出最少的步骤数。
     */
    public static int minStep(String member) {
        String[] members = member.split(" ");

        int length = members.length;

        int min = -1;
        int nextMax = length / 2;
        for (int i = 1; i < nextMax; i++) {
            int res = 1;
            int nextStep = Integer.parseInt(members[i]);
            int cur = nextStep + i;
            while (true) {
                res++;
                if (cur == length - 1) {
                    min = min != -1 ? Math.min(res, min) : res;
                    break;
                }
                if (cur > length - 1) {
                    break;
                }
                int next = Integer.parseInt(members[cur]);
                cur += next;
            }
        }

        return min;
    }

    /**
     * 一条长l的笔直的街道上有n个路灯，若这条街的起点为0，终点为l，第i个路灯坐标为a i，每盏灯可以覆盖到的最远距离为d，
     * 为了照明需求，所有灯的灯光必须覆盖整条街，但是为了省电，要是这个d最小，请找到这个最小的d。
     * 输入描述:
     * 每组数据第一行两个整数n和l（n大于0小于等于1000，l小于等于1000000000大于0）。第二行有n个整数(均大于等于0小于等于l)，为每盏灯的坐标，多个路灯可以在同一点。
     * 输出
     * 输出答案，保留两位小数。
     * 输入例子:
     * 7 15
     * 15 5 3 7 9 14 0
     * <p>
     * 输出例子:
     * 2.5
     */
    public static void minD() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int l = scanner.nextInt();

        int[] posArr = new int[n];
        for (int i = 0; i < n; i++) {
            posArr[i] = scanner.nextInt();
        }

        Arrays.sort(posArr);

        int maxD = 0;
        for (int i = 0; i < posArr.length; i++) {
            // 注意首尾灯位的问题，在两端时，距离应该*2才能够保证能够覆盖
            if (i == 0) {
                if (posArr[i] != 0) {
                    maxD = Math.max(maxD, posArr[i] * 2);
                }
                continue;
            } else if (i == posArr.length - 1) {
                if (posArr[i] != l) {
                    maxD = Math.max(maxD, (l - posArr[i]) * 2);
                    continue;
                }
            }
            maxD = Math.max(maxD, posArr[i] - posArr[i - 1]);
        }

        System.out.println(maxD / 2.00);
    }
}
