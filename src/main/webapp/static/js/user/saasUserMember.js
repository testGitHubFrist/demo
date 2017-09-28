// JavaScript Document  弹出修改密码框
	$(document).ready(function() {
      	$('.open_pop').click(function(){
			$('.mask').fadeIn();
			$('.pop').show().addClass('animated flipInX');
			
		});
		$('.close_pop').click(function(){
			$('.mask').fadeOut();
			$('.pop').fadeOut();
		}) 
		$(".sub_title > ul > li:nth-child(2)").addClass('active');
		$(".sub_title > ul > li").click(function(){
			$(this).addClass('active').siblings().removeClass('active');
		})  
    });

var vm = new Vue({
    //绑定
    el: "#memberList",
    //数据
    data: {
    },
    //加载
    mounted: function () {
        this.$nextTick(function () {
            this.queryAddress()
        });

    },
    //计算
    computed: {
       /* aDouble: function () {
            return this.videoFrameRate
        }*/
    },
    //方法
    methods: {
        queryAddress: function () {
            var oSaasListQuit=nilo.getById('saasListQuit'); //退出
            var oSaasListUserName=nilo.getById('saasListUserName');//用户名;
            var product=nilo.getById('product');//已购买产品;
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
            console.log(listTicket);
           
        },

    }
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
$('#userMember,#userProduct').click(function(){
	window.location=__baseURL+"/user/products";
});

/**
 * 修改密码
 */
var oMember=nilo.getById('editPassword');
oMember.onclick=function () { 
	 window.location=__baseURL+"/user/edit";
}

/**
 * 跳转终端管理
 */
$('#terminalList').click(function(){
	window.location=__baseURL+"/user/terminal";
});


/**
 * 修改密码
 */
editPassWord.onclick=function () {
	var password = $("#newPassWord").val();
	var password1 = $("#newPassWord1").val();
	var oldPassword=$("#oldPassWord").val();
	if(password.trim()==null||password.trim()==""){
		swal("请输入新密码！");
		return false;
	}
	if(oldPassword.trim()==null||oldPassword.trim()==""){
		swal("请输入原始密码！");
		return false;
	}
	
	//确认密码校验
    if(password != password1){
    	swal('您两次输入的密码不一样！');
   	    return false;
    }
	var userTicket=nilo.getCookie('ticket');
	$.ajax({
		url: __baseURL + "/user/editPassWord",
		type: 'POST',
		dataType: 'json',
		data: {
			newPassWord:password,
			oldPassword:oldPassword,
			ticket:userTicket
			},
		success: function (req){
			switch(req.responseCode){
				case 100000 : {
					if (confirm("修改成功！")) {
						window.location=__baseURL+"/user/products";
		             }
					break;
				}
				case 100009 : {
					swal('登录超时！');
					window.location.href=__baseURL;
					break;
				}
				default : {
					swal('出错了：' + req.responseMessage + '，请重试！');	    	    	 
				}
			}
		},
		error: function(){
			swal('服务异常，请稍后重试！');
			regCode.removeClass('dis').text('发送验证码');
		}
	});
}