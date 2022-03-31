package com.fwhat.algorithm.binary;

import java.util.Scanner;

public class IP {

    public static void main(String[] args) {
        convertNum();
    }

    /**
     * 原理：ip地址的每段可以看成是一个0-255的整数，把每段拆分成一个二进制形式组合起来，然后把这个二进制数转变成
     * 一个长整数。
     * 举例：一个ip地址为10.0.3.193
     * 每段数字             相对应的二进制数
     * 10                   00001010
     * 0                    00000000
     * 3                    00000011
     * 193                  11000001
     * 组合起来即为：00001010 00000000 00000011 11000001,转换为10进制数就是：167773121，即该IP地址转换后的数字就是它了。
     *
     * 输入
     * 1 输入IP地址
     * 2 输入10进制型的IP地址
     *
     * 输出
     * 1 输出转换成10进制的IP地址
     * 2 输出转换后的IP地址
     *
     * 10.0.3.193
     * 167969729
     *
     * 167773121
     * 10.3.3.193
     */
    public static void convertNum() {
        Scanner scanner = new Scanner(System.in);
        String[] ipStrings = scanner.nextLine().split("\\.");
        int ipNum = scanner.nextInt();
        scanner.close();
        int resIpNum = 0;
        StringBuilder resIpString = new StringBuilder();
        for (int i = 0; i < ipStrings.length; i++) {
            int num = Integer.parseInt(ipStrings[i]);
            resIpNum |= num << 8 * (3 - i);
        }

        for (int i = 0; i < 4; i++) {
            int move = 8 * (3 - i);
            // 注意运算顺序, 先位移255 再 与 ipNum, 在右移;
            // 注意右移时，需要带符号位
            resIpString.append((ipNum & (255 << move)) >>> move);
            if (i != 3) {
                resIpString.append(".");
            }
        }

        System.out.println(resIpNum);
        System.out.println(resIpString);
    }
}
