package com.lsx.algorithm.datastructure.tree.test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import tree.Node;

/*
 * 按要求打印树
 */
public class PrintTree {

	//从上到下，一层一层打印节点
	public static void printFromTopToBottom(Node head) {
		if(head==null) {
			return;
		}
		
		//定义一个队列来存放节点，也用来保证输出顺序
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			System.out.print(node.key+" ");
			
			//先看左子树是否为空
			if(node.leftChild!=null) {
				//加入队列
				queue.add(node.leftChild);
			}
			
			if(node.rightChild!=null) {
				queue.add(node.rightChild);
			}
			
		}
	}
	
	//分行从上到下打印树
	public static void printFromTopToBottom2(Node head) {
		if(head==null) {
			return;
		}
		
		//定义一个队列来存放节点，也用来保证输出顺序
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		//定义一个变量表示当前行剩下要打印的个数
		int isPrint = 1;
		//统计下一行要打印的个数
		int nextPrint=0;
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			System.out.print(node.key+" ");
			isPrint--;
			//先看左子树是否为空
			if(node.leftChild!=null) {
				//加入队列
				queue.add(node.leftChild);
				nextPrint++;
			}
			
			if(node.rightChild!=null) {
				queue.add(node.rightChild);
				nextPrint++;
			}
			
			//判断当前行是否打印完了，换行
			if(isPrint==0) {
				System.out.println();
				isPrint=nextPrint;
				nextPrint=0;
			}
			
		}
	}
	
	//之字形打印
	public static void printZhi(Node head) {
		if(head == null) {
			return;
		}
		
		//定义两个栈，一个存从左到右的节点，一个存从右到左的节点
		Stack<Node> stack1 = new Stack<>();
		Stack<Node> stack2 = new Stack<>();
		stack1.add(head);
		//定义一个变量表示该行走向
		int go=0;//0左到右，1右到左
		while(!stack1.isEmpty() || !stack2.isEmpty()) {
			if(go==0) {
				Node node = stack1.pop();
				System.out.print(node.key+" ");
				//把左右节点添加到stack2,先左后右
				if(node.leftChild!=null) {
					stack2.add(node.leftChild);
				}
				if(node.rightChild!=null) {
					stack2.add(node.rightChild);
				}
				if(stack1.isEmpty()) {
					//表示当前行走完了
					go=1;
					System.out.println();
					continue;
				}
			}
			if(go==1) {
				Node node = stack2.pop();
				System.out.print(node.key+" ");
				//把左右节点添加到stack1，先右后左
				if(node.rightChild!=null) {
					stack1.add(node.rightChild);
				}
				if(node.leftChild!=null) {
					stack1.add(node.leftChild);
				}
				if(stack2.isEmpty()) {
					//表示当前行走完了
					go=0;
					System.out.println();
					continue;
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		Node head = new Node(1);
		head.leftChild = new Node(2);
		head.rightChild = new Node(3);
		head.leftChild.leftChild = new Node(4);
		head.leftChild.rightChild = new Node(5);
		head.rightChild.leftChild = new Node(6);
		head.rightChild.rightChild = new Node(7);
		printFromTopToBottom(head);
		System.out.println();
		
		printFromTopToBottom2(head);
		
		printZhi(head);
	}
}
