package com.fwhat.algorithm.other;

import java.util.HashMap;

public class MajorityElement {
    /**
     * 给定一个大小为 n 的数组nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于[ n/2 ]的元素。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     */
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> cache = new HashMap<>();
        int max = 0;
        cache.put(max, 0);
        for (int num : nums) {
            int count;
            if (cache.containsKey(num)) {
                count = cache.get(num);
                count++;
            } else {
                count = 1;
            }

            if (count > cache.get(max)) {
                max = num;
            }
            cache.put(num, count);
        }

        return max;
    }

    public int majorityElement2(int[] nums) {
        int count = 0;
        int v = nums[0];
        for (int num : nums) {
            if (count == 0) {
                v = num;
            }

            if (num == v) {
                count ++;
            } else {
                count --;

            }
        }

        return v;
    }
}
