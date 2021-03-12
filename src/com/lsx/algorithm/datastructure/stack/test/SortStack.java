package com.lsx.algorithm.datastructure.stack.test;

import java.util.Stack;

/*
 * 利用栈来对栈中元素进行排序，借助辅助空间
 */
public class SortStack {

	public static void sort(Stack<Integer> stack) {
		//定义一个辅助栈
		Stack<Integer> stack1 = new Stack<>();
		while(!stack.isEmpty()) {
			int temp = stack.pop();
			
			//简化版,辅助栈不为空，且栈顶元素大于temp，则取出放入stack
			while(!stack1.isEmpty() && stack1.peek() > temp) {
				stack.push(stack1.pop());
			}
			//辅助栈为空或者栈顶元素小于temp，直接放入stack1
			stack1.push(temp);
			
			/*if(stack1.isEmpty()) {
				//直接进栈
				stack1.push(temp);
			}else {
				//如果辅助栈不为空，则不断弹出元素与temp进行比较
				while(!stack1.isEmpty()) {
					int temp1 = stack1.pop();
					if(temp >= temp1) {
						//temp大于，直接进辅助栈
						stack1.push(temp1);
						stack1.push(temp);
						break;
					}else {
						//temp小于，将temp1放到stack，继续找到temp在辅助栈的位置
						stack.push(temp1);
					}
				}
				//最后如果辅助栈为空，temp直接进栈
				if(stack1.isEmpty()) {
					stack1.push(temp);
				}
			}*/
		}
		//循环结果是辅助栈的元素都是有序的，从大到小
		while(!stack1.isEmpty()) {
			System.out.print(stack1.pop()+" ");
		}
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(34);
		stack.push(4);
		stack.push(20);
		stack.push(13);
		stack.push(3);
		sort(stack);
	}
}
