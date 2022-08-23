package com.fwhat.algorithm.io;

public class Read4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {

        char[] buf4 = new char[4];
        int readN = 0;
        while (readN < n) {
            int read = read4(buf4);

            System.arraycopy(buf4, 0, buf, readN, Math.min((n - readN), read));
            readN += read;

            if (read < 4) {
                break;
            }
        }


        return readN;
    }

    public static int read4(char[] buf) {
        return 0;
    }
}
