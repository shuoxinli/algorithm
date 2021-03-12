package com.lsx.algorithm.datastructure.tree;

/*
 * 二叉搜索树的封装类:特点是左小右大
 */
public class BinarySearchTree {

	private Node root; // 根节点

	public BinarySearchTree() {
		root = null;
	}

	// 通过关键字查找节点
	public Node find(int key) {
		Node cur = root;
		if (cur == null) {
			return null;
		}

		while (cur.key != key) {
			if (cur.key > key) {
				cur = cur.leftChild;
			} else {
				cur = cur.rightChild;
			}

			// 遍历到叶子节点还没有找到
			if (cur == null) {
				return null;
			}
		}
		return cur;
	}

	// 插入新节点,作为新的叶子节点
	public void insert(Node node) {
		if (root == null) {
			root = node;
		} else {
			Node cur = root;
			while (true) {
				if (cur.key > node.key) {
					// 如果当前节点的左节点为空，直接插入
					if (cur.leftChild == null) {
						cur.leftChild = node;
						return;
					}
					// 否则，搜索左子树
					cur = cur.leftChild;
				} else {
					// 包括相等的，也都插入到右子节点
					if (cur.rightChild == null) {
						cur.rightChild = node;
						return;
					}
					cur = cur.rightChild;
				}
			}
		}
	}

	// 删除指定节点，要用其后继节点顶上
	// 删除指定节点
	public boolean delete(Node node) {
		if (root == null) {
			return false; // 如果为空树，直接返回false
		}

		boolean isLeftChild = true; // 记录目标节点是否为父节点的左子节点
		Node cur = root; // 要删除的节点
		Node parent = null; // 要删除节点的父节点

		while (cur.key != node.key) { // 确定要删除节点和它的父节点
			parent = cur;
			if (node.key < cur.key) { // 目标节点小于当前节点，跳转左子节点
				cur = cur.leftChild;
			} else {// 目标节点大于当前节点，跳转右子节点
				isLeftChild = false;
				cur = cur.rightChild;
			}
			if (cur == null) {
				return false; // 没有找到要删除的节点
			}
		}
		if (cur.leftChild == null && cur.rightChild == null) { // 目标节点为叶子节点（无子节点）
			if (cur == root) { // 要删除的为根节点
				root = null;
			} else if (isLeftChild) {
				// 要删除的不是根节点，则该节点肯定有父节点，该节点删除后，需要将父节点指向它的引用置空
				parent.leftChild = null;
			} else {
				parent.rightChild = null;
			}
		} else if (cur.leftChild == null) { // 只有一个右子节点
			if (cur == root) {
				root = cur.rightChild;
			} else if (isLeftChild) {
				parent.leftChild = cur.rightChild;
			} else {
				parent.rightChild = cur.rightChild;
			}
		} else if (cur.rightChild == null) { // 只有一个左子节点
			if (cur == root) {
				root = cur.leftChild;
			} else if (isLeftChild) {
				parent.leftChild = cur.leftChild;
			} else {
				parent.rightChild = cur.leftChild;
			}
		} else { // 有两个子节点
			// 第一步要找到欲删除节点的后继节点
			Node successor = cur.rightChild;
			Node successorParent = null;
			while (successor.leftChild != null) {
				successorParent = successor;
				successor = successor.leftChild;
			}
			// 欲删除节点的右子节点就是它的后继，证明该后继无左子节点，则将以后继节点为根的子树上移即可
			if (successorParent == null) {
				if (cur == root) { // 要删除的为根节点，则将后继设置为根，且根的左子节点设置为欲删除节点的做左子节点
					root = successor;
					root.leftChild = cur.leftChild;
				} else if (isLeftChild) {
					parent.leftChild = successor;
					successor.leftChild = cur.leftChild;
				} else {
					parent.rightChild = successor;
					successor.leftChild = cur.leftChild;
				}
			} else { // 欲删除节点的后继不是它的右子节点
				successorParent.leftChild = successor.rightChild;
				successor.rightChild = cur.rightChild;
				if (cur == root) {
					root = successor;
					root.leftChild = cur.leftChild;
				} else if (isLeftChild) {
					parent.leftChild = successor;
					successor.leftChild = cur.leftChild;
				} else {
					parent.rightChild = successor;
					successor.leftChild = cur.leftChild;
				}
			}
		}
		return true;
	}

	// 前序遍历
	public void preorder(Node currentRoot) {
		if (currentRoot != null) {
			System.out.print(currentRoot.key + "\t");
			preorder(currentRoot.leftChild);
			preorder(currentRoot.rightChild);
		}
	}

	// 中序遍历，这三种遍历都用了迭代的思想
	public void inorder(Node currentRoot) {
		if (currentRoot != null) {
			inorder(currentRoot.leftChild); // 先对当前节点的左子树对进行中序遍历
			System.out.print(currentRoot.key + "\t"); // 然后访问当前节点
			inorder(currentRoot.rightChild); // 最后对当前节点的右子树对进行中序遍历
		}
	}

	// 后序遍历
	public void postorder(Node currentRoot) {
		if (currentRoot != null) {
			postorder(currentRoot.leftChild);
			postorder(currentRoot.rightChild);
			System.out.print(currentRoot.key + "\t");
		}
	}

	//用迭代方法获取指定节点的左子树和右子树的最大深度，取最大
	private int getDepth(Node currentNode,int initDeep) {
		//当前节点已经到达的深度
		int deep = initDeep;
		int leftDeep = initDeep; //存放左子树的深度
		int rightDeep = initDeep; //存放右子树的深度
		if(currentNode.leftChild!=null) {
			leftDeep = getDepth(currentNode.leftChild, deep+1);
		}
		if(currentNode.rightChild!=null) {
			rightDeep = getDepth(currentNode.rightChild, deep+1);
		}
		
		return Math.max(leftDeep, rightDeep);
	}
	
	//获取树的深度
	public int getTreeDepth() {
		if(root == null) {
			return 0;
		}
		return getDepth(root, 1);
	}
	
	//获取关键字最大的节点,找到最右节点
	public Node getMax() {
		if(isEmpty()) {
			return null;
		}
		Node cur = root;
		while(cur.rightChild!=null) {
			cur = cur.rightChild;
		}
		return cur;
	}
	
	//获取关键字最小的节点，找到最左节点
	public Node getMin() {
		if(isEmpty()) {
			return null;
		}
		Node cur = root;
		while(cur.leftChild!=null) {
			cur = cur.leftChild;
		}
		return cur;
	}
	
	public boolean isEmpty() {
		return root==null;
	}
	
	//判断是否为叶子节点
	public boolean isLeaf(Node node) {
		return (node.leftChild==null && node.rightChild==null);
	}
	
	public Node getRoot() {
		return root;
	}
	
}
