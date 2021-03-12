package com.lsx.algorithm.datastructure.array;
/*
 * 重新排列数组，将正数放在索引为偶数的位置，负数放在索引为奇数的位置
 */
public class Alternate {

	//时间复杂度为O(n),空间为1
	static void rearrange(int[] arr) {
		//先利用一次快排，将正负分为前后两个部分，0为基准
		int i=0;
		int j=arr.length-1;
		while(i<j) {
			while(i<j && arr[i]<0)
				i++;
			while(i<j && arr[j]>0)
				j--;
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		
		//交换正负的位置
		int pos=i,neg=0; //定义正负的起始
		while(neg<pos && pos<arr.length-1 && arr[neg]<0) {
			int temp = arr[neg];
			arr[neg] = arr[pos];
			arr[pos] = temp;
			pos++;
			neg+=2;
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {-1, 2, -3, 4, 5, 6, -7, 8, 9};
		rearrange(arr);
		for(int i : arr) {
			System.out.print(i+" ");
		}
	}
}
