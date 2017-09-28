/**
 * Created by luof on 2017/8/27.
 */
var orientVal = nilo.checkOrient();

$.ajaxSetup({  
    async : false  
});

var vpw = $('html,body')[0].clientWidth;
var vph = $('html,body')[0].clientHeight;

var locationSearch = location.search;
//var locationSearch = '?videoId=7ujn3scved';
var locationSearchSub = locationSearch.substring(1,locationSearch.length);
var vpCtrlTime = null, playerSizeTime = null, previewTime = null;
var videoWidth=640; //视频分辨率
var videoHeight=480;//视频分辨率

(function () {
    var locationSearch=location.search;
    //var locationSearch='?videoId=7ujn3scved';
    var locationSearchSub=locationSearch.substring(1,locationSearch.length);
    
    var videoFrameRate = 25;    
    
    var ajaxData; //object数据
    var frameList;
    var tagData;  //tag数据
    var totalTime=0 //单位为秒 
    var times=null;//时间倒计时
    var timeIndex=0;//时间初始值
    var h=0;//小时
    var m=0;//分钟
    var s=0;//秒数    

    //播放控制
    var oVideoPreviewBoxId=nilo.getById('videoPreviewBoxId');//预览视频
    var oVideoPreviewBoxBigPlay=nilo.getById("videoPreviewBoxBigPlay");//播放大按钮 //整个视频屏幕
    var oVideoPreviewOverlayId=nilo.getById("videoPreviewOverlayId");
    var oVideoPreviewBoxObjDivId=nilo.getById('videoPreviewBoxObjDivId');//tag标签     
    
    //视频点击时获取onclick事件
    oVideoPreviewBoxObjDivId.onclick=function(e){
    	if (oVideoPreviewBoxId.paused) {
    		$('.zy_controls').css({'opacity':'0', 'bottom':'-45px'});//隐藏控制条
            previewPlay();
            $('.zy_playpause_btn').removeClass('zy_play').addClass('zy_pause');
            
            //iOS手机 微信里
        	if(nilo.checkPlatform() == 'i' && nilo.isWeixin() == 1){
        		setTimeout(function(){
        			previewPlay();
        			$('.zy_playpause_btn').removeClass('zy_play').addClass('zy_pause');
    			},10);
        	}
        }else {        	
        	$('.zy_controls').css({'opacity':'1', 'bottom':'0'});
        	
        	var e = e || window.event;        	
        	previewShowOverlay(e.offsetX,e.offsetY);
        }
    }    
    //绑定按钮事件end

    //开始加载视频数据
    objectData();
    
  //获取数据
    function objectData() {
        $.get(__baseURL+'/tag/queryPreview?videoId='+locationSearchSub,function(data,status){

            ajaxData=JSON.parse(data);
            if(ajaxData.responseCode==100000){
            	$('#videoPreviewBoxId').attr({
            		'poster' : ajaxData.data.loadingURL,
            		'src' : ajaxData.data.videoUrl + '?v=' + videoVersion
            	});
            	zymedia('video',{enableFullscreen: false});
            	videoFrameRate=ajaxData.data.rate;//帧率
            	videoWidth=ajaxData.data.width;//分辨率宽度
                videoHeight=ajaxData.data.height;//分辨率宽度
                frameList=ajaxData.data.objects;//每帧
              //  console.log("frameList:"+frameList);
//                     initDiv();
                
                setPlayerSize();
            }else{
            	swal(ajaxData.responseMessage);
            }
        });
    }
 
    //播放
    function previewPlay() {
    	if(!frameList){
    		swal('视频文件暂未解析');
    		return false;
    	}
        oVideoPreviewBoxId.play();
        oVideoPreviewBoxBigPlay.className='videoPreviewBoxPlayNone';
        
        oVideoPreviewOverlayId.style.display="none";
        var vardivIframe=document.getElementById("divIframe");
        vardivIframe.src="";
         
        previewCountDown();
    }
    
    
    //播放完成
    oVideoPreviewBoxId.onended=function () {    	
        oVideoPreviewBoxBigPlay.className='';
        clearInterval(previewTime); 
    }
    //声音

    function previewCountDown() {
        previewTime=setInterval(function () {
        	//视频播放的当前秒数
            var currSecond = oVideoPreviewBoxId.currentTime;
            //倒计时
            var h=Math.floor(currSecond/60/60%24);
            var m=Math.floor(currSecond/60%60);
            var s=Math.floor(currSecond%60);
            
            $("#videoPreviewBoxObjDivId").empty();
            
            $(".tagDivBorderNes").css('display','none')
            
            //当前帧数
            var frameNo = Math.floor(currSecond*videoFrameRate);
            for(var i=0;i<frameList.length;i++){
            	var objFF = frameList[i].fno;
            	if(objFF == frameNo)
        		{
            		//得到某一帧的object list
            		var objs = frameList[i].objs;
            		for(var j=0;j<objs.length;j++)
        			{
            			var obj = objs[j];
            			var tagDiv=document.createElement("div");
            			tagDiv.className="tagDivBorderNes "+"delete"+obj.oid;
            			tagDiv.id = "tag_"+objFF + "_"+ obj.oid;
            			
            			var imgW = obj.imgW;
                   	 	var imgH = obj.imgH;
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
    
    function previewShowOverlay(positionX,positionY){
    	var tagElem = $('.tagDivBorderNes:visible'), 
    		isShowNo = tagElem.length;
    	
    	if(isShowNo){
    		var _id = tagElem.attr('id'),
    			_tmpArr = _id.split('_'),
    			_left = parseInt(tagElem.css('left')),
    			_top = parseInt(tagElem.css('top')),
    			_width = parseInt(tagElem.css('width')),
    			_height = parseInt(tagElem.css('height')),
    			_range = 50
    		;
    		
    		if(positionX > (_left - _range) && positionX < (_left + _width + _range) && positionY > (_top - _range) && positionY < (_top + _height + _range)){
    			console.log('objFno=' + _tmpArr[1] + ', objOid=' + _tmpArr[2]);
        	    //从object list中根据objFno和objOid的值得到obj
        	    var obj = getObjByFnoOidValue(frameList, _tmpArr[1], _tmpArr[2]);
        	    if(obj){
        	    	$('.zy_playpause_btn').removeClass('zy_pause').addClass('zy_play');
            		
            		oVideoPreviewBoxId.pause();
            		oVideoPreviewBoxBigPlay.className='';
            	    clearInterval(previewTime);
        	    	
        	    	displayOverlayInfo(obj);
        	    }
    		}else{
    			//点击位置没在范围内
    			return false;
    		}   		
    		
    	}else{
    		return false;
    	}
    }
    
  //显示overlay
    function displayOverlayInfo(obj){
    	if(obj == null || obj.tagPopStyle == null){
    		return false;
    	}
    		
    	//根据tag的不同动作显示不同的overlay
   		if(obj.tagPopStyle == 1){
   			//打开新窗口
   			window.open(obj.tagPopUrl);
		}else if(obj.tagPopStyle == 2){
			//显示overlay
			var _width = __baseVideoWidth + 'px',
				_height = (__baseVideoHeight - 10) + 'px',
				_top = '10px',
				_right = 'auto',
				_left = '0px',
				_bottom = 'auto'
			;
			
			if(obj.overlayShowType != 1){
				switch(obj.overlayPopPosition){
					case 1 : {
						//left												
						_width = Math.floor(__baseVideoWidth * obj.overlayWidth) + 'px';
	                    _height = __baseVideoHeight + 'px';
	                    _top = '0px';
						
						break;
					}
					case 2 : {
						//right						
						_width = Math.floor(__baseVideoWidth * obj.overlayWidth) + 'px';
	                    _height = __baseVideoHeight + 'px';
	                    _top = '0px';
	                    _left = 'auto';
	                    _right = '0px';
						
						break;
					}
					case 3 : {
						//top
						_width = __baseVideoWidth + 'px';
	                    _height = Math.floor(__baseVideoHeight * obj.overlayHeight) + 'px';
	                    _top = '0px';
						
						break;
					}
					default : {
						//bottom
						_width = __baseVideoWidth + 'px';
						_height = Math.floor(__baseVideoHeight * obj.overlayHeight) + 'px';
						_top = 'auto';
	                    _bottom = '0px';
					}
					
				}
			}
			$('#videoPreviewOverlayId').css({
				'z-index' : 1000, 
				'width' : _width,
				'height' : _height,
				'top' : _top,
				'right' : _right,
				'bottom' : _bottom,
				'left' : _left
			}).show();
			
			var overlayURL = obj.overlayUrl+"?ooid="+obj.tagId + '&v=1.4';
			$('#divIframe').attr('src', overlayURL);
		}else{//点击tag没有任何反应
   			return false;
		}
    }

    
    //在播放条上点击时进行捕获并进行处理

    $('#videoPlayerDiv').mousemove(function(e){
    	clearTimeout(vpCtrlTime);
    	
    	var y = e.originalEvent.y || e.originalEvent.layerY || 0, 
    		elem = $('#videoPreviewControl');
    	if(y > (__baseVideoHeight - 50)){    		
    		elem.addClass('show');    		
    	}
    	if(y < 50){
    		elem.removeClass('show');		
    	}
    }).mouseleave(function(){
    	vpCtrlTime = setTimeout(function(){
    		$('#videoPreviewControl').removeClass('show');
    	},100);
    });
    
    function initDiv() {
    	for(var i=0;i<frameList.length;i++){
        	var objFF = frameList[i].fno;
    		//console.log(Math.floor(currSecond)+"," + frameNo + ",true");
    		//得到某一帧的object list
    		var objs = frameList[i].objs;
    		for(var j=0;j<objs.length;j++)
			{
    			var obj = objs[j];
    			var tagDiv=document.createElement("div");
    			tagDiv.className="tagDivBorderNes "+"delete"+obj.oid;
    			tagDiv.id = "tag_"+objFF + "_"+ obj.oid;
    			 var objX=parseInt(obj.objX * (__baseVideoWidth/videoWidth) + (obj.objW * (__baseVideoWidth/videoWidth))/2);
                 var objY=parseInt(obj.objY * (__baseVideoHeight/videoHeight)+ (obj.objH * (__baseVideoHeight/videoHeight))/2);
                 var objW=parseInt((obj.objW * (__baseVideoWidth/videoWidth)));
                 var objH=parseInt((obj.objH * (__baseVideoHeight/videoHeight))); 
                 var imgW = obj.imgW;
            	 var imgH = obj.imgH;
            	 
                 tagDiv.style.left=objX+"px";
                 tagDiv.style.top=objY+"px";
                 tagDiv.style.width=imgW+"px";
                 tagDiv.style.height=imgH+"px"; 
                 
                 tagDiv.style.display="none";
                 
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
      
})()


//用户变化屏幕方向时调用
$(window).bind('orientationchange', function(){
	clearTimeout(playerSizeTime);
	
	orientVal = nilo.checkOrient();
	
	if(orientVal == 'l' && nilo.checkPlatform() == 'i'){
		vpw = screen.height;
		vph = screen.width - 80;
	}else{		
		vpw = screen.width;
		vph = screen.height - 120;
	}
	
	playerSizeTime = setTimeout(function() {
    	setPlayerSize();
    }, 500);
});

function setPlayerSize(){
	switch(orientVal){
		case 'l' : {
			//横屏
			__baseVideoWidth = parseInt(__baseVideoWidth * vph / __baseVideoHeight);
			__baseVideoHeight = vph;
			
			break;
		}
		case 'p' : {
			//竖屏			
			__baseVideoHeight = parseInt(vpw * __baseVideoHeight / __baseVideoWidth);
			__baseVideoWidth = vpw;
			break;
		}
		default : {			
			var val1 = vpw / vph,
				val2 = videoWidth / videoHeight
			;

			if(val1 == val2){
				__baseVideoWidth = vpw;
				__baseVideoHeight = vph;
			}else if(val1 > val2){
				__baseVideoWidth = parseInt((videoWidth * vph) / videoHeight);
				__baseVideoHeight = vph;				
			}else{
				__baseVideoWidth = vpw;
				__baseVideoHeight = parseInt((videoHeight * vpw) / videoWidth);
			}
		}
	}
	
	//根据实际宽高设置
	$('#videoPlayerDiv').css({'width' : __baseVideoWidth, 'height' : __baseVideoHeight, 'margin' : '0 auto'})
	$('#videoPreviewBoxId,#videoPreviewBoxObjDivId').css({'width' : __baseVideoWidth, 'height' : __baseVideoHeight});
	$('#videoPreviewBoxBigPlay').show();
}

//根据fno和oid的值获取对应的obj
function getObjByFnoOidValue(jsonData, fnoVal, oidVal){
    var result = null;
	$(jsonData).each(function(){
		if(this.fno == fnoVal){
			
			$(this.objs).each(function(){
				if(this.oid == oidVal){
					result = this;
					return false;
				}
			});
			
		}
	});
	return result;
}
