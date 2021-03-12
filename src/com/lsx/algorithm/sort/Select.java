package com.lsx.algorithm.sort;

/*
 * 选择排序：每次扫描整个待排的数组，找到最小的与第一个元素交换，第二小的与第二个元素交换。。。
 * 相比冒泡的好处：减少交换的次数，每一趟排序后，都至少有一个元素被放到它该在的位置。
 * 					判断可能是第n趟排序后的结果，找出至少有n个元素在它该在的位置即可。
 */
public class Select {

	//算法复杂度O(n*n)
	public static void selectionSort(int[] arr) {
		// 总共length-1趟排序
		for (int i = 0; i < arr.length - 1; i++) {
			// 标志每一趟要放的位置，先标志i为最小
			int minPoint = i;
			// 内循环：扫描i之后的未排好的数，找到最小的
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[minPoint] > arr[j]) {
					minPoint = j;
				}
			}
			// 扫描发现更小的，交换位置
			if (minPoint != i) {
				int temp = arr[i];
				arr[i] = arr[minPoint];
				arr[minPoint] = temp;
			}
		}

		// 输出排好次序
		for (int num : arr) {
			System.out.print(num + " ");
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {2,4,6,3,7,3};
		selectionSort(arr);
	}
}
