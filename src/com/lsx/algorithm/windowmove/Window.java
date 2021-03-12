package com.lsx.algorithm.windowmove;

import java.util.LinkedList;

/*
 * 给定一个数组arr和num，求共有多少个子数组满足：
 * 		最大值 - 最小值 <= num     子数组必须连续。
 */
public class Window {

	/*
	 * 基于：在L-R范围内，max-min<=num 达标，则其内部所有子数组都达标。
	 * 		在其范围内往外扩的数组，都不达标
	 */
	public static int getNum(int[] arr,int num) {
		if(arr == null || arr.length==0) {
			return 0;
		}
		//维护两个双端队列
		LinkedList<Integer> qmax = new LinkedList<>();
		LinkedList<Integer> qmin = new LinkedList<>();
		//子数组都以L开头，R右扩到不达标时，停下，统计子数组个数R-L个
		int L = 0;
		int R = 0;
		int res = 0;
		while(L<arr.length) {
			//R右扩到不达标停下
			while(R<arr.length) {
				// 维护两个双端队列
				while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
					qmax.pollLast();
				}
				qmax.addLast(R);
				while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
					qmin.pollLast();
				}
				qmin.addLast(R);
				//判断是否达标
				if(arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
					//不达标，R停下
					break;
				}
				R++;
			}
			//判断两个队列的队头是否过期,为L前移做准备
			if(qmax.peekFirst() == L) {
				qmax.pollFirst();
			}
			if(qmin.peekFirst() == L) {
				qmin.pollFirst();
			}
			//有R-L个子数组满足条件
			res += R-L;
			L++;
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] arr = {2,4,6,7,3,8,9};
		int num = 3;
		int num2 = getNum(arr, num);
		System.out.println(num2);
	}
}
