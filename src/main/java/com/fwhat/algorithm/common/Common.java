package com.fwhat.algorithm.common;


import java.util.List;

public class Common {
    public static String binaryString(int num) {
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

    public static String listIntString(List<Integer> arr) {
        StringBuilder str = new StringBuilder();

        for (Integer integer : arr) {
            str.append(integer);
            str.append(",");
        }

        return str.toString();
    }
}
