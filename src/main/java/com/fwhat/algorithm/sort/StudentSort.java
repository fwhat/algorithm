package com.fwhat.algorithm.sort;

import java.util.Arrays;
import java.util.Comparator;

public class StudentSort {
    public static void main(String[] args) {
        heightWeightSort(new int[]{100, 100, 120, 130}, new int[]{40, 30, 60, 50});
        System.out.println();
        heightWeightSort(new int[]{90, 110, 90}, new int[]{45, 60, 45});
    }

    /**
     * 某学校举行运动会,学生们按编号（1、2、3…n)进行标识现需要按照身高由低到高排列，对身高相同的人，按体重由轻到重排列，对于身高体重都相同的人，维持原有的编号顺序关系。
     * 请输出排列后的学生编号
     * 输入描述：
     * <p>
     * 两个序列，每个序列由N个正整数组成，(0<n<=100)。
     * 第一个序列中的数值代表身高
     * 第二个序列中的数值代表体重
     * 输出描述：
     * <p>
     * 排列结果，每个数值都是原始序列中的学生编号，编号从1开始，身高从低到高，身高相同体重从轻到重，体重相同维持原来顺序。
     * 示例：
     * <p>
     * 输入：
     * <p>
     * 4
     * 100 100 120 130
     * 40 30 60 50
     * 输出：
     * <p>
     * 2134
     * 输入：
     * <p>
     * 3
     * 90 110 90
     * 45 60 45
     * 输出：
     * <p>
     * 132
     */
    public static void heightWeightSort(int[] heights, int[] weights) {
        Student[] students = new Student[heights.length];

        for (int i = 0; i < heights.length; i++) {
            students[i] = new Student(i+1, heights[i], weights[i]);
        }

        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int compare = Integer.compare(o1.getHeight(), o2.getHeight());
                if (compare == 0) {
                    return Integer.compare(o1.getWeight(), o2.getWeight());
                }

                return compare;
            }
        });

        for (Student student : students) {
            System.out.print(student.getIndex());
        }
    }

    public static class Student {
        private int height;
        private int weight;
        private int index;

        Student(int index, int height, int weight) {
            this.height = height;
            this.weight = weight;
            this.index = index;
        }

        public int getHeight() {
            return height;
        }

        public int getWeight() {
            return weight;
        }

        public int getIndex() {
            return index;
        }
    }
}
