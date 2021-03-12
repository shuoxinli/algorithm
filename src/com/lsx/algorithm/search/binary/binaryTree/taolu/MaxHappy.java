package com.lsx.algorithm.search.binary.binaryTree.taolu;

import java.util.ArrayList;
import java.util.List;

/*
 * 公司上下级关系是一棵多叉树，现要组织晚会，一个员工如果他的直接上级参加，则这个员工就不参加，
 * 每个员工都有自己的活跃度，求整个晚会能达到的最大活跃度
 */
public class MaxHappy {

	public static class Node{
		public int huo; //活跃度
		public List<Node> nexts; //关联的下级
		public Node(int huo) {
			this.huo = huo;
			nexts = new ArrayList<>();
		}
	}
	
	//封装的信息体
	public static class ReturnData{
		public int lai_huo;  //该员工来的整个晚会活跃度
		public int bu_lai_huo;  //该员工不来的整个晚会活跃度
		public ReturnData(int lai_huo,int bu_lai_huo) {
			this.lai_huo = lai_huo;
			this.bu_lai_huo = bu_lai_huo;
		}
	}
	
	public static ReturnData process(Node head) {
		int lai_huo = head.huo;
		int bu_lai_huo = 0;
		
		//遍历该节点的所有下级节点
		for(int i=0;i<head.nexts.size();i++) {
			//取得每个节点，递归
			Node next = head.nexts.get(i);
			ReturnData nextData = process(next);
			//统计他来的活跃度和不来的活跃度
			lai_huo += nextData.bu_lai_huo;
			bu_lai_huo += Math.max(nextData.lai_huo, nextData.bu_lai_huo);
		}
		return new ReturnData(lai_huo,bu_lai_huo);
	}
	
	public static int getMaxHuo(Node head) {
		ReturnData headData = process(head);
		return Math.max(headData.lai_huo, headData.bu_lai_huo);
	}
	
	public static void main(String[] args) {
		Node head = new Node(10);
		head.nexts.add(new Node(100));
		head.nexts.add(new Node(8));
		System.out.println(getMaxHuo(head));
	}
}
