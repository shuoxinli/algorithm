package com.lsx.algorithm.twopointer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/*
 * 归属题目类型：双指针问题
 * 题目：给定一个字符串，反转字符串中的元音字符
 * 例如 s=hello , r=holle
 * 时间复杂度都为O（n）
 */
public class ReverseString {

	public static void main(String[] args) {
		//输入一个字符串
		Scanner sc = new Scanner(System.in);
		String string = sc.nextLine();
		String reverseVowels = reverseVowels(string);
		System.out.println(reverseVowels);
	}
	
	//定义一个无序的hashSet用于存放元音字符，方便判断字符串中的字符是否属于元音，也可以自己写一个判断方法
	private final static HashSet<Character> vowels = new HashSet<Character>(
			Arrays.asList('a','e','i','o','u','A','E','I','O','U')); //将数组转为列表，作为构造参数
	
	//反转元音字符
	public static String reverseVowels(String s) {
		//定义指向字符串的前后指针
		int i=0;
		int j=s.length()-1;
		//定义一个等长度的数组来存放反转后的字符串
		char[] result = new char[s.length()];
		while(i<=j) {	//为什么要=，因为这次是整个字符串的每个字符都要遍历到，不是找到就停止的那种。
			char ci = s.charAt(i);
			char cj = s.charAt(j);
			//取出前后两个字符，判断是否是元音字符
			if(!vowels.contains(ci)) {
				//i先移动到元音字符处，停下，如果不是元音直接填入数组
				result[i++] = ci;
			}else if(!vowels.contains(cj)) {
				//j再移动到元音字符处，停下，如果不是元音直接填入数组
				result[j--] = cj;
			}else {
				//i和j都指向元音字符了，交换两个元音字符的位置
				result[i++] = cj;
				result[j--] = ci;
			}
		}
		return new String(result);
	}
}
