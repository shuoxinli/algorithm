package com.lsx.algorithm.datastructure.tree;
/*
 * 根据前序和中序构建二叉树，并转为求和树
 */

import java.util.Scanner;

class Snode{
		int value;
		int sum;
		Snode left;
		Snode right;
	}

public class STree {

	static int[] preOrder;
	static int[] inOrder;
	
	public static Snode createTreeByPreAndIno(int root,int begin,int end) {
		if(begin > end) {
			return null;
		}
		//前序的第一个就是根节点
		Snode snode = new Snode();
		snode.value = preOrder[root];
		//找到根节点在中序的位置
		int rootIndex = 0;
		int count = 0;
		for(rootIndex = begin;rootIndex <= end; rootIndex++) {
			count++;
			if(inOrder[rootIndex] == preOrder[root]) {
				break;
			}
		}
		//然后中序的根节点前面为左子树，后面为右子树
		//按照同样的办法遍历构建左子树和右子树
		snode.left = createTreeByPreAndIno(root+1, begin, rootIndex-1);
		snode.right = createTreeByPreAndIno(root+count, rootIndex+1, end);
		return snode;
	}
	
	//求每个节点的sum
	static void sumsnode(Snode snode) {
		if(snode.left == null && snode.right == null) {
			snode.sum = 0;
		}else if(snode.left == null) {
			sumsnode(snode.right);
			snode.sum = snode.right.sum + snode.value;
		}else if(snode.right == null) {
			sumsnode(snode.left);
			snode.sum = snode.left.sum + snode.value;
		}else {
			sumsnode(snode.left);
			sumsnode(snode.right);
			snode.sum = snode.left.sum + snode.left.value + snode.right.sum + snode.right.value;
		}
	}
	
	//中序遍历输出sum
	static void inOrderSum(Snode snode) {
		if(snode == null)
			return;
		inOrderSum(snode.left);
		System.out.print(snode.sum+" ");
		inOrderSum(snode.right);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			int n = sc.nextInt();
			preOrder = new int[n];
			inOrder = new int[n];
			for(int i=0;i<n;i++) {
				preOrder[i] = sc.nextInt();
			}
			for(int i=0;i<n;i++) {
				inOrder[i] = sc.nextInt();
			}
			
			Snode snode = createTreeByPreAndIno(0, 0, n-1);
			sumsnode(snode);
			inOrderSum(snode);
		}
	}
}
