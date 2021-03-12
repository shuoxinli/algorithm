package com.lsx.algorithm.dp.pack01;
/*
 * 题目归属：动态规划，0-1背包问题
 * 题目：有一个容量为 N 的背包，要用这个背包装下物品的价值最大，这些物品有两个属性：体积 w 和价值 v。
 * 思路：定义一个二维数组 dp 存储最大价值，其中 dp[i][j] 表示前 i 件物品体积不超过 j 的情况下能达到的最大价值。
 * 		设第 i 件物品体积为 w，价值为 v，根据第 i 件物品是否添加到背包中，可以分两种情况讨论：
 * 		当第i件物品没添加到背包，则dp[i][j] = d[i-1][j]
 * 		当第i件物品添加到背包中，则dp[i][j] = dp[i-1][j-w] + v;
 * 综上：dp[i][j] = max(dp[i-1][j],dp[i-1][j-w]+v); 取两者中的最大
 */
public class Pack01 {
	
	//W为总容量，N为物品个数
	public static int knapsack(int W,int N,int[] weights,int[] values) {
		int[][] dp = new int[N+1][W+1];
		//遍历所有物品
		for(int i=1;i<=N;i++) {
			//取上一件物品的体积和价值
			int w = weights[i-1], v = values[i-1];
			//遍历所有容量
			for (int j = 1; j <= W; j++) {
				//遍历结束后，w-W这段区间都是一样的dp[i][j]
	            if (j >= w) {
	                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
	            } else {
	                dp[i][j] = dp[i - 1][j];
	            }
	        }	
		}
		return dp[N][W];
	}
	
	/*
	 * 空间优化：dp[j]既可以表示dp[i-1][j]，也可以表示dp[i][j]。
	 * 此时：dp[j] = max(dp[j], dp[j-w]+v)
	 * 因为 dp[j-w] 表示 dp[i-1][j-w]，因此不能先求 dp[i][j-w]，以防将 dp[i-1][j-w] 覆盖。
	 * 也就是说要先计算 dp[i][j] 再计算 dp[i][j-w]，在程序实现时需要按倒序来循环求解。
	 */
	public static int knapsack2(int W,int N,int[] weights,int[] values) {
		int[] dp = new int[W+1];
		for(int i=1;i<=N;i++) {
			int w = weights[i-1],v = values[i-1];
			//画图就知了
			for(int j=W;j>=1;j--) {
				if(j>=w) {
					dp[j] = Math.max(dp[j], dp[j-w]+v);
				}
			}
		}
		return dp[W];
	}
	public static void main(String[] args) {
		int N = 4,W=8;
		int[] weights = {2,3,4,5};
		int[] values = {3,4,5,6};
		int knapsack = knapsack(W, N, weights, values);
		System.out.println(knapsack);
		
		System.out.println(knapsack2(W, N, weights, values));
	}
}
