package com.fwhat.algorithm.other;

public class AddBinary {
    public static void main(String[] args) {
        System.out.println(addBinary("1010", "1011"));;
    }

    public static String addBinary(String a, String b) {
        int max = Math.max(a.length(), b.length());

        StringBuilder builder = new StringBuilder();

        int addNum = 0;
        for (int i = 0; i < max; i++) {
            char ai = '0';
            char bi = '0';
            if (i <= a.length() - 1) {
                ai = a.charAt(a.length() - 1 - i);
            }

            if (i <= b.length() - 1) {
                bi = b.charAt(b.length() - 1 - i);
            }
            int add = charAdd(ai, bi) + addNum;

            if (add >= 2) {
                addNum = 1;
                builder.insert(0, intToChar(add - 2));
            } else {
                addNum = 0;
                builder.insert(0, intToChar(add));
            }
        }

        if (addNum > 0) {
            builder.insert(0, '1');
        }

        return builder.toString();
    }

    public static int charAdd(char a, char b) {
        return charToInt(a) + charToInt(b);
    }

    public static int charToInt(char a) {
        return a == '0' ? 0 : 1;
    }

    public static char intToChar(int a) {
        return a == 0 ? '0' : '1';
    }
}
