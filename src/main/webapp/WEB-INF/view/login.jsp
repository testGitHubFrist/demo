<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/static/css/globel.css">
<link type="text/css" rel="stylesheet" href="${ctx}/static/css/main.css">
<script type="text/javascript" src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<title>登录</title>
</head>

<body>

	<!--[[container start-->
    <div class="login">
    	<div class="login_bg">
        	<div class="login_conainter">
            	<div class="bg_logo">
                	<img src="${ctx}/static/images/big_logo.png" />
                </div>
                <form action="${ctx}/login" method="post">  
                <div class="login_win">
                	<div class="label">
                    	<input type="text" placeholder="用户名" class="input_txt" required="required" id="user.loginAccount" name="user.loginAccount" />
                    </div>
                    <div class="label">
                    	<input type="password" placeholder="密码" class="input_txt" required="required" id="user.syspassword" name="user.syspassword"/>
                    </div>
                    <!--error-->
                    	<p class="error_txt">${error}</p> 
                    <!--error-->
                    <div class="login_btn">
                    	<input type="submit" value="登录" />
                    </div>
                </div>
                </form>  
            </div>
        </div>
    </div>
    <!--container end]]-->
</body>
</html>
