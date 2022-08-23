package com.fwhat.algorithm.other;

/**
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * ZY -> 701
 */
public class TitleToNumber {
    public static void main(String[] args) {
        System.out.println((new TitleToNumber()).titleToNumber("ZY"));
    }

    public int titleToNumber(String columnTitle) {
        int value = 0;

        int length = columnTitle.length();
        for (int i = 0; i < length; i++) {
            value += (Math.pow(26, length - 1 -i) * (columnTitle.charAt(i) - 64));
        }

        return value;
    }
}
