package com.lsx.algorithm.sort;
/*
 * 希尔排序：基于插入排序，又叫缩小增量排序。
 * 区别于插入排序：
 * 		在插入排序中，如果一个很小的数在最右端，当它插入前面的有序数组时，需要移动大量数据。
 * 		
 * 		希尔排序加大了插入排序时元素之间的间隔，也就是将第1个和第1+n个，第1+2n个。。进行比较，
 *		然后把最小的交换到第1个，这样跨度比较交换。n为增量，每次希尔排序后，增量n要相应减少，
 *		再进行下一次希尔排序。
 * 
 * 增量生成的表达式：h=3*h+1
 * 第一个取不超过len的最近的h，之后不断递减
 * 
 * 比如 len=1000 1,4,13,40,12,121,364
 * 第一轮排序，应该选取的间隔为364，第二轮为121，第三轮为12……
 */
public class Shell {

	private int[] arr;
	
	public Shell(int[] arr) {
		this.arr = arr;
	}
	
	//希尔排序
	public void shellSort() {
		int len = arr.length;
		
		int h = 1;
		//确定第一轮的增量
		while(3*h+1<len) {
			h = 3*h+1;
		}
		
		while(h>0) {
			for(int i=0;i<h;i++) {
				//对间隔为h的元素进行插入排序
				shellInsertSort(i,h);
			}
			
			//下一次希尔排序的增量
			h=(h-1)/3;
		}
	}
	
	//跨度比较，并插入
	private void shellInsertSort(int beginIndex,int increment) {
		//跨度increment的下一个元素下标
		int targetIndex = beginIndex+increment;
		
		while(targetIndex < arr.length) {
			int temp = arr[targetIndex];
			
			//前一个元素的下标，跨度为increment
			int previousIndex = targetIndex - increment;
			//找到当前元素在前面有序数组的位置，并插入，跟插入排序一样。只不过插入排序的跨度为1
			while(previousIndex>=0 && arr[previousIndex] > temp) {
				//后移n位
				arr[previousIndex+increment] = arr[previousIndex];
				previousIndex = previousIndex - increment;
			}
			//插入
			arr[previousIndex+increment] = temp;
			
			targetIndex = targetIndex+increment; //进入跨度n的下一个元素的插入
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {2,5,1,9,3};
		Shell shell = new Shell(arr);
		shell.shellSort();
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]+" ");
		}
		
	}
}
