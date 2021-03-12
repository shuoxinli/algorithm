package com.lsx.algorithm.search.binary;
/*
 * 题目归属：二分查找
 * 题目：给定一个数字，求它开方后的数。
 * 例子：4 - 2  8 -2
 */
public class SqrtNum {

	public static void main(String[] args) {
		int sqrtNum = sqrtNum(25);
		System.out.println(sqrtNum);
	}
	
	public static int sqrtNum(int num) {
		if(num<=1) {
			return num;
		}
		int l=1;
		int h=num;
		while(l<=h) {
			int mid = (h-l)/2+l;
			int sqrt = num/mid; //避免移除整数范围
			if(mid == sqrt) {
				return mid;
			}else if(mid > sqrt) {
				h = mid-1;
			}else {
				l = mid+1;
			}
		}
		return l-1;
	}
}
