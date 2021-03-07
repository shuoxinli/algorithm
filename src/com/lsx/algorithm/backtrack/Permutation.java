package com.lsx.algorithm.backtrack;

import java.util.*;

/**
 * 题目：
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class Permutation {

    // 全局变量，记录最终所有路径列表
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        // 记录当前路径
        List<Integer> track = new ArrayList<>();
        backtrack(nums, track);
        return res;
    }

    /**
     * 回溯算法关键：
     * 【路径】：已经做出的选择
     * 【选择列表】：当前可以做的选择
     * 【结束条件】：到达决策树底层，无法再做选择的条件。
     * <p>
     * 回溯算法就是在遍历多叉树，核心就是在for循环里面，在递归调用前【做选择】，在递归调用后【撤销选择】
     * <p>
     * 路径：记录在 track 中
     * 选择列表：nums 中不存在于 track 的那些元素
     * 结束条件：nums 中的元素全都在 track 中出现
     */
    private void backtrack(int[] nums, List<Integer> track) {
        // 结束条件
        if (track.size() == nums.length) {
            // 记录当前路径，记得要拷贝
            res.add(new ArrayList<>(track));
            return;
        }
        // 遍历选择列表
        for (int i = 0; i < nums.length; i++) {
            // 因为是不重复，直接判断是否存在当前路径里即可
            if (track.contains(nums[i])) {
                continue;
            }
            // 做出选择
            track.add(nums[i]);
            // 递归
            backtrack(nums, track);
            // 撤销选择，移除最后一个
            track.remove(track.size() - 1);
        }
    }

    /**
     * 回溯 + 剪枝
     * 难度增加：给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     * 输入：nums = [1,1,2]
     * 输出：
     * [[1,1,2],
     * [1,2,1],
     * [2,1,1]]
     * <p>
     * 思路：在遍历的过程中，一边遍历一边检查，在一定会产生重复结果集的地方剪枝。
     * <p>
     * 先对原先数组进行排序，方便后续的检查
     * 使用一个标记数组，来标记当前数字是否被使用过，
     * 当出现与之前相同的数字时，判断之前的数字是正在使用中还是已经被撤销了，
     * - 正在使用，说明下面不会再出现，不会有重复
     * - 已经被撤销了，说明下面还会被使用，肯定会重复，减去这样的分支。
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        // 结果集
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        // 对原数组进行排序，方便后续检查，排序是剪枝的前提
        Arrays.sort(nums);

        // 标记数组，标记是否在使用
        boolean[] used = new boolean[len];
        // 定义一个栈，方便进出，记录当前路径
        Deque<Integer> path = new ArrayDeque<>(len);
        backtrack(nums,used,path,res);
        return res;
    }

    private void backtrack(int[] nums, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        // 结束条件
        if (nums.length == path.size()) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 遍历选择列表
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                // 当前数字正在被使用，不能重复使用
                continue;
            }
            /**
             * 剪枝的条件：
             *  i>0 确保nums[i-1]有意义
             *  !used[i-1] 说明nums[i-1]在遍历过程中刚刚被撤销，后续会重复
             */
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            // 做出选择
            path.addLast(nums[i]);
            used[i] = true; // 正在使用
            // 递归
            backtrack(nums, used, path, res);
            // 撤销选择
            used[i] = false; // 撤销使用
            path.removeLast();
        }
    }
}
