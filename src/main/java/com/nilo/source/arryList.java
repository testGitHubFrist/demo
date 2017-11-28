package com.nilo.source;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * arraylist原理深究;底层是数组；新增删除比较慢，因为需要重新数据定位
 * @author 张善闯
 *
 * @param <AnyType>
 */
public class arryList<AnyType> implements Iterable<AnyType> {

	@Override
	public Iterator<AnyType> iterator() {
		return null;
	}

	private static final int DEFAULT_CAPACITY=10;//初始化
	
	private int theSize;//大小
	private AnyType[] theItems;
	
	
	public void arraList(){
		clear();
	}

	
	/**清空方法
	 * 
	 */
	public void clear() {
   
		theSize=0;
		ensureCapacity(DEFAULT_CAPACITY);
	}



	/**
	 * 数组扩容
	 * @param newCapacity
	 */
	public void ensureCapacity(int newCapacity) {
		
		if(newCapacity<theSize){
			return;
		}
		//创建新的数组，并赋值
		AnyType[] old=theItems;
		theItems=(AnyType[])new Object[newCapacity];
		for (int i = 0; i < size(); i++) {
			theItems[i]=old[i];
		}
		
	}
	
	public int size(){
		return theSize;
	}
	
	public boolean isEmpty(){
		return size()==0;
		
	}
	
	
	public void trimToSize(){
		ensureCapacity(size());
	}

	/**
	 * get方法
	 * @param idx
	 * @return
	 */
	public AnyType get(int idx){
		if(idx<0||idx>size()){
			throw new ArrayIndexOutOfBoundsException();
		}
		return theItems[idx];
	}
	
	/**
	 * set方法
	 * @param idx
	 * @param newVal
	 * @return
	 */
	public AnyType set(int idx ,AnyType newVal){
		if(idx<0||idx>size()){
			throw new ArrayIndexOutOfBoundsException();
		}
		AnyType old=theItems[idx];
		theItems[idx]=newVal;
		return old;
	}
	
	/**
	 * 新增元素
	 * @param x
	 * @return
	 */
	public boolean add(AnyType x){
		add(size(),x);
		return true;
	}
	
	
	public void add(int idx, AnyType x) {
		if(theItems.length==size())
			ensureCapacity(size()*2+1);
		for (int i = theSize; i < idx; i--) {
			theItems[i]=theItems[i-1];
		}
		theItems[idx]=x;
		theSize++;
	}
	
	/**
	 * 删除
	 * @param idx
	 * @return
	 */
	public AnyType remove(int idx){
		AnyType old=theItems[idx];
		for (int i = idx; i < size()-1; i++) {
			theItems[i]=theItems[i-1];
			
		}
		theSize--;
		return old;
	}


	public java.util.Iterator<AnyType> iterator1(){
		return new ArrayListIterator();
	}
	
	/**
	 * 迭代器
	 * @author 张善闯
	 *
	 */
	private class ArrayListIterator implements java.util.Iterator<AnyType>{

		private int current=0;
		@Override
		public boolean hasNext() {
			return current<size();
		}

		@Override
		public AnyType next() {
			if(!hasNext()){
				throw new java.util.NoSuchElementException();
			}
			return theItems[current++];
		}
		
		public void remove(){
			arryList.this.remove(current--);
		}
		
	}
	
	public static void main(String[] args) {
		ArrayList<String> list=new ArrayList<String>();
		list.add("1");
		System.out.println(list.set(0, "2"));;
	}
}
