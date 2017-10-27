<#include "/common/head.ftl"/> 
<title>首汽约车管理系统</title>

<!--页面整体内容-->
<div class="layout grid-s5m0e6">
  <div class="col-main">
    <div class="main-wrap">
      <div class="here">当前位置：系统管理 --&gt;角色权限管理</div>
      <div class="bor ks-center">

	<#-- 目前以后11列, id值也是11  -->
	<form action="/manage/role/save" name="form" id="form" method="post">
		<table class="table table-bordered">
		<input type="hidden" name="roleId" id="roleId" value="${role.roleId?if_exists}">
			<tr>
				<td colspan="3" style="text-align: center;">
					<@shiro.hasPermission name="admin:roleAdd">
					<button  method="/manage/role/save" class="btn btn-success" onclick="return submitForm(this);">
						<i class="icon-ok icon-white"></i> 保存
					</button>	
					<button class="btn btn-success" type="button" onclick="javascript:history.go(-1);">
						<i class="icon-ok icon-white"></i> 返回
					</button>
					</@shiro.hasPermission>
				</td>
			</tr>
			<tr style="background-color: #dff0d8">
				<td colspan="3" style="background-color: #dff0d8;text-align: left;">
					<#if !role.roleId?exists>
						<strong>增加角色权限</strong>
		 			<#else>
						<strong>编辑角色权限</strong>
					</#if>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;width: 100px;">角色名称</td>
				<td style="text-align: left;" colspan="2">
					<input type="text" name="roleName" id="roleName" value="${role.roleName?if_exists}" <#if role.roleId?exists>readonly="true"</#if> style="width: 200px;" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right;width: 100px;">角色状态</td>
				<td style="text-align: left;" colspan="2">
				<select id="status" name="status">
					<option value="1">有效</option>
					<option value="2">无效</option>
				</select>
				</td>
			</tr>	
			<tr style="background-color: #dff0d8">
				<td style="background-color: #dff0d8;text-align: left;" colspan="3"> 
					<strong>可赋予的权限</strong>
				</td>
			</tr>
			
			<#-- 经营数据统计start -->
			<tr >
				<th style="width:130px;" rowspan="8">
					<input class="zxq_10" onclick="checks(1, this);" type="checkbox" name="authorities" value="admin:statistics"  <#if role.authorities?seq_contains("admin:statistics")> checked="checked"</#if>/>经营数据统计:
				</th>
				<td style="width:130px;">
					<input id="zxq_10_01" class="zxq_10_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:turnover"  <#if role.authorities?seq_contains("admin:turnover")> checked="checked"</#if>/>营业额
				</td>
				<td>
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_10_02" class="zxq_10_02" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:orderNums"  <#if role.authorities?seq_contains("admin:orderNums")> checked="checked"</#if>/>订单数量
				</td>
				<td>
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_10_03" class="zxq_10_03" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:customerNums"  <#if role.authorities?seq_contains("admin:customerNums")> checked="checked"</#if>/>用户新增数量
				</td>
				<td>
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_10_08" class="zxq_10_08" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:customerCount"  <#if role.authorities?seq_contains("admin:customerCount")> checked="checked"</#if>/>用户总量
				</td>
				<td>
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_10_04" class="zxq_10_04" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:settlement"  <#if role.authorities?seq_contains("admin:settlement")> checked="checked"</#if>/>结算方式
				</td>
				<td>
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_10_05" class="zxq_10_05" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:driverReport"  <#if role.authorities?seq_contains("admin:driverReport")> checked="checked"</#if>/>司机日报/月报
				</td>
				<td>
				</td>
			</tr>
			
			<tr>
				<td>
					<input id="cl_11_01" class="cl_11_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:customerRechargeDetail"  <#if role.authorities?seq_contains("admin:customerRechargeDetail")> checked="checked"</#if>/>用户充值/消费统计
				</td>
				<td>
					<input class="cl_11_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:customerRechargeDetailExport"  <#if role.authorities?seq_contains("admin:customerRechargeDetailExport")> checked="checked"</#if>/>导出&nbsp;&nbsp;
				</td>
			</tr>
			
			<#-- add by wangbj begin -->
			<tr>
				<td>
					<input id="zxq_10_07" class="zxq_10_07" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:paymentDetail"  <#if role.authorities?seq_contains("admin:paymentDetail")> checked="checked"</#if>/>平台刷卡流水
				</td>
				<td></td>
			</tr>
			<#-- add by wangbj end -->
			
			
			
			<#-- 经营数据统计end -->
			
			<#-- 订单管理start -->
			<tr >
				<th style="width:130px;" rowspan="9">
					<input class="zxq_02" onclick="checks(1, this);" type="checkbox" name="authorities" value="admin:order"  <#if role.authorities?seq_contains("admin:order")> checked="checked"</#if>/>订单管理:
				</th>
				<td style="width:130px;">
					<input id="zxq_02_01" class="zxq_02_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:orderList"  <#if role.authorities?seq_contains("admin:orderList")> checked="checked"</#if>/>订单列表
				</td>
				<td>
					<input class="zxq_02_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:orderCancel"  <#if role.authorities?seq_contains("admin:orderCancel")> checked="checked"</#if>/>订单取消&nbsp;&nbsp;
					<input class="zxq_02_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:orderYiyi"  <#if role.authorities?seq_contains("admin:orderYiyi")> checked="checked"</#if>/>订单异议&nbsp;&nbsp;
					<input class="zxq_02_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:orderJm"  <#if role.authorities?seq_contains("admin:orderJm")> checked="checked"</#if>/>订单减免&nbsp;&nbsp;
					<input class="zxq_02_01_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:orderAdd"  <#if role.authorities?seq_contains("admin:orderAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_02_01_05" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:orderSys"  <#if role.authorities?seq_contains("admin:orderSys")> checked="checked"</#if>/>系统派单&nbsp;&nbsp;
					<input class="zxq_02_01_06" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:orderPer"  <#if role.authorities?seq_contains("admin:orderPer")> checked="checked"</#if>/>人工派单&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td style="width:130px;">
					<input id="lx_02_01" class="lx_02_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:orderMesh"  <#if role.authorities?seq_contains("admin:orderMesh")> checked="checked"</#if>/>筛选订单列表
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_02_02" class="zxq_02_02" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:charteredorderList"  <#if role.authorities?seq_contains("admin:charteredorderList")> checked="checked"</#if>/>日租/半日租订单列表
				</td>
				<td>
					<input class="zxq_02_02_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:charteredDaochu"  <#if role.authorities?seq_contains("admin:charteredDaochu")> checked="checked"</#if>/>导出&nbsp;&nbsp;
					<input class="zxq_02_02_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:charteredEdit"  <#if role.authorities?seq_contains("admin:charteredEdit")> checked="checked"</#if>/>回访&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<input id="cl_010_01" class="cl_010_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:charteredNew"  <#if role.authorities?seq_contains("admin:charteredNew")> checked="checked"</#if>/>日租/半日租订单列表(新)
				</td>
				<td>
					
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_02_03" class="zxq_02_03" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:dispatchorderList"  <#if role.authorities?seq_contains("admin:dispatchorderList")> checked="checked"</#if>/>人工调度列表
				</td>
				<td>
					<input class="zxq_02_03_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:dispatchorderCancel"  <#if role.authorities?seq_contains("admin:dispatchorderCancel")> checked="checked"</#if>/>订单取消&nbsp;&nbsp;
					<input class="zxq_02_03_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:dispatchorderOpen"  <#if role.authorities?seq_contains("admin:dispatchorderOpen")> checked="checked"</#if>/>人工调度&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr>
			<#--注释人工变更订单状态
				<td>
					<input id="cl_011_01" class="cl_011_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:orderStatuslist"  <#if role.authorities?seq_contains("admin:orderStatuslist")> checked="checked"</#if>/>人工变更订单状态
				</td>
				<td>
					
				</td>
				-->
			</tr>
			
			<tr>
			
				<td>
					<input id="cl_019_01" class="cl_019_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:multiDayOrderList"  <#if role.authorities?seq_contains("admin:multiDayOrderList")> checked="checked"</#if>/>多日接送订单列表
				</td>
				<td>
					
				</td>
				
			</tr>
			
			<tr>
			
				<td>
					<input id="cl_119_01" class="cl_119_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:orderPoolList"  <#if role.authorities?seq_contains("admin:orderPoolList")> checked="checked"</#if>/>订单池列表
				</td>
				<td>
					<input class="cl_119_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:orderPoolCancel"  <#if role.authorities?seq_contains("admin:orderPoolCancel")> checked="checked"</#if>/>订单取消
				</td>
				
			</tr>
			
			<tr>
			
				<td>
					<input id="cl_159_01" class="cl_159_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:cooperationpartnetList"  <#if role.authorities?seq_contains("admin:cooperationpartnetList")> checked="checked"</#if>/>第三方订单列表
				</td>
				<td>
					
				</td>
				
			</tr>
			
			<#-- 订单管理end -->
			
			<#-- 财务start -->
			<tr >
				<th style="width:130px;" rowspan="31">
					<input class="zxq_03" onclick="checks(1, this);" type="checkbox" name="authorities" value="admin:financial"  <#if role.authorities?seq_contains("admin:financial")> checked="checked"</#if>/>财务:
				</th>
				<td style="width:130px;">
					<input id="zxq_03_01" class="zxq_03_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:financeList"  <#if role.authorities?seq_contains("admin:financeList")> checked="checked"</#if>/>司机提成结算
				</td>
				<td>
					<input class="zxq_03_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:financeExport"  <#if role.authorities?seq_contains("admin:financeExport")> checked="checked"</#if>/>导出&nbsp;&nbsp;
					<input class="zxq_03_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:financeSettle"  <#if role.authorities?seq_contains("admin:financeSettle")> checked="checked"</#if>/>结算/批量结算&nbsp;&nbsp;
					<input class="zxq_03_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:financeHistory"  <#if role.authorities?seq_contains("admin:financeHistory")> checked="checked"</#if>/>结算记录
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_03_02" class="zxq_03_02" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:financeOutList"  <#if role.authorities?seq_contains("admin:financeOutList")> checked="checked"</#if>/>司机代付结算
				</td>
				<td>
					<input class="zxq_03_02_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:financeCreExport"  <#if role.authorities?seq_contains("admin:financeCreExport")> checked="checked"</#if>/>导出&nbsp;&nbsp;
					<input class="zxq_03_02_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:financeOutSettle"  <#if role.authorities?seq_contains("admin:financeOutSettle")> checked="checked"</#if>/>结算/批量结算&nbsp;&nbsp;
					<input class="zxq_03_02_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:financeOutHistoryCre"  <#if role.authorities?seq_contains("admin:financeOutHistoryCre")> checked="checked"</#if>/>结算记录
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_03_03" class="zxq_03_03" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:financeCreList"  <#if role.authorities?seq_contains("admin:financeCreList")> checked="checked"</#if>/>司机代收结算
				</td>
				<td>
					<input class="zxq_03_03_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:financeOutExport"  <#if role.authorities?seq_contains("admin:financeOutExport")> checked="checked"</#if>/>导出&nbsp;&nbsp;
					<input class="zxq_03_03_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:financeCreSettle"  <#if role.authorities?seq_contains("admin:financeCreSettle")> checked="checked"</#if>/>结算/批量结算&nbsp;&nbsp;
					<input class="zxq_03_03_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:finanaceHistoryOut"  <#if role.authorities?seq_contains("admin:finanaceHistoryOut")> checked="checked"</#if>/>结算记录
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_03_04" class="zxq_03_04" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:doorManSettleList"  <#if role.authorities?seq_contains("admin:doorManSettleList")> checked="checked"</#if>/>代理人结算
				</td>
				<td>
					<input class="zxq_03_04_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:doorManSettleExport"  <#if role.authorities?seq_contains("admin:doorManSettleExport")> checked="checked"</#if>/>导出&nbsp;&nbsp;
					<input class="zxq_03_04_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:doorManSettleSettle"  <#if role.authorities?seq_contains("admin:doorManSettleSettle")> checked="checked"</#if>/>结算/批量结算&nbsp;&nbsp;
					<input class="zxq_03_04_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:doorManSettleSettleLog"  <#if role.authorities?seq_contains("admin:doorManSettleSettleLog")> checked="checked"</#if>/>结算记录
				</td>
			</tr>
			
			<tr>
				<td>
					<input id="zxq_03_05" class="zxq_03_05" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:invoiceList"  <#if role.authorities?seq_contains("admin:invoiceList")> checked="checked"</#if>/>待开发票
				</td>
				<td>
					<input class="zxq_03_05_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:invoiceOpen"  <#if role.authorities?seq_contains("admin:invoiceOpen")> checked="checked"</#if>/>开票&nbsp;&nbsp;
					<input class="zxq_03_05_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:invoiceDel"  <#if role.authorities?seq_contains("admin:invoiceDel")> checked="checked"</#if>/>删除
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_03_06" class="zxq_03_06" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:invoiceonList"  <#if role.authorities?seq_contains("admin:invoiceonList")> checked="checked"</#if>/>已开发票
				</td>
				<td>
				</td>
				<#--
				<td>
					<input class="zxq_03_06_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:invoiceOpen"  <#if role.authorities?seq_contains("admin:invoiceOpen")> checked="checked"</#if>/>开票&nbsp;&nbsp;
					<input class="zxq_03_06_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:invoiceonDel"  <#if role.authorities?seq_contains("admin:invoiceonDel")> checked="checked"</#if>/>删除
				</td>
				-->
			</tr>
			<tr>
				<td>
					<input id="zxq_03_07" class="zxq_03_07" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:invoiceonPersonList"  <#if role.authorities?seq_contains("admin:invoiceonPersonList")> checked="checked"</#if>/>人工开票
				</td>
				<td>
					<input class="zxq_03_07_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:invoiceonPersonOpen"  <#if role.authorities?seq_contains("admin:invoiceonPersonOpen")> checked="checked"</#if>/>申请&nbsp;&nbsp;
					<#--
					<input class="zxq_03_07_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:invoiceonPersonDel"  <#if role.authorities?seq_contains("admin:invoiceonPersonDel")> checked="checked"</#if>/>删除
					-->
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_03_08" class="zxq_03_08" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:businessSettleList"  <#if role.authorities?seq_contains("admin:businessSettleList")> checked="checked"</#if>/>机构结算
				</td>
				<td>
					<input class="zxq_03_08_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:businessSettleExport"  <#if role.authorities?seq_contains("admin:businessSettleExport")> checked="checked"</#if>/>导出&nbsp;&nbsp;
					<input class="zxq_03_08_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:businessSettleJiezhuan"  <#if role.authorities?seq_contains("admin:businessSettleJiezhuan")> checked="checked"</#if>/>结转&nbsp;&nbsp;
					<input class="zxq_03_08_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:businessSettleSettle"  <#if role.authorities?seq_contains("admin:businessSettleSettle")> checked="checked"</#if>/>结算&nbsp;&nbsp;
					<input class="zxq_03_08_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:businessSettleSettleLog"  <#if role.authorities?seq_contains("admin:businessSettleSettleLog")> checked="checked"</#if>/>结算记录&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_03_09" class="zxq_03_09" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:statisticsAmount"  <#if role.authorities?seq_contains("admin:statisticsAmount")> checked="checked"</#if>/>金额统计
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_03_10" class="zxq_03_10" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:statisticsInvoice"  <#if role.authorities?seq_contains("admin:statisticsInvoice")> checked="checked"</#if>/>开票统计
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_03_11" class="zxq_03_11" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:statisticsCoupon"  <#if role.authorities?seq_contains("admin:statisticsCoupon")> checked="checked"</#if>/>优惠券统计
				</td>
				<td>
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_03_15" class="zxq_03_15" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:reportList"  <#if role.authorities?seq_contains("admin:reportList")> checked="checked"</#if>/>月度报表统计
				</td>
				<td></td>
			</tr>
			<tr>
				<td>
					<input id="zxq_03_12" class="zxq_03_12" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:machineinvoiceList"  <#if role.authorities?seq_contains("admin:machineinvoiceList")> checked="checked"</#if>/>机打发票
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_03_13" class="zxq_03_13" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:cumulativeinvoiceList"  <#if role.authorities?seq_contains("admin:cumulativeinvoiceList")> checked="checked"</#if>/>机打发票汇总列表	
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_03_14" class="zxq_03_14" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:bathRechargeGift"  <#if role.authorities?seq_contains("admin:bathRechargeGift")> checked="checked"</#if>/>批量充值赠送	
				</td>
				<td>
					<input class="zxq_03_14_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:bathRechargeGiftSave"  <#if role.authorities?seq_contains("admin:bathRechargeGiftSave")> checked="checked"</#if>/>保存&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<input id="cl_01_01" class="cl_01_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:driverAccountReportDetail"  <#if role.authorities?seq_contains("admin:driverAccountReportDetail")> checked="checked"</#if>/>平台司机账户流水	
				</td>
				<td>
					<input class="cl_01_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:driverAccountReportDetailExport"  <#if role.authorities?seq_contains("admin:driverAccountReportDetailExport")> checked="checked"</#if>/>导出&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<input id="cl_02_01" class="cl_02_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:platformList"  <#if role.authorities?seq_contains("admin:platformList")> checked="checked"</#if>/>平台用户流水
				</td>
				<td>
					<input class="cl_02_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:platformListExport"  <#if role.authorities?seq_contains("admin:platformListExport")> checked="checked"</#if>/>导出&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr>
				<td>
					<input id="cl_03_01" class="cl_03_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:batchRechargeCardList"  <#if role.authorities?seq_contains("admin:batchRechargeCardList")> checked="checked"</#if>/>储蓄卡管理
				</td>
				<td>
					<input class="cl_03_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:batchRechargeCardSave"  <#if role.authorities?seq_contains("admin:batchRechargeCardSave")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_03_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:batchRechargeCardExport"  <#if role.authorities?seq_contains("admin:batchRechargeCardExport")> checked="checked"</#if>/>导出&nbsp;&nbsp;
				</td>
			</tr>
			
			<#-- add by wangbj begin -->
			<tr>
				<td>
					<input id="zxq_03_18" class="zxq_03_18" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:CustomerAccountMonthlyBalanceList"  <#if role.authorities?seq_contains("admin:CustomerAccountMonthlyBalanceList")> checked="checked"</#if>/>乘客月度期初余额
				</td>
				<td></td>
			</tr>
			<#-- add by wangbj end -->
			
			<#-- add by chenlei begin -->
			<tr>
				<td>
					<input id="cl_09_01" class="cl_09_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:wxdifferenceList"  <#if role.authorities?seq_contains("admin:wxdifferenceList")> checked="checked"</#if>/>微信交易差异数据统计
				</td>
				<td></td>
			</tr>
			<tr>
				<td>
					<input id="cl_09_02" class="cl_09_02" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:ailipayList"  <#if role.authorities?seq_contains("admin:ailipayList")> checked="checked"</#if>/>支付宝充值记录
				</td>
				<td>
				<input class="cl_09_02_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:ailipayImport"  <#if role.authorities?seq_contains("admin:ailipayImport")> checked="checked"</#if>/>导入&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<input id="cl_09_03" class="cl_09_03" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:posRushCardList"  <#if role.authorities?seq_contains("admin:posRushCardList")> checked="checked"</#if>/>POS机刷卡流水
				</td>
			</tr>
			<tr>
				<td>
					<input id="cl_09_04" class="cl_09_04" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:wxRechargeList"  <#if role.authorities?seq_contains("admin:wxRechargeList")> checked="checked"</#if>/>微信充值流水
				</td>
			</tr>
			<tr>
				<td>
					<input id="cl_09_05" class="cl_09_05" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:jdRechargeList"  <#if role.authorities?seq_contains("admin:jdRechargeList")> checked="checked"</#if>/>京东充值流水
				</td>
			</tr>
			<tr>
				<td>
					<input id="cl_09_06" class="cl_09_06" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:applePayRechargeList"  <#if role.authorities?seq_contains("admin:applePayRechargeList")> checked="checked"</#if>/>ApplePay充值流水
				</td>
			</tr>
			<tr>
				<td>
					<input id="cl_09_07" class="cl_09_07" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:unionPayRechargeList"  <#if role.authorities?seq_contains("admin:unionPayRechargeList")> checked="checked"</#if>/>银联充值流水
				</td>
			</tr>
			<tr>
				<td>
					<input id="cl_99_07" class="cl_99_07" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:baiduBillList"  <#if role.authorities?seq_contains("admin:baiduBillList")> checked="checked"</#if>/>百度对账
				</td>
				<td></td>
			</tr>
			
			<tr>
				<td>
					<input id="cl_99_08" class="cl_99_08" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:refundList"  <#if role.authorities?seq_contains("admin:refundList")> checked="checked"</#if>/>退款管理
				</td>
				<td></td>
			</tr>
			
			<tr>
				<td>
					<input id="cl_99_98" class="cl_99_98" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:customeraccountinvoicedetail"  <#if role.authorities?seq_contains("admin:customeraccountinvoicedetail")> checked="checked"</#if>/>乘客发票流水查询
				</td>
				<td></td>
			</tr>
			
			<tr>
				<td>
					<input id="zxq_33_08" class="zxq_33_08" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:rechargeManagementList"  <#if role.authorities?seq_contains("admin:rechargeManagementList")> checked="checked"</#if>/>充值管理
				</td>
				<td>
					<input class="zxq_33_08_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:rechargeManagementAdd"  <#if role.authorities?seq_contains("admin:rechargeManagementAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_33_08_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:rechargeManagementEdit"  <#if role.authorities?seq_contains("admin:rechargeManagementEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_33_08_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:rechargeManagementDel"  <#if role.authorities?seq_contains("admin:rechargeManagementDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			
			
			<tr>
				<td>
					<input id="zxq_313_08" class="zxq_313_08" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:inviterRchargeDataList"  <#if role.authorities?seq_contains("admin:inviterRchargeDataList")> checked="checked"</#if>/>邀请好友充值返现记录
				</td>
				<td>
					
				</td>
			</tr>
			
			<#-- add by chenlei end -->
			
			<#-- 财务end -->
			
			<#-- 优惠活动管理start -->
			<tr >
				<th style="width:130px;" rowspan="4">
					<input class="zxq_05" onclick="checks(1, this);" type="checkbox" name="authorities" value="admin:promotionActivity"  <#if role.authorities?seq_contains("admin:promotionActivity")> checked="checked"</#if>/>优惠活动管理:
				</th>
				<td style="width:130px;">
					<input id="zxq_05_01" class="zxq_05_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:promotionActivityList"  <#if role.authorities?seq_contains("admin:promotionActivityList")> checked="checked"</#if>/>优惠活动
				</td>
				<td>
					<input class="zxq_05_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:promotionActivityAdd"  <#if role.authorities?seq_contains("admin:promotionActivityAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_05_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:promotionActivityEdit"  <#if role.authorities?seq_contains("admin:promotionActivityEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_05_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:promotionActivityDel"  <#if role.authorities?seq_contains("admin:promotionActivityDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="zxq_05_01_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:promotionActivityDelall"  <#if role.authorities?seq_contains("admin:promotionActivityDelall")> checked="checked"</#if>/>批量删除
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_05_02" class="zxq_05_02" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:businessPromotionActivityList"  <#if role.authorities?seq_contains("admin:businessPromotionActivityList")> checked="checked"</#if>/>机构优惠活动
				</td>
				<td>
					<input class="zxq_05_02_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:businessPromotionActivityAdd"  <#if role.authorities?seq_contains("admin:businessPromotionActivityAdd")> checked="checked"</#if>/>增加
					<input class="zxq_05_02_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:businessPromotionActivityUpdate"  <#if role.authorities?seq_contains("admin:businessPromotionActivityUpdate")> checked="checked"</#if>/>修改
					<input class="zxq_05_02_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:businessPromotionActivityDel"  <#if role.authorities?seq_contains("admin:businessPromotionActivityDel")> checked="checked"</#if>/>删除
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_05_02" class="zxq_05_02" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:couponsList"  <#if role.authorities?seq_contains("admin:couponsList")> checked="checked"</#if>/>优惠券管理
				</td>
				<td>
					<input class="zxq_05_02_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:couponsAdd"  <#if role.authorities?seq_contains("admin:couponsAdd")> checked="checked"</#if>/>增加
					<input class="zxq_05_02_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:couponsDel"  <#if role.authorities?seq_contains("admin:couponsDel")> checked="checked"</#if>/>删除
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_05_03" class="zxq_05_03" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:redeemCode"  <#if role.authorities?seq_contains("admin:redeemCode")> checked="checked"</#if>/>兑换码管理
				</td>
				<td>
					<input class="zxq_05_03_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:redeemCodeAdd"  <#if role.authorities?seq_contains("admin:redeemCodeAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_05_03_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:redeemCodeEdit"  <#if role.authorities?seq_contains("admin:redeemCodeEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_05_03_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:redeemCodeDel"  <#if role.authorities?seq_contains("admin:redeemCodeDel")> checked="checked"</#if>/>删除
				</td>
			</tr>
			<#-- add by wangbj begin -->
			<tr>
				<td>
					<input id="zxq_05_04" class="zxq_05_04" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:couponsListV3"  <#if role.authorities?seq_contains("admin:couponsListV3")> checked="checked"</#if>/>折扣券管理
				</td>
				<td>
					<input class="zxq_05_04_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:couponsAddV3"  <#if role.authorities?seq_contains("admin:couponsAddV3")> checked="checked"</#if>/>增加
				</td>
			</tr>
			<#-- add by wangbj end -->
			<#-- 优惠活动管理end -->
			
			<#-- 租车用户管理start -->
			<tr >
				<th style="width:130px;" rowspan="4">
					<input class="zxq_06" onclick="checks(1, this);" type="checkbox" name="authorities" value="admin:member"  <#if role.authorities?seq_contains("admin:member")> checked="checked"</#if>/>租车用户管理:
				</th>
				<td style="width:130px;">
					<input id="zxq_06_01" class="zxq_06_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:memberList"  <#if role.authorities?seq_contains("admin:memberList")> checked="checked"</#if>/>用户管理
				</td>
				<td>
					<input class="zxq_06_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:memberAdd"  <#if role.authorities?seq_contains("admin:memberAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_06_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:memberEdit"  <#if role.authorities?seq_contains("admin:memberEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_06_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:memberShowOrder"  <#if role.authorities?seq_contains("admin:memberShowOrder")> checked="checked"</#if>/>订单明细&nbsp;&nbsp;
					<input class="zxq_06_01_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:memberHistory"  <#if role.authorities?seq_contains("admin:memberHistory")> checked="checked"</#if>/>账户流水&nbsp;&nbsp;
					<input class="zxq_06_01_05" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:memberBlacklist"  <#if role.authorities?seq_contains("admin:memberBlacklist")> checked="checked"</#if>/>拉黑/反白&nbsp;&nbsp;
					<input class="zxq_06_01_06" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:resetPassword"  <#if role.authorities?seq_contains("admin:resetPassword")> checked="checked"</#if>/>修改密码
					<input class="zxq_06_01_07" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:transferAccount"  <#if role.authorities?seq_contains("admin:transferAccount")> checked="checked"</#if>/>转账/人工清零
					<#-- 
					<input class="zxq_06_01_08" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:resetUserStatus"  <#if role.authorities?seq_contains("admin:resetUserStatus")> checked="checked"</#if>/>重置
				    -->
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_06_02" class="zxq_06_02" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:doormanList"  <#if role.authorities?seq_contains("admin:doormanList")> checked="checked"</#if>/>代理人管理
				</td>
				<td>
					<input class="zxq_06_02_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:doormanAdd"  <#if role.authorities?seq_contains("admin:doormanAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_06_02_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:doormanEdit"  <#if role.authorities?seq_contains("admin:doormanEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_06_02_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:doormanOrder"  <#if role.authorities?seq_contains("admin:doormanOrder")> checked="checked"</#if>/>订单明细&nbsp;&nbsp;
					<input class="zxq_06_02_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:doormanHistory"  <#if role.authorities?seq_contains("admin:doormanHistory")> checked="checked"</#if>/>账户流水
				</td>
			</tr>
			
			<tr>
				<td>
					<input id="zxq_606_02" class="zxq_606_02" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:imeiList"  <#if role.authorities?seq_contains("admin:imeiList")> checked="checked"</#if>/>IMEI拉黑管理
				</td>
				<td>
					<input class="zxq_606_02_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:adImei"  <#if role.authorities?seq_contains("admin:adImei")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					
				</td>
			</tr>
			
			<tr>
				<td>
					<input id="zxq_06_04" class="zxq_06_04" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:vipUserList" <#if role.authorities?seq_contains("admin:vipUserList")> checked="checked"</#if> />vip用户列表
				</td>
				<td>
					<input class="zxq_06_04_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:vipUserEdit"  <#if role.authorities?seq_contains("admin:vipUserEdit")> checked="checked"</#if>/>设置&nbsp;&nbsp;
					<input class="zxq_06_04_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:vipUserDetailList"  <#if role.authorities?seq_contains("admin:vipUserDetailList")> checked="checked"</#if>/>充值记录&nbsp;&nbsp;
				</td>
			</tr>
			<#-- 租车用户管理end -->
			
			<#-- 机构用户管理start -->
			<tr >
				<th style="width:130px;" rowspan="3">
					<input class="zxq_11" onclick="checks(1, this);" type="checkbox" name="authorities" value="admin:business"  <#if role.authorities?seq_contains("admin:business")> checked="checked"</#if>/>机构用户管理:
				</th>
				<td style="width:130px;">
					<input id="zxq_11_01" class="zxq_11_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:businessList"  <#if role.authorities?seq_contains("admin:businessList")> checked="checked"</#if>/>机构信息管理
				</td>
				<td>
					<input class="zxq_11_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:businessAdd"  <#if role.authorities?seq_contains("admin:businessAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_11_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:businessEdit"  <#if role.authorities?seq_contains("admin:businessEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_11_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:businessStatus"  <#if role.authorities?seq_contains("admin:businessStatus")> checked="checked"</#if>/>禁用/启用&nbsp;&nbsp;
					<input class="zxq_11_01_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:businessAccountDetail"  <#if role.authorities?seq_contains("admin:businessAccountDetail")> checked="checked"</#if>/>账户流水&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_11_02" class="zxq_11_02" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:businessAuthorize"  <#if role.authorities?seq_contains("admin:businessAuthorize")> checked="checked"</#if>/>机构授权管理
				</td>
				<td>
					<input class="zxq_11_02_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:businessAuthorizeAdd"  <#if role.authorities?seq_contains("admin:businessAuthorizeAdd")> checked="checked"</#if>/>人工授权&nbsp;&nbsp;
					<input class="zxq_11_02_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:businessAuthorizeDel"  <#if role.authorities?seq_contains("admin:businessAuthorizeDel")> checked="checked"</#if>/>解除/批量解除授权&恢复授权&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_11_03" class="zxq_11_03" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:businessOrder"  <#if role.authorities?seq_contains("admin:businessOrder")> checked="checked"</#if>/>机构订单管理
				</td>
				<td>
					<input class="zxq_11_03_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:businessOrderExport"  <#if role.authorities?seq_contains("admin:businessOrderExport")> checked="checked"</#if>/>导出机构订单&nbsp;&nbsp;
				</td>
			</tr>
			<#-- 机构用户管理end -->
			
			<#-- 车队管理start -->
			<tr >
				<th style="width:130px;" rowspan="7">
					<input class="zxq_08" onclick="checks(1, this);" type="checkbox" name="authorities" value="admin:driver"  <#if role.authorities?seq_contains("admin:driver")> checked="checked"</#if>/>车队管理:
				</th>
				<td style="width:130px;">
					<input id="zxq_08_01" class="zxq_08_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:driverList"  <#if role.authorities?seq_contains("admin:driverList")> checked="checked"</#if>/>司机管理
				</td>
				<td>
					<input class="zxq_08_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:driverAdd"  <#if role.authorities?seq_contains("admin:driverAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_08_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:driverEdit"  <#if role.authorities?seq_contains("admin:driverEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_08_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:driverStatus"  <#if role.authorities?seq_contains("admin:driverStatus")> checked="checked"</#if>/>设为无效/有效&nbsp;&nbsp;
					<input class="zxq_08_01_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:driverOrder"  <#if role.authorities?seq_contains("admin:driverOrder")> checked="checked"</#if>/>订单明细&nbsp;&nbsp;
					<input class="zxq_08_01_05" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:driverHistory"  <#if role.authorities?seq_contains("admin:driverHistory")> checked="checked"</#if>/>账户流水&nbsp;&nbsp;
					<input class="zxq_08_01_06" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:driverAccident"  <#if role.authorities?seq_contains("admin:driverAccident")> checked="checked"</#if>/>司机事故&nbsp;&nbsp;
					<input class="zxq_08_01_07" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:driverResetStatus"  <#if role.authorities?seq_contains("admin:driverResetStatus")> checked="checked"</#if>/>重置&nbsp;&nbsp;
					<input class="zxq_08_01_08" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:driverLead"  <#if role.authorities?seq_contains("admin:driverLead")> checked="checked"</#if>/>导入
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_08_02" class="zxq_08_02" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:carInfoList"  <#if role.authorities?seq_contains("admin:carInfoList")> checked="checked"</#if>/>车辆管理
				</td>
				<td>
					<input class="zxq_08_02_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:carInfoAdd"  <#if role.authorities?seq_contains("admin:carInfoAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_08_02_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:carInfoEdit"  <#if role.authorities?seq_contains("admin:carInfoEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_08_02_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:carInfoDel"  <#if role.authorities?seq_contains("admin:carInfoDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="zxq_08_02_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:carInfoStatus"  <#if role.authorities?seq_contains("admin:carInfoStatus")> checked="checked"</#if>/>设为无效/有效&nbsp;&nbsp;
					<input class="zxq_08_02_05" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:carInfoLead"  <#if role.authorities?seq_contains("admin:carInfoLead")> checked="checked"</#if>/>导入&nbsp;&nbsp;
					<input class="zxq_08_02_05" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:carDaochu"  <#if role.authorities?seq_contains("admin:carDaochu")> checked="checked"</#if>/>导出
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_08_03" class="zxq_08_03" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:supplierList"  <#if role.authorities?seq_contains("admin:supplierList")> checked="checked"</#if>/>供应商管理
				</td>
				<td>
					<input class="zxq_08_03_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:supplierAdd"  <#if role.authorities?seq_contains("admin:supplierAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_08_03_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:supplierEdit"  <#if role.authorities?seq_contains("admin:supplierEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_08_03_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:supplierDel"  <#if role.authorities?seq_contains("admin:supplierDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="zxq_08_03_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:supplierStatus"  <#if role.authorities?seq_contains("admin:supplierStatus")> checked="checked"</#if>/>设为无效/有效
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_08_04" class="zxq_08_04" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:driverDutyList"  <#if role.authorities?seq_contains("admin:driverDutyList")> checked="checked"</#if>/>司机考勤列表
				</td>
				<td>
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_08_05" class="zxq_08_05" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:driverActionList"  <#if role.authorities?seq_contains("admin:driverActionList")> checked="checked"</#if>/>司机事件列表
				</td>
				<td>
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_08_06" class="zxq_08_06" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:driverAppraisal"  <#if role.authorities?seq_contains("admin:driverAppraisal")> checked="checked"</#if>/>司机评分管理
				</td>
				<td>
					<input class="zxq_08_06_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:driverAppraisalHistory"  <#if role.authorities?seq_contains("admin:driverAppraisalHistory")> checked="checked"</#if>/>司机历史评分
				</td>
			</tr>
			
			<#-- 查询司机详细信息 add by zsc 2016-1-21 -->
			<tr >
				<td>
					<input id="zsc_08_07" class="zxq_08_07" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:queryDriverInfo"  <#if role.authorities?seq_contains("admin:queryDriverInfo")> checked="checked"</#if>/>查询司机详情
				</td>
				<td>
				</td>
			</tr>
			<#-- 车队管理end -->
			
			<#-- 基础信息管理start -->
			<tr >
				<th style="width:130px;" rowspan="36">
					<input class="zxq_07" onclick="checks(1, this);" type="checkbox" name="authorities" value="admin:base"  <#if role.authorities?seq_contains("admin:base")> checked="checked"</#if>/>基础信息管理:
				</th>
				<td style="width:130px;">
					<input id="zxq_07_01" class="zxq_07_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:scopeList"  <#if role.authorities?seq_contains("admin:scopeList")> checked="checked"</#if>/>使用范围管理
				</td>
				<td>
					<input class="zxq_07_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:scopeAdd"  <#if role.authorities?seq_contains("admin:scopeAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_07_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:scopeEdit"  <#if role.authorities?seq_contains("admin:scopeEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_07_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:scopeDel"  <#if role.authorities?seq_contains("admin:scopeDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="zxq_07_01_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:scopeStatus"  <#if role.authorities?seq_contains("admin:scopeStatus")> checked="checked"</#if>/>设为无效/有效
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_07_02" class="zxq_07_02" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:fancyList"  <#if role.authorities?seq_contains("admin:fancyList")> checked="checked"</#if>/>用车喜好管理
				</td>
				<td>
					<input class="zxq_07_02_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:fancyAdd"  <#if role.authorities?seq_contains("admin:fancyAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_07_02_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:fancyEdit"  <#if role.authorities?seq_contains("admin:fancyEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_07_02_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:fancyDel"  <#if role.authorities?seq_contains("admin:fancyDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="zxq_07_02_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:fancyStatus"  <#if role.authorities?seq_contains("admin:fancyStatus")> checked="checked"</#if>/>设为无效/有效
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_07_03" class="zxq_07_03" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:airportList"  <#if role.authorities?seq_contains("admin:airportList")> checked="checked"</#if>/>机场管理
				</td>
				<td>
					<input class="zxq_07_03_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:airportAdd"  <#if role.authorities?seq_contains("admin:airportAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_07_03_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:airportEdit"  <#if role.authorities?seq_contains("admin:airportEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_07_03_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:airportDel"  <#if role.authorities?seq_contains("admin:airportDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="zxq_07_03_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:airportStatus"  <#if role.authorities?seq_contains("admin:airportStatus")> checked="checked"</#if>/>设为无效/有效
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_07_04" class="zxq_07_04" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:advertiseList"  <#if role.authorities?seq_contains("admin:advertiseList")> checked="checked"</#if>/>手机广告图管理
				</td>
				<td>
					<input class="zxq_07_04_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:advertiseAdd"  <#if role.authorities?seq_contains("admin:advertiseAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_07_04_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:advertiseEdit"  <#if role.authorities?seq_contains("admin:advertiseEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_07_04_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:advertiseDel"  <#if role.authorities?seq_contains("admin:advertiseDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="zxq_07_04_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:advertiseStatus"  <#if role.authorities?seq_contains("admin:advertiseStatus")> checked="checked"</#if>/>设为无效/有效
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_07_05" class="zxq_07_05" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:cityList"  <#if role.authorities?seq_contains("admin:cityList")> checked="checked"</#if>/>城市管理
				</td>
				<td>
					<input class="zxq_07_05_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:cityAdd"  <#if role.authorities?seq_contains("admin:cityAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_07_05_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:cityEdit"  <#if role.authorities?seq_contains("admin:cityEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_07_05_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:cityDel"  <#if role.authorities?seq_contains("admin:cityDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="zxq_07_05_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:cityStatus"  <#if role.authorities?seq_contains("admin:cityStatus")> checked="checked"</#if>/>设为无效/有效
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_07_06" class="zxq_07_06" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:serviceList"  <#if role.authorities?seq_contains("admin:serviceList")> checked="checked"</#if>/>服务类型管理
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_07_07" class="zxq_07_07" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:cancelReasonList"  <#if role.authorities?seq_contains("admin:cancelReasonList")> checked="checked"</#if>/>取消原因管理
				</td>
				<td>
					<input class="zxq_07_07_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:cancelReasonAdd"  <#if role.authorities?seq_contains("admin:cancelReasonAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_07_07_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:cancelReasonEdit"  <#if role.authorities?seq_contains("admin:cancelReasonEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_07_07_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:cancelReasonDel"  <#if role.authorities?seq_contains("admin:cancelReasonDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="zxq_07_07_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:cancelReasonStatus"  <#if role.authorities?seq_contains("admin:cancelReasonStatus")> checked="checked"</#if>/>设为无效/有效
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_07_08" class="zxq_07_08" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:modelList"  <#if role.authorities?seq_contains("admin:modelList")> checked="checked"</#if>/>车型管理
				</td>
				<td>
					<input class="zxq_07_08_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:modelAdd"  <#if role.authorities?seq_contains("admin:modelAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_07_08_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:modelEdit"  <#if role.authorities?seq_contains("admin:modelEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_07_08_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:modelDel"  <#if role.authorities?seq_contains("admin:modelDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="zxq_07_08_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:modelStatus"  <#if role.authorities?seq_contains("admin:modelStatus")> checked="checked"</#if>/>设为无效/有效
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_07_09" class="zxq_07_09" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:groupList"  <#if role.authorities?seq_contains("admin:groupList")> checked="checked"</#if>/>车型类别管理
				</td>
				<td>
					<input class="zxq_07_09_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:groupAdd"  <#if role.authorities?seq_contains("admin:groupAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_07_09_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:groupEdit"  <#if role.authorities?seq_contains("admin:groupEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_07_09_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:groupDel"  <#if role.authorities?seq_contains("admin:groupDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="zxq_07_09_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:groupStatus"  <#if role.authorities?seq_contains("admin:groupStatus")> checked="checked"</#if>/>设为无效/有效
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_97_09" class="zxq_97_09" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:promotionList"  <#if role.authorities?seq_contains("admin:promotionList")> checked="checked"</#if>/>车型升级配置
				</td>
				<td>
					<input class="zxq_97_09_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:promotionAdd"  <#if role.authorities?seq_contains("admin:promotionAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_97_09_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:promotionEdit"  <#if role.authorities?seq_contains("admin:promotionEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_97_09_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:promotionDel"  <#if role.authorities?seq_contains("admin:promotionDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_07_10" class="zxq_07_10" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:costTypeList"  <#if role.authorities?seq_contains("admin:costTypeList")> checked="checked"</#if>/>费用价格管理
				</td>
				<td>
					<input class="zxq_07_10_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:costTypeAdd"  <#if role.authorities?seq_contains("admin:costTypeAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_07_10_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:costTypeEdit"  <#if role.authorities?seq_contains("admin:costTypeEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_07_10_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:costTypeDel"  <#if role.authorities?seq_contains("admin:costTypeDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="zxq_07_10_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:costTypeStatus"  <#if role.authorities?seq_contains("admin:costTypeStatus")> checked="checked"</#if>/>设为无效/有效
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_07_11" class="zxq_07_11" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:charteredgroupList"  <#if role.authorities?seq_contains("admin:charteredgroupList")> checked="checked"</#if>/>包车车型管理
				</td>
				<td>
					<input class="zxq_07_11_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:charteredgroupAdd"  <#if role.authorities?seq_contains("admin:charteredgroupAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_07_11_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:charteredgroupEdit"  <#if role.authorities?seq_contains("admin:charteredgroupEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_07_11_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:charteredgroupDel"  <#if role.authorities?seq_contains("admin:charteredgroupDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="zxq_07_11_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:charteredgroupStatus"  <#if role.authorities?seq_contains("admin:charteredgroupStatus")> checked="checked"</#if>/>设为无效/有效
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_07_12" class="zxq_07_12" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:nightServiceTime"  <#if role.authorities?seq_contains("admin:nightServiceTime")> checked="checked"</#if>/>夜间服务时间管理
				</td>
				<td>
					<input class="zxq_07_12_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:nightServiceTimeAdd"  <#if role.authorities?seq_contains("admin:nightServiceTimeAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_07_12_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:nightServiceTimeEdit"  <#if role.authorities?seq_contains("admin:nightServiceTimeEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_07_13" class="zxq_07_13" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:groupImgList"  <#if role.authorities?seq_contains("admin:groupImgList")> checked="checked"</#if>/>车型图片管理
				</td>
				<td>
					<input class="zxq_07_13_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:groupImgAdd"  <#if role.authorities?seq_contains("admin:groupImgAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_07_13_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:groupImgEdit"  <#if role.authorities?seq_contains("admin:groupImgEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_07_13_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:groupImgDel"  <#if role.authorities?seq_contains("admin:groupImgDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="zxq_07_13_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:groupImgStatus"  <#if role.authorities?seq_contains("admin:groupImgStatus")> checked="checked"</#if>/>设为无效/有效
				</td>
			</tr>
			
			<tr>
				<td>
					<input id="cl_07_01" class="cl_07_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:newsList"  <#if role.authorities?seq_contains("admin:newsList")> checked="checked"</#if>/>公司消息
				</td>
				<td>
					<input class="cl_07_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:newsSave"  <#if role.authorities?seq_contains("admin:newsSave")> checked="checked"</#if>/>增加&nbsp;&nbsp;
				</td>
			</tr>
			<#-- add by zsc 2016-1-8 -->
			<tr>
				<td>
					<input id="zsc_07_14" class="zsc_07_14" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:appraisalList"  <#if role.authorities?seq_contains("admin:appraisalList")> checked="checked"</#if>/>司机评价管理
				</td>
				<td>
				</td>
			</tr>
			
				<tr >
				<td>
					<input id="cl_08_01" class="cl_08_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:tradingAreaList"  <#if role.authorities?seq_contains("admin:tradingAreaList")> checked="checked"</#if>/>商圈管理
				</td>
				<td>
					<input class="cl_08_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:tradingAreaAdd"  <#if role.authorities?seq_contains("admin:tradingAreaAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_08_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:tradingAreaEdit"  <#if role.authorities?seq_contains("admin:tradingAreaEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_08_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:tradingAreaDel"  <#if role.authorities?seq_contains("admin:tradingAreaDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="cl_08_01_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:tradingAreaStatus"  <#if role.authorities?seq_contains("admin:tradingAreaStatus")> checked="checked"</#if>/>设为无效/有效
				</td>
			</tr>
			
			<tr >
				<td>
					<input id="cl_79_01" class="cl_79_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:foreacstList"  <#if role.authorities?seq_contains("admin:foreacstList")> checked="checked"</#if>/>预估时间比例设置
				</td>
				<td>
					<input class="cl_79_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:foreacstSave"  <#if role.authorities?seq_contains("admin:foreacstSave")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_79_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:foreacstEdit"  <#if role.authorities?seq_contains("admin:foreacstEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_79_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:foreacstDel"  <#if role.authorities?seq_contains("admin:foreacstDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr >
				<td>
					<input id="cl_89_01" class="cl_89_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:cityOrderServiceList"  <#if role.authorities?seq_contains("admin:cityOrderServiceList")> checked="checked"</#if>/>订单服务城市设置
				</td>
				<td>
					<input class="cl_89_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:cityOrderServiceAdd"  <#if role.authorities?seq_contains("admin:cityOrderServiceAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_89_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:cityOrderServiceDel"  <#if role.authorities?seq_contains("admin:cityOrderServiceDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr >
				<td>
					<input id="cl_199_01" class="cl_199_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:adList"  <#if role.authorities?seq_contains("admin:adList")> checked="checked"</#if>/>广告位管理
				</td>
				<td>
					<input class="cl_199_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:adAdd"  <#if role.authorities?seq_contains("admin:adAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_199_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:adEdit"  <#if role.authorities?seq_contains("admin:adEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_199_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:adDel"  <#if role.authorities?seq_contains("admin:adDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="cl_199_01_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:adSupervise"  <#if role.authorities?seq_contains("admin:adSupervise")> checked="checked"</#if>/>广告管理
				</td>
			</tr>
			
				<tr >
				<td>
					<input id="cl_200_01" class="cl_200_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:adInfoList"  <#if role.authorities?seq_contains("admin:adInfoList")> checked="checked"</#if>/>广告管理
				</td>
				<td>
					<input class="cl_200_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:adInfoAdd"  <#if role.authorities?seq_contains("admin:adInfoAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_200_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:adInfoEdit"  <#if role.authorities?seq_contains("admin:adInfoEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_200_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:adInfoDel"  <#if role.authorities?seq_contains("admin:adInfoDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="cl_210_01" class="cl_210_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:terminalsList"  <#if role.authorities?seq_contains("admin:terminalsList")> checked="checked"</#if>/>自助终端设备默认地址管理
				</td>
				<td>
					<input class="cl_210_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:terminalsAdd"  <#if role.authorities?seq_contains("admin:terminalsAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_210_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:terminalsEdit"  <#if role.authorities?seq_contains("admin:terminalsEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_210_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:terminalsDel"  <#if role.authorities?seq_contains("admin:terminalsDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="cl_220_01" class="cl_220_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:terminalsAddrList"  <#if role.authorities?seq_contains("admin:terminalsAddrList")> checked="checked"</#if>/>自助终端设备快捷地址管理
				</td>
				<td>
					<input class="cl_220_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:terminalsAddrAdd"  <#if role.authorities?seq_contains("admin:terminalsAddrAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_220_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:terminalsAddrEdit"  <#if role.authorities?seq_contains("admin:terminalsAddrEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_220_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:terminalsAddrDel"  <#if role.authorities?seq_contains("admin:terminalsAddrDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="cl_230_01" class="cl_230_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:customerclassconfigList"  <#if role.authorities?seq_contains("admin:customerclassconfigList")> checked="checked"</#if>/>会员等级配置
				</td>
				<td>
					<input class="cl_230_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:customerclassconfigAdd"  <#if role.authorities?seq_contains("admin:customerclassconfigAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_230_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:customerclassconfigEdit"  <#if role.authorities?seq_contains("admin:customerclassconfigEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_230_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:customerclassconfigDel"  <#if role.authorities?seq_contains("admin:customerclassconfigDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="cl_240_01" class="cl_240_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:classacountproportionList"  <#if role.authorities?seq_contains("admin:classacountproportionList")> checked="checked"</#if>/>会员成长值加成比例
				</td>
				<td>
					<input class="cl_240_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:classacountproportionAdd"  <#if role.authorities?seq_contains("admin:classacountproportionAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_240_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:classacountproportionEdit"  <#if role.authorities?seq_contains("admin:classacountproportionEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_240_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:classacountproportionDel"  <#if role.authorities?seq_contains("admin:classacountproportionDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="cl_250_01" class="cl_250_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:inviteNewCustomer"  <#if role.authorities?seq_contains("admin:inviteNewCustomer")> checked="checked"</#if>/>新用户邀请统计
				</td>
				<td></td>
			</tr>
			
			<tr >
				<td>
					<input id="cl_270_01" class="cl_270_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:companyinfoList"  <#if role.authorities?seq_contains("admin:companyinfoList")> checked="checked"</#if>/>网约车平台公司基础信息
				</td>
				<td></td>
			</tr>
			
			
			<tr >
				<td>
					<input id="cl_218_01" class="cl_218_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:companypaymentList"  <#if role.authorities?seq_contains("admin:companypaymentList")> checked="checked"</#if>/>网约车平台公司支付信息
				</td>
				<td>
					<input class="cl_218_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:companypaymentAdd"  <#if role.authorities?seq_contains("admin:companypaymentAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_218_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:companypaymentEdit"  <#if role.authorities?seq_contains("admin:companypaymentEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_218_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:companypaymentDel"  <#if role.authorities?seq_contains("admin:companypaymentDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr >
				<td>
					<input id="cl_219_01" class="cl_219_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:servicecompanyList"  <#if role.authorities?seq_contains("admin:servicecompanyList")> checked="checked"</#if>/>网约车平台公司服务机构管理
				</td>
				<td>
					<input class="cl_219_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:servicecompanyAdd"  <#if role.authorities?seq_contains("admin:servicecompanyAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_219_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:servicecompanyEdit"  <#if role.authorities?seq_contains("admin:servicecompanyEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_219_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:servicecompanyDel"  <#if role.authorities?seq_contains("admin:servicecompanyDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr >
				<td>
					<input id="cl_319_01" class="cl_319_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:sensitiveList"  <#if role.authorities?seq_contains("admin:sensitiveList")> checked="checked"</#if>/>敏感词管理
				</td>
				<td>
					<input class="cl_319_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:sensitiveAdd"  <#if role.authorities?seq_contains("admin:sensitiveAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_319_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:sensitiveEdit"  <#if role.authorities?seq_contains("admin:sensitiveEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_319_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:sensitiveDel"  <#if role.authorities?seq_contains("admin:sensitiveDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="cl_319_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:sensitiveAppr"  <#if role.authorities?seq_contains("admin:sensitiveAppr")> checked="checked"</#if>/>审批通过&nbsp;&nbsp;
					<input class="cl_319_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:sensitiveOnLine"  <#if role.authorities?seq_contains("admin:sensitiveOnLine")> checked="checked"</#if>/>上线&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr >
				<td>
					<input id="cl_319_02" class="cl_319_02" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:languageServiceList"  <#if role.authorities?seq_contains("admin:languageServiceList")> checked="checked"</#if>/>语言服务费管理
				</td>
				<td>
					<input class="cl_319_02_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:languageServiceAdd"  <#if role.authorities?seq_contains("admin:languageServiceAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_319_02_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:languageServiceEdit"  <#if role.authorities?seq_contains("admin:languageServiceEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_319_02_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:languageServiceDel"  <#if role.authorities?seq_contains("admin:languageServiceDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr >
				<td>
					<input id="cl_319_03" class="cl_319_03" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:carConfigList"  <#if role.authorities?seq_contains("admin:carConfigList")> checked="checked"</#if>/>订单池配置管理
				</td>
				<td>
					<input class="cl_319_03_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:carConfigAdd"  <#if role.authorities?seq_contains("admin:carConfigAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_319_03_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:carConfigEdit"  <#if role.authorities?seq_contains("admin:carConfigEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_319_03_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:carConfigDel"  <#if role.authorities?seq_contains("admin:carConfigDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="cl_319_04" class="cl_319_04" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:messageSendList"  <#if role.authorities?seq_contains("admin:messageSendList")> checked="checked"</#if>/>消息推送管理
				</td>
				<td>
					<input class="cl_319_04_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:messageSendAdd"  <#if role.authorities?seq_contains("admin:messageSendAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_319_04_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:messageSendEdit"  <#if role.authorities?seq_contains("admin:messageSendEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_319_04_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:messageSendDel"  <#if role.authorities?seq_contains("admin:messageSendDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					
					<input class="cl_319_04_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:messageSendPush"  <#if role.authorities?seq_contains("admin:messageSendPush")> checked="checked"</#if>/>消息推送&nbsp;&nbsp;
					
				</td>
			</tr>
			
			<tr >
				<td>
					<input id="cl_314_04" class="cl_314_04" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:faceOddsList"  <#if role.authorities?seq_contains("admin:faceOddsList")> checked="checked"</#if>/>人脸识别配置
				</td>
				<td>
					<input class="cl_314_04_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:faceOddsAdd"  <#if role.authorities?seq_contains("admin:faceOddsAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_314_04_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:faceOddsEdit"  <#if role.authorities?seq_contains("admin:faceOddsEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_314_04_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:faceOddsDel"  <#if role.authorities?seq_contains("admin:faceOddsDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr >
				<td>
					<input id="cl_354_04" class="cl_354_04" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:faceRateList"  <#if role.authorities?seq_contains("admin:faceRateList")> checked="checked"</#if>/>人脸识别配置（按城市）
				</td>
				<td>
					<input class="cl_354_04_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:faceRateAdd"  <#if role.authorities?seq_contains("admin:faceRateAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_354_04_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:faceRateEdit"  <#if role.authorities?seq_contains("admin:faceRateEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_354_04_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:faceRateDel"  <#if role.authorities?seq_contains("admin:faceRateDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr >
				<td>
					<input id="cl_394_04" class="cl_394_04" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:billObjectionList"  <#if role.authorities?seq_contains("admin:billObjectionList")> checked="checked"</#if>/>账单异议配置
				</td>
				<td>
					<input class="cl_394_04_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:billObjectionAdd"  <#if role.authorities?seq_contains("admin:billObjectionAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_394_04_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:billObjectionEdit"  <#if role.authorities?seq_contains("admin:billObjectionEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_394_04_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:billObjectionDelete"  <#if role.authorities?seq_contains("admin:billObjectionDelete")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			
			<#-- 基础信息管理end -->
			
			<#-- 价格管理start -->
			<tr >
				<th style="width:130px;">
					<input class="zxq_04" onclick="checks(1, this);" type="checkbox" name="authorities" value="admin:pringPlan"  <#if role.authorities?seq_contains("admin:pringPlan")> checked="checked"</#if>/>价格管理:
				</th>
				<td style="width:130px;">
					<input id="zxq_04_01" class="zxq_04_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:pringPlanList"  <#if role.authorities?seq_contains("admin:pringPlanList")> checked="checked"</#if>/>价格计划管理
				</td>
				<td>
					<input class="zxq_04_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:pringPlanEdit"  <#if role.authorities?seq_contains("admin:pringPlanEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_04_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:pringPlanDel"  <#if role.authorities?seq_contains("admin:pringPlanDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="zxq_04_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:pringPlanPriceQt"  <#if role.authorities?seq_contains("admin:pringPlanPriceQt")> checked="checked"</#if>/>定价&nbsp;&nbsp;
					<input class="zxq_04_01_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:pringPlanPriceAdd"  <#if role.authorities?seq_contains("admin:pringPlanPriceAdd")> checked="checked"</#if>/>定价增加&nbsp;&nbsp;
					<input class="zxq_04_01_05" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:pringPlanPriceEdit"  <#if role.authorities?seq_contains("admin:pringPlanPriceEdit")> checked="checked"</#if>/>定价修改&nbsp;&nbsp;
					<input class="zxq_04_01_06" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:pringPlanPriceDel"  <#if role.authorities?seq_contains("admin:pringPlanPriceDel")> checked="checked"</#if>/>定价删除&nbsp;&nbsp;
					<input class="zxq_04_01_07" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:pringPlanAdd"  <#if role.authorities?seq_contains("admin:pringPlanAdd")> checked="checked"</#if>/>新建计划&nbsp;&nbsp;
					<input class="zxq_04_01_08" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:pringPlanPriceBase"  <#if role.authorities?seq_contains("admin:pringPlanPriceBase")> checked="checked"</#if>/>配置基本计划&nbsp;&nbsp;
					<input class="zxq_04_01_09" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:pringPlanPriceAddBase"  <#if role.authorities?seq_contains("admin:pringPlanPriceAddBase")> checked="checked"</#if>/>基本计划增加&nbsp;&nbsp;
					<input class="zxq_04_01_10" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:pringPlanPriceEditBase"  <#if role.authorities?seq_contains("admin:pringPlanPriceEditBase")> checked="checked"</#if>/>基本计划修改&nbsp;&nbsp;
					<input class="zxq_04_01_11" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:pringPlanPriceDelBase"  <#if role.authorities?seq_contains("admin:pringPlanPriceDelBase")> checked="checked"</#if>/>基本计划删除
				</td>
			</tr>
			<#-- 价格管理end -->
			
			<#-- 运营策略start -->
			<tr >
				
				<th style="width:130px;" rowspan="37">
					<input class="zxq_09" onclick="checks(1, this);" type="checkbox" name="authorities" value="admin:award"  <#if role.authorities?seq_contains("admin:award")> checked="checked"</#if>/>运营策略:
				</th>
				<#-- 
				<td style="width:130px;">
					<input id="zxq_09_01" class="zxq_09_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:operate"  <#if role.authorities?seq_contains("admin:operate")> checked="checked"</#if>/>平台运营
				</td>
				-->
				<td>
				</td>
				
			</tr>
			<tr >
				<td>
					<input id="zxq_09_02" class="zxq_09_02" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:paySet"  <#if role.authorities?seq_contains("admin:paySet")> checked="checked"</#if>/>支付设置
				</td>
				<td>
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_09_03" class="zxq_09_03" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:hotTimeSet"  <#if role.authorities?seq_contains("admin:hotTimeSet")> checked="checked"</#if>/>高峰时段设置
				</td>
				<td>
					<input class="zxq_09_03_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:hotTimeAdd"  <#if role.authorities?seq_contains("admin:hotTimeAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_09_03_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:hotTimeSetSet"  <#if role.authorities?seq_contains("admin:hotTimeSetSet")> checked="checked"</#if>/>设置&nbsp;&nbsp;
					<input class="zxq_09_03_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:hotTimeDelete"  <#if role.authorities?seq_contains("admin:hotTimeDelete")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_09_04" class="zxq_09_04" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:driverAward"  <#if role.authorities?seq_contains("admin:driverAward")> checked="checked"</#if>/>司机奖励
				</td>
				<td>
				</td>
			</tr>
			<tr >
				<td>
					<input id="cl_099_01" class="cl_099_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:commissionList"  <#if role.authorities?seq_contains("admin:commissionList")> checked="checked"</#if>/>司机奖励（新）
				</td>
				<td>
					<input class="cl_099_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:commissionSave"  <#if role.authorities?seq_contains("admin:commissionSave")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_099_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:commissionEdit"  <#if role.authorities?seq_contains("admin:commissionEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_099_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:commissionDel"  <#if role.authorities?seq_contains("admin:commissionDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_09_05" class="zxq_09_05" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:doorManAward"  <#if role.authorities?seq_contains("admin:doorManAward")> checked="checked"</#if>/>代理人奖励
				</td>
				<td>
				</td>
			</tr>
				<tr >
				<td>
					<input id="zxq_09_10" class="zxq_09_10" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:spreadMobileList"  <#if role.authorities?seq_contains("admin:spreadMobileList")> checked="checked"</#if>/>短息推广
				</td>
				<td>
					<input class="zxq_09_10_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:spreadMobileAdd"  <#if role.authorities?seq_contains("admin:spreadMobileAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_09_10_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:spreadMobileDel"  <#if role.authorities?seq_contains("admin:spreadMobileDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_09_06" class="zxq_09_06" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:mobileVersion"  <#if role.authorities?seq_contains("admin:mobileVersion")> checked="checked"</#if>/>手机端版本发布
				</td>
				<td>
					<input class="zxq_09_06_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:mobileVersionAdd"  <#if role.authorities?seq_contains("admin:mobileVersionAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_09_06_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:mobileVersionEdit"  <#if role.authorities?seq_contains("admin:mobileVersionEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_09_06_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:mobileVersionPublish"  <#if role.authorities?seq_contains("admin:mobileVersionPublish")> checked="checked"</#if>/>发布&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_09_07" class="zxq_09_07" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:carBizSpecialList"  <#if role.authorities?seq_contains("admin:carBizSpecialList")> checked="checked"</#if>/>个人中心活动
				</td>
				<td>
					<input class="zxq_09_07_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:carBizSpecialAdd"  <#if role.authorities?seq_contains("admin:carBizSpecialAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_09_07_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:carBizSpecialEdit"  <#if role.authorities?seq_contains("admin:carBizSpecialEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_09_07_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:carBizSpecialDelete"  <#if role.authorities?seq_contains("admin:carBizSpecialDelete")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_09_08" class="zxq_09_08" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:hotTimeHolidaySet"  <#if role.authorities?seq_contains("admin:hotTimeHolidaySet")> checked="checked"</#if>/>高峰时段设置(节假日)
				</td>
				<td>
					<input class="zxq_09_08_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:hotTimeHolidayAdd"  <#if role.authorities?seq_contains("admin:hotTimeHolidayAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_09_08_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:hotTimeHolidaySetSet"  <#if role.authorities?seq_contains("admin:hotTimeHolidaySetSet")> checked="checked"</#if>/>设置&nbsp;&nbsp;
					<input class="zxq_09_08_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:hotTimeHolidayDelete"  <#if role.authorities?seq_contains("admin:hotTimeHolidayDelete")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="cl_99_01" class="cl_99_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:carBizOrderRuleList"  <#if role.authorities?seq_contains("admin:carBizOrderRuleList")> checked="checked"</#if>/>派单规则
				</td>
				<td>
					
				</td>
			</tr>
			<tr >
				<td>
					<input id="cl_98_01" class="cl_98_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:tradingAreaOrderRuleList"  <#if role.authorities?seq_contains("admin:tradingAreaOrderRuleList")> checked="checked"</#if>/>派单规则（商圈）
				</td>
				<td>
					<input class="cl_98_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:tradingAreaOrderRuleAdd"  <#if role.authorities?seq_contains("admin:tradingAreaOrderRuleAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_98_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:tradingAreaOrderRuleEdit"  <#if role.authorities?seq_contains("admin:tradingAreaOrderRuleEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_98_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:tradingAreaOrderRuleDel"  <#if role.authorities?seq_contains("admin:tradingAreaOrderRuleDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
					<input class="cl_98_01_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:tradingAreaOrderRuleStatus"  <#if role.authorities?seq_contains("admin:tradingAreaOrderRuleStatus")> checked="checked"</#if>/>设为无效/有效
				</td>
			</tr>
			
			<tr >
				<td>
					<input id="cl_201_01" class="cl_201_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:limitList"  <#if role.authorities?seq_contains("admin:limitList")> checked="checked"</#if>/>派单规则（限行）
				</td>
				<td>
					<input class="cl_201_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:limitAdd"  <#if role.authorities?seq_contains("admin:limitAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_201_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:limitEdit"  <#if role.authorities?seq_contains("admin:limitEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="cl_201_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:limitDel"  <#if role.authorities?seq_contains("admin:limitDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			
			<#-- 注释掉 派单规则节假日 及平时
			<tr >
				<td>
					<input id="zxq_09_09" class="zxq_09_09" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:orderRuleHolidayList"  <#if role.authorities?seq_contains("admin:orderRuleHolidayList")> checked="checked"</#if>/>派单规则(节假日)
				</td>
				<td>
					<input class="zxq_09_09_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:orderRuleHolidayAdd"  <#if role.authorities?seq_contains("admin:orderRuleHolidayAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_09_09_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:orderRuleHolidayUpdate"  <#if role.authorities?seq_contains("admin:orderRuleHolidayUpdate")> checked="checked"</#if>/>修改&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_09_10" class="zxq_09_10" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:orderRuleNormalList"  <#if role.authorities?seq_contains("admin:orderRuleNormalList")> checked="checked"</#if>/>派单规则(平时)
				</td>
				<td>
					<input class="zxq_09_10_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:orderRuleNormalAdd"  <#if role.authorities?seq_contains("admin:orderRuleNormalAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_09_10_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:orderRuleNormalUpdate"  <#if role.authorities?seq_contains("admin:orderRuleNormalUpdate")> checked="checked"</#if>/>修改&nbsp;&nbsp;
				</td>
			</tr>
			-->
			<tr >
				<td>
					<input id="zxq_09_11" class="zxq_09_11" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:waitingPriceList"  <#if role.authorities?seq_contains("admin:waitingPriceList")> checked="checked"</#if>/>司机等候设置费用
				</td>
				<td>
					<input class="zxq_09_11_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:waitingPriceAdd"  <#if role.authorities?seq_contains("admin:waitingPriceAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_09_11_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:waitingPriceUpdate"  <#if role.authorities?seq_contains("admin:waitingPriceUpdate")> checked="checked"</#if>/>修改&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_09_12" class="zxq_09_12" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:designatedDriverPriceList"  <#if role.authorities?seq_contains("admin:designatedDriverPriceList")> checked="checked"</#if>/>指定司机服务费设置
				</td>
				<td>
					<input class="zxq_09_12_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:designatedDriverPriceAdd"  <#if role.authorities?seq_contains("admin:designatedDriverPriceAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_09_12_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:designatedDriverPriceUpdate"  <#if role.authorities?seq_contains("admin:designatedDriverPriceUpdate")> checked="checked"</#if>/>修改&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_99_05" class="zxq_99_05" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:damageList"  <#if role.authorities?seq_contains("admin:damageList")> checked="checked"</#if>/>违约金后台管理
				</td>
				<td>
					<input class="zxq_99_05_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:damageAdd"  <#if role.authorities?seq_contains("admin:damageAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_99_05_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:damageEdit"  <#if role.authorities?seq_contains("admin:damageEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="cl_99_02" class="cl_99_02" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:IsVerificationCode"  <#if role.authorities?seq_contains("admin:IsVerificationCode")> checked="checked"</#if>/>验证码开关设置
				</td>
				<td>
				</td>
			</tr>
			<tr >
				<td>
					<input id="cl_99_113" class="cl_99_113" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:outCostList"  <#if role.authorities?seq_contains("admin:outCostList")> checked="checked"</#if>/>价外费用占订单比率设置
				</td>
				<td>
					<input class="cl_99_113_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:outCostAdd"  <#if role.authorities?seq_contains("admin:outCostAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="cl_99_113_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:outCostUpdate"  <#if role.authorities?seq_contains("admin:outCostUpdate")> checked="checked"</#if>/>修改&nbsp;&nbsp;
				</td>
			</tr>
			<tr >
				<td>
					<input id="yp_99_114" class="yp_99_114" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:cityPhoneList"  <#if role.authorities?seq_contains("admin:cityPhoneList")> checked="checked"</#if>/>隐藏手机号城市配置
				</td>
				<td>
					<input class="yp_99_114_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:cityPhoneAdd"  <#if role.authorities?seq_contains("admin:cityPhoneAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="yp_99_114_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:cityPhoneUpdate"  <#if role.authorities?seq_contains("admin:cityPhoneUpdate")> checked="checked"</#if>/>修改&nbsp;&nbsp;
				</td>
			</tr>
			<#--addBy zsc 2016-5-19 优化平台运营配置管理 -->
			<tr >
				<td>
				</td>
				<td>
				<input id="zsc_99_113" class="zsc_99_113" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:tipList"  <#if role.authorities?seq_contains("admin:tipList")> checked="checked"</#if>/>提示语配置
				</td>
					<td>
				</td>
			</tr>
			<tr >
				<td>
					<input id="zsc_99_113" class="zsc_99_113" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:tipList"  <#if role.authorities?seq_contains("admin:tipList")> checked="checked"</#if>/>提示语配置
				</td>
				<td>
				</td>
			</tr>
			<tr >
				<td>
					<input id="wfc_99_113" class="wfc_99_113" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:cityTipList"  <#if role.authorities?seq_contains("admin:cityTipList")> checked="checked"</#if>/>提示语配置
				</td>
				<td>
				</td>
			</tr>
			<tr >
				<td>
					<input id="zxq_099_03" class="zxq_099_03" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:settleConfigList"  <#if role.authorities?seq_contains("admin:settleConfigList")> checked="checked"</#if>/>渠道结算配置
				</td>
				<td>
					<input class="zxq_099_03_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:settleConfigAdd"  <#if role.authorities?seq_contains("admin:settleConfigAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_099_03_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:settleConfigEdit"  <#if role.authorities?seq_contains("admin:settleConfigEdit")> checked="checked"</#if>/>设置&nbsp;&nbsp;
					<input class="zxq_099_03_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:settleConfigDel"  <#if role.authorities?seq_contains("admin:settleConfigDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			<#--addBy zsc 2016-9-14 邀请新用户配置管理 -->
			<tr >
				<td>
					<input id="zsc_99_112" class="zsc_99_112" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:invitationList"  <#if role.authorities?seq_contains("admin:invitationList")> checked="checked"</#if>/>邀请新用户配置管理
				</td>
				<td>
				</td>
			</tr>
			<tr >
				<td>
					<input id="zsc_899_112" class="zsc_899_112" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:inviteRechargeList"  <#if role.authorities?seq_contains("admin:inviteRechargeList")> checked="checked"</#if>/>邀请好友充值返现配置
				</td>
				<td>
				</td>
			</tr>
			<#--addBy zsc 2016-9-19司机评价返券后台配置 -->
			<tr>
				<td>
					<input id="zsc_099_114" class="zsc_099_114" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:appraiseReturnCouponList" 
					 <#if role.authorities?seq_contains("admin:appraiseReturnCouponList")> checked="checked"</#if>/>司机评价返券设置 
				</td>
				<td>
				</td>
			</tr>
		   <#--addBy zsc 2016-9-26派单规则后台配置 -->
			<tr>
				<td>
					<input id="zsc_099_115" class="zsc_099_115" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:sendOrderOperationList" 
					 <#if role.authorities?seq_contains("admin:sendOrderOperationList")> checked="checked"</#if>/>派单规则后台配置 
				</td>
				<td>
				</td>
			</tr>
			
			<tr>
				<td>
					<input id="zsc_099_116" class="zsc_099_116" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:memberLevelList" 
					 <#if role.authorities?seq_contains("admin:memberLevelList")> checked="checked"</#if>/>会员等级策略管理
				</td>
				<td>
					<input class="zsc_099_116_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:memberLevelAdd"  <#if role.authorities?seq_contains("admin:memberLevelAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zsc_099_116_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:memberLevelEdit"  <#if role.authorities?seq_contains("admin:memberLevelEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zsc_099_116_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:memberLevelDel"  <#if role.authorities?seq_contains("admin:memberLevelDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			
			<#--addby wangfc 2017-01-04 渠道规划 -->
			<tr>
				<td>
					<input id="zsc_099_117" class="zsc_099_117" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:driverTipsList" 
					<#if role.authorities?seq_contains("admin:driverTipsList")> checked="checked"</#if>/>渠道规划
				</td>
				<td></td>
			</tr>
			
			<tr>
				<td>
					<input id="zsc_299_116" class="zsc_299_116" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:cooperationList" 
					 <#if role.authorities?seq_contains("admin:cooperationList")> checked="checked"</#if>/>第三方派单配置
				</td>
				<td>
					<input class="zsc_299_116_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:cooperationAdd"  <#if role.authorities?seq_contains("admin:cooperationAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zsc_299_116_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:cooperationEdit"  <#if role.authorities?seq_contains("admin:cooperationEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zsc_299_116_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:cooperationDel"  <#if role.authorities?seq_contains("admin:cooperationDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr>
				<td>
					<input id="zsc_999_116" class="zsc_999_116" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:ctripBuyoutPercentList" 
					 <#if role.authorities?seq_contains("admin:ctripBuyoutPercentList")> checked="checked"</#if>/>携程一口价比率
				</td>
				<td>
					<input class="zsc_999_116_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:ctripBuyoutPercentAdd"  <#if role.authorities?seq_contains("admin:ctripBuyoutPercentAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zsc_999_116_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:ctripBuyoutPercentEdit"  <#if role.authorities?seq_contains("admin:ctripBuyoutPercentEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					
				</td>
			</tr>
			
			<tr>
				<td>
					<input id="zsc_799_116" class="zsc_799_116" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:percentList" 
					 <#if role.authorities?seq_contains("admin:percentList")> checked="checked"</#if>/>一口价比率设置
				</td>
				<td>
					<input class="zsc_799_116_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:percentAdd"  <#if role.authorities?seq_contains("admin:percentAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zsc_799_116_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:percentEdit"  <#if role.authorities?seq_contains("admin:percentEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					
				</td>
			</tr>
			<#-- addBy zsc 2017-03-16 新增数据上报后台配置-->
			<tr>
				<td>
					<input id="zsc_799_117" class="zsc_799_116" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:pushDataList" 
					 <#if role.authorities?seq_contains("admin:pushDataList")> checked="checked"</#if>/>数据上报后台配置
				</td>
				<td>
					<input class="zsc_799_117_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:pushDataAdd"  <#if role.authorities?seq_contains("admin:pushDataAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zsc_799_117_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:pushDataEdit"  <#if role.authorities?seq_contains("admin:pushDataEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr>
				<td>
					<input id="zsc_999_117" class="zsc_999_117" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:hurlList" 
					 <#if role.authorities?seq_contains("admin:hurlList")> checked="checked"</#if>/>H5链接配置
				</td>
				<td>
					<input class="zsc_999_117_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:hurlAdd"  <#if role.authorities?seq_contains("admin:hurlAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zsc_999_117_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:hurlEdit"  <#if role.authorities?seq_contains("admin:hurlEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zsc_999_117_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:hurlDel"  <#if role.authorities?seq_contains("admin:hurlDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr>
				<td>
					<input id="zsc_999_187" class="zsc_999_187" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:discountList" 
					 <#if role.authorities?seq_contains("admin:discountList")> checked="checked"</#if>/>优惠折扣配置
				</td>
				<td>
					<input class="zsc_999_187_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:discountAdd"  <#if role.authorities?seq_contains("admin:discountAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zsc_999_187_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:discountEdit"  <#if role.authorities?seq_contains("admin:discountEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zsc_999_187_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:discountDel"  <#if role.authorities?seq_contains("admin:discountDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr>
				<td>
					<input id="zsc_949_187" class="zsc_949_187" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:probabilityList" 
					 <#if role.authorities?seq_contains("admin:probabilityList")> checked="checked"</#if>/>微信支付宝扫码开关
				</td>
				<td>
					<input class="zsc_949_187_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:probabilityEdit"  <#if role.authorities?seq_contains("admin:probabilityEdit")> checked="checked"</#if>/>修改
				</td>
			</tr>
			<#-- 运营策略end -->
			
			<#-- 系统管理start -->
			<tr >
				<th rowspan="5" style="width:130px;">
					<input class="zxq_01" onclick="checks(1, this);" type="checkbox" name="authorities" value="admin:sys"  <#if role.authorities?seq_contains("admin:sys")> checked="checked"</#if>/>系统管理:
				</th>
				<td style="width:130px;">
					<input id="zxq_01_01" class="zxq_01_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:user"  <#if role.authorities?seq_contains("admin:user")> checked="checked"</#if>/>员工管理
				</td>
				<td>
					<input class="zxq_01_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:userAdd"  <#if role.authorities?seq_contains("admin:userAdd")> checked="checked"</#if>/>员工增加&nbsp;&nbsp;
					<input class="zxq_01_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:userEdit"  <#if role.authorities?seq_contains("admin:userEdit")> checked="checked"</#if>/>员工编辑&nbsp;&nbsp;
					<#--  updateBy zsc 2016-7-4 
					<input class="zxq_01_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:userDel"  <#if role.authorities?seq_contains("admin:userDel")> checked="checked"</#if>/>员工删除&nbsp;&nbsp;
					-->
					<input class="zxq_01_01_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:userResetPassword"  <#if role.authorities?seq_contains("admin:userResetPassword")> checked="checked"</#if>/>修改密码
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_01_02" class="zxq_01_02" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:role"  <#if role.authorities?seq_contains("admin:role")> checked="checked"</#if>/>角色权限管理
				</td>
				<td>
					<input class="zxq_01_02_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:roleAdd"  <#if role.authorities?seq_contains("admin:roleAdd")> checked="checked"</#if>/>角色增加&nbsp;&nbsp;
					<input class="zxq_01_02_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:roleEdit"  <#if role.authorities?seq_contains("admin:roleEdit")> checked="checked"</#if>/>角色编辑&nbsp;&nbsp;
					<input class="zxq_01_02_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:roleDel"  <#if role.authorities?seq_contains("admin:roleDel")> checked="checked"</#if>/>角色删除
				</td>
			</tr>
			<tr>
				<td>
					<input id="zxq_01_03" class="zxq_01_03" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:area"  <#if role.authorities?seq_contains("admin:area")> checked="checked"</#if>/>地区管理
				</td>
				<td>
					<input class="zxq_01_03_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:areaEdit"  <#if role.authorities?seq_contains("admin:areaEdit")> checked="checked"</#if>/>地区编辑&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr>
				<td rowspan="2">
					<input id="zxq_01_04" class="zxq_01_04" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:sysparam"  <#if role.authorities?seq_contains("admin:sysparam")> checked="checked"</#if>/>系统变量管理
				</td>
				<td>
					<input class="zxq_01_04_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:sysparamAdd"  <#if role.authorities?seq_contains("admin:sysparamAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_01_04_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:sysparamEdit"  <#if role.authorities?seq_contains("admin:sysparamEdit")> checked="checked"</#if>/>修改&nbsp;&nbsp;
					<input class="zxq_01_04_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:sysparamDel"  <#if role.authorities?seq_contains("admin:sysparamDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
            <tr>
                <td>
                    <input class="zxq_01_04_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:param:PREFERENTIAL_POLICY"  <#if role.authorities?seq_contains("admin:param:PREFERENTIAL_POLICY")> checked="checked"</#if>/>公测返现活动&nbsp;&nbsp;
                    <input class="zxq_01_04_05" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:param:RECHARGE_PROCESSUS"  <#if role.authorities?seq_contains("admin:param:RECHARGE_PROCESSUS")> checked="checked"</#if>/>充值红包活动&nbsp;&nbsp;
                    <input class="zxq_01_04_06" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:param:ONLINETIME"  <#if role.authorities?seq_contains("admin:param:ONLINETIME")> checked="checked"</#if>/>在线时长模块开通城市&nbsp;&nbsp;
                    <input class="zxq_01_04_07" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:param:PUSH"  <#if role.authorities?seq_contains("admin:param:PUSH")> checked="checked"</#if>/>推送设置&nbsp;&nbsp;
                </td>
            </tr>
			<#--
			<tr>
				<td>
					<input type="checkbox" name="authorities" value="admin:user"  <#if role.authorities?seq_contains("admin:dictionary")> checked="checked"</#if>/>字典管理
				</td>
				<td>
					<input type="checkbox" name="authorities" value="admin:userList"  <#if role.authorities?seq_contains("admin:userList")> checked="checked"</#if>/>字典查询&nbsp;&nbsp;
					<input type="checkbox" name="authorities" value="admin:userAdd"  <#if role.authorities?seq_contains("admin:userAdd")> checked="checked"</#if>/>字典增加&nbsp;&nbsp;
					<input type="checkbox" name="authorities" value="admin:userEdit"  <#if role.authorities?seq_contains("admin:userEdit")> checked="checked"</#if>/>字典编辑&nbsp;&nbsp;
					<input type="checkbox" name="authorities" value="admin:userDel"  <#if role.authorities?seq_contains("admin:userEdit")> checked="checked"</#if>/>字典删除&nbsp;&nbsp;
				</td>
			</tr>
			-->
			<#-- 系统管理END -->
			<#-- 百度优惠设置start -->
			<tr >
				<th rowspan="5" style="width:130px;">
					<input class="zxq_01" onclick="checks(1, this);" type="checkbox" name="authorities" value="admin:baidu"  <#if role.authorities?seq_contains("admin:baidu")> checked="checked"</#if>/>百度优惠设置:
				</th>
				<td style="width:130px;">
					<input id="zxq_01_01" class="zxq_01_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="admin:baiduList"  <#if role.authorities?seq_contains("admin:baiduList")> checked="checked"</#if>/>百度优惠设置
				</td>
				<td>
					<input class="zxq_01_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:baiduAdd"  <#if role.authorities?seq_contains("admin:baiduAdd")> checked="checked"</#if>/>增加&nbsp;&nbsp;
					<input class="zxq_01_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:baiduEdit"  <#if role.authorities?seq_contains("admin:baiduEdit")> checked="checked"</#if>/>编辑&nbsp;&nbsp;
					<input class="zxq_01_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="admin:baiduDel"  <#if role.authorities?seq_contains("admin:baiduDel")> checked="checked"</#if>/>删除&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
			<#-- 百度优惠设置END -->
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
<input id="roleNames" type="hidden" value="<#list roleNames as name>${name},</#list>"/>
<script type="text/javascript">
$("#form").validator({
	rules: {
		myRemote: function(){
   			return	hadUsed() || '该角色名称已存在';
    	}
	},
	fields: {
        roleName : {
        	rule :'required;myRemote;'
        } 
    },
});


function hadUsed(name){
	var names = $('#roleNames').val().split(',');
	var name = $('#roleName').val();
	
	var hadName = false;
	if(names.length>0){
		for(var i=0; i<names.length; i++){
			if(name == names[i]){
				hadName = true;
				return false;
			}
		}
	} else {
		return true;
	}
	
	if(hadName){
		return false;
	} else {
		return true;
	}
}

//选中
function checks(lv, o){
	var obj = $(o).attr('class');
	var arr = obj.split('_');
	if(lv == 1){
		if($(o).prop("checked")){
			$("input[class^='"+obj+"']").prop("checked", true);
		} else {
			$("input[class^='"+obj+"']").prop("checked", false);
		}
	} else if(lv == 2){
		var lv1 = arr[0]+'_'+arr[1];
		if(judgeThisLVHadChecked(lv, arr)){
			$("."+lv1).prop("checked", true);
		} else {
			$("."+lv1).prop("checked", false);
		}
		
		if($(o).prop("checked")){
			$("input[class^='"+obj+"']").prop("checked", true);
		} else {
			$("input[class^='"+obj+"']").prop("checked", false);
		}
	} else if(lv == 3){
		var lv1 = arr[0]+'_'+arr[1];
		var lv2 = arr[0]+'_'+arr[1]+'_'+arr[2];
		if(judgeThisLVHadChecked(lv, arr)){
			$("."+lv2).prop("checked", true);
		} 
		//else {
		//	$("."+lv2).prop("checked", false);
		//}
		
		if(judgeThisLVHadChecked(2, arr)){
			$("."+lv1).prop("checked", true);
		} 
		//else {
		//	$("."+lv1).prop("checked", false);
		//}
	}
	
};


// 判断同等级下是否有选中的
function judgeThisLVHadChecked(lv, arr){
	var count = 0;
	
	if(lv == 2){
		var o = arr[0]+'_'+arr[1];
		$("input[id][class^='"+o+"']").each(function(){
			if($(this).prop("checked")){
				count += 1;
			}
		});
	} else if(lv == 3) {
		var o = arr[0]+'_'+arr[1]+'_'+arr[2]+'_';
		$("input[class^='"+o+"']").each(function(){
			if($(this).prop("checked")){
				count += 1;
			}
		});
	}

	if(count > 0){
		return true;
	} else {
		return false;
	}
}

</script>

</body>
</html>