//var locationSearch=location.search;

var vm = new Vue({
    //绑定
    el: "#userRegister",
    //数据
    data: {
    	saasVideoRegisterUser:'',
    	saasVideoRegisterCode:'',
    	saasVideoRegisterPassword:'',
    	saasVideoRegisterPassword1:'',
    	saasVideoRegisterCheck:false
    },
    //加载
    mounted: function () {
        this.$nextTick(function () {
            this.loadingState = true;
        });
    },
    //方法
    methods: {
    	saasVideoRegisterEmail:function () {
            var _self = this;
            if(this.saasVideoRegisterCheck==false){
            	swal('请选择用户协议！');
           	    return false;
            }
            //校验邮箱
            var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
            if(!myreg.test(this.saasVideoRegisterUser)){
            	 swal('请输入有效的E_mail！');
            	 return false;
            }
            //验证码必填校验
            if(this.saasVideoRegisterCode==null || this.saasVideoRegisterCode.replace(/(^\s*)|(\s*$)/g,'') ==""){
            	swal('验证码必填！');
           	    return false;
            }
            //密码必填校验
            if(this.saasVideoRegisterPassword==null ||this.saasVideoRegisterPassword.replace(/(^\s*)|(\s*$)/g,'') ==""){
            	swal('密码必填！');
           	    return false;
            }
            //确认密码校验
            if(this.saasVideoRegisterPassword1 != this.saasVideoRegisterPassword){
            	swal('您两次输入的密码不一样！');
           	    return false;
            }
            this.$http.post(__baseURL+"/user/register",{loginAccount:this.saasVideoRegisterUser,code:this.saasVideoRegisterCode,password:this.saasVideoRegisterPassword,
            	type:'0'},{emulateJSON: true})
                .then((response)=>{
                     var loginData=response.data;
                     if(loginData.responseCode==100000){
                         console.log(loginData);
                         if (confirm("注册成功！")) {
	                    	 window.location=__baseURL;
	                     }
                     }
                     if(loginData.responseCode!=100000){
                    	 console.log(loginData);
                    	 swal(loginData.responseMessage);
                     }
                  },
                 (error)=>{
                       console.log(error);
                     }
            );
        },
        saasVideoRegisterPhone:function () {
	        var _self = this;
	        if(this.saasVideoRegisterCheck==false){
            	swal('请选择用户协议！');
           	    return false;
            }
	        //校验手机号
	        var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	        if(!myreg.test(this.saasVideoRegisterUser)){
	        	 swal('请输入有效的手机号码！');
	        	 return false;
	        }
	        //验证码必填校验
	        if(this.saasVideoRegisterCode==null || this.saasVideoRegisterCode.replace(/(^\s*)|(\s*$)/g,'') ==""){
	        	swal('验证码必填！');
	       	    return false;
	        }
	        //密码必填校验
	        if(this.saasVideoRegisterPassword==null ||this.saasVideoRegisterPassword.replace(/(^\s*)|(\s*$)/g,'') ==""){
	        	swal('密码必填！');
	       	    return false;
	        }
	        //确认密码校验
            if(this.saasVideoRegisterPassword1 != this.saasVideoRegisterPassword){
            	swal('您两次输入的密码不一样！');
           	    return false;
            }
	        this.$http.post(__baseURL+"/user/register",{loginAccount:this.saasVideoRegisterUser,code:this.saasVideoRegisterCode,password:this.saasVideoRegisterPassword,
	        	type:'0'},{emulateJSON: true})
	            .then((response)=>{
	                 var loginData=response.data;
	                 if(loginData.responseCode==100000){
	                     console.log(loginData);
	                     if (confirm("注册成功！")) {
	                    	 window.location=__baseURL;
	                     }
	                 }
	                 if(loginData.responseCode!=100000){
	                	 console.log(loginData);
	                	 swal(loginData.responseMessage);
	                 }
	              },
	             (error)=>{
	                   console.log(error);
	                 }
	        );
	    }
    }
});

var flag = true, num = 60, timer = null, isReg = 0;
$('#regCode').click(function () {
	var m = $("#saasVideoRegisterUser").val(), regCode = $('#regCode');
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
    if(!myreg.test(m)){
    	 swal('请输入有效的手机号码！');
    	 $("#saasVideoRegisterUser").focus();
    	 return false;
    }
    regCode.addClass('dis');
    
    if(flag){			
		//获取手机验证码TODO
    	$.ajax({
			url: __baseURL + "/user/sendMessage",
			type: 'POST',
			dataType: 'json',
			data: {mobile:m},
			success: function (req){
				switch(req.responseCode){
					case 100000 : {
						//短信发送成功
						flag = false;
						num = 60;
						regCode.text(num+'秒后重发');
						timer = setInterval(function (){
							if (num <= 1) {
								flag = true;
								regCode.removeClass('dis').text('重发验证码');
								clearInterval(timer);
								return false;
							}
							num--;
							regCode.text(num+'秒后重发');
						},1000);
						
						break;
					}
					case 100025 : {
						swal('该手机号码已注册，请使用其他手机号码！');
				    	$("#saasVideoRegisterUser").focus();
				    	 
						regCode.removeClass('dis').text('发送验证码');
						
						break;
					}
					default : {
						swal('出错了：' + req.responseMessage + '，请重试！');	    	    	 
						regCode.removeClass('dis').text('发送验证码');
					}
				}
			},
			error: function(){
				swal('服务异常，请稍后重试！');
				regCode.removeClass('dis').text('发送验证码');
			}
		});
	}else{
		swal('请查收短信');
	}	
});

var oSaasBack1=nilo.getById('aLogin'); //退出
oSaasBack1.onclick=function () { 
    window.location=__baseURL;
}

/**
 * 用户协议
 */
var oUserAgreement=nilo.getById('userAgreement');
oUserAgreement.onclick=function () { 
	window.location=__baseURL+'/user/agreement';
}
