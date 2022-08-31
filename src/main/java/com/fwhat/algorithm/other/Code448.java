package com.fwhat.algorithm.other;

import com.fwhat.algorithm.common.Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Code448 {
    public static void main(String[] args) {
        List<Integer> disappearedNumbers3 = (new Code448()).findDisappearedNumbers3(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
        System.out.println(Common.listIntString(disappearedNumbers3));;
    }

    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> l = new ArrayList<>();

        HashMap<Integer, Boolean> cache = new HashMap<>();
        for (int num : nums) {
            cache.put(num, true);
        }

        for (int i = 1; i <= nums.length; i++) {
            if (cache.get(i) == null) {
                l.add(i);
            }
        }

        return l;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> l = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1) {
                continue;
            }
            int find = nums[i];

            while (true) {
                int index = find - 1;
                if (nums[index] == -1) {
                    break;
                } else {
                    find = nums[index];
                    nums[index] = -1;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != -1) {
                l.add(i +1);
            }
        }
        return l;
    }

    public List<Integer> findDisappearedNumbers3(int[] nums) {
        List<Integer> l = new ArrayList<>();

        int n = nums.length;

        for (int i = 0; i < nums.length; i++) {
            int find = nums[i];
            if (find > n) {
                find = find - n;
            }
            if (nums[find - 1] < n) {
                nums[find - 1] = nums[find - 1] + n;
            }
            System.out.println(Arrays.toString(nums));
        }


        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= n) {
                l.add(i +1);
            }
        }
        return l;
    }

    public List<Integer> findDisappearedNumbers4(int[] nums) {
        List<Integer> l = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            nums[Math.abs(nums[i]) - 1] = -Math.abs(nums[Math.abs(nums[i])-1]);
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                l.add(i +1);
            }
        }
        return l;
    }


}
