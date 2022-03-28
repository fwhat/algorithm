package com.fwhat.algorithm.list;

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
}
