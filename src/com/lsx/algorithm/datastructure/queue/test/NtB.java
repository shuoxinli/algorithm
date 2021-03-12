package com.lsx.algorithm.datastructure.queue.test;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 利用队列来实现十进制转二进制！！
 * 思路：平时都是利用栈的后进先出来实现二进制的转化。计算单个数的二进制更好！！
 * 
 * 	利用队列，对位数来进行判断，每一个都存在两种可能，0或1，从1开始进队，然后出队，加上0或1，
 * 	再两种可能都重新进队，再出来一个数，继续循环。。直到出了n次队，即可得到小于等于n的所有二进制。
 * 	遍历所有可能二进制更优！
 */
public class NtB {

	static void generateBinary(int n) {
		//把二进制看成string，才能进行加0或1
		Queue<String> queue = new LinkedList<String>();
		
		//先把1进队
		queue.add("1");
		while(n-- > 0) {
			String s1 = queue.poll();
			System.out.println(s1);
			
			String s2 = s1;
			
			s1 = s1 + "0"; //每次加0，进队
			queue.add(s1);
			
			s2 = s2 + "1"; //每次加1，进队
			queue.add(s2);
		}
	}
	
	public static void main(String[] args) {
		generateBinary(10);
	}
}
