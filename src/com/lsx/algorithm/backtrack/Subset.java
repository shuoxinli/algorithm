package com.lsx.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 回溯算法：子集问题
 * 题目：给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 举例：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
public class Subset {

    List<List<Integer>> res = new ArrayList<>();

    /**
     * 通过 start 来排除索引之前的数字，避免重复选择
     */
    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length == 0) {
            return res;
        }
        // 记录当前路径
        List<Integer> track = new ArrayList<>();
        backtrack(nums, 0, track);
        return res;
    }

    private void backtrack(int[] nums, int start, List<Integer> track) {
        // 当前每个集合都记录
        res.add(new ArrayList<>(track));
        // for 循环控制树的横向遍历，start 限制了索引之前的数字，避免重复选择
        for (int i = start; i < nums.length; i++) {
            // 做出选择
            track.add(nums[i]);
            // 递归控制树的深度遍历，start+1 排除选择过的数字
            backtrack(nums, i + 1, track);
            // 撤销选择
            track.remove(track.size() - 1);
        }
    }

    /**
     * 进阶：给定一个可能包含【重复】元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     *
     * 示例:
     * 输入: [1,2,2]
     * 输出:
     * [
     *   [2],
     *   [1],
     *   [1,2,2],
     *   [2,2],
     *   [1,2],
     *   []
     * ]
     */

    /**
     * 只要有重复元素，先排序，就使用【标记数组】，来判断之前撤销的元素是否和当前元素相同，
     * 相同，则跳过，避险重复，因为是子集问题，所以子集中元素顺序都是可变的。
     * （画回溯树即可知）
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        // 记录当前路径
        List<Integer> track = new ArrayList<>();
        backtrack(nums, 0, used, track);
        return res;
    }

    private void backtrack(int[] nums, int start, boolean[] used, List<Integer> track) {
        // 当前每个集合都记录
        res.add(new ArrayList<>(track));
        // for 循环控制树的横向遍历，start 限制了索引之前的数字，避免重复选择
        for (int i = start; i < nums.length; i++) {
            // 与前一个相同，并且前一个刚刚被撤销，直接跳过，避免重复
            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
                continue;
            }
            // 做出选择
            track.add(nums[i]);
            used[i] = true;
            // 递归控制树的深度遍历，start+1 排除选择过的数字
            backtrack(nums, i + 1, used, track);
            // 撤销选择
            used[i] = false;
            track.remove(track.size() - 1);
        }
    }
}
