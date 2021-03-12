package com.lsx.algorithm.datastructure.link.test;

import java.util.HashSet;

/*
 * 移除表中重复的元素
 * 
 * 难理解的点，传一个头节点给函数去修改，不用返回修改后的链表的头节点：
 * 		这就涉及jvm内存，因为都定义为静态的，所有对象共享，你定义一个链表是共享的，head是该链表的头节点，
 * 		而你在函数中修改的链表就是head指向的链表，所以修改后，head指向的还是原来那个链表，只不过被修改过了。
 */
public class RemoveRepeat {

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
	
	//移除重复元素
	public static void remove(Node head) {
		
		//定义一个hashset来判断重复元素
		HashSet<Integer> set = new HashSet<>();
		
		//定义一个存前一个节点
		Node pre = null;
		//定义当前节点
		Node cur = head;
		while(cur!=null) {
			if(set.contains(cur.data)) {
				pre.next = cur.next;
			}else {
				set.add(cur.data);
				pre = cur;
			}
			cur = cur.next;
		}
	}
	
	public static void main(String[] args) {
		Node start = new Node(10); 
        start.next = new Node(12); 
        start.next.next = new Node(11); 
        start.next.next.next = new Node(11); 
        start.next.next.next.next = new Node(12); 
        start.next.next.next.next.next = new Node(11); 
        start.next.next.next.next.next.next = new Node(10);
        
        remove(start);
        while(start!=null) {
        	System.out.println(start.data);
        	start = start.next;
        }
        
	}
}
