package com.lsx.algorithm.twopointer;

import java.util.Scanner;

/*
 * 归属问题：数组前后指针的问题
 * 题目：输入一个有序的数组，给定一个目标数，输出数组中是否有两数之和等于目标数，有则输出两数的索引值。
 * 时间复杂度都为O（n）
 */
public class FindSum {

	public static void main(String[] args) {
		//定义一个有序数组
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i] = sc.nextInt();
		}
		int target = sc.nextInt();
		getTwoSum(arr,target);
	}
	
	public static void getTwoSum(int[] arr,int target) {
		if(arr == null || arr.length < 0 || target >= 2*arr[arr.length-1]) {
			System.out.println("null");
		}
		//定义数组头尾两个指针
		int i = 0;
		int j = arr.length-1;
		//分别从两端开始遍历
		while(i<j) {
			int sum = arr[i]+arr[j];
			if(sum < target) {
				//因为数组是有序的， 所以小于的时候，i+1
				i++;
			}else if(sum > target) {
				//大于的时候，j-1
				j--;
			}else {
				//当相等的时候，返回索引值
				System.out.println("index1="+i+",index2="+j);
				break;
			}
		}
	}
}
