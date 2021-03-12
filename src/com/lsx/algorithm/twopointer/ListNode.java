package com.lsx.algorithm.twopointer;
/*
 * 题目：判断链表是否存在环
 * 思路：定义两个指针从头开始走，一个每次走一步，一个每次走两步，看走两步的是否会遇到走一步的，遇到则有环。
 */
public class ListNode {

	protected ListNode next; //指针域
	public int data; //数据域
	
	public boolean hasCycle(ListNode head) {
		if(head == null)
			return false;
		ListNode l1 = head,l2 = head.next;
		while(l1 != null && l2 != null && l2.next != null) {
			if(l1 == l2)
				return true;
			l1 = l1.next;
			l2 = l2.next.next;
		}
		return false;
	}
	
}
