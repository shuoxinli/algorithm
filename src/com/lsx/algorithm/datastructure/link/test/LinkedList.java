package com.lsx.algorithm.datastructure.link.test;
/*
 * 反转单向链表
 */
public class LinkedList {

	static Node head;
	//定义一个节点类
	static class Node{
		int data;
		Node next;
		
		Node(int d){
			data = d;
			next = null;
		}
	}
	
	//反转单向表
	public static Node reverse(Node head) {
		if(head.next == null) {
			return head;
		}
		//定义两个辅助指针
		Node p = null; //中间指针
		Node q = null; //最后反转后的头指针
		
		while(head!=null) {
			p = head.next;
			head.next = q;
			q = head;
			head = p;
		}
		return q;
	}
	
	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		Node reverse_head = reverse(head);
		while(reverse_head!=null) {
			System.out.println(reverse_head.data);
			reverse_head = reverse_head.next;
		}
	}
}
