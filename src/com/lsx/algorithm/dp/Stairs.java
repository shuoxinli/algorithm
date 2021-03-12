package com.lsx.algorithm.dp;
/*
 * 题目归属：动态规划：将原问题拆成多个子问题，然后求解，注意，动态规划会保存子问题的解，避免重复计算
 * 题目：爬楼梯，有N阶楼梯，每次可以上一阶或两阶，求多少种上楼的方法
 * 思路：将原问题拆分成子问题：dp[i]表示走到第i阶的方法数：则
 * 	dp[i]=dp[i-1]+dp[i-2] 等于走到第i-1阶再走一阶的方法+第i-2阶再走两阶的方法数
 */
public class Stairs {

	//即斐波那契数列
	public static int climb(int n) {
		if(n<=2) {
			return n;
		}
		int pre1 = 1,pre2 =2;
		for(int i=2;i<=n;i++) {
			int cur = pre1 + pre2;
			pre1 = pre2;
			pre2 = cur;
		}
		return pre1;
	}
	
	public static void main(String[] args) {
		int climb = climb(5);
		System.out.println(climb);
	}
	
}
