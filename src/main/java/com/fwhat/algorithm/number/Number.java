package com.fwhat.algorithm.number;

import com.fwhat.algorithm.Assert;

import java.util.Scanner;

public class Number {

    public static void main(String[] args) {
        Assert.equal(minSum("bb12-34aa"), -31);
        Assert.equal(minSum("bb1234aa"), 10);

        System.out.println(maxLongSubString());;
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

    /**
     * 有N个正整数组成的一个序列，给定一个整数sum
     * 求长度最长的的连续子序列使他们的和等于sum
     * 返回次子序列的长度，如果没有满足要求的序列 返回-1
     * 备注：
     *
     * 输入序列仅由数字和英文逗号构成，数字之间采用英文逗号分割
     * 序列长度 1<=N<=200，输入序列不考虑异常情况
     * 由题目保证输入序列满足要求
     * 示例
     * 输入：
     * 1,2,3,4,2
     * 6
     * 输出：
     * 3
     * 解析：
     * 1,2,3和4,2两个序列均能满足要求，所以最长的连续序列为1,2,3 因此结果为3
     * 输入：
     * 1,2,3,4,2
     * 20
     * 输出：
     * -1
     * 解释：
     *
     * 没有满足要求的子序列，返回-1
     */
    public static int maxLongSubString() {
        Scanner scanner = new Scanner(System.in);
        String[] nums = scanner.nextLine().split(",");
        int target = scanner.nextInt();
        scanner.close();

        int[] numArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numArr[i] = Integer.parseInt(nums[i]);
        }

        int maxLong = -1;

        for (int i = 0; i < numArr.length; i++) {
            int tempSum = 0;
            System.out.println(i);
            if (maxLong > numArr.length - i) {
                break;
            }
            for (int j = i; j < numArr.length; j++) {
                tempSum += numArr[j];
                if (tempSum > target) {
                    break;
                }

                if (tempSum == target && j > i) {
                    maxLong = Math.max(maxLong, j - i + 1);
                }
            }
        }

        return maxLong;
    }
}
