package com.lsx.algorithm.dp;

/**
 * 题目归属：动态规划
 * 题目：给定一个无序整数数组，找到其中最长上升子序列的长度。
 * 例子：nums = [10,9,2,5,3,7,101,18] return 4 [2,3,7,101]
 * 思路：利用动态，定义一个动态数组dp，dp[i]表示以nums[i]结尾的最长上升子序列的长度，
 * 如果i<n,且nums[i]<nums[n],那么dp[n]=dp[i]+1。
 * 则遍历n之前的数，找到小于nums[n]且dp[i]最大的值+1就为dp[n]的值
 * 状态转移方程：	dp[n]=max{1,dp[i]+1| i<n && nums[i]<nums[n] }
 */
public class LIS {

    /**
     * 动态规划：O(n*n)
     */
    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        //定义一个dp数组，存每个位置的最长上升子序列的长度
        int[] dp = new int[n];
        // 遍历数组
        for (int i = 0; i < n; i++) {
            // 定义当前位置的最长上升子序列长度为1，（只有本身）
            int max = 1;
            // 遍历i之前的dp数组
            for (int j = 0; j < i; i++) {
                // 找到比当前元素nums[i]小，且dp值最大的，+1就是当前i的dp值
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }

        // 取出dp中最大的值
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }
}
