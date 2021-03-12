package com.lsx.algorithm.datastructure.link;
/*
 * 链表结构:单链表
 */
public class Link {
	public int key;
	public Link next;
	
	public Link(int key) {
		this.key = key;
	}
	
	public void displayLink() {
		System.out.println(key);
	}
}
