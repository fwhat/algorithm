package com.fwhat.algorithm.number;

import com.fwhat.algorithm.Assert;

import java.util.*;

public class Number {

    public static void main(String[] args) {
//        Assert.equal(minSum("bb12-34aa"), -31);
//        Assert.equal(minSum("bb1234aa"), 10);
//
//        System.out.println(maxLongSubString());
//
//        System.out.println(reserveNum(1516000));

//        printSeries();
//        maxNum();
//        printNumAllSeries();
//        minNum();
//        minSum();
//        System.out.println(calc4(155));
//        maxSub("2.3,3,5.6,7,6;11,3,8.6,25,1;0.3,9,5.3,66,7.8;1,3,2,7,5;340,670,80.6;<=,<=,<=");
//        rsaPrime(15);
//        mergeArray(new int[]{1, 2, 5}, new int[]{-1, 0, 3, 2});

        System.out.println(maxMinN(new int[]{3, 3, 2, 4, 2}, 2));
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(123));
    }

    /**
     * 求字符串中所有整数的最小和
     * 输入字符串s，输出s中包含所有整数的最小和
     * 说明
     * 字符串s，只包含 a-z A-Z +- ；
     * 合法的整数包括
     * 1） 正整数 一个或者多个0-9组成，如 0 2 3 002 102
     * 2）负整数 负号 - 开头，数字部分由一个或者多个0-9组成，如 -0 -012 -23 -00023
     * 输入描述:
     * 包含数字的字符串
     * 输出描述:
     * 所有整数的最小和
     * 示例1
     * bb1234aa
     * 10
     * bb12-34aa
     * -31
     */
    public static int minSum(String string) {
        char[] chars = string.toCharArray();
        StringBuilder negTemp = new StringBuilder();
        boolean isNeg = false;

        int sum = 0;
        for (char aChar : chars) {
            if (Character.isDigit(aChar)) {
                if (isNeg) {
                    negTemp.append(aChar);
                } else {
                    sum += Character.digit(aChar, 10);
                }
            } else {
                if (negTemp.length() > 0) {
                    sum -= Integer.parseInt(negTemp.toString());
                    negTemp.delete(0, negTemp.length());
                    isNeg = false;
                }

                if (aChar == '-') {
                    isNeg = true;
                }
            }
        }

        if (negTemp.length() > 0) {
            sum -= Integer.parseInt(negTemp.toString());
        }

        return sum;
    }

    /**
     * 有N个正整数组成的一个序列，给定一个整数sum
     * 求长度最长的的连续子序列使他们的和等于sum
     * 返回次子序列的长度，如果没有满足要求的序列 返回-1
     * 备注：
     * 输入序列仅由数字和英文逗号构成，数字之间采用英文逗号分割
     * 序列长度 1<=N<=200，输入序列不考虑异常情况
     * 由题目保证输入序列满足要求
     * 示例
     * 输入：
     * 1,2,3,4,2
     * 6
     * 输出：
     * 3
     * 解析：
     * 1,2,3和4,2两个序列均能满足要求，所以最长的连续序列为1,2,3 因此结果为3
     * 输入：
     * 1,2,3,4,2
     * 20
     * 输出：
     * -1
     * 解释：
     * 没有满足要求的子序列，返回-1
     */
    public static int maxLongSubString() {
        Scanner scanner = new Scanner(System.in);
        String[] nums = scanner.nextLine().split(",");
        int target = scanner.nextInt();
        scanner.close();

        int[] numArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numArr[i] = Integer.parseInt(nums[i]);
        }

        int maxLong = -1;

        for (int i = 0; i < numArr.length; i++) {
            int tempSum = 0;
            System.out.println(i);
            if (maxLong > numArr.length - i) {
                break;
            }
            for (int j = i; j < numArr.length; j++) {
                tempSum += numArr[j];
                if (tempSum > target) {
                    break;
                }

                if (tempSum == target && j > i) {
                    maxLong = Math.max(maxLong, j - i + 1);
                }
            }
        }

        return maxLong;
    }

    /**
     * 题目描述
     * 
     * 描述：
     * 
     * 输入一个整数，将这个整数以字符串的形式逆序输出
     * 
     * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
     * 
     * 输入描述:
     * 
     * 输入一个int整数
     * 
     * 输出描述:
     * 
     * 将这个整数以字符串的形式逆序输出
     * 示例1
     * 
     * 输入
     * 
     * 1516000
     * 输出
     * 
     * 0006151
     */
    public static String reserveNum(int num) {
        StringBuilder str = new StringBuilder();

        while (num > 0) {
            str.append(num % 10);
            num /= 10;
        }

        return str.toString();
    }

    /**
     * 已知连续正整数数列{K}=K1,K2,K3...Ki的各个数相加之和为S，i=N (0<S<100000, 0<N<100000), 求此数列K。
     * 
     * 输入描述:
     * 
     * 输入包含两个参数，1）连续正整数数列和S，2）数列里数的个数N。
     * 
     * 输出描述:
     * 
     * 如果有解输出数列K，如果无解输出-1
     * 
     * 示例1
     * 
     * 输入
     * 
     * 525 6
     * 输出
     * 
     * 85 86 87 88 89 90
     */
    public static void printSeries() {
        Scanner scanner = new Scanner(System.in);
        int sum = scanner.nextInt();
        int n = scanner.nextInt();
        // a1*n + n(0+n-1)/2 = sum

        int a1n = sum - n * (n - 1) / 2;
        if (a1n % n == 0) {
            int a1 = a1n / n;
            for (int i = 0; i < n; i++) {
                System.out.print((a1 + i) + " ");
            }
        } else {
            System.out.println("-1");
        }

        scanner.close();
    }

    /**
     * 小组中每位都有一张卡片，卡片上是6位内的正整数，将卡片连起来可以组成多种数字，计算组成的最大数字。
     * 
     * 输入描述:
     * “,”号分割的多个正整数字符串，不需要考虑非数字异常情况，小组最多25个人
     * 
     * 输出描述:
     * 最大的数字字符串
     * 
     * 示例1
     * 输入
     * 22,221
     * 输出
     * 22221
     * 
     * 输入
     * 
     * 4589,101,41425,9999
     * 输出
     * 
     * 9999458941425101
     * 
     * 通过字符串比较排序, 将较大的数排在前面(这种方式不用考虑总数溢出问题)
     */
    public static void maxNum() {
        Scanner scanner = new Scanner(System.in);
        String[] numStr = scanner.nextLine().split(",");
        Arrays.sort(numStr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        for (String s : numStr) {
            System.out.print(s);
        }
        scanner.close();
    }

    /**
     * 一个整数可以由连续的自然数之和来表示 给定一个整数 计算该整数有几种连续自然数之和的表达式 并打印出每一种表达式
     * 
     * 输入描述
     * 
     * 一个目标整数t 1<= t <=1000
     * 
     * 输出描述 该整数的所有表达式和表达式的个数。如果有多种表达式，输出要求为：
     * 
     * 自然数个数最少的表达式优先输出
     * 每个表达式中按自然数递增的顺序输出，具体的格式参见样例。在每个测试数据结束时，输出一行”Result:X”，其中X是最终的表达式个数。
     * 具体的格式参见样例
     * 
     * 示例1
     * 
     * 输入
     * 
     * 9
     * 输出
     * 
     * 9=9
     * 9=4+5
     * 9=2+3+4
     * Result:3
     */
    public static void printNumAllSeries() {
        Scanner scanner = new Scanner(System.in);
        int sum = scanner.nextInt();

        // a1n = sum - (0+n-1)n/2
        StringBuilder print = new StringBuilder();
        int count = 0;
        for (int n = 1; n < sum; n++) {
            int a1n = sum - (n - 1) * n / 2;
            // 注意 a1n 不能为负数
            if (a1n % n == 0 && a1n > 0) {
                count++;
                int a1 = a1n / n;
                print.append(sum).append("=").append(a1);
                for (int i = 1; i < n; i++) {
                    print.append("+").append(a1 + i);
                }
                System.out.println(print);
                print.delete(0, print.length());
            }
        }
        System.out.println("Result:" + count);
    }

    /**
     * 数组组成的最小数字
     * 
     * 题目描述: 给定一个整型数组，请从该数组中选择3个元素组成最小数字并输出（如果数组长度小于3，则选择数组中所有元素来组成最小数字）。
     * 
     * 输入描述:
     * 
     * 一行用半角逗号分割的字符串记录的整型数组，0 < 数组长度 <= 100，0 < 整数的取值范围 <= 10000。
     * 
     * 输出描述:
     * 
     * 由3个元素组成的最小数字，如果数组长度小于3，则选择数组中所有元素来组成最小数字。
     * 
     * 示例1
     * 
     * 输入
     * 
     * 21,30,62,5,31
     * 输出
     * 
     * 21305
     */
    public static void minNum() {
        // 给定一个int数组 请从改数组中选择3个最小数字并输出
        // 输入 21,30,62,5,31
        // 输出 21305
        // 5,21
        // 215
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] split = str.split(",");
        int[] newArr = null;
        if (split.length < 3) {
            newArr = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                newArr[i] = Integer.parseInt(split[i]);
            }
        } else {
            int[] intArr = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                intArr[i] = Integer.parseInt(split[i]);
            }
            Arrays.sort(intArr);
            newArr = Arrays.copyOf(intArr, 3);
        }

        // 新数组最小组合
        String minCombine = printMinNumber(newArr);
        System.out.println(minCombine);
    }

    private static String printMinNumber(int[] newArr) {
        if (newArr == null || newArr.length == 0) {
            return "";
        }
        int length = newArr.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = String.valueOf(newArr[i]);
        }
        StringBuilder sb = new StringBuilder();
        Arrays.sort(strArr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String c1 = o1 + o2;
                String c2 = o2 + o1;
                return c1.compareTo(c2);
            }
        });

        for (int i = 0; i < length; i++) {
            sb.append(strArr[i]);
        }
        return sb.toString();
    }

    /**
     * 给定两个整数数组
     * array1 array2  数组元素按升序排列
     * 假设从arr1 arr2中分别取出一个元素，可构成一对元素
     * 现在需要取出k对元素，并对取出的所有元素求和
     * 计算和的最小值
     * 注意：两对元素对应arr1 arr2的下标是相同的
     * 视为同一对元素
     * 
     * 输入描述
     * 输入两行数组arr1 arr2
     * 每行首个数字为数组大小size   0<size<=100
     * arr1，2中的每个元素   0< <1000
     * 接下来一行  正整数k   0<k<=arr1.size * arr2.size
     * 输出描述
     * 满足要求的最小值
     * 
     * 例子
     * 
     * 输入
     * 3 1 1 2
     * 3 1 2 3
     * 2
     * 
     * 输出
     * 4
     * 
     * 说明：用例中需要取两个元素，
     * 取第一个数组第0个元素与第二个数组第0个元素组成一个元素
     * [1,1]
     * 取第一个数组第1个元素与第二个数组第0个元素组成一个元素
     * [1,1]
     * 
     * 求和为1+1+1+1=4 ,满足要求最小
     */
    public static void minSum() {
        Scanner scanner = new Scanner(System.in);
        int size1 = scanner.nextInt();
        int[] arr1 = new int[size1];
        for (int i = 0; i < size1; i++) {
            arr1[i] = scanner.nextInt();
        }

        int size2 = scanner.nextInt();
        int[] arr2 = new int[size2];
        for (int i = 0; i < size2; i++) {
            arr2[i] = scanner.nextInt();
        }

        int k = scanner.nextInt();

        ArrayList<Integer> sumArr = new ArrayList<>();

        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                sumArr.add(arr1[i] + arr2[j]);
            }
        }

        Collections.sort(sumArr);
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += sumArr.get(i);
        }

        System.out.println(sum);

        scanner.close();
    }

    public static int calc4(int N) {
        int ans = N, temp = 0, k = 0, j = 1;
        while (N > 0) {
            //先判断个位上是否跳了4，如果个位上是5~9，就先temp=1。
            if (N % 10 > 4) {
                temp += (N % 10 - 1) * k + j;
            } else {
                temp += (N % 10) * k;
            }
            /*
             * 0	1	也即{4}
             * 100	1×9+10	9个1~10范围内含4的数，也即{4,14,24,34,54,64,74,84,94}再加上{40~49}	19个
             * 1000	19×9+100	9个1~100范围内含4的数，再加上{400~499}集合内的100个数	271个
             * 10000	271×9+1000	9个1~1000范围内含4的数，再加上{4000~4999}集合内的1000个数	3439个
             */
            k = k * 9 + j;//k代表跳了多少次4，多收了多少个1元
            j *= 10;//j代表位数，1代表个位，10代表十位
            N /= 10;//相当于将N整体右移一位
        }
        return ans - temp;
    }

    /**
     * 【判断一组不等式是否满足约束并输出最大差】给定一组不等式，判断是否成立并输出不等式的最大差(输出浮点数的整数部分)
     * 要求:
     * 1)不等式系数为 double类型，是一个二维数组
     * 2)不等式的变量为 int类型，是一维数组;
     * 3)不等式的目标值为 double类型，是一维数组
     * 4)不等式约束为字符串数组，只能是:">",">=","<","="，
     * 例如，不等式组:
     * a11x1+a12x2+a13x3+a414x+a15x5<=b1; a21x1+a22x2+a23x3+a24x4+a25x5<=b2; a31x1+a32x2+a33x3+a34x4+a35x5<=b3;
     * 最大差 =max{(a11x1+a12x2+a13x3+a14x4+a15x5-b1),(a21x1+a22x2+a23x3+a24x4+ a25x5-b2),(a31x1+a32x2+a33x3+a34x4+a35x5-b3)},类型为整数(输出浮点数的整数部分)
     * 输入描述:
     * 1)不等式组系数(double类型):
     * a11,a12,a13,a14,a15
     * a21,a22,a23,a24,a25
     * a31,a32,a33,a34,a35
     * 2)不等式变量(int类型):
     * x1,x2,x3,x4,x5 3)不等式目标值(double类型):b1,b2,b3 4)不等式约束(字符串类型):<=,<=,<=
     * 输入:
     * a11,a12,a13,a14,a15,a21,a22,a23,a24,a25, a31,a32,a33,a34,a35,x1,x2,x3,x4,x5,b1,b2,b3,<=,<=,<=
     * 输出描述:true或者 false，最大差
     * 示例:
     * 输入
     * 2.3,3,5.6,7,6;11,3,8.6,25,1;0.3,9,5.3,66,7.8;1,3,2,7,5;340,670,80.6;<=,<=,<=
     * 输出
     * false,458
     */
    public static void maxSub(String str) {
        String[] split = str.split(";");
        int len = split.length;
        String[] operators = split[len - 1].split(",");
        String[] rights = split[len - 2].split(",");
        String[] mutis = split[len - 3].split(",");
        boolean isTrue = true;
        int maxSub = 0;
        for (int i = 0; i < len - 3; i++) {
            String[] curs = split[i].split(",");
            double tempSum = 0;
            for (int i1 = 0; i1 < curs.length; i1++) {
                // 注意取数是 i1
                int muti = Integer.parseInt(mutis[i1]);
                tempSum += (Double.parseDouble(curs[i1]) * muti);
            }
            double right = Double.parseDouble(rights[i]);
            int sub = (int) Math.abs(tempSum - right);
            // 注意绝对值
            maxSub = Math.max(sub, maxSub);

            switch (operators[i]) {
                case "<=":
                    if (tempSum > right) {
                        isTrue = false;
                    }
                    break;
                case "<":
                    if (tempSum >= right) {
                        isTrue = false;
                    }
                    break;
                case "=":
                    if (tempSum != right) {
                        isTrue = false;
                    }
                    break;
                case ">":
                    if (tempSum <= right) {
                        isTrue = false;
                        break;
                    }
            }
        }

        System.out.println(isTrue);
        System.out.println(maxSub);
    }

    /**
     * 素数之积
     *
     * RSA加密算法在网络安全世界中无处不在，它利用了极大整数因数分解的困难度，数据越大，安全系数越高，给定一个32位正整数， 请对其进行因数分解，找出是哪两个素数的乘积。
     *
     * 输入描述:
     *
     * 一个正整数num
     * 0 < num <= 2147483647
     * 输出描述:
     *
     * 如果成功找到，以单个空格分割，从小到大输出两个素数，分解失败，请输出-1 -1
     *
     * 示例1
     *
     * 输入
     *
     * 15
     * 输出
     *
     * 3 5
     * 说明
     *
     * 因数分解后，找到两个素数3和5，使得3*5=15，按从小到大排列后，输出3 5
     *
     * 示例2
     *
     * 输入
     *
     * 27
     * 输出
     *
     * -1 -1
     * 说明
     *
     * 通过因数分解，找不到任何素数，使得他们的乘积为27，输出-1 -1
     */
    public static void rsaPrime(int n) {
        int max = (int)Math.sqrt(n);

        for (int i = 2; i <= max; i++) {
            if (isPrime(i) && n % i == 0 && isPrime(n / i)) {
                System.out.println(i + " " + (n / i));
                return;
            }
        }
        System.out.println("-1 -1");
    }

    public static boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }

        int max = (int) Math.sqrt(num);

        for (int i = 3; i <= max; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 将两个整型数组按照升序合并，并且过滤掉重复数组元素。
     *
     * 输出时相邻两数之间没有空格。
     *
     * 请注意本题有多组样例。
     *
     * 输入描述:
     *
     * 输入说明，按下列顺序输入：
     * 1 输入第一个数组的个数
     * 2 输入第一个数组的数值
     * 3 输入第二个数组的个数
     * 4 输入第二个数组的数值
     *
     * 输出描述:
     * 输出合并之后的数组
     * 示例1
     *
     * 输入
     * 复制
     *
     * 3
     * 1 2 5
     * 4
     * -1 0 3 2
     * 输出
     *
     * -101235
     */
    public static void mergeArray(int[] arr1, int[] arr2) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int i : arr1) {
            if (!map.containsKey(i)) {
                map.put(i, true);
            }
        }

        for (int i : arr2) {
            if (!map.containsKey(i)) {
                map.put(i, true);
            }
        }

        Integer[] nums = map.keySet().toArray(new Integer[0]);
        Arrays.sort(nums);
        for (Integer num : nums) {
            System.out.print(num);
        }
    }

    /**
     * 题目描述
     * 输入一个数 M ，表示数组中有 M 个数
     * 输入 M 个数。
     * 输入 n
     * 求数组 M 中，去除重复值后，最大 n 个数和最小 n 个数的和
     * 注意：最大和最小的数中不能有重复值，否则输出 -1
     *
     * 样例输入
     * 5
     * 3 3 2 4 2
     * 2
     * 样例输出
     * -1
     * 说明
     * 去除重复后最大的2个数为[4,3]，最小的2个数为[2,3]；有相同值，所以返回-1
     *
     * 样例输入
     * 5
     * 3 3 2 4 2
     * 1
     * 样例输出
     * 6
     * 说明
     * 去除重复后最大的1个数为[4]，最小的1个数为[2]；没有相同值，返回6
     */
    public static int maxMinN(int[] arr, int n) {
        HashMap<Integer, Boolean> map = new HashMap<>();

        for (int i : arr) {
            if (!map.containsKey(i)) {
                map.put(i, true);
            }
        }
        Integer[] arr2 = map.keySet().toArray(new Integer[0]);
        Arrays.sort(arr2);

        int len = arr2.length;

        // min 0 ~ n -1 ;
        // len - n ~ len - 1;
        if (len - n <= n -1) {
            return -1;
        }

        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += arr2[i];
        }

        for (int i = len - n; i < len; i++) {
            sum += arr2[i];
        }

        return sum;
    }

    /**
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     *
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     *
     * 例如，121 是回文，而 123 不是。
     * 
     *
     * 示例 1：
     *
     * 输入：x = 121
     * 输出：true
     * 示例2：
     *
     * 输入：x = -121
     * 输出：false
     * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * 示例 3：
     *
     * 输入：x = 10
     * 输出：false
     * 解释：从右向左读, 为 01 。因此它不是一个回文数。
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int v = 0;
        String s = String.valueOf(x);
        int length = s.length();
        for (int i = 0; i < length / 2; i++) {
            if (s.charAt(i) != s.charAt(length - 1 - i)) {
                 return false;
            }
        }

        return true;
    }

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     *
     * 你可以按任意顺序返回答案。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * 示例 2：
     *
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * 示例 3：
     *
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     */
    public static int[] twoSum(int[] nums, int target) {
        // 这里可以不用先全初始化一个hashmap; 省一次遍历;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(var i = 0; i < nums.length; i ++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }

        return new int[]{};
    }
}
