package com.lsx.algorithm.greedy;

import java.util.ArrayList;
import java.util.List;

/*
 * 题目归属：贪心算法
 * 题目：给出了一个由小写字母组成的字符串S。我们希望将这个字符串划分为尽可能多的部分，以便每个字母最多出现在一个部分中，
 * 		并返回一个表示这些部分大小的整数列表。注意：每一部分都不能包含其他部分里的字符，还有尽可能分最多份。
 * 例子：Input: S = "ababcbacadefegdehijhklij"
		Output: [9,7,8] 分成三部分为：ababcbaca  defegde  hijhklij
	思路：先统计一下每个字符最后出现的索引值，然后再重新遍历字符串，获得当前字符的最后出现的索引作为最后索引，
		  然后从当前字符开始到最后索引，期间的字符是否有最后出现的索引大于最后索引的，有就更新
		 最后索引，继续遍历，如果没有则把最后索引到当前字符位置的所有字符归为一部分。
 */
public class SortPart {

	public static void main(String[] args) {
		String s = "ababcbacadefegdehijhklij";
		List<Integer> partLength = partString(s);
		System.out.println(partLength);
	}
	
	public static List<Integer> partString(String s){
		//统计每个字符最后出现的索引值
		int[] lastIndexOfChar = new int[26];
		for(int i=0;i<s.length();i++) {
			//存入每个字符最后出现的索引值
			lastIndexOfChar[char2Index(s.charAt(i))] = i;
		}
		//存入每个部分的长度
		List<Integer> partions = new ArrayList<>();
		//每部分的第一个字符的索引值
		int firstIndex = 0;
		//从头遍历字符串
		while(firstIndex < s.length()) {
			//标记每部分的最后字符的索引
			int lastIndex = firstIndex;
			//从每部分的第一个字符遍历到最后索引，不断更新最后索引，到该部分每个字符出现的最后索引都小于lastIndex
			for(int i=firstIndex;i<s.length() && i <= lastIndex;i++) {
				//获取每个字符出现的最后索引
				int index = lastIndexOfChar[char2Index(s.charAt(i))];
				//如果大于该部分的最后索引，则更新
				if(index > lastIndex) {
					lastIndex = index;
				}
			}
			//循环出来，说明该部分的字符已经分割好了,统计该部分的长度
			partions.add(lastIndex-firstIndex+1);
			firstIndex = lastIndex + 1;
		}
		
		return partions;
	}
	
	//获得该字符在26个字母中的位置，代表该字符
	public static int char2Index(char c) {
		return c-'a';
	}
}
