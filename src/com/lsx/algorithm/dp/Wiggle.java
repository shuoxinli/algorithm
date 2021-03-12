package com.lsx.algorithm.dp;
/*
 * 题目归属：动态规划
 * 题目：给定一个序列，返回一个摆动序列的最大长度。摆动序列指一大一小一大一小。。。
 * 例子：[1,17,5,10,13,15,10,5,16,8] return 7 [1,17,10,13,10,16,8]
 */
public class Wiggle {

	//要求在O（N）下
	public static int wiggleMaxLength(int[] nums) {
		if(nums == null || nums.length == 0) {
			return 0;
		}
		//以第一个数为开始，up是统计结尾两个数是以升序结束的摆动序列的长度，
		//down是统计结尾两个数是以降序结束的摆动序列的长度。
		int up=1,down=1;
		for(int i=1;i<nums.length;i++) {
			if(nums[i] > nums[i-1]) {
				up = down+1;
			}else if(nums[i] < nums[i-1]) {
				down = up+1;
			}
		}
		return Math.max(up, down);
	}
	
	public static void main(String[] args) {
		int[] nums = {1,17,5,10,13,15,10,5,16,8};
		int wiggleMaxLength = wiggleMaxLength(nums);
		System.out.println(wiggleMaxLength);
	}
}
