package com.lsx.algorithm.twopointer;

import java.util.Scanner;

/*
 * 题目归属：数组双指针问题
 * 题目：输入两个有序的数组，然后将数组2合并到数组1，合并后的数组仍为有序。
 * 例子：a=[1,4,5,0,0,0]  b=[2,6,7] a足够存放
 * 合并后：a=[1,2,4,5,6,7]
 */
public class MergeTwoArray {
	
	public static void main(String[] args) {
		//输入两个数组
		int a[] = {1,2,7,0,0,0};
		int b[] = {2,4,5};
		mergeTwoSortedArray(a, 3, b, 3);
	}
	
	public static void mergeTwoSortedArray(int[] a,int m, int[] b,int n) {
		//m，n分别为数组a，b中的元素个数
		//定义两个指针分别指向数组a，b的末尾
		int i = m-1;
		int j = n-1;
		//定义一个指针指向a和b合并后，数组a的末尾
		int k = m+n-1;
		while(i>=0 && j>=0) {
			if(a[i] > b[j]) {
				//将a[i]写入a中
				a[k--] = a[i--];
			}else {
				//将b[j]写入a中
				a[k--] = b[j--];
			}
		}
		
		//如果a还有剩余，则全部写入a
		while(i>=0) {
			a[k--] = a[i--];
		}
		//如果b还有剩余，则全部写入a
		while(j>=0) {
			a[k--] = b[j--];
		}
		
		//遍历输出a
		for(i=0;i<=m+n-1;i++) {
			System.out.println(a[i]);
		}
	}
}
