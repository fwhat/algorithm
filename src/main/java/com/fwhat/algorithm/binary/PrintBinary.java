package com.fwhat.algorithm.binary;

import com.fwhat.algorithm.Assert;

/**
 * 输出二进制字符串
 */
public class PrintBinary {
    public static void main(String[] args) {
        // 00000000000000000000000000110010
        print(Integer.MAX_VALUE);
        print(Integer.MIN_VALUE);
        print(-1);
        // 一个数的相反数等 ~num + 1
        // 00000000000000000000000000110010
        print(50);
        Assert.right(string(-50).equals(string(~50 + 1)));
        Assert.right(string(50).equals(string(~-50 + 1)));
    }

    public static void print(int num) {
        System.out.println(string(num));
    }

    public static String string(int num) {
        StringBuilder str = new StringBuilder();

        for (var i = 31; i >= 0; i --) {
            if ((num & 1 << i) == 0) {
                str.append("0");
            } else {
                str.append("1");
            }
        }

        return str.toString();
    }
}
