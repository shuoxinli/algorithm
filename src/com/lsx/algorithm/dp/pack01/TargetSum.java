package com.lsx.algorithm.dp.pack01;
/*
 * 题目归属：动态规划的0-1背包
 * 题目：改变一组数的正负号，使得它们的和为目标数
 * 例子：{1，1，1，1，1} target=3 return 5种方法使得和为3 其中一种-1+1+1+1+1 = 3
 * 思路：将这组数看成两部分，P和N，P使用正号，N使用负号，则
 * 		sum(P) - sum(N) = target
 * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
 * 		2 * sum(P) = target + sum(nums)
 * 因此只要找到一个子集，令它们都取正号，并且和等于 (target + sum(nums))/2，就证明存在解。
 */
public class TargetSum {

	public static int findTargetSumWays(int[] nums,int target) {
		//求这组数的和
		int sum = computeArraySum(nums);
		if(sum < target || (sum+target)%2==1) {
			return 0;
		}
		//背包容量
		int W = (sum+target)/2;
		int[] dp = new int[W+1];
		dp[0]=1;
		for(int num : nums) {
			for(int i=W;i>=num;i--) {
				dp[i] = dp[i]+dp[i-num];
			}
		}
		return dp[W];
	}
	
	private static int computeArraySum(int[] nums) {
		int sum = 0;
		for (int i : nums) {
			sum += i;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		int[] nums = {1,1,1,1,1};
		int target = 3;
		int findTargetSumWays = findTargetSumWays(nums, target);
		System.out.println(findTargetSumWays);
	}
	
	//DFS深度遍历解法
	public int findTargetSum(int[] nums,int S) {
		return findTargetSum(nums,0,S);
	}
	
	private int findTargetSum(int[] nums,int start,int S) {
		if(start == nums.length) {
			return S==0 ? 1:0;
		}
		
		return findTargetSum(nums, start+1, S-nums[start])+
				findTargetSum(nums, start+1, S+nums[start]);
	}
}
