package com.lsx.algorithm.datastructure.array;

import java.util.Scanner;

//给一个数组，将0都移到后面
public class MoveZero {
	
	public static void moveZeros(int[] num) {
		int n = num.length;
		int index = 0;
		for(int i=0;i<n;i++) {
			if(num[i]!=0) {
				num[index++] = num[i];
			}
		}
		
		while(index < n) {
			num[index++] = 0;
		}
		
		for(int i=0;i<n;i++) {
			System.out.println(num[i]);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			int n = sc.nextInt();
			int[] num = new int[n];
			for(int i=0;i<n;i++) {
				num[i] = sc.nextInt();
			}
			
			moveZeros(num);
			
		}
	}
}
