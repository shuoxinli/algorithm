package com.lsx.algorithm.twopointer;

import java.util.Scanner;

/*
 * 归属问题：数组前后指针的问题
 * 输入一个数：判断这个数是否可以由两个数的平分和组成。
 * 例如：5=1*1+2*2
 * 时间复杂度都为O（n）
 */
public class SquareSum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		judgeSquareSum(n);
	}
	
	public static void judgeSquareSum(int n) {
		if(n <= 0) {
			System.out.println(false);
		}else {
			// 指定前后两个指针
			int i = 0;
			int j = (int) Math.sqrt(n); // 如果分析一下，就知道这两个数不可能大于n开方后的数
			while (i < j) {
				int powSum = i * i + j * j;
				if (powSum == n) {
					System.err.println(true);
					break;
				} else if (powSum < n) {
					i++;
				} else {
					j--;
				}
			}
			// 如果i=j，说明n没法分成两数的平方和。
			if (i == j) {
				System.out.println(false);
			}
		}
		
	}
}
