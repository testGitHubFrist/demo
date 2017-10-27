package com.nilo.action;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroTest {
	private static final Logger logger = LoggerFactory.getLogger(ShiroTest.class);

	@Test
	public void testShiro(){
		try {
			//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager  
		    Factory<org.apache.shiro.mgt.SecurityManager> factory =  
		            new IniSecurityManagerFactory("classpath:config\\dev\\shiro.ini");  
		    //2、得到SecurityManager实例 并绑定给SecurityUtils  
		    org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();  
		    SecurityUtils.setSecurityManager(securityManager);  
		    //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）  
		    Subject subject = SecurityUtils.getSubject();  
		    UsernamePasswordToken token = new UsernamePasswordToken("zhang", "1223");  
		  
		    try {  
		        //4、登录，即身份验证  
		        subject.login(token);  
		        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录  
		    } catch (AuthenticationException e) {  
		        //5、身份验证失败  
		    	logger.info(e.getMessage());
		    }  
		  
		   
		  
		    //6、退出  
		    subject.logout();  
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
		}
		
	}
}
