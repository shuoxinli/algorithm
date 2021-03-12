package com.lsx.algorithm.search.Backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * 题目归属：回溯
 * 题目：给出一个数n和k，列出所有1-n的k个数的组合。
 * 例子：n=3 k=2  return [1,2] [1,2] [2,3]
 * 思路：遍历树+减枝，树的深度为k，抛弃那些深度不够k的树
 */
public class Combination {

	
	public List<List<Integer>> combine(int n,int k){
		//定义存放所有可能的组合
		/*List<List<Integer>> combinations = new ArrayList<>();
		//定义存放每一种组合的元素
		List<Integer> combineList = new ArrayList<>();
		//回溯，从1开始到n，剩余空间为k
		backtracking(combineList,combinations,1,k,n);
		return combinations;*/
		List<List<Integer>> combinations = new ArrayList<>();
		List<Integer> combineList = new ArrayList<>();
		backtracking(combineList, combinations, 1, k, n);
		return combinations;
	}

	//回溯,start为每次从哪个元素开始遍历，k为每个组合剩余的空间，n为界限
	private void backtracking(List<Integer> combineList, List<List<Integer>> combinations,int start,int k,int n) {
		/*if(k==0) {
			//剩余空间为0，说明这个组合已满，加入combinations
			combinations.add(new ArrayList<>(combineList));
			return;
		}
		//每次从start开始遍历, n-k+1是start达到这个数之后，就不用再遍历了，因为不够组合了，直接砍掉
		//举个例子：n=5，k=3，5-3+1=3  也就是只需遍历到3，如果从4开始遍历，只剩下5，两个元素不够组成k个
		for(int i=start;i<=n-k+1;i++) {
			//这个循环有包含减枝：如 k=3 n=4，那么start=3之后的就不用遍历了，因为只剩3，4两个元素，组成不了3个
			combineList.add(i);
			backtracking(combineList, combinations, i+1, k-1, n);
			//回溯，移除掉最后一个
			combineList.remove(combineList.size()-1);
		}*/
		
		if(k==0) {
			combinations.add(new ArrayList<>(combineList));
			return;
		}
		for(int i=start;i<=n-k+1;i++) {
			combineList.add(i);
			backtracking(combineList, combinations, i+1, k-1, n);
			//回溯回来要移除最后一个
			combineList.remove(combineList.size()-1);
		}
	}
	
	public static void main(String[] args) {
		int n = 4;
		int k = 2;
		Combination combination = new Combination();
		List<List<Integer>> combine = combination.combine(n, k);
		for(int i=0;i<combine.size();i++) {
			System.out.println(combine.get(i));
		}
	}
}
