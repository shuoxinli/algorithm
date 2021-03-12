package com.lsx.algorithm.datastructure.array;
/*
 * 合并两个排好序的数组
 */
public class Union {

	//时间和空间复杂度都为n1+n2
	public static int[] union(int[] arr1,int[] arr2) {
		int low1 = 0;
		int high1 = arr1.length-1;
		int low2 = 0;
		int high2 = arr2.length-1;
		int i=0;
		int[] arr3 = new int[arr1.length+arr2.length];
		
		while(low1<=high1 && low2<=high2) {
			if(arr1[low1] < arr2[low2]) {
				arr3[i++] = arr1[low1++];
			}else {
				arr3[i++] = arr2[low2++];
			}
		}
		
		while(low1<=high1) {
			arr3[i++] = arr1[low1++];
		}
		while(low2<=high2) {
			arr3[i++] = arr2[low2++];
		}
		
		return arr3;
	}
	
	public static void main(String[] args) {
		int[] arr1 = {1,4,5,7};
		int[] arr2 = {2,5,8,9};
		int[] arr3 = union(arr1, arr2);
		for(int i : arr3) {
			System.out.print(i+" ");
		}
	}
}
