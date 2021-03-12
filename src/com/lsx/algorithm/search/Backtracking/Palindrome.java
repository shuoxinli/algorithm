package com.lsx.algorithm.search.Backtracking;

import java.util.ArrayList;
import java.util.List;
/*
 * 题目归属：回溯
 * 题目：给定一个字符串，找出里面可以组成回文串的所有组合
 * 例子：s=aab  return [aa,b],[a,a,b]
 * 思路：将s当成一个有限的空间，遍历s，不断地分割它，分割后的子串为回文串，则把当前元素添加到temp表，
 * 		直到s的剩余空间为0，则当前组合成立，加入partitions表。
 */
public class Palindrome {

	public List<List<String>> partition(String s){
		List<List<String>> partitions = new ArrayList<>();
		List<String> tempPartition = new ArrayList<>();
		backtracking(s,partitions,tempPartition);
		return partitions;
	}
	
	private void backtracking(String s,List<List<String>> partitions,List<String> tempPartition) {
		if(s.length()==0) {
			partitions.add(new ArrayList<>(tempPartition));
			return;
		}
		
		for(int i=0;i<s.length();i++) {
			if(isPalindrome(s,0,i)) {
				//是回文串添加，分割后的子串
				tempPartition.add(s.substring(0, i+1));
				//继续遍历s的剩余空间
				backtracking(s.substring(i+1), partitions, tempPartition);
				tempPartition.remove(tempPartition.size()-1);
			}
		}
	}
	
	//判断是否是回文串
	private boolean isPalindrome(String s,int start,int end) {
		while(start < end) {
			if(s.charAt(start++) != s.charAt(end--)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		String s = "aab";
		Palindrome pal = new Palindrome();
		List<List<String>> partition = pal.partition(s);
		for(int i=0;i<partition.size();i++) {
			System.out.println(partition.get(i));
		}
	}
}
