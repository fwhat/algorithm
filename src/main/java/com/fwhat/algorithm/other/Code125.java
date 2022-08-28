package com.fwhat.algorithm.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Code125 {
    public static void main(String[] args) {
        System.out.println(isPalindrome("race a car"));
    }

    public static boolean isPalindrome(String s) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                if (ch >= 97) {
                    list.add(ch - 32);
                } else {
                    list.add((int) ch);
                }
            }
        }

        int sp = list.size() / 2;
        for (int i = 0; i < sp; i++) {
            if (!Objects.equals(list.get(i), list.get(list.size() - 1 - i))) {
                return false;
            }
        }

        return true;
    }
}
