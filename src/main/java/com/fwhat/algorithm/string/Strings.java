package com.fwhat.algorithm.string;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Strings {
    public static void main(String[] args) {
        System.out.println(subStringLastIndex("ace", "abcde"));
        System.out.println(subStringLastIndex("fgh", "abcde"));
        System.out.println(subStringLastIndex("ace", "aceaceaceaceacedaaed"));

//        subString8();
        System.out.println(encrypt("TRAILBLAZERS", "Attack AT DAWN"));
    }

    /**
     * 字符串序列判定
     *
     * 输入两个字符串S和L，都只包含英文小写字母。S长度<=100，L长度<=500,000。判定S是否是L的有效子串。
     *
     * 判定规则：
     *
     * S中的每个字符在L中都能找到（可以不连续），且S在Ｌ中字符的前后顺序与S中顺序要保持一致。
     *
     * （例如，S="ace"是L="abcde"的一个子序列且有效字符是a、c、e，而"aec"不是有效子序列，且有效字符只有a、e）
     *
     * 输入描述:
     *
     * 输入两个字符串S和L，都只包含英文小写字母。
     * S长度<=100，L长度<=500,000。
     * 先输入S，再输入L，每个字符串占一行。
     * 输出描述:
     *
     * S串最后一个有效字符在L中的位置。（首位从0开始计算，无有效字符返回-1）
     *
     * 示例1
     * 输入
     * ace
     * abcde
     *
     * 输出 4
     */
    public static int subStringLastIndex(String sub, String full) {
        char[] subArr = sub.toCharArray();
        char[] fullArr = full.toCharArray();


        int subSize = subArr.length;
        int curSub = 0;
        int find;
        int lastIndex = -1;
        for (find = 0; find < fullArr.length; find++) {
            if (fullArr[find] == subArr[curSub]) {
                if (curSub == subSize - 1) {
                    lastIndex = find;
                } else{
                    curSub++;
                }
            }
        }

        return lastIndex;
    }

    /**
     * 字符串分割
     *
     * 描述
     * •连续输入字符串，请按长度为8拆分每个输入字符串并进行输出；
     *
     * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
     * （注：本题有多组输入）
     * 输入描述：
     * 连续输入字符串(输入多次,每个字符串长度小于等于100)
     *
     * 输出描述：
     * 依次输出所有分割后的长度为8的新字符串
     */
    public static void subString8() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            char[] s = sc.nextLine().toCharArray();
            int i;
            for (i = 0; i < s.length; i++) {
                System.out.print(s[i]);
                if ((i + 1) % 8 == 0) {
                    System.out.println();
                }
            }
            int append = 8 - (i % 8);
            if (append != 8) {
                while (append > 0) {
                    System.out.print("0");
                    append--;
                }
                System.out.println();
            }
        }

        sc.close();
    }

    /**
     * 有一种技巧可以对数据进行加密，它使用一个单词作为它的密匙。下面是它的工作原理：
     * 首先，选择一个单词作为密匙，如TRAILBLAZERS。如果单词中包含有重复的字母，只保留第1个，其余几个丢弃。现在，修改过的那个单词属于字母表的下面，
     * 如下：
     * A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
     *
     * T R A I L B Z E S C D F G H J K M N O P Q U V W X Y
     */
    public static String encrypt(String key, String data) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] keyChars = key.toUpperCase(Locale.ROOT).toCharArray();
        HashMap<Character, Character> decryptMap = new HashMap<>();
        HashMap<Character, Character> encryptMap = new HashMap<>();
        int encryptIndex = 0;
        for (char keyChar : keyChars) {
            if (!decryptMap.containsKey(keyChar)) {
                encryptMap.put(chars[encryptIndex], keyChar);
                decryptMap.put(keyChar, chars[encryptIndex++]);
            }
        }


        for (char aChar : chars) {
            if (!decryptMap.containsKey(aChar)) {
                encryptMap.put(chars[encryptIndex], aChar);
                decryptMap.put(aChar, chars[encryptIndex++]);
            }
        }

        char[] dataChars = data.toCharArray();
        for (int i = 0; i < dataChars.length; i++) {
            char charUpper = Character.toUpperCase(dataChars[i]);
            if (encryptMap.containsKey(charUpper)) {
                dataChars[i] =  Character.isUpperCase(dataChars[i]) ?encryptMap.get(dataChars[i]) : Character.toLowerCase(encryptMap.get(charUpper));
            }
        }

        return new String(dataChars);
    }
}
