package com.lsx.algorithm.datastructure.tree;
/*
 * 树节点的结构
 */
public class Node {

	public int key;
	public Node leftChild;
	public Node rightChild;
	
	public Node(int key) {
		this.key=key;
		leftChild=null;
		rightChild=null;
	}
	
	public void displayNode() {
		System.out.println(key);
	}
}
