var locationSearch = location.search;
var videoId = getQueryStringByName("v");

$(window).load(function(){
	setTimeout(function(){
		$('#loading').hide();
		$('#nextPage').show();
		var index = $('.section.active').index('.section'), len = $('.section').length;
		if(index == (len-1)){
			$('#nextPage').hide();
		}
		$('#fullPage').removeClass('hide');
	}, 2000);
});



$(function() {
    // 微信分享
    wxShare(videoId);

	var overlayURL = __baseURL + '/video/videoPlayer?' + videoId;
	var	sw = screen.width, vw = 0, vh = 0, pageSize = 4, pageNo = 1, totalPage = 1;
	var orientVal = nilo.checkOrient(), playerSizeTime = null;
	
	if(sw > 640){
		vw = 640;
		vh = 360;
	}else{
		vw = sw;
		vh = parseInt(360 * sw / 640);
	}
	
	var pageHtml = '';
	
	if(videoId != ''){
		$.ajax({
	        url: __baseURL + "/video/queryLandingPage",
	        type: 'POST',
	        dataType: 'json',
	        data : {
                  'videoId': videoId
	        },
			async : false,
		    success: function(req){
		    	if(req.responseCode == 100000){
		    		if(req.data){
		    			var rd = req.data, imgData = rd.imgData;
		    			switch(rd.templiteId){
				        	case 1 : {
				        		//模板1
				        		pageHtml += build1stSection(imgData[0].actionUrl, imgData[0].ImgURL, imgData[1].actionUrl, imgData[1].ImgURL);

				        		pageHtml += buildSection(2, imgData[2].actionUrl, imgData[2].ImgURL);

				        		pageHtml += buildSection(3, imgData[3].actionUrl, imgData[3].ImgURL);

				        		pageHtml += buildSection(4, imgData[4].actionUrl, imgData[4].ImgURL);
				    	    
				        		pageHtml += initCommentArea(1, 5, imgData[5].ImgURL);				        		
				        		break;
				        	}
				        	case 2 : {
				        		//模板2
				        		pageHtml += build1stSection('', '', imgData[0].actionUrl, imgData[0].ImgURL);
				        		
				        		pageHtml += buildSection(2, imgData[1].actionUrl, imgData[1].ImgURL);

				        		pageHtml += buildSection(3, imgData[2].actionUrl, imgData[2].ImgURL);
				        		
				        		pageHtml += buildSection(4, imgData[3].actionUrl, imgData[3].ImgURL);
				    	    
				        		pageHtml += initCommentArea(2, 5, imgData[4].ImgURL);				        		
				        		break;
				        	}
				        	case 3 : {
				        		//模板3
				        		pageHtml += build1stSection('', '', imgData[0].actionUrl, imgData[0].ImgURL);
				    	    
				        		pageHtml += buildSection(2, imgData[1].actionUrl, imgData[1].ImgURL);

				        		pageHtml += buildSection(3, imgData[2].actionUrl, imgData[2].ImgURL);
				        		
				        		pageHtml += buildSection(4, imgData[3].actionUrl, imgData[3].ImgURL);
				        		
				        		pageHtml += buildSection(5, imgData[4].actionUrl, imgData[4].ImgURL);
				        						    	    
				        		pageHtml += initCommentArea(3, 6, imgData[5].ImgURL);
				        		break;
				        	}
			        	}
			    		
			    		
		    		}		    		
		        }else{
		        	pageHtml = req.responseMessage;
		        }
		    },
		    error : function(){
		    	pageHtml = '服务器忙，稍候请重试';
		    }
    	});
		
		$('#fullPage').html(pageHtml).fullpage({
			verticalCentered: false,
			resize : true,
			css3: true,
			//scrollOverflow : true,
			anchors : ['1stPage', '2ndPage', '3rdPage', '4thPage', '5thPage', '6thPage', 'lastPage'],
			/*navigation: true,
			navigationPosition: 'right',*/
			fixedElements: '#nextPage',
			normalScrollElements: '#commentList',
			bigSectionsDestination: 'top',
			
			onLeave: function(index, nextIndex, direction){
				var leavingSection = $(this), totalIndex = $('#fullPage .section').size();
				
				if(nextIndex == totalIndex){
					$('#nextPage').hide();
				}else{
					$('#nextPage').show();
				}
				
			}
		});
		
		//取总数
		$.ajax({
		        url: __baseURL + "/video/queryLandingPageCommentsCount",
		        type: 'POST',
		        dataType: 'json',
		        data : {
					'videoId': videoId
		        },
				async: false,
			    success: function(req){
			    	if(req.responseCode == 100000){
			    		if(req.data == 0){
			    			$('#commentList').html('<p id="noData" style="color:red; font-size:14px; padding:15px;">暂无评论</p>');
			    			$('#loadMore').hide();
			    		}else{
			    			totalPage = Math.ceil(req.data / pageSize);
			    		}			    		
			    	}
			    }
		});
		
		$(document).on('click', '#nextPage', function(e){
		  	$.fn.fullpage.moveSectionDown();
		  	
		  	var e = e || event;
		  	e.stopPropagation();
		});
		
		$(document).on('click', '#like li', function(){
			var _t = $(this), gradeId = _t.attr('data-grade'), len = $('#like li.click').size(), count = parseInt(_t.find('b').text());
			if(len > 0){
				swal('您已点击');
			}else{
				$.ajax({
				        url: __baseURL + "/video/addLandingPageGrades",
				        type: 'POST',
				        dataType: 'json',
				        data : {
							'videoId': videoId,
							'grade': gradeId
				        },
						async : false,
					    success: function(req){
					    	if(req.responseCode == 100000){
					    		count = ++count > 999999 ? '999999<sup>+</sup>' : count;
								_t.addClass('click').find('b').html(count);
					    	}
					    }
				});	
			}
		});
		
		$(document).on('click', '#loadMore', function(){
		  	pageNo++;
		  	if(pageNo <= totalPage){
		  		$('#commentList').append(getComment()).animate({'scrollTop' : (280 * pageNo) + 'px'});
		  	}else{
		  		swal('没有更多了哦~');
		  	}
		});
		
		$(document).on('click', '#addComment .headImgs', function(e){
			var e = e || event;
		  	//$('#addComment ul').slideToggle();
			e.stopPropagation();
		});
		$(document).on('click', '#addComment ul li', function(e){
			var _t = $(this), id = _t.attr('data-id'), e = e || event;
		  	$('#addComment ul').slideUp();
			$('#addComment .headImgs').attr({
				'class' : 'headImgs headImg' + id,
				'data-id' : id
			});
			e.stopPropagation();
		});
		$(document).on('keyup', '#commentCon', function(){
			if($(this).val() != ''){
				$('#commentSent').addClass('on');
			}else{
				$('#commentSent').removeClass('on');
			}
		});
		$(document).on('click', '#commentSent', function(){
			var commentCon = $.trim($('#commentCon').val());
			
			if(commentCon != ''){
				var headImgId = $('#addComment .headImgs').attr('data-id');				
				
				$.ajax({
				        url: __baseURL + "/video/addCommentsLandingPage",
				        type: 'POST',
				        dataType: 'json',
				        data : {
							'videoId': videoId,
							'contents': commentCon,
							'headId': headImgId
				        },
						async : false,
					    success: function(req){
					    	if(req.responseCode == 100000){
					    		$('#commentCon').val('');
					    		$('#noData').remove();
					    		$('#commentList').prepend(renderComment(req.data)).animate({'scrollTop' : 0});
					    	}else{
					    		swal(req.responseMessage);
					    	}
					    },
					    error: function(){
					    	swal('服务器忙，稍候请重试');
					    }
				});	
				
			}else{
				swal('请输入您的评论内容');
			}
		});	

	}
	setPlayerSize();
	
	//构建首屏section
	function build1stSection(actionUrl1, ImgURL1, actionUrl2, ImgURL2){
		var result = '';
		
		result += '<div class="section fp-auto-height-responsive active" id="section1">';
		if(ImgURL1 != ''){			
			if(actionUrl1 != ''){
				result += '<a href="' + actionUrl1 + '" target="_blank"><img data-src="' + ImgURL1 + '"></a>';
			}else{
				result += '<img data-src="' + ImgURL1 + '">';
			}
		}
		
		result += '    <iframe src="' + overlayURL + '" id="videoPlayer" width="' + vw + '" height="' + vh + '" frameborder="0" allowtransparency="true" scrolling="no"></iframe>';
		
		if(actionUrl2 != ''){
			result += '<a href="' + actionUrl2 + '" target="_blank"><img data-src="' + ImgURL2 + '"></a>';
		}else{
			result += '<img data-src="' + ImgURL2 + '">';
		}
		
		result += '</div>';
		
		return result;
	}

	//构建section
	function buildSection(sectionId, actionUrl, ImgURL){
		var result = '';
		
		result += '<div class="section fp-auto-height-responsive" id="section' + sectionId + '">';
		result += '    <a href="' + actionUrl + '" target="_blank"><img data-src="' + ImgURL + '"></a>';
		result += '</div>';
		
		return result;
	}

	//初始化评论区
	function initCommentArea(templateId, sectionId, bgImg){
		var result = '', bgUrl = '', gradeData = null;	
		result += '<div class="section fp-auto-height-responsive" id="section' + sectionId + '">';
		
		if(bgImg){
			bgUrl = 'style="background-image:url(' + bgImg + ')"';
		}
		
		result += '<div id="commentBg' + templateId + '"' + bgUrl + '></div>';
		
		result += '<div class="commentDiv">';
				
		result += '	 <ul id="like">';
		$.ajax({
		        url: __baseURL + "/video/queryLandingPageGrades",
		        type: 'POST',
		        dataType: 'json',
		        data : {
	                  'videoId': videoId
		        },
				async : false,
			    success: function(req){
			    	if(req.responseCode == 100000){
			    		if(req.data){
			    			gradeData = req.data;
			    		}
			    	}
			    }
		});
		
		result += '    	  <li data-grade="4">';
		result += '        	<em class="icon1"></em>';
		result += '            太好玩了(<b>' + getCountByGradeValue(gradeData, 4) + '</b>)';
		result += '        </li>';
		result += '        <li data-grade="3">';
		result += '        	<em class="icon2"></em>';
		result += '            很好玩了(<b>' + getCountByGradeValue(gradeData, 3) + '</b>)';
		result += '        </li>';
		result += '        <li data-grade="2">';
		result += '        	<em class="icon3"></em>';
		result += '            一般(<b>' + getCountByGradeValue(gradeData, 2) + '</b>)';
		result += '        </li>';
		result += '        <li data-grade="1">';
		result += '        	<em class="icon4"></em>';
		result += '            不喜欢(<b>' + getCountByGradeValue(gradeData, 1) + '</b>)';
		result += '        </li>';
		result += '    </ul><!--like-->';
		    
		result += '    <div id="commentList">';		
		result += getComment();
		result += '    </div><!--commentList-->';
		result += '    <a id="loadMore">加载更多</a>';
		    
		result += '    <div id="addComment">';
		result += '    	<div class="wrap">';
		result += '    	  <div class="headImgs headImg1" data-id="1">';
		result += '        	<ul>';
		result += '            	<li class="headImg1" data-id="1"></li>';
		result += '                <li class="headImg2" data-id="2"></li>';
		result += '                <li class="headImg3" data-id="3"></li>';
		result += '                <li class="headImg4" data-id="4"></li>';
		result += '                <li class="headImg5" data-id="5"></li>';
		result += '            </ul>';
		result += '        </div>';
		result += '        <input type="text" placeholder="发表你的评论" value="" id="commentCon" />';
		result += '        <input type="button" value="发送" id="commentSent" />';
		result += '     </div>';
		result += '    </div><!--addComment-->';
		    
		result += '</div>';
				
		result += '</div>';		
		
		result += '<div id="nextPage" onclick=""></div>';		
		return result;
	}
	
	//获取评论
	function getComment(){
		var result = '';
		$.ajax({
		        url: __baseURL + "/video/queryLandingPageComments",
		        type: 'POST',
		        dataType: 'json',
		        data : {
					'videoId': videoId,
					'limit': pageSize,
					'page': pageNo
		        },
				async: false,
			    success: function(req){
			    	if(req.responseCode == 100000){
			    		if(req.data){
			    			$.each(req.data, function(){
			    				result += renderComment(this);
			    			});
			    		}
			    	}
			    }
		});
		return result;
	}
	
	//渲染评论
	function renderComment(data){
		var result = '';
		
		if(data){
			result += '<dl>';
			result += '     <dt class="headImg' + data.head + '"></dt>';
			result += '     <dd>';
			result += '        <div><span class="ip">' + data.name + '</span><span class="ymd">' + data.date + '</span></div>';
			result += '        <div class="con">' + data.contents + '</div>';
			result += '        <p class="time">' + data.time + '</p>';
			result += '     </dd>';
			result += '</dl>';
		}
		
		return result;
	}
	
	//根据grade的值获取其count
	function getCountByGradeValue(jsonData, gradeValue){
	    var count = 0, result = '';
		$(jsonData).each(function(){
			if(this.grade == gradeValue){
				count = this.count;
				return false;
			}
		});
		result = count > 999999 ? '999999<sup>+</sup>' : count;
		return result;
	}
	
	//用户变化屏幕方向时调用
	$(window).bind('orientationchange', function(){
		clearTimeout(playerSizeTime);
		
		orientVal = nilo.checkOrient();
				
		playerSizeTime = setTimeout(function() {
	    	setPlayerSize();
	    }, 500);
	});


	function setPlayerSize(){
		var _vw = 640, _vh = 360;
		
		if(orientVal == 'l'){//横屏
			$.fn.fullpage.setAllowScrolling(false);
			$('#fullPage').addClass('fullScreen');
			$('#nextPage,#section1 img').hide();
		}else{
			$.fn.fullpage.setAllowScrolling(true);
			$('#fullPage').removeClass('fullScreen');
			$('#section1 img').show();
		}
		
		if(orientVal == 'l' && nilo.checkPlatform() == 'i'){
			_vw = screen.height;
		}else{		
			_vw = screen.width;
		}
		
		_vw = _vw >= 1024 ? 640 : _vw;
		_vh = parseInt(360 * _vw / 640);
		
		$('#videoPlayer').attr({'width' : _vw, 'height' : _vh})

	}
});


function getQueryStringByName(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null){
        return unescape(r[2])
    }
    return null;
}
