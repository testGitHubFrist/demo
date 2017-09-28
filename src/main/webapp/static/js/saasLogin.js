//var locationSearch=location.search;

var vm = new Vue({
    //绑定
    el: "#saasLogin",
    //数据
    data: {
        saasVideoLoginUser:'',
        saasVideoLoginPassword:'',
        saasVideoLoginError:false
    },
    //加载
    mounted: function () {
        this.$nextTick(function () {
            this.loadingState = true;
           // this.saasVideoLoginSubmit();
            /*vm.saasVideoLoginSubmit();*/

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
       /* saasVideoLoginSubmit:function(event) {
            this.queryAddress();
        },*/
        saasVideoLoginSubmit:function () {
            var _self = this;
            this.$http.post(__baseURL+"/user/login",{name:this.saasVideoLoginUser,password:this.saasVideoLoginPassword},{emulateJSON: true})
                .then((response)=>{
                     var loginData=response.data;
                     if(loginData.responseCode==100000){
                         console.log(loginData);
                         nilo.setCookie('ticket',loginData.data.ticket,0.02);
                         nilo.setCookie('nickName',loginData.data.nickName,0.02);
                         _self.saasVideoLoginError=false;
                       // window.location='./index.html?page=1';
                        var ticket=loginData.data.ticket;
                        window.location=__baseURL+'/video/videolist?page=1&ticket='+ticket;
                     } else if(loginData.responseCode==100033){
                    	 nilo.setCookie('ticket',loginData.data.ticket,0.02);
                         nilo.setCookie('nickName',loginData.data.nickName,0.02);
                    	 window.location=__baseURL+"/user/products";
                     }else if(loginData.responseCode!=100000){
                         _self.saasVideoLoginError=true;
                     }
                  },
                 (error)=>{
                       console.log(error);
                     }
            );
        }


       /* queryAddress: function () {
            var _self = this;
            this.$http.post("https://studio.hypervideo.cn/user/login",{name:'jiangfeng',password:'123'},{emulateJSON: true})
                .then(
                    (response)=>{
                       /!*console.log(response);*!/
                       console.log(response.data)
            /!*console.log(data);*!/
                     },
                 (error)=>{
                       console.log(error);
                   }
            );
        },*/

        /*queryAddress: function () {
            var _self = this;
            this.$http.post("https://studio.hypervideo.cn/video/index",{page:'2',ticket:'b5bad9f0-cdef-4ee8-8bd2-b6f9e4496f95'},{emulateJSON: true})
                .then(
                    (response)=>{
                /!*console.log(response);*!/
                console.log(response.data)
            /!*console.log(data);*!/
        },
            (error)=>{
                console.log(error);
            }
            );
        },*/

    }
});

var oSaasBack1=nilo.getById('aForgotPwd'); //退出
oSaasBack1.onclick=function () { 
    window.location=__baseURL+"/user/forgotPassword";
}

var aRegist1=nilo.getById('aRegist1'); //退出
aRegist1.onclick=function () { 
    window.location=__baseURL+"/user/registerPhone";
}

var aRegist2=nilo.getById('aRegist2'); //退出
aRegist2.onclick=function () { 
    window.location=__baseURL+"/user/registerOrgPhone";
}

