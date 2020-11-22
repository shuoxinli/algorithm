package com.lsx.algorithm.dp;

/**
 * 完全背包问题：
 * <p>
 * 题目：给定不同面额的硬币和一个总金额，计算可以凑成总金额的硬币组合数，假设每一种面额的硬币有无限个。
 * 示例：
 * 输入：amount = 5, coins = [1,2,5]
 * 输出：4
 * 解释：有四种方式可以凑成总金额：
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 */
public class CompletePack {

    /**
     * 翻译成背包问题：
     * 有一个背包，最大容量为amount，有一系列物品coins，每个物品的重量为coins[i]，每个物品的数量无限。请问有多少种方法，能够把背包恰好装满？
     * <p>
     * 与0-1经典背包最大的区别就是，每个物品的数量是无限的，这也就是【完全背包问题】。
     * <p>
     * N为物品种类，amount为总金额，coins为物品的重量。
     */
    public int completePack(int N, int amount, int[] coins) {
        //第一步明确状态，定义dp数组
        int[][] dp = new int[N + 1][amount + 1];
        //第二步初始化base case
        for (int i = 0; i <= N; i++) {
            // 要凑金额为0
            dp[i][0] = 1;
        }
        for (int j = 0; j <= amount; j++) {
            // 没有物品可选
            dp[0][j] = 0;
        }
        // 第三步遍历状态，根据状态转移方程作选择，注意coins是从0开始，状态dp是从1开始
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i - 1] > 0) {
                    // 如果找上一个j-coins[i]金额小于0，只能直接继承dp[i-1][j]，否则
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][amount];
    }

    // dp数组的转移只和dp[i][...] 和 dp[i-1][..]有关，所以可以压缩状态，进一步降低算法的空间复杂度：
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i] >= 0) {
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
        }
        return dp[amount];
    }
}
