package com.fwhat.algorithm.other;

import java.util.ArrayList;
import java.util.List;

public class Code119 {

    public static void main(String[] args) {
        List<Integer> row = getRow2(3);
        for (Integer integer : row) {
            System.out.println(integer);
        }
    }

    // 递归很慢
    public static List<Integer> getRow2(int rowIndex) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i <= rowIndex; i++) {
            res.add(getVal(rowIndex, i));
        }

        return res;
    }

    public static Integer getVal(int row, int cell) {
        if (row < 0) {
            return 0;
        }

        if (cell < 0) {
            return 0;
        }

        if (row == 0 && cell == 0) {
            return 1;
        }



        return getVal(row - 1, cell - 1) + getVal(row - 1, cell);
    }


    public static List<Integer> getRow(int rowIndex) {
        List<Integer> lastRow = new ArrayList<>();
        lastRow.add(1);

        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> currentRow = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                Integer val = 0;
                if (j != 0) {
                    val = lastRow.get(j-1);
                }

                if (j < lastRow.size()) {
                    val += lastRow.get(j);
                }

                currentRow.add(val);
            }

            if (rowIndex == i) {
                return currentRow;
            } else {
                lastRow = currentRow;
            }
        }

        return lastRow;
    }
}
