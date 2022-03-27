package com.fwhat.algorithm;

/**
 * 阶乘和 1!+2!+3!+...+N!
 */
public class FactorialSum {
    public static void main(String[] args) {
        Assert.equal(sum(5), 153);
    }

    public static int sum(int last) {
        var preSum = 1;
        var sum = 0;
        for (var i = 1; i <= last; i ++) {
            preSum = preSum * i;
            sum += preSum;
        }

        return sum;
    }
}
