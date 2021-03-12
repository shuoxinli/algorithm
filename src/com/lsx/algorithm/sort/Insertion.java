package com.lsx.algorithm.sort;

/*
 * 插入排序：在一组待排数组中，默认前i-1个都是排好序的，找到第i个相应的位置插入，使得前i个排好序，循环。。
 * 			特点：需要在前i-1个中查找第i个应该要插入的位置，可用二分查找。插入后，需要移动数组。
 * 算法复杂度为O(n*n)
 * 二分排序 = 二分插入排序
 * 
 * 改进：2-路插入排序：利用一个辅助的数组d，用first和last指针来实现首尾连接，大于last元素，插入其后，
 * 		小于first，插入到其前。last指向的是排好序的最大一个，first指向的是排好序的最小一个。
 * 		只是减少了移位次数而已。
 */
public class Insertion {

	public static void insertionSort(int[] arr) {
		int len = arr.length;

		// 外层循环，默认第1个是排好序的，从第二个开始
		for (int i = 1; i < len; i++) {
			// 待排元素
			int temp = arr[i];
			// 与待排元素比较的元素的下标
			int insertPoint = i - 1;

			// 内循环，找到要插入的位置
			while (insertPoint >= 0 && arr[insertPoint] > temp) {
				// 后移一位
				arr[insertPoint + 1] = arr[insertPoint];
				insertPoint--;
			}
			// 找到插入位置,插入第i个元素
			arr[insertPoint + 1] = temp;
		}

		// 输出排好次序
		for (int num : arr) {
			System.out.print(num + " ");
		}
	}
	
	//查找元素位置可用二分
	public static int binarySearch(int[]arr,int start,int end,int target) {
		while(start < end) {
			int mid = start+(end-start)/2;
			if(arr[mid] > target) {
				end = mid - 1;
			}else {
				start = mid + 1;
			}
		}
		return start;
	}

	public static void main(String[] args) {
		int[] arr = { 2, 4, 6, 3, 7, 3 };
		insertionSort(arr);
	}
}
