package com.lsx.algorithm.search.binary.binaryTree.taolu;

import binaryTree.taolu.BinarySearchTree.Node;

/*
 * 在一棵二叉树中，求最远两个节点的距离。
 */
public class MaxDistance {

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
	
	//封装信息体
	public static class ReturnData{
		public int maxDistance; //当前树的最远距离
		public int h; //当前树的最大深度
		public ReturnData(int maxDistance,int h) {
			this.maxDistance = maxDistance;
			this.h = h;
		}
	}
	
	public static ReturnData process(Node head) {
		if(head == null) {
			return new ReturnData(0, 0);
		}
		ReturnData leftData = process(head.left);
		ReturnData rightData = process(head.right);
		
		int includeHeadDistance = leftData.h+rightData.h+1;
		int p1 = leftData.maxDistance;
		int p2 = rightData.maxDistance;
		
		int maxDistance = Math.max(Math.max(p1, p2), includeHeadDistance);
		int hitself = Math.max(leftData.h, rightData.h)+1;
		
		return new ReturnData(maxDistance, hitself);
	}
	
	public static int getMaxDistance(Node head) {
		return process(head).maxDistance;
	}
	
	public static void main(String[] args) {
		Node head = new Node(6);
		head.left = new Node(10);
		head.left.left = new Node(9);
		head.left.right = new Node(11);
		head.right = new Node(8);
		System.out.println(getMaxDistance(head));
	}
}
