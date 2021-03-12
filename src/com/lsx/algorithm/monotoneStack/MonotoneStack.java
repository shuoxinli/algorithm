package com.lsx.algorithm.monotoneStack;

import java.util.Stack;

/*
 * 单调栈：栈中元素严格按照栈底大--栈顶小存放，碰到不符合的，弹出栈顶，收集它的左大和右大。
 * 解决的问题：O（N）时间内，在一个数组中，找到每个数的左大和右大。
 */
public class MonotoneStack {

	/*
	 * 引子题：给出一组连续的高度数组，求他们中构成的最大矩阵面积。
	 */
	public static int getMaxRecFromBottom(int[] height) {
		/*if(height == null || height.length==0) {
			return 0;
		}
		int maxArea = 0;
		Stack<Integer> stack = new Stack<>();
		for(int i=0;i<height.length;i++) {
			//维护单调栈，栈底小--栈顶大
			while(!stack.isEmpty() && height[i] <= height[stack.peek()]) {
				//弹出，记录其左小和右小i
				int j = stack.pop();
				int k = stack.isEmpty() ? -1 : stack.peek(); //左小
				int curArea = (i-k-1)*height[j];
				maxArea = Math.max(maxArea, curArea);
			}
			stack.push(i);
		}
		//遍历完数组，自行弹出，可到达最右边界
		while(!stack.isEmpty()) {
			int j = stack.pop();
			int k = stack.isEmpty() ? -1 : stack.peek(); //左小
			int curArea = (height.length-k-1)*height[j];
			maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;*/
		
		if(height == null || height.length == 0) {
			return 0;
		}
		int maxArea =0;
		Stack<Integer> stack = new Stack<>();
		for(int i=0;i<height.length;i++) {
			//底小顶大，遇到一个更顶小的，弹出顶元素，记录其构成的最大面积
			while(!stack.isEmpty() && height[i] < height[stack.peek()]) {
				int j = stack.pop();
				int k = stack.isEmpty() ? -1 : stack.peek(); //当前j的左小,注意左小可能为空
				int curArea = (i-k-1)*height[j];
				maxArea = Math.max(maxArea, curArea);
			}
			stack.push(i);
		}
		
		//遍历完数组，直接弹出，可达最右边界
		while(!stack.isEmpty()) {
			int j = stack.pop();
			int k = stack.isEmpty() ? -1 : stack.peek();
			int curArea = (height.length-k-1)*height[j];
			maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;
	}
	
	/*
	 * 增强：给一个map,只有0，1，求其中全为1的矩形区域的最大。
	 * 例子：1 0 1 1
	 *     1 1 1 1
	 *     1 1 1 0    return 6
	 */
	public static int maxRecSize(int[][] map) {
		/*if(map == null || map.length == 0 || map[0].length ==0) {
			return 0;
		}
		int maxSize = 0;
		//存放每一行压缩后的数
		int[] height = new int[map[0].length];
		//遍历每一行
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				//压缩每一行，必须以该行的元素打底
				height[j] = map[i][j]==0 ? 0 : height[j]+1;
			}
			//求每一行的最大
			maxSize = Math.max(maxSize, getMaxRecFromBottom(height));
		}
		return maxSize;*/
		
		if(map == null || map.length == 0 || map[0].length == 0) {
			return 0;
		}
		int maxSize = 0;
		int[] height = new int[map[0].length];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				height[j] = map[i][j] == 0 ? 0 : height[j]+1;
			}
			maxSize = Math.max(maxSize, getMaxRecFromBottom(height));
		}
		return maxSize;
	}
	
	public static void main(String[] args) {
		int[] height = {4,3,2,5,6};
		int maxArea = getMaxRecFromBottom(height);
		System.out.println(maxArea);
		
		int[][] map = {
				{1,0,1,1},
				{1,1,1,1},
				{1,1,1,0}
		};
		int maxRecSize = maxRecSize(map);
		System.out.println(maxRecSize);
	}
}
