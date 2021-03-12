package com.lsx.algorithm.datastructure.queue;
/*
 * 优先级队列：数组实现，数据项按关键字的值排序，数据项插入的时候会按照顺序插入到合适的位置
 * 	数组的第一个元素永远是队尾，最后一个永远是队头，便于移除，如果第一个是队头，每次移除都要
 * 	移动数组其他元素。队头是优先级最高的。
 * 
 * 	设置一个基准点，元素到基准点的距离越近则优先级越高。
 */
public class PriorityQueue {
	private int[] queArray;
	private int maxSize;
	private int length;	//队列长度
	private int referencePoint; //基准点
	
	//构造方法
	public PriorityQueue(int maxSize,int referencePoint) {
		this.maxSize = maxSize;
		this.referencePoint = referencePoint;
		queArray = new int[maxSize];
		length=0;
	}
	
	//插入
	public void insert(int elem) throws Exception{
		if(isFull()) {
			throw new Exception("队列已满，不能插入。");
		}
		//如果队列为空，则插入到第一个位置
		if(length==0) {
			queArray[length++] = elem;
		}else {
			int i; //待插入元素的位置
			//从后往前遍历
			for(i=length;i>0;i--) {
				//待插入节点的优先级
				int dis = Math.abs(elem-referencePoint);
				//当前节点的优先级
				int curDis = Math.abs(queArray[i-1]-referencePoint);
				
				//将比插入元素优先级高的元素后移一位
				if(dis >= curDis) { //越小越高
					queArray[i] = queArray[i-1];
				}else {
					break;
				}
			}
			//插入元素
			queArray[i] = elem;
			length++;
		}
	}
	
	//移除
	public int remove() throws Exception{
		if(isEmpty()) {
			throw new Exception("队列为空，不能移除。");
		}
		int elem = queArray[--length];
		return elem;
	}
	
	//查看队头元素
	public int peek() throws Exception{
		if(isEmpty()) {
			throw new Exception("队列为空。");
		}
		return queArray[length-1];
	}
	
	//获取队列长度
	public int size() {
		return length;
	}
	
	//判断是否为空
	public boolean isEmpty() {
		return length==0;
	}
	
	//判断是否为满
	public boolean isFull() {
		return length==maxSize;
	}
}
