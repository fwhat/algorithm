package com.fwhat.algorithm.sort;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class OtherSort {
    public static void main(String[] args) {
        arrayCountSort("1,3,3,3,2,4,4,4,5");
        System.out.println();
        charCountSort("xyxyXX");
    }

    /**
     * 数组去重和排序
     * 给定一个乱序的数组，删除所有的重复元素，使得每个元素只出现一次，并且按照出现的次数从高到低进行排序，相同出现次数按照第一次出现顺序进行先后排序。
     * <p>
     * 输入描述:
     * 一个数组
     * <p>
     * 输出描述:
     * 去重排序后的数组
     * <p>
     * 示例1
     * 输入
     * <p>
     * 1,3,3,3,2,4,4,4,5
     * 输出
     * <p>
     * 3,4,1,2,5
     * 备注:
     * <p>
     * 数组大小不超过100 数组元素值大小不超过100
     */
    public static void arrayCountSort(String str) {
        String[] split = str.split(",");
        HashMap<Integer, Member> map = new HashMap<>();

        for (String s : split) {
            int num = Integer.parseInt(s);
            if (map.containsKey(num)) {
                map.get(num).count++;
            } else {
                Member member = new Member();
                member.num = num;
                member.count = 1;

                map.put(num, member);
            }
        }
        Member[] members = map.values().toArray(new Member[]{});
        Arrays.sort(members, new Comparator<Member>() {
            @Override
            public int compare(Member o1, Member o2) {
                return Integer.compare(o2.count, o1.count);
            }
        });

        for (int i = 0; i < members.length; i++) {
            System.out.print(members[i].num);
            if (i != members.length - 1) {
                System.out.print(",");
            }
        }
    }

    private static class Member {
        public int num;
        public int count;
    }

    /**
     * 【字符统计及重排】给出一个仅包含字母的字符串，不包含空格，统计字符串中各个字母(区分大小写)出现的次数，并按照字母出现次数从大到小的顺序输出各个字母及
     * 其出现次数。如果次数相同，按照自然顺序进行排序，且小写字母在大写字母之前。
     * 输入描述:
     * 输入一行，为一个仅包含字母的字符串。
     * 输出描述:
     * 按照字母出现次数从大到小的顺序输出各个字母和字母次数，用英文分号分隔，注意末尾的分号;字母和次数间用英文冒号分隔。
     * 示例1:
     * 输入
     * xyxyXX
     * 输出
     * x:2;y:2;X:2;
     */
    public static void charCountSort(String str) {

        HashMap<Character, CharMember> map = new HashMap<>();

        int len = str.length();
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (map.containsKey(ch)) {
                map.get(ch).count++;
            } else {
                CharMember charMember = new CharMember();
                charMember.ch = ch;
                charMember.count = 1;

                map.put(ch, charMember);
            }
        }

        CharMember[] charMembers = map.values().toArray(new CharMember[]{});
        Arrays.sort(charMembers, new Comparator<CharMember>() {
            @Override
            public int compare(CharMember o1, CharMember o2) {
                int countRes = Integer.compare(o2.count, o1.count);
                // 注意审题 小写在前面, 但是在ascii 表中，小写数值更大; 不能直接比较
                if (countRes == 0) {
                    if ((Character.isUpperCase(o1.ch) && Character.isUpperCase(o2.ch))
                            || (Character.isLowerCase(o1.ch) && Character.isLowerCase(o2.ch))
                    ) {
                        return Character.compare(o1.ch, o2.ch);
                    }
                    if (Character.isLowerCase(o1.ch)) {
                        return -1;
                    } else {
                        return 1;
                    }
                }

                return countRes;
            }
        });
        for (CharMember charMember : charMembers) {
            System.out.print(charMember.ch);
            System.out.print(":");
            System.out.print(charMember.count);
            System.out.print(";");
        }
    }

    private static class CharMember {
        public char ch;
        public int count;
    }
}
