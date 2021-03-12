package com.lsx.algorithm.findkthelement;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * 题目归属：寻找第k个元素，排序
 * 题目：输入一组无序的数组，找到第2大的元素。不考虑重复元素。
 * 例子：[3,2,1,5,6,4] k=2 输出：5
 */
public class FindKth {

	public static void main(String[] args) {
		int[] nums = {3,2,1,5,6,7};
		int k = 3;
		//int kthLargest = findKthLargestByArray(nums, k);
		//int kthLargest = findKthLargestByMinHeap(nums, k);
		int kthLargest = findKthLargestByQuickSort(nums, k);
		System.out.println(kthLargest);
	}
	
	//1.直接用Arrays来排序，输出倒数第二个元素，时间复杂度为O(NlogN),空间复杂度为O(1)
	public static int findKthLargestByArray(int[] nums,int k) {
		Arrays.sort(nums);
		return nums[nums.length-k];
	}
	
	//2.利用PriorityQueue实现小顶堆，维持在k个，最后输出堆顶元素即为第k个元素，时间复杂度O(NlogK),空间复杂度为O(K)
	//技巧：寻找第k大的元素，实现小顶堆。寻找第k小个元素，实现大顶堆。
	/*传入一个自定义比较器就可以实现大顶堆了。
	 * PriorityQueue<>(new Comparator<Integer>() {
	 * 		@Override
    		public int compare(Integer o1, Integer o2) {
        		return o2.compareTo(o1);
    		}
		}
	 */
	public static int findKthLargestByMinHeap(int[] nums,int k) {
		/*PriorityQueue<Integer> queue = new PriorityQueue<>();
		
		//遍历将数组元素都添加进去
		for (int num : nums) {
			queue.add(num);//添加进去后会自动排序，最小的在堆顶
			//维持最小堆的元素个数在k
			if(queue.size() > k) {
				queue.poll();//输出堆顶元素，并删除
			}
		}
		//最后堆顶元素就是我们要求的第k个元素
		return queue.peek();*/
		
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for(int num : nums) {
			queue.add(num);
			if(queue.size() > k) {
				queue.poll();
			}
		}
		return queue.peek();
	}
	
	
	
	/*
	 * 3.使用快速排序：寻找第k个元素，时间复杂度为O(N)，空间复杂度为O(1),适合于一个无序数组
	 * 不稳定，可能出现O（n*n）
	 * 原理：使用快排，选择一个基准元素，将数组切分为两个子数组，返回基准元素所在的索引位置j
	 * 		若j>k,则第k个元素在左子数组中，继续遍历
	 * 		若j=k，则第k个元素为基准元素
	 * 		若j<k，则第k个元素在右子数组中，继续遍历
	 */
	public static int findKthLargestByQuickSort(int[] nums,int k) {
		//第k大的元素即为数组第n-k个元素
		k = nums.length-k;
		int begin = 0, end = nums.length-1;
		while(begin<end) {
			//调用快排，返回基准元素所在的索引
			int j = partition(nums,begin,end);
			if(j==k) {
				break;
			}else if(j < k) {
				begin = j+1;
			}else {
				end = j-1;
			}
		}
		return nums[k];
	}
	
	//快排，返回一次排序后基准元素所在位置
	private static int partition(int[] nums,int begin,int end) {
		//前后两个指针
		int i=begin,j=end+1;
		while(true) {
			//默认把数组第一个a[begin]挖出来做基准元素
			//i向后移动，直到找到a[i]大于基准指针
			while(nums[++i] < nums[begin] && i<end);
			//j向前移动，直到找到a[j]小于基准指针
			while(nums[--j] > nums[begin] && j>begin);
			if(i>=j) {
				break;
			}
			swap(nums,i,j);
		}
		//最后将基准元素与j位置进行交换
		swap(nums,begin,j);
		return j;
		
	}
	
	private static void swap(int[] nums,int i,int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
