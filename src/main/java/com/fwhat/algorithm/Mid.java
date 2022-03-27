package com.fwhat.algorithm;

public class Mid {

    public static void main(String[] args) {
        Assert.equal(mid(0, 10), 5);
        Assert.equal(mid(0, 9), 4);
        Assert.equal(mid(1, 10), 5);
        Assert.equal(mid(1, 11), 6);

        // 2147483647
        Assert.equal(mid(2000000000, 2100000000), 2050000000);
        Assert.notEqual((2000000000 + 2100000000) / 2, 2050000000);
    }

    public static int mid(int L, int R) {
        return (R - L) / 2 + L;
    }
}
