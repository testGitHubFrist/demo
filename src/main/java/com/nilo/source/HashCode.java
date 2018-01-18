package com.nilo.source;

import org.apache.james.mime4j.codec.EncoderUtil.Encoding;

/**
 * Java中hashCode的作用
 * @author GaoQun
 *
 */
public class HashCode {

	/**
	 * hashCode 方法返回改对象的哈希码值。支持该方法是为哈希表提供一些优点，例如：java.util.Hashtable提供的哈希表。
	 * hashCode 的常规协定是：在Java应用程序执行期间，在同一个对象上多次调用hashCode方法时，必须一致地返回相同的整数，前提是对象上equals比较中所用的信息
	 * 没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。如果equals（object）方法，两个对象是相等的，那么在两个对象中的每个对象
	 * 调用hashcode方法都必须生成相同的整数结果。当equals方法被重写时，通常有必要重写 hashCode 方法，以维护 hashCode 方法的常规协定，该协定声明相等对象必须具有相等的哈希码。
	 * 
	 * 
	 * 以上这段官方文档的定义，我们可以抽出成以下几个关键点：
	 * 1、hashcode的存在主要是查找的快捷性，如hashtable、hashmap等，hashcode是用来在散列存储结构中确定对象的存储地址的。
	 * 2、如果两个对象相等，就是适用于equals(Object)方法，那么这两个对象的hashcode一定要相等
	 * 3、如果对象的equals方法被重写，那么对象的hashCode也尽量重写，并且产生hashCode使用的对象，一定要和equals方法中使用的一致，否则就会违反上面提到的第2点；
	 * 4、两个对象的hashCode相同，并不一定表示两个对象就相同，也就是不一定适用于equals(java.lang.Object) 方法，只能够说明这两个对象在散列存储结构中，如Hashtable，他们“存放在同一个篮子里”。
	 * 
	 * 1.hashcode是用来查找的，如果你学过数据结构就应该知道，在查找和排序这一章有  
		例如内存中有这样的位置  
		0  1  2  3  4  5  6  7    
		而我有个类，这个类有个字段叫ID,我要把这个类存放在以上8个位置之一，如果不用hashcode而任意存放，那么当查找时就需要到这八个位置里挨个去找，或者用二分法一类的算法。  
		但如果用hashcode那就会使效率提高很多。  
		我们这个类中有个字段叫ID,那么我们就定义我们的hashcode为ID％8，然后把我们的类存放在取得得余数那个位置。比如我们的ID为9，9除8的余数为1，那么我们就把该类存在1这个位置，
		如果ID是13，求得的余数是5，那么我们就把该类放在5这个位置。这样，以后在查找该类时就可以通过ID除 8求余数直接找到存放的位置了。
	 * 2.但是如果两个类有相同的hashcode怎么办那（我们假设上面的类的ID不是唯一的），例如9除以8和17除以8的余数都是1，那么这是不是合法的，回答是：可以这样。那么如何判断呢？在这个时候就需要定义 equals了。  
		也就是说，我们先通过 hashcode来判断两个类是否存放某个桶里，但这个桶里可能有很多类，那么我们就需要再通过 equals 来在这个桶里找到我们要的类。  
		那么。重写了equals()，为什么还要重写hashCode()呢？  
		想想，你要在一个桶里找东西，你必须先要找到这个桶啊，你不通过重写hashcode()来找到桶，光重写equals()有什么用啊  
	 */
	
//	Object obj=new Object();
	public static void main(String[] args) {
	}
	
	//HashMap、Hashtable、HashSet 和 ConcurrentHashMap 的比较
	  //http://www.54tianzhisheng.cn/2017/06/10/HashMap-Hashtable/
	
}
