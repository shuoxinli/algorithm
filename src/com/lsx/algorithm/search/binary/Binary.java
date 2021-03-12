package com.lsx.algorithm.search.binary;
/*
 * 二分查找：也称为折半查找，前提是数组有序，时间复杂度是O(logN)
 */
public class Binary {
	//正常的二分查找
	public int binarySearch(int[] nums,int key) {
		int l=0;
		int h=nums.length-1;
		while(l<=h) {
			int mid = l+(h-l)/2; //这样取中间值不会溢出整数范围，如果是(l+h)/2，有可能会溢出
			if(nums[mid] == key) {
				return mid;
			}else if(nums[mid] > key) {
				h = mid-1;
			}else {
				l = mid+1;
			}
		}
		//如果到这里，说明找不到key
		return -1;
	}
	
	//如果有重复元素key，想要查找key的最左位置的索引
	public int binarySearchLeft(int[] nums,int key) {
		int l=0;
		int h=nums.length-1;
		while(l<h) { //不能等于，因为h=mid，没有+1操作，不然可能死循环
			int mid = (h-l)/2+l;
			if(nums[mid] >= key) {
				h = mid; //mid也有可能是key
			}else {
				l = mid+1;
			}
		}
		//最后l指向的位置就是key,也可能不是key
		return l;
	}
}
