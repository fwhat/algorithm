package com.fwhat.algorithm.other;

import java.util.ArrayList;

public class Intersection {

    public static void main(String[] args) {
        intersection("1 3 2 4 4 8 5 9");
        intersection("1 6 2 5 5 7");
    }

    /**
     * 题目描述
     * 给若干个区间，区间数少于1000，区间范围[-10000,10000]。当区间数少于2个时输出None。
     * 当各个区间有交集的时候取交集，再求交集的并集
     *
     * 样例输入
     * 1 3 2 4 4 8 5 9
     * 样例输出
     * 2 3 4 4 5 8
     * 说明
     * [1,3]、[2,4]、[4,8]、[5,9] 四个区间
     * [1,3]与[2,4]交集为[2,3]，[1,3]与[4,8]、[5,9]没有交集
     * [2,4]与[4,8]]交集为[4,4]。[2,4]与[5,9]没有交集
     * [4,8]与[5,9]的交集为[5,8]
     * 所以最终的输出为[2,3]、[4,4]、[5,8]
     *
     * 样例输入
     * 1 2 3 4
     * 样例输出
     * None
     *
     * 样例输入
     * 1 6 2 5 5 7
     * 样例输出
     * 2 6
     * 说明
     * [1,6]、[2,5]的交集为[2,5]，[1,6]、[5,7]的交集为[5,6]
     * [2,5]、[5,7]的交集为[5,5]
     * 最后的输出为：2 6
     */
    public static void intersection(String s) {
        String[] s1 = s.split(" ");
        ArrayList<Region> regions = new ArrayList<>();

        for (int i = 0; i < s1.length; i += 2) {
            regions.add(new Region(Integer.parseInt(s1[i]), Integer.parseInt(s1[i+1])));
        }

        ArrayList<Region> regionIntersect = new ArrayList<>();

        for (int i = 0; i < regions.size(); i++) {
            for (int j = i + 1; j < regions.size(); j++) {
                Region intersect = regions.get(i).intersect(regions.get(j));
                if (intersect != null) {
                    regionIntersect.add(intersect);
                }
            }
        }

        ArrayList<Region> res = new ArrayList<>();
        while (regionIntersect.size() > 1) {
            Region compare = regionIntersect.get(0);
            boolean hasUnion = false;
            for (int i = 1; i < regionIntersect.size(); i++) {
                Region union = compare.union(regionIntersect.get(i));
                if (union != null) {
                    hasUnion = true;
                    regionIntersect.remove(i);
                    regionIntersect.remove(0);
                    regionIntersect.add(union);
                    break;
                }
            }
            if (!hasUnion) {
                res.add(compare);
                regionIntersect.remove(0);
            }
        }
        res.addAll(regionIntersect);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            stringBuilder.append(res.get(i).left).append(" ").append(res.get(i).right);
            if (i != res.size() - 1) {
                stringBuilder.append(" ");
            }
        }

        System.out.println(stringBuilder);
    }

    public static class Region {
        private final int right;
        private final int left;

        Region(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public Region intersect(Region other) {
            int maxLeft = Math.max(other.left, this.left);
            int minRight = Math.min(other.right, this.right);

            if (maxLeft <= minRight) {
                return new Region(maxLeft, minRight);
            }

            return null;
        }

        public Region union(Region other) {
            int maxLeft = Math.max(other.left, this.left);
            int minRight = Math.min(other.right, this.right);

            if (maxLeft <= minRight) {
                return new Region(Math.min(other.left, this.left), Math.max(other.right, this.right));
            }

            return null;
        }
    }
}
