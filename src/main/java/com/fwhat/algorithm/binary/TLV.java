package com.fwhat.algorithm.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TLV {
    public static void main(String[] args) {
        System.out.println(findTVL("31", "32 01 00 AE 90 02 00 01 02 30 03 00 AB 32 31 31 02 00 32 33 33 01 00 CC"));
    }

    /**
     * 题目描述
     * TLV 编码是按 [ Tag Length Value ] 格式进行编码的，一段码流中的信元用Tag标识， Tag在码流中 唯一不重复 ，Length表示信元Value的长度，Value表示信元的值。
     * 码流以某信元的Tag开头，Tag固定占 一个字节，Length固定占 两个字节，字节序为 小端序 。
     * 现给定TLV格式编码的码流，以及需要解码的信元Tag，请输出该信元的Value。
     * 输入码流的16进制字符中，不包括小写字母，且要求输出的16进制字符串中也不要包含小写字母；
     * 码流字符串的最大长度不超过50000个字节。
     * 输入描述:
     * 输入的第一行为一个字符串，表示待解码信元的 Tag ；
     * 输入的第二行为一个字符串，表示待解码的 16进制码流 ，字节之间用 空格分隔 。
     * 输出描述:
     * 输出一个字符串，表示待解码信元以16进制表示的 Value 。
     * 输入
     * 31
     * 32 01 00 AE 90 02 00 01 02 30 03 00 AB 32 31 31 02 00 32 33 33 01 00 CC
     * 输出
     * 32 33
     */
    public static String findTVL(String tag, String bytes) {
        String[] s = bytes.split(" ");
        int index = 0;

        ByteBuffer allocate = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN);

        while (index < s.length) {
            String currentTag = s[index];
            allocate.put(0, Byte.parseByte(s[index + 1], 16));
            allocate.put(1, Byte.parseByte(s[index + 2], 16));
            int len = allocate.getShort(0);

            if (currentTag.equals(tag)) {
                StringBuilder builder = new StringBuilder();
                for (int i = 1; i <= len; i++) {
                    builder.append(s[index + 2 + i]).append(" ");
                }

                return builder.toString();
            } else {
                index += len + 3;
            }
        }

        return "";
    }
}
