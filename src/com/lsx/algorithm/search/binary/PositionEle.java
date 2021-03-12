package com.lsx.algorithm.search.binary;
/*
 * 题目归属：二分查找
 * 题目：给定一个区间，和一个目标数，查找这个目标数在区间中的起始和终止位置，返回。
 * 例子：[1,1,2,2,3,4,4,5] target=4 返回[5,6]  target=6 返回[-1,-1]
 * 思路：找到目标数在区间的最左位置，和目标数+1在区间的最左位置然后-1就是目标数在区间的最右位置。
 */
public class PositionEle {

	public static void main(String[] args) {
		int[] nums = {1,1,2,2,3,4,4,5};
		int target = 5;
		int[] searchRange = searchRange(nums, target);
		for(int i=0;i<searchRange.length;i++) {
			System.out.println(searchRange[i]);
		}
	}
	
	//1次
	public static int[] searchRange(int[] nums,int target) {
		int first = binarySearch(nums,target);
		int last = binarySearch(nums,target+1)-1;
		if(nums == null || nums.length==0 ||nums[first]!=target) {
    		return new int[] {-1,-1};
    	}else if(nums[nums.length-1] == target) {
            // 1 1 2 2 3 3 4 5 5 这种情况last=7，要加1
    		return new int[] {first,last+1};
    	}else{
            //普通情况，不以target结尾
            return new int[]{first,last};
        }
		
	}
	
	private static int binarySearch(int[] nums,int target) {
		int l = 0;
		int h = nums.length-1;
		while(l<h) {
			int m = l+(h-l)/2;
			if(nums[m] >= target) {
				h = m;
			}else {
				l = m+1;
			}
		}
		return l;
	}
	
	/*public static int[] searchRange(int[] nums,int target) {
		//存放最左位置
		int first = binarySearch(nums,target);
		int last = binarySearch(nums, target+1)-1;
		if(nums[first]!=target) {
			return new int[] {-1,-1};
		}else {
			return new int[] {first,Math.max(first, last)}; //用max取最大，因为如果是5的话，last会比first-1
		}
	}
	
	//二分查找目标数的最左位置
	public static int binarySearch(int[] nums,int target) {
		int l=0,h=nums.length-1;
		while(l<h) {
			int m = l+(h-l)/2;
			if(nums[m] >= target) {
				h = m;
			}else {
				l = m+1;
			}
		}
		return l;
	}*/
}
