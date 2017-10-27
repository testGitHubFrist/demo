/**
 * 
 */
package com.nilo.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.nilo.dao.UserDao;
import com.nilo.model.User;

/**
 * @author zhangshanchaung
 *
 */
public class AuthenticationRealm extends AuthorizingRealm {

	@Autowired UserDao userDao;
	/**
	 * 获取认证信息
	 * 
	 * @param token 令牌
	 * @return 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) {
		AuthenticationToken authenticationToken = (AuthenticationToken) token;
		String username = authenticationToken.getUsername();
		String password = new String(authenticationToken.getPassword());
		String ip = authenticationToken.getHost();
		String l="";
		if (username != null && password != null) {
			
			try {
				User user=userDao.queryByName(username);
				if (user == null) {
					throw new Exception("NOACCOUNT");
				}
				if(!user.getPassword().equals(password)){
					throw new Exception("ERRORPSD");
				}
				return new SimpleAuthenticationInfo(new Principal(user.getUid(),username, user.getRole_id()), password, getName());
			} catch (Exception e) {
				l=e.getMessage();
				if(!e.getMessage().equals("ERRORPSD")&&!e.getMessage().equals("NOACCOUNT"))
				e.printStackTrace();
			}
		}
		throw new UnknownAccountException(l);	    
	}

	/**
	 * 获取授权信息
	 * 
	 * @param principals
	 *        principals
	 * @return 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
		String username = principal.getLoginAccount(); 
		if (username != null) {
			List<String> authorities = new ArrayList<String>();//查询权限
			authorities.add("admin:user");
			if (authorities != null) {
				SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
				authorizationInfo.addStringPermissions(authorities);
				return authorizationInfo;
			}
		}
		return null;
	}
}
