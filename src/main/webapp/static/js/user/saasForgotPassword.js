//var locationSearch=location.search;

var vm = new Vue({
    //绑定
    el: "#forgotPassword",
    //数据
    data: {
    	saasUserPhone:'',
    	saasUserCode:'',
    	saasUserNewPassword:'',
    	saasUserNewPassword1:''
    },
    //加载
    mounted: function () {
        this.$nextTick(function () {
            this.loadingState = true;
        });

    },
    //方法
    methods: {
        saasForgotPassword:function () {
	        var _self = this;
	        //校验手机号
	        var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
	        if(!myreg.test(this.saasUserPhone)){
	        	 swal('请输入有效的手机号码！');
	        	 return false;
	        }
	        //验证码必填校验
	        if(this.saasUserCode==null || this.saasUserCode.replace(/(^\s*)|(\s*$)/g,'') ==""){
	        	swal('验证码必填！');
	       	    return false;
	        }
	        //密码必填校验
	        if(this.saasUserNewPassword==null ||this.saasUserNewPassword.replace(/(^\s*)|(\s*$)/g,'') ==""){
	        	swal('密码必填！');
	       	    return false;
	        }
	        //确认密码校验
            if(this.saasUserNewPassword != this.saasUserNewPassword1){
            	swal('您两次输入的密码不一样！');
           	    return false;
            }
	        this.$http.post(__baseURL+"/user/resetPassword",{loginAccount:this.saasUserPhone,code:this.saasUserCode,password:this.saasUserNewPassword},{emulateJSON: true})
	            .then((response)=>{
	                 var resultData=response.data;
	                 if(resultData.responseCode==100000){
	                     /*console.log(loginData);*/
	                     if (confirm("修改成功！")) {
	                    	 window.location=__baseURL;
	                     }
	                 }
	                 if(resultData.responseCode!=100000){
	                	 /*console.log(loginData);*/
	                	 swal(resultData.responseMessage);
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
	var m = $("#saasUserPhone").val(), regCode = $('#regCode');
	 var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
    if(!myreg.test(m)){
    	 swal('请输入有效的手机号码！');
    	 $("#saasUserPhone").focus();
    	 return false;
    }
    regCode.addClass('dis');
    
    if(flag){			
		//获取手机验证码TODO
    	$.ajax({
			url: __baseURL + "/user/sendMessage",
			type: 'POST',
			dataType: 'json',
			data: {
				   mobile:m,
				   type:'forgotPassword'
				  },
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
						swal('验证码已发送到您手机，请及时查收。');	   
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


