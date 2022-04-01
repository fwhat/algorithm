package com.fwhat.algorithm.other;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class SplitStudentClass {

    public static void main(String[] args) {

        splitClass();
    }

    /**
     * 幼儿园两个班的小朋友在排队时混在了一起，每位小朋友都知道自己是否与前面一位小朋友是否同班，请你帮忙把同班的小朋友找出来。
     *
     * 小朋友的编号为整数，与前一位小朋友同班用Y表示，不同班用N表示。
     *
     * 输入描述:
     *
     * 输入为空格分开的小朋友编号和是否同班标志。
     *
     * 比如：6/N 2/Y 3/N 4/Y，表示共4位小朋友，2和6同班，3和2不同班，4和3同班。
     *
     * 其中，小朋友总数不超过999，每个小朋友编号大于0，小于等于999。
     *
     * 不考虑输入格式错误问题。
     *
     * 输出描述:
     *
     * 输出为两行，每一行记录一个班小朋友的编号，编号用空格分开。且：
     *
     * 编号需要按照大小升序排列，分班记录中第一个编号小的排在第一行。
     * 若只有一个班的小朋友，第二行为空行。
     * 若输入不符合要求，则直接输出字符串ERROR。
     * 示例1：
     *
     * 输入
     *
     * 1/N 2/Y 3/N 4/Y
     *
     * 输出
     *
     * 1 2
     * 3 4
     */
    public static void splitClass() {
        Scanner scanner = new Scanner(System.in);
        String[] students = scanner.nextLine().split(" ");
        ArrayList<String> class1 = new ArrayList<>();
        ArrayList<String> class2 = new ArrayList<>();

        class1.add(students[0].split("/")[0]);

        boolean lastIn1 = true;
        for (int i = 1; i < students.length; i++) {
            String[] split = students[i].split("/");

            if (split[1].equals("Y")) {
                if (lastIn1) {
                    class1.add(split[0]);
                } else {
                    class2.add(split[0]);
                }
            } else {
                if (lastIn1) {
                    class2.add(split[0]);
                    lastIn1 = false;
                } else {
                    class1.add(split[0]);
                    lastIn1 = true;
                }
            }
        }
        for (String s : class1) {
            System.out.print(s + " ");
        }
        System.out.println();
        for (String s : class2) {
            System.out.print(s + " ");
        }
        scanner.close();
    }
}
