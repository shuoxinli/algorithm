package com.lsx.algorithm.search.Backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * 题目归属：回溯：属于DFS，主要用于求解排列组合问题，执行到特定位置返回后还会继续执行求解过程。有序的。
 * 					而普通的dfs主要用求可达性问题，无序的，求出解立即返回即可。
 * 			注意：1.在访问一个新元素进入新的递归调用时，需要将新元素标记为已经访问，
 * 					这样才能在继续递归调用时不用重复访问该元素；
 * 				 2.但是在递归返回时，需要将元素标记为未访问，因为只需要保证在一个递归链中不同时访问一个元素，
 * 					可以访问已经访问过但是不在当前递归链中的元素。
 * 题目：Given "25525511135",return ["255.255.11.135", "255.255.111.35"].
 * 思路：划分ip地址，分为4部分，每部分组成有1-3个字符，从左到右遍历，一块一块划分
 * 		一块又分为1或2或3个字符的划分，最后得到结果是刚好划分为4块，每块1-3个字符。
 */
public class IPAddress {

	public List<String> restoreIpAddresses(String s){
		//存放可能的ip地址
		List<String> addresses = new ArrayList<>();
		//暂存ip地址
		StringBuilder tempAddress = new StringBuilder();
		doRestore(0,tempAddress,addresses,s);
		return addresses;
	}
	
	private void doRestore(int k,StringBuilder tempAddress,List<String> addresses,String s) {
		//k为块数
		if(k==4 || s.length()==0) {
			if(k==4 && s.length()==0) {
				//刚好划分为4块
				addresses.add(tempAddress.toString());
			}
			return;
		}
		//每块可能划分有1-3个字符，小于s.length是因为可能分到最后一块不够两个字符
		for(int i=0;i<s.length()&&i<=2;i++) {
			if(i!=0 && s.charAt(0)=='0') {
				//剩下的不够i个字符
				break;
			}
			//划分出一块
			String part = s.substring(0, i+1);
			if(Integer.valueOf(part)<=255) {
				//每块都要小于255
				if(tempAddress.length()!=0) {
					//不是第一块，都要加 .
					part = "."+part;
				}
				//标记这一块已访问
				tempAddress.append(part);
				//对剩下的s取第二块
				doRestore(k+1, tempAddress, addresses, s.substring(i+1));
				//返回上一块要取消标记
				tempAddress.delete(tempAddress.length()-part.length(), tempAddress.length());
			}
		}
	}
	public static void main(String[] args) {
		String s = "25525511135";
		IPAddress address = new IPAddress();
		List<String> restoreIpAddresses = address.restoreIpAddresses(s);
		for (String string : restoreIpAddresses) {
			System.out.println(string);
		}
	}
}
