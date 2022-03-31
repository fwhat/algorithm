package com.fwhat.algorithm.stack;

import java.util.HashMap;

public class Stack {

    public static void main(String[] args) {
        System.out.println(maxDeep("([]{()})"));
        System.out.println(maxDeep("(]"));
        System.out.println(maxDeep("({()})"));
        System.out.println(maxDeep("([{()}])"));
        System.out.println(maxDeep("(((([{()}]))))"));
        System.out.println(maxDeep("((((()))))[]{}"));
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
     * <p>
     * 示例 2
     * 输入
     * ([]{()})
     * 输出
     * 3
     * 说明
     * 有效字符串，最大嵌套深度为3
     * <p>
     * 示例3
     * 输入
     * (]
     * 输出
     * 0
     * 说明
     * 无效字符串，有两种类型的左右括号数量不相等
     * <p>
     * 示例4
     * 输入
     * ([)]
     * 输出
     * 0
     * 说明
     * 无效字符串，存在未按正确顺序闭合的括号
     * <p>
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
}
