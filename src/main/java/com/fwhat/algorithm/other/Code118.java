package com.fwhat.algorithm.other;

import com.fwhat.algorithm.Time100;

import java.util.ArrayList;
import java.util.List;

public class Code118 {
    public static void main(String[] args) {
        print(generate(5));
    }

    @Time100
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>(numRows);
        List<Integer> first = new ArrayList<>(1);
        first.add(1);
        res.add(first);

        for (int i = 1; i < numRows; i++) {
            List<Integer> lastRow = res.get(i - 1);

            List<Integer> row = new ArrayList<>(i);
            for (int j = 0; j <= i; j++) {
                Integer val = 0;
                if (j != 0) {
                    val = lastRow.get(j - 1);
                }

                if (j < lastRow.size()) {
                    val +=  lastRow.get(j);
                }

                row.add(val);
            }

            res.add(row);
        }

        return res;
    }

    public static void print(List<List<Integer>> val) {
        for (List<Integer> integers : val) {
            for (Integer integer : integers) {
                System.out.print(integer);
            }

            System.out.println();
        }
    }
}
