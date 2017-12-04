package com.nilo.action;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nilo.security.AuthenticationToken;
import com.nilo.utils.DESUtils;

@Controller
public class UserAction implements ExecutorService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAction.class);
	
	@Value("${ds}")
	private String DESSalt;
	
	/**
	 * 初始化
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping({"/index","/"})
	public String index (Model model, HttpServletRequest request){
		return "login";
	}
	
	/**
	 * 登录
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/login")
	public void login(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("----------------begin------------------");
		String username = request.getParameter("user.loginAccount");
		String password = request.getParameter("user.syspassword");
		//TODO 密码需要解密
		AuthenticationToken token = new AuthenticationToken(username, DESUtils.encrypt(password, DESSalt), false, request.getRemoteHost());
		//根据用户名 密码 查找 用户的信息
		Subject subject = SecurityUtils.getSubject();
		
		if (subject != null) {
			subject.login(token);
			response.sendRedirect("test");
		}else{
			response.sendRedirect("unauthorized");
		}
		return;
	}
	
	/**
	 * 没权限
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/unauthorized")
	public String unauthorized (Model model, HttpServletRequest request){
		return "error/unauthorized";
	}
	

	/**
	 * 退出
	 */    
	@RequestMapping("/user/logout")
	public void logout(Model model, HttpServletRequest request, HttpServletResponse response){		
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		try {
			response.sendRedirect("login");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	@RequestMapping("/user/text")
	public String text (Model model, HttpServletRequest request){
		return "asyn-echarts";
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException  {
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		Future Future=threadPool.submit(new test());
		System.out.println(Future.get());
	}

	@Override
	public void execute(Runnable command) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Runnable> shutdownNow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isShutdown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Future<T> submit(Runnable task, T result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<?> submit(Runnable task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<Future<T>> invokeAll(
			Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
			throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks,
			long timeout, TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}
}
