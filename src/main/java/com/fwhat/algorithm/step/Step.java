package com.fwhat.algorithm.step;

public class Step {

    public static void main(String[] args) {
        System.out.println(minStep("7 5 8 4 7 3 5 3 4 4 1 1 9"));
    }

    /**
     * 找终点
     *
     * 题目描述
     * 给定一个正整数数组，最大为100个成员，从第一个成员开始，走到数组最后一个成员最少的步骤数。第一步必须从第一元素开始，1<=步长<len/2,
     * 第二步开始以所在成员的数字走相应的步数，如果目标不可达返回-1，只输出最少的步骤数。
     */
    public static int minStep(String member) {
        String[] members = member.split(" ");

        int length = members.length;

        int min = -1;
        int nextMax = length / 2;
        for (int i = 1; i < nextMax; i ++) {
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
}
