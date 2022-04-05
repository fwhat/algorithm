package com.fwhat.algorithm.other;

public class Resort {
    public static void main(String[] args) {
        resort7("0 0 0 2 1");
        System.out.println();
        resort7("0 1 0");
    }

    /**
     * 喊7的次数重排
     * 题目描述
     * <p>
     * 喊7是一个传统的聚会游戏，N个人围成一圈，按顺时针从1到N编号。
     * 编号为1的人从1开始喊数，下一个人喊的数字为上一个人的数字加1，但是当将要喊出来的数字是7的倍数或者数字本身含有7的话，不能把这个数字直接喊出来，而是要喊"过"。
     * 假定玩这个游戏的N个人都没有失误地在正确的时机喊了"过"，当喊到数字K时，可以统计每个人喊"过"的次数。
     * 现给定一个长度为N的数组，存储了打乱顺序的每个人喊"过"的次数，请把它还原成正确的顺序，即数组的第i个元素存储编号i的人喊"过"的次数。
     * 输入描述:
     * <p>
     * 输入为一行，为空格分隔的喊"过"的次数，注意K并不提供，K不超过200，而数字的个数即为N。
     * <p>
     * 输出描述:
     * <p>
     * 输出为一行，为顺序正确的喊"过"的次数，也由空格分隔。
     * <p>
     * 示例1：
     * 输入
     * 0 1 0
     * 输出
     * 1 0 0
     * 说明
     * 一共只有一次喊"过"，那只会发生在需要喊7时，按顺序，编号为1的人会遇到7，故输出1 0 0。
     * 注意，结束时的K不一定是7，也可以是8、9等，喊过的次数都是1 0 0。
     * <p>
     * 示例2：
     * 输入
     * 0 0 0 2 1
     * 输出
     * 0 2 0 1 0
     * 说明
     * <p>
     * 一共有三次喊"过"，发生在7 14 17，按顺序，编号为2的人会遇到7 17，编号为4的人会遇到14，故输出0 2 0 1 0。
     */
    public static void resort7(String str) {
        String[] s = str.split(" ");
        int sum = 0;
        for (String s1 : s) {
            sum += Integer.parseInt(s1);
        }

        int n = s.length;
        int i = 1;
        int count7 = 0;
        int[] res = new int[n];
        boolean loop = true;
        while (loop) {
            for (int j = 0; j < n; j++) {
                if ((i % 7 == 0 || String.valueOf(i).contains("7"))) {
                    res[j]++;
                    count7++;
                    if (count7 == sum) {
                        loop = false;
                    }
                }
                i++;
            }
        }

        for (int re : res) {
            System.out.print(re + " ");
        }
    }
}
