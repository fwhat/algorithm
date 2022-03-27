package com.fwhat.algorithm;

public class Assert {
    public static void right(boolean right, String errorMsg) {
        if (!right) {
            throw new RuntimeException(errorMsg);
        }
    }

    public static void right(boolean right) {
        if (!right) {
            throw new RuntimeException("assert fail");
        }
    }

    public static void equal(int a, int b) {
        if (a != b) {
            throw new RuntimeException("assert fail: " + a + " != " + b);
        }
    }

    public static void notEqual(int a, int b) {
        if (a == b) {
            throw new RuntimeException("assert fail: " + a + " != " + b);
        }
    }
}
