package com.lsx.algorithm.dp;

import java.util.Arrays;

/**
 * 子集分割： 背包应用
 * 题目：
 * 给定一个只包含正整数的非空数组，是否可以将这个数组分割成两个子集，使得两个子集的元素相等。
 * <p>
 * 示例：
 * 输入：[1,5,11,5]
 * 输出：true  可以分割成[1,5,5] 和 [11]
 */
public class SubsetDivision {

    /**
     * 如何转为背包问题：
     * 对集合求和sum，然后转为：
     * 对于容量为sum/2的背包，N个物品，物品i的体积为nums[i-1],能否恰好装满背包？
     */
    boolean canPartition(int[] nums) {
        // 求和，reduce 流式求和
        int sum = Arrays.stream(nums).reduce(0, Integer::sum);
        if (sum % 2 != 0) {
            // 奇数和不能分割为两个相等的子集
            return false;
        }
        int len = nums.length;
        // 定义dp数组
        boolean[][] dp = new boolean[len + 1][sum / 2];
        // 初始化base case
        for (int i = 0; i < len; i++) {
            dp[i][0] = true;
        }
        for (int j = 0; j < sum / 2; j++) {
            dp[0][j] = false;
        }
        // 遍历两种状态，选择状态转移
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= sum / 2; j++) {
                if (j - nums[i - 1] < 0) {
                    // 背包余下容量装不了i物品
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 可选择装或不装
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }

            }
        }
        return dp[len][sum / 2];
    }
}
