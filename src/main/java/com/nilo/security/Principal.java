/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.nilo.security;

import java.io.Serializable;

/**
 * 身份信息
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public class Principal implements Serializable {


	private static final long serialVersionUID = -2607760637362349876L;

	private String uid;

	private String loginAccount;
	
	private Integer roleId;

	/**
	 * 
	 * @param uid
	 * @param loginAccount
	 * @param roleId
	 */
	public Principal(String uid, String loginAccount, Integer roleId) {
		this.uid = uid;
		this.loginAccount = loginAccount;
		this.roleId = roleId;
	}

	

	public String getUid() {
		return uid;
	}



	public void setUid(String uid) {
		this.uid = uid;
	}



	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	@Override
	public String toString(){
		return loginAccount;
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}