package com.lsx.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*
 * 题目归属：贪心算法
 * 题目：给定一组区间，计算需要移除的区间数，使得剩下的区间都不重叠，且数量最多。
 * 例子：[[1,2],[3,6],[4,5],[5,7]]  移除[3,6] 剩下三个区间都不重叠。
 * 思路：使用贪心策略，计算最多能有多少不重叠的区间，然后用总数减去不重叠的区间数就等于移除的区间数。
 * 		贪心策略就是每次选择都是选最优，区间的结尾很重要，因为选择的结尾越小，留给后面的空间就越大，
 * 		可选择的区间数也越多。
 * 		所以先按区间的结尾进行排序，每次都选择那个结尾最小的，且不与上一个区间重叠的。
 */
public class Interval {

	//定义一个区间
	private int start;
	private int end;
	
	
	
	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public static void main(String[] args) {
		//创建一个对象数组
		Interval[] interval = new Interval[4];
		interval[0] = new Interval(1,2);
		interval[1] = new Interval(3,6);
		interval[2] = new Interval(4,5);
		interval[3] = new Interval(5,7);
		int removeCount = eraseOverlapIntervals(interval);
		System.out.println(removeCount);
		
		
	}
	
	/*public static int eraseOverlapIntervals(Interval[] interval) {
		if(interval.length == 0) {
			return 0;
		}
		//按区间的结尾排序,传入一个比较器
		Arrays.sort(interval, Comparator.comparingInt(a -> a.end));
		int count = 1; //第一个区间默认选,统计不重叠的区间数
		int end = interval[0].end;
		for(int i=1;i<interval.length;i++) {
			if(interval[i].start >= end) {
				//不与前一个重叠，就选上
				count++;
				//更新end
				end = interval[i].end;
			}
		}
		return interval.length-count;
	}*/
	
	public static int eraseOverlapIntervals(Interval[] interval) {
		if(interval.length == 0) {
			return 0;
		}
		//对区间尾部进行排序，每次选最小且不与上一个区间重叠
		Arrays.sort(interval, Comparator.comparingInt(a->a.end));
		int count=1;
		int end = interval[0].end;
		for(int i=1;i<interval.length;i++) {
			if(interval[i].start >= end) {
				count++;
				end = interval[i].end;
			}
		}
		return interval.length-count;
	}
}
