package com.lsx.algorithm.search.Backtracking;

import java.util.ArrayList;
import java.util.List;
/*
 * 题目归属：回溯
 * 题目：给一个集合，一个目标数，遍历集合，找出所有元素和为目标数的组合，元素可重复。
 * 例子：[2, 3, 6, 7] and target 7 return[[7],[2, 2, 3]]
 * 思路：就是从0开始遍历集合，每次每个元素都可以重复，比target小则加上，剩余空间为target-当前元素。
 * 		最后剩余空间target等于0，说明这个组合是成立的。
 * 
 * 补充：如果每个元素不可重复使用，则加入hasVisited数组标记是否已访问过。
 * 		含有相同元素，先进行sort，再进入回溯。
 */
public class CombineSum {

	public List<List<Integer>> combinationSum(int[] candidates,int target){
		//定义一个存放所有组合的结果
		List<List<Integer>> combinations = new ArrayList<>();
		//定义一个存放每种组合
		List<Integer> tempCombination = new ArrayList<>();
		backtracking(tempCombination,combinations,0,target,candidates);
		return combinations;
	}
	
	private void backtracking(List<Integer> tempCombination,List<List<Integer>> combinations, 
			int start,int target,int[] candidates) {
		//判断target是否为0，是表示组合成功
		if(target == 0) {
			combinations.add(new ArrayList<>(tempCombination));
			return;
		}
		//从start开始遍历
		for(int i=start;i<candidates.length;i++) {
			if(candidates[i] <= target) {
				//小于target都加上
				tempCombination.add(candidates[i]);
				//回溯，start=i说明每个元素都可以重复多次，target-当前元素，剩余空间继续填
				backtracking(tempCombination, combinations, i, target-candidates[i], candidates);
				//返回上一层，删除最后一个元素
				tempCombination.remove(tempCombination.size()-1);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] candidates = {2,3,6,7};
		int target = 8;
		CombineSum combineSum = new CombineSum();
		List<List<Integer>> combinationSum = combineSum.combinationSum(candidates, target);
		for(int i=0;i<combinationSum.size();i++) {
			System.out.println(combinationSum.get(i));
		}
	}
}
