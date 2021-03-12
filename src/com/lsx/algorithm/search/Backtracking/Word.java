package com.lsx.algorithm.search.Backtracking;

import javafx.scene.layout.Border;

/*
 * 题目归属：回溯
 * 题目：给一个二维字符数组，然后给一个字符串，判断里面的字符是否都在数组中出现，且是有顺序的。
 * 例子：[['A','B','C','E'],
		['S','F','C','S'],
		['A','D','E','E']] word=ABCCED return true; word=ABCB return false
	思路：遍历每个节点，进行深度搜索，且是有序的，找不到返回上一节点继续寻找。
 */
public class Word {
	
	//定义方向
	private final static int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
	private int m;
	private int n;
	
	public boolean exist(char[][] board,String word) {
		if(word == null || word.length()==0) {
			return true;
		}
		if(board == null || board.length==0) {
			return false;
		}
		
		m = board.length;
		n = board[0].length;
		boolean[][] hasVisited = new boolean[m][n];
		
		for(int r=0;r<m;r++) {
			for(int c=0;c<n;c++) {
				if(backtracking(0,r,c,hasVisited,board,word)) {
					return true;
				}
			}
		}
		//遍历完还找不到
		return false;
	}
	//curlen当前访问到word的第几个字符
	private boolean backtracking(int curlen,int r,int c,boolean[][] visited,char[][] board,String word) {
		if(curlen == word.length()) {
			return true;
		}
		if(r<0 || r>=m || c<0 || c>=n || board[r][c]!=word.charAt(curlen) || visited[r][c] == true) {
			return false;
		}
		//标记访问
		visited[r][c]=true;
		//遍历四个方向
		for(int[] d : direction) {
			if(backtracking(curlen+1, r+d[0], c+d[1], visited, board, word)) {
				return true;
			}
		}
		//返回上一个，标记访问取消
		visited[r][c]=false;
		return false;
	}
	
	public static void main(String[] args) {
		char[][] board = {{'A','B','C','E'},
							{'S','F','C','S'},
							{'A','D','E','E'}};
		Word word = new Word();
		String s = "ABCCES";
		boolean isExist = word.exist(board, s);
		System.out.println(isExist);
	}
}
