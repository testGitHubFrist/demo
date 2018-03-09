package com.nilo.source.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;





public class BinaryTree {

	/**
	 * 二叉树（Binary Tree）是n（n≥0）个结点的有限集合，该集合或者为空集（称为空二叉树），或者由一个根结点和两棵互不相交的、
	 * 分别称为根结点的左子树和右子树的二叉树组成。
	 * 
	 * 二叉树特点： 1.每个结点最多有两棵子树，所以二叉树中不存在度大于2的结点。没有子树或者有一棵子树是可以的，最多有两棵子树。
	 * 2.左子树和右子树是有顺序的，次序不能颠倒。 3.即使树中某结点只有一棵子树，也要区分它是左子树还是右子树。
	 * 
	 * 在计算机科学中，二叉树是每个节点最多有两个子树的树结构。通常子树被称作“左子树”（left subtree）和“右子树”（right
	 * subtree）
	 * 
	 * 特殊二叉树 斜树
	 * 所有的结点都只有左子树的二叉树叫左斜树。所有的结点都是只有右子树的二叉树叫右斜树。这二者统称为斜树。斜树有明显特点，每一层都只有一个结点
	 * ，结点的个数和二叉树的深度相同。斜树和线性表结构一样，线性表结构是树的一种特殊表现形式。
	 * 
	 * 满二叉树 在一棵二叉树中，如果所有分支结点都存在左子树和右子树，并且所有叶子都在同一层上，这样的二叉树称为满二叉树。单是每个节点都存在左右子树，
	 * 不能算是满n二叉树
	 * ，还必须要所有的叶子结点都在同一层上，这样就做到了整棵树的平衡。所以，满二叉树的特点是：1.叶子只能出现在最下一层，出现在其他层就不能达到平衡
	 * ；2.非叶子结点的度一定是2；3.在同样深度的二叉树中，满二叉树的结点最多，叶子数最多。
	 * 
	 * 完全二叉树 对一棵具有n个结点的二叉树 按层序编号
	 * ，如果编号为i（1≤i≤n）的结点与同样深度的满二叉树中编号为i的结点在二叉树中位置完全相同，则这颗二叉树称为完全二叉树。
	 * 
	 * 完全二叉树的特性： （1）叶子结点只能出现在最下两层。 （2）最下层的叶子一定集中在左部连续位置。
	 * （3）倒数第二层，若有叶子结点，一定都在右部连续位置。 （4）如果结点度为1，则该结点只有左孩子，即不存在只有右子树的情况。
	 * （5）同样结点数的二叉树，完全二叉树的深度最小。
	 * 
	 * 性质一：在二叉树的第i层上至多有 2i−1 个结点（i≥1）。—–归纳法
	 * 
	 * 性质二：深度为k的二叉树至多有 2k−1 个结点（k≥1）。—–归纳法
	 * 
	 * 性质三：对任何一棵二叉树T，如果其终端结点数为 n0，度为2的结点数为 n2，则 n0=n2+1。
	 * 
	 * 设 n1 为度是1的结点数，那么T结点总数 n=n0+n1+n2
	 * 。换个角度，数数连接连线，因为根结点只有分支出去，没有分支进入，所以分支线总数为结点总数减去1，分支线总数为 n−1=n1+2n2
	 * ，两个式子相减得到 n0=n2+1。
	 * 
	 * 性质四：具有n个结点的完全二叉树的深度为 |log2n|+1（其中|x|表示不大于x的最大整数）。
	 * 
	 * 性质五：如果对一棵有n个结点的完全二叉树（深度为 |log2n|+1）的结点按层序编号（从第1层到第
	 * |log2n|+1层，每层从左到右），对任一结点i（1≤i≤n）有： 1.如果 i=1，则结点i是二叉树的根，无双亲；如果
	 * i>1，则其双亲是结点|i/2|。 2.如果2i>n，则结点i无左孩子（结点i为叶子结点）；否则其左孩子是结点2i。
	 * 3.如果2i+1>n，则结点i无右孩子；否则其右孩子是结点2i+1。
	 */

	private TreeNode root;// 根节点
	

	/**
	 * 构造树的节点
	 * 
	 */
	private static class TreeNode<T> {
		T val;
		TreeNode<T> leftChild;
		TreeNode<T> rightChild;

		public TreeNode(T val) {
			this.val = val;
		}
	}

	/**
	 * 创建二叉树
	 *      1 
	 *     / \ 
	 *    2   3
	 *   / \  / \
	 *  #   4 5  #
	 *  
	 *  先序12#435# 集合按照先序遍历逆向生成二叉树
	 *  12#435###
	 * @param <T>
	 */
	public <T> TreeNode<T> CreateBinaryTree(int size,ArrayList dataList) {
		if(dataList.size()==0){
			return null;
		}
		T data=(T) dataList.get(0);
		TreeNode<T> node;
		int index=size-dataList.size();
		if("#".equals(data)){
			node=null;
			dataList.remove(0);
			return node;
		}
		node=new TreeNode<T>(data);
		//判断根节点
		if(index==0){
			root=node;
		}
		//移除已用的元素
		dataList.remove(0);
		//递归
		node.leftChild=CreateBinaryTree(++index, dataList);
		
		node.rightChild=CreateBinaryTree(++index, dataList);
		
		return node;
	}
	
	/**
	 * 求二叉树的深度（高度） 递归解法： O(n) （1）如果二叉树为空，二叉树的深度为0 （2）如果二叉树不为空，二叉树的深度 =
	 * max(左子树深度， 右子树深度) + 1
	 */
	public  int getDepthRec(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int leftDepth = getDepthRec(root.leftChild);
		int rightDepth = getDepthRec(root.rightChild);
		return Math.max(leftDepth, rightDepth) + 1;
	}
	
	
	
	/**
	 * 先序遍历：节点，左节点，右节点
	 * @param root
	 */
	public  <T> void preorderTraversalRec(TreeNode<T> root) {
		if (root == null) {
			return;
		}
		System.out.print(" "+root.val + " ");
		preorderTraversalRec(root.leftChild);
		preorderTraversalRec(root.rightChild);
	}
	
	/**
	 * 求二叉树中的节点个数递归解法： O(n) （1）如果二叉树为空，节点个数为0 （2）如果二叉树不为空，二叉树节点个数 = 左子树节点个数 +
	 * 右子树节点个数 + 1
	 */
	public  int getNodeNumRec(TreeNode root) {
		if (root == null) {
			return 0;
		} else {
			return getNodeNumRec(root.leftChild) + getNodeNumRec(root.rightChild) + 1;
		}
	}
	
	/**
	 * 求二叉树中的节点个数迭代解法O(n)：基本思想同LevelOrderTraversal，
	 * 即用一个Queue，在Java里面可以用LinkedList来模拟
	 */
	
	public  int getNodeNum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int count = 1;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode cur = queue.remove(); // 从队头位置移除
			if (cur.leftChild != null) { // 如果有左孩子，加到队尾
				queue.add(cur.leftChild);
				count++;
			}
			if (cur.rightChild != null) { // 如果有右孩子，加到队尾
				queue.add(cur.rightChild);
				count++;
			}
		}

		return count;
	}
	
	public  static void main(String[] args) {
//		String[] str=new String[]{"1","2","#","4","3","5","#"};
//		ArrayList<String> datalist=new ArrayList<String>();
//		for (String string : str) {
//			datalist.add(string);
//		}
		TreeNode<String> t1=new TreeNode<String>("1");
		TreeNode<String> t2=new TreeNode<String>("2");
		TreeNode<String> t3=new TreeNode<String>("3");
		TreeNode<String> t4=new TreeNode<String>("4");
		TreeNode<String> t5=new TreeNode<String>("5");
		t1.leftChild=t2;
		t1.rightChild=t3;
		t2.rightChild=t4;
		t3.leftChild=t5;
		
		BinaryTree bt=new BinaryTree();
//		TreeNode<String> tree=bt.CreateBinaryTree(datalist.size(), datalist);
//		bt.preorderTraversalRec(tree);
		bt.preorderTraversalRec(t1);
//		System.out.println("树的深度："+bt.getDepthRec(tree));
		System.out.println("t1树的深度："+bt.getDepthRec(t1));
		System.out.println("t1树的叶子个数："+bt.getNodeNumRec(t1));
		
		/**
	        1 
	 *     / \ 
	 *    2   3
	 *   / \  / \
	 *  #   4 5  #
		 * 或者
		 *     1
		 *    / 
		 *   2
		 *   \
		 *    4
		 *    /
		 *    3
		 *    /
		 *    5
		 *    因为没有指定父节点
		 */
	}
}
