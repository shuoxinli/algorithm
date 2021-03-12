package com.lsx.algorithm.windowmove;

import java.util.LinkedList;

/*
 * 滑动窗口：解决时刻更新滑动窗口内的最大值。
 * 数据结构：双端队列 LinkedList 头大尾小，下标为过期时间
 * 要求：L<R L和R只能前进不能后退
 * 情况：L不动，R动：队尾元素比R大，直接进队。
 * 				   队尾元素比R小，弹出，再进队，等于也要出队，因为新来的过期时间晚
 * L动，R不动：代表窗口L左边的元素已过期，队列头是此元素，则弹出，否则不变
 * L动，R动：一定进队一个，但不一定出队
 */
public class MaxWindow {

	/*
	 * 题目：给定一个数组，如{4，3，5，4，3，3，6，7} 窗口大小为3，求每次移动窗口时的最大值，
	 * 		存到一个数组中，并返回。
	 */
	public static int[] getMaxWindow(int[] arr,int w) {
		if(arr == null || arr.length==0) {
			return null;
		}
		//定义一个双端链表，来存放每次窗口的最大值，存的是元素的索引
		LinkedList<Integer> qmax = new LinkedList<>();
		//结果数组
		int[] res = new int[arr.length-w+1];
		int index = 0;
		for(int i=0;i<arr.length;i++) {
			//队尾元素小于等于新来的，出队
			while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
				qmax.pollLast();
			}
			//新来的进队,进队是进索引
			qmax.addLast(i);
			//判断队头是否过期了
			if(qmax.peekFirst() == i-w) {
				//出队
				qmax.pollFirst();
			}
			//当前窗口完整时才收集最大值，为3,防止前期窗口不完整
			if(i>=w-1) {
				res[index++] = arr[qmax.peekFirst()];
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] arr = {4,3,5,4,3,3,6,7};
		int w = 3;
		int[] maxWindow = getMaxWindow(arr, w);
		for(int i : maxWindow) {
			System.out.print(i+" ");
		}
	}
}
