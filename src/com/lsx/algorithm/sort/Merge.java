package com.lsx.algorithm.sort;
/*
 * 归并排序：利用递归来分解数组，利用分治来合并数组。
 * 核心思想：将一个无序的n长数组切成n个有序子序列（只有一个数据默认为有序），
 * 			然后两两合并，再将合并后的n/2个子序列继续进行两两合并，循环。。
 * 因为将两个有序的数组归并到另一个数组中，所以需要开辟额外的空间。workspace
 * 
 * 算法：元素总数为n，需要分log2n层，每一层归并复制后，再与上一层继续归并，复制总数n*log2n
 * 		所以算法复杂度为O(n*log2n),比较次数远小于复制次数，可忽略。
 */
public class Merge {
	
	public static int[] mergeSort(int[] nums,int l,int h) {
		if(l==h) {
			return new int[] {nums[l]}; //分解到一个元素默认有序。
		}
		
		//先递归分解
		int mid = l + (h-l)/2;
		int[] leftArr = mergeSort(nums, l, mid); //对左分组排序
		int[] rightArr = mergeSort(nums, mid+1, h); //对右分组排序
		int[] newNum = new int[leftArr.length + rightArr.length]; //存放归并排好序的数组
		
		//后归并排序
		int m=0,i=0,j=0;
		while(i<leftArr.length && j<rightArr.length) {
			newNum[m++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
		}
		while(i<leftArr.length) {
			newNum[m++] = leftArr[i++];
		}
		while(j<rightArr.length) {
			newNum[m++] = rightArr[j++];
		}
		
		return newNum; //返回归并后的数组
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {9,3,6,2,0,7,4,1};
		int[] newNums = mergeSort(nums, 0, nums.length-1);
		for(int x : newNums) {
			System.out.print(x+" ");
		}
	}
}
