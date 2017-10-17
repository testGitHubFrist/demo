<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入 ECharts 文件 -->
<script src="${pageContext.request.contextPath}/static/js/echarts.common.min.js"></script>
<title>Insert title here</title>
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>
    <div id="main1" style="width: 400px;height:400px;"></div>
    <script type="text/javascript">
    	// 基于准备好的dom，初始化echarts实例
          var myChart = echarts.init(document.getElementById('main'));
          var myChart1 = echarts.init(document.getElementById('main1'));
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
          myChart1.setOption(option); 
          function myrefresh()
          {
        	     
                 window.location.reload();
          }
          setInterval("myrefresh()", 5000); 
        
    </script>
</body>
</html>