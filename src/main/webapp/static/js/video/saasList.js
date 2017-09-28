function getQueryStringByName(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null){
        return unescape(r[2])
    }
    return null;
}

var vm = new Vue({
    //绑定
    el: "#saasList",
    //数据
    data: {
        listItems:[],
        saasListUploading:true,
        vuevideoLittleImgUrl:false,
        vuevideoLittleImgUrlDefault:false
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
            var oSaasListPage=nilo.getById('saasListPage');//分页
            var oMinusListPage=nilo.getById("minusListPage");//上一页
            var oAddListPage=nilo.getById('addListPage');//下一页
            var oUploadPage=nilo.getById('aUploadPage');//上传页面
            oSaasListQuit.onclick=function () {
                nilo.removeCookie('ticket');
                nilo.removeCookie('nickName');
                window.location=__baseURL;
            }
            
            oUploadPage.onclick=function () { 
                window.location=__baseURL+"/video/uploadPage";
            }
            oSaasListUserName.innerHTML=nilo.getCookie('nickName');
            var locationSearch=location.search;
            //var locationSearchSub=locationSearch.substring(6,locationSearch.length);
            var locationSearchSub = getQueryStringByName('page');

            if(locationSearchSub=="" || locationSearchSub==null){
                locationSearchSub=1;
            }
//            if(locationSearchSub==1){
//                this.saasListUploading=true;
//            }
//            else {
//                this.saasListUploading=false;
//            }
            if(nilo.getCookie('ticket')==null || nilo.getCookie('ticket')==''){
                window.location=__baseURL;
            }
            var listTicket=nilo.getCookie('ticket');
            var _self = this;
            this.$http.post(__baseURL+"/video/index",{page:locationSearchSub,ticket:listTicket},{emulateJSON: true})
                .then(
                    (response)=>{
                /*console.log(response);*/
                var listData=response.data;
                if(listData.responseCode==100000){
                    _self.listItems=listData.data.list;
                    console.log(listData.data.list)
                    for(var i=0; i<listData.data.list.length;i++){
                        if(listData.data.list[i].videoLittleImgUrl!="" ||listData.data.list[i].videoLittleImgUrl!=null){
                            this.vuevideoLittleImgUrl=true;
                        }
                        else {
                            this.vuevideoLittleImgUrlDefault=false;
                        }
                    }

                    //分页开始
                   var pageListSum=listData.data.totalPage;
                   // var pageListSum=20;

                    for(var i=0;i<pageListSum;i++){
                        var pageList=document.createElement('a');
                        //pageList.href="index.html?page="+parseInt(i+1);
                        pageList.href=__baseURL+'/video/videolist?page='+parseInt(i+1)+'&ticket='+listTicket;
                        pageList.innerHTML=i+1;
                        if(locationSearchSub==i+1){

                            /*var pageList=document.createElement('a');
                            pageList.href="index.html?page="+parseInt(i+1);
                            pageList.innerHTML=i+1;*/



                            pageList.className='active';
                            if(locationSearchSub==1){
                                console.log('------------')
                                oMinusListPage.className='forbid';
                                oMinusListPage.href='javascript:;'
                            }
                            else{
                               // oMinusListPage.href="index.html?page="+parseInt(i);
                                oMinusListPage.href=__baseURL+'/video/videolist?page='+parseInt(i)+'&ticket='+listTicket;
                                oMinusListPage.className='';
                            }
                            if(locationSearchSub==pageListSum){
                                oAddListPage.className='forbid';
                                oAddListPage.href='javascript:;';
                            }
                            else {
                               // oAddListPage.href="index.html?page="+parseInt(i+2);
                                oAddListPage.href=__baseURL+'/video/videolist?page='+parseInt(i+2)+'&ticket='+listTicket;
                                oAddListPage.className='';
                            }

                        }


                        oSaasListPage.getElementsByTagName('b')[0].appendChild(pageList)
                    }
                    //分页结束

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

    }
});

/**
 * 跳转个人中心
 */
var oSaasBack=nilo.getById('userMember');
oSaasBack.onclick=function () { 
    window.location=__baseURL+"/user/products";//默认跳转已购产品
}
