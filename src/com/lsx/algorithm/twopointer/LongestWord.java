package com.lsx.algorithm.twopointer;

import java.util.ArrayList;
import java.util.List;

/*
 * 归属：双指针问题
 * 题目：删除s中的一些字符，使他能构成字符列表d中的一个字符串，找出能构成的最长字符串，如果有相同，取第一个最长的。
 * 例子：s = "abpcplea"  d = ["ale","apple","monkey","plea"]
 * 输出：apple
 * 解决思路：遍历d列表，看每个字符串是否是s中的子序列，是的话统计其字符串长度取最大。
 */
public class LongestWord {

	public static void main(String[] args) {
		String s = "abpcplea";
		List<String> d = new ArrayList<String>();
		d.add("ale");
		d.add("apple");
		d.add("monkey");
		d.add("acplea");
		String longestWords = findLongestWords(s, d);
		System.out.println(longestWords);
		
	}
	
	public static String findLongestWords(String s, List<String> d) {
		//定义一个字符串存最长的那个
		String longestWords = "";
		//遍历列表d
		for (String string : d) {
			//当前最长字符串的长度
			int l1 = longestWords.length();
			//当前要比较的字符串长度
			int l2 = string.length();
			//如果长度小于最长，或等于，都不用进行检查是否是s的子序列
			if(l1 > l2 || (l1 == l2 && !longestWords.equals(string))) {
				continue;
			}
			
			//否则，检查当前字符串是否是s的子序列
			if(isSubstr(s,string)) {
				longestWords = string;
			}
		}
		return longestWords;
	}
	
	//可以用双指针来检查string是否是s的子序列
	public static boolean isSubstr(String s , String string) {
		//定义双指针
		int i = 0;
		int j = 0;
		while(i<s.length() && j<string.length()) {
			if(s.charAt(i) == string.charAt(j)) {
				j++;
			}
			i++;
		}
		//如果j=string.length,说明string是s的子序列
		return j == string.length();
	}
}
