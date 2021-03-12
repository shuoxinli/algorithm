package com.lsx.algorithm.twopointer;

import java.util.Scanner;

/*
 * 题目归属：双指针问题
 * 题目：输入一个字符串，判断它是否是回文串，或者删掉其中一个字符后是回文串，输出true，否则输出false
 * 例子：abba，acbba 输出true
 * 
 * 遇到问题，当前后指针开始遍历时到不同后，不知道要删除前还是后一个。
 * 解决：分成两个判断。
 */
public class Palindrome {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String string = sc.nextLine();
		boolean isPalind = validPalindrome(string);
		System.out.println(isPalind);
	}
	
	public static boolean validPalindrome(String s) {
		//定义前后指针，遍历字符串
		int i,j;
		for(i=0,j=s.length()-1;i<j;i++,j--) {
			if(s.charAt(i) != s.charAt(j)) {
				//第一次不同，可以允许删除一个字符，再看看是否是回文串
				return isPalindrome(s,i+1,j) || isPalindrome(s,i,j-1);
			}
		}
		return true;
	}
	
	//删除前或后的一个字符后是否还是回文串
	public static boolean isPalindrome(String s,int i,int j) {
		while(i<j) {
			if(s.charAt(i) != s.charAt(j)) {
				return false; //第二次不同，就不是回文串了。
			}
			i++;
			j--;
		}
		return true;
	}
}
