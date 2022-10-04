package com.fwhat.algorithm.other;

import java.util.HashMap;

public class Code202 {
    public static void main(String[] args) {

    }

    public boolean isHappy(int n) {
        HashMap<Integer, Boolean> cache = new HashMap<>();

        int res = getSum(n);
        cache.put(res, true);

        while (res != 1) {
            res = getSum(res);
            if (cache.containsKey(res)) {
                return false;
            }
            cache.put(res, true);
        }

        return true;
    }

    public static int getSum(int n) {
        int res = 0;

        while (n > 0) {
            res += Math.pow(n % 10, 2);
            n/= 10;
        }

        return res;
    }
}
