package com.lsx.algorithm.datastructure.queue.test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * 反转队列中前k个数，如 1 2 3 4 5 k=3 return 3 2 1 4 5
 * 思路：利用栈来辅助，将前k个数从队列中弹出并进栈，然后再出栈进队列，
 * 		最后将队列的前size-k个移到队列尾部即可
 */
public class Reverse_K_elem_queue {

	public static void reverse(Queue<Integer> queue,int k) {
		//定义一个辅助栈
		Stack<Integer> stack = new Stack<>();
		
		//将队列的前k个进栈
		for(int i=1;i<=k;i++) {
			stack.add(queue.peek());
			queue.poll();
		}
		
		//然后将k个重新出栈，再进队列
		for(int i=1;i<=k;i++) {
			queue.offer(stack.pop());
		}
		
		//将队列的前size-k，移到队尾
		for(int i=1;i<=queue.size()-k;i++) {
			queue.offer(queue.poll());
		}
		
		//输出队列的元素
		while(!queue.isEmpty()) {
			System.out.print(queue.poll()+" ");
		}
	}
	
	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		queue.offer(4);
		queue.offer(5);
		reverse(queue, 3);
	}
}
