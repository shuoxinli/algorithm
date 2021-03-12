package com.lsx.algorithm.search.DFS;
/*
 * 题目归属：深度优先搜索：
 * 			对一个节点进行遍历，然后不断深入找新的节点，直到没有新的节点，返回对另一个节点深入遍历。
 * 			对图来说，能够遍历到的节点都是从初始节点可达的，用来求解可达性问题。
 * 注意：栈：用来保存当前节点信息，当遍历新节点返回时能够继续遍历当前节点，递归栈。
 * 		标记：需要对已经遍历过的节点进行标记。
 * 
 * 题目：给一个二维的矩阵，里面数都是0或者1，判断1相连的个数最多为多少（从四个方向出来）
 * 例子：[{0,0,1,0,0,0,0,1,0,0,0,0,0},
		 {0,0,0,0,0,0,0,1,1,1,0,0,0},
		 {0,1,1,0,1,0,0,0,0,0,0,0,0},
		 {0,1,0,0,1,1,0,0,1,0,1,0,0},
		 {0,1,0,0,1,1,0,0,1,1,1,0,0},
		 {0,0,0,0,0,0,0,0,0,0,1,0,0},
		 {0,0,0,0,0,0,0,1,1,1,0,0,0},
		 {0,0,0,0,0,0,0,1,1,0,0,0,0}]   return 6
		
     思路：使用一个数组标记方向，然后遍历所有节点，每个节点都进行深度搜索，即从上下左右四个方向搜索，
     	找到1相连最多的个数。
     	
   	进一步求不相连的1 有多个块？
   	思路：对每个节点进行深度遍历之前，判断是否为0，不为0说明没遍历过，记为1块。
   		/或者在每次返回area时，判断是否为0，不为0，加一块
 * 
 */
public class Island {

	private int m,n;
	//定义方向
	private int[][] direction= {{0,1},{0,-1},{1,0},{-1,0}};
	
	private int islandsNum=0;
	
	public int maxAreOfIsland(int[][] grid) {
		if(grid == null || grid.length==0) {
			return 0;
		}
		//行数
		m = grid.length;
		//列数
		n = grid[0].length;
		int maxArea = 0;
		//遍历每个节点
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(grid[i][j]!=0) {
					maxArea = Math.max(maxArea, dfs(grid,i,j));//取所搜结果最大的
					islandsNum++;
				}
			}
		}
		return maxArea;
	}
	
	//对每个节点进行深度搜索
	private int dfs(int[][] grid,int row,int col) {
		if(row<0 || row>=m || col<0 || col>=n || grid[row][col]==0) {
			return 0;
		}
		//grid[][]为1的
		grid[row][col]=0;//标记访问过
		int area = 1;
		for (int[] dir : direction) {
			area += dfs(grid,row+dir[0],col+dir[1]);//从四面八方递归访问
		}
		return area;
	}
	
	public static void main(String[] args) {
		int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
		       		 {0,0,0,0,0,0,0,1,1,1,0,0,0},
		    		 {0,1,1,0,1,0,0,0,0,0,0,0,0},
		    		 {0,1,0,0,1,1,0,0,1,0,1,0,0},
		    		 {0,1,0,0,1,1,0,0,1,1,1,0,0},
		    		 {0,0,0,0,0,0,0,0,0,0,1,0,0},
		    		 {0,0,0,0,0,0,0,1,1,1,0,0,0},
		    		 {0,0,0,0,0,0,0,1,1,0,0,0,0}};
		Island island = new Island();
		//获得岛屿的最大面积，即1相连的最多个数
		int maxAreOfIsland = island.maxAreOfIsland(grid);
		System.out.println(maxAreOfIsland);
		//获得岛屿的个数，即不相连的1的块数
		System.out.println(island.islandsNum);
	}
}
