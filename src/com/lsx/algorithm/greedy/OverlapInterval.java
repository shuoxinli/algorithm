package com.lsx.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法经典题目：区间调度
 * 题目：
 *      给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 *      可以认为区间的终点总是大于它的起点。
 *      区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * 例子：
 *      输入: [ [1,2], [2,3], [3,4], [1,3] ]
 *      输出: 1
 *      解释: 移除 [1,3] 后，剩下的区间没有重叠。
 */
public class OverlapInterval {

    /**
     * 贪心算法的原则：每次局部都取最优，得到的全局结果就是最优的！
     *
     * 注意： 贪心思路可以有多种，但是有的不一定正确！！！
     *
     * 区间调度思路：
     *      1. 每个区间按end升序排，取出 end 最小的那个区间 x
     *      2. 剔除与该区间 x 重叠的其他区间
     *      3. 重复1，2步骤，最终取到的所有 x 区间就是最多不重叠的区间。
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0){
            return 0;
        }
        int n = intervals.length;
        // 将每个区间按end升序排
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 取的是 end 做比较
                return o1[1] - o2[1];
            }
        });
        // 取出第一个区间 x 的 end
        int x_end = intervals[0][1];
        int count = 1; // 最少一个了
        // 循环剔除重叠区间，并不断更新区间 x 的 end
        for (int i=1;i<n;i++){
            // 剔除的思路 就是下一区间的 start < 当前区间 x 的 end
            int start = intervals[i][0];
            if (start >= x_end){
                // 更新区间 x 的 end
                count++;
                x_end = intervals[i][1];
            }
        }
        return n-count;
    }
}
