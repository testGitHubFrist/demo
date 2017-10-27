<#include "/common/head.ftl"/> 

<title>首汽约车管理系统</title>

<!--页面整体内容-->
<div class="layout grid-s5m0e6">
  <div class="col-main">
    <div class="main-wrap">
      <div class="here">当前位置：系统管理 --&gt;角色权限管理</div>
      <div class="bor ks-center">

	<form action="/manage/role/list?leftId=evaluation1" method="post" name="form" id="form">
		<table class="table table-bordered">
			<tr>
				<td colspan="8">
					<@shiro.hasPermission name="admin:roleAdd">
					<a href="/manage/role/add?leftId=evaluation1" class="btn btn-success">
						<i class="icon-plus-sign icon-white"></i> 增加
					</a>
					</@shiro.hasPermission>
				</td>
			</tr>
		</table>
		

		<table class="table table-bordered table-hover">
			<tr style="background-color: #dff0d8">
				<th nowrap="nowrap" style="width:20px; text-align:center;">序号</th>
				<th nowrap="nowrap" style="text-align:center;">角色</th>
				<th nowrap="nowrap" style="text-align:center;">状态</th>
				<th nowrap="nowrap" style="width:120px; text-align:center;">操作</th>
			</tr>
			<#list pager.list as p>
            <tr>
              <td style="text-align:center;">${p_index+1+role.offset}</td>
              <td style="text-align:center;">${p.roleName}</td>
              <td style="text-align:center;">
              	<#if p.status == 1>
              	  启用
				<#else> 
				 禁用
				</#if> 
			  </td>
              <td style="text-align:center;">
              	<@shiro.hasPermission name="admin:roleEdit">
              		<a href="/manage/role/edit/${p.roleId}?leftId=evaluation1">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
              	</@shiro.hasPermission>
              	<@shiro.hasPermission name="admin:roleDel">
              		<a href="javascript:del(${p.roleId});">删除</a>
              	</@shiro.hasPermission>
              </td>
            </tr>
			</#list>
		</table>
		    		
    	<div class=PageNum>  
            <#import "/common/page.ftl" as com>  
            <#--前一个参数是总记录数，后一个参数是页面记录数-->  
            <@com.pagination pager.total pager.pageSize/>  
        </div> 		    		
		    		
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

<script type="text/javascript">
function del(id){
	if(confirm("确定删除该角色？")){
		window.location.href = "/manage/role/delete/"+id+"?leftId=evaluation1";
	}
}


</script>
</body>
</html>