package com.lsx.algorithm.search.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * 题目归属：回溯
 * 题目：给一个数字数组，然后找出它可能出现的所有排列情况。
 * 例子：{1，2，3}
 * 思路：定义一个存放所有可能的排列，一个存放每一种排列出现的元素，不断遍历标记数组，
 * 		如果没有访问到则添加该元素，有访问到则跳过，直到排列元素的个数等于num的个数，
 * 		算为一种排列，回溯到上一层，取消标记，继续遍历。
 * 
 * 补充：若存在重复元素，需要排除一下重复的排列。
 * 思路：先对nums进行排序，然后再添加一个元素时，判断这个元素是否等于前一个元素，如果等于，且前一个元素还未访问，
 * 		那么就跳过这个元素。
 */
public class Permutation {

	public List<List<Integer>> permute(int[] nums){
		//定义一个双重集合存放可能的排列
		List<List<Integer>> permutes = new ArrayList<>();
		//定义一个集合存每一个排列的具体元素
		List<Integer> permuteList = new ArrayList<>();
		//对nums进行排序
		Arrays.sort(nums);
		//标记访问
		boolean[] hasVisited = new boolean[nums.length];
		//回溯调用
		backtracking(permutes,permuteList,hasVisited,nums);
		
		return permutes;
	}
	
	private void backtracking(List<List<Integer>> permutes,
			List<Integer> permuteList,
			boolean[] hasVisited,
			int[] nums) {
		//如果permutelist长度等于nums，添加这组排列
		if(permuteList.size() == nums.length) {
			permutes.add(new ArrayList<>(permuteList)); //每次都要重新构造一个list，因为permutelist还要用于操作
			return;
		}
		
		//遍历访问标记
		for(int i=0;i<hasVisited.length;i++) {
			//判断是否等于前一个元素，且前一个元素未访问过
			if(i!=0 && nums[i] == nums[i-1] && !hasVisited[i-1]) {
				continue;//防止重复
			}
			if(hasVisited[i]) {
				//为true，说明访问过了
				continue;
			}
			//标记访问
			hasVisited[i] = true;
			//添加该元素
			permuteList.add(nums[i]);
			backtracking(permutes, permuteList, hasVisited, nums);
			//回溯到上一层，取消最后一个元素的访问标记
			permuteList.remove(permuteList.size()-1);
			hasVisited[i] = false;
		}
	}
	
	public static void main(String[] args) {
		Permutation per = new Permutation();
		int[] nums = {1,1,3};
		List<List<Integer>> permute = per.permute(nums);
		for(int i=0;i<permute.size();i++) {
			System.out.println(permute.get(i));
		}
	}
}
