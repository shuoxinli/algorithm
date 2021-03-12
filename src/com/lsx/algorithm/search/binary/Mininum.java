package com.lsx.algorithm.search.binary;
/*
 * 题目归属：二分查找
 * 题目：给一个旋转数组，找出其中最小的那个数。
 * 例子：[3,4,5,0,1,2] 0
 * 思路：找到每个数组中间的数m，如果nums[m]<=h,则最小值在m左边，h=m；
 * 		如果nums[m]>h,则最小值在m右边，l=m+1;
 * 关键要找准跟谁比较，这道题本来是有序的，旋转之后前后部分也是有序的，即前半部分
 * 		都大于最后一个数字。
 */
public class Mininum {

	public static void main(String[] args) {
		int[] nums = {3,4,5,1,2};
		int minNum = findMin(nums);
		System.out.println(minNum);
	}
	
	public static int findMin(int[] nums) {
		int l=0;
		int h=nums.length-1;
		while(l<h) { //h=mid,不能取等于
			int mid = l+(h-l)/2;
			if(nums[mid]<=nums[h]) {
				//最小值在左边
				h = mid;
			}else {
				//最小值在右边
				l = mid+1;
			}
		}
		return nums[l];
	}
}
