package com.fwhat.algorithm.other;

public class LastWordLen {
    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("   fly me   to   the moonm"));
    }

    public static int lengthOfLastWord(String s) {
        int lastLen = 0;
        boolean start = true;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                start = true;
            } else {
                if (start) {
                    lastLen = 1;
                } else {
                    lastLen ++;
                }

                start = false;
            }
        }
        return lastLen;
    }
}
