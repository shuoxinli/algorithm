package com.lsx.algorithm.greedy;

import java.util.Arrays;

/*
 * 题目归属：贪心算法
 * 题目：每个孩子都有一个满足度，每个饼干都有一个大小，只有饼干的大小大于等于一个孩子的满足度，
 * 该孩子才会获得满足。求解最多可以获得满足的孩子数量。
 * 例子：两个小孩 [1,2]  三块饼干[1,2,3] 能满足的孩子数量为2个。
 * 思路：使用贪心策略，从当前满足度最小的孩子开始，给她能满足她的最小尺寸的饼干，这样剩下大尺寸的饼干就能
 * 		留到后面给满足度更大的孩子了。每次操作都是局部最优的，并且最后得到的结果也是全局最优的，则贪心
 * 		算法就是最优的了。
 */
public class Cookie {

	public static void main(String[] args) {
		int[] kids = {1,2};
		int[] cookies = {1,2,3};
		int kidCount = assignCookies(kids, cookies);
		System.out.println(kidCount);
	}
	
	//分配饼干
	public static int assignCookies(int[] kids,int[] cookies) {
		//先排好序
		Arrays.sort(kids);
		Arrays.sort(cookies);
		int i=0;
		int j=0;
		//将饼干和孩子从小开始遍历，出现满足于孩子的则分给她。
		while(i<kids.length && j<cookies.length) {
			if(kids[i]<=cookies[j]) {
				i++;
			}
			j++;
		}
		return i;
	}
}
