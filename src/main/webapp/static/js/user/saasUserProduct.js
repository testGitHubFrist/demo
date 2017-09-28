// JavaScript Document  弹出修改密码框
	$(document).ready(function() {
       
		$(".sub_title > ul > li:first-child").addClass('active');
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
            var userTicket=nilo.getCookie('ticket');
            this.$http.post(__baseURL+"/user/productInfo",{ticket:userTicket},{emulateJSON: true})
                .then(
                    (response)=>{
               /* console.log(response);*/
                var productInfo=response.data;
                console.log(productInfo);
                if(productInfo.responseCode==100000){
                   if(productInfo.data.productName!=null && productInfo.data.productName!=''){
                	   product.innerHTML=productInfo.data.productName;
                	  /* console.log(productInfo.data.ticket);*/
                   }
                }
                else if(productInfo.responseCode==100009)
	        	{
                	window.location.href=__baseURL;
	        	}
        },
            (error)=>{
                console.log(error);
            }
            );
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
 * 跳转产品商店
 */
var oProductStore=nilo.getById('productStore');
oProductStore.onclick=function () { 
	 window.location=__baseURL+"/user/productStore";
}

/**
 * 修改
 */
var oMember=nilo.getById('editPassword');
oMember.onclick=function () { 
	 window.location=__baseURL+"/user/edit";
}

/**
 * 跳转用户产品中心
 */
$('#userMember,#userProduct').click(function(){
	window.location=__baseURL+"/user/products";
});


/**
 * 跳转终端管理
 */
$('#terminalList').click(function(){
	window.location=__baseURL+"/user/terminal";
});

