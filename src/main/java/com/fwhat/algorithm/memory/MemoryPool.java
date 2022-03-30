package com.fwhat.algorithm.memory;

import java.util.TreeMap;

public class MemoryPool {

    public static void main(String[] args) {
        String allocate = allocate("64:2,128:1,32:4,1:128", "50,36,64,128,127");
        System.out.println(allocate);
    }

    /**
     * 内存资源分配
     *
     * 有一个简易内存池，内存按照大小粒度分类，每个粒度有若干个可用内存资源，用户会进行一系列内存申请，需要按需分配内存池中的资源，返回申请结果成功失败列表。
     *
     * 分配规则如下：
     *
     * 1、分配的内存要大于等于内存申请量，存在满足需求的内存就必须分配，优先分配粒度小的，但内存不能拆分使用。
     *
     * 2、需要按申请顺序分配，先申请的先分配。
     *
     * 3、有可用内存分配则申请结果为true，没有可用内存分配则返回false。
     *
     * 输入描述:
     *
     * 输入为两行字符串：
     *
     * 第一行为内存池资源列表，包含内存粒度数据信息，粒度数据间用逗号分割，一个粒度信息内部用冒号分割，冒号前为内存粒度大小，冒号后为数量。
     *
     * 资源列表不大于1024，每个粒度的数量不大于4096
     *
     * 第二行为申请列表，申请的内存大小间用逗号分隔。申请列表不大于100000
     *
     * 如：
     * 64:2,128:1,32:4,1:128
     * 50,36,64,128,127
     * 输出描述:
     *
     * 输出为内存池分配结果。
     *
     * 如：
     * true,true,true,false,false
     */
    public static String allocate(String memoryPoolInfo, String applications) {
        if (memoryPoolInfo == null || memoryPoolInfo.length() == 0) {
            return "0";
        }

        String[] split = memoryPoolInfo.split(",");

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (String s : split) {
            String[] pool = s.split(":");
            if (pool.length != 2) {
                return "0";
            }
            int value = Integer.parseInt(pool[1]);
            if (value > 0) {
                map.put(Integer.parseInt(pool[0]), value);
            }
        }

        StringBuilder res = new StringBuilder();
        String[] applicationArr = applications.split(",");
        for (String application : applicationArr) {
            Integer key = map.ceilingKey(Integer.parseInt(application));
            if (res.length() != 0) {
                res.append(",");
            }
            if (key == null) {
                res.append("false");
                continue;
            }
            res.append("true");
            Integer num = map.get(key);
            num--;
            if (num > 0) {
                map.put(key, num);
            } else {
                map.remove(key);
            }
        }

        return res.toString();
    }
}
