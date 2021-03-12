package com.lsx.algorithm.findkthelement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 题目归属：桶排序
 * 题目：给定一个字符串，然后按字符出现的次数从多到少排序重新输出字符串。
 * 例子：tree  输出：eert
 */
public class CharFrequency {
	public static void main(String[] args) {
		String str = "aabbbdxkkkk";
		String frequencySort = frequencySort(str);
		System.out.println(frequencySort);
	}
	
	public static String frequencySort(String str) {
		//定义一个hashmap来统计每个字符出现的次数
		Map<Character,Integer> frequencyForNum = new HashMap<>();
		//遍历该字符串
		for(char ch : str.toCharArray()) {
			frequencyForNum.put(ch, frequencyForNum.getOrDefault(ch, 0)+1);
		}
		
		//定义一个桶来装出现相同次数的字符,是一个列表数组
		List<Character>[] buckets = new ArrayList[str.length()+1];
		for(char c : frequencyForNum.keySet()) {
			int frequencyNum = frequencyForNum.get(c);//取得每个字符出现次数
			if(buckets[frequencyNum] == null) {
				//该频率下还没有数，创建一个列表来存该频率下的数
				buckets[frequencyNum] = new ArrayList<>();
			}
			buckets[frequencyNum].add(c);
		}
		
		//定义个StringBuilder来存重新排序后的字符
		StringBuilder sb = new StringBuilder();
		for(int i = buckets.length-1; i>0; i--) {
			if(buckets[i] == null) {
				continue;
			}
			//遍历相同次数下的字符
			for(char c : buckets[i]) {
				for(int j=0;j<i;j++) {
					sb.append(c);  //出现多少次就要添加多少次
				}
			}
		}
		
		return sb.toString();
	}
}
