// JavaScript Document
$(document).ready(function() {
	
	
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




var vm=new Vue({
	//绑定数据
	el:'#productList',
	
	//数据
	data:{
		listItems:[]
	},
	
	//加载
    mounted: function () {
        this.$nextTick(function () {
            this.queryProducts()
        });

    },
    
    //计算
    computed: {
    },
    
    //方法
    methods:{
    	queryProducts: function() {
    		 var oSaasListQuit=nilo.getById('saasListQuit'); //退出
             var oSaasListUserName=nilo.getById('saasListUserName');//用户名;
             oSaasListQuit.onclick=function () {
                 nilo.removeCookie('ticket');
                 nilo.removeCookie('nickName');
                 window.location=__baseURL;
             }
             oSaasListUserName.innerHTML=nilo.getCookie('nickName');
             var userTicket=nilo.getCookie('ticket');
             var _self = this;
             this.$http.post(__baseURL+"/product/list",{userTicket:userTicket},{emulateJSON: true})
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
    	
    	//点击产品事件
    	selectProduct : function(productPrice,productId) {
    		$('#productId').val(productId);
    		$('#price').val(productPrice);
    		document.getElementById("productPrice").innerText =productPrice;
    	},
    	
    	//购买
    	submitProduct : function(){
    		
    		//选中购买的商品
    		var productId=$('#productId').val();
    		//选中支付方式
    		var payType = $('input[name="tabs"]:checked').val();	
    		
    		if(productId==null || productId=='' ){
    			swal('请选择要购买的商品！');
    			return false;
    		}
    		
    		if(productId==0){
    			swal('暂无可购买的商品！');
    			return false;
    		}
    		
    		var price =$('#price').val();
    		if(payType!=1&&payType!=2&&payType!=3 &&productPrice!=0){
    			swal('请选择支付方式！');
    			return false;
    		}
    		
		    if(nilo.getCookie('ticket')==null || nilo.getCookie('ticket')==''){
              window.location=__baseURL;
            }
          
            var userTicket=nilo.getCookie('ticket');
            this.$http.post(__baseURL+"/user/buyProduct",{userTicket:userTicket,productId:productId,payType:payType},{emulateJSON: true})
              .then((response)=>{
                  var loginData=response.data;
                  if(loginData.responseCode==100000){
                      console.log(loginData);
                      if (confirm("购买成功！")) {
                    	  window.location=__baseURL+"/user/products";
	                     }
                  }
                  if(loginData.responseCode!=100000){
                 	 console.log(loginData);
                 	 swal(loginData.responseMessage);
                 	 window.location=__baseURL+"/user/productStore";
                  }
               },
              (error)=>{
                    console.log(error);
                  }
             );
    	}
    }
});





