package com.lsx.algorithm.backtrack;

import java.util.Scanner;
/*
 * 输入一个二维数组，里面都是0 1
 * 1代表有岛屿，连成一片的1算一个岛屿，算出有多少岛屿。
 */
public class Island {

	public static void goFind(int[][] num,int i,int j,int n,int m) {
		if(i<0 || i>=n || j<0 || j>=m || num[i][j]!=1) {
			return;
		}
		
		num[i][j] = 2;
		goFind(num, i, j-1, n, m);
		goFind(num, i, j+1, n, m);
		goFind(num, i-1, j, n, m);
		goFind(num, i+1, j, n, m);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int[][] num = new int[n][m];
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					num[i][j] = sc.nextInt();
				}
			}
			int count=0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(num[i][j] == 1) {
						count++;
						goFind(num,i,j,n,m);
					}
				}
			}
			System.out.println(count);
		}
	}
}
