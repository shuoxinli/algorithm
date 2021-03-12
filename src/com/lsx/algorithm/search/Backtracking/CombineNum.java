package com.lsx.algorithm.search.Backtracking;

import java.util.ArrayList;
import java.util.List;
/*
 * 题目归属：回溯
 * 题目：从 1-9 数字中选出 k 个数不重复的数，使得它们的和为 n。
 * 例子：k=3 n=9 return [[1,2,6], [1,3,5], [2,3,4]]
 * 思路：从1遍历到9，每次i<=n,i都加上，k每次减1，当k和n的剩余空间都刚好为0时，这个组合成立
 * 		k或n任一个为0，不成立
 */
public class CombineNum {

	public List<List<Integer>> combine(int k,int n){
		/*List<List<Integer>> combinations = new ArrayList<>();
		List<Integer> tempCombination = new ArrayList<>();
		backtracking(k,n,1,combinations,tempCombination);
		return combinations;*/
		List<List<Integer>> combinations = new ArrayList<>();
		List<Integer> tempCombination = new ArrayList<>();
		backtracking(k, n, 1, combinations, tempCombination);
		return combinations;
	}
	
	private void backtracking(int k,int n,int start,List<List<Integer>> combinations,
			List<Integer> tempCombination) {
		/*//如果k和n的剩余空间刚好都为0，说明这个组合成立
		if(k == 0 && n==0) {
			combinations.add(new ArrayList<>(tempCombination));
			return;
		}
		//如果只有一方剩余空间为0，说明这个组合不成立
		if(k == 0 || n == 0) {
			return;
		}
		for(int i=start;i<=9;i++) {
			if(i <= n) {
				tempCombination.add(i);
				backtracking(k-1, n-i, i+1, combinations, tempCombination);
				tempCombination.remove(tempCombination.size()-1);
			}
		}*/
		if(k==0 && n==0) {
			combinations.add(new ArrayList<>(tempCombination));
			return;
		}
		if(k==0 || n==0) {
			return;
		}
		for(int i=start;i<=9;i++) {
			tempCombination.add(i);
			backtracking(k-1, n-i, i+1, combinations, tempCombination);
			tempCombination.remove(tempCombination.size()-1);
		}
	}
	
	public static void main(String[] args) {
		int k=3;
		int n=9;
		CombineNum com = new CombineNum();
		List<List<Integer>> combine = com.combine(k, n);
		for(int i=0;i<combine.size();i++) {
			System.out.println(combine.get(i));
		}
	}
}
