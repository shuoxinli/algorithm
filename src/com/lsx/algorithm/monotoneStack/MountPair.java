package com.lsx.algorithm.monotoneStack;

import java.util.Stack;
import java.util.TreeMap;

/*
 * 题目：给定一个含重复元素的数组，围成一个环，规定：
 * 		相邻的数能相互看见，不相邻的数，只要路径上都小于这两个数的最小值即可看见。
 * 	求这样的数有多少对。
 */
public class MountPair {
	
	//定义一个结构类，元素，次数
	public static class Pair{
		public int value;
		public int times;
		public Pair(int value) {
			this.value = value;
			this.times = 1;
		}
	}

	public static long communications(int[] arr) {
		if(arr == null || arr.length<2) {
			return 0;
		}
		int size = arr.length;
		int maxIndex = 0;
		//取最大数作为打底元素，确保其他元素都能找到左大。
		for(int i=0;i<size;i++) {
			maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
		}
		int value = arr[maxIndex];
		//从最大元素的下标开始找下一个
		int index = nextIndex(size,maxIndex);
		long res = 0L;
		Stack<Pair> stack = new Stack<>();
		//最大元素进栈,作为栈底
		stack.push(new Pair(value));
		while(index!=maxIndex) {
			value = arr[index];
			//维护栈，栈顶小，栈底大
			while(!stack.isEmpty() && stack.peek().value < value) {
				int times = stack.pop().times;
				res += getInternalSum(times)+2*times;
			}
			if(!stack.isEmpty() && stack.peek().value == value) {
				stack.peek().times++;  //如果值相等，直接次数+1
			}else {
				stack.push(new Pair(value));
			}
			index = nextIndex(size,index);
		}
		//遍历完数组，栈不为空，自行弹出
		while(!stack.isEmpty()) {
			int times = stack.pop().times;
			res += getInternalSum(times);
			if(!stack.isEmpty()) {
				res += times;
				if(stack.size()>1) {
					res += times;
				}else {
					res += stack.peek().times > 1 ? times : 0;
				}
			}
		}
		return res;
	}
	
	//计算C2m
	public static long getInternalSum(int n) {
		return n == 1 ? 0 : (long)n * (long) (n-1) / 2L;
	}
	
	//计算从某个位置出发的下一个
	public static int nextIndex(int size,int i) {
		return i <(size-1) ? i+1 : 0;
	}
	
	public static void main(String[] args) {
		int[] arr = {5,4,2,1,3};
		long communications = communications(arr);
		System.out.println(communications);
	}
}
