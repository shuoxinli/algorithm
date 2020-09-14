package com.lsx.algorithm.arrayandstring;

/**
 * 寻找旋转排序数组中的最小值
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 你可以假设数组中不存在重复元素。
 *
 * 输入: [3,4,5,1,2]
 * 输出: 1
 */
public class FindArrayMin {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }
        int min = nums[0];
        int len = nums.length;
        for(int i=1;i<len;i++){
            min = nums[i] < min ? nums[i] : min;
        }
        return min;
    }
}
