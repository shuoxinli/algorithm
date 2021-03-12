package com.lsx.algorithm.search.binary;
/*
 * 题目归属：二分查找
 * 题目：给定一个有序的字符数组 letters 和一个字符 target，要求找出 letters 中大于 target 的最小字符，如果找不到就返回第 1 个字符。
 * 例子：[c,f,j,k,l] d 返回f   o 返回c
 * 思路：类似查找有重复key的最左位置，但不同的是等于也要排除在外，因为要找小于的，最后如果一直找不到会l会超出数组长度
 */
public class SmallestLetter {
	public static void main(String[] args) {
		char[] letters = {'c','e','f','j','k','l'};
		char target = 'd';
		char smallestLetter = getSmallestLetter(letters, target);
		System.out.println(smallestLetter);
		
	}
	//1次
	public static char getSmallestLetter(char[] letters,char target) {
		int l = 0;
		int h = letters.length-1;
		while(l<=h) {
			int m = l+(h-l)/2;
			if(letters[m] <= target) {
				l = m+1;
			}else {
				h = m-1;
			}
		}
		return l > letters.length ? letters[0]:letters[l];
	}
	
	/*public static char getSmallestLetter(char[] letters,char target) {
		int l=0;
		int h=letters.length-1;
		while(l<=h) { //可以等于,因为下面l和h都是有+1
			int mid = l+(h-l)/2;
			if(letters[mid] <= target) { //要的是比他大的，等于也不要
				l = mid+1;
			}else {
				h = mid-1;
			}
		}
		//如果一直没有找到，l最终会等于letters.length;
		return l < letters.length ? letters[l]:letters[0];
	}*/
}
