package com.lsx.algorithm.divideandconquer;

import java.util.LinkedList;
import java.util.List;

/*
 * 题目归属：分治算法
 * 题目：给定一个数字n，要求生成所有值为1...n的二叉搜索树
 * 思路：从1还是遍历到n，左子树和右子树分开生成，最后合并起来。
 */
public class Subtrees {

	//树类
	public static class TreeNode{
		TreeNode left;
		TreeNode right;
		TreeNode parent;
		int key;
		public TreeNode(TreeNode left, TreeNode right, TreeNode parent, int key) {
			this.left = left;
			this.right = right;
			this.parent = parent;
			this.key = key;
		}
		public TreeNode(int key) {
			this.key = key;
		}
		
	}
	
	public static List<TreeNode> generateTrees(int n){
		if(n<1) {
			return new LinkedList<TreeNode>();
		}
		return generateSubtrees(1,n);
	}
	
	//s为头节点，e为结束节点
	/*public static List<TreeNode> generateSubtrees(int s,int e){
		List<TreeNode> res = new LinkedList<TreeNode>();
		if(s>e) {
			res.add(null);
			return res;
		}
		//从s遍历到e，从i分为左右两部分进行各自建树，最后再合并
		for(int i=s;i<=e;i++) {
			List<TreeNode> leftSubtrees = generateSubtrees(s, i-1);
			List<TreeNode> rightSubtrees = generateSubtrees(i+1, e);
			//合并
			for (TreeNode left : leftSubtrees) {
				for (TreeNode right : rightSubtrees) {
					TreeNode root = new TreeNode(i);
					root.left = left;
					root.right = right;
					res.add(root);
				}
			}
		}
		return res;
	}*/
	

	public static List<TreeNode> generateSubtrees(int s, int e){
		//存每个子过程生成的子树
		List<TreeNode> res = new LinkedList<>();
		if(s>e) {
			res.add(null);
			return res;
		}
		//从头遍历尾
		for(int i=s;i<=e;i++) {
			//黑盒操作，调用子过程，把s-e分为左右两部分，分别取生成当前节点i的左右子树
			List<TreeNode> leftSubTrees = generateSubtrees(s, i-1);
			List<TreeNode> rightSubTrees = generateSubtrees(i+1, e);
			//得到左右子树，合并在当前节点i的子树下
			for(TreeNode l : leftSubTrees) {
				for(TreeNode r : rightSubTrees) {
					TreeNode root = new TreeNode(i);
					root.left = l;
					root.right = r;
					res.add(root);
				}
			}
		}
		return res;
	}
	
	//中序遍历
	public static void inorder(TreeNode x) {
		if(x!=null) {
			inorder(x.left);
			System.out.print(x.key+" ");
			inorder(x.right);
		}
	}
	
	public static void preorder(TreeNode x) {
		if(x!=null) {
			System.out.print(x.key+" ");
			inorder(x.left);
			inorder(x.right);
		}
	}
	public static void main(String[] args) {
		List<TreeNode> trees = generateTrees(5);
		//list存的都是每个过程中生成的子树，最后只需要取头节点，就可以遍历所有节点了。
		/*for (TreeNode treeNode : trees) {
			inorder(treeNode);
			System.out.println(treeNode.key+" ");
		}*/
		inorder(trees.get(0));
		preorder(trees.get(0));
	}
}
