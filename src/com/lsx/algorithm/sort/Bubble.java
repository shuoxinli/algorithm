package com.lsx.algorithm.sort;
/*
 * 冒泡排序：从左到右，每次与相邻位置进行比较，交换位置，一次排序过后，就有一个数沉到尾部。
 * 时间复杂度都是O（n*n）
 */
public class Bubble {

	//算法复杂度为O(n*n)
	public static void bubbleSort(int[] arr) {
		//外层循环：每次循环就确定一个相对最大的元素，沉到尾部,length-1次
		for(int i=0;i<arr.length-1;i++) {
			//改进，设置交换变量，如果此次外循环没有进行交换，说明已排好序，后面的外循环都不用做了
			boolean exchange = false;
			//内层循环：每次外层循环需要交换的次数,有i个已经排好序了，剩下交换length-i个即可
			for(int j=1;j<arr.length-i;j++) {
				if(arr[j-1] > arr[j]) {
					int temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = temp;
					
					if(!exchange)
						exchange = true;
				}
			}
			if(!exchange)
				break;
		}
		//输出排好次序
		for(int num : arr) {
			System.out.print(num+" ");
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {2,4,6,3,7,3};
		bubbleSort(arr);
	}
}
