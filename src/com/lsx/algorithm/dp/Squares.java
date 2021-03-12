package com.lsx.algorithm.dp;

import java.util.ArrayList;
import java.util.List;

/*
 * 题目归属：动态规划
 * 题目：给定一个数n，例如n=12，return 3 因为12=4+4+4,返回组成这个的平方数的最少个数。
 * 思路：先找出小于n的所有平方数，然后从头开始遍历到n，遍历平方数列表，求出每个数的最少平方数
 * 		后面的数依赖于前面已经求出的数。
 */
public class Squares {
	
	
	public static int numSquares(int n) {
		//获取小于n的所有平方数
		List<Integer> squareList = generateSquareList(n);
		//定义一个dp数组存放每个数的最少的平方数和
		int[] dp = new int[n+1];
		for(int i=1;i<=n;i++) {
			int min = Integer.MAX_VALUE;
			for(int square : squareList) {
				//遍历平方数列表，如果大于当前数i，则跳出
				if(square > i) {
					break;
				}
				//取组合成当前数i的最少平方数
				min = Math.min(min, dp[i-square] + 1);
			}
			dp[i] = min;
		}
		return dp[n];
	}
	
	//生成小于n的所有平方数列表
	public static List<Integer> generateSquareList(int n){
		List<Integer> squareList = new ArrayList<>();
		//这是一条找平方数的规律
		int diff = 3;
		int square = 1;
		while(square <= n) {
			squareList.add(square);
			square += diff;
			diff += 2;
		}
		return squareList;
	}
	
	public static void main(String[] args) {
		int n = 13;
		int numSquares = numSquares(n);
		System.out.println(numSquares);
		
		Integer a = 1;
		Integer b = 1;
		System.out.println(Math.round(11.5));
	}
}
