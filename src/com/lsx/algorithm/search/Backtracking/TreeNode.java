package com.lsx.algorithm.search.Backtracking;

import java.util.ArrayList;
import java.util.List;
/*
 * 题目归属：回溯
 * 题目：给定一个树，返回从根到叶子节点的所有路径。
 * 例子：  1
	  /   \
	 2     3
	  \
	   5              return ["1->2->5", "1->3"]
	思路：从根出发，不断向下遍历，到达叶子节点，然后回溯到上一层，继续遍历，找到所有路径。
 */
public class TreeNode {

	TreeNode left;
	TreeNode right;
	int value;
	public TreeNode(int value) {
		this.value=value;
	}
	
	public static List<String> binaryTreePaths(TreeNode root){
		//存储每一条从根到叶子的路径
		List<String> paths = new ArrayList<>();
		if(root == null) {
			return paths;
		}
		//标记被访问过的节点
		List<Integer> values = new ArrayList<>();
		backTracking(root,values,paths);
		return paths;
	}
	
	public static void backTracking(TreeNode node,List<Integer> values,List<String> paths) {
		if(node == null) {
			return;
		}
		
		//标记该节点被访问
		values.add(node.value);
		//判断是否是叶子节点
		if(isLeaf(node)) {
			//添加路径到paths
			paths.add(buildPath(values));
		}else {
			//遍历左子树
			backTracking(node.left, values, paths);
			//遍历右子树
			backTracking(node.right, values, paths);
		}
		//返回上一层节点，取消访问标记
		values.remove(values.size()-1); //最后一个的索引
	}
	
	//判断是否是叶子
	private static boolean isLeaf(TreeNode node) {
		if(node.left == null && node.right == null) {
			return true;
		}
		return false;
	}
	
	//根据values中的值来构建一个从根到叶子的路径
	private static String buildPath(List<Integer> values) {
		StringBuilder builder = new StringBuilder();
		for (int i=0;i<values.size();i++) {
			if(i==0) {
				builder.append(values.get(i));
			}else {
				builder.append("->"+values.get(i));
			}
			
		}
		return builder.toString();
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.right = new TreeNode(5);
		
		List<String> treepath = binaryTreePaths(root);
		for (String string : treepath) {
			System.out.println(string);
		}
	}
}
