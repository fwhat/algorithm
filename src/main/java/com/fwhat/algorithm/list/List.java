package com.fwhat.algorithm.list;

import java.util.Comparator;
import java.util.PriorityQueue;

public class List {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static class SingleList {
        private ListNode head = null;
        private ListNode tail = null;

        private int size = 0;

        public void add(int val) {
            var newNode = new ListNode(val);

            if (head == null) {
                head = newNode;
            } else {
                tail.next = newNode;
            }
            tail = newNode;
            size++;
        }

        public void add(int index, int val) {
            if (index > size) {
                throw new IndexOutOfBoundsException();
            }

            var newNode = new ListNode(val);
            if (index == 0) {
                newNode.next = head;
                head = newNode;
            }

            var cur = head;

            while (index > 1) {
                cur = cur.next;
                index--;
            }

            var oldNex = cur.next;
            cur.next = newNode;
            newNode.next = oldNex;
            size++;
        }

        public ListNode find(int index) {
            if (index >= size) {
                return null;
            }

            var find = head;
            while (index > 0) {
                find = find.next;

                index--;
            }

            return find;
        }

        public ListNode getHead() {
            return head;
        }
    }

    public static class DoubleNode {
        public final int value;
        public DoubleNode next = null;
        public DoubleNode last = null;

        DoubleNode(int val) {
            this.value = val;
        }
    }

    public static class DoubleList {
        private DoubleNode head = null;
        private DoubleNode tail = null;

        public void add(int val) {
            var newNode = new DoubleNode(val);

            if (head == null) {
                head = newNode;
            } else {
                tail.next = newNode;
                newNode.last = tail;
            }
            tail = newNode;
        }

        public DoubleNode pop() {
            if (head == null) {
                return null;
            }

            head.next.last = null;

            return head;
        }

        public DoubleNode getHead() {
            return head;
        }

        public DoubleNode getTail() {
            return tail;
        }
    }

    public static void main(String[] args) {
        SingleList list = new SingleList();
        list.add(3);
        list.add(4);
        list.add(1);
        list.add(5);
        list.add(10);

        print(list.getHead());
        var newHead = reserve(list.getHead());
        print(newHead);

        System.out.println("=================");

        DoubleList doubleList = new DoubleList();
        doubleList.add(3);
        doubleList.add(4);
        doubleList.add(1);
        doubleList.add(5);
        doubleList.add(10);

        print(doubleList.getHead());
        printReserve(doubleList.getTail());

        var newHead2 = DoubleReserve(doubleList.getHead());
        print(newHead2);
        printReserve(doubleList.getHead());

        System.out.println("=================");
        SingleList list3 = new SingleList();
        list3.add(3);
        list3.add(4);
        list3.add(1);
        list3.add(5);
        list3.add(10);
        list3.add(1, 20);
        list3.add(0, 20);
        print(list3.getHead());

        System.out.println("=================");

        SingleList list4 = new SingleList();
        list4.add(3);
        list4.add(4);
        list4.add(1);
        list4.add(5);
        list4.add(10);

        // node 5
        ListNode integerNode = list4.find(3);

        ListNode reserve = reserve(list4.getHead(), integerNode);
        print(reserve);

        System.out.println("=================");

        SingleList list5 = new SingleList();
        list5.add(3);
        list5.add(4);
        list5.add(1);
        list5.add(5);
        list5.add(10);
        list5.add(11);
        list5.add(16);
        list5.add(17);

        ListNode newHead5 = reserveGroupByK(list5.getHead(), 3);
        print(newHead5);

        System.out.println("=================");

        SingleList list6 = new SingleList();
        list6.add(1);
        list6.add(3);
        list6.add(4);
        list6.add(5);
        list6.add(7);

        SingleList list7 = new SingleList();
        list7.add(1);
        list7.add(3);
        list7.add(4);
        list7.add(5);
        list7.add(7);

        ListNode newHead6 = mergeTwoLists(list6.getHead(), list7.getHead());
        print(newHead6);

        SingleList list8 = new SingleList();

        list8.add(9);
        list8.add(9);

        SingleList list9 = new SingleList();
        list9.add(1);

        ListNode newHead7 = addTwoNumbers(list8.getHead(), list9.getHead());
        print(newHead7);
    }

    public static void printReserve(DoubleNode tail) {
        while (tail != null) {
            System.out.print(tail.value);
            System.out.print(",");

            tail = tail.last;
        }

        System.out.println();
    }

    public static void print(DoubleNode head) {
        while (head != null) {
            System.out.print(head.value);
            System.out.print(",");

            head = head.next;
        }

        System.out.println();
    }

    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            System.out.print(",");

            head = head.next;
        }

        System.out.println();
    }

    public static ListNode reserve(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode preNode = null;

        while (head != null) {
            var next = head.next;
            head.next = preNode;
            preNode = head;

            head = next;
        }

        return preNode;
    }

    public static DoubleNode DoubleReserve(DoubleNode head) {
        if (head == null) {
            return null;
        }

        DoubleNode preNode = null;

        while (head != null) {
            var next = head.next;
            head.next = preNode;
            head.last = next;
            preNode = head;
            head = next;
        }

        return preNode;
    }

    /**
     * k 个节点一组，进行逆序; 不足k个的部分保持原样
     */
    public static ListNode reserveGroupByK(ListNode head, int k) {

        ListNode end = getKEnd(head, k);
        if (end == null) {
            return head;
        }

        var start = head;

        reserve(start, end);
        head = end;
        var preEnd = start;
        while (preEnd.next != null) {
            start = preEnd.next;

            end = getKEnd(start, k);

            if (end == null) {
                return head;
            }

            reserve(start, end);
            preEnd.next = end;
            preEnd = start;
        }

        return head;
    }

    public static ListNode reserve(ListNode begin, ListNode end) {
        if (begin == null) {
            return null;
        }

        if (end == null) {
            return begin;
        }

        var nextStart = end.next;
        var cur = begin;
        ListNode pre = null;
        while (cur != end) {
            var next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        cur.next = pre;
        begin.next = nextStart;

        return end;
    }

    public static ListNode getKEnd(ListNode start, int k) {
        while (start != null && --k > 0) {
            start = start.next;
        }

        return start;
    }


    public static int size(ListNode start) {
        var size = 0;
        while (start != null) {
            start = start.next;
            size++;
        }

        return size;
    }

    /**
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/submissions/
     * 合并两个有序链表
     * 1. 找到一个主链表，主链表第一个值较小
     * 2. 将另一个链表的值逐步比较添加进主链表
     * 3. 处理副链表剩余
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        ListNode minHead;
        ListNode thanHead;

        if (list1.val < list2.val) {
            minHead = list1;
            thanHead = list2;
        } else {
            minHead = list2;
            thanHead = list1;
        }
        var preNode = minHead;
        var start = minHead.next;

        while (start != null && thanHead != null) {
            if (start.val > thanHead.val) {
                var otherNext = thanHead.next;
                thanHead.next = start;
                preNode.next = thanHead;
                preNode = thanHead;
                thanHead = otherNext;
            } else {
                preNode = start;
                start = start.next;
            }
        }

        if (start == null) {
            preNode.next = thanHead;
        }

        return minHead;
    }

    public static ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode resLast = null;
        ListNode resHead = null;
        // 1 -> 2 -> 3->4->5->6
        // 1->2->3
        // 4->5->6
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        while (list1 != null && list2 != null) {
            // 2
            ListNode currentNode = new ListNode();

            if (list1.val < list2.val) {
                currentNode.val = list1.val;
                list1 = list1.next;
            } else {
                currentNode.val = list2.val;
                list2 = list2.next;
            }

            if (resLast == null) {
                resLast = currentNode;
                resHead = currentNode;
            } else {
                resLast.next = currentNode;
                resLast = resLast.next;
            }
        }

        // last 6
        if (list1 != null) {
            resLast.next = list1;
        }

        if (list2 != null) {
            resLast.next = list2;
        }

        return resHead;
    }

    /**
     * https://leetcode-cn.com/problems/add-two-numbers-ii/submissions/
     * 两个链表相加 1-2-3 5-6-7-8
     * 1. 加法由最后开始加，然后逐步进1;
     * 2. 所以先翻转两个链表, 保证从个位数开始
     * 3. 选择一个较长的链表作为主链记录新值, 记录每一步的进位
     * 4. 要记录上一个的值，用于处理最后一个进位
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode reserve1 = reserve(l1);
        ListNode reserve2 = reserve(l2);
        int size1 = size(reserve1);
        int size2 = size(reserve2);

        var longL = size1 > size2 ? reserve1 : reserve2;
        var shortL = size1 > size2 ? reserve2 : reserve1;

        var head = longL;

        var add = 0;
        ListNode last = null;
        while (shortL != null) {
            int sum = longL.val + shortL.val + add;
            longL.val = sum % 10;
            add = sum / 10;
            shortL = shortL.next;
            last = longL;
            longL = longL.next;
        }

        while (longL != null) {
            int sum = longL.val + add;
            longL.val = sum % 10;
            add = sum / 10;
            last = longL;
            longL = longL.next;
        }

        if (add > 0) {
            last.next = new ListNode(1);
        }

        return reserve(head);
    }

    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     * 示例 2：
     * <p>
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     * 示例 3：
     * <p>
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     * <p>
     * 提示：
     * <p>
     * 每个链表中的节点数在范围 [1, 100] 内
     * 0 <= Node.val <= 9
     * 题目数据保证列表表示的数字不含前导零
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int size1 = size(l1);
        int size2 = size(l2);

        ListNode longNode = null;
        ListNode shortNode = null;

        if (size1 > size2) {
            longNode = l1;
            shortNode = l2;
        } else {
            longNode = l2;
            shortNode = l1;
        }

        ListNode head = longNode;

        int add = 0;

        ListNode last = null;
        while (shortNode != null) {
            longNode.val = shortNode.val + longNode.val + add;
            if (longNode.val >= 10) {
                longNode.val = longNode.val % 10;
                add = 1;
            } else {
                add = 0;
            }

            last = longNode;
            shortNode = shortNode.next;
            longNode = longNode.next;
        }

        while (longNode != null) {
            longNode.val = longNode.val + add;
            if (longNode.val >= 10) {
                longNode.val = longNode.val % 10;
                add = 1;
            } else {
                add = 0;
            }

            last = longNode;
            longNode = longNode.next;
        }
        if (add > 0) {
            last.next = new ListNode(1);
        }

        return head;
    }

    /**
     * https://leetcode-cn.com/problems/merge-k-sorted-lists/
     * 给你一个链表数组，每个链表都已经按升序排列。
     *
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     * 示例 1：
     *
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 将它们合并到一个有序链表中得到。
     * 1->1->2->3->4->4->5->6
     *
     */
    public ListNode mergeKList(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });

        for (ListNode listNode : lists) {
            if (listNode != null) {
                heap.add(listNode);
            }
        }

        ListNode head = heap.poll();
        if (head == null) {
            return null;
        }
        if (head.next != null) {
            heap.add(head.next);
        }
        ListNode last = head;
        while (!heap.isEmpty()) {
            ListNode poll = heap.poll();
            last.next = poll;
            last = poll;
            if (poll.next != null) {
                heap.add(poll.next);
            }
        }

        return head;
    }
}
