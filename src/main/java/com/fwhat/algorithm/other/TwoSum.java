package com.fwhat.algorithm.other;

import java.util.HashMap;
import java.util.Set;
import java.util.function.Consumer;

/**
 * -10^5 <= number <= 10^5
 * -23^1 <= value <= 23^1 - 1
 * 最多调用 10^4 次 add 和 find 最多10000个数
 */
public class TwoSum {

    public static void main(String[] args) {

        TwoSum twoSum = new TwoSum();
        twoSum.add(0);
        twoSum.add(-1);
        twoSum.add(1);
        System.out.println(twoSum.find(0));
    }

    HashMap<Integer, Integer> cache = new HashMap<>();

    public TwoSum() {
    }

    public void add(int number) {
        if (cache.containsKey(number)) {
            cache.put(number, cache.get(number) + 1);
        } else {
            cache.put(number, 1);
        }
    }

    public boolean find(int value) {
        Set<Integer> integers = cache.keySet();
        for (Integer integer : integers) {
            int sub = value - integer;
            if (cache.containsKey(sub)) {
                if (sub == integer) {
                    if (cache.get(integer) > 1) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }

        return false;
    }
}
