package com.lsx.algorithm.datastructure.queue.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 利用两个队列实现栈操作
 * 思路：定义两个队列q1,q2，进栈从q2进，并每次检查q1是否有元素，有就搬到q2，然后交换q1和q2指向，
 * 		就可实现后进先出了。出栈就从q1出
 */
public class QtoS {

	//定义一个内部静态栈类，不依赖于外部类
	static class Stack{
		//静态属性size
		static int size;
		//构造方法
		Stack(){
			size=0;
		}
		
		//定义两个队列来操作
		static Queue q1 = new LinkedList();
		static Queue q2 = new LinkedList();
		
		//定义进栈操作
		static void push(int x) {
			size++;
			//添加到q2
			q2.add(x);
			//检查q1是否有元素，有则转到q2
			while(!q1.isEmpty()) {
				q2.add(q1.peek());
				q1.remove();
			}
			//交换q1和q2的指向，这样就实现后来先出
			Queue q = q1;
			q1 = q2;
			q2 = q;
		}
		
		//定义出栈操作,都是从q1出去的
		static Object pop() {
			if(q1.isEmpty()) {
				return null;
			}
			size--;
			return q1.poll();
		}
		
		static Object top() {
			if(q1.isEmpty()) {
				return null;
			}
			return q1.peek();
		}
		
		static int size() {
			return size;
		}
	};
	
	public static void main(String[] args) {
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println(stack.size());
		System.out.println(stack.top());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
}
