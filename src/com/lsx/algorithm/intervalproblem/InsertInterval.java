package com.lsx.algorithm.intervalproblem;

import java.util.Arrays;

/**
 * 区间问题之  插入区间
 * <p>
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然 有序且不重叠（如果有必要的话，可以 合并区间）。
 * <p>
 * 示例 2:：
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 新区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠，因此合并成为 [3,10]。
 */
public class InsertInterval {

    /**
     * 分析：
     * 一般按起始端点 或 结尾端点 排序。本题已按起始端点升序排，直接遍历区间，找到新区间插入位置即可。
     * 第一步： 将新区间左边且相离的区间加入结果集，因为与新区间不冲突。
     * （遍历时，如果当前区间的结束位置小于新区间的开始位置，说明当前区间在新区间的左边且相离）
     * 第二步：判断当前区间是否与新区间重叠，重叠的话就进行合并，直到遍历到当前区间在新区间的右边且相离，
     * 将最终合并后的新区间加入结果集。
     * 第三步：将新区间右边且相离的区间加入结果集。
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //结果集
        int[][] res = new int[intervals.length + 1][2];
        int idx = 0;
        //遍历区间列表
        //第一步，新区间左边且相离加入结果集
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            res[idx++] = intervals[i++];
        }
        //第二步，解决重叠部分
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        //加入新区间
        res[idx++] = newInterval;
        //第三步，新区间右边且相离加入结果集
        while (i < intervals.length) {
            res[idx++] = intervals[i++];
        }
        //返回结果集有值的部分
        return Arrays.copyOf(res, idx);
    }
}
