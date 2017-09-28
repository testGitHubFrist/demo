$.ajaxSetup({  
    async : false  
}); 

	//	var __baseURL = "https://studio.hypervideo.cn/";
//	var __baseURL = "http://localhost:8080/clipsstudio/";
 
	console.log('_baseUrl:' + __baseURL);
	var _baseVideoWidth="480";
	var _baseVideoHeight="270";
	
    var locationSearch=location.search;
    var videoId = locationSearch.substring(1,locationSearch.length);
    
    var videoFrameRate = 25;
    var videoWidth=640; //视频分辨率
    var videoHeight=480;//视频分辨率
    
    var ajaxData; //object数据
    var frameList;
    var tagData;  //tag数据
    var oVideoPreviewBoxTimeSapnAjax=0//预览进度时间
    var totalTime=0 //单位为秒 
    var times=null;//时间倒计时
    var timeIndex=0;//时间初始值
    var h=0;//小时
    var m=0;//分钟
    var s=0;//秒数 
    
    var oVideoPreviewBoxObjDivId=nilo.getById('tagContainer');//tag标签 
    var oVideoPreviewBoxId=nilo.getById('video');//预览视频
    
    auto();
    function auto(){  //获取点击坐标 
        __baseVideoWidth=document.getElementById("video").offsetWidth;
        __baseVideoHeight=document.getElementById("video").offsetHeight; 
        swal('__baseVideoHeight:'+__baseVideoHeight);
        swal('__baseVideoWidth:' + __baseVideoWidth);
	}
	
	$(window).resize(function(){ //自动调整坐标位置
		auto();	
	})
	
  //视频点击时获取onclick事件
    oVideoPreviewBoxObjDivId.onclick=function (e) {
    	console.log('click x=' )  
    	if (oVideoPreviewBoxId.paused) {
    		oVideoPreviewBoxId.play();
        }else {
        	oVideoPreviewBoxId.pause();
        }
    }
 
  //开始加载视频数据
    objectData(); 
  //获取数据
    function objectData() {
        $.get(__baseURL+'/tag/queryPreview?videoId='+videoId,function(data,status){

            ajaxData=JSON.parse(data);
            console.log(ajaxData);
            if(ajaxData.data!=null){  
                if(ajaxData.responseCode==100000){
                	oVideoPreviewBoxId.src=ajaxData.data.videoUrl;
                	videoFrameRate=ajaxData.data.rate;//帧率
                	videoWidth=ajaxData.data.width;//分辨率宽度
                    videoHeight=ajaxData.data.height;//分辨率宽度
                    
                    frameList=ajaxData.data.objects;//每帧
                    console.log("frameList:"+frameList); 
                }
                else if(ajaxData.responseCode==0){
                	swal(ajaxData.responseCode)
                }
            }
            else{
                swal("无法获取视频数据");
            } 
        });
    }
    
    oVideoPreviewBoxId.addEventListener("play",
            function() {
    				previewCountDown();
//    				swal(oVideoPreviewBoxId.height);
//    				swal(oVideoPreviewBoxId.height);
      		}
    );
    
    oVideoPreviewBoxId.addEventListener("pause",
            function() {
    	 		clearInterval(previewTime);
      		}
    );
    
    
    

    function previewCountDown() {
        previewTime=setInterval(function () {
        	//视频播放的当前秒数
            var currSecond = oVideoPreviewBoxId.currentTime;
            
            if($("#tagContainer div").length>0){
                $("#tagContainer").empty();
            } 
            oVideoPreviewBoxObjDivId.style.display='none';
            
            //当前帧数
            var frameNo = Math.floor(currSecond*videoFrameRate);
            for(var i=0;i<frameList.length;i++){
            	var objFF = frameList[i].fno;
            	if(objFF == frameNo)
        		{
            		oVideoPreviewBoxObjDivId.style.display='block';
            		//得到某一帧的object list
            		var objs = frameList[i].objs;
            		for(var j=0;j<objs.length;j++)
        			{
            			var obj = objs[j];
            			var tagDiv=document.createElement("div");
            			tagDiv.className="tagDivBorderNes";
            			tagDiv.id = "tag_"+objFF + "_"+ obj.oid;
            			
            			var imgW = parseInt((obj.imgW * (__baseVideoWidth/videoWidth)));
                   	 	var imgH = parseInt((obj.imgH * (__baseVideoHeight/videoHeight)));
            			 var objX=parseInt(obj.objX * (__baseVideoWidth/videoWidth) + (obj.objW * (__baseVideoWidth/videoWidth))/2 - imgW/2);
                         var objY=parseInt(obj.objY * (__baseVideoHeight/videoHeight)+ (obj.objH * (__baseVideoHeight/videoHeight))/2  - imgH/2);
                         var objW=parseInt((obj.objW * (__baseVideoWidth/videoWidth)));
                         var objH=parseInt((obj.objH * (__baseVideoHeight/videoHeight))); 
                    	 
                         tagDiv.style.left=objX+"px";
                         tagDiv.style.top=objY+"px";
                         tagDiv.style.width=imgW+"px";
                         tagDiv.style.height=imgH+"px"; 
                         
                		 //TODO 根据tag不同配置显示不同信息，目前只是显示了图片,wirewax也是只显示了图片
                		 var tagDivImg=document.createElement("a"); 
                         tagDivImg.className='tagDivBorderImg';  
                         tagDivImg.style.width=imgW+"px";
                         tagDivImg.style.height=imgH+"px";
                         tagDivImg.href = "javascript:void(0)"; 
                         
                    	 var tagDivImg2=document.createElement("img");
                    	 
                         tagDivImg2.src=obj.styleUrl;
                         tagDivImg2.style.width=imgW+"px";
                         tagDivImg2.style.height=imgH+"px";
                         
                         tagDivImg.appendChild(tagDivImg2);
                         tagDiv.appendChild(tagDivImg); 
                         oVideoPreviewBoxObjDivId.appendChild(tagDiv);
        			}
        		}
            }
        },30)
    }
	 