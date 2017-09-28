// JavaScript Document
$(document).ready(function() {
	//关闭弹窗
	$('.close_pop').click(function(){
		$('.mask').fadeOut();
		$('.pop').fadeOut();
	});
});

/**
 * 跳转视频列表
 */
var oVideoList=nilo.getById('videoList');
oVideoList.onclick=function () { 
	var ticket=nilo.getCookie('ticket');
	window.location=__baseURL+'/video/videolist?page=1&ticket='+ticket;
}


/**
 * 跳转用户产品中心
 */

var oUserMembert=nilo.getById('userMember');
oUserMembert.onclick=function () { 
	window.location=__baseURL+"/user/products";
}


/**
 * 跳转终端管理
 */

var oTerminalList=nilo.getById('terminalList');
oTerminalList.onclick=function () { 
	window.location=__baseURL+"/user/terminal";
}

/**
 * 取消
 */
function cancel(){
	window.location=__baseURL+'/user/terminal';
}

/**
 * 修改保存
 */
function editSave(){
	var editAppUid = $("#editAppUid").val();
	var editAppName=$('#editAppName').val();
	/*var editAppKey=$('#editAppKey').val();*/
	var editDomainName=$('#editDomainName').val();
	/*var editAppType=$('#editAppType').val();*/
	var userTicket=nilo.getCookie('ticket');
	//js校验
	if(editAppName==null || editAppName.replace(/(^\s*)|(\s*$)/g,'') ==""){
		swal('请输入有效应用名！');
		return false;
	}
	
	var domainName=editDomainName.split(",");
	var reg=/^(([A-Za-z0-9-~]+)\.)+([A-Za-z0-9-~\/])+$/;
	for(var i=0;i<domainName.length;i++){
		 str = domainName[i];
		 if(!reg.test(str)){
			swal('输入域名不符合要求！');
			return false;
		 }
	}
	
	$.ajax({
		url:__baseURL+"/user/saveTerminal",
		type: 'POST',
		dataType: 'json',
		data:{
			appUid:editAppUid,
			appName:editAppName,
			domainName:editDomainName,
			userTicket:userTicket,
			type:1
		},
		success: function (req){        
            var responseCode=req.responseCode;       
            var responseMessage=req.responseMessage;
            
			if(responseCode==100000){
				if(confirm("操作成功")){
					window.location.href=__baseURL+"/user/terminal";
				}
            }else if(responseCode==100009){
            	swal('登录超时！');
				window.location.href=__baseURL;
            }else{
            	swal('出错了：' + req.responseMessage);	   
            }
			
		},
		error: function(){
			swal('服务异常，请稍后重试！');
		}
	});
}

/**
 * 新增保存
 */
function addSave(){
	var addAppName=$('#addAppName').val();
	/*var addAppKey=$('#addAppKey').val();*/
	var addDomainName=$('#addDomainName').val();
	var addAppType=$('#addAppType').val();
	var userTicket=nilo.getCookie('ticket');
	//js校验
	if(addAppName==null || addAppName.replace(/(^\s*)|(\s*$)/g,'') ==""){
		swal('请输入有效应用名！');
		return false;
	}
	
/*	if(addAppKey==null || addAppKey.replace(/(^\s*)|(\s*$)/g,'') ==""){
		swal('请输入有效APPKey！');
		return false;
	}*/
	
	if(addAppType==null || addAppType.replace(/(^\s*)|(\s*$)/g,'') ==""){
		swal('请输入有效应用类型！');
		return false;
	}
	
	var domainName=addDomainName.split(",");
	var reg=/^(([A-Za-z0-9-~]+)\.)+([A-Za-z0-9-~\/])+$/;
	for(var i=0;i<domainName.length;i++){
		 str = domainName[i];
		 if(!reg.test(str)){
			swal('输入域名不符合要求！');
			return false;
		 }
	}
	
	$.ajax({
		url:__baseURL+"/user/saveTerminal",
		type: 'POST',
		dataType: 'json',
		data:{
			appName:addAppName,
			/*appKey:addAppKey,*/
			domainName:addDomainName,
			appType:addAppType,
			userTicket:userTicket,
			type:0
		},
		success: function (req){        
            var responseCode=req.responseCode;       
            var responseMessage=req.responseMessage;
            
			if(responseCode==100000){
				if(confirm("操作成功")){
					window.location.href=__baseURL+"/user/terminal";
				}
            }else if(responseCode==100009){
            	swal('登录超时！');
				window.location.href=__baseURL;
            }else{
            	swal('出错了：' + req.responseMessage);	   
            }
			
		},
		error: function(){
			swal('服务异常，请稍后重试！');
		}
	});
}


/**
 * 删除保存
 */
function removeSave(){

	var appUid = $("#removeAppUid").val();
	var userTicket=nilo.getCookie('ticket');
/*	alert(appUid);
	return false;*/
	$.ajax({
		url: __baseURL + "/user/removeTerminal",
		type: 'POST',
		dataType: 'json',
		data: {
			appUid:appUid,
			userTicket:userTicket
			},
		success: function (req){        
            var responseCode=req.responseCode;       
            var responseMessage=req.responseMessage;
            
			if(responseCode==100000){
				if(confirm("操作成功")){
					window.location.href=__baseURL+"/user/terminal";
				}
            }else if(responseCode==100009){
            	swal('登录超时！');
				window.location.href=__baseURL;
            }else{
            	swal('出错了：' + req.responseMessage);	   
            }
		},
		error: function(){
			swal('服务异常，请稍后重试！');
		}
	});
}


var vm = new Vue({
    //绑定
    el: "#terminal",
    //数据
    data: {
        listItems:[]
    },
    //加载
    mounted: function () {
        this.$nextTick(function () {
            this.queryTerminal()
        });

    },
    //计算
    computed: {
    },
    //方法
    methods: {
    	//查询
    	queryTerminal: function () {
            var oSaasListQuit=nilo.getById('saasListQuit'); //退出
            var oSaasListUserName=nilo.getById('saasListUserName');//用户名;
            oSaasListQuit.onclick=function () {
                nilo.removeCookie('ticket');
                nilo.removeCookie('nickName');
                window.location=__baseURL;
            }
            
            oSaasListUserName.innerHTML=nilo.getCookie('nickName');
            if(nilo.getCookie('ticket')==null || nilo.getCookie('ticket')==''){
                window.location=__baseURL;
            }
            
            var listTicket=nilo.getCookie('ticket');
            var _self = this;
            this.$http.post(__baseURL+"/user/terminalList",{userTicket:listTicket},{emulateJSON: true})
                .then(
                    (response)=>{
                var listData=response.data;
                if(listData.responseCode==100000){
                    _self.listItems=listData.data;
                    console.log(listData.data);
                }
                else if(listData.responseCode==100009)
	        	{
                	window.location.href=__baseURL;
	        	}
        },
            (error)=>{
                console.log(error);
            }
            );
        },
        
        //修改弹框
        edit : function(appUid,appName,appKey,domainName,appType){
        	$('#editAppUid').val(appUid);
        	$('#editAppName').val(appName);
        	$('#editAppKey').val(appKey);
        	$('#editDomainName').val(domainName);
        	$('#editAppType').val(appType);
        	$('#editTerminal').show().addClass('animated flipInX');
        },
        
        //删除弹框
        remove: function(appUid,appName){
        	$('#removeAppUid').val(appUid);
        	$("#removeAppName").html(appName)
        	$('#removeTerminal').show().addClass('animated flipInX');
        },
        
        //新增弹框
        add: function(){
        	$('#addTerminal').show().addClass('animated flipInX');
        }
    }
});






