package com.lsx.algorithm.greedy;
/*
 * 重点！！
 * 题目：贪心算法，保证每次都是局部最优，到最后也是全局最优。
 * 题目：给定字符串s="abc",t="sfjafkbfkck",判断s是否是t的子序列。
 * 思路：遍历子序列s，判断每个字符都在t中出现，且出现的顺序是从前往后，有序的。
 */
public class Subsequence {

	public static void main(String[] args) {
		String s = "abc";
		String t = "sfacjfbsklk";
		boolean isSubsequence = isSubsequence(s, t);
		System.out.println(isSubsequence);
	}
	
	public static boolean isSubsequence(String s, String t) {
		char[] ch = s.toCharArray();
		int index = -1; //用来指示t的索引位置
		for (char c : ch) {
			//核心代码：string.indexOf(ch,fromIndex) 从fromIndex开始查找是否含有该str
			//如果有返回index，下次从这里的下一个位置开始找，如果查找到最后还没有，返回-1
			index = t.indexOf(c, index+1);
			if(index == -1) {
				return false;
			}
		}
		return true;
	}
}
