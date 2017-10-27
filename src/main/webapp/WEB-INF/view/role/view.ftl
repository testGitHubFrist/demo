<#include "/common/head.ftl"/> 

<script type="text/javascript" src="<@dragons.s/>My97DatePicker/WdatePicker.js"></script>
<title>首汽约车管理系统</title>


<!--页面整体内容-->
<div class="layout grid-s5m0e6">
  <div class="col-main">
    <div class="main-wrap">
      <div class="here">当前位置：系统管理 --&gt;用户管理</div>
      <div class="bor ks-center">



	<form action="/manage/user/insert" name="form" id="form" >
		<table class="table table-bordered">

			<tr style="background-color: #dff0d8">
				<td colspan="2" style="background-color: #dff0d8;text-align: center;">
					<strong>用户信息 </strong>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;width: 100px;">用户名称</td>
				<td style="text-align: left;">${user.username}</td>
			</tr>
			<tr>
				<td style="text-align: right;width: 100px;">用户密码</td>
				<td style="text-align: left;">${user.password}</td>
			</tr>
			
		</table>
	</form>


      </div>
<#include "/common/footer.ftl"/> 
      <!--end 版权--> 
    </div>

    <!--end 右侧 --> 
  </div>
  <!-- 左侧 -->
<#include "/common/left.ftl"/> 
    <!--end 左侧 --> </div>
</div>
</body>
</html>