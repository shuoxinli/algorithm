package com.lsx.algorithm.search.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * 典型的回溯算法
 * 题目：给一个字符串，一个字符集，分割字符串，使分割出来的子串都在字符集里面找得到，最后组合成一个句子。
 * 例子：String s = "catsanddog";
		String[] dict = {"cat","cats","and","sand","dog"};
		return [cat, sand, dog]，[cats, and, dog]
 */
public class Sentence {

	public static List<List<String>> Search(String s,String[] dict){
		//定义一个存储所有可能组合的列表
		List<List<String>> compinations = new ArrayList<>();
		//定义一个存放组合的所有可能的元素
		List<String> tempCompination= new ArrayList<>();
		//将字符数组转化为list，便于查询里面是否包含某字符串
		List<String> dictlist = new ArrayList<String>(Arrays.asList(dict));
		backtracking(s,dictlist,compinations,tempCompination);
		return compinations;
	}
	
	public static void backtracking(String s,List<String> dictlist,
			List<List<String>> compinations,List<String> tempCompination) {
		//当s的剩余空间刚好为0时，这个组合成立
		if(s.length()==0) {
			compinations.add(new ArrayList<>(tempCompination));
			return;
		}
		//每次都从s的剩余空间开始遍历
		for(int i=0;i<s.length();i++) {
			//判断字符集是否含有该子串，有才添加到集合
			if(dictlist.contains(s.substring(0, i+1))) {
				tempCompination.add(s.substring(0, i+1));
				backtracking(s.substring(i+1), dictlist, compinations, tempCompination);
				//回溯到上一步
				tempCompination.remove(tempCompination.size()-1);
			}
		}
	}
	
	public static void main(String[] args) {
		String s = "catsanddog";
		String[] dict = {"cat","cats","and","sand","dog"};
		List<List<String>> search = Search(s, dict);
		for(int i=0;i<search.size();i++) {
			System.out.println(search.get(i));
		}
	}
}
