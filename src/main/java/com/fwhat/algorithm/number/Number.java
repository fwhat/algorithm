package com.fwhat.algorithm.number;

import com.fwhat.algorithm.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Number {

    public static void main(String[] args) {
        Assert.equal(minSum("bb12-34aa"), -31);
        Assert.equal(minSum("bb1234aa"), 10);

//        System.out.println(maxLongSubString());

        System.out.println(reserveNum(1516000));

//        printSeries();
//        maxNum();
//        printNumAllSeries();
        minNum();
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

    /**
     * 题目描述
     *
     * 描述：
     *
     * 输入一个整数，将这个整数以字符串的形式逆序输出
     *
     * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
     *
     * 输入描述:
     *
     * 输入一个int整数
     *
     * 输出描述:
     *
     * 将这个整数以字符串的形式逆序输出
     * 示例1
     *
     * 输入
     *
     * 1516000
     * 输出
     *
     * 0006151
     */
    public static String reserveNum(int num) {
        StringBuilder str = new StringBuilder();

        while (num > 0) {
            str.append(num % 10);
            num /= 10;
        }

        return str.toString();
    }

    /**
     * 已知连续正整数数列{K}=K1,K2,K3...Ki的各个数相加之和为S，i=N (0<S<100000, 0<N<100000), 求此数列K。
     *
     * 输入描述:
     *
     * 输入包含两个参数，1）连续正整数数列和S，2）数列里数的个数N。
     *
     * 输出描述:
     *
     * 如果有解输出数列K，如果无解输出-1
     *
     * 示例1
     *
     * 输入
     *
     * 525 6
     * 输出
     *
     * 85 86 87 88 89 90
     */
    public static void printSeries() {
        Scanner scanner = new Scanner(System.in);
        int sum = scanner.nextInt();
        int n = scanner.nextInt();
        // a1*n + n(0+n-1)/2 = sum

        int a1n = sum - n * (n - 1) / 2;
        if (a1n % n == 0) {
            int a1 = a1n / n;
            for (int i = 0; i < n; i ++) {
                System.out.print((a1+i) + " ");
            }
        } else {
            System.out.println("-1");
        }

        scanner.close();
    }

    /**
     * 小组中每位都有一张卡片，卡片上是6位内的正整数，将卡片连起来可以组成多种数字，计算组成的最大数字。
     *
     * 输入描述:
     * “,”号分割的多个正整数字符串，不需要考虑非数字异常情况，小组最多25个人
     *
     * 输出描述:
     * 最大的数字字符串
     *
     * 示例1
     * 输入
     * 22,221
     * 输出
     * 22221
     *
     * 输入
     *
     * 4589,101,41425,9999
     * 输出
     *
     * 9999458941425101
     *
     * 通过字符串比较排序, 将较大的数排在前面(这种方式不用考虑总数溢出问题)
     */
    public static void maxNum() {
        Scanner scanner = new Scanner(System.in);
        String[] numStr = scanner.nextLine().split(",");
        Arrays.parallelSort(numStr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                char[] chars1 = o1.toCharArray();
                char[] chars2 = o2.toCharArray();

                int maxSize = Math.max(chars1.length, chars2.length);

                for (int i = 0; i < maxSize; i++) {
                    if (i > chars1.length-1) {
                        return 1;
                    }
                    if (i > chars2.length - 1) {
                        return -1;
                    }

                    if (chars1[i] > chars2[i]) {
                        return -1;
                    }

                    if (chars1[i] < chars2[i]) {
                        return 1;
                    }
                }

                return 0;
            }
        });

        for (String s : numStr) {
            System.out.print(s);
        }
        scanner.close();
    }

    /**
     * 一个整数可以由连续的自然数之和来表示 给定一个整数 计算该整数有几种连续自然数之和的表达式 并打印出每一种表达式
     *
     * 输入描述
     *
     * 一个目标整数t 1<= t <=1000
     *
     * 输出描述 该整数的所有表达式和表达式的个数。如果有多种表达式，输出要求为：
     *
     * 自然数个数最少的表达式优先输出
     * 每个表达式中按自然数递增的顺序输出，具体的格式参见样例。在每个测试数据结束时，输出一行”Result:X”，其中X是最终的表达式个数。
     * 具体的格式参见样例
     *
     * 示例1
     *
     * 输入
     *
     * 9
     * 输出
     *
     * 9=9
     * 9=4+5
     * 9=2+3+4
     * Result:3
     */
    public static void printNumAllSeries() {
        Scanner scanner = new Scanner(System.in);
        int sum = scanner.nextInt();

        // a1n = sum - (0+n-1)n/2
        StringBuilder print = new StringBuilder();
        int count = 0;
        for (int n = 1; n < sum; n++) {
            int a1n = sum - (n -1 ) * n / 2;
            // 注意 a1n 不能为负数
            if (a1n % n == 0 && a1n > 0) {
                count++;
                int a1 = a1n / n;
                print.append(sum).append("=").append(a1);
                for (int i = 1; i < n; i++) {
                    print.append("+").append(a1 + i);
                }
                System.out.println(print);
                print.delete(0, print.length());
            }
        }
        System.out.println("Result:"+count);
    }

    /**
     * 数组组成的最小数字
     *
     * 题目描述: 给定一个整型数组，请从该数组中选择3个元素组成最小数字并输出（如果数组长度小于3，则选择数组中所有元素来组成最小数字）。
     *
     * 输入描述:
     *
     * 一行用半角逗号分割的字符串记录的整型数组，0 < 数组长度 <= 100，0 < 整数的取值范围 <= 10000。
     *
     * 输出描述:
     *
     * 由3个元素组成的最小数字，如果数组长度小于3，则选择数组中所有元素来组成最小数字。
     *
     * 示例1
     *
     * 输入
     *
     * 21,30,62,5,31
     * 输出
     *
     * 21305
     */
    public static void minNum() {
       // todo
    }
}
