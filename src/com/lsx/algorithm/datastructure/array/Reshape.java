package com.lsx.algorithm.datastructure.array;

import java.util.Scanner;

//将一个n行m列的数组，转为指定行r指定列c的数组。
public class Reshape {
	
	//反转数组
	public static void matrixReshape(int[][] num, int r, int c) {
		int n = num.length, m = num[0].length;
		if(n*m != r*c) {
			System.out.println("转换不了！");
		}
		
		int[][] reNum = new int[r][c];
		int index = 0;
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				reNum[i][j] = num[index/m][index%m];
				index++;
				System.out.print(reNum[i][j]+" ");
			}
			System.out.println();
		}
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
			
			int r = sc.nextInt();
			int c = sc.nextInt();
			
			matrixReshape(num,r,c);
		}
	}
}
