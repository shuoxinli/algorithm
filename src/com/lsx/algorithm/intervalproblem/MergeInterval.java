package com.lsx.algorithm.intervalproblem;

import java.util.Arrays;

/**
 * 区间问题之 合并区间
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:：
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释:  区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6]。
 */
public class MergeInterval {

    /**
     * 分析：
     * 将区间按起始端点升序排，然后逐个判断当前区间与前一个区间是否重叠，
     * 不重叠直接将当前区间加入结果集，重叠将当前区间与前一个合并。
     */
    public int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals,(v1,v2) -> v1[0] - v2[0]);
        //结果集
        int[][] res = new int[intervals.length][2];
        //遍历区间
        int idx = -1;
        for (int[] interval : intervals){
            //结果集为空 或 当前区间起始端点 > 结果集最后区间的终止端点，不重叠，将当前区间加入结果集
            if (idx == -1 || interval[0] > res[idx][1]){
                res[++idx] = interval;
            }else{
                //当前区间的起始端点 <= 结果集最后区间的终止端点，重叠，更新结果集最后区间的终止端点
                res[idx][1] = Math.max(res[idx][1],interval[1]);
            }
        }
        //返回结果集有效值部分
        return Arrays.copyOf(res,idx+1);
    }
}
