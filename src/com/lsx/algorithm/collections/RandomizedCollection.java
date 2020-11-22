package com.lsx.algorithm.collections;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 * <p>
 * 注意: 允许出现重复元素。
 * <p>
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 * <p>
 * 示例：
 * // 初始化一个空的集合。
 * RandomizedCollection collection = new RandomizedCollection();
 * <p>
 * // 向集合中插入 1 。返回 true 表示集合不包含 1 。
 * collection.insert(1);
 * <p>
 * // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
 * collection.insert(1);
 * <p>
 * // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
 * collection.insert(2);
 * <p>
 * // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
 * collection.getRandom();
 * <p>
 * // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
 * collection.remove(1);
 * <p>
 * // getRandom 应有相同概率返回 1 和 2 。
 * collection.getRandom();
 */
public class RandomizedCollection {

    /**
     * 分析：
     * 想要O（1）获取元素，用ArrayList，基于索引去获取元素。
     * 插入元素时，需判断该值是否存在，但我们并不知道该值的索引。
     * 所以需要维护一个map，存<值，索引>,O(1)时间内获取到，且值可以重复，所以索引应该是一个集合Set。
     * <p>
     * list数组删除并不是O（1），只需要将删除的值跟数组最后一个交换位置，然后去除最后元素即可。
     * <p>
     * 随机获取元素，只需随机获取一个索引值即可，就能获得该索引对应的值。
     */

    Map<Integer, Set<Integer>> idx;
    List<Integer> nums;

    public RandomizedCollection() {
        idx = new HashMap<Integer, Set<Integer>>();
        nums = new ArrayList<Integer>();
    }

    public boolean insert(int val) {
        //添加该元素
        nums.add(val);
        //拿到该元素所属的索引集合
        Set<Integer> set = idx.getOrDefault(val, new HashSet<Integer>());
        //添加该索引到集合
        set.add(nums.size() - 1);
        idx.put(val, set);
        //返回是否添加之前已经存在该值了
        return set.size() == 1;
    }

    public boolean remove(int val) {
        //如果该值不存在索引集合,则不存在
        if (!idx.containsKey(val)) {
            return false;
        }
        //如果该值有多个，随机删除一个
        //返回该值对应索引的迭代器
        Iterator<Integer> it = idx.get(val).iterator();
        //第一次访问，返回序列的第一个元素,即val的第一个索引位置
        Integer i = it.next();
        //获取列表的最后一个元素
        Integer lastNum = nums.get(nums.size() - 1);
        //将最后一个移到i位置
        nums.set(i, lastNum);
        //对val值的索引集合移除i
        idx.get(val).remove(i);
        //对lastNum值的索引集合移除原索引
        idx.get(lastNum).remove(nums.size() - 1);
        //然后对lastNum的索引集合添加i
        if (i < nums.size() - 1) {
            idx.get(lastNum).add(i);
        }
        //如果val的索引集合为0，移除掉
        if (idx.get(val).size() == 0) {
            idx.remove(val);
        }
        //最后移除nums列表最后一个元素
        nums.remove(nums.size() - 1);
        return true;
    }

    public int getRandom() {
        //随机获取一个索引值
        return nums.get((int) (Math.random() * nums.size()));
    }
    
}
