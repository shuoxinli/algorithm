package com.lsx.algorithm.datastructure.stack;
/*
 * 栈：利用数组来实现栈结构
 */
public class Stack {

	private int size; //栈大小
	private int top; //栈顶元素的下标
	private int[] stackArray; //栈容器
	
	//构造函数
	public Stack(int size) {
		stackArray = new int[size];
		top = -1; //初始化栈，无元素，栈顶下标为-1
		this.size = size;
	}
	
	//入栈，栈顶指针加1
	public void push(int elem) {
		stackArray[++top] = elem;
	}
	
	//出栈，删除栈顶元素，下标减1
	public int pop() {
		return stackArray[top--];
	}
	
	//查看栈顶元素
	public int peek() {
		return stackArray[top];
	}
	
	//判断是否为空
	public boolean isEmpty() {
		return (top == -1);
	}
	
	//判断是否满
	public boolean isFull() {
		return (top==size-1);
	}
}
