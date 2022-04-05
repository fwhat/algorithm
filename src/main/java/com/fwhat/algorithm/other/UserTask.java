package com.fwhat.algorithm.other;

public class UserTask {
    public static void main(String[] args) {
        userTask(3, new String[]{"15 8 17", "12 20 9", "11 7 5"});
    }

    /**
     * 用户调度问题
     * 在通信系统中，一个常见的问题是对用户进行不同策略的调度，会得到不同的系统消耗和性能。
     *
     * 假设当前有n个待串行调度用户，每个用户可以使用A/B/C三种不同的调度策略，不同的策略会消耗不同的系统资源。请你根据如下规则进行用户调度，并返回总的消耗资源数。 规则：
     *
     * 相邻的用户不能使用相同的调度策略，例如，第1个用户使用了A策略，则第2个用户只能使用B或者C策略。
     * 对单个用户而言，不同的调度策略对系统资源的消耗可以归一化后抽象为数值。例如，某用户分别使用A/B/C策略的系统消耗分别为15/8/17。
     * 每个用户依次选择当前所能选择的对系统资源消耗最少的策略（局部最优），如果有多个满足要求的策略，选最后一个。
     * 输入描述:
     *
     * 第一行表示用户个数n
     *
     * 接下来每一行表示一个用户分别使用三个策略的系统消耗resA resB resC
     *
     * 输出描述:
     *
     * 最优策略组合下的总的系统资源消耗数
     * 示例 1
     *
     * 输入
     *
     * 3
     * 15 8 17
     * 12 20 9
     * 11 7 5
     * 输出
     *
     * 24
     * 说明
     *
     * 1号用户使用B策略，2号用户使用C策略，3号用户使用B策略。系统资源消耗: 8 + 9 + 7 = 24。
     * 备注:
     *
     * 所有策略对系统的资源消耗均为正整数，n < 1000
     */
    public static void userTask(int users, String[] userPolicy) {
        int sum = 0;
        int lastMinIndex = -1;
        for (int i = 0; i < users; i++) {
            String[] policyStr = userPolicy[i].split(" ");
            int minPolicy;
            int currentMinIndex;
            if (lastMinIndex == 0) {
                minPolicy = Integer.parseInt(policyStr[1]);
                currentMinIndex = 1;
            } else {
                minPolicy = Integer.parseInt(policyStr[0]);
                currentMinIndex = 0;
            }

            for (int j = 0; j < policyStr.length; j++) {
                if (j == lastMinIndex) continue;;
                int policy = Integer.parseInt(policyStr[j]);
                if (policy <= minPolicy) {
                    minPolicy = policy;
                    currentMinIndex = j;
                }
            }
            lastMinIndex = currentMinIndex;
            sum += minPolicy;
        }

        System.out.println(sum);
    }
}
