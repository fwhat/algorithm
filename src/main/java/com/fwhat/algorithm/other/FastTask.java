package com.fwhat.algorithm.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class FastTask {
    public static void main(String[] args) {
        fastTask(new String[]{"1 1", "2 2"});
        fastTask(new String[]{"2 2"});
        fastTask(new String[]{"1 1", "2 2", "3 3"});
    }

    /**
     * 题目描述：
     * 你有 n 台机器编号为 1~n，每台都需要完成完成一项工作，机器经过配置后都能完成独立完成一项工作。
     *
     * 假设第 i 台机器你需要花 B 分钟进行设置，然后开始运行，J 分钟后完成任务。
     *
     * 现在，你需要选择布置工作的顺序，使得用最短的时间完成所有工作。
     *
     * 注意，不能同时对两台机器进行配置，但配置完成的机器们可以同时执行它们各自的工作。
     *
     * 注：此题对效率有要求，请考虑高效的实现方式
     *
     * 输入描述：
     * 第一行输入代表总共有 M 组任务数据（1<M<=10）。
     * 每组数第一行为一个整数，指定机器的数量 N（0<N<=1000）。
     * 随后的 N 行每行两个整数，第一个表示 B（0<=B<=10000），第二个表示 J（0<=J<=10000）。
     * 每组数据连续输入，不会用空行分隔。
     * 各组任务单独计时。
     * 输出描述：
     * 对于每组任务，输出最短完成时间，且每组的结果独占一行。
     * 例如，两组任务就应该有两行输出。
     * 示例一
     *
     * 输入
     *
     * 1
     * 1
     * 2 2
     * 输出
     *
     * 4
     * 解释：
     *
     * 第一行1为一组任务，
     * 第二行1代表只有一台机器，
     * 第三行表示该机器配置需2分钟，执行需要2分钟。
     * 示例二
     *
     * 输入
     *
     * 2
     * 2
     * 1 1
     * 2 2
     * 3
     * 1 1
     * 2 2
     * 3 3
     * 输出
     *
     * 4
     * 7
     * 解释：
     *
     * 第一行2代表两组任务，
     * 第二行2代表第一组任务有2个机器，
     * 第三行1 1代表机器1配置需要1分运行需要1分，
     * 第四行2 2代表机器2配置需要2分运行需要2分，
     * 第五行3代表第二组任务需要3个机器，
     * 第6-8行分别表示3个机器的配置与运行时间。
     */
    public static void fastTask(String[]... groups) {
        for (String[] group : groups) {
            ArrayList<Task> tasks = new ArrayList<>();

            for (String value : group) {
                String[] s = value.split(" ");
                tasks.add(new Task(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
            }

            tasks.sort(new Comparator<Task>() {
                @Override
                public int compare(Task o1, Task o2) {
                    int res = Integer.compare(o2.run, o1.run);
                    if (res == 0) {
                        return Integer.compare(o1.config, o2.config);
                    }

                    return res;
                }
            });
            int total = 0;
            while (!tasks.isEmpty()) {
                total++;
                ArrayList<Task> remove = new ArrayList<>();
                for (Task task : tasks) {
                    if (task.isRunning) {
                        task.run--;
                        if (task.run == 0) {
                            // 注意循环内不能删除成员
                            remove.add(task);
                        }
                        continue;
                    }
                    task.config --;
                    if (task.config == 0) {
                        task.isRunning = true;
                    }
                    break;
                }
                for (Task task : remove) {
                    tasks.remove(task);
                }
            }
            System.out.println(total);
        }
    }

    private static class Task {
        public int config;

        public int run;

        public boolean isRunning = false;

        Task(int config, int run) {
            this.config = config;
            this.run = run;
        }
    }
}
