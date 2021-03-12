package com.lsx.algorithm.sort;
/*
 * 快速排序：定义low和high两个指针，分别指向头和尾，选择第一个元素作为基准，先移动high，
 * 			直到找到比基准小的元素，填入基准位置，然后空出自身位置，移动low，找到比基准
 * 			大的元素，交换至上一个空出来的位置，然后空出自身位置，直到low=high，将基准
 * 			元素放到low指向的位置，完成一次快排。然后利用递归，对前后两部分分别继续使用快排。
 * 算法复杂度：利用分治分为前后两部分，为log2n，每次找到基准元素位置，需要n次，所以为O(n*log2n)
 * 	注意：当数据基本有序时，使用快排的效率最低，全部有序为最差情况为O(N*N).
 * 
 * 改进：基准元素的选择很大程度决定了该算法的优劣性，如果取到最大或最小值，效率将严重下降。
 * 		取到数组的中值是最好的，但是不想浪费时间去取中值，就可以选用”三项取中”，取第一项，中间项，
 * 		最后一项，然后取值三者的中值。
 */
public class Quick {
	
	private int[] arr;
	
	public Quick(int[] arr) {
		this.arr = arr;
	}
	
	//递归使用快排
	public void recursiveQuickSort(int low,int high) {
		if(low>=high) {
			return;    //只有一个元素，不用排序
		}else {
			int pivot = arr[low];  //默认使用第一个元素作为基准元素
			//调用一次排序，找到基准元素该在的位置
			int partition = partition(low,high,pivot);
			
			//一次快排后，递归对前后两部分分别继续快排
			recursiveQuickSort(low, partition-1);
			recursiveQuickSort(partition+1, high);
		}
	}
	
	//以基准为标准把数组划分为前后两部分，前小后大
	private int partition(int low,int high,int pivot) {
		while(low<high) {
			//先移动high指针，直到找到小于基准的元素
			while(low<high && arr[high]>=pivot) {
				high--;
			}
			//找到小于基准的元素，与low指向的元素交换位置
			swap(low,high);
			
			//移动low指针，直到找到大于基准的元素
			while(low<high && arr[low]<=pivot) {
				low++;
			}
			//将该元素与high指向的元素交换位置
			swap(low,high);
		}
		return low; //low就是一次快排后，基准该放的位置
	}
	
	//交换元素位置
	private void swap(int low,int high) {
		int temp = arr[low];
		arr[low] = arr[high];
		arr[high] = temp;
	}
	
	public static void main(String[] args) {
		int[] arr = {2,5,3,7,3};
		Quick quick = new Quick(arr);
		quick.recursiveQuickSort(0, arr.length-1);
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
	}
}
