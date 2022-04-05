package com.fwhat.algorithm.other;

import java.util.Arrays;

public class MinTime {
    public static void main(String[] args) {
        System.out.println(minTime(3, new int[]{8, 4, 3, 2, 10, 11, 12, 3, 4, 6}));
    }

    /**
     * 一个工厂有m条流水线，来并行完成n个独立的作业，该工厂设置了一个调度系统，在安排作业时，总是优先执行处理时间最短的作业。
     *
     * 现给定流水线个数m，需要完成的作业数n, 每个作业的处理时间分别为t1,t2…tn。请你编程计算处理完所有作业的耗时为多少？
     *
     * 当n>m时，首先处理时间短的m个作业进入流水线，其他的等待，当某个作业完成时，依次从剩余作业中取处理时间最短的进入处理。
     *
     * 输入描述:
     *
     * 第一行为2个整数（采用空格分隔），分别表示流水线个数m和作业数n；
     * 第二行输入n个整数（采用空格分隔），表示每个作业的处理时长t1,t2…tn。
     * 0< m,n<100，0<t1,t2…tn<100。
     * 注：保证输入都是合法的。
     *
     * 输出描述:
     *
     * 输出处理完所有作业的总时长
     *
     * 示例1
     *
     * 输入
     *
     * 3 5
     * 8 4 3 2 10
     * 输出
     *
     * 13
     * 说明
     */
    public static int minTime(int n, int[] taskTimes) {
        Arrays.sort(taskTimes);

        int i;
        int[] runs = new int[n];
        for (i = 0; i < taskTimes.length; i++) {
            runs[i % n] += taskTimes[i];
        }

        return runs[(i-1) % n];
    }
}
