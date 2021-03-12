package com.lsx.algorithm.dp;
/*
 * 题目归属：动态规划
 * 题目：给定一个正整数n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。
 * 例子：10 return 3*3*4=36
 * 思路：从2遍历到n，定义一个dp数组，存到每个数的最大乘积和，然后不断遍历每个数计算它的最大乘积和，
 * 		存放起来。
 */
public class SoluNum {

	public static int integerBreak(int n) {
		//定义一个dp数组存放每个数的最大乘积和
		int[] dp = new int[n+1];
		dp[1] = 1; //1默认为1
		//遍历每个数,从2开始
		for(int i=2;i<=n;i++) {
			//再从头遍历每个数的和，计算出最大乘积
			for(int j=1;j<=i-1;j++) {
				//当前数i的最大乘积为dp[i]
				//j*dp[i-j]为与过去的最大乘积相乘，j*(i-j)为现在的乘积，两者取最大
				dp[i] = Math.max(dp[i], Math.max(j*dp[i-j], j*(i-j)));
			}
		}
		return dp[n];
	}
	
	public static void main(String[] args) {
		int n = 10;
		int integerBreak = integerBreak(n);
		System.out.println(integerBreak);
	}
}
