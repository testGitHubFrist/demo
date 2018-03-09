package com.nilo.source.tree;

import java.util.ArrayList;
import java.util.List;

/**  
 * 博客：http://blog.csdn.net/fjssharpsword/article/details/51969991
 * 二叉查找树（bst）实现一个二叉查找树的功能，可以进行动态插入、删除关键字； 查询给定关键字、最小关键字、最大关键字；转换为有序列表(用于排序)
 * 

       1.所有非叶子结点至多拥有两个儿子（Left和Right）；

       2.所有结点存储一个关键字；

       3.非叶子结点的左指针指向小于其关键字的子树，右指针指向大于其关键字的子树；

       如：
 * @author GaoQun
 *
 */
public class BinarySearchTree {

	// 树的根结点
	private TreeNode root = null;

	// 遍历结点列表
	private List<TreeNode> nodelist = new ArrayList<TreeNode>();

	// 定义树结构
	private class TreeNode {
		private int key;
		private TreeNode leftChild;
		private TreeNode rightChild;
		private TreeNode parent;

		public TreeNode(int key, TreeNode leftChild, TreeNode rightChild,
				TreeNode parent) {
			this.key = key;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			this.parent = parent;
		}

		public int getKey() {
			return key;
		}

		public String toString() {
			String leftkey = (leftChild == null ? "" : String
					.valueOf(leftChild.key));
			String rightkey = (rightChild == null ? "" : String
					.valueOf(rightChild.key));
			return "(" + leftkey + " , " + key + " , " + rightkey + ")";
		}
	}

	/**
	 * isEmpty: 判断二叉查找树是否为空；若为空，返回 true ，否则返回 false
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		if (root == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * TreeEmpty: 对于某些二叉查找树操作(比如删除关键字)来说，若树为空，则抛出异常。
	 */
	public void TreeEmpty() throws Exception {
		if (isEmpty()) {
			throw new Exception("树为空!");
		}
	}

	/**
	 * 在二叉查找树中查询给定关键字  
	 * @param key
	 * @return
	 */
	public TreeNode search(int key){
		TreeNode pNode = root;    
		//循环终止条件遍历找到一个非空节点的值等于输入的值
		while (pNode != null && pNode.key != key) {    
            if (key < pNode.key) {    
            	//判断输入的值是与当前节点比较，如果小于当前节点的值，则遍历左子树（递归）
                pNode = pNode.leftChild;    
            } else {    
                pNode = pNode.rightChild;    
            }    
        }    
        return pNode; 
	}

	
	/**
	 * minElemNode: 获取二叉查找树中的最小关键字结点  ;二叉查找树的最小关键字结点 ，一直向左 ;最小节点肯定没有左子节点
	 * @param node
	 * @return
	 * @throws Exception 
	 */
	public TreeNode minElemNode(TreeNode node) throws Exception {
		if (node == null) {
			throw new Exception("该树为空");
		}
		TreeNode pNode = node;
		while (pNode.leftChild != null) {
			pNode = pNode.leftChild;
		}
		return pNode;
	}
	
	/**
	 * 获取二叉查找树中的最大关键字结点;二叉查找树的最大关键字结点 ，一直向右 ；做大节点肯定没有右自节点
	 * @param node
	 * @return
	 * @throws Exception
	 */
	public TreeNode maxElemNode(TreeNode node) throws Exception{
		if (node == null) {
			throw new Exception("该树为空");
		}
		
		TreeNode pNode = node;
		while (pNode.rightChild != null) {
			pNode = pNode.rightChild;
		}
		return pNode;
	}
	
	/**
	 *               10
	 *             /     \
	 *            8      20
	 *          /  \     / \
	 *         5   9    11  30
	 * 
	 * 获取给定结点在中序遍历顺序下的后继结点；中序遍历结果是按照从小到大顺序排列 
	 * @param node  给定树中的结点  
	 * @return 若该结点存在中序遍历顺序下的后继结点，则返回其后继结点；否则返回 null  ；思想中序遍历的顺序为：左中右，所以该方法中节点存在右节点，则根据中序遍历的规则，找到右子树最小的节点
	 *           ；不存在右节点时，则找上上一个节点
	 * @throws Exception
	 */
	public TreeNode successor(TreeNode node) throws Exception {
		if (node == null) {
			return null;
		}
		//TODO 总结其原理
		// 若该结点的右子树不为空，则其后继结点就是右子树中的最小关键字结点 （例如：遍历8这个节点，首先中序遍历树：5，8，9，10，11，20，30）
		if (node.rightChild != null) {
			return minElemNode(node.rightChild);
		}

		// 若该结点右子树为空  （例如：遍历9这个节点，首先中序遍历树：5，8，9，10，11，20，30）如果该节点的父结点的左孩子是该节点，那么该父结点即为所求，否则继续向上找。
		TreeNode parentNode = node.parent;//20
		while (parentNode != null && node == parentNode.rightChild) {
			node = parentNode;
			parentNode = parentNode.parent;
		}
		return parentNode;
	}
	
	/**
	 *                10
	 *             /     \
	 *            8      20
	 *          /  \     / \
	 *         5   9    11  30
	 *         首先中序遍历树：5，8，9，10，11，20，30）
	 *  获取给定结点在中序遍历顺序下的前趋结点  
	 * @param node 给定树中的结点
	 * @return 若该结点存在中序遍历顺序下的前趋结点，则返回其前趋结点；否则返回 null  
	 * @throws Exception
	 */
	public TreeNode precessor(TreeNode node) throws Exception {
		if (node == null) {
			return null;
		}
		//TODO 总结其原理
		// 若该结点的左子树不为空，则其前趋结点就是左子树中的最大关键字结点
		if (node.leftChild != null) {
			return maxElemNode(node.leftChild);
		}
		// 若该结点左子树为空    
		TreeNode parentNode = node.parent;
		while (parentNode != null && node == parentNode.leftChild) {
			node = parentNode;
			parentNode = parentNode.parent;
		}
		return parentNode;
	}
	
	/**
	                 10
	 *             /     \
	 *            8      20
	 *          /  \     / \
	 *         5   9    11  30
	 * insert: 将给定关键字插入到二叉查找树中
	 * 插入后要调整二叉查找树左小右大结构 
	 * @param key
	 */
	public void insert(int key) {
		TreeNode parentNode = null;
		TreeNode newNode = new TreeNode(key, null, null, null);
		TreeNode pNode = root;
		// 判断根节点是否为空
		if (root == null) {
			root = newNode;
			return;
		}
        //为新增数据查找父节点  
		//TODO 总结其原理 找到最相近叶子节点的值；使新值成为叶子的子节点
		while (pNode != null) {
			parentNode = pNode;
			if (key < pNode.key) {
				pNode = pNode.leftChild;
			} else if (key > pNode.key) {
				pNode = pNode.rightChild;
			} else {
				// 树中已存在匹配给定关键字的结点，则什么都不做直接返回
				return;
			}
		}
		//判断新增的值是父节点的哪个子树
		if (key < parentNode.key) {
			parentNode.leftChild = newNode;
			newNode.parent = parentNode;
		} else {
			parentNode.rightChild = newNode;
			newNode.parent = parentNode;
		}
		//例如插入新值6
	}
	
	/**
	 * delete: 从二叉查找树中删除匹配给定关键字相应的树结点  
	 * @param key
	 * @throws Exception
	 */
	public void delete(int key) throws Exception {
		TreeNode pNode = search(key);
		if (pNode == null) {
			throw new Exception("树中不存在要删除的关键字!");
		}
		delete(pNode);
	}

	/**
	 * 从二叉查找树中删除给定的结点. 
	 * @param pNode 要删除的结点  前置条件： 给定结点在二叉查找树中已经存在
	 * 
	 * 删除后要调整二叉查找树，满足左小右大结构 
	 * 删除分四种情况：
	 * 该结点既无左孩子结点，也无右孩子结点
	 * 该结点左孩子结点为空，右孩子结点非空 
	 * 该结点左孩子结点非空，右孩子结点为空
	 * 该结点左右孩子结点均非空  
	 * 
	 *                10
	 *             /     \
	 *            8      20
	 *          /  \     / \
	 *         5   9    11  30
	 * @throws Exception
	 */
	private void delete(TreeNode pNode) throws Exception {
		if (pNode == null) {
			return;
		}
		// 该结点既无左孩子结点，也无右孩子结点
		if(pNode.leftChild==null && pNode.rightChild==null){
			TreeNode parentNode=pNode.parent;
			if(pNode==parentNode.leftChild){
				parentNode.leftChild=null;
			}else{
				parentNode.rightChild=null;
			}
			pNode=null;
			return;
		}
		// 该结点左孩子结点为空，右孩子结点非空
		if(pNode.leftChild==null && pNode.rightChild!=null){
			TreeNode parentNode=pNode.parent;
			TreeNode rightNode=pNode.rightChild;
			if(pNode==parentNode.leftChild){
				rightNode.parent=parentNode;
				parentNode.leftChild=rightNode;
			}else{
				rightNode.parent=parentNode;
				parentNode.rightChild=rightNode;
			}
			pNode=null;
			return;
		}
		// 该结点左孩子结点非空，右孩子结点为空
		if(pNode.leftChild!=null && pNode.rightChild==null){
			TreeNode parentNode=pNode.parent;
			TreeNode leftNode=pNode.leftChild;
			if(pNode==parentNode.leftChild){
				leftNode.parent=parentNode;
				parentNode.leftChild=leftNode;
			}else{
				leftNode.parent=parentNode;
				parentNode.rightChild=leftNode;
			}
			pNode=null;
			return;
		}
		// 该结点左右孩子结点均非空  ；递归
		if(pNode.leftChild != null && pNode.rightChild != null){
            TreeNode successorNode = successor(pNode);    
            pNode.key = successorNode.key;    
            delete(successorNode);        
        } 
	}
	
	
}
