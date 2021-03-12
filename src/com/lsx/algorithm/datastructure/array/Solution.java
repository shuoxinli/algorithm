package com.lsx.algorithm.datastructure.array;

import java.util.Stack;
/*
 * 输入: [1,8,6,2,5,4,8,3,7]
	输出: 49
 */
public class Solution {
	
	//用单调栈解决还有一个问题：1 2 4 3 到4的时候，左边不是找最低为1，而是2，return 4
    public int maxArea(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int n = height.length;
        int maxArea = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            while(!stack.isEmpty() && height[stack.peek()] < height[i]){
                int p = stack.pop();
                int l = stack.peek();
                int area = Math.min(height[i],height[l])*(i-l);
                maxArea = Math.max(maxArea,area);
            }
            stack.push(i);
        }
        
        while(!stack.isEmpty()){
            int p = stack.pop();
            int l;
            if(stack.isEmpty()){
                l =0;
            }else{
                l = stack.peek();
            }
            int area = Math.min(height[n-1],height[l])*(n-1-l);
            maxArea = Math.max(maxArea,area);
        }
        return maxArea;
    }
    
    //用双指针来做，一个指向头，一个指向尾，不断计算围起来的面积，然后那个矮指向向前移动一格，重复。。
    public int getmaxArea(int[] height) {
    	int l = 0;
    	int r = height.length-1;
    	int maxArea = Integer.MIN_VALUE;
    	while(l<r) {
    		int area = Math.min(height[l], height[r])*(l-r);
    		maxArea = Math.max(maxArea, area);
    		if(height[l] <= height[r]) {
    			l++;
    		}else {
    			r--;
    		}
    	}
    	return maxArea;
    }
}