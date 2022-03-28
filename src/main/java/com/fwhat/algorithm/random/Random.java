package com.fwhat.algorithm.random;

public class Random {

    @FunctionalInterface
    public interface DoubleRandom {
        double run();
    }

    @FunctionalInterface
    public interface IntRandom {
        int run();
    }

    public static void main(String[] args) {
        var times = 100000;
        var count = 0;
        for (int i = 0; i < times; i++) {
            if (randomDouble(8) < 5) {
                count++;
            }
        }

        // ≈ 5 / 8
        System.out.println("randomDouble 8 < 5");
        System.out.println((double) count / (double) times);


        count = 0;
        for (int i = 0; i < times; i++) {
            if (xToPower2(() -> randomDouble(8)) < 5) {
                count++;
            }
        }

        // ≈ (5 / 8) ^ 2
        System.out.println("xToPower2(randomDouble 8) < 5");
        System.out.println((double) count / (double) times);

        count = 0;
        for (int i = 0; i < times; i++) {
            if (randomInt(10) < 5) {
                count++;
            }
        }

        System.out.println("randomInt 10 < 5");
        System.out.println((double) count / (double) times);

        count = 0;
        for (int i = 0; i < times; i++) {
            if (randomTo01(() -> randomInt(6), 0, 5) == 1) {
                count++;
            }
        }

        System.out.println("randomTo01(randomInt 6, 0, 5) < 5");
        System.out.println((double) count / (double) times);

        count = 0;
        for (int i = 0; i < times; i++) {
            if (randomTo01(() -> randomInt(5), 0, 4) == 1) {
                count++;
            }
        }

        System.out.println("randomTo01(randomInt 5, 0, 4) < 5");
        System.out.println((double) count / (double) times);

        var counts = new int[18];

        for (int i = 0; i < times; i++) {
            counts[randomToRandom(() -> randomInt(5), 0, 4, 10, 17)]++;
        }

        for (int i = 10; i <= 17; i++) {
            System.out.println("数字 " + i + " 出现 " + counts[i] + "次");
        }

        System.out.println("==================");

        count = 0;
        for (int i = 0; i < times; i++) {
            if (random01To01(() -> Math.random() > 0.87 ? 1 : 0) == 1) {
                count++;
            }
        }

        System.out.println("random01To01(Math.random() > 0.87 ? 1 : 0) == 1");
        System.out.println((double) count / (double) times);
    }

    /**
     * [0, n) 之间的小数
     */
    public static double randomDouble(int n) {
        return Math.random() * n;
    }

    /**
     * [0, n) 之间的小数
     */
    public static int randomInt(int n) {
        return (int) (Math.random() * n);
    }

    /**
     * 由 0 ~ x 的等概率随机方法; 生成 0 ~ x^2 的等概率随机
     */
    public static double xToPower2(DoubleRandom random) {
        return Math.max(random.run(), random.run());
    }

    /**
     * 由 a ~ b 等概率 返回 0, 1 等概率
     */
    public static int randomTo01(IntRandom random, int a, int b) {
        if ((b - a + 1) % 2 == 0) {
            return random.run() >= (b - a + 1) / 2 ? 1 : 0;
        }

        var value = 0;
        // 如果是奇数个, 去除中间位的值; 中间值概率会平分到两端，所以两端等概率
        var mid = a + (b - a) / 2;

        do {
            value = random.run();
        } while (value == mid);

        return value > mid ? 1 : 0;
    }

    /**
     * 由 [0, 1] 不等概率 返回 0, 1 等概率
     * 已知 randomF 出现1的概率是P
     * 则 两次 randomF() 的结果
     * 1. 00 (1-P)(1-P)
     * 2. 11 (P)(P)
     * 3. 01(1-P)(P)
     * 4. 10(P)(1-P)
     *
     * 其中 01, 10 等概率相等; 即保证 两次随机 不相等，就能得到 0 (1) 1 (0), 等概率;
     */
    public static int random01To01(IntRandom randomF) {
        var random = 0;

        do {
            random = randomF.run();
        } while (random == randomF.run());

        return random;
    }

    /**
     * 等概率返回 [a, b] 随机数
     */
    public static int randomAToB(int a, int b) {
        return randomInt(b - a) + a;
    }

    @FunctionalInterface
    public interface IntRandomWith {
        int run(int a);
    }

    /**
     * 由 [a, b] 等概率转换为 [A, B] 等概率
     * 1. 将 random [a, b] 转化为 0, 1 随机函数 f01
     * 2. 将 [toA, toB] 转化为 [0 , toB-toA] + toA
     * 3. 计算 [0, toB-toA] 至少需要多少位bit 存储 n
     * 4. 通过 f01 生成 n 位组成的 随机函数 [f01(), f01(), f01()] f0ToSub (这里的函数会返回 大于 sub 的数)
     * 5. 通过 f0ToSub 去除 大于 sub 后的随机数;
     * 6. 将得到的随机数 + toA
     */
    public static int randomToRandom(IntRandom randomF, int a, int b, int toA, int toB) {
        IntRandom f01 = () -> randomTo01(randomF, a, b);

        var sub = toB - toA;

        var tempSum = 0;
        var bitCount = 0;
        while (tempSum < sub) {
            tempSum += 1 << bitCount;
            bitCount++;
        }

        IntRandomWith f0ToSub = (int bit) -> {
            var random = 0;

            while (bit >= 0) {
                random += f01.run() << bit;
                bit--;
            }

            return random;
        };

        var random = 0;

        do {
            random = f0ToSub.run(bitCount);
        } while (random > sub);

        return random + toA;
    }
}
