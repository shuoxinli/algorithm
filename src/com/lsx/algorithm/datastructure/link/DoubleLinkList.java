package com.lsx.algorithm.datastructure.link;
/*
 * 双向链表：每个节点有一个指向上一节点的指针和一个指向下一节点的指针。
 */
public class DoubleLinkList {

	class Node{
		public int key;
		public Node previous;
		public Node next;
		
		public Node(int key) {
			this.key = key;
		}
		
		public void displayNode() {
			System.out.println(key);
		}
	}
	
	private Node first; //指向链表中的第一个节点
	private Node last; //指向最后一个节点
	
	public DoubleLinkList() {
		first = null;
		last = null;
	}
	
	//插入链表的头节点
	public void insertFirst(Node node) {
		if(isEmpty()) {
			//空链表，插入的第一个节点即是头节点，也是尾节点
			last = node;
		}else {
			first.previous = node;
		}
		node.next = first;
		first = node;
	}
	
	//插入链表的末端，画图就知道了
	public void insertLast(Node node) {
		if(isEmpty()) {
			//空链表，插入的第一个节点即是头节点，也是尾节点
			first = node;
		}else {
			last.next = node;
			node.previous = last;
		}
		last = node;
	}
	
	//删除第一个节点,并返回
	public Node deleteFirst() throws Exception{
		if(isEmpty()) {
			throw new Exception("链表为空，不能删除。");
		}
		Node temp = first;
		if(first.next==null) {
			//如果只有一个节点，删除后，会影响last节点
			last = null;
		}else {
			first.next.previous = null; //注意删除会影响前后指针
		}
		first = first.next;
		return temp;
	}
	
	//删除最后一个节点，并返回
	public Node deleteLast() throws Exception{
		if(isEmpty()) {
			throw new Exception("链表为空，不能删除。");
		}
		Node temp = last;
		if(last.previous==null) {
			//如果只有一个节点，删除后，会影响first
			first = null;
		}else {
			last.previous.next = null;
		}
		last = last.previous;
		return temp;
	}
	
	//寻找属性为指定值的节点
	public Node find(int key) {
		Node cur = first;
		while(cur!=null && cur.key!=key) {
			if(cur.next==null) {
				return null;
			}
			cur = cur.next;
		}
		return cur;
	}
	
	//在指定节点之后插入，并返回是否成功
	public boolean insertAfter(int key,Node node) {
		Node target = find(key); //查找目标节点
		boolean flag = true;
		if(target==null) {
			flag = false;
		}else {
			//在目标节点之后插入node
			if(target.next==null) {
				//目标节点为表尾
				insertLast(node);
			}else {
				//必须先处理node节点和它下一节点的关联
				target.next.previous = node;
				node.next = target.next;
				
				//才能处理node和它上一节点的关联
				target.next = node;
				node.previous = target;
			}
		}
		return flag;
	}
	
	public void displayList() {
		Node cur = first;
		while(cur!=null) {
			cur.displayNode();
			cur = cur.next;
		}
	}
	
	public boolean isEmpty() {
		return first==null;
	}
	
	
}
