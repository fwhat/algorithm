package com.fwhat.algorithm.sort;

import java.lang.reflect.Array;
import java.util.*;

public class DiskSort {

    public static void main(String[] args) {
        diskSort();
    }

    /**
     * 磁盘容量排序
     * 题目描述
     * <p>
     * 磁盘的容量单位常用的有M，G，T这三个等级，它们之间的换算关系为1T = 1024G，1G = 1024M，
     * 现在给定n块磁盘的容量，请对它们按从小到大的顺序进行稳定排序，
     * 例如给定5块盘的容量，1T，20M，3G，10G6T，3M12G9M排序后的结果为20M，3G，3M12G9M，1T，10G6T。
     * 注意单位可以重复出现，上述3M12G9M表示的容量即为3M+12G+9M，和12M12G相等。
     * 输入描述:
     * 输入第一行包含一个整数n(2 <= n <= 100)，表示磁盘的个数，
     * 接下的n行，每行一个字符串(长度大于2，小于30)，表示磁盘的容量，
     * 由一个或多个格式为mv的子串组成，其中m表示容量大小，v表示容量单位，
     * 例如20M，1T，30G，10G6T，3M12G9M。
     * 磁盘容量m的范围为1到1024的正整数，容量单位v的范围只包含题目中提到的M，G，T三种，换算关系如题目描述。
     * <p>
     * 输出描述:
     * 输出n行，表示n块磁盘容量排序后的结果。
     * <p>
     * 示例1：
     * 输入
     * 3
     * 1G
     * 2G
     * 1024M
     * 输出
     * <p>
     * 1G
     * 1024M
     * 2G
     * 说明
     * <p>
     * 1G和1024M容量相等，稳定排序要求保留它们原来的相对位置，故1G在1024M之前
     */
    public static void diskSort() {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        StringBuilder tempNumStr = new StringBuilder();

        HashMap<Integer, ArrayList<String>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String disk = scanner.nextLine();
            char[] chars = disk.toCharArray();

            int diskVal = 0;
            for (char aChar : chars) {
                switch (aChar) {
                    case 'T':
                        diskVal += Integer.parseInt(tempNumStr.toString()) * 1024 * 1024;
                        tempNumStr.delete(0, tempNumStr.length());
                        break;
                    case 'G':
                        diskVal += Integer.parseInt(tempNumStr.toString()) * 1024;
                        tempNumStr.delete(0, tempNumStr.length());
                        break;
                    case 'M':
                        diskVal += Integer.parseInt(tempNumStr.toString());
                        tempNumStr.delete(0, tempNumStr.length());
                        break;
                    default:
                        tempNumStr.append(aChar);
                }
            }
            if (map.containsKey(diskVal)) {
                map.get(diskVal).add(disk);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(disk);
                map.put(diskVal, list);
            }
        }
        Integer[] objects = map.keySet().toArray(new Integer[0]);
        Arrays.sort(objects);
        for (Integer object : objects) {
            ArrayList<String> strings = map.get(object);
            for (String string : strings) {
                System.out.println(string);
            }
        }

        scanner.close();
    }
}
