package com.lsx.algorithm.dp.pack01;
/*
 * 题目归属：动态规划的0-1背包问题
 * 题目：将一个数组划分为和相等的两部分，可以返回true，不可以返回false。
 * 例子：{1,5,11,5} return true {1,5,5}和{11}
 * 思路：可以看成一个背包大小为sum/2的0-1背包问题。
 */
public class Partition {

	public static boolean canPartition(int[] nums) {
		//取得数组和的一半
		int sum = computeArraySum(nums);
		//如果为奇数，说明不可能分为两部分
		if(sum%2 ==1) {
			return false;
		}
		//为偶数，背包容量即为sum/2
		int W = sum/2;
		//定义一个dp数组存储结果
		boolean[] dp = new boolean[W+1];
		dp[0] = true;
		for (int num : nums) {
			// 从后往前，先计算 dp[i] 再计算 dp[i-num]
			for(int i=W;i>=num;i--) {
				dp[i] = dp[i] || dp[i-num];
				System.out.println(dp[i]);
			}
			System.out.println("-----------");
		}
		return dp[W];
	}
	
	//算出数组和的一半
	private static int computeArraySum(int[] nums) {
		int sum = 0;
		for (int i : nums) {
			sum += i;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		int[] nums = {1,5,11,5};
		boolean canPartition = canPartition(nums);
		System.out.println(canPartition);
	}
}
