package com.lsx.algorithm.doublepointer;

import java.util.ArrayList;
import java.util.List;

/**
 * 双指针： 取区间列表的集合
 *
 * 题目：
 *      给定两个由一些 闭区间 组成的列表，firstList 和 secondList ，其中 firstList[i] = [starti, endi] 而 secondList[j] = [startj, endj] 。
 *      每个区间列表都是成对 不相交 的，并且 已经排序 。
 *      返回这 两个区间列表的交集 。
 *
 * 举例：
 *      输入：firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 *      输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 */
public class IntervalIntersection {

    /**
     * 思路：
     *      区间都是排好序的，用两个指针分别扫描 A,B两数组，根据子区间的左右端，求出一个交集区间
     *      指针移动，直到任一指针越界，结束得到交集区间列表。
     *
     * 如何判断交集区间？
     *      交集区间的 start 取的是 A,B 子区间中较大的左界
     *      交集区间的 end 取的是 A,B 子区间中较小的右界
     *      当 start <= end 时，就存在交集区间
     *
     * 什么时机可以移动指针？
     *      得到交集区间后，较先结束的子区间是不可能再与别的子区间有重叠，所以移动该指针。
     *      较长的子区间还有可能与别的子区间重叠，暂时不移动。
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res = new ArrayList<>();
        int i=0;
        int j=0;
        while (i<firstList.length && j<secondList.length){
            int start = Math.max(firstList[i][0],secondList[j][0]);
            int end = Math.min(firstList[i][1],secondList[j][1]);

            if (start <= end){
                res.add(new int[]{start,end});
            }

            // 判断谁先结束，谁移动
            if (firstList[i][1] < secondList[j][1]){
                i++;
            }else {
                j++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
