package com.lsx.algorithm.datastructure.array;


import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			int n = sc.nextInt();
			int[] arr = new int[n];
			for(int i=0;i<n;i++) {
				arr[i] = sc.nextInt();
			}
			
			int sum=0;
			int l=0,r=n-1;
			int lsum=arr[l];
			int rsum=arr[r];
			while(l<r) {
				if(lsum == rsum) {
					sum = Math.max(sum, lsum);
					lsum+=arr[++l];
				}else if(lsum >rsum) {
					rsum+=arr[--r];
				}else {
					lsum+=arr[++l];
				}
			}
			System.out.println(sum);
		}
	}
}
