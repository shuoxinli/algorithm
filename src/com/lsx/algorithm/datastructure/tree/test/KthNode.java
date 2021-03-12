package com.lsx.algorithm.datastructure.tree.test;

import tree.BinarySearchTree;
import tree.Node;

/*
 * 利用二叉搜索树找到第k大的节点：
 * 思路就是将中序遍历的思维反一下，先遍历右节点，中间节点，左节点。
 */
public class KthNode {

	//定义一个全局变量，避免递归调用被频繁的放入调用栈中
	static int count = 0;
	
	//找第k大
	public static void findKthNode(Node root,int k) {
		if(root==null) {
			return;
		}
		//先找右节点
		findKthNode(root.rightChild, k);
		//计数
		count++;
		if(count==k) {
			System.out.println(root.key);
			return;
		}
		//再找左节点
		findKthNode(root.leftChild, k);
	}
	
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree(); 
		tree.insert(new Node(50)); 
        tree.insert(new Node(30)); 
        tree.insert(new Node(20)); 
        tree.insert(new Node(40)); 
        tree.insert(new Node(70)); 
        tree.insert(new Node(60)); 
        tree.insert(new Node(80));
        findKthNode(tree.getRoot(), 5);
	}
}
