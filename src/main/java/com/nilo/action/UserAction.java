package com.nilo.action;

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
public class UserAction {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAction.class);
	
	@Value("${ds}")
	private String DESSalt;
	
	
	@RequestMapping(value="/user/login")
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
			response.sendRedirect(request.getContextPath()+"/test");
			return;
		}
		response.sendRedirect(request.getContextPath()+"/unauthorized");
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
		return "/unauthorized";
	}
}
