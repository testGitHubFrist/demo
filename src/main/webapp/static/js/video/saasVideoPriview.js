var oSaasListQuit=nilo.getById('saasListQuit'); //退出
var oSaasBack2=nilo.getById('saasBack2'); //退出
var oSaasBack1=nilo.getById('saasBack1'); //退出
var oSaasListUserName=nilo.getById('saasListUserName');//用户名;

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
    window.location=__baseURL+"/video/videolist?page=1&ticket="+ticket;
}
oSaasBack2.onclick=function () { 
	var ticket=nilo.getCookie('ticket');
    window.location=__baseURL+"/video/videolist?page=1&ticket="+ticket;
}

oSaasListUserName.innerHTML=nilo.getCookie('nickName');



var locationSearch = location.search;
//var locationSearch = '?videoId=7ujn3scved';
var locationSearchSub = locationSearch.substring(1,locationSearch.length);
var overlayURL = __baseURL+"/video/videoPlayer?"+locationSearchSub;
var vardivIframe=document.getElementById("videoPlayer");
vardivIframe.src=overlayURL;
