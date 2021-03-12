package com.lsx.algorithm.greedy;
/*
 * 题目归属：贪心算法
 * 题目；给定一个数组，判断是否能只修改一个数使得数组成为非递减数组。(递增或相等）
 * 思路：遍历数组，第一种：遇到nums[i-1]>nums[i]时，优先考虑nums[i-1]=nums[i],而不是nums[i]=nums[i-1],
 * 		因为这样nums[i]可能会影响后面的数组。
 * 		第二种为第一种的特殊情况：遇到nums[i-2]>nums[i]时，前提是num[i]<nums[i-1],但nums[i-1]>nums[i-2],例如 6 7 5，
 * 				此时要只修改一次，只能修改nums[i]=nums[i-1],即5改为7，才能非递减。
 * 分着两种情况，如果是 6 7 7 5 nums[i]<nums[i-3] 可通过第二种情况解决。
 * 例子：[6,7,5] 修改一次 [6,7,7]
 */
public class Nodecarray {
	public static void main(String[] args) {
		int[] nums = {6,5,5,8};
		boolean isCheckOne = checkPossibility(nums);
		System.out.println(isCheckOne);
	}
	
	public static boolean checkPossibility(int[] nums) {
		int count=0;
		for(int i=1;i<nums.length;i++) {
			if(nums[i] >= nums[i-1]) {
				//递增或相等，直接跳过
				continue;
			}
			//否则交换数+1
			count++;
			if(i-2>=0 && nums[i-2] > nums[i]) {
				//先从第二种特殊情况入手
				nums[i] = nums[i-1];
			}else {
				//第一种的普通情况
				nums[i-1] = nums[i];
			}
		}
		return count<=1;
	}
}
