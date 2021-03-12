package com.lsx.algorithm.findkthelement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 题目归属：寻找第k个出现频率最高的元素，桶排序
 * 题目：给定一个数组，和k，然后输出出现频率最高的k个元素。
 * 例子：[1,1,1,2,2,3]  k=2, 输出result=[1,2]
 * 利用的知识点：桶排序的工作原理：将一个数组中的数分配到有限数量的桶里面，再对每个桶进行排序。
 * 
 */
public class FindKthFrequency {

	public static void main(String[] args) {
		int[] nums = {1,1,1,2,2,3};
		int k = 2;
		List<Integer> topKForquent = topKForquent(nums, k);
		System.out.println(topKForquent);
	}
	
	/*
	 * 设置若干个桶，每个桶存储出现频率相同的数，桶的下标就是该数出现的频率，
	 * 即第i个桶中存储的数的出现频率为i。最后从后往前遍历桶，最先得到的k个数就是出现频率最多的k个。
	 */
	/*public static List<Integer> topKForquent(int[] nums,int k){
		//利用HashMap来统计数的出现频率
		Map<Integer,Integer> frequencyForNum = new HashMap<>();
		for (int num : nums) {
			//frequencyForNum.getOrDefault获取该key的值，若没有该key，默认为0
			frequencyForNum.put(num, frequencyForNum.getOrDefault(num, 0)+1);
		}
		//利用一个桶列表，来存放那些频率相同的数，并且桶的下标就是出现的频率。
		//下标对应下的是一个列表，列表中的数都是频率相同的
		List<Integer>[] buckets = new ArrayList[nums.length+1]; //是一个列表数组
		//遍历hashmap中的数，放进桶里
		for(int key : frequencyForNum.keySet()) {
			//取出该key的频率
			int frequency = frequencyForNum.get(key);
			//将该key放在频率下标的桶中
			if(buckets[frequency] == null) {
				//如果该频率的桶还没有数，则建立一个列表来放这些数
				buckets[frequency] = new ArrayList<>();
			}
			buckets[frequency].add(key);
		}
		
		//利用一个k大小的列表来存频率出现最多的k个数
		List<Integer> topK = new ArrayList<>();
		//从后往前遍历桶，输出k个
		for(int i=buckets.length-1; i>=0 && topK.size() < k; i--) {
			//i代表桶的下标 也就是频率
			if(buckets[i] == null) {
				continue;
			}
			//如果该频率下的数多于 k-topk.size ，即相同频率下的数太多，不够放，取k-topk.size个
			if(buckets[i].size() > (k-topK.size())) {
				topK.addAll(buckets[i].subList(0, k-topK.size())); //从0取到k-topk.size个
			}else {
				topK.addAll(buckets[i]); //全部取
			}
		}
		//返回topk
		return topK;
	}*/
	
	public static List<Integer> topKForquent(int[] nums,int k){
		Map<Integer,Integer> map = new HashMap<>();
		for(int num : nums) {
			map.put(num, map.getOrDefault(num, 0)+1);
		}
		int n = nums.length;
		List<Integer>[] buckets = new ArrayList[n+1];
		for(int key : map.keySet()) {
			int value = map.get(key);
			if(buckets[value] == null) {
				buckets[value] = new ArrayList<>();
			}
			buckets[value].add(key);
		}
		
		List<Integer> topK = new ArrayList<>();
		for(int i=buckets.length-1;i>=0 && topK.size() < k;i--) {
			if(buckets[i] == null) {
				continue;
			}
			if(buckets[i].size() > k-topK.size()) {
				topK.addAll(buckets[i].subList(0, k-topK.size()));
			}else {
				topK.addAll(buckets[i]);
			}
		}
		return topK;
	}
}
