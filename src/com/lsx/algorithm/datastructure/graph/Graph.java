package com.lsx.algorithm.datastructure.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 图的深度遍历和广度遍历
 */
public class Graph {

	//图的存储结构
	private int V; //图的节点个数
	private LinkedList<Integer> adj[]; //数组存节点，链表存与该节点相关联的所有节点
	
	Graph(int v){
		V = v;
		adj = new LinkedList[v];
		for(int i=0;i<v;i++) {
			//每个节点数组都有一个链表
			adj[i] = new LinkedList();
		}
	}
	
	void addEdge(int v,int w) {
		adj[v].add(w); //v节点与w节点相关联
	}
	
	//从某个节点开始进行广度搜索图
	void BFS(int s) {
		
		//定一个数组，标记访问过
		boolean[] visited = new boolean[V];
		
		//利用一个队列来存储每个要输出的节点
		Queue<Integer> queue = new LinkedList<>();
		//从节点s开始
		visited[s] = true;
		queue.add(s);
		//遍历整个队列
		while(queue.size()!=0) {
			
			s = queue.poll();
			System.out.println(s+" ");
			
			//利用迭代器遍历s节点的链表
			Iterator<Integer> it = adj[s].iterator();
			while(it.hasNext()) {
				int n = it.next();
				//判断是否访问过
				if(!visited[n]) {
					visited[n] = true;
					queue.add(n); //进队
				}
			}
		}
	}
	
	//深度优先遍历
	void DFS(int s) {
		boolean[] visited = new boolean[V];
		DFSUtil(s, visited);
	}
	//分开的原因是每次递归都要有visited数组，要共享
	void DFSUtil(int s,boolean[] visited) {
		
		visited[s] = true;
		System.out.println(s+" ");
		
		//迭代遍历完s相关
		Iterator<Integer> it = adj[s].iterator();
		while(it.hasNext()) {
			int n = it.next();
			if(!visited[n]) {
				//递归调用
				DFSUtil(n,visited);
			}
		}
	}
	
	
	public static void main(String[] args) {
		Graph g = new Graph(4); 
		  
        g.addEdge(0, 1); 
        g.addEdge(0, 2); 
        g.addEdge(1, 2); 
        g.addEdge(2, 0); 
        g.addEdge(2, 3); 
        g.addEdge(3, 3); 
  
        System.out.println("从图的某个节点开始广度优先遍历："); 
        g.BFS(2); 
        System.out.println("从图的某个节点开始深度优先遍历："); 
        g.DFS(2);
	}
}
