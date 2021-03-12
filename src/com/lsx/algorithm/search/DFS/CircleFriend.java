package com.lsx.algorithm.search.DFS;
/*
 * 题目归属：深度搜索
 * 题目：好友关系可以看成是一个无向图，例如第 0 个人与第 1 个人是好友，那么 M[0][1] 和 M[1][0] 的值都为 1。
 * 例子：[[1,1,0],
		 [1,1,0],
		 [0,0,1]]  return 2 还是一样发现连通的圈数
		 不同与有向图，这里第i行第i列代表第i人，如果与第i行第j列为朋友，即为1，那么第j行第i列就也为1，表明i和j是朋友
 */
public class CircleFriend {

	private int n;
	
	public int findCircleNum(int[][] M) {
		n=M.length;
		int circleNum=0;
		boolean[] hasVisited = new boolean[n];
		//第i行代表第i个人的朋友圈
		for(int i=0;i<n;i++) {
			if(!hasVisited[i]) {
				//没有被访问过
				dfs(M,i,hasVisited);
				circleNum++;
			}
		}
		return circleNum;
	}
	
	private void dfs(int[][] M,int i,boolean[] hasVisited) {
		//被访问过，即使是被动访问的，也就是在访问第i人的朋友圈时，访问到第j人，那么j也为true
		hasVisited[i]=true;
		for(int k=0;k<n;k++) {  //从第i行左到右遍历她的朋友
			if(M[i][k]==1&&!hasVisited[k]) {
				//如果有[i][k]等于1，说明k是i朋友,去到第k行，遍历令[k][i]为true
				dfs(M,k,hasVisited);
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] M = {{1,0,1},
		             {0,1,0},
		             {1,0,1}};
		CircleFriend cif = new CircleFriend();
		System.out.println(cif.findCircleNum(M));
	}
}
