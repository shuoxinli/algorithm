package com.lsx.algorithm.dp;

/**
 * 经典0-1背包问题：
 *  ⼀个可装载重量为 W 的背包和 N 个物品，每个物品有重量和价值两 个属性。
 *  其中第 i 个物品的重量为 wt[i] ，价值为 val[i] ，现在让你⽤ 这个背包装物品，最多能装的价值是多少？
 */
public class Knapsack01 {

    public int knapsack(int W, int N, int[] wt, int[] val){
        // 定义dp数组，二维
        int[][] dp = new int[N+1][W+1];

        // 初始化dp base case
        for (int i = 0;i<=W;i++){
            // 0个物体，无论可装载多少都是0
            dp[0][i] = 0;
        }
        for (int j=0;j<=N;j++){
            // 可装载0，无论多少物体都是0
            dp[j][0] = 0;
        }
        // 遍历状态
        for (int i=1;i<=N;i++){
            for (int j=1;j<=W;j++){
                // 状态转移方程，i物体放或不放，取最大
                dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-wt[i]]+val[i]);
            }
        }
        return dp[N][W];
    }
}
