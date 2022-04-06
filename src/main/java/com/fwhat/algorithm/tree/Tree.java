package com.fwhat.algorithm.tree;

import java.util.Stack;

public class Tree {

    public static void main(String[] args) {
        minNodePath(new int[] {3, 5, 7, -1, -1, 2, 4});
        minNodePath(new int[] {5, 9, 8, -1, -1, 7, -1, -1, -1, -1, -1, 6});
    }

    public static void minNodePath(int[] arr) {
        int len = arr.length;
        int minNode = arr[1];
        int minNodeIndex = 1;
        for (int i = 2; i < len; i++) {
            if (arr[i] != -1 && arr[i] < minNode) {
                minNodeIndex = i;
                minNode = arr[i];
            }
        }
        minNodeIndex = minNodeIndex + 1;
        Stack<Integer> list = new Stack<>();
        while (minNodeIndex >= 1) {
            list.add(minNodeIndex);
            if (minNodeIndex % 2 == 0) {
                minNodeIndex = minNodeIndex / 2;
            } else {
                minNodeIndex = (minNodeIndex - 1) / 2;
            }
        }

        while (!list.isEmpty()) {
            System.out.print(arr[list.pop() - 1] + " ");
        }
    }
}
