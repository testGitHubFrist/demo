/**
 * Created by baikaishui on 2017/5/16.
 */

var reloadObjectInfo = false;

var vm = new Vue({
    //绑定
    el: "#saasVideoEditings",
    //数据
    data: {
        //视频编辑
        vueCompileTitle:'',
        vueCompileSubhead:'',
        vueCompileCenter:'',
        vueCompileVideoSrc:'',
        vueCompileVideoPoster:'',
        //obj数据
        vueObjFrameNo:'',
        vueObjX:'',
        vueObjY:'',
        vueObjW:'',
        vueObjH:'',
        //tag编辑
        vueTagObjectId:'',//id
        vueTagName:'', //名称
        vueTagContent:'',//描述
        vueTagStyleId:'',//样式
        vueTagStyleUrl:'',//样式url
        vueTagPopStyle:'',//交互方式
        vueTagPopUrl:'',//交互弹出链接
        vueTagFixed:'',//固定
        vueTagX:'',//x坐标
        vueTagY:'',//y坐标
        vueModifyTagX:"",//x改动坐标
        vueModifyTagY:"",//Y改动坐标
        vueTagDelaySecond:'',//标签显示时间
        vueTagId:'',//tagId

        //overlay选择
        vueOverlaySmallOverlayId01:'',//获取overlay id
        vueOverlaySmallOverlayId02:'',//获取overlay id
        vueOverlayId:'', //上传overlayId

        //传递参数
        vueContentTitle:'',//overlay标题
        vueContentTitleUrl:'',//overlay标题链接
        vueContentSummary:'',//摘要
        vueContentSummaryUrl:'',//摘要链接
        vueContentDesc:'',//中心
        vueContentDescUrl:'',//中心链接
        vueOverlayStyle:"",//浮层样式
        vueOverlayAnimation : '', //浮层动画
        vueOverlayPopUrl:"",

        //获取样式
        vueItationSelect:[],//样式数组
        
        //是否显示提示
        showTooltip: false,
        tagTipsText: '创建新标签热点',
        compileSubmitTipsText: '提交成功',
        overlaySubmitTipsText: '提交成功'
    },
    //加载
    mounted: function () {
        this.$nextTick(function () {
            this.queryAddress();
            this.vueOverlaySmallOverlayId();
            this.vueTagStyle();

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
        //获取视频信息
        queryAddress: function () {
            var oSaasListQuit=nilo.getById('saasListQuit'); //退出
            var oSaasListUserName=nilo.getById('saasListUserName');//用户名;
            var oSaasBack2=nilo.getById('saasBack2'); //退出
            var oSaasBack1=nilo.getById('saasBack1'); //退出

            oSaasListQuit.onclick=function () {
                nilo.removeCookie('ticket');
                nilo.removeCookie('nickName');
                window.location=__baseURL;
            }
            
            if(nilo.getCookie('ticket')==null || nilo.getCookie('ticket')==''){
            	window.location=__baseURL;
            }
            
            oSaasBack1.onclick=function () { 
            	var ticket=nilo.getCookie('ticket');
            	window.location=__baseURL+'/video/videolist?page=1&ticket='+ticket;
//                window.location=__baseURL+"/video/videolist?page=1";
            }
            oSaasBack2.onclick=function () { 
            	var ticket=nilo.getCookie('ticket');
            	window.location=__baseURL+'/video/videolist?page=1&ticket='+ticket;
//                window.location=__baseURL+"/video/videolist?page=1";
            }
            
            oSaasListUserName.innerHTML=nilo.getCookie('nickName');

            
            var listTicket=nilo.getCookie('ticket');
            var _self = this;
            var locationSearch=location.search;
            var locationSearchSub=locationSearch.substring(1,locationSearch.length);
            this.$http.post(__baseURL+"/video/description",{ticket:listTicket,videoId:locationSearchSub},{emulateJSON: true})
                .then(
                    (response)=>{
//                console.log(response);
                var listData=response.data;
                if(listData.responseCode==100000){
                    _self.vueCompileTitle=listData.data.titel1;
                    _self.vueCompileSubhead=listData.data.titel2;
                    _self.vueCompileCenter=listData.data.description;
                    _self.vueCompileVideoSrc=listData.data.url;
                    _self.vueCompileVideoPoster=listData.data.videoLoadingImgUrl;
                    /*console.log(listData.data.description)
                    console.log(listData)*/
                }
        },
            (error)=>{
                console.log(error);
            }
            );
        },

        vueTagStyle: function () { 
            var listTicket=nilo.getCookie('ticket'); 
            this.$http.post(__baseURL+"/tagStyleInfo/query",{ticket:listTicket},{emulateJSON: true})
                .then(
                    (response)=>{
                /*console.log(response);*/
                var listData=response.data;
            if(listData.responseCode==100000){
                this.vueItationSelect=listData.data; 
                //图标样式定义图片
                setTimeout(function () {
                    videoTagImitationSelectId(0);
                },10) 
            }
        },
            (error)=>{
                console.log(error);
            }
            );
        },

        //视频信息上传
        vueCompileSubmint:function () {
            var listTicket=nilo.getCookie('ticket');
            var _self = this;
            var locationSearch=location.search;
            var locationSearchSub=locationSearch.substring(1,locationSearch.length);
            this.$http.post(__baseURL+"/video/descrip",{ticket:listTicket,videoId:locationSearchSub,videoTitleMaster:this.vueCompileTitle,videoTitleSlave:this.vueCompileSubhead,videoDescriptioin:this.vueCompileCenter},{emulateJSON: true})
                .then(
                    (response)=>{
                /*console.log(response);*/
                var listData=response.data;
            if(listData.responseCode==100000){
            	if(this.compileSubmitTipsText == '已提交'){
            		swal('已提交');
            	}else{
            		swal(this.compileSubmitTipsText, '', 'success');
            	}                
                this.compileSubmitTipsText = '已提交';
               /* console.log('---------------')
                 console.log(listData)*/
            }
        },
            (error)=>{
                console.log(error);
            }
            );
        },

        //tag信息上传 overlay获取数据
        vueTagSubmint:function () {
            var listTicket=nilo.getCookie('ticket');
            var _self = this;
            var locationSearch=location.search;
            var locationSearchSub=locationSearch.substring(1,locationSearch.length);
            //OBJECTID
            var oVideoCompileObjectId=nilo.getById('videoCompileObjectId');//objectId
            this.vueTagObjectId=oVideoCompileObjectId.innerHTML;
            //TAGID
            var oVideoCompileTagId=nilo.getById("videoCompileTagId");//tagId
            this.vueTagId=oVideoCompileTagId.innerHTML;
            //tag标题
            var oVideoCompileTitle=nilo.getById('videoCompileTitle');
            this.vueTagName=oVideoCompileTitle.value;
            //标签描述
            var oVideoCompileDescribe=nilo.getById('videoCompileDescribe');
            this.vueTagContent=oVideoCompileDescribe.value
            //选择的样式
            var oVideoTagStyle=nilo.getById('videoCompileImitationSelectId').getElementsByTagName("h5")[0].getElementsByTagName("a")[0].innerHTML;
            this.vueTagStyleId=oVideoTagStyle;
            //样式图片链接
            var oVideoTagSmallIconId=nilo.getById('videoTagSmallIconId').src;
            this.vueTagStyleUrl=oVideoTagSmallIconId;
            //交互方式
            var oVideoTagInteraction=nilo.getById('videoTagInteraction').selectedIndex;
            this.vueTagPopStyle=oVideoTagInteraction;
            //固定
            var oVideoTagFixation=nilo.getById('videoTagFixation');
            //获取tagx
            var oVideoTagLocationXTxt=nilo.getById('videoTagLocationXTxt');
            var oVideoTagLocationYTxt=nilo.getById('videoTagLocationYTxt'); 
             
            if(oVideoTagFixation.checked){
                this.vueTagFixed=1; 
            }
            else{
                this.vueTagFixed=0; 
            }
            this.vueTagX=parseInt(oVideoTagLocationXTxt.value);
            this.vueTagY=parseInt(oVideoTagLocationYTxt.value);
            //标签显示时间
            var oVueTagCompileDelayTime=nilo.getById('vueTagCompileDelayTime');
            //在新窗口打开链接
            var oVideoCompileTxtNewHerf=nilo.getById('videoCompileTxtNewHerf');
            this.vueTagPopUrl=oVideoCompileTxtNewHerf.value;
            this.vueTagDelaySecond=parseInt(oVueTagCompileDelayTime.options[oVueTagCompileDelayTime.selectedIndex].value);
            //overlayURL
            var oVideoCompileTxtNewHerf=nilo.getById('videoOverlayNewHerf');
            this.vueOverlayPopUrl=oVideoCompileTxtNewHerf.value;
            
            //新增object时，需要把obj的坐标保存至服务器
            this.vueObjFrameNo = $('#curTime').val();
            this.vueObjX       = $('#videoTagXVal').val();
            this.vueObjY       = $('#videoTagYVal').val();
            this.vueObjW       = $('#videoTagWVal').val();
            this.vueObjH       = $('#videoTagHVal').val();
            
            this.$http.post(__baseURL+"/tag/modifyTags",
            		{ticket:listTicket,videoId:locationSearchSub,objectId:this.vueTagObjectId,tagId:this.vueTagId,
            			tagName:this.vueTagName,tagContent:this.vueTagContent,
            			tagStyleId:this.vueTagStyleId,tagStyleUrl:this.vueTagStyleUrl,
            			tagPopStyle:this.vueTagPopStyle,tagPopUrl:this.vueTagPopUrl,
            			tagFixed:this.vueTagFixed,tagX:this.vueTagX,tagY:this.vueTagY,delaySecond:this.vueTagDelaySecond,
            			overlayUrl:this.vueOverlayPopUrl,
            			objFrameNo:this.vueObjFrameNo,
            			objX:this.vueObjX,
            			objY:this.vueObjY,
            			objW:this.vueObjW,
            			objH:this.vueObjH
            		},
            		{emulateJSON: true})
                .then(
                    (response)=>{
                /*console.log(response);*/
                var listData=response.data;
            if(listData.responseCode==100000){
                loadingTips('<span class="tips"></span>提交成功', 0);
                
                oVideoCompileTagId.innerHTML=listData.data;
                console.log(listData.data);
                $('#videoTagBorderBrId .tagDivBorderSetUpA').append(this.vueTagName);
                //如果是新增，并且选择的是“弹出浮层”
                //if(isNewAdd){
                if(this.vueTagObjectId==null || this.vueTagObjectId=="0"){
                	reloadObjectInfo = true;
                    $('#loading').show();
                    checkVideoStatus();
            		if($('#videoTagInteraction').val() == 2){
            			$('#editPopup').trigger('click');
            		}
            	}
                
                $('#videoTagCloseId').trigger('click');
                
                pauseObj();
            }else{
            	swal('保存失败，请稍后重试！', '', 'error');
            }
        },
            (error)=>{
                console.log(error);
            }
            );
        },

        //overlay浮层

        vueOverlaySmallOverlayId:function () {
            var listTicket=nilo.getCookie('ticket');
            var _self = this;
            var locationSearch=location.search;
            var locationSearchSub=locationSearch.substring(1,locationSearch.length);
            this.$http.get(__baseURL+"/overlay/info",{params:{ticket:listTicket,videoId:locationSearchSub}},{emulateJSON: true})
                .then(
                    (response)=>{
                /*console.log(response);*/
                var listData=response.data;
            if(listData.responseCode==100000){
                _self.vueOverlaySmallOverlayId01=listData.data[0].overlayId;
                _self.vueOverlaySmallOverlayId02=listData.data[1].overlayId;
                /* console.log('---------------')
                 console.log(listData)*/
            }
        },
            (error)=>{
                console.log(error);
            }
            );
        },

        //overlay修改提交
        vueOverlayAddSubmit:function () {
            var listTicket=nilo.getCookie('ticket');
            var _self = this;
            var locationSearch=location.search;
            var locationSearchSub=locationSearch.substring(1,locationSearch.length);
            var oVideoCompileObjectId=nilo.getById('videoCompileObjectId');//objectId
            this.vueTagObjectId=oVideoCompileObjectId.innerHTML;
            var oVideoCompileTagId=nilo.getById("videoCompileTagId");//tagId
            this.vueTagId=oVideoCompileTagId.innerHTML;
            var oVueOverlaySmallOverlayId=nilo.getById('vueOverlaySmallOverlayId');
            var aVueOverlaySmallOverlayIdEm=oVueOverlaySmallOverlayId.getElementsByTagName('em');
            var vueOverlayIdIndex=0;
            var btnElem = $('.videoOverlayCompile:visible .go2PayBtn');
            
            for(var i=0;i<aVueOverlaySmallOverlayIdEm.length;i++){
                if(nilo.getStyle(aVueOverlaySmallOverlayIdEm[i],'display')=="block"){
                    this.vueOverlayId=aVueOverlaySmallOverlayIdEm[i].innerHTML;
                    vueOverlayIdIndex=i;
                    this.vueContentTitle=document.getElementById("vueContentTitle"+i).value;
                    this.vueContentTitleUrl=document.getElementById("vueContentTitleUrl"+i).innerHTML;
                    if(document.getElementById('vueContentSummary'+i)){
                        this.vueContentSummary=document.getElementById('vueContentSummary'+i).value;
                        this.vueContentSummaryUrl=document.getElementById('vueContentSummaryUrl'+i).innerHTML;
                    }
                    this.vueContentDesc=document.getElementById('vueContentDesc'+i).value;
                    this.vueContentDescUrl=document.getElementById('vueContentDescUrl'+i).innerHTML;
                }
            }
            //获取动画位置
            var oOverlayStyle=nilo.getById("overlayStyle");
            this.vueOverlayStyle=oOverlayStyle.options[oOverlayStyle.selectedIndex].value;
            var oOverlayAnimation = nilo.getById('overlayAnimation');
            this.vueOverlayAnimation = oOverlayAnimation.options[oOverlayAnimation.selectedIndex].value;
            this.vueTagPopStyle=2;
            this.$http.post(__baseURL+"/tagOverlay/modify",{
	            	ticket:listTicket,videoId:locationSearchSub,objectId:this.vueTagObjectId,
	            	tagId:this.vueTagId,overlayId:this.vueOverlayId,
	            		            	
	            	popStyle:this.vueOverlayStyle,popAnimation:this.vueOverlayAnimation,
	            	tagPopStyle:this.vueTagPopStyle,	            	
	            	
	            	p1_imgUrl: $('#p1').find('img').attr('src'), p1_actionUrl: $('#p1').attr('href'), 
	            	p2_title: $.trim($('#vueContentTitle0').val()),  p2_url: $.trim($('#vueContentTitleUrl0').text()), 
	            	p3_title: $.trim($('#vueContentSummary0').val()),  p3_url: $.trim($('#vueContentSummaryUrl0').text()), 
	            	p4_title: $.trim($('#vueContentDesc0').val()),  p4_url: $.trim($('#vueContentDescUrl0').text()), 
	            	p5_title: btnElem.text(),  p5_url: btnElem.attr('href')
	            	
            	},{emulateJSON: true})
                .then(
                    (response)=>{
                /*console.log(response);*/
                var listData=response.data;
            if(listData.responseCode==100000){
            	if(this.overlaySubmitTipsText == '已提交'){
            		swal('已提交');
            	}else{
            		swal(this.overlaySubmitTipsText, '', 'success');
            	}                
                this.overlaySubmitTipsText = '已提交';
                console.log(listData)
                /* console.log('---------------')
                 console.log(listData)*/
            }
            else {
            	swal('提交失败', '', 'error');
            }
        },
            (error)=>{
                console.log(error);
            }
            );
        },

        //overlay查找提交

        vueGetOverlayCenter:function () {
            var oVideoTagInteraction=nilo.getById("videoTagInteraction");
            //overlay暂时使用外部链接的方式
            return false;
            
            if(oVideoTagInteraction.selectedIndex==2){
                var listTicket=nilo.getCookie('ticket');
                var _self = this;
                var locationSearch=location.search;
                var locationSearchSub=locationSearch.substring(1,locationSearch.length);
                var oVideoCompileTagId=nilo.getById("videoCompileTagId");//tagId
                this.vueTagId=oVideoCompileTagId.innerHTML;
                var oVideoOverlayAllId=nilo.getById("videoOverlayAllId");
                if(this.vueTagId=="" || this.vueTagId==null){
                    swal("请先创建Tag在创建浮层");
                    oVideoOverlayAllId.style.display="none";
                    return true;
                }
                else {
                    oVideoOverlayAllId.style.display="block";
                }
                var oVueOverlaySmallOverlayId=nilo.getById('videoCompileOverlayId');
                var oVueOverlaySmallAnOverlayId=nilo.getById("vueOverlaySmallAnOverlayId");
                this.vueOverlayId=oVueOverlaySmallOverlayId.innerHTML;
                var oVueOverlaySmallOverlayOne=nilo.getById("vueOverlaySmallOverlayOne").innerHTML;//overlayId
                var oVueOverlaySmallOverlayTwo=nilo.getById("vueOverlaySmallOverlayTwo").innerHTML;//overlayId
                this.$http.get(__baseURL+"/tagOverlay/query",{params:{ticket:listTicket,videoId:locationSearchSub,tagId:this.vueTagId,overlayId:this.vueOverlayId}},{emulateJSON: true})
                .then(
                	(response)=>{
		                var listData=response.data;
		                if(listData.responseCode==100000){
		                    oVueOverlaySmallOverlayId.style.display="none";                    
		                    oVueOverlaySmallAnOverlayId.style.display="none";
		                    
		                    if(listData.data!=null){
		                        var oOverlayStyle=nilo.getById("overlayStyle");
		                        oOverlayStyle.options[listData.data.popStyle-1].selected=true;
		                        
		                        oVueOverlaySmallAnOverlayId.style.display="block";
		                        var oVueContentTitleUrl0=nilo.getById("vueContentTitleUrl0");
		                        var oVueContentTitle0=nilo.getById("vueContentTitle0");
		                        var oVueContentSummaryUrl0=nilo.getById("vueContentSummaryUrl0");
		                        var oVueContentSummary0=nilo.getById("vueContentSummary0")
		                        var oVueContentDescUrl0=nilo.getById("vueContentDescUrl0");
		                        var oVueContentDesc0=nilo.getById("vueContentDesc0");
		                        //第二个
		                        var oVueContentTitleUrl1=nilo.getById("vueContentTitleUrl1");
		                        var oVueContentTitle1=nilo.getById("vueContentTitle1");
		                        var oVueContentDescUrl1=nilo.getById("vueContentDescUrl1");
		                        var oVueContentDesc1=nilo.getById("vueContentDesc1");
		
		                        //显示内容
		                        var oVideoOverlayCompileOne=nilo.getById("videoOverlayCompileOne");
		                        var oVideoOverlayCompileTwo=nilo.getById("videoOverlayCompileTwo");
		
		                        //小的图标
		                        var oVueOverlaySmallOverlayOneS=nilo.getById("vueOverlaySmallOverlayOne");
		                        var oVueOverlaySmallOverlayTwoS=nilo.getById("vueOverlaySmallOverlayTwo");
		                        if(listData.data.overlayId==oVueOverlaySmallOverlayOne){
		                            oVideoOverlayCompileOne.style.display="block";
		                            oVideoOverlayCompileTwo.style.display="none";
		                            oVueOverlaySmallOverlayOneS.style.display="block";
		                            oVueOverlaySmallOverlayTwoS.style.display="none";
		                            if(listData.data.contentTitleUrl==''||listData.data.contentTitleUrl==null){
		                                oVueContentTitleUrl0.href="javascript:;";
		                            }
		                            else{
		                                oVueContentTitleUrl0.href=listData.data.contentTitleUrl;
		                            }
		                            oVueContentTitle0.value=listData.data.contentTitle;
		                            if(listData.data.contentSummaryUrl==''||listData.data.contentSummaryUrl==null){
		                                oVueContentSummaryUrl0.href="javascript:;";
		                            }
		                            else{
		                                oVueContentSummaryUrl0.href=listData.data.contentSummaryUrl;
		                            }
		                            oVueContentSummary0.value=listData.data.contentSummary;
		                            if(listData.data.contentDescUrl==""||listData.data.contentDescUrl==null){
		                                oVueContentDescUrl0.href="javascript:;";
		                            }
		                            else{
		                                oVueContentDescUrl0.href=listData.data.contentDescUrl;
		                            }
		                            oVueContentDesc0.innerHTML=listData.data.contentDesc;
		
		                        }
		                        if(listData.data.overlayId==oVueOverlaySmallOverlayTwo){
		                            oVideoOverlayCompileOne.style.display="none";
		                            oVideoOverlayCompileTwo.style.display="block";
		                            oVueOverlaySmallOverlayOneS.style.display="none";
		                            oVueOverlaySmallOverlayTwoS.style.display="block";
		                            if(listData.data.contentTitleUrl==''||listData.data.contentTitleUrl==null){
		                                oVueContentTitleUrl1.href="javascript:;";
		                            }
		                            else{
		                                oVueContentTitleUrl1.href=listData.data.contentTitleUrl;
		                            }
		                            oVueContentTitle1.innerHTML=listData.data.contentTitle;
		                            if(listData.data.contentDescUrl==""||listData.data.contentDescUrl==null){
		                                oVueContentDescUrl1.href="javascript:;";
		                            }
		                            else{
		                                oVueContentDescUrl1.href=listData.data.contentDescUrl;
		                            }
		                            oVueContentDesc1.innerHTML=listData.data.contentDesc;
		
		                        }
		                    }else{
		                    	oVueOverlaySmallOverlayId.style.display="block";
		                    }
		
		                }
	                },
	                (error)=>{
	                    console.log(error);
	                }
                );
            }
        },
        //准备发布
        videoReadyReleaseSubmint:function () {
            var listTicket=nilo.getCookie('ticket');
            var _self = this;
            var locationSearch=location.search;
            var locationSearchSub=locationSearch.substring(1,locationSearch.length);

            this.$http.post(__baseURL+"/video/publish",{ticket:listTicket,videoId:locationSearchSub},{emulateJSON: true})
                .then(
                    (response)=>{
            var listData=response.data;
            if(listData.responseCode==100000){
//               alert("发布成功！")
                window.location.href=__baseURL + "video/videolist?page=1&ticket="+listTicket;
            }
        },
            (error)=>{
                console.log(error);
            }
            );
        },
        //删除
        videoReadyReleaseDelete:function () {
            var listTicket=nilo.getCookie('ticket');
            var _self = this;
            var locationSearch=location.search;
            var locationSearchSub=locationSearch.substring(1,locationSearch.length);

            this.$http.post(__baseURL+"/tag/removeTags",{ticket:listTicket,videoId:locationSearchSub},{emulateJSON: true})
                .then(
                    (response)=>{
                var listData=response.data;
            if(listData.responseCode==100000){
                console.log(listData)
                swal("删除成功！");
                location.reload();

            }
        },
            (error)=>{
                console.log(error);
            }
            );
        },
        
        //tooltip
        hideTooltip: function(){
            this.showTooltip = false;
        },
        toggleTooltip: function(){
            this.showTooltip = !this.showTooltip;
        }

    }
});

$.ajaxSetup({  
    async : false  
}); 

var locationSearch=location.search;
//var locationSearch='?videoId=7ujn3scved';
var locationSearchSub=locationSearch.substring(1,locationSearch.length);
nilo.videoId = locationSearch.substring(1,locationSearch.length);
var ajaxData;
var tagData;
var oVideoPreviewBoxTimeSapnAjax=0//预览进度时间
var totalTime=0 //单位为秒
var oVideoSaas=nilo.getById("videoSaas");//视频播放
var oVideoSaasPlayId=nilo.getById("videoSaasPlayId");//视频播放
var oObjTag = nilo.getById("objDivId");
var oPlaySaas=nilo.getById("playSaas");//播放
var oVoiceSaas=nilo.getById("voiceSaas");//声音
var oDragPlanSaasId=nilo.getById("dragPlanSaasId");//进度按钮
var oTimeSaasId=nilo.getById("timeSaasId");//时间
var oTimeSaasIdB=oTimeSaasId.getElementsByTagName("b")[0];//跟随时间
var oTimeSaasIdSpan=oTimeSaasId.getElementsByTagName("span")[0];//跟随时间

var h=0;//小时
var m=0;//分钟
var s=0;//秒数
var oDragSaasId=nilo.getById("dragSaasId");//进度条
var aDragSaasBg=nilo.getByClass(oDragSaasId,"dragSaasBg")[0];//进度条背景
var aDragPlanSaasBg=nilo.getByClass(oDragSaasId,"dragPlanSaasBg")[0];//进度背景
var aDragFilterSaas=nilo.getByClass(oDragSaasId,"dragFilterSaas")[0];//点击进度
var aDragPlanSaas=nilo.getByClass(oDragSaasId,"dragPlanSaas")[0];//拖拽小圆圈
/*obj*/  
var objTime=null;
var times=null;//时间倒计时 

var	writeCanvas = nilo.getById('writeCanvas'),
	context = writeCanvas.getContext('2d'),
	flag = false,
	x = 0,
	y = 0,
	x1 = 0,
	y1 = 0,
	isNewAdd = 0,
	loadingTimer = null,
	go2Timer = null
;


//(function () {	
	checkVideoStatus();

	$('#overlayFileUpTicket,#fileUpTicket').val(nilo.getCookie('ticket'));
	
    //视频播放按钮，只要能获取到视频的URL就允许播放
//    oObjTag.onclick=oPlaySaas.onclick=oVideoSaas.onclick=function () {
    oPlaySaas.onclick=function () {
        if (oVideoSaas.paused) {
            playObj()
        }
        else {
            pauseObj()
        }
    }
    //object点击事件,
    oObjTag.onclick=function () {
    	if (oVideoSaas.paused) {
    		playObj()
        }else {
        	pauseObj()
        }
    }
    
    function getObjData(){
	    //获取视频基本信息
		$.post(__baseURL+'/video/description',{ticket:nilo.getCookie('ticket'),videoId:locationSearchSub},function(data,status){
	        ajaxData=JSON.parse(data);
	        if(ajaxData.data!=null){
	        	if(ajaxData.responseCode ==100000){
		        	nilo.videoRate = ajaxData.data.videoFrameRate;
		        	nilo.videoWidth = ajaxData.data.videoWidth;
		        	nilo.videoHeight = ajaxData.data.videoHeight;
		            totalTime=parseInt(ajaxData.data.videoTime);
		            totalTimes(totalTime,oTimeSaasIdSpan);
		            videoTagCompileDelayTime(totalTime)//标签显示时间
	        	}
	        }else{
	            loadingTips('<span class="tips"></span>获取视频数据失败，<b class="timer">5</b>秒后自动跳转到登录页面!', 1);
	        }
	    });
	    
		//获取视频object信息
	    $.get(__baseURL+'/object/queryObjects?videoId='+locationSearchSub,{ticket:nilo.getCookie('ticket')},function(data,status){
	    	ajaxData=JSON.parse(data);
	        if(ajaxData.data!=null){
	        	nilo.videoObjData=ajaxData.data.objects;
	        }
	    });
	    
	  //获取视频tag信息
	    $.get(__baseURL+'/tag/query?videoId='+locationSearchSub,{ticket:nilo.getCookie('ticket')},function(data,status){
	    	if(data!=null)
			{
		        tagData=JSON.parse(data); 
		        if(tagData.responseCode==100009)
	        	{
		        	window.location=__baseURL;
	        	}
		        else
	        	{
	        		nilo.videoTagData = tagData.data.tags;
	        	}
			}
	    	else
			{
	    		window.location=__baseURL;
			}
	    });
    }
    
    //获取video object & tag info
    getObjData();
    
    /*播放*/
    function playObj() {
    	//清除画布内容   by luof
    	delRect(context, 0, 0, writeCanvas.width, writeCanvas.height);    	
    	$('#writeCanvas').hide();
    	$('#videoCtrl,#videoBtns').removeClass('active');
    	
        oVideoSaas.play();
        oPlaySaas.className="playSaasClass";
        //大屏幕播放按钮
        oVideoSaasPlayId.className="videoSaasPlay" 
        timeSaas();//视频进度条计时 
        objString()//显示obj div & tag div
    }
    
    /*暂停*/
    function pauseObj() {    	
        oVideoSaas.pause();
        //大屏幕播放按钮
        if(arguments[0] == undefined){
        	oVideoSaasPlayId.className="videoSaasPlay videoSaasPlayNone";
        }else{
        	oVideoSaasPlayId.className="videoSaasPlay";
        }
        
        oPlaySaas.className="pauseSaasClass";
        clearInterval(times);
        clearInterval(objTime);
    }

    //播放完成
    oVideoSaas.onended=function () {
        oVideoSaasPlayId.className="videoSaasPlay videoSaasPlayNone"
        oPlaySaas.className="pauseSaasClass";
        clearInterval(times);
        clearInterval(objTime);  
    } 
    
    //声音
    oVoiceSaas.onclick=function () {
        if(oVideoSaas.volume!=0.0){
            oVideoSaas.volume=0.0;
            this.className="muteSaasClass";
        }
        else {
            oVideoSaas.volume=1.0;
            this.className="voiceSaasClass";
        }
    }
    
    //播放器进度条显示播放时间，以及进度条样式
    function timeSaas() {
        clearInterval(times)
        times=setInterval(function () {
        	//视频播放的当前秒数
            var timeIndex = (oVideoSaas.currentTime).toFixed();
            timeIndex = timeIndex < 0 ? 0 : timeIndex;
           
            var h=Math.floor(timeIndex/60/60%24);
            var m=Math.floor(timeIndex/60%60);
            var s=Math.floor(timeIndex%60);
            if(h==0){
                oTimeSaasIdB.innerHTML=+addTens(m)+":"+addTens(s);
            }
            else{
                oTimeSaasIdB.innerHTML=h+":"+addTens(m)+":"+addTens(s);
            }
            //进度条
            aDragPlanSaasBg.style.width=parseInt(timeIndex*(aDragSaasBg.clientWidth/totalTime))+"px";//计算速度V=S/t
            aDragPlanSaas.style.left=parseInt(timeIndex*(aDragSaasBg.clientWidth/totalTime))+"px";//计算速度V=S/t
        },1000)
    }
    function planRelevance(relevance) {
    	relevance = relevance < 0 ? 0 : relevance;
        aDragPlanSaasBg.style.width = relevance+"px";
        aDragPlanSaas.style.left = relevance+"px";
        var thisTime=parseInt(relevance/(aDragSaasBg.clientWidth/totalTime))
        oVideoSaas.currentTime=thisTime;
        totalTimes(thisTime,oTimeSaasIdB);
    }
    //拖拽小圆点
    aDragPlanSaas.onmousedown=function(ev){
        var oEvent=ev||event;
        var disX=oEvent.clientX-aDragPlanSaas.offsetLeft;
        if(aDragPlanSaas.setCapture){
            aDragPlanSaas.onmousemove=fnMove
            aDragPlanSaas.onmouseup=fnUp
            aDragPlanSaas.setCapture()
        }
        else{
            document.onmousemove=fnMove
            document.onmouseup=fnUp
        }
        function fnMove(ev){
            var oEvent=ev||event;
            var dissX=oEvent.clientX-disX;

            if(dissX<=0){
                aDragPlanSaas.style.left=0+"px";
                planRelevance(dissX);
            }
            else if(dissX>=parseInt(aDragSaasBg.offsetWidth-aDragPlanSaas.offsetWidth)){
                aDragPlanSaas.style.left=parseInt(aDragSaasBg.offsetWidth-aDragPlanSaas.offsetWidth)+"px";
                planRelevance(aDragSaasBg.offsetWidth);
            }
            else{
                aDragPlanSaas.style.left=dissX+"px";
                planRelevance(dissX);
            }

        }
        function fnUp(){
            this.onmousemove=null;
            this.onmouseup=null;
            if(this.releaseCapture){
                aDragPlanSaas.releaseCapture()
            }
        }
        return false;
    }

    //十进位
    function addTens(tens) {
        if(tens<10){
            return "0"+tens;
        }
        else{
            return tens;
        }
    }
    //总时长
    function totalTimes(time,obj) {
    	time = time < 0 ? 0 : time;
    	
        var h=Math.floor(time/60/60%24);
        var m=Math.floor(time/60%60);
        var s=Math.floor(time%60);
        if(h==0){
            obj.innerHTML=+addTens(m)+":"+addTens(s);
        }
        else{
            obj.innerHTML=h+":"+addTens(m)+":"+addTens(s);
        }
    } 

    /**
     * 显示object数据
     * */
    function objString() {
    	if(ajaxData==null || ajaxData.data==null || ajaxData.data.objects==null || ajaxData.data.objects.length<=0)
    		return false;
        var oObjDivId=document.getElementById("objDivId"); 
        var tagDataVideo=tagData;
        var videoFrameRate=nilo.videoRate;//帧率
        var width=nilo.videoWidth;//分辨率宽度
        var height=nilo.videoHeight;//分辨率宽度
        var frameList=nilo.videoObjData;//每帧
        var oVideoCompileRatioWidth=nilo.getById("videoCompileRatioWidth");//获取分辨率宽度
        oVideoCompileRatioWidth.innerHTML=width;
        var oVideoCompileRatioHeight=nilo.getById("videoCompileRatioHeight");//获取分辨率高度
        oVideoCompileRatioHeight.innerHTML=height;
        
        objTime=setInterval(function () { 
        	$('.objDivBorder').css('visibility','hidden');
        	 
          //视频播放的当前秒数
            var currSecond = oVideoSaas.currentTime;
            var frameNo = Math.floor(currSecond*videoFrameRate);
            
            for(var frameIndex=0;frameIndex<frameList.length;frameIndex++)
        	{
            	var objFF = frameList[frameIndex].fno;
            	 
            	if(objFF == frameNo)
        		{             		
            		//取这一帧里的所有obj
            		var objs = frameList[frameIndex].objs;
            		for(var j=0;j<objs.length;j++)
        			{
            			var obj = objs[j];

            			var ooid='objDiv_'+objFF +'_'+obj.oid;
            	        var ootag = nilo.getById(ooid); 
            	        ootag.style.visibility = 'visible';
            			//break;
        			}
        		} //end of if(objFF == frameNo)
        	} //end of for(var frameIndex=0;frameIndex<frameList.length;frameIndex++)
        },30)
    }

    /**
     * 页面加载时画obj数据
     * */
    function initDiv() {
    	if(ajaxData==null || ajaxData.data==null || ajaxData.data.objects==null || ajaxData.data.objects.length<=0)
    		return false;
        var oObjDivId=document.getElementById("objDivId"); 
        var tagDataVideo=tagData;
        var videoFrameRate=nilo.videoRate;//帧率
        var width=nilo.videoWidth;//分辨率宽度
        var height=nilo.videoHeight;//分辨率宽度
        var frameList=nilo.videoObjData;//每帧
        var oVideoCompileRatioWidth=nilo.getById("videoCompileRatioWidth");//获取分辨率宽度
        oVideoCompileRatioWidth.innerHTML=width;
        var oVideoCompileRatioHeight=nilo.getById("videoCompileRatioHeight");//获取分辨率高度
        oVideoCompileRatioHeight.innerHTML=height;
//        alert('init div ,data length:' + frameList.length);
         
        $("#objDivId").empty();
        
        for(var frameIndex=0;frameIndex<frameList.length;frameIndex++)
    	{
        	var objFF = frameList[frameIndex].fno;   
    		//取这一帧里的所有obj
    		var objs = frameList[frameIndex].objs;
    		for(var j=0;j<objs.length;j++)
			{
    			var obj = objs[j];            			
    			//画obj边框
    			var objDiv=document.createElement("div");
                objDiv.className="objDivBorder "+"delete_"+objFF+"_" + obj.oid;
                objDiv.id = "objDiv_"+objFF + "_" + obj.oid;
                var x=parseInt(obj.x*(__baseVideoWidth/nilo.videoWidth));
                var y=parseInt(obj.y*(__baseVideoHeight/nilo.videoHeight));
                var w=parseInt(obj.w*(__baseVideoWidth/nilo.videoWidth));
                var h=parseInt(obj.h*(__baseVideoHeight/nilo.videoHeight));
               
                objDiv.style.left=x+"px";
                objDiv.style.top=y+"px";
                objDiv.style.width=w+"px";
                objDiv.style.height=h+"px";
                objDiv.style.visibility = 'hidden';
                
                //tag
                oObjDivId.appendChild(objDiv);
                
                //画tag
                if(nilo.videoTagData!=null && nilo.videoTagData.length>0){
                    for(var z=0; z<nilo.videoTagData.length;z++){
                        if(obj.oid==nilo.videoTagData[z].objectId){
                        	var tagInfo = nilo.videoTagData[z];
                        	//tag相对于object的偏移量，不需要根据视频实际尺寸偏移
                            var tagX=parseInt(tagInfo.tagX);//tagX
                            var tagY=parseInt(tagInfo.tagY);//tagY
                            var tagName=tagInfo.tagName;  //tag名称
                            var tagContent=tagInfo.tagContent; // tag描述
                            var tagStyleId=tagInfo.tagStyleId; //tagStyleId
                            var tagPopStyle=tagInfo.tagPopStyle; //标签交互
                            var tagPopUrl=""; //标签地址
                            if(!(tagInfo.tagPopUrl==null || tagInfo.tagPopUrl=="" || tagInfo.tagPopUrl == "null"))
                            	tagPopUrl = tagInfo.tagPopUrl;
                            var tagOverlayUrl = "";
                            if(!(tagInfo.overlayUrl==null || tagInfo.overlayUrl=="" || tagInfo.overlayUrl == "null"))
                            	tagOverlayUrl = tagInfo.overlayUrl;
                            	
                            var overlayId=tagInfo.overlayId; //标签地址
                            var tagFixed=tagInfo.tagFixed; //是否固定
                            var tagId=tagInfo.tagId;//tagId;
                        	
                            var tagDivA=document.createElement("a");
                            tagDivA.className='tagDivBorderSetUpA';
                            tagDivA.style.width = w + 'px';
                            tagDivA.innerHTML = '<b class="delBtn" data-fno="' + objFF + '" data-oid="' + obj.oid + '" data-tagid="' + tagId + '"></b>' + tagInfo.tagName;
                            objDiv.appendChild(tagDivA);
                            
                        }
                    }
                }                        
			} 
    	} //end of for(var frameIndex=0;frameIndex<frameList.length;frameIndex++)  
    }

    //初始化界面中的所有div
    initDiv();
    
    //tag显示
    function tagShow() {
        var oVideoTagAllId=nilo.getById("videoTagAllId");
        var oVueTagCompileDelayTime=nilo.getById("vueTagCompileDelayTime");
        oVideoTagAllId.style.display="block";
        oVueTagCompileDelayTime.options[0].selected=true;

    }
    //关闭tag
    $('#videoTagCloseId,#videoTagCompileBtnGoBackId').click(function(){
    	$('#createHotspot').removeClass('closeTag').find('.plusCross').text('+');
		vm.tagTipsText = '创建新标签热点';
    	
    	$('#videoTagAllId,#videoTagHref').hide();
        
        //显示标签层、“预览”、“发布”、“删除全部Tag”按钮     by luof
        $('#videoTagBorderBrId').removeClass('select');
		$('#objDivId,#videoTagBorderBrId').show();    		
		playObj();   
    });
    //tag画布
    

    //tag action 下拉列表onchange
    function VideoTagInteraction() {
        var oVideoTagInteraction=nilo.getById("videoTagInteraction");
        oVideoTagInteraction.onchange=function () {
        	changeTagAction();
        }
    }
    function changeTagAction()
    {
    	$('#editPopup').hide();
    	$('#sub4TagEdit').val('保存');
    	
    	 var oVideoTagInteraction=nilo.getById("videoTagInteraction");
         var oVideoTagHref=nilo.getById("videoTagHref");
         var oVideoOverlayHref=nilo.getById("videoOverlayHref");
    	if(oVideoTagInteraction.selectedIndex==1){
        	oVideoOverlayHref.style.display="none";
            oVideoTagHref.style.display="block";
        }
        else if(oVideoTagInteraction.selectedIndex==2){
            oVideoTagHref.style.display="none";
//            oVideoOverlayHref.style.display="block";
            //显示“编辑浮层”按钮   by luof            
            if(!isNewAdd){            	
        		$('#editPopup').slideDown();
        	}else{
        		$('#sub4TagEdit').val('保存后弹出浮层');
        	}
        }
        else {
            oVideoTagHref.style.display="none";
            oVideoOverlayHref.style.display="none";
        }
    }
    VideoTagInteraction();
    
    $('#editPopup').click(function(){
    	//显示浮层 并改变其他右侧层的透明度  by luof
    	overlayShow();
    	$('.videoCenterR,.videoTagCenterR').addClass('active');
    });
    
  //改变tag位置，调整tag位置方法
    function adjustTagLocation(objX,objY,objW,objH) { 
    	var oVideoTagLocationXTxt=nilo.getById("videoTagLocationXTxt"); //x value 值
    	var oVideoTagLocationYTxt=nilo.getById("videoTagLocationYTxt"); //y value值
    	
        var oVideoTagLocationXMinus=nilo.getById("videoTagLocationXMinus"); //x减        
        var oVideoTagLocationXAdd=nilo.getById("videoTagLocationXAdd"); //x加
        var oVideoTagLocationYMinus=nilo.getById("videoTagLocationYMinus"); //y减        
        var oVideoTagLocationYAdd=nilo.getById("videoTagLocationYAdd"); //Y加;
        
        
        var tagx = parseInt(oVideoTagLocationXTxt.value);
        var tagy = parseInt(oVideoTagLocationYTxt.value);  
        oVideoTagLocationXMinus.onclick=function () {
            tagx--;
            oVideoTagLocationXTxt.value=tagx;
            displayTagLocation(objX,objY,objW,objH,tagx,tagy);
        }
        oVideoTagLocationXAdd.onclick=function () { 
            tagx++; 
            oVideoTagLocationXTxt.value=tagx;
            displayTagLocation(objX,objY,objW,objH,tagx,tagy);
        }
        oVideoTagLocationYMinus.onclick=function () {
            tagy--; 
            oVideoTagLocationYTxt.value=tagy;
            displayTagLocation(objX,objY,objW,objH,tagx,tagy);
        }
        oVideoTagLocationYAdd.onclick=function () {
            tagy++;
            oVideoTagLocationYTxt.value=tagy;
            displayTagLocation(objX,objY,objW,objH,tagx,tagy);
        }
    }
    
    function displayTagLocation(objX,objY,objW,objH,tagX,tagY)
    {
    	var oVideoTagSmallIconId=nilo.getById("videoTagSmallIconId"); //tag图标  
    	var oVideoTagFixation=nilo.getById('videoTagFixation'); //是否固定
    	if(oVideoTagFixation.checked){
    		oVideoTagSmallIconId.style.left=parseInt(tagX)+"px";
    		oVideoTagSmallIconId.style.top=parseInt(tagY)+"px";
    	}
    	else
		{
    		oVideoTagSmallIconId.style.left=parseInt(parseInt(objX)+parseInt(parseInt(objW)/2)+parseInt(tagX))+"px";
    		oVideoTagSmallIconId.style.top=parseInt(parseInt(objY)+parseInt(parseInt(objH)/2)+parseInt(tagY))+"px";
		}
    }
    
    //关闭添加链接
    function tagNewLinkClose() {
        var oVideoTagHref=nilo.getById("videoTagHref");
        var oVideoTagCompileBtnId=nilo.getById("videoTagCompileBtnId");
        oVideoTagCompileBtnId.onclick=function () {
            oVideoTagHref.style.display="none"
        }
    }
    tagNewLinkClose()
    
    
    //overlay暂时使用外部URL的方式实现
    //关闭overlay div
    function tagOverlayNewLinkClose() {
        var oVideoOverlayHref=nilo.getById("videoOverlayHref");
        var oVideoTagCompileBtnId=nilo.getById("videoOverlayCompileBtnId");
        oVideoTagCompileBtnId.onclick=function () {
        	oVideoOverlayHref.style.display="none"
        }
    }
    tagOverlayNewLinkClose()
    
    //设置标签显示时间vlue
    function videoTagCompileDelayTime(CompileDelayTime) {
        var oVueTagCompileDelayTime=nilo.getById('vueTagCompileDelayTime');
        oVueTagCompileDelayTime.options[oVueTagCompileDelayTime.length-1].value=CompileDelayTime;
    }

    /*浮层*/
    function overlayShow() {
        $('#videoOverlayAllId,#vueOverlaySmallOverlayId').show();
        canvasOverlayObj();
    }
    /*浮层画布*/
    function canvasOverlayObj() {
        var oVideoTagCanvasId=nilo.getById("videoOverlayCanvasId");//画布
        var oVideoTagCanvasIdCTX=oVideoTagCanvasId.getContext("2d");
        oVideoTagCanvasIdCTX.drawImage(oVideoSaas,0,0,oVideoSaas.offsetWidth,oVideoSaas.offsetHeight);
    }
    //鼠标移上去修改图片加链接
    $(".videoOverlayLCharacter").mouseover(function () {
        $(this).find(".videoOverlayLCharacterText").addClass("videoOverlayCompileTBorder");
        $(this).find(".videoOverlayLCharacterBg").css("display","block");
    })
    $(".videoOverlayLCharacter").mouseout(function () {
        $(this).find(".videoOverlayLCharacterText").removeClass("videoOverlayCompileTBorder");
        $(this).find(".videoOverlayLCharacterBg").css("display","none");
    })
    //选择浮层样式
    $("#vueOverlaySmallOverlayId li").click(function () {
        $(".videoOverlayCompile,#vueOverlaySmallOverlayId li em").hide();
        var index = $(this).index();
        $(".videoOverlayCompile:eq(" + index + "),#vueOverlaySmallOverlayId li em:eq(" + index + ")").show();
        
        if(index == 1){
        	//地图只有居中显示
        	$('#overlayStyle').val(0);
        	$('.videoOverlayCompile').attr('class','videoOverlayCompile style00');
        	$('#overlayStyle option:not(:eq(0))').attr('disabled','disabled');
        }else{
        	$('#overlayStyle option').removeAttr('disabled');
        }
    })
    //浮层添加链接
    var videoOverlayLMHerfTXT;
    $(".videoOverlayLMHerf").click(function(e){
    	var e = e || event;
    	e.stopPropagation();
    	
    	$('.videoOverlay2Pay').hide();
        $('.videoOverlayAddHImg,.videoOverlayAddHerf').show();
        videoOverlayLMHerfTXT=$(this);
        $('#videoOverlayAddHerfIdTXT').val(videoOverlayLMHerfTXT.text());
        
        var elem = $(this).parents('.videoOverlayCompileImg'),
			_id = elem.find('.overlayImg').attr('id'),
			_imgUrl = elem.find('img').attr('src')
		;
		$('#overlayImgId').val(_id);
    })
    //获取链接
    $('#videoOverlayAddHerfId').click(function () {
    	var href = $.trim($('#videoOverlayAddHerfIdTXT').val()),
    		elemId = $('#overlayImgId').val();
    	
        videoOverlayLMHerfTXT.html(href);
        if(href != ''){
        	$('#' + elemId).attr({'href' : href , 'target' : '_blank'});
        }else{
        	$('#' + elemId).removeAttr('href target');
        }
        
        videoOverlayAddCloseObject();
    })
    //浮层添加图片
    $(".videoOverlayLMCharacterImg").click(function(e){
    	var e = e || event;
    	e.stopPropagation();
    	
    	var elem = $(this).parents('.videoOverlayCompileImg'),
    		_id = elem.find('.overlayImg').attr('id'),
    		_imgUrl = elem.find('img').attr('src')
    	;
    	$('#overlayImgId').val(_id);
    	$('#overlayImgUrl').val(_imgUrl);
    	
        $('.videoOverlayAddHImg,.videoOverlayAddImg').show();
    })
    //关闭添加图片，添加链接浮层
    $('.videoOverlayAddHImgClose').click(function () {
        videoOverlayAddCloseObject();
    })
    function videoOverlayAddCloseObject() {
        $('.videoOverlayAddHImg,.videoOverlayAddHerf,.videoOverlayAddImg').hide();
    }
    //关闭浮层样式，显示浮层编辑
    $(".videoOverlayStyleCompileBtn").click(function () {
    	$('#vueOverlaySmallAnOverlayId').show();
        $('#vueOverlaySmallOverlayId').hide();
    })
    //关闭浮层

    function videoOverlayClose() {        
        var oVideoOverlayCompileClose=nilo.getById("videoOverlayCompileClose");
        oVideoOverlayCompileClose.onclick=function () {
            videoOverlayCloseObject()
        }
        $('.videoOverlayLClose').click(function () {
            videoOverlayCloseObject()
        })
    }
    videoOverlayClose()
    function videoOverlayCloseObject() {
        var oVideoOverlayAllId=nilo.getById("videoOverlayAllId");
        oVideoOverlayAllId.style.display="none";
        var oVideoTagAllId=nilo.getById("videoTagAllId");
        oVideoTagAllId.style.display="none";
        //还原交互方式 及 隐藏“编辑浮层”按钮    by luof
        $('#videoTagAllId').show();
        $('#vueOverlaySmallOverlayId').hide();
        $('.videoCenterR,.videoTagCenterR').removeClass('active');
        //交互方式 还原
        if(!!isNewAdd){
        	$('#videoTagInteraction').val(0);
        }
    }
    //选择浮层样式
    $('.videoOverlayLStyle').click(function () {
    	$('#vueOverlaySmallAnOverlayId').hide();
        $('#vueOverlaySmallOverlayId').show();
    })
    
 // 预览显示
    function previewShow() {
        var oVideoPreviewButtonL=nilo.getById('videoPreviewButtonL');//显示按钮 
        oVideoPreviewButtonL.onclick=function () {
            //oVideoPreviewId.style.display='block';
            pauseObj();
            var tmpURL = 'previewVideo?'+locationSearchSub;
            window.open(tmpURL);
            //objectData();
        }
    }
    previewShow(); 
    
    
    /**
     * 根据点击坐标判断是否存在object
     * */
    $(document).on('click', '.objDivBorder', function(){
    	var that = $(this);
    	
    	if(!that.is(':visible')){
    		return false;
    	}
    	
    	that.css('visibility','visible').siblings('.objDivBorder').css('visibility','hidden');
    	
    	var _obj = that.find('.delBtn'), 
	    	objFno = _obj.attr('data-fno'),
	    	objOid = _obj.attr('data-oid'),
	    	tagId = _obj.attr('data-tagid'),
	    	tagX = that.css('left'),
	    	tagY = that.css('top'),
	    	tagW = that.css('width'),
	    	tagH = that.css('height')
	    ;
    	
    	pauseObj();
    	$('#sub4TagEdit').val('保存');
        
        $('#videoTagBorderBrId').addClass('select');
    	$('#videoCtrl,#videoBtns').addClass('active');
    	isNewAdd = 0;
    	showTagDetailInfo(tagX, tagY, tagW, tagH, objFno, objOid);
    	tagShow();    	     
        
    });
    
    function showTagDetailInfo(objX, objY, objW, objH, objFno, objId){
    	var arrId=objId;//id
        //obj的位置
        var arrW=objW;//宽度
        var arrH=objH;//高度
        var arrX=objX;//left
        var arrY=objY;//top
        
        //tag偏移量，不需要根据视频分辨率等比计算
        var tagX=0;//tagX
        var tagY=0; //tagY
        var tagName="";  //tag名称
        var tagContent=""; // tag描述
        var tagStyleId=""; //tagStyleId
        var tagPopStyle=0; //标签交互
        var tagPopUrl=""; //标签地址
        var tagOverlayUrl="";
        var overlayId=""; //标签地址
        var tagFixed=0; //是否固定;
        var tagId="";//tagid   
        
        if(nilo.videoTagData!=null && nilo.videoTagData.length>0){
	    	for(var z=0; z<nilo.videoTagData.length;z++){
	    		if(objId==nilo.videoTagData[z].objectId){
	    			var tagInfo = nilo.videoTagData[z];
	    			tagName=tagInfo.tagName;
	    			tagContent=tagInfo.tagContent;
	    			tagStyleId=tagInfo.tagStyleId;
	    			tagPopStyle = tagInfo.tagPopStyle;
	    			overlayId = tagInfo.overlayId; //标签地址
	    			tagFixed=tagInfo.tagFixed; //是否固定
	    			tagId =tagInfo.tagId;//tagId;
	    			tagX=parseInt(tagInfo.tagX);//tagX
	                tagY=parseInt(tagInfo.tagY);//tagY
	                if(!(tagInfo.tagPopUrl==null || tagInfo.tagPopUrl=="" || tagInfo.tagPopUrl == "null"))
	                	tagPopUrl = tagInfo.tagPopUrl;
	                if(!(tagInfo.overlayUrl==null || tagInfo.overlayUrl=="" || tagInfo.overlayUrl == "null"))
	                	tagOverlayUrl = tagInfo.overlayUrl;

	                var oVideoCompileObjectId=nilo.getById('videoCompileObjectId');
	                oVideoCompileObjectId.innerHTML=arrId;
	                var oVideoCompileTagId=nilo.getById('videoCompileTagId');
	                oVideoCompileTagId.innerHTML=tagId;
	                var oVideoCompileOverlayId=nilo.getById("videoCompileOverlayId");
	                oVideoCompileOverlayId.innerHTML=overlayId;
	                
	                console.log('display edit tag x:' + arrX + ",y:" + arrY + ",w:" + arrW + ",h:" + arrH );
	                var oVideoTagBorderBrId=nilo.getById("videoTagBorderBrId");//框框 obj的边框
	                oVideoTagBorderBrId.style.left=arrX+"px";
	                oVideoTagBorderBrId.style.top=arrY+"px";
	                oVideoTagBorderBrId.style.width=arrW+"px";
	                oVideoTagBorderBrId.style.height=arrH+"px";	                
	                
	                oVideoTagBorderBrId.innerHTML = $('#objDiv_' + objFno + '_' + objId).html();
	                
	                //小图标的位置应为obj.x + obj.w/2 - iconImgW + tagX
	                //小图标的宽高已是实际宽高的一半，所以不再除以2
	                var videoTagSmallIconIdElem = $('#videoTagSmallIconId'),
	                	iconImgW = videoTagSmallIconIdElem.get(0).width, 
	                	iconImgH = videoTagSmallIconIdElem.get(0).height,
	                	_left = parseInt(parseInt(arrX) + parseInt(parseInt(arrW) / 2) - iconImgW + parseInt(tagX)) + 'px';
	                	_top = parseInt(parseInt(arrY) + parseInt(parseInt(arrH) / 2) - iconImgH + parseInt(tagY)) + 'px';
	                
	                videoTagSmallIconIdElem.css({'left' : _left, 'top' : _top, 'visibility' : 'visible'});
	                        
	                $(function(){            
	                    nilo.getById("videoTagLocationXTxt").value=parseInt(tagX);
	                    nilo.getById("videoTagLocationYTxt").value=parseInt(tagY);
	                    //videosLocation(arrXImg,arrYImg)//位置
	                    adjustTagLocation(arrX,arrY,arrW,arrH);
	                })
	                
	                var oVideoCompileTitle=nilo.getById('videoCompileTitle'); //tag名称
	                var oVideoCompileDescribe=nilo.getById('videoCompileDescribe');//tag描述 
	                var oVideoCompileImitationSelectId=nilo.getById("videoCompileImitationSelectId"); //tag样式
	                var oVideoTagInteraction=nilo.getById('videoTagInteraction');//tag标签交互方式	
	                oVideoCompileTitle.value=tagName;
	                oVideoCompileDescribe.value=tagContent; 
	                ////tag样式下拉列表
	                var aVideoCompileImitationSelectIdUl=oVideoCompileImitationSelectId.getElementsByTagName("ul")[0];
	                var aVideoCompileImitationSelectIdUlEm=aVideoCompileImitationSelectIdUl.getElementsByTagName("a");
	                if(tagStyleId==""||tagStyleId==null){
	                    videoTagImitationSelectId(0);
	                }
	                else {
	                    for(var i=0;i<aVideoCompileImitationSelectIdUlEm.length;i++){
	                        if(aVideoCompileImitationSelectIdUlEm[i].innerHTML==tagStyleId){
	                            videoTagImitationSelectId(i);
	                        }
	                    }
	                }
	                //绑定标签交互方式
	                oVideoTagInteraction.value=tagPopStyle;
	                if(tagPopStyle == 2){
	                	$('#editPopup').slideDown();
	                }
//	                changeTagAction();
	                
	                //tag打开新链接时的链接地址
	                var oVideoCompileTxtNewHerf=nilo.getById('videoCompileTxtNewHerf');
	                oVideoCompileTxtNewHerf.value=tagPopUrl;
	                //tag打开overlay的链接地址
	                var oVideoOverlyCompileTxtNewHerf=nilo.getById('videoOverlayNewHerf');
	                oVideoOverlyCompileTxtNewHerf.value=tagOverlayUrl;
	                //固定
	                var oVideoTagFixation=nilo.getById("videoTagFixation");
	                if(tagFixed==0){
	                    oVideoTagFixation.checked=false;
	                }
	                else {
	                    oVideoTagFixation.checked=true;
	                }
	    		}
	    	}
        }
    }
    
	//“创建热点”按钮  by luof
    $('#createHotspot').click(function(){
    	var _t = $(this);
    	
    	if(_t.hasClass('closeTag')){    		
    		$('#videoTagCloseId').trigger('click');
    	}else{
    		_t.addClass('closeTag').find('.plusCross').text('×');
    		vm.tagTipsText = '取消创建新标签热点';
    		
    		isNewAdd = 1;
        	//隐藏标签层
        	$('#objDivId,#videoTagBorderBrId').hide();
        	    	
        	writeRect(writeCanvas, context);    	

        	var currSecond = oVideoSaas.currentTime;
            var frameNo = Math.floor(currSecond*nilo.videoRate);
            $('#curTime').val(frameNo);
            
        	$('#writeCanvas').show();
        	    	
        	pauseObj(1);
    	}
    	
    });
    
    //“立即购买”按钮  by luof
    $('.videoOverlay2PayHref').click(function(){
    	var _t = $(this), 
    		_tp = _t.parents('.videoOverlayCompileBtn'),
    		_btnElem = _tp.find('.go2PayBtn');
    	
        $('.videoOverlayAddHImg,.videoOverlay2Pay').show();
        
        $('#go2PayBtnElemId').val(_btnElem.attr('id'));
        $('#videoOverlayAdd2PayHref').val(_btnElem.attr('href'));
        $('#videoOverlayAdd2PayBackg').val(_btnElem.css('backgroundColor'));
        $('#videoOverlayAdd2PayColor').val(_btnElem.css('color'));
        $('#videoOverlayAdd2PayText').val(_btnElem.text());
        
    });
    $('#videoOverlayAdd2PayId').click(function(){
    	var _btnElem = $('#' + $('#go2PayBtnElemId').val()),
    		href = $.trim($('#videoOverlayAdd2PayHref').val()),
    		bgColor = $.trim($('#videoOverlayAdd2PayBackg').val()),
    		BtnColor = $.trim($('#videoOverlayAdd2PayColor').val()),
    		btnText = $.trim($('#videoOverlayAdd2PayText').val())
    		target = 'self';
    	
    	if(!href){
    		swal('请输入链接地址');
    		$('#videoOverlayAdd2PayHref').focus();
    		return false;
    	}
    	
    	if(href.toLowerCase().indexOf('javascript') <= -1){
    		target = '_blank';
    	}
    	
    	_btnElem.attr({
    		'href' : href,
    		'target' : target
    	}).css({
    		'background' : bgColor,
    		'color' : BtnColor 		
    	}).text(btnText);
    	
        videoOverlayAddCloseObject();
    });
    
    //鼠标动作-start
    writeCanvas.onmousedown = function(e){
        flag = true;
        x = e.offsetX;
        y = e.offsetY;
    };

    //鼠标动作-stop
    writeCanvas.onmouseup = function(e){
    	if(oVideoSaas.paused){    		
    		//隐藏“预览”、“发布”、“删除全部Tag”按钮   by luof
    		$('#editPopup').hide();    	

    		//鼠标动作停止时，清空tag div中的数据
        	initTagDiv();
        	tagShow(); 
        	$('#videoCtrl,#videoBtns').addClass('active');
    	}
        flag = false;
    };

    writeCanvas.onmousemove = function(e){
    	if(!oVideoSaas.paused){
    		return;
    	}
    	if(flag){
    		writeRect(writeCanvas, context);
    		
    		x1 = e.offsetX;
    		y1 = e.offsetY;
    		delRect(context, x, y, (x1-x), (y1-y));
    		
    		getCoordinates();
        }
    };
    //鼠标动作-end

    //获取坐标
    function getCoordinates(){
    	$('#videoCompileObjectId,#videoCompileTagId,#videoCompileOverlayId').html('');
    	
    	var x2 = parseInt( (x / __baseVideoWidth) * nilo.videoWidth ),
    		y2 = parseInt( (y / __baseVideoHeight) * nilo.videoHeight ),
    		
    		x3 = parseInt( (x1 / __baseVideoWidth) * nilo.videoWidth ),
    		y3 = parseInt( (y1 / __baseVideoHeight) * nilo.videoHeight ),
    		
    		tagX = 0,
    		tagY = 0,
    		iconImgW = 66,
    		iconImgH = 66
    	;    	
    	
    	$('#videoTagXVal').val(x2);
    	$('#videoTagYVal').val(y2);
    	$('#videoTagWVal').val(x3-x2);
    	$('#videoTagHVal').val(y3-y2);    	
    	$('#videoTagLocationXTxt').val(tagX);
    	$('#videoTagLocationYTxt').val(tagY);    	
    	
    	//console.log('x = ' + x + ', x1 = ' + x1 + ', 居中=' + (x + parseInt((x1-x)/2) + tagX));
    	        
        //小图标的位置应为obj.x + obj.w/2 - iconImgH + tagX
        //小图标的宽高已是实际宽高的一半，所以不再除以2
        var videoTagSmallIconIdElem = $('#videoTagSmallIconId'),
        	iconImgW = videoTagSmallIconIdElem.get(0).width, 
        	iconImgH = videoTagSmallIconIdElem.get(0).height,
        	_left = (x + parseInt((x1-x)/2) - parseInt(iconImgW / 2) + tagX) + 'px';
        	_top = (y + parseInt((y1-y)/2) - parseInt(iconImgH / 2) + tagY) + 'px';
        
        videoTagSmallIconIdElem.css({'left' : _left, 'top' : _top, 'visibility' : 'visible'});
        
        $(function(){            
            nilo.getById("videoTagLocationXTxt").value=parseInt(tagX);
            nilo.getById("videoTagLocationYTxt").value=parseInt(tagY);
            
            adjustTagLocation(x, y, (x1-x-iconImgW), (y1-y-iconImgH));
        })
    	
    }
    
  //创建热点时，需要清除tag div中的数据
    function initTagDiv()
    {
        var oVideoCompileObjectId=nilo.getById('videoCompileObjectId');
        oVideoCompileObjectId.innerHTML="0";
        var oVideoCompileTagId=nilo.getById('videoCompileTagId');
        oVideoCompileTagId.innerHTML="0";
        var oVideoCompileOverlayId=nilo.getById("videoCompileOverlayId");
        oVideoCompileOverlayId.innerHTML="0";
        
        var oVideoCompileTitle=nilo.getById('videoCompileTitle'); //tag名称
        var oVideoCompileDescribe=nilo.getById('videoCompileDescribe');//tag描述 
        var oVideoCompileImitationSelectId=nilo.getById("videoCompileImitationSelectId"); //tag样式
        var oVideoTagInteraction=nilo.getById('videoTagInteraction');//tag标签交互方式	
        
        oVideoCompileTitle.value="";
        oVideoCompileDescribe.value=""; 
        ////tag样式下拉列表
        videoTagImitationSelectId(0);
        //绑定标签交互方式
        oVideoTagInteraction.value=0;        
        
        //tag打开新链接时的链接地址
        var oVideoCompileTxtNewHerf=nilo.getById('videoCompileTxtNewHerf');
        oVideoCompileTxtNewHerf.value="";
        //固定
        var oVideoTagFixation=nilo.getById("videoTagFixation");
        oVideoTagFixation.checked=false;
        
    }
    
    $(document).on('click', '.tagDivBorderSetUpA .delBtn', function(e){
    	var _t = $(this),
    		fno = $.trim(_t.attr('data-fno')),
    		oid = $.trim(_t.attr('data-oid')),
    		tagid = $.trim(_t.attr('data-tagid'))
    	;
    	
    	swal('删除fno=' + fno + ', oid=' + oid + ', tagid=' + tagid);
    	e = e || window.event;
    	e.stopPropagation();
    });
    
    //落地页 by luof
    var clickTime = null;
    $('#editLandingPageBtn').click(function(){
    	$.ajax({
	        url: __baseURL + "/video/queryLandingPage",
	        type: 'POST',
	        dataType: 'json',
	        data : {
                  'videoId': nilo.videoId,
                  'ticket': nilo.getCookie('ticket')
	        },
		    success: function(req){
		    	if(req.responseCode == 100000 || req.responseCode == 100032){
		        	var templiteId = 1;
		        	
		        	if(req.data){
		        		templiteId = req.data.templiteId;
		        		var imgData = req.data.imgData;
		        		
		        		$.each(imgData, function(index, value){
		        			var posId = value.position,
		        				elem = $('#t' + templiteId + 'Pos' + posId),
		        				bgStr = 'url(' + value.ImgURL + ')';
		        			
		        			if(posId == imgData.length){
		        				bgStr += ' center bottom no-repeat #fff';
		        			}else{
		        				bgStr += ' 0 0 no-repeat #fff';
		        			}
		        			elem.css('background', bgStr);
			        		
			        		if(value.actionUrl){
			        			elem.addClass('link').attr('data-href', value.actionUrl);
			        		}
			        	});
		        	}
		        	
		        	$('#landingPageTemplate li').eq(templiteId - 1).addClass('selected').siblings().removeClass('selected');
	        		$('#landingPageCompile' + templiteId).show();
		        	
		        	$('#editLandingPageId').show();
		            /*浮层画布*/
		        	$('.landingPageCanvas').each(function(){
		        		var ctx = nilo.getById($(this).attr('id')).getContext('2d');            
		                ctx.drawImage(oVideoSaas,0,0,oVideoSaas.offsetWidth,oVideoSaas.offsetHeight);
		        	});
		        	
		        }else{
		        	swal(req.responseMessage);
		        }
		    }
    	});
    	
    });
    
    $('#editLandingPageId .editLandingPageClose,#landingPageCompileClose').click(function(){
    	$('#editLandingPageId').hide();
    });
    
    $('#landingPageTemplate li').dblclick(function(){
    	window.open($(this).attr('data-bigImg'),'_blank')
    }).click(function(){
    	var _t = $(this),
    		index = _t.index('#landingPageTemplate li');
    	
    	_t.addClass('selected').siblings().removeClass('selected');
    	$('#landingPageCompile' + (index + 1)).show().siblings().hide();
    });
    
    $('#editLandingPageId .landingPageImgTpl').dblclick(function(e){
    	clearTimeout(clickTime);
    	    	
    	var e = e || event;
    	var x = e.offsetX || 0;
    	var y = e.offsetY || 0;
    	
    	$('#editLandingPageId .videoOverlayLCharacterBg').hide();
    	$(this).find('.videoOverlayLCharacterBg').css({'left': x+'px', 'top': y+'px'}).show();  
    		
    }).click(function(){
    	clearTimeout(clickTime);
    	
    	var _t = $(this);
    	clickTime = setTimeout(function(){
    		var href = _t.attr('data-href');
        	if(href != '' && href != undefined){
        		window.open(href, '_blank');
        	}
    	}, 300);
    	
    }).hover(function(){
    	$(this).find('.videoOverlayLCharacterBg').css({'left': '10px', 'top': '10px'}).show();
    },function(){
    	$('#editLandingPageId .videoOverlayLCharacterBg').hide();
    });
    
    $('#editLandingPageId .videoOverlayLCharacterBg').click(function(e){  
    	$(window).scrollTop(0);
    	
    	var _t = $(this),
    		_tp = _t.parents('.landingPageImgTpl'),
    		imgUrl = _tp.css('backgroundImage');
    	    	
    	imgUrl = imgUrl.replace(/url\("/, "");
    	imgUrl = imgUrl.replace(/"\)/g, "");    	
    	
        $('.videoOverlayAddHImg,.landingPageAddImgHref').show();
        
        $('#landingPageImgId').val(_tp.attr('id'));
        $('#landingPageImgHref').val(_tp.attr('data-href'));
        $('#landingPageImg').attr('src', imgUrl);
        
        var e = e || event;
        e.stopPropagation();
        
    });
    
    $('#landingPageImg').click(function () {
    	$('#landingPageImgFile').click().trigger('change');
    });
    
    $('#landingPageImgFile').change(function(){
    	var objUrl = nilo.getObjectURL(this.files[0]);
    	if (objUrl) {
    		$('#landingPageImg').attr('src', objUrl);
    	}
    });
    
    $('.landingPageAddImgHrefClose').click(function(){    	
        $('.videoOverlayAddHImg,.landingPageAddImgHref').hide();
        
        $('#landingPageImgId').val('');
        $('#landingPageImgHref').val('');
        $('#landingPageImg').attr('src', '');
        
    });
    
    $('#landingPageFileSub').click(function(){
    	var elemId = $('#landingPageImgId').val(), 
    		elem = $('#' + elemId),
    		href = $.trim($('#landingPageImgHref').val());
    	
    	if(href != ''){
    		elem.addClass('link').attr('data-href', href);
    	}else{
    		elem.removeClass('link').removeAttr('data-href');
    	}
    	
    	if($('#landingPageImgFile').val() != ''){
    		$('#fileUpFrm').ajaxSubmit({
                type: 'post',
                url: __baseURL + '/video/uploadLandingPage',
                data: $('#fileUpFrm').serialize(),
                dataType : 'json',
                success: function(req){
                    if(req.responseCode == 100000){
                    	elem.css('background', 'url(' + req.data.imgUrl + ') 0 0 no-repeat');
                    	$('.landingPageAddImgHrefClose').trigger('click');
                    }else{
                    	swal(req.responseMessage);
                    }
                }
            }).resetForm();
    	}else{
    		$('.landingPageAddImgHrefClose').trigger('click');
    	}
    	
        return false;
    });
    
    $('#landingPageCompileSub').click(function(){
    	var template = $('.landingPageCompile:visible'),    		
    		templateId = template.attr('id').replace('landingPageCompile',''),
    		listData = [],
    		flag = true;
    	
    	template.find('.landingPageImgTpl').each(function(){
    		 var _t = $(this),
    		 	bgUrl = _t.css('backgroundImage'),
    		 	href = _t.attr('data-href');
 	    	
    		 bgUrl = bgUrl.replace(/url\("/, "");
    		 bgUrl = bgUrl.replace(/"\)/g, "");
    		 
    		 if(bgUrl.indexOf('landingPageImg') > -1){
    			 swal('您还有图片没有上传哦');
    			 flag = false;
    			 return false;
    		 }
    		 
    		 if(!href){
    			 href = '';
    		 }
    	    	
    		 listData.push({
    			 'imgUrl' : bgUrl,
    			 'actionUrl' : href,
    			 'position' : parseInt(_t.attr('data-posId'))
    		});
    	});
    	
    	if(flag){
    		$.ajax({
		        url: __baseURL + "/video/saveLandingPage",
		        type: 'POST',
		        dataType: 'json',
		        data : {
	                  'videoId': nilo.videoId,
	                  'templateId': templateId,
	                  'ticket': nilo.getCookie('ticket'),
	                  'info':JSON.stringify(listData)
		        },
			    success: function(req){
			        if(req.responseCode == 100000){		        	
			        	swal('保存成功', '', 'success');
			        	$('#landingPageCompileClose').trigger('click'); 	
			        }else{
			        	swal(req.responseMessage, '', 'error');
			        }
			    }
	    	});
    	}
    	
    });    
    
    $('#overlayStyle').change(function(){
    	var v = $(this).val();
    	$('.videoOverlayCompile').attr({'class' : 'videoOverlayCompile style0' + v});
    	$('.videoOverlayCompile:visible').attr({'style' : 'display:block;'});
    	$('#overlayAnimation').val(0);
    });
    $('#overlayAnimation').change(function(){
    	var v = $(this).val() * 1, 
    		elem = $('.videoOverlayCompile:visible')
    		_width = elem[0].clientWidth,
    		_height = elem[0].clientHeight,
    		_left = elem.css('left'),
    		_right = elem.css('right'),
    		_top = elem.css('top'),
    		_bottom = elem.css('bottom')
    	;
    	elem.fadeOut(600,function(){
    		switch(v){
		    	case 1 : {
		    		//从上面滑入
		    		elem.css({'top' : ('-'  + _top) , 'bottom' : 'auto'}).show();
		    		elem.animate({top : _top}, 600);
		    		break;
		    	}
		    	case 2 : {
		    		//从右边滑入
		    		elem.css({'right' : ('-'  + _right) , 'left' : 'auto'}).show();
		    		elem.animate({right : _right}, 600);
		    		break;
		    	}
		    	case 3 : {
		    		//从下面滑入
		    		elem.css({'top' : 'auto' , 'bottom' : ('-'  + _bottom)}).show();
		    		elem.animate({bottom : _bottom}, 600);
		    		break;
		    	}
		    	case 4 : {
		    		//从左边滑入		    		
		    		elem.css({'left' : ('-'  + _left) , 'right' : 'auto'}).show();
		    		elem.animate({left : _left}, 600);
		    		break;
		    	}
		    	default : {
		    		//直接显示
		    		elem.fadeIn();
		    	}
	    	}
    	});    	
    	
    });
    
    
    $('#overlayImg').click(function () {
    	$('#overlayImgFile').click().trigger('change');
    });
    
    $('#overlayFileSub').click(function(){
    	var imgUrl = $.trim($('#overlayImgUrl').val()),
    		imgFile = $.trim($('#overlayImgFile').val()),
    		elemId = $('#overlayImgId').val(), 
    		elem = $('#' + elemId);

    	if(imgFile != ''){
    		$('#overlayFileUpFrm').ajaxSubmit({
                type: 'post',
                url: __baseURL + '/video/uploadLandingPage',
                data: $('#overlayFileUpFrm').serialize(),
                dataType : 'json',
                success: function(req){
                    if(req.responseCode == 100000){
                    	$('#overlayImgUrl').val(req.data.imgUrl);
                    	elem.find('img').attr('src', req.data.imgUrl);
                    	$('.videoOverlayAddHImgClose').trigger('click');
                    }else{
                    	swal(req.responseMessage);
                    }
                }
            }).resetForm();
    	}else{
    		if(imgUrl == ''){
    			swal('请上传或者输入图片地址', '', 'error');
        	}else{
        		elem.find('img').attr('src', imgUrl);
        		$('.videoOverlayAddHImgClose').trigger('click');
        	}
    		
    	}
    	
        return false;
    });
    
    
//})()


function checkVideoStatus(){
	loadingTimer = setInterval(function(){
		$.ajax({
	        url: __baseURL + "/video/status",
	        type: 'GET',
	        dataType: 'json',
	        data : {
                  'videoId': nilo.videoId
	        },
		    success: function(req){
		        if(req.responseCode == 100000){
		        	if(req.data.s == 15){
		        		loadingTips('<span class="tips"></span>视频解析中', 0);
		        	}else{
		        		clearInterval(loadingTimer);
		        		$('#loading').hide();
		        		//reload video object & tag info
		        		if(reloadObjectInfo)
	        			{
		        			getObjData();
		        			initDiv();
	        			}
		        	}		        	
		        }else{
		        	loadingTips('<span class="tips"></span>' + req.responseMessage, 0);
		        }
		    }
    	});
	}, 1000);
}

function loadingTips(con, hasTimer){
	$('#loading .s').html(con);
	if(hasTimer){
		clearInterval(loadingTimer);
		
		go2Timer = setInterval(function(){
        	var _elem = $('#loading .timer'), _t = _elem.text();
        	_elem.text(--_t);
        	if(_t == 0){
        		clearInterval(go2Timer);
        		location.href = __baseURL;
        	}
        },1000);
	}
}