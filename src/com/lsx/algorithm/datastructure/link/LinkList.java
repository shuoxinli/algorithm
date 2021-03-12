package com.lsx.algorithm.datastructure.link;
/*
 * 单链表的封装类
 */
public class LinkList {
	private Link first; //链表的第一个节点
	
	public LinkList() {
		this.first = null;
	}
	
	//插入到链表的头节点
	public void insertFirst(int key) {
		Link link = new Link(key);
		link.next = first;
		first = link;
	}
	
	//删除第一个节点，返回该节点
	public Link deleteFirst() throws Exception{
		if(isEmpty()) {
			throw new Exception("链表为空，不能删除。");
		}
		Link temp = first;
		first = first.next;
		return temp;
	}
	
	//打印所有节点元素
	public void displayList() {
		Link cur = first;
		while(cur!=null) {
			cur.displayLink();
			cur = cur.next;
		}
	}
	
	//删除指定属性的节点
	public Link delete(int key) {
		Link cur = first; //当前节点
		Link previous = first; //当前节点的上一节点
		while(cur.key!=key) {
			if(cur.next==null) {
				return null;
			}
			previous = cur;
			cur = cur.next;
		}
		//如果为头节点，删除
		if(cur == first) {
			first = first.next;
		}else {
			previous.next = cur.next;
		}
		return cur;
		
	}
	
	//找到指定数据的节点
	public Link findByKey(int key) {
		Link cur = first;
		while(cur.key!=key) {
			if(cur.next==null) {
				return null;
			}
			cur = cur.next;
		}
		return cur;
	}
	
	public boolean isEmpty() {
		return first==null;
	}
}
