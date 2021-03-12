package com.lsx.algorithm.dp;

import java.util.Arrays;

/*
 * 有一个X*Y的网格，小团要在此网格上从左上角到右下角，只能走格点且只能向右或向下走。
 * 请设计一个算法，计算小团有多少种走法。给定两个正整数int x,int y，请返回小团的走法数目。
 */
public class Move {
	
	//利用dp数组来做
	public static int uniquePaths(int x,int y) {
		int[] dp = new int[y];
		Arrays.fill(dp, 1);
		for(int i=1;i<x;i++) {
			for(int j=1;j<y;j++) {
				//注意循环，排除了第一行和第一列，因为都是1
				//列个二维网格，到达每个格子都有两种路径可选
				//向右dp[j-1]为当前行的前一列的路径数或向下dp[j]为前一行的第j列的路径数,以列为观察对象
				dp[j] = dp[j] + dp[j-1];
			}
		}
		return dp[y-1];
	}
	
	//用递归来做
	/*public static int move(int x,int y) {
		if(x==1 || y==1) {
			return 1;
		}else {
			return move(x-1,y)+move(x,y-1);
		}
	}*/
	//1
	public static int move(int x,int y) {
		if(x == 1 || y==1) {
			return 1;
		}else {
			return move(x-1,y)+move(x,y-1);
		}
	}

	public static void main(String[] args) {
		int x=4,y=4;
		int move = move(x, y);
		System.out.println(move);
		
		int uniquePaths = uniquePaths(x, y);
		System.out.println(uniquePaths);
	}
}
