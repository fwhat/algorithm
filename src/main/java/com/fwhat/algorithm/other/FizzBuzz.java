package com.fwhat.algorithm.other;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
    public static void main(String[] args) {

    }

    public List<String> fizzBuzz(int n) {
        ArrayList<String> res = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                if (i % 5 == 0) {
                    res.add("FizzBuzz");
                } else {
                    res.add("Fizz");
                }
            } else if (i % 5 == 0) {
                res.add("Buzz");
            } else {
                res.add(String.valueOf(i));
            }
        }

        return res;
    }
}
