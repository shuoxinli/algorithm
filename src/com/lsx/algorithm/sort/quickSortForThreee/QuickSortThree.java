package com.lsx.algorithm.sort.quickSortForThreee;
/*
 * 题目归属：三向切分的快速排序，典型例子 荷兰国旗问题
 * 题目：荷兰国旗包含三种颜色：红、白、蓝。
 * 有三种颜色的球，算法的目标是将这三种球按颜色顺序正确地排列。
 * 它其实是三向切分快速排序的一种变种，在三向切分快速排序中，每次切分都将数组分成三个区间：
 * 小于切分元素、等于切分元素、大于切分元素，而该算法是将数组分成三个区间：等于红色、等于白色、等于蓝色。
 * 例子：输入[2,0,2,1,1,0] 输出[0,0,1,1,2,2]
 */
public class QuickSortThree {
	public static void main(String[] args) {
		int[] nums = {2,0,2,1,1,0};
		quickSortForThree(nums);
		for (int i : nums) {
			System.out.println(i);
		}
		
	}
	
	//三向切分快速排序
	public static void quickSortForThree(int[] nums) {
		//定义三个指针，分别为ls指向小于部分，i指向等于部分，gt指向大于部分
		/*int ls=-1,i=0,gt=nums.length;
		while(i<gt) {
			//这里三色已经确定为0，1，2，就不用再选基准元素了,默认以1为基准
			if(nums[i]==0) {
				//小于1 交换到前面小于部分
				swap(nums,++ls,i++);
			}else if(nums[i]==2) {
				//大于1 交换到后面大于部分
				swap(nums,i++,--gt);
			}else {
				//等于不变
				i++;
			}
		}*/
		
		int ls=-1,i=0,gt=nums.length;
		while(i<gt) {
			if(nums[i]==0) {
				swap(nums, ++ls, i++);
			}else if(nums[i]==2) {
				swap(nums,i++,--gt);
			}else {
				i++;
			}
		}
	}
	
	public static void swap(int[] nums,int i,int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
