package com.lsx.algorithm.greedy;

import java.util.List;

/*
 * 题目归属：贪心算法
 * 题目：给定一个数组，计算这个数组的最大和的一个子数组。
 * 例子：[2,4,-3,5,-2,1]  最大的子数组为[2,4,-3,5]
 * 思路：从头开始遍历数组，判断前面计算的和是否大于0，大于0，加上当前这个元素，然后与最大值比较，替换
 * 如果小于0，从当前这个数重新开始，继续向后面遍历。
 */

public class MaxSubarray {

	public static void main(String[] args) {
		int[] nums = {2,4,-3,5,-2,3};
		int maxSum = getMaxSubarray(nums);
		System.out.println(maxSum);
	}
	
	public static int getMaxSubarray(int[] nums) {
		//存最大的和
		int maxSum = nums[0];
		//存当前的和
		int curSum = nums[0];
		//从头开始遍历数组
		for (int i=1;i<nums.length;i++) {
			//当前和如果大于0，则加上当前值，小于0，则从当前值重新开始
			curSum = curSum > 0 ? curSum+nums[i]:nums[i];
			//不断更新最大和
			maxSum = Math.max(maxSum, curSum);
		}
		return maxSum;
	}
}
