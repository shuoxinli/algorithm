package com.lsx.algorithm.divideandconquer;

import java.util.Random;

import javax.swing.plaf.synth.SynthSpinnerUI;

/*
 * 二叉搜索树：左小右大
 * 
 */
public class BinarySearchTree {
	
	//根节点
	public static Node root = null;

	//树的节点类,属性静态内部类
	public static class Node{
		Node par;
		Node left;
		Node right;
		int key;
		public Node(Node par, Node left, Node right, int key) {
			this.par = par;
			this.left = left;
			this.right = right;
			this.key = key;
		}	
	}
	
	
	//在合适的位置插入节点z，找到一个叶子节点插入z
	public static void insert(Node z) {
		//如果树为空，直接做根节点
		if(root == null) {
			root = z;
		}else {
			//y是来存节点z的父节点的
			Node y = null;
			Node x = root;
			//找到节点z的合适位置
			while(x!=null) {
				y = x;
				//使用key比较查找
				if(z.key < x.key) {
					x = x.left;
				}else {
					x = x.right;
				}
			}
			//y就是z的父节点
			z.par = y;
			if(z.key < y.key) {
				y.left = z;
			}else {
				y.right = z;
			}
		}
	}
	
	//中序遍历树
	public static void inorderTreeWalk(Node x) {
		if(x!=null) {
			inorderTreeWalk(x.left);
			System.out.println(x.key+" ");
			inorderTreeWalk(x.right);
		}
	}
	
	//查找指定关键字的节点
	public static Node search(int k) {
		Node x = root;
		while(x!=null && x.key!= k) {
			if(x.key < k) {
				x = x.right;
			}else {
				x = x.left;
			}
		}
		return x;
	}
	
	//查找关键字最小的节点，遍历左节点
	public static Node mininum(Node x) {
		while(x.left!=null) {
			x = x.left;
		}
		return x;
	}
	
	//查找关键字最大的节点，遍历右节点
	public static Node maxnum(Node x) {
		while(x.right!=null) {
			x = x.right;
		}
		return x;
	}
	
	//节点的后继节点(中序遍历)
	public static Node successor(Node x) {
		//如果x的右子树不为空，则x的后继节点为x右子树中具有最小关键字的节点
		if(x.right!=null) {
			return mininum(x.right);
		}else {
			//如果x的右子树为空，后继节点为x的最底层祖先，如果x是左子树，那后继节点就是其父节点，如果是右子树，继续向上找
			Node y = x.par;
			while(y!=null && x ==y.right) {
				x = y;
				y = y.par;
			}
			return y;
		}
	}
	
	//节点的先驱节点（中序遍历）
	public static Node predecessor(Node x) {
		if(x.left!=null) {
			return maxnum(x.left);
		}else {
			Node y = x.par;
			while(y!=null && x==y.left) {
				//哪一个结点把它作为右子树上第一个位置。
				x = y;
				y = y.par;
			}
			return y;
		}
	}
	
	/*
	 * 移动子树：用另一棵子树替换一棵子树，并成为其父节点的孩子节点
	 * u：被替换子树的根节点
	 * v：替换子树的根节点
	 */
	public static void transplant(Node u,Node v) {
		if(u.par==null) {
			root = v;
		}else if(u==u.par.left) {
			//u是其父节点的左节点
			u.par.left = v;
		}else {
			//u是其父节点的右节点
			u.par.right = v;
		}
		//指定v的父节点
		if(v!=null) {
			v.par = u.par;
		}
	}
	
	/*
	 * 删除指定的节点：z为待删除节点
	 */
	public static void delete(Node z) {
		//如果z最多只有一个孩子节点，则直接用孩子节点替换掉z
		if(z.left==null) {
			transplant(z, z.right);
		}else if(z.right == null) {
			transplant(z, z.left);
		}else {
			//如果z有两个节点以上，需要找到它的后继节点进行替换，还要保证替换后的树结构不变
			Node y = mininum(z.right);
			if(y.par!=z) {
				transplant(z, z.right);
				y.right = z.right;
				y.right.par = y;
			}
			transplant(z, y);;
			y.left = z.left;
			y.left.par = y;
		}
	}
	
	public static void main(String[] args){
        Random rand = new Random();
        // 结点数组
        Node[] node = new Node[10];
        int i = 0;
        System.out.println("生成二叉树结点并插入树中：");
        for(i = 0; i < node.length ;i++){
            node[i] = new Node(null, null, null, rand.nextInt(100) + 1);
            System.out.print(node[i].key+"\t");
            insert(node[i]);
        }
        // 中序遍历
        System.out.println("\n"+"中序遍历二叉搜索树：");
        inorderTreeWalk(root);
        // 查找指定结点
        Node x = search(node[5].key);
        System.out.println("\n"+"查找结果：");
        System.out.println("自身关键字："+x.key+"\t"+"父结点的关键字："+x.par.key);
        // 具有最小关键字的结点
        x = mininum(root);
        System.out.println("树中最小关键字："+x.key);
        // 具有最大关键字的结点
        x = maxnum(root);
        System.out.println("树中最大关键字："+x.key);
        // x的后继
        x = predecessor(node[5]);
        System.out.println("前驱的关键字："+x.key);
        // x的前驱
        x = successor(node[5]);
        System.out.println("后继的关键字："+x.key);     
        // 删除结点，并中序输出观看结果
        delete(node[5]);
        System.out.println("删除结点：");
        inorderTreeWalk(root);
    }
}
