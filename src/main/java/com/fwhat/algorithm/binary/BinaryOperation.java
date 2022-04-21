package com.fwhat.algorithm.binary;

public class BinaryOperation {

    public static void main(String[] args) {
        System.out.println(add(10, 20));
        System.out.println(add(100000, 20));
        System.out.println(add(10, 10));
        System.out.println(sub(10, 10));
        System.out.println(sub(100010, 10));
        System.out.println(multi(-10, -10));
        System.out.println(div(100, 10));
    }

    /**
     * a + b = 无进位相加(a') + 进位信息(b')
     * 再计算 a' + b'
     * -> 直到进位信息为0
     * -> 答案就是a'
     */
    public static int add(int a, int b) {
        var sum = a;
        while (b != 0) {
            sum = a ^ b; // 无进位相加
            b = (a & b) << 1; // 进位信息
            a = sum;
        }

        return sum;
    }

    public static int absolute(int a) {
        if (a < 0) {
            return neg(a);
        } else {
            return a;
        }
    }

    public static int neg(int a) {
        // 负数等于该数 取反+1
        return add(~a, 1);
    }

    public static boolean isNeg(int a) {
        return a < 0;
    }

    /**
     * 相减就等于 a + -b
     */
    public static int sub(int a, int b) {
        return add(a, add(~b, 1));
    }

    /**
     * 0100110
     * 0000110
     * = 01001100 + 010011000
     * 如何取b中的1，就是将b 无符号右移，判断当前最末尾的数是不是 1; 判断的方法就是 b & 1 != 0
     * a 随着b 1的位置的放大，a也随之放大; 所以 b 右移的同时，a 左移放大
     */
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
     * a / b
     * a / b = c
     * a = b * c
     * a = b * (2^m + 2^n + 2^q ...)
     * 关键就是找到这些 m,n.. 的位置
     * a >= b * 2^m; (a < b* 2^(m+1))
     * 所以就是将a 左移一个数，当a >= b时，找到第一个位置m; res在m位置就是1
     * 再a- (b * 2^m) 即，a - b 向左移m位置
     * a- (b * 2^m) = b * (2^n + 2^q ...); 此时 a- (b * 2^m) -> a;
     * 因n < m;
     * 所以直接从m-1位置左移判断;
     * 直到a左移0位置
     * 注意1: 这个方法适合正数，所以先求a,b 的绝对值
     */
    private static int _div(int a, int b) {
        int x = absolute(a);
        int y = absolute(b);
        int res = 0;
        for (int i = 30; i >= 0; i--) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = sub(x, y << i);
            }
        }

        return isNeg(a) ^ isNeg(b) ? neg(res) : res;
    }

    /**
     * _div 的中异常处理
     * 由于 Integer.MIN_VALUE 无法转成绝对值; 所以特殊判断
     * a == b == Integer.MIN_VALUE -> 1;
     * b == Integer.MIN_VALUE -> 0;
     * a == Integer.MIN_VALUE
     *  1. b == -1 特殊处理，返回Integer.MAX_VALUE;
     *  2.
     *      转换成 Integer.Min_VALUE + 1 / b = c; (可以求绝对值)
     *      a - c * b; 求得差值d
     *      再由 c + d/b
     * _div(a/b)
     */
    public static int div(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            if (b == -1) {
                return Integer.MAX_VALUE;
            } else {
                int ans = _div(add(a, 1), b);
                return add(ans, _div(sub(a, multi(b, ans)), b));
            }
        } else {
            return _div(a, b);
        }
    }
}
