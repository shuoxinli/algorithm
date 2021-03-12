package com.lsx.algorithm.search.binary.binaryTree.taolu;
/*
 * 在一棵二叉树中，求整棵树的最大搜索二叉树的大小
 */
public class BinarySearchTree {

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
	
	//封装的返回信息体
	public static class ReturnType{
		public int size; //搜树的大小
		public Node head; //搜树的头节点
		public int min; //当前节点右子树的最小值
		public int max; //当前节点左子树的最大值
		public ReturnType(int size,Node head,int min,int max) {
			this.size = size;
			this.head = head;
			this.min = min;
			this.max = max;
		}
	}
	
	//递归过程，即二叉树的动归，子树的信息可以被父节点利用
	public static ReturnType process(Node head) {
		if(head == null) {
			return new ReturnType(0, null, Integer.MAX_VALUE, Integer.MIN_VALUE);
		}
		//黑盒操作，直接拿到左搜树和右搜树
		Node left = head.left;
		ReturnType leftSubTreeInfo = process(left);
		Node right = head.right;
		ReturnType rightSubTreeInfo = process(right);
		
		//判断几种可能性
		//第一种，整棵树都是搜
		int includeItSelf = 0;
		if(leftSubTreeInfo.head == left && rightSubTreeInfo.head == right
				&& leftSubTreeInfo.max < head.value && rightSubTreeInfo.min > head.value) {
			includeItSelf = leftSubTreeInfo.size+rightSubTreeInfo.size+1;
		}
		
		//第二种，搜在左
		int p1 = leftSubTreeInfo.size;
		//第三种，搜在右
		int p2 = rightSubTreeInfo.size;
		
		//三者取最大
		int maxSize = Math.max(Math.max(p1, p2), includeItSelf);
		//当前搜的头节点
		Node maxHead = p1 > p2 ? leftSubTreeInfo.head : rightSubTreeInfo.head;
		if(maxSize == includeItSelf) {
			maxHead = head;
		}
		
		//返回信息体给上一层调用
		return new ReturnType(maxSize, maxHead, 
				Math.min(Math.min(leftSubTreeInfo.min, rightSubTreeInfo.min), head.value), 
				Math.max(Math.max(leftSubTreeInfo.max, rightSubTreeInfo.max), head.value));
	}
	
	public static int getMaxSubTreeSize(Node head) {
		return process(head).size;
	}
	
	public static void main(String[] args) {
		Node head = new Node(6);
		head.left = new Node(10);
		head.left.left = new Node(9);
		head.left.right = new Node(11);
		head.right = new Node(8);
		System.out.println(getMaxSubTreeSize(head));
	}
}
