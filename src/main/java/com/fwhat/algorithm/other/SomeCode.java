package com.fwhat.algorithm.other;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class SomeCode {

    public static void main(String[] args) {
        code1(1, 20);
        code2(3);
        System.out.println();
        code3(new String[] {
                "2 2",
                "2 1",
                "3 2",
                "5 2",
                "3 1",
                "7 2",
        });
    }

    /**
     * 勾股数元组
     * 如果3个正整数(a,b,c)满足a^2 + b^2 = c^2的关系，则称(a,b,c)为勾股数（著名的勾三股四弦五），为了探索勾股数的规律，
     * 我们定义如果勾股数(a,b,c)之间两两互质（即a与b，a与c，b与c之间均互质，没有公约数），
     * 则其为勾股数元组（例如(3,4,5)是勾股数元组，(6,8,10)则不是勾股数元组）。请求出给定范围[N,M]内，所有的勾股数元组。
     * 
     * 输入描述:
     * 
     * 起始范围N，1 <= N <= 10000
     * 结束范围M，N < M <= 10000
     * 输出描述:
     * 
     * a,b,c请保证a < b < c,输出格式：a b c；
     * 多组勾股数元组请按照a升序，b升序，最后c升序的方式排序输出；
     * 给定范围中如果找不到勾股数元组时，输出"NA"。
     * 示例
     * 
     * 输入
     * 
     * 1
     * 20
     * 输出
     * 
     * 3 4 5
     * 5 12 13
     * 8 15 17
     * 说明
     * 
     * [1, 20]范围内勾股数有：(3 4 5)，(5 12 13)，(6 8 10)，(8 15 17)，(9 12 15)，(12 16 20)；
     * 其中，满足(a,b,c)之间两两互质的勾股数元组有：(3 4 5)，(5 12 13)，(8 15 17)；
     * 按输出描述中顺序要求输出结果。
     */
    public static void code1(int n, int m) {
        int begin = Math.max(n, 3);

        for (int a = begin; a <= m; a++) {
            for (int b = a + 1; b <= m; b++) {
                if (hasCommonDivisor(a, b)) {
                    continue;
                }
                int sum = a * a + b * b;
                int c = (int) Math.sqrt(sum);
                if (c <= m && c * c == sum && !hasCommonDivisor(b, c) && !hasCommonDivisor(a, c)) {
                    System.out.println(a + " " + b + " " + c);
                }
            }
        }
    }

    private static boolean hasCommonDivisor(int a, int b) {
        ArrayList<Integer> divA = new ArrayList<>();

        for (int i = 2; i <= a; i++) {
            if (a % i == 0) {
                divA.add(i);
            }
        }

        for (Integer integer : divA) {
            if (b % integer == 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * 100个人围成一圈，每个人有一个编码，编号从1开始到100。
     * 
     * 他们从1开始依次报数，报到为M的人自动退出圈圈，然后下一个人接着从1开始报数，直到剩余的人数小于M。 请问最后剩余的人在原先的编号为多少？
     * 
     * 输入描述:
     * 
     * 输入一个整数参数 M
     * 
     * 输出描述:
     * 
     * 如果输入参数M小于等于1或者大于等于100，输出“ERROR!”；
     * 
     * 否则按照原先的编号从小到大的顺序，以英文逗号分割输出编号字符串
     * 
     * 示例1：
     * 
     * 输入
     * 3
     * 输出
     * 
     * 58,91
     */
    public static void code2(int m) {
        if (m <= 1 || m >= 100) {
            System.out.println("ERROR");
            return;
        }
        ArrayList<Code2User> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(new Code2User(i));
        }
        int size = 100;
        int index = 0;
        while (size >= m) {
            int removeIndex = index + m - 1;
            if (removeIndex >= size) {
                removeIndex = removeIndex - size;
            }
            list.remove(removeIndex);
            // 注意这里 删除了元素后，array list 会自动前移; 所以不需要 removeIndex + 1
            index = removeIndex;
            size--;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(list.get(i).num);
            if (i != size - 1) {
                System.out.print(",");
            }
        }
    }

    private static class Code2User {
        public int num;

        Code2User(int num) {
            this.num = num;
        }
    }

    /**
     * 分糖果
     * 幼儿园小朋友站成一列，按位置1、2、3....顺序编号，每个小朋友都拿了若干糖果，请找出3个小朋友，他们拿着相同颜色的糖果，
     * 且他们拿的糖果总数不少于其他任何小朋友（拿相同颜色的糖果）的糖果总数，如果存在多组这样的小朋友，则找出位置编号最小的小朋友所在的组。
     * 
     * 设置的前提条件：
     * 
     * （1）每个小朋友最少拿一颗糖，最多拿1024颗糖，且只拿一种颜色的糖果；不存在两个小朋友拿相同颜色相同树木的糖果。
     * 
     * （2）糖果颜色只有2种：1为红色，2为蓝色。
     * 
     * 输出描述：
     * 
     * 拿相同颜色且糖果总数最多的3位小朋友位置编号，糖果颜色及总数；第一行为3个小朋友位置编号（糖果数从小到大对应的位置编号） ，第二行为糖果颜色，第三行为糖果总数。
     * 
     * 如果没有满足条件的小朋友，则输出字符串“null”。
     * 
     * 示例1：
     * 
     * 输入
     * 
     * 6
     * 2 2
     * 2 1
     * 3 2
     * 5 2
     * 3 1
     * 7 2
     * 
     * 输出
     * 
     * 3 4 6
     * 2
     * 15
     */
    public static void code3(String[] strings) {
        HashMap<Integer, Student> map1 = new HashMap<>();
        HashMap<Integer, Student> map2 = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            String str = strings[i];
            String[] s = str.split(" ");
            int count = Integer.parseInt(s[0]);

            if (s[1].equals("1") && !map1.containsKey(count)) {
                map1.put(count, new Student(i + 1, count));
            }
            if (s[1].equals("2") && !map2.containsKey(count)) {
                map2.put(count, new Student(i + 1, count));
            }
        }

        int top3 = 0;
        int res = 0;
        Student[] studentTop = new Student[3];

        if (map1.size() >= 3) {
            Student[] student1 = map1.values().toArray(new Student[0]);

            Arrays.sort(student1, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return Integer.compare(o2.count, o1.count);
                }
            });

            top3 = student1[0].count + student1[1].count + student1[2].count;
            System.arraycopy(student1, 0, studentTop, 0, 3);
            res = 1;
        }

        if (map2.size() >= 3) {
            Student[] student2 = map2.values().toArray(new Student[0]);

            Arrays.sort(student2, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return Integer.compare(o2.count, o1.count);
                }
            });

            int top23 = student2[0].count + student2[1].count + student2[2].count;
            if (top23 > top3 || (top23 == top3 && student2[0].num < studentTop[0].num)) {
                top3 = top23;
                res = 2;
                System.arraycopy(student2, 0, studentTop, 0, 3);
            }
        }

        if (top3 == 0) {
            System.out.println("null");
        } else {
            Arrays.sort(studentTop, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return Integer.compare(o1.num, o2.num);
                }
            });
            for (int i = 0; i < studentTop.length; i++) {
                System.out.print(studentTop[i].num);
                if (i != studentTop.length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            System.out.println(res);
            System.out.println(top3);
        }
    }

    private static class Student {
        int num;
        int count;

        Student(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    /**
     * 用数组代表每个人的能力
     * 一个比赛活动要求 参赛团队的最低能力值为N
     * 每个团队可以由一人或者两人组成
     * 且一个人只能参加一个团队
     * 计算出最多可以派出多少只符合要求的队伍
     *
     * 输入描述
     * 5
     * 3 1 5 7 9
     * 8
     * 第一行代表总人数，范围  1~500000
     * 第二行数组代表每个人的能力
     *     数组大小范围 1~500000
     *     元素取值范围 1~500000
     * 第三行数值为团队要求的最低能力值
     * 1~500000
     *
     * 输出描述
     * 3
     * 最多可以派出的团队数量
     *
     * 示例一
     * 输入
     * 5
     * 3 1 5 7 9
     * 8
     *
     * 输出
     * 3
     *
     * 说明 3、5组成一队   1、7一队  9自己一队  输出3
     *
     * 7
     * 3 1 5 7 9 2 6
     * 8
     *
     * 3
     * 1 1 9
     * 8
     */
    public static void code4() {
        // todo
    }
}
