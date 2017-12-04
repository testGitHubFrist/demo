<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link type="text/css" rel="stylesheet" href="${ctx}/static/css/globel.css">
<link type="text/css" rel="stylesheet" href="${ctx}/static/css/main.css">
<!-- 引入 ECharts 文件 -->
<script src="${ctx}/static/js/echarts.common.min.js"></script>
<style type="text/css">
.echarts_css{position: absolute;left: 30%; top: 50%;width:600px;height:400px;margin-left:-100px;margin-top:-50px;}
.table-d table{ background:#acd6ff} 
.table-d table td{ background:#FFF;} 
</style>
<title>数据库管理</title>
</head>
<body>
<!--[[header start-->
	<header class="header">
    	<div class="warp_auto flex">
        	<div class="logo">
            	<img src="${ctx}/static/images/logo.png" />
            </div>
            <div class="into">
                <a id="logout"  href="${ctx}/user/logout">
                	<i class="ico-close"></i>
                </a>
            </div>
        </div>
    </header>
<!--header end]]-->
<div class="main">
   <!--[[left start-->
    	<div class="leftbar">
        	<nav class="nav-list">
            	<ul>
	                	<li>
	                    	<a href="${ctx}/user/text" id="statistics"><i class="ico-item"></i>统计分析(admin权限可以访问：备注权限测试)</a>
	                    </li>
	                <shiro:hasPermission  name="admin:user">
	                    <li>
	                    	<a href="javascript:void(0);" id="userManager"><i class="ico-member"></i>用户管理</a>
	                    </li>
	               </shiro:hasPermission >
	               <shiro:hasPermission  name="admin:user">
	                    <li>
	                    	<a href="javascript:void(0);" id="roleManager"><i class="ico-add"></i>权限管理</a>
	                    </li>
	              </shiro:hasPermission >
                </ul>
            </nav>
        </div>
    <!--left end]]-->
    <!--[[right start-->
        <div class="rightbar">
           <div class="table-d">
        	<table width="800" border="0" cellspacing="5">
        	  <tr>
        	    <th>备注</th>
        	    <th>表名称</th>
        	    <th>操作</th>
        	  </tr>
        	  <c:forEach items="${tables}" var="tabels" >  
        	   <tr>
        	     <td>${tabels.tableComment}</td>  
        	     <td>${tabels.tableName}</td>  
        	     <td><a href="${ctx}/editDataBase/${tabels.tableName}"><i class="ico-edit"></i>同步数据</a></td>
        	   </tr>
        	  </c:forEach>
        	</table>
           </div>
        </div>
</div>
   <!--  <script type="text/javascript">
    	// 基于准备好的dom，初始化echarts实例
          var myChart = echarts.init(document.getElementById('main'));
          var data =[];
          for (var i = 0; i < 6; i++) {
        	  data[i]= Math.floor(Math.random()*100)+1;
  	     }
          console.log("----------------"+new Date()+data+"---------------");
          // 指定图表的配置项和数据
          var option = {
              title: {
                  text: 'ECharts 入门示例'
              },
              tooltip: {},
              legend: {
                  data:['销量']
              },
              xAxis: {
                  data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
              },
              yAxis: {},
              series: [{
                  name: '销量',
                  type: 'bar',
                  data: data
              }]
          };
          // 使用刚指定的配置项和数据显示图表。
          myChart.setOption(option); 
          function myrefresh()
          {
        	     
                 window.location.reload();
          }
           setInterval("myrefresh()", 5000);
        
    </script> -->
</body>
</html>