package com.fwhat.algorithm.other;

public class Code190 {

    public static void main(String[] args) {
        Code190 code190 = new Code190();

        System.out.println(string(-3));
        int res = code190.reverseBits(-3);
        System.out.println(string(res));
    }


    /**
     * 00000010100101000001111010011100
     * 00111001011110000010100101000000
     */
    public int reverseBits(int n) {
        int res = 0;

        for (int i = 0; i < 32; i++) {
            if ((1 << i & n) != 0) {
                res ^= 1 << (31 - i);
            }
        }

        return res;
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
