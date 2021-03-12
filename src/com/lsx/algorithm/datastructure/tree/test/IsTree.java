package com.lsx.algorithm.datastructure.tree.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IsTree {

	//给定一个整数数组，判断是否是某个二叉搜索树的后序遍历序列
	public static boolean isPostTree(int[] num,int start,int end) {
		//遍历完该部分返回true
		if(start >= end) {
			return true;
		}
		
		//二叉搜索树的后序遍历有一定规律，最后一个为根节点，且数组分为两个连续的部分
		//前部分都小于根节点，后部分都大于根节点，然后，分别对前后部分递归
		int root = num[end];
		
		//遍历数组，找到前部分的结束位置
		int i;
		for(i=start;i<end;i++) {
			if(num[i] > root) {
				break;
			}
		}
		
		//遍历剩下的后部分，判断是否有不满足条件的，直接返回false
		int j;
		for(j=i;j<end;j++) {
			if(num[j] < root) {
				return false;
			}
		}
		//递归判断前后两部分
		return isPostTree(num,start,i-1) && isPostTree(num,i,end-1);
	}
	
	public static boolean VerifySquenceOfBST(int[] num) {
		if(num == null || num.length ==0)
			return false;
		return isPostTree(num, 0, num.length-1);
	}
	
	public static void main(String[] args) {
		int[] num = {5,7,6,9,11,10,8};
		System.out.println(VerifySquenceOfBST(num));
	}
}
