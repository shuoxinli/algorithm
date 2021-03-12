package com.lsx.algorithm.search.DFS;
/*
 * 题目归属：深度搜索
 * 题目：将被X包围的O转换为X，边界的O不转换
 * 例子：      X X X X         return X X X X
			X O O X				   X X X X
			X X O X				   X X X X
			X O X X				   X O X X
			
	思路：从四个边界出发，遍历，如果有O，则转换为T，剩下的O就都是内边界被包围的了，
		  在遍历所有节点，将O的转为X，将T的转为O，即可。
 */
public class Regions {

	//定义方向数组
	private int[][] direction = {{0,1},{0,-1},{1,0},{1,-1}};
	private int m,n; //行和列
	
	public void solve(char[][] grid) {
		if(grid == null || grid.length == 0) {
			return;
		}
		
		m = grid.length;
		n = grid[0].length;
		
		//先遍历行边界，即每行的首尾
		for(int i=0;i<m;i++) {
			dfs(grid,i,0); //首
			dfs(grid,i,n-1); //尾
		}
		//再遍历列边界，即每列的头尾
		for(int j=0;j<n;j++) {
			dfs(grid,0,j);
			dfs(grid,m-1,j);
		}
		
		//最后遍历所有节点，将O转为X，将T转为O
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(grid[i][j]=='O') {
					grid[i][j]='X';
				}
				if(grid[i][j]=='T') {
					grid[i][j]='O';
				}
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	private void dfs(char[][] grid,int r,int c) {
		if(r>=m || r<0 || c>=n || c<0 || grid[r][c]!='O') {
			return;
		}
		//将O标记为T
		grid[r][c] = 'T';
		//向所有方向遍历,将所有与边界O相连的O都变为T
		for(int[] d : direction) {
			dfs(grid,r+d[0],c+d[1]);
		}
	}
	
	public static void main(String[] args) {
		char[][] grid = {{'X','X','X','X'},
						{'X','O','O','X'},
						{'X','X','O','X'},
						{'X','O','X','X'}};
		Regions region = new Regions();
		region.solve(grid);
	}
}
