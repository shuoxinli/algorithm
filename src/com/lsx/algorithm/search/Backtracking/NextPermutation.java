package com.lsx.algorithm.search.Backtracking;
//寻找下一字典排列，如  1 5 3 4 2  --》 1 5 4 2 3
public class NextPermutation {

	public void nextPermutation(int[] nums) {
        int index1 = -1;
        int index2 = -1;
        
        //从后往前遍历，找到第一个nums[i-1] < nums[i]
        for(int i=nums.length-1;i>=1;i--) {
        	if(nums[i-1] < nums[i]) {
        		index1 = i-1;
        		index2 = i;
        		break;
        	}
        }
        
        if(index1 == -1) {
        	//说明遍历到头还没找到满足 i-1 < i的，直接反转整个数组
        	reverse(nums,0);
        }else {
        	//找到了，然后从index2 遍历尾部找到一个比index1最接近的比他大的数
        	//例子 8 3 7 6 5 4 1 找到4 与 3 交换，然后从index2开始反转后面的数组
        	int i;
        	for(i=index2;i<nums.length;i++) {
        		if(nums[i] <= nums[index1]) {
        			//相等不算
        			//找到交换index1和 i-1，然后反转index2以后的数组
        			swap(nums,index1,i-1);
        			reverse(nums,index2);
        			break;
        		}
        	}
        	 //防止找到最后没有找到比index1小的，直接交换最后一个
            if(i == nums.length){
                swap(nums,index1,i-1);
                reverse(nums,index2);
            }
        }
    }
	
	private void reverse(int[] nums,int start) {
		int i = start;
		int j = nums.length-1;
		while(i<=j) {
			swap(nums,i,j);
			i++;
			j--;
		}
	}
	
	private void swap(int[] nums,int i,int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
