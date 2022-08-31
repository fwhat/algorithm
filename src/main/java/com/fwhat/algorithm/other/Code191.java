package com.fwhat.algorithm.other;

import static com.fwhat.algorithm.common.Common.binaryString;

public class Code191 {
    public static void main(String[] args) {
        System.out.println(binaryString(1));;

        System.out.println((new Code191()).hammingWeight(1));
    }

    public int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (((1 << i) & n) != 0) {
                res ++;
            }
        }

        return res;
    }


}
