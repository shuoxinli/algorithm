package com.lsx.algorithm.dp;
/*
 * 题目归属：动态规划
 * 题目：给一个矩阵，求从矩阵的左上角到右下角的最小路径和，每次只能向右和向下移动。
 * 例子：[[1,3,1],
		 [1,5,1],
		 [4,2,1]]  return 1+3+1+1+1=7
	思路：利用一个动态数组来求路径和，dp[j]=min(dp[j],dp[j-1])+grid[i][j];
	如果为第一行，dp[j]=dp[j-1]从左边走过来，加上当前grid[i][j]
	如果为第一列，dp[j]=dp[j]从上边走下来，加上当前grid[i][j]
 */
public class Path {

	/*public static int minPathSum(int[][] grid) {
		if(grid.length==0 || grid[0].length==0) {
			return 0;
		}
		int m = grid.length; //行
		int n = grid[0].length; //列
		//定义一个dp数组记录走到每一步的最小路径和
		int[] dp = new int[n];
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(j==0) {
					//第一列，只能从上侧走下来
					dp[j] = dp[j];
				}else if(i==0) {
					//第一行，只能从左侧走过去
					dp[j] = dp[j-1];
				}else {
					//即可从左侧 也可从上侧，取最小路径
					dp[j] = Math.min(dp[j], dp[j-1]);	
				}
				//加上当前这一步的代价
				dp[j] += grid[i][j];
			}
		}
		//返回dp数组的最后一个数
		return dp[n-1];
	}*/
	
	public static int minPathSum(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int n1 = grid.length;
		int n2 = grid[0].length;
		int[] dp = new int[n2];
		for(int i=0;i<n1;i++) {
			for(int j=0;j<n2;j++) {
				if(i==0 && j!=0) {
					//为第一行，只能往右走
					dp[j] = dp[j-1];
				}else if(j==0) {
					//第一列只能往下走
					dp[j] = dp[j];
				}else {
					//其他，可以往下或往右，取最小那个
					dp[j] = Math.min(dp[j], dp[j-1]);
				}
				dp[j] += grid[i][j]; //加上自己这一格的代价
			}
		}
		return dp[n2-1];
	}
	
	public static void main(String[] args) {
		int[][] grid = {{1,3,1},
						{1,5,1},
						{1,2,1}};
		int minPathSum = minPathSum(grid);
		System.out.println(minPathSum);
	}
}
