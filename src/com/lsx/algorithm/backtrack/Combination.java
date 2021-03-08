package com.lsx.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯算法：组合问题
 * 题目： 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 举例：输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class Combination {

    List<List<Integer>> res = new ArrayList<>();

    /**
     * 典型的回溯算法：
     * n 决定了树的宽度，k 决定了树的高度。
     */
    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0) {
            return res;
        }
        // 记录当前列表
        List<Integer> track = new ArrayList<>();
        backtrack(n, k, 1, track);
        return res;
    }

    private void backtrack(int n, int k, int start, List<Integer> track) {
        if (k == track.size()) {
            // 达到底部
            res.add(new ArrayList<>(track));
            return;
        }
        // for 循环控制了树的横向遍历，start 限制了索引之前的数字，避免重复选择
        for (int i = start; i <= n; i++) {
            // 做出选择
            track.add(i);
            // 递归控制了树的深度遍历，start+1 排除了已选择过的数字
            backtrack(n, k, i + 1, track);
            // 撤销选择
            track.remove(track.size() - 1);
        }
    }
}
