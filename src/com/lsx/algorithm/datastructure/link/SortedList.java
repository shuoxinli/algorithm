package com.lsx.algorithm.datastructure.link;
/*
 * 有序链表：与单链表主要的取标就是插入的时候，会排序进行插入，输出的时候也是有序的。
 */
public class SortedList {

	private Link first; // 指向链表中的第一个链结点

	public SortedList() {
		first = null;
	}

	// 插入
	public void insert(Link link) {
		Link cur = first;
		Link previous = null;
		while(cur!=null && cur.key < link.key) {
			previous =cur;
			cur = cur.next;
		}
		if (previous == null) {
			//表头插入
			this.first = link;
		}else {
			previous.next = link;
		}
		link.next = cur;
	}

	// 删除第一个链结点，返回删除的链结点引用
	public Link deleteFirst() throws Exception {
		if (isEmpty()) {
			throw new Exception("链表为空！不能进行删除操作");
		}
		Link temp = first;
		first = first.next;
		return temp;
	}

	// 打印出所有的链表元素
	public void displayList() {
		Link cur = first;
		while (cur != null) { // 循环打印每个链结点
			cur.displayLink();
			cur = cur.next;
		}
	}

	// 判空
	public boolean isEmpty() {
		return (first == null);
	}

}
