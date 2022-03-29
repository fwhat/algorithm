package com.fwhat.algorithm.bitmap;

public class BitMap {
    long[] bits;

    BitMap(int max) {
        bits = new long[(max / 64) + 1];
    }

    public void add(int num) {
        // 找到 num 应该在的 bits[n]
        // 将对应的 bit 设置为1
        // num & 63 == num & 63
        bits[num/ 64] |= 1L << (num & 63);
    }

    public void remove(int num) {
        // 找到 num 应该在的 bits[n]
        // 将对应的 bit 设置为0
        bits[num/ 64] &= ~1L << (num & 63);
    }

    public boolean contain(int num) {
        return (bits[num / 64] & (1L << (num & 63))) != 0;
    }

    public static void main(String[] args) {
        var map = new BitMap(1000000000);
        map.add(10);
        System.out.println(map.contain(10));
        System.out.println(map.contain(79));
        map.add(1000000000);
        System.out.println(map.contain(1000000000));
    }
}
