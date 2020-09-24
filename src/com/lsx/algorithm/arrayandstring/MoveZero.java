package com.lsx.algorithm.arrayandstring;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * 示例
 *  输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 */
public class MoveZero {

    // 保证相对顺序
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length ==0){
            return;
        }
        // 双指针
        int i=0; // i快指针
        int j=0; // j慢指针
        int len = nums.length;

        for (;i<len;i++){
            // 快指针不为0，跟慢指针交换元素，慢指针才向前
            if (nums[i]!=0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
     }


    //这个方法保证非零元素的绝对顺序
    public void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0){
            return;
        }
        int j = nums.length-1;
        int k = 0;
        int i = 0;
        while (i<j){
            // 从左往右，找到为0
            while(i<j && nums[i]!=0){
                // 对当前i元素进行插入排序，默认数组前部分有序
                k = i;
                int temp = nums[k];
                while((k-1)>=0 && nums[k-1]>temp){
                    nums[k] = nums[k-1];
                    k--;
                }
                nums[k]= temp;
                i++;
            }
            // 从右往左，找到不为0
            while(i<j && nums[j] ==0){
                j--;
            }
            // 交换i和j元素，并对j元素进行插入排序
            if(i<j){
                int temp = nums[j];
                nums[j] = nums[i];
                k = i;
                while((k-1)>=0 && nums[k-1]>temp){
                    nums[k] = nums[k-1];
                    k--;
                }
                nums[k]= temp;
                i++;
                j--;
            }
        }
    }
}
