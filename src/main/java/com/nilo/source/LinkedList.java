package com.nilo.source;

import java.util.Iterator;

/**
 * LinkedList原理深究
 * @author 张善闯
 *
 */
public class LinkedList<AnyType> implements Iterable<AnyType> {

	private int theSize;
	private int modCount=0;
	private Node<AnyType> benginMarker;
	private Node<AnyType> endMarker;
	
	public LinkedList(){
		clear();
	}
	
	/**
	 * 清除
	 */
	public void clear() {

		benginMarker=new Node<AnyType>(null, null, null);
		endMarker=new Node<AnyType>(null, benginMarker, null);
		theSize=0;
		modCount++;
	}

	/**
	 * 大小
	 * @return
	 */
	public int size(){
		return theSize;
	} 
	
	/**
	 * 判断是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return size()==0;
	}
	
	public boolean add(AnyType x){
		add(size(),x);
		return true;
	}
	
	
	public void add(int idx, AnyType x) {
		addBefore(getNode(idx),x);
	}


	public void addBefore(Node<AnyType> p, AnyType x) {
		Node<AnyType> newNode=new Node<AnyType>(x, p, p.prev);
		newNode.prev.next=newNode;
		p.prev=newNode;
		theSize++;
		modCount++;
	}

	private Node<AnyType> getNode(int idx) {
		Node<AnyType> p;
		if(idx<0||idx>size()){
			throw new IndexOutOfBoundsException();
		}
		if(idx<size()/2){
			p=benginMarker.next;
			for (int i = 0; i < idx; i++) {
				p=p.next;
				
			}
		}else{
			p=endMarker;
			for (int i = size(); i >idx; i--) {
              p=p.prev;
			}
		}
		return p;
	}

	public AnyType get(int idx){
		return getNode(idx).data;
		
	}

	public AnyType set(int idx,AnyType newVal){
		Node<AnyType>p=getNode(idx);
		AnyType old=p.data;
		p.data=newVal;
		return old;
	}
	
	
	private static class Node<AnyType>{
		private AnyType data;
		private Node<AnyType> prev;
		private Node<AnyType> next;
		public Node(AnyType d, Node<AnyType> p,Node<AnyType> n ){
			data=d;
			prev=p;
			next=n;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public Iterator<AnyType> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
