package com.fwhat.algorithm.tree;

import com.fwhat.algorithm.Time100;

import java.util.Stack;

public class Tree {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static void main(String[] args) {
        minNodePath(new int[]{3, 5, 7, -1, -1, 2, 4});
        minNodePath(new int[]{5, 9, 8, -1, -1, 7, -1, -1, -1, -1, -1, 6});
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


    public void eachPre(TreeNode head) {
        if (head == null) {
            return;
        }

        System.out.println(head.val);
        eachPre(head.left);
        eachPre(head.right);
    }

    public void eachIn(TreeNode head) {
        if (head == null) {
            return;
        }

        eachIn(head.left);
        System.out.println(head.val);
        eachIn(head.right);
    }

    public void eachEnd(TreeNode head) {
        if (head == null) {
            return;
        }

        eachEnd(head.left);
        System.out.println(head.val);
        eachEnd(head.right);
    }

    /**
     * https://leetcode-cn.com/problems/same-tree/
     * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     * <p>
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     */
    @Time100
    public Boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }

        // p == null && q == null; 当a为null时，b一定为null；因为在上一步判断；p == null ^ q == null
        if (p == null) {
            return true;
        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * https://leetcode-cn.com/problems/symmetric-tree/
     *
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     */
    @Time100
    public Boolean isSymmetric(TreeNode p) {
        return isMirror(p, p);
    }

    private Boolean isMirror(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }

        // p == null && q == null; 当a为null时，b一定为null；因为在上一步判断；p == null ^ q == null
        if (p == null) {
            return true;
        }

        return p.val == q.val && isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }
}
