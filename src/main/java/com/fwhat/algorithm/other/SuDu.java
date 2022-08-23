package com.fwhat.algorithm.other;

import java.util.HashMap;

public class SuDu {

    public static void main(String[] args) {
        char[][] v = new char[][]{
                new char[]{'5','3','.','.','7','.','.','.','.'},
                new char[]{'6','.','.','1','9','5','.','.','.'},
                new char[]{'.','9','8','.','.','.','.','6','.'},
                new char[]{'8','.','.','.','6','.','.','.','3'},
                new char[]{'4','.','.','8','.','3','.','.','1'},
                new char[]{'7','.','.','.','2','.','.','.','6'},
                new char[]{'.','6','.','.','.','.','2','8','.'},
                new char[]{'.','.','.','4','1','9','.','.','5'},
                new char[]{'.','.','.','.','8','.','.','7','9'},
        };

        System.out.println(isValidSudoku(v));
    }

    public static boolean isValidSudoku(char[][] board) {
        int[][] line = new int[9][10];
        int[][] row = new int[9][10];
        int[][] box = new int[9][10];

        for (int i = 0; i < 9 ; i++) {
            for (int j = 0; j < 9; j++) {
                char v = board[i][j];
                if (v == '.') {
                    continue;
                }

                int value = Character.digit(v, 10);

                if (line[i][value] != 0) {
                    return false;
                }

                if (row[j][value] != 0) {
                    return false;
                }

                // 2, 2 -> 0
                // 3, 3 -> 4
                // 8, 8 -> 8
                int ind = i / 3 + (j / 3) * 3;
                if (box[ind][value] != 0) {
                    return false;
                }

                line[i][value] = value;
                row[j][value] = value;
                box[ind][value] = value;
            }
        }

        return true;
    }
}
