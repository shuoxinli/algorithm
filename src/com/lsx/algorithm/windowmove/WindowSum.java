package com.lsx.algorithm.windowmove;

public class WindowSum {

	public static int getMinWindow(int[] arr,int num) {
		int L=0;
		int R=0;
		int sum=0;
		int min=0;
		while(L<arr.length) {
			if(R<arr.length) {
				if(sum<num) {
					sum+=arr[R];
					R++;
				}else {
					//考虑到R不动，L前移仍满足要求
					min = Math.min(min, R-L);
					sum -= arr[L];
					L++;
				}
			}else {
				//R到达右边界就不动了。
				if(sum<num) {
					break;
				}else {
					min = Math.min(min, R-L);
					sum -= arr[L];
					L++;
				}
			}
		}
		return min;
	}
	
	public static void main(String[] args) {
		int[] arr = {1,1,1,1,1};
		int num=8;
		int minWindow = getMinWindow(arr, num);
		System.out.println(minWindow);
	}
}
