<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>出错了</title>
		<style type="text/css">
			body{ margin:0; padding:0; background:#efefef; font-family:Arial; }
			div#mother{ margin:0 auto; width:943px; height:572px; position:relative; }
			div#errorBox{width:943px; height:572px; margin:auto; }
			div#errorText{ color:#39351e; padding:146px 0 0 446px }
			div#errorText p{ width:503px; font-size:14px; line-height:26px; }
			div.link{ /*background:#f90;*/ height:50px; width:145px; float:left; }
			div#home{ margin:20px 0 0 444px;}
			div#contact{ margin:20px 0 0 25px;}
			h1{ font-size:40px; margin-bottom:35px; }
		</style>
	</head>
	<body>
		<div id="mother">
			<div id="errorBox">
				<div id="errorText">
					<h1>Sorry..出错了！</h1>
					<h1>错误代码：500</h1>
					<p>
						请与管理员联系。
					</p>
					<h2>异常信息如下</h2>
					${ex.message}<br>
					${ex.cause}<br>
				</div>
				<a href="javascript:;" onClick="javascript:history.back(-1);">
					<div class="link" id="home">返回</div>
				</a>
			</div>
		</div>
	</body>
</html>