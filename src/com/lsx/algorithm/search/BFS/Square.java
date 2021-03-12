package com.lsx.algorithm.search.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 题目归属：广度优先搜索：一层一层遍历，每层遍历都以上一层的遍历结果作为起点，且遍历过的节点不能再次遍历。
 * 实现BFS时需要注意的问题：队列：用来存储每一轮遍历得到的节点。
 * 						   标记：对于遍历过的节点进行标记，防止重复遍历
 * 题目：给一个整数，求组成它的最小平方数的数量
 * 例子:12=4+4+4 输出3 13=4+9 输出2 3=1+1+1 输出3
 * 思路：先求出整数以下的所有可能的平方数，然后把每个整数看成图中的一个节点，如果两个整数之差为一个平方数，
 * 		那这两个整数所在节点就有一条边。求最小的平方数数量，就是求n到0的最短路径，最后减到0说明有最小数。
 * 把它想象成一棵树，根节点为n，平方数作为层节点，一层一层遍历下来，最后最先得到0的那条路径就是最小路径。
 */
public class Square {

	public static void main(String[] args) {
		int numSquares = numSquares(11);
		System.out.println(numSquares);
	}
	public static int numSquares(int n) {
		//先求小于n的所有平方数
		List<Integer> squares = generateSquares(n);
		//队列用来存放每一轮遍历得到的节点
		Queue<Integer> queue = new LinkedList<>();
		//标记：遍历过的节点
		boolean[] marked = new boolean[n+1];
		queue.add(n);//从根节点n开始
		marked[n]=true;
		int level=0; //标记多少个平方数
		while(!queue.isEmpty()) {
			//队列不为空，继续遍历
			int size = queue.size();
			level++;
			while(size-- >0) {
				//队列拿出当前节点
				int cur = queue.poll();
				for(int s : squares) {
					//将平方数与当前节点做差，为0说明找到
					int next = cur-s;
					if(next<0) {
						//失败，退出
						break;
					}
					if(next==0) {
						//成功找到
						return level;
					}
					if(marked[next]) {
						continue; //已遍历过
					}
					//标记添加到队列
					marked[next] = true;
					queue.add(next);
				}
			}
		}
		//最后没找到最小，返回本身,因为1+1+1+1.。。
		return n;
	}
	
	
	//小于n的所有平方数  1,4,9,16 
	public static List<Integer> generateSquares(int n){
		List<Integer> squares = new ArrayList<>();
		int square = 1;
		int diff = 3;
		while(square <= n) {
			squares.add(square);
			square += diff;
			//观察规律，每个平方数相差的数组合起来就是一组奇数
			diff += 2;
		}
		return squares;
	}
}
