package com.lsx.algorithm.search.binary;
/*
 * 题目归属：二分查找
 * 题目：给定一个有序数组，其中只有一个数不出现两次，找出这个数。
 * 例子：[1,1,2,2,3,4,4,5,5]  3
 * 解决思路：观察数组发现，在这个单个数字的索引index之前，如果索引值为偶数m，
 * 			且m+1<index，则nums[m]==nums[m+1]。在index之后，即m+1>=index,
 * 			则nums[m]!=nums[m+1]。大前提是确保m为偶数！
 */
public class SingleElement {

	public static void main(String[] args) {
		int[] nums = {1,1,2,2,3,4,4,5,5};
		int index = singleNonDuplicate(nums);
		System.out.println(nums[index]);
	}
	
	//1次
	public static int singleNonDuplicate(int[] nums) {
		int l=0;
		int h=nums.length-1;
		while(l<h) {
			int m = l+(h-l)/2;
			if(m%2 != 0) {
				m--;
			}
			if(nums[m] == nums[m+1]) {
				l = m+2;
			}else {
				h = m;
			}
		}
		return l;
	}
	
	/*public static int singleNonDuplicate(int[] nums) {
		int l=0;
		int h=nums.length-1;
		while(l<h) {
			int m=l+(h-l)/2;
			//前提要确保m为偶数
			if(m%2==1) {
				m--;
			}
			//根据思路，反过来推理
			if(nums[m]==nums[m+1]) {
				//那么index是在右半部分的
				l = m+2; //跳过两个相等的
			}else {
				//否则index在左半部分
				h = m; //index有可能就在m了
			}
		}
		return l;  //l继续跳，最后l指向的就是index
	}*/
}
