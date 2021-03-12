package com.lsx.algorithm.tree.morris;
/*
 * morris遍历二叉树：时间复杂度还是O（n），但空间复杂度从O(H)降到O（1）。
 * 	本质利用叶子节点多于出来的空间。
 * 规则：从head出发，记录一个当前节点cur=head
 * 		1 如果cur无左孩子，cur右移，cur=cur.right
 * 		2 如果cur有左孩子，找到左子树上的最右节点，记为mostRight
 * 			1）如果mostRight的right指针指向空，则让其指向cur，cur左移，此时cur第一次出现
 * 				mostRight.right = cur,cur = cur.left;
 * 			2）如果mostRight的right指针指向cur，则让其指向空，cur右移，此时cur第二次出现
 * 				mostRight.right = null,cur = cur.right;
 */
public class Morris {
	
	public static class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}

	//Morris遍历，就是把打印时机埋在cur出现的不同时候。
	//先序
	public static void morrisPre(Node head) {
		if(head==null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while(cur!=null) {
			mostRight = cur.left;
			if(mostRight != null) {
				//找到左子树的最右节点
				while(mostRight.right!=null && mostRight.right!=cur) {
					mostRight = mostRight.right;
				}
				if(mostRight.right == null) {
					//让其指向cur，cur左移
					mostRight.right = cur;
					//代表cur第一次出现，打印
					System.out.println(cur.value+" ");
					cur = cur.left;
					continue;
				}else {
					//指向cur，代表第二次出现，让其指向null，cur右移
					mostRight.right = null;
				}
			}else {
				//说明cur无左孩子，直接打印cur
				System.out.println(cur.value+" ");
			}
			cur = cur.right;
		}
	}
	
	//中序遍历，把打印时机埋在左子树都遍历完后
	public static void morrisIn(Node head) {
		if(head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while(cur!=null) {
			mostRight = cur.left;
			if(mostRight!=null) {
				while(mostRight.right!=null && mostRight.right!=cur) {
					mostRight = mostRight.right;
				}
				if(mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				}else {
					mostRight.right = null;
				}
			}
			System.out.println(cur.value+" ");
			cur = cur.right;
		}
	}
	
	//后序遍历：打印时机埋在当cur节点第二次出现时，逆序打印其左子树的右边界，最后逆序打印
	// 整棵树的右边界
	public static void morrisPos(Node head) {
		if(head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while(cur!=null) {
			mostRight = cur.left;
			if(mostRight!=null) {
				while(mostRight.right!=null && mostRight.right!=cur) {
					mostRight = mostRight.right;
				}
				if(mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				}else {
					mostRight.right = null;
					//cur第二次出现，逆序打印其左子树的右边界
					printEdge(cur.left);
				}
			}
			cur = cur.right;
		}
		//最后打印整棵树的右边界
		printEdge(head);
	}
	
	//逆序右边界
	public static Node reverseEdge(Node head) {
		Node p = head;
		Node q = null;
		while(head!=null) {
			p = head.right;
			head.right = q;
			q = head;
			head = p;
		}
		return q;
	}
	
	//逆序打印右边界
	public static void printEdge(Node head) {
		Node tail = reverseEdge(head);
		Node cur = tail;
		while(cur!=null) {
			System.out.println(cur.value+" ");
			cur = cur.right;
		}
		//要逆序回来，不能改变原来结构
		reverseEdge(tail);
	}
	
	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		//morrisPre(head);
		//morrisIn(head);
		morrisPos(head);
	}
}
