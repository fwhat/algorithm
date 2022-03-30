package com.fwhat.algorithm.number;

import com.fwhat.algorithm.Assert;

public class Number {

    public static void main(String[] args) {
        Assert.equal(minSum("bb12-34aa"), -31);
        Assert.equal(minSum("bb1234aa"), 10);
    }

    /**
     * 求字符串中所有整数的最小和
     *
     * 输入字符串s，输出s中包含所有整数的最小和
     *
     * 说明
     *
     * 字符串s，只包含 a-z A-Z +- ；
     *
     * 合法的整数包括
     *
     * 1） 正整数 一个或者多个0-9组成，如 0 2 3 002 102
     *
     * 2）负整数 负号 - 开头，数字部分由一个或者多个0-9组成，如 -0 -012 -23 -00023
     *
     * 输入描述:
     *
     * 包含数字的字符串
     *
     * 输出描述:
     *
     * 所有整数的最小和
     *
     * 示例1
     * bb1234aa
     * 10
     *
     * bb12-34aa
     * -31
     */
    public static int minSum(String string) {
        char[] chars = string.toCharArray();
        StringBuilder negTemp = new StringBuilder();
        boolean isNeg = false;

        int sum = 0;
        for (char aChar : chars) {
            if (Character.isDigit(aChar)) {
                if (isNeg) {
                    negTemp.append(aChar);
                } else {
                    sum += Character.digit(aChar, 10);
                }
            } else {
                if (negTemp.length() > 0) {
                    sum -= Integer.parseInt(negTemp.toString());
                    negTemp.delete(0, negTemp.length());
                    isNeg = false;
                }

                if (aChar == '-') {
                    isNeg = true;
                }
            }
        }

        if (negTemp.length() > 0) {
            sum -= Integer.parseInt(negTemp.toString());
        }

        return sum;
    }
}
