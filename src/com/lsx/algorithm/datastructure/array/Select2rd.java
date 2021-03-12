package com.lsx.algorithm.datastructure.array;
/*
 * 查找给定数组中，第二小的元素
 */
public class Select2rd {

	public static void select2rd(int[] arr) {
		
		if(arr.length<2) {
			System.out.println("Error");
			return;
		}
		
		int min1st = Integer.MAX_VALUE;  //这里不能取第一个元素，防止重复元素出现
		int min2rd = Integer.MAX_VALUE;
		
		for(int i=0;i<arr.length;i++) {
			if(min1st > arr[i]) {
				//当找到比当前元素还小，当前元素自动退化为第二小
				min2rd = min1st;
				min1st = arr[i];
			}else if(min2rd > arr[i] && arr[i]!=min1st) {  //arr[i]!=min1st 防止出现重复元素
				min2rd = arr[i];
			}
		}
		
		if(min2rd == Integer.MAX_VALUE) {
			//当出现全为1的情况，第二小元素不存在
			System.out.println("No exist");
			return;
		}else {
			System.out.println(min2rd);
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {1,1,1};
		select2rd(arr);
	}
}
