package com.lsx.algorithm.intervalproblem;

import java.util.Arrays;

/**
 * 区间问题之 重叠区间
 *
 * 给定一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，请你判断一个人是否能够参加这里面的全部会议。
 *
 * 示例 1:：
 * 输入: intervals = [[0,30],[5,10],[15,20]]
 * 输出: false
 * 解释: 存在重叠区间，一个人在同一时刻只能参加一个会议。
 */
public class OverlapInterval {

    /**
     * 分析：
     * 将区间按开始时间排序，然后遍历一遍即可判断是否有重叠
     */
    public boolean canAttendMeetings(int[][] intervals) {
        //将区间按开始端点升序排
        Arrays.sort(intervals,(v1,v2) -> v1[0] - v2[0]);
        //遍历区间，如果下个区间的开始端点 小于 当前区间的结束端点，即重叠
        for (int i=1;i<intervals.length;i++){
            if (intervals[i][0] < intervals[i-1][1]){
                return false;
            }
        }
        return true;
    }
}
