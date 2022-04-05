package com.fwhat.algorithm.other;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MaxMatrix {
    public static void main(String[] args) {
        System.out.println(maxMatrix(new String[] {
                "1,0,0,0,1",
                "0,0,0,1,1",
                "0,1,0,1,0",
                "1,0,0,1,1",
                "1,0,1,0,1",
        }));
    }

    /**
     * 给定一个仅包含0和1的N*N二维矩阵，请计算二维矩阵的最大值，计算规则如下：
     *
     * 每行元素按下标顺序组成一个二进制数（下标越大越排在低位），二进制数的值就是该行的值。矩阵各行值之和为矩阵的值。
     * 允许通过向左或向右整体循环移动每行元素来改变各元素在行中的位置。 比如：
     * [1,0,1,1,1]向右整体循环移动2位变为[1,1,1,0,1]，二进制数为11101，值为29。
     * [1,0,1,1,1]向左整体循环移动2位变为[1,1,1,1,0]，二进制数为11110，值为30。
     * 输入描述:
     *
     * 输入的第一行为正整数，记录了N的大小，0 < N <= 20。
     * 输入的第2到N+1行为二维矩阵信息，行内元素半角逗号分隔。
     * 输出描述:
     *
     * 矩阵的最大值。
     *
     * 示例1：
     * 输入
     *
     * 5
     * 1,0,0,0,1
     * 0,0,0,1,1
     * 0,1,0,1,0
     * 1,0,0,1,1
     * 1,0,1,0,1
     * 输出
     *
     * 122
     * 说明
     *
     * 第一行向右整体循环移动1位，得到本行的最大值[1,1,0,0,0]，二进制值为11000，十进制值为24。
     * 第二行向右整体循环移动2位，得到本行的最大值[1,1,0,0,0]，二进制值为11000，十进制值为24。
     * 第三行向左整体循环移动1位，得到本行的最大值[1,0,1,0,0]，二进制值为10100，十进制值为20。
     * 第四行向右整体循环移动2位，得到本行的最大值[1,1,1,0,0]，二进制值为11100，十进制值为28。
     * 第五行向右整体循环移动1位，得到本行的最大值[1,1,0,1,0]，二进制值为11010，十进制值为26。
     * 因此，矩阵的最大值为122。
     */
    public static int maxMatrix(String[] strings) {
        int max = 0;

        for (String string : strings) {
            String str = string.replaceAll(",", "");
            int length = str.length();
            String[] all = new String[length];

            for (int i = 0; i < length; i++) {
                all[i] = str.substring(i) + str.substring(0, i);
            }
            Arrays.sort(all);
            max += Integer.parseInt(all[length - 1], 2);
        }

        return max;
    }
}
