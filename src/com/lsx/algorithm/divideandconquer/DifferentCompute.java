package com.lsx.algorithm.divideandconquer;

import java.util.ArrayList;
import java.util.List;

/*
 * 题目归属：分治算法，分成几个独立的子问题，然后合并成原问题的解
 * 题目：给一个算式，对它进行不同的加括号运算，求出所有可能的结果。
 * 例子：2-1-1 第一种：2-(1-1)=2  第二种: (2-1)-1=0
 * 思路：遍历算式，碰到运算符则进行分治，分为前后两部分进行递归，然后把求解的结果合并为整个算式的解。
 */
public class DifferentCompute {

	public static void main(String[] args) {
		String str = "2-3*2";
		List<Integer> differentWaysOut = differentCompute(str);
		for (Integer integer : differentWaysOut) {
			System.out.println(integer);
		}
	}
	
	//2
	public static List<Integer>	differentCompute(String str){
		if(str == null || str.length()==0) {
			return null;
		}
		List<Integer> wayout = new ArrayList<>();
		for(int i=0;i<str.length();i++) {
			char ch = str.charAt(i);
			if(ch=='-' || ch=='+' || ch=='*') {
				List<Integer> left = differentCompute(str.substring(0,i));
				List<Integer> right = differentCompute(str.substring(i+1));
				for(int l : left) {
					for(int r : right) {
						switch (ch) {
						case '-':
							wayout.add(l-r);
							break;
						case '+':
							wayout.add(l+r);
							break;
						case '*':
							wayout.add(l*r);
							break;
						default:
							break;
						}
					}
				}
			}
		}
		if(wayout.size()==0) {
			wayout.add(Integer.valueOf(str));
		}
		return wayout;
	}
	
	//1
	/*public static List<Integer> differentCompute(String str){
		List<Integer> ways = new ArrayList<>();
		for(int i=0;i<str.length();i++) {
			char ch = str.charAt(i);
			if(ch=='-' || ch=='+'||ch=='*') {
				List<Integer> left = differentCompute(str.substring(0, i));
				List<Integer> right = differentCompute(str.substring(i+1));
				for(int l : left) {
					for(int r : right) {
						switch (ch) {
						case '-':
							ways.add(l-r);
							break;
						case '+':
							ways.add(l+r);
							break;
						case '*':
							ways.add(l*r);
							break;
						default:
							break;
						}
					}
				}
			}
		}
		if(ways.size()==0) {
			ways.add(Integer.parseInt(str));
		}
		return ways;
	}*/
	
	/*//分而治之
	public static List<Integer> differentCompute(String str){
		//存放不同求解的结果
		List<Integer> waysOut = new ArrayList<Integer>();
		//遍历算式
		for(int i=0;i<str.length();i++) {
			int ch = str.charAt(i);
			//如果是运算符号，对运算符的前后两部分进行分开加括号
			if(ch == '-' || ch == '+' || ch == '*') {
				//想象成黑盒，肯定能得到左边和右边部分的不同解结果
				//存放前半部分的求解结果
				List<Integer> leftOut = differentCompute(str.substring(0, i));
				//存放后半部分的求解结果
				List<Integer> rightOut = differentCompute(str.substring(i+1));
				//将结果进行合并
				for (int l : leftOut) {
					for (int r : rightOut) {
						switch(ch) {
						case '+':
							waysOut.add(l+r);
							break;
						case '-':
							waysOut.add(l-r);
							break;
						case '*':
							waysOut.add(l*r);
							break;
						}
					}
				}
			}
		}
		if(waysOut.size()==0) {
			//说明输入的是一个数
			waysOut.add(Integer.parseInt(str));
		}
		return waysOut;
	}*/
}
