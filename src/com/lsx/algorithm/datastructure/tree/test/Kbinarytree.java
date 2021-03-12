package com.lsx.algorithm.datastructure.tree.test;

import tree.Node;

/*
 * 寻找距离给定节点为k的节点，即第k层的所有节点
 */
public class Kbinarytree {

	Node root;
	
	//找到第k层的所有节点
	public void findKNodes(Node node,int k) {
		if(node == null) {
			return;
		}
		if(k==0) {
			System.out.println(node.key);
			return;
		}else {
			findKNodes(node.leftChild, k-1);
			findKNodes(node.rightChild, k-1);
		}
	}
	
	public static void main(String[] args) {
		Kbinarytree tree = new Kbinarytree();
		tree.root = new Node(1); 
        tree.root.leftChild = new Node(2); 
        tree.root.rightChild = new Node(3); 
        tree.root.leftChild.leftChild = new Node(4); 
        tree.root.leftChild.rightChild = new Node(5); 
        tree.root.rightChild.leftChild = new Node(8);
        
        tree.findKNodes(tree.root, 2);
	}
}
