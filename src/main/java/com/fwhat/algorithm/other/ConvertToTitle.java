package com.fwhat.algorithm.other;

public class ConvertToTitle {
    public static void main(String[] args) {
        System.out.println(convertToTitle(52));;
    }

    public static String convertToTitle(int columnNumber) {
        StringBuilder str = new StringBuilder();

        for (int i = 6; i >= 0; i--) {
            int pow = (int) Math.pow(26, i);
            int lowerSum = 0;
            for (int ii = i - 1; ii >=0; ii --) {
                lowerSum += (int) Math.pow(26, ii);
            }

            if (columnNumber >= (pow + lowerSum)) {
                int near = 0;
                for (int j = 1; j <= 26; j++) {
                    if ((pow * j + lowerSum) > columnNumber || pow * j < 0) {
                        near = j-1;
                        break;
                    }
                }

                if (near == 0) {
                    near = 26;
                }

                str.append(Character.toString((char) near + 64));
                columnNumber -= (pow * near);
            }
        }

        return str.toString();
    }

}
