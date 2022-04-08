package com.fwhat.algorithm.stack;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Stacks {

    public static void main(String[] args) {
        System.out.println(maxDeep("([]{()})"));
        System.out.println(maxDeep("(]"));
        System.out.println(maxDeep("({()})"));
        System.out.println(maxDeep("([{()}])"));
        System.out.println(maxDeep("(((([{()}]))))"));
        System.out.println(maxDeep("((((()))))[]{}"));
        System.out.println(calc("7#6$5$12#11#11$11$22#11"));
    }

    /**
     * 现有一字符串仅由 '('， ')'， '{'， '}'， '['， ']'六种括号组成。 若字符串满足以下条件之一，则为无效字符串：
     * ①任一类型的左右括号数量不相等；
     * ②存在未按正确顺序（先左后右）闭合的括号。
     * 输出括号的最大嵌套深度，若字符串无效则输出 0。 0≤字符串长度≤100000
     * 输入描述:
     * 一个只包括 '('， ')'， '{'， '}'， '['， ']'的字符串
     * 输出描述:
     * 一个整数，最大的括号深度
     * 示例1
     * 输入
     * []
     * 输出
     * 1
     * 说明
     * 有效字符串，最大嵌套深度为1
     *  
     * 示例 2
     * 输入
     * ([]{()})
     * 输出
     * 3
     * 说明
     * 有效字符串，最大嵌套深度为3
     *  
     * 示例3
     * 输入
     * (]
     * 输出
     * 0
     * 说明
     * 无效字符串，有两种类型的左右括号数量不相等
     *  
     * 示例4
     * 输入
     * ([)]
     * 输出
     * 0
     * 说明
     * 无效字符串，存在未按正确顺序闭合的括号
     *  
     * 示例5
     * 输入
     * )(
     * 输出
     * 0
     * 说明
     * 无效字符串，存在未按正确顺序闭合的括号
     */
    public static int maxDeep(String str) {
        char[] chars = str.toCharArray();
        char[] stack = new char[chars.length];
        int stackIndex = 0;
        int maxDeep = 0;
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        for (char aChar : chars) {
            if (stackIndex > 0) {
                if (map.get(stack[stackIndex - 1]) == aChar) {
                    stackIndex--;
                    continue;
                }
            }
            stack[stackIndex++] = aChar;
            maxDeep = Math.max(maxDeep, stackIndex);
        }
        if (stackIndex != 0) {
            return 0;
        }

        return maxDeep;
    }

    /**
     * 火星文计算
     *  
     * 已知火星人使用的运算符为#、$，其与地球人的等价公式如下：
     *  
     * x#y = 2x+3y+4
     * x$y = 3*x+y+2
     * 其中x、y是无符号整数
     * 地球人公式按C语言规则计算
     * 火星人公式中，$的优先级高于#，相同的运算符，按从左到右的顺序计算 现有一段火星人的字符串报文，请你来翻译并计算结果。
     * 输入描述:
     *  
     * 火星人字符串表达式（结尾不带回车换行）
     * 输入的字符串说明
     * 字符串为仅由无符号整数和操作符（#、$）
     *  
     * 组成的计算表达式。例如：
     * 123#4$5#67$78
     * 示例
     * 7#6$5#12
     * 输出
     * 226
     */
    public static void calc() {
        Scanner scanner = new Scanner(System.in);
        String calcStr = scanner.nextLine();
        System.out.println(calc(calcStr));
    }

    public static int calc(String calcStr) {
        int len = calcStr.length();

        StringBuilder temp = new StringBuilder();
        Stack<Character> operator = new Stack<>();
        Stack<Integer> nums = new Stack<>();

        for (int i = 0; i < len; i++) {
            char ch = calcStr.charAt(i);
            if (Character.isDigit(ch)) {
                temp.append(ch);
            } else {
                int num = Integer.parseInt(temp.toString());
                nums.add(num);
                temp.delete(0, temp.length());
                if (nums.size() == 1) {
                    operator.add(ch);
                    continue;
                }
                while (operator.size() >= 1) {
                    if (gtChar(ch, operator.lastElement()) <= 0) {
                        nums.push(calc(operator.pop(), nums.pop(), nums.pop()));
                    } else {
                        break;
                    }
                }
                operator.push(ch);
            }
        }

        nums.push(Integer.parseInt(temp.toString()));

        while (!operator.isEmpty()) {
            nums.push(calc(operator.pop(), nums.pop(), nums.pop()));
        }

        return nums.pop();
    }

    private static int calc(char ch, int y, int x) {
        System.out.println(x + "" + ch + "" + y);
        switch (ch) {
            case '$':
                return 3 * x + y + 2;
            case '#':
                return 2 * x + 3 * y + 4;
        }
        throw new RuntimeException("unknown operator");
    }

    private static int gtChar(char a, char b) {
        if (a == '$' && b == '#') {
            return 1;
        }

        if (a == b) {
            return 0;
        }

        return -1;
    }
}
