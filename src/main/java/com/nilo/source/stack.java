package com.nilo.source;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * 栈是先进后出；运用这一原理实现计算器；因为涉及到符号运算;先把第一个压进栈，偶数位都是运算符号，所以根据符号计算后一项是正还是负数后压进栈，最后顺序取栈的数求和。
 * @author 张善闯
 *
 */
/**
 * 内部类
 * @author 张善闯
 *
 */
 class MyException extends Exception{
	public MyException() {
		super();
	}
	public MyException(String message) {
		super(message);
	}
}

 class SwingConsole{
	public static void run(final JFrame f,final int width,final int height){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				f.setTitle(f.getClass().getSimpleName());
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(width,height);
				f.setVisible(true);
			}
		});
	}
}


public class stack extends JFrame {
	private JTextField textField;				//输入文本框
	private String input;						//结果
	private String operator;					//操作符
		
	public  stack() {
		input = "";
		operator = "";
		
		Container container = this.getContentPane();
		JPanel panel = new JPanel();
		textField = new JTextField(30);
		textField.setEditable(false);						//文本框禁止编辑
		textField.setHorizontalAlignment(JTextField.LEFT);
		//textField.setBounds(100, 100, 20, 20);			//在容器布局为空情况下生效
		textField.setPreferredSize(new Dimension(200,30));
		container.add(textField, BorderLayout.NORTH);
		
		String[] name= {"7","8","9","+","4","5","6","-","1","2","3","*","0","C","=","/"};
		
		panel.setLayout(new GridLayout(4,4,1,1));
		
		for(int i=0;i<name.length;i++) {
			JButton button = new JButton(name[i]);
			button.addActionListener(new MyActionListener());
			panel.add(button);
		}
		container.add(panel,BorderLayout.CENTER);
	}
	
	class MyActionListener implements ActionListener{			//内部类实现按钮响应

		@Override
		public void actionPerformed(ActionEvent e) {
			int cnt=0;
			String actionCommand = e.getActionCommand();			//获取按钮上的字符串
			if(actionCommand.equals("+") || actionCommand.equals("-") || actionCommand.equals("*")
					|| actionCommand.equals("/")) {
				input += " " + actionCommand + " ";
			}
			else if(actionCommand.equals("C")) {					//清除输入
				input = "";
			} 
			else if(actionCommand.equals("=")) {					//按下等号
				try {
					input+= "="+calculate(input);
				} catch (MyException e1) {
					if(e1.getMessage().equals("Infinity"))
						input+= "=" + e1.getMessage();
					else
						input = e1.getMessage();
				}
				textField.setText(input);
				input="";
				cnt = 1;
			}
			else
				input += actionCommand;							//按下数字
			
			if(cnt == 0)
				textField.setText(input);
		}
	}
	

private String calculate(String input) throws MyException{				//计算函数
	String[] comput = input.split(" ");					
	Stack<Double> stack = new Stack<Double>();
	Double m = Double.parseDouble(comput[0]);
	stack.push(m);										//第一个操作数入栈
	
	for(int i = 1; i < comput.length; i++) {
		if(i%2==1) {				
			if(comput[i].equals("+"))
				stack.push(Double.parseDouble(comput[i+1]));
			if(comput[i].equals("-"))
				stack.push(-Double.parseDouble(comput[i+1]));
			if(comput[i].equals("*")) {					//将前一个数出栈做乘法再入栈
				Double d = stack.peek();				//取栈顶元素
				stack.pop();
				stack.push(d*Double.parseDouble(comput[i+1]));
			}
			if(comput[i].equals("/")) {					//将前一个数出栈做乘法再入栈
				 double help = Double.parseDouble(comput[i+1]);  
				 if(help == 0)
					 throw new MyException("Infinity");			//不会继续执行该函数
				 double d = stack.peek(); 
				 stack.pop(); 
				 stack.push(d/help);  
			}
		}
	}
	
	double d = 0d;
	
	while(!stack.isEmpty()) {			//求和
		d += stack.peek();
		stack.pop();
	}
	
	String result = String.valueOf(d);
	return result;
}
public static void main(String[] args) {
	SwingConsole.run(new stack(), 250, 300);
}
}
