package com.lsx.algorithm.dp;
/*
 * 题目归属：动态规划
 * 题目：抢劫一排住户，但是不能抢邻近的住户，求最大抢劫量。
 * 思路：数组nums来表示每户的抢劫量，dp数组来存储最大的抢劫量，dp[i]表示抢劫到第i户时的最大抢劫量
 * 		由于不能抢邻近住户，所以抢了第i-1家，就不能抢第i户
 * 		dp[i]=max(dp[i-2]+nums[i],dp[i-1])
 */
public class Robber {

	//在一排街区抢
	public static int rob(int[] nums) {
		int pre1=0,pre2=0;
		for(int i=0;i<nums.length;i++) {
			int cur = Math.max(pre1+nums[i], pre2);
			pre1 = pre2;
			pre2 = cur;
		}
		return pre2;
	}
	
	//在环形街区抢
	public static int robCircle(int[] nums) {
		if(nums == null || nums.length==0) {
			return 0;
		}
		int n=nums.length;
		if(n==1) {
			return nums[0];
		}
		//环形分为两段0到n-2，1到n-1
		return Math.max(robs(nums,0,n-2), robs(nums,1,n-1));
	}

	public static int robs(int[] nums,int start,int end) {
		int pre1=0,pre2=0;
		for(int i=start;i<=end;i++) {
			int cur = Math.max(pre1+nums[i], pre2);
			pre1 = pre2;
			pre2 = cur;
		}
		return pre2;
	}
	
	
	public static void main(String[] args) {
		int[] nums = {1,2,3,4,1,3};
		int rob = rob(nums);
		System.out.println(rob);
		
		//环形街区抢
		int robCircle = robCircle(nums);
		System.out.println(robCircle);
	}
	
	
}
