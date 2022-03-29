package com.fwhat.algorithm.binary;

public class BinaryOperation {

    public static void main(String[] args) {
        System.out.println(add(10, 20));
        System.out.println(add(100000, 20));
        System.out.println(add(10, 10));
        System.out.println(sub(10, 10));
        System.out.println(sub(100010, 10));
        System.out.println(multi(-10, -10));
    }

    public static int add(int a, int b) {
        var sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }

        return sum;
    }

    public static int absolute(int a) {
        if (a < 0) {
            return add(~a, 1);
        } else {
            return a;
        }
    }

    public static boolean isNeg(int a) {
        return a < 0;
    }

    public static int sub(int a, int b) {
        return add(a, add(~b, 1));
    }

    public static int multi(int a, int b) {
        int res = 0;

        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a = a << 1;
            b = b >>> 1;
        }

        return res;
    }

    /**
     * todo
     */
    public static int div(int a, int b) {
        return 0;
    }
}
