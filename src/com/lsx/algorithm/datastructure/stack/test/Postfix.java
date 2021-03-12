package com.lsx.algorithm.datastructure.stack.test;

import java.util.Stack;

/*
 * 利用栈解决后缀表达式
 */
public class Postfix {

	//复杂度为O(n)
	public static int compute(String s) {
		//定义一个栈来存放表达式中的数值
		Stack<Integer> stack = new Stack<>();
		//遍历表达式的每个字符
		int a,b;
		for(int i=0;i<s.length();i++) {
			char c = s.charAt(i);
			if(c >= '0' && c <= '9') {
				stack.push((int)(c-'0'));
			}else {
				a=stack.pop();
				b=stack.pop();
				switch(c) {
				case '+':
					stack.push(b+a);
					break;
				case '-':
					stack.push(b-a);
					break;
				case '*':
					stack.push(b*a);
					break;
				case '/':
					stack.push(b/a);
					break;
				}
			}
		}
		return stack.pop();
	}
	
	public static void main(String[] args) {
		String s = "231*+9-";
		int result = compute(s);
		System.out.println(result);
	}
}
