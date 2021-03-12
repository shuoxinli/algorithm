package com.lsx.algorithm.datastructure.array;

import java.util.HashMap;
import java.util.Map;

/*
 * 找到第一个没有重复的数
 */
public class Norepeat {

	//暴力解法，算法复杂度为n*n
	public static void findNoRepeat(int[] arr) {
		int n = arr.length;
		for(int i=0;i<n;i++) {
			boolean flag = false;
			for(int j=0;j<n;j++) {  //这里要从头开始，漏掉前面的数
				if(i!=j) {   //跳过下标相同的	
					if (arr[j] == arr[i]) {
						flag = true;
						break;
					}
				}
			}
			if(flag == false) {
				System.out.println(arr[i]);
				return;
			}
		}
	}
	
	//利用hashmap，时间和空间复杂度为n
	public static void findNoRepeatByMap(int[] arr) {
		//定义一个hashmap
		Map<Integer,Integer> map = new HashMap<>();
		
		//遍历数组添加到map中
		for(int i=0;i<arr.length;i++) {
			//先判断map里面是否有该元素了
			if(map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i])+1);
			}else {
				map.put(arr[i], 1);
			}
		}
		//遍历map，找出次数=1的
		for(int i=0;i<arr.length;i++) {
			if(map.get(arr[i]) == 1) {
				System.out.println(arr[i]);
				return;
			}
		}
		
		//也可以这样遍历
		for(Map.Entry<Integer, Integer> x : map.entrySet()) {
			if(x.getValue()==1) {
				System.out.println(x.getKey());
				return;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {-1,2,-1,3,2};
		findNoRepeat(arr);
		findNoRepeatByMap(arr);
	}
}
