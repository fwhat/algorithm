package com.fwhat.algorithm.string;

import com.fwhat.algorithm.Time100;

import java.util.*;

public class Strings {
    public static void main(String[] args) {
        System.out.println(subStringLastIndex("ace", "abcde"));
        System.out.println(subStringLastIndex("fgh", "abcde"));
        System.out.println(subStringLastIndex("ace", "aceaceaceaceacedaaed"));

//        subString8();
        System.out.println(encrypt("TRAILBLAZERS", "Attack AT DAWN"));
        System.out.println(maskString(3, "password_a12345678_timeout_100"));

        System.out.println(maxSubString("abcabcbb"));
        System.out.println(maxSubString2("AAAAHHHBBCDHHHH", 3));
        System.out.println(maxSubString2("AABAAA", 2));
        System.out.println(urlJoin("/acm,/bb"));
        System.out.println(urlJoin("/abc/,/bcd"));
        System.out.println(urlJoin(","));
        minString("abcdef");
        minString("bcdefa");
        sameSubString("12312");
        sameSubString("12314");
        sameSubString("123123");

        maxLenSubString(0, "asdbuiodevauufgh");
        maxLenSubString(1, "aabeebuu");
        System.out.println(allCharExists("bc", "abc"));
        System.out.println(romanToInt("MCMXCIV"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring(" "));
        System.out.println(lengthOfLongestSubstring("tmmzuxt"));
        System.out.println(strStr("aaaaa", "baa"));
        System.out.println(strStr("hello", "ll"));

        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
        System.out.println(longestPalindrome("a"));
        System.out.println(longestPalindrome("ac"));
        System.out.println(longestPalindrome("abb"));
        System.out.println(convertZ("PAYPALISHIRING", 4));
        System.out.println(convertZ("PAYPALISHIRING", 3));
        System.out.println(convertZ("AB", 1));
        System.out.println(myAtoi("   2147483649asada"));
    }

    /**
     * 字符串序列判定
     * <p>
     * 输入两个字符串S和L，都只包含英文小写字母。S长度<=100，L长度<=500,000。判定S是否是L的有效子串。
     * <p>
     * 判定规则：
     * <p>
     * S中的每个字符在L中都能找到（可以不连续），且S在Ｌ中字符的前后顺序与S中顺序要保持一致。
     * <p>
     * （例如，S="ace"是L="abcde"的一个子序列且有效字符是a、c、e，而"aec"不是有效子序列，且有效字符只有a、e）
     * <p>
     * 输入描述:
     * <p>
     * 输入两个字符串S和L，都只包含英文小写字母。
     * S长度<=100，L长度<=500,000。
     * 先输入S，再输入L，每个字符串占一行。
     * 输出描述:
     * <p>
     * S串最后一个有效字符在L中的位置。（首位从0开始计算，无有效字符返回-1）
     * <p>
     * 示例1
     * 输入
     * ace
     * abcde
     * <p>
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
                } else {
                    curSub++;
                }
            }
        }

        return lastIndex;
    }

    /**
     * 字符串分割
     * <p>
     * 描述
     * •连续输入字符串，请按长度为8拆分每个输入字符串并进行输出；
     * <p>
     * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
     * （注：本题有多组输入）
     * 输入描述：
     * 连续输入字符串(输入多次,每个字符串长度小于等于100)
     * <p>
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
     * <p>
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
                dataChars[i] = Character.isUpperCase(dataChars[i]) ? encryptMap.get(dataChars[i]) : Character.toLowerCase(encryptMap.get(charUpper));
            }
        }

        return new String(dataChars);
    }

    /**
     * 12、标题:敏感字段加密
     * 【敏感字段加密敏】给定一个由多个命令字组成的命令字符串:
     * 1、字符串长度小于等于127字节，只包含大小写字母、数字、下划线和偶数个双引号;
     * 2、命令字之间以一个或多个下划线_进行分割;
     * 3、可以通过两个双引号来"标识包含下划线_的命令字或空命令字(仅包含两个引双引号的命令字)双引号不会在命令字内部出现;
     * 仅对指定索引的敏感字段进行加密，替换为*(6个*)，并删除命令字前后多余的下划线_。如果无法找到指定索引的命令字，输出字符串ERROR。
     * 输入描述:
     * 输入为两行，第一行为命令字索引K(从0开始)，第二行为命令字符串S。
     * 输出描述:
     * 输出处理后的命令字符串，如果无法找到指定索引的命令字，输出字符串ERROR
     * 示例:
     * 输入
     * 1
     * password_a12345678_timeout_100
     * 输出
     * password_******_timeout_100
     */
    public static void maskString() {
        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());
        String str = scanner.nextLine();

        System.out.println(maskString(k, str));
    }

    public static String maskString(int k, String str) {
        StringBuilder masked = new StringBuilder();

        int len = str.length();
        int underline = 0;
        boolean maskMode = (k == underline);
        if (maskMode) {
            masked.append("******");
        }
        for (int i = 0; i < len; i++) {
            if (maskMode) {
                if (str.charAt(i) == '_') {
                    masked.append(str.substring(i));
                    break;
                }
            } else {
                if (str.charAt(i) == '_') {
                    underline++;
                    if (k == underline) {
                        maskMode = true;
                        masked.append('_').append("******");
                        continue;
                    }
                }
                masked.append(str.charAt(i));
            }
        }

        return masked.toString();
    }

    /**
     * 题目描述：
     * 给定一个字符串String，求取该字符串满足条件的最长子串的长度。
     * 条件：该子串中各字符最多出现两次。
     * <p>
     * 测试用例：
     * 输入：abcabcbb
     * 输出：6
     * <p>
     * 说明：子串abcabc每个字符出现的次数都小于等于2，满足条件且为最长，输出长度6。
     */
    public static int maxSubString(String str) {
        int maxSub = 0;

        for (int i = 0; i < str.length(); i++) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (int j = i; j < str.length(); j++) {
                char ch = str.charAt(j);
                if (map.containsKey(ch)) {
                    if (map.get(ch) > 2) {
                        if (j - i - 1 > maxSub) {
                            maxSub = j - i - 1;
                        }
                        break;
                    } else {
                        map.put(ch, map.get(ch) + 1);
                    }
                } else {
                    map.put(ch, 1);
                }
            }
        }

        return maxSub;
    }

    /**
     * 连续字母长度
     * 给定一个字符串，只包含大写字母，求在包含同一字母的子串中，长度第 k 长的子串的长度，相同字母只取最长的那个子串。
     * 输入描述:
     * <p>
     * 第一行有一个子串(1<长度<=100)，只包含大写字母。
     * 第二行为 k的值
     * 输出描述:
     * <p>
     * 输出连续出现次数第k多的字母的次数。
     * 示例
     * <p>
     * 输入：
     * AABAAA
     * 2
     * 输出：
     * 1
     * 说明：
     * <p>
     * 同一字母连续出现最多的A 3次
     * 第二多2次 但A出现连续3次
     * <p>
     * 输入：
     * AAAAHHHBBCDHHHH
     * 3
     * 输出：
     * 2
     * 说明：
     * <p>
     * 同一字母连续出现的最多的是A和H，四次
     * 第二多的是B，2次
     */
    public static int maxSubString2(String str, int k) {
        int len = str.length();
        HashMap<Character, Integer> map = new HashMap<>();

        char subChar = str.charAt(0);
        int charLen = 1;

        for (int i = 1; i < len; i++) {
            if (str.charAt(i) == subChar) {
                charLen++;
            } else {
                if (map.containsKey(subChar)) {
                    map.put(subChar, Math.max(map.get(subChar), charLen));
                } else {
                    map.put(subChar, charLen);
                }
                subChar = str.charAt(i);
                charLen = 1;
            }
        }

        Integer[] integers = map.values().toArray(new Integer[]{});
        if (k > integers.length) {
            return -1;
        }
        Arrays.sort(integers);
        return integers[integers.length - k];
    }

    /**
     * 题目描述
     * <p>
     * 给定一个url前缀和url后缀,通过,分割 需要将其连接为一个完整的url
     * 如果前缀结尾和后缀开头都没有/，需要自动补上/连接符
     * 如果前缀结尾和后缀开头都为/，需要自动去重
     * 约束：不用考虑前后缀URL不合法情况
     * 输入描述
     * <p>
     * url前缀(一个长度小于100的字符串) url后缀(一个长度小于100的字符串)
     * <p>
     * 输出描述
     * <p>
     * 拼接后的url
     * <p>
     * 一、 输入
     * /acm,/bb
     * 输出
     * <p>
     * /acm/bb
     * 二、输入
     * <p>
     * /abc/,/bcd
     * 输出
     * <p>
     * /abc/bcd
     * 拼接 URL
     */
    public static String urlJoin(String urlStr) {
        StringBuilder url = new StringBuilder();
        url.append('/');
        String[] urls = urlStr.split(",");
        for (String s : urls) {
            int begin = 0;
            int end = s.length();
            if (s.startsWith("/")) {
                begin = 1;
            }
            if (s.endsWith("/")) {
                end = s.length() - 1;
            }
            url.append(s, begin, end).append('/');
        }
        if (url.length() > 1) {
            return url.substring(0, url.length() - 1);
        }

        return url.toString();
    }

    /**
     * 题目描述：
     * 给定一个字符串s，最多只能进行一次变换，返回变换后能得到的最小字符串（按照字典序进行比较）。
     * 变换规则：交换字符串中任意两个不同位置的字符。
     * 输入描述：
     * 一串小写字母组成的字符串s。
     * 输出描述：
     * 按照要求进行变换得到的最小字符串。
     * 备注：
     * s是都是小写字符组成
     * 1<=s.length<=1000
     * 示例
     * 输入：abcdef
     * <p>
     * 输出：abcdef
     * <p>
     * 说明：abcdef已经是最小字符串，不需要交换
     * <p>
     * <p>
     * 输入：bcdefa
     * <p>
     * 输出：acdefb
     * <p>
     * 说明：a和b进行位置交换，可以得到最小字符串
     */
    public static void minString(String str) {
        int len = str.length();
        char[] chars = str.toCharArray();
        for (int i = 0; i < len; i++) {
            int minCharIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (str.charAt(j) < str.charAt(minCharIndex)) {
                    minCharIndex = j;
                }
            }
            if (minCharIndex != i) {
                char temp = chars[i];
                chars[i] = chars[minCharIndex];
                chars[minCharIndex] = temp;
                break;
            }
        }

        System.out.println(String.valueOf(chars));
        ;
    }

    /**
     * 输入一个字符串，判断是否含有相同的子串（字串长度大于1），是输出1，否，输出0。
     * 例如12312含有两个12,所以输出1；23456则没有相同子序列，输出0.
     * <p>
     * 输入：12312
     * 输出：1
     */
    public static void sameSubString(String str) {
        int len = str.length();
        boolean continueMode = true;

        for (int i = 0; i < len - 1 && continueMode; i++) {
            String sub = String.valueOf(new char[]{str.charAt(i), str.charAt(i + 1)});
            for (int j = i + 2; j < len - 1; j++) {
                if (sub.equals(String.valueOf(new char[]{str.charAt(j), str.charAt(j + 1)}))) {
                    continueMode = false;
                    break;
                }
            }
        }
        if (continueMode) {
            System.out.println("0");
        } else {
            System.out.println("1");
        }
    }

    /**
     * 最长的指定瑕疵度的元音字串
     * 开头和结尾都是元音字母（aeiouAEIOU）的字符串为 元音字符串 ，其中混杂的非元音字母数量为其 瑕疵度 。比如:
     * · “a” 、 “aa”是元音字符串，其瑕疵度都为0
     * · “aiur”不是元音字符串（结尾不是元音字符）
     * · “abira”是元音字符串，其瑕疵度为2
     * <p>
     * 现给定一个字符串，请找出指定瑕疵度的最长元音字符子串，并输出其长度，如果找不到满足条件的元音字符子串，输出0。
     * 子串：字符串中任意个连续的字符组成的子序列称为该字符串的子串。
     * <p>
     * 输入输出
     * 输入描述:
     * 首行输入是一个整数，表示预期的瑕疵度flaw，取值范围[0, 65535]。
     * 接下来一行是一个仅由字符a-z和A-Z组成的字符串，字符串长度(0, 65535]。
     * 输出描述:
     * 输出为一个整数，代表满足条件的元音字符子串的长度。
     * <p>
     * 示例1
     * 输入
     * 0
     * asdbuiodevauufgh
     * 输出
     * 3
     * 说明
     * 满足条件的最长元音字符子串有两个，分别为uio和auu，长度为3。
     * <p>
     * 示例2
     * 输入
     * 2
     * aeueo
     * 输出
     * 0
     * 说明
     * 没有满足条件的元音字符子串，输出0
     * 示例3
     * 输入
     * 1
     * aabeebuu
     * 输出
     * 5
     * 说明
     * 满足条件的最长元音字符子串有两个，分别为aabee和eebuu，长度为5
     */
    public static void maxLenSubString(int target, String str) {
        HashMap<Character, Boolean> map = new HashMap<>();
        char[] vs = new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        for (char v : vs) {
            map.put(v, true);
        }
        int len = str.length();

        int maxLen = 0;

        for (int i = 0; i < len; i++) {
            int strLen = 0;
            int notVCount = 0;
            for (int j = i; j < len; j++) {
                char ch = str.charAt(j);
                if (!map.containsKey(ch)) {
                    notVCount++;
                    if (notVCount > target) {
                        if (strLen >= 2 && strLen > maxLen) {
                            maxLen = strLen;
                        }
                        break;
                    }
                }

                strLen++;
            }
            if (notVCount == target) {
                if (strLen >= 2 && strLen > maxLen) {
                    maxLen = strLen;
                }
            }
        }

        System.out.println(maxLen);
    }

    /**
     * 题目标题：
     * <p>
     * 判断短字符串中的所有字符是否在长字符串中全部出现
     * <p>
     * 详细描述：
     * <p>
     * 接口说明
     * <p>
     * 原型：
     * <p>
     * 输入描述:
     * 输入两个字符串。第一个为短字符，第二个为长字符。
     * <p>
     * 输出描述:
     * 返回值：
     * <p>
     * 示例1
     * <p>
     * 输入
     * bc
     * abc
     * 输出
     * true
     */
    public static boolean allCharExists(String sub, String str) {
        int len = str.length();
        HashMap<Character, Boolean> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            map.put(str.charAt(i), true);
        }

        int subLen = sub.length();
        for (int i = 0; i < subLen; i++) {
            if (!map.containsKey(sub.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 罗马数字包含以下七种字符:I，V，X，L，C，D和M。
     * <p>
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做II，即为两个并列的 1 。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，
     * 所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
     * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
     * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
     * 给定一个罗马数字，将其转换成整数。
     * <p>
     * <p>
     * <p>
     * 示例1:
     * <p>
     * 输入:s = "III"
     * 输出: 3
     * 示例2:
     * <p>
     * 输入:s = "IV"
     * 输出: 4
     * 示例3:
     * <p>
     * 输入:s = "IX"
     * 输出: 9
     * 示例4:
     * <p>
     * 输入:s = "LVIII"
     * 输出: 58
     * 解释: L = 50, V= 5, III = 3.
     * 示例5:
     * <p>
     * 输入:s = "MCMXCIV"
     * 输出: 1994
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 15
     * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
     * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
     * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
     * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
     * 关于罗马数字的详尽书写规则
     */
    public static int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int len = s.length();
        int sum = 0;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            int v = map.get(ch);
            if (i != len - 1) {
                char nextCh = s.charAt(i + 1);
                if (
                        (ch == 'I' && (nextCh == 'V' || nextCh == 'X'))
                                || (ch == 'X' && (nextCh == 'L' || nextCh == 'C'))
                                || ch == 'C' && (nextCh == 'D' || nextCh == 'M')
                ) {
                    v = map.get(nextCh) - v;
                    i++;
                }
            }
            sum += v;
        }

        return sum;
    }

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * <p>
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * <p>
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 0 <= s.length <= 5 * 104
     * s 由英文字母、数字、符号和空格组成
     */
    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int max = 0;

        // todo
        HashMap<Character, Integer> map = new HashMap<>();
        int size = 0;
        for (int i = 0; i < len; i++) {
            if (map.containsKey(s.charAt(i))) {
                max = Math.max(size, max);
                Integer integer = map.get(s.charAt(i));
                size = size - integer - 1;
            }
            map.put(s.charAt(i), i);
            size++;
        }

        return Math.max(size, max);
    }

    /**
     * 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * <p>
     * 如果不存在公共前缀，返回空字符串 ""。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     * 示例 2：
     * <p>
     * 输入：strs = ["dog","racecar","car"]
     * 输出：""
     * 解释：输入不存在公共前缀。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= strs.length <= 200
     * 0 <= strs[i].length <= 200
     * strs[i] 仅由小写英文字母组成
     */
    @Time100
    public String longestCommonPrefix(String[] strs) {
        int minLen = strs[0].length();

        for (String str : strs) {
            minLen = Math.min(str.length(), minLen);
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < minLen; i++) {
            char ch = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (ch != strs[j].charAt(i)) {
                    return builder.toString();
                }
            }

            builder.append(ch);
        }
        return builder.toString();
    }

    /**
     * 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "()"
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：s = "()[]{}"
     * 输出：true
     * 示例 3：
     * <p>
     * 输入：s = "(]"
     * 输出：false
     * 示例 4：
     * <p>
     * 输入：s = "([)]"
     * 输出：false
     * 示例 5：
     * <p>
     * 输入：s = "{[]}"
     * 输出：true
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 104
     * s 仅由括号 '()[]{}' 组成
     */
    @Time100
    public static boolean isValid(String s) {
        int len = s.length();
        if (len % 2 != 0) {
            return false;
        }

        char[] chars = new char[len];
        int charIndex = 0;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch == ')' || ch == ']' || ch == '}') {
                if (charIndex > 0) {
                    if (
                            (ch == ')' && chars[charIndex - 1] == '(') ||
                                    (ch == ']' && chars[charIndex - 1] == '[') ||
                                    (ch == '}' && chars[charIndex - 1] == '{')
                    ) {
                        charIndex--;
                    } else {
                        chars[charIndex++] = ch;
                    }
                } else {
                    return false;
                }
            } else {
                chars[charIndex++] = ch;
            }
        }
        return charIndex == 0;
    }

    /**
     * 实现 strStr()
     * <p>
     * 实现 strStr() 函数。
     * <p>
     * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
     * <p>
     * 说明：
     * <p>
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * <p>
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
     * <p>
     * 示例 1：
     * <p>
     * 输入：haystack = "hello", needle = "ll"
     * 输出：2
     * 示例 2：
     * <p>
     * 输入：haystack = "aaaaa", needle = "bba"
     * 输出：-1
     * 示例 3：
     * <p>
     * 输入：haystack = "", needle = ""
     * 输出：0
     */
    @Time100
    public static int strStr(String haystack, String needle) {
        int needleLen = needle.length();
        int haystackLen = haystack.length();

        if (needle.length() == 0) {
            return 0;
        }

        if (haystackLen < needleLen) {
            return -1;
        }

        for (int i = 0; i < haystackLen; i++) {
            char ch = haystack.charAt(i);
            if (ch == needle.charAt(0)) {
                if (haystackLen - 1 - i < needleLen - 1) {
                    return -1;
                }
                boolean find = true;

                for (int j = 1; j < needleLen; j++) {
                    if (haystack.charAt(i + j) != needle.charAt(j)) {
                        find = false;
                        break;
                    }
                }
                if (find) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     *
     * 示例 1：
     *
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * 示例 2：
     *
     * 输入：s = "cbbd"
     * 输出："bb"
     *
     * 提示：
     *
     * 1 <= s.length <= 1000
     * s 仅由数字和英文字母组成
     *
     */
    public static String longestPalindrome(String s) {
        int len = s.length();
        String longest = "";

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if ((j - i  +1) > longest.length()) {
                    if (isPalindrome(s, i, j)) {
                        longest = s.substring(i, j + 1);
                    }
                }
            }
        }

        return longest;
    }

    private static boolean isPalindrome(String s, int start, int end) {
        int len = end - start + 1;

        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i + start) != s.charAt(end - i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Z 字形变换
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行Z 字形排列。
     *
     * 比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
     *
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
     *
     * 请你实现这个将字符串进行指定行数变换的函数：
     *
     * string convert(string s, int numRows);
     *
     * 示例 1：
     * 输入：s = "PAYPALISHIRING", numRows = 3
     * 输出："PAHNAPLSIIGYIR"
     * 示例 2：
     * 输入：s = "PAYPALISHIRING", numRows = 4
     * 输出："PINALSIGYAHRPI"
     * 解释：
     * P     I    N
     * A   L S  I G
     * Y A   H R
     * P     I
     * 示例 3：
     *
     * 输入：s = "A", numRows = 1
     * 输出："A"
     */
    public static String convertZ(String s, int numRows) {
        /*
         * 注意numRows为1时直接返回
         */
        if (numRows == 1) {
            return s;
        }

        StringBuilder[] group = new StringBuilder[numRows];
        int len = s.length();
        int groupIndex = 0;
        int up = 1;
        int down = -1;
        int mod = down;
        for (int i = 0; i < len; i++) {
            if (group[groupIndex] == null) {
                group[groupIndex] = new StringBuilder();
            }
            group[groupIndex].append(s.charAt(i));
            if (mod == down) {
                if (groupIndex == numRows - 1) {
                    groupIndex--;
                    mod = up;
                } else {
                    groupIndex++;
                }
            } else {
                if (groupIndex == 0) {
                    groupIndex++;
                    mod = down;
                } else {
                    groupIndex --;
                }
            }
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            // 注意可能存在 len < numRows; 会出现空
            if (group[i] != null) {
                str.append(group[i]);
            }
        }

        return str.toString();
    }

    /**
     * 字符串转换整数 (atoi)
     * 请你来实现一个myAtoi(string s)函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     *
     * 函数myAtoi(string s) 的算法如下：
     *
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231, 231− 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231− 1 的整数应该被固定为 231− 1 。
     * 返回整数作为最终结果。
     * 注意：
     *
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
     */
    public static int myAtoi(String s) {
        int len = s.length();
        int res = 0;
        boolean negative = false;
        boolean start = false;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (start) {
                if (Character.isDigit(ch)) {
                    int num = Character.digit(ch, 10);
                    if ((Integer.MAX_VALUE - num) / 10 < res) {
                        if (negative) {
                            return Integer.MIN_VALUE;
                        }
                        return Integer.MAX_VALUE;
                    }
                    res = res*10 + num;
                } else {
                    break;
                }
            } else {
                if (ch == ' ') {
                    continue;
                }
                start = true;

                if (ch == '-') {
                    negative = true;
                    continue;
                }
                if (ch == '+') {
                    continue;
                }

                if (Character.isDigit(ch)) {
                    res = Character.digit(ch, 10);
                } else {
                    break;
                }
            }
        }

        if (negative) {
            return -res;
        } else {
            return res;
        }
    }
}
