/**
 * Created by baikaishui on 2015/7/6.
 */
var nilo={}||"";
var __baseURL = window.location.protocol + "//studio.hypervideo.cn/";
// var __baseURL = window.location.protocol + "//184zs64917.51mypc.cn/";
if(location.href.indexOf('localhost') > -1 || location.href.indexOf('http://192.168') > -1 || location.href.indexOf('http://127.0') > -1){
	__baseURL = "/clipsstudio/";
}
var __baseVideoWidth =688;
var __baseVideoHeight = 400;

var nilo= {
   videoId:"",
   videoUrl: "",
   videoRate:0,
   videoTime:0,
   videoWidth:0,
   videoHeight:0,
   videoObjData:[],
   videoTagData:[],
    //获得ID
    getById: function (id) {
        return document.getElementById(id);
    },
    //获得class
    getByClass: function (oParent, sClass) {
        var aEle = oParent.getElementsByTagName('*');
        var aResult = [];
        var re = new RegExp('\\b' + sClass + '\\b', 'i');
        var i = 0;
        for (i = 0; i < aEle.length; i++) {
            if (re.test(aEle[i].className)) {
                aResult.push(aEle[i]);
            }
        }

        return aResult;
    },
    //添加事件
    myAddEvent: function (obj, sEvent, fn) {
        if (obj.addEventListener) {
            obj.addEventListener(sEvent, fn, false);  //ie事件
        }
        else {
            obj.attachEvent("on" + sEvent, fn); //谷歌火狐，苹果，浏览器
        }
    },
    ////获取样式
    getStyle: function (obj, attr) {
        if (obj.currentStyle) {
            return obj.currentStyle[attr];
        }
        else {
            return getComputedStyle(obj, false)[attr];
        }
    },
    //阻止冒泡
    stopPropagation: function (event) {
        if (event.stopPropagation) {
            event.stopPropagation();//谷歌浏览器阻止事件冒泡
        }
        else {
            event.cancelBubble = true;//ie8以下浏览器阻止事件冒泡，
        }
    },
    //阻止默认事件
    preventDefault: function (event) {
        if (event.preventDefault) {
            event.preventDefault();
        }
        else {
            event.returnValue = false;//ie8以下浏览器阻止默认事件
        }
    },
    //事件的目标节点
    eventElement: function (event) {
        return event.target || event.srcElement;  //srcElement 兼容ie8以下
    },
    //事件名称
    eventType: function (event) {
        return event.type;
    },
    //拖拽
    Drag:function(id){
        var oDiv=nilo.getById(id);
        oDiv.onmousedown=function(ev){
            var oEvent=ev||event;
            disX=oEvent.clientX-oDiv.offsetLeft;
            disY=oEvent.clientY-oDiv.offsetTop;
            if(oDiv.setCapture){
                oDiv.onmousemove=fnMove
                oDiv.onmouseup=fnUp
                oDiv.setCapture()
            }
            else{
                document.onmousemove=fnMove
                document.onmouseup=fnUp
            }
            function fnMove(ev){
                var oEvent=ev||event;
                oDiv.style.left=oEvent.clientX-disX+"px";
                oDiv.style.top=oEvent.clientY-disY+"px";
            }
            function fnUp(){
                this.onmousemove=null;
                this.onmouseup=null;
                if(this.releaseCapture){
                    oDiv.releaseCapture()
                }
            }
            return false;
        }
    },
    //运动
    startMove:function(obj, json, fn)
    {
        clearInterval(obj.timer);
        obj.timer=setInterval(function (){
            var bStop=true;		//这一次运动就结束了——所有的值都到达了
            for(var attr in json)
            {
                //1.取当前的值
                var iCur=0;

                if(attr=='opacity')
                {
                    iCur=parseInt(parseFloat(nilo.getStyle(obj, attr))*100);
                }
                else
                {
                    iCur=parseInt(nilo.getStyle(obj, attr));
                }

                //2.算速度
                var iSpeed=(json[attr]-iCur)/8;
                iSpeed=iSpeed>0?Math.ceil(iSpeed):Math.floor(iSpeed);

                //3.检测停止
                if(iCur!=json[attr])
                {
                    bStop=false;
                }

                if(attr=='opacity')
                {
                    obj.style.filter='alpha(opacity:'+(iCur+iSpeed)+')';
                    obj.style.opacity=(iCur+iSpeed)/100;
                }
                else
                {
                    obj.style[attr]=iCur+iSpeed+'px';
                }
            }

            if(bStop)
            {
                clearInterval(obj.timer);

                if(fn)
                {
                    fn();
                }
            }
        }, 30)
    },
    //获取ajax

    ajax:function(url, fnSucc, fnFaild)
    {
        //1.创建ajax对象
        var oAjax=null;

        if(window.XMLHttpRequest)
        {
            oAjax=new XMLHttpRequest();
        }
        else
        {
            oAjax=new ActiveXObject("Microsoft.XMLHTTP");
        }

        //2.连接服务器
        //open(方法, url, 是否异步)
        oAjax.open('GET', url, true);

        //3.发送请求
        oAjax.send();

        //4.接收返回
        //OnReadyStateChange
        oAjax.onreadystatechange=function ()
        {
            if(oAjax.readyState==4)
            {
                if(oAjax.status==200)
                {
                    //alert('成功：'+oAjax.responseText);
                    fnSucc(oAjax.responseText);
                }
                else
                {
                    if(fnFaild)
                    {
                        fnFaild();
                    }
                }
            }
        }
    },
    //设置cookie
    //示例：nilo.setCookie("键名", "值", {path: "/", domain:""})，options为空表示会话cookie  --luof 添加浏览器兼容
    setCookie:function (name, value, options)
    {
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString();
        }
        var path = options.path ? '; path=' + (options.path) : '';
        var domain = options.domain ? '; domain=' + (options.domain) : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
        
    },
    //获取cookie
    //示例：nilo.getCookie("键名")，有则返回对应的值，没有则返回null。 --luof 添加浏览器兼容
    getCookie:function (name)
    {
    	var cookieValue = null;
		var dc = document.cookie;
	    if (dc && dc != '') {
	          var cookies = unescape(dc).split(';');
	          for (var i = 0; i < cookies.length; i++) {
	              var cookie = cookies[i].toString().replace(/^\s+|\s+$/gm,'');
	              if (cookie.substring(0, name.length + 1) == (name + '=')) {
	                  cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
	                  break;
	              }
	          }
	    }
	    return cookieValue;
    },
    //删除cookie
    removeCookie:function (name)
    {
//        this.setCookie(name, '1', -1);
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval=nilo.getCookie(name);
        if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
    },
    //是否在微信浏览器里 1:是  0:不是 --luof
    isWeixin : function(){
    	var ua = navigator.userAgent.toLowerCase();
    	if(ua.match(/MicroMessenger/i) == 'micromessenger') {
    		return 1;
     	} else {
    		return 0;
    	}
    },
    //检测手机平台     i:iPhone a:Android --luof
    checkPlatform : function(){
        var u = navigator.userAgent;
        if(/AppleWebKit.*Mobile/i.test(u) || (/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE/.test(u))){
	         if(location.href.indexOf("?mobile")<0){
		          try{
			           if(/iPhone|mac|iPod|iPad/i.test(u)){
			        	   return 'i';
			           }else{
			        	   return 'a';
			           }
		          }catch(e){}
	         }
        }else if( u.indexOf('iPad') > -1){
            return 'i';
        }else{
            return 'a';
        }
    },
    //建立一個可存取到该file的url
    getObjectURL : function(file) {
	    var url = null;
	    if (window.createObjectURL != undefined) { // basic
	    	url = window.createObjectURL(file);
	    } else if (window.URL != undefined) { // mozilla(firefox)
	    	url = window.URL.createObjectURL(file);
	    } else if (window.webkitURL != undefined) { // webkit or chrome
	    	url = window.webkitURL.createObjectURL(file);
	    }
	    return url;
    },
    //检测横竖屏      l:landscape(横屏) p:portrait(竖屏) --luof
    checkOrient : function(){
    	var orientation = '';
    	if(window.orientation == 90 || window.orientation == -90){
    		orientation = 'l';
    	}else if(window.orientation == 0 || window.orientation == 180){
    		orientation = 'p';
    	}
    	return orientation;
    }

}
function videoTagImitationSelectId(selectIndex) {
    var oVideoTagImitationSelectId=nilo.getById("videoCompileImitationSelectId");
    var aVideoTagImitationSelectIdH4=oVideoTagImitationSelectId.getElementsByTagName("h5")[0];
    var aVideoTagImitationSelectIdH4Img=aVideoTagImitationSelectIdH4.getElementsByTagName("img")[0];
    var aVideoTagImitationSelectIdH4Em=aVideoTagImitationSelectIdH4.getElementsByTagName("em")[0];
    var aVideoTagImitationSelectIdH4A=aVideoTagImitationSelectIdH4.getElementsByTagName("a")[0];
    var aVideoTagImitationSelectIdUl=oVideoTagImitationSelectId.getElementsByTagName("ul")[0];
    var aVideoTagImitationSelectIdLi=aVideoTagImitationSelectIdUl.getElementsByTagName("li");
    var aVideoTagImitationSelectIdUlEm=aVideoTagImitationSelectIdUl.getElementsByTagName("em");
    var oVideoTagSmallIconId=nilo.getById("videoTagSmallIconId");//url小图标
    if(aVideoTagImitationSelectIdLi.length!=0){
        aVideoTagImitationSelectIdH4Img.src=aVideoTagImitationSelectIdLi[selectIndex].getElementsByTagName("img")[0].src;
        oVideoTagSmallIconId.src=aVideoTagImitationSelectIdLi[selectIndex].getElementsByTagName("img")[0].src;
        aVideoTagImitationSelectIdH4Em.innerHTML=aVideoTagImitationSelectIdLi[selectIndex].getElementsByTagName("em")[0].innerHTML;
        aVideoTagImitationSelectIdH4A.innerHTML=aVideoTagImitationSelectIdLi[selectIndex].getElementsByTagName("a")[0].innerHTML;
    }
    nilo.myAddEvent(aVideoTagImitationSelectIdH4,"click",function (ev) {
        aVideoTagImitationSelectIdUl.style.display="block";
        var oEvent=ev||event;
        nilo.stopPropagation(oEvent)
    })
    for(var i=0;i<aVideoTagImitationSelectIdLi.length;i++){
        aVideoTagImitationSelectIdLi[i].index=i;
        nilo.myAddEvent(aVideoTagImitationSelectIdLi[i],"click",function () {
            aVideoTagImitationSelectIdUl.style.display="none";

            aVideoTagImitationSelectIdH4Img.src=aVideoTagImitationSelectIdLi[this.index].getElementsByTagName("img")[0].src;
            oVideoTagSmallIconId.src=aVideoTagImitationSelectIdLi[this.index].getElementsByTagName("img")[0].src;
            aVideoTagImitationSelectIdH4Em.innerHTML=aVideoTagImitationSelectIdLi[this.index].getElementsByTagName("em")[0].innerHTML;
            aVideoTagImitationSelectIdH4A.innerHTML=aVideoTagImitationSelectIdLi[this.index].getElementsByTagName("a")[0].innerHTML;
        })
    }
    document.onclick=function (ev) {
        aVideoTagImitationSelectIdUl.style.display="none";

    }
}


//sweet-alert
!function(e,t){function n(t){var n=y(),o=n.querySelector("h2"),r=n.querySelector("p"),a=n.querySelector("button.cancel"),c=n.querySelector("button.confirm");if(o.innerHTML=w(t.title).split("\n").join("<br>"),r.innerHTML=w(t.text||"").split("\n").join("<br>"),t.text&&S(r),C(n.querySelectorAll(".icon")),t.type){for(var l=!1,s=0;s<d.length;s++)if(t.type===d[s]){l=!0;break}if(!l)return e.console.error("Unknown alert type: "+t.type),!1;var u=n.querySelector(".icon."+t.type);switch(S(u),t.type){case"success":v(u,"animate"),v(u.querySelector(".tip"),"animateSuccessTip"),v(u.querySelector(".long"),"animateSuccessLong");break;case"error":v(u,"animateErrorIcon"),v(u.querySelector(".x-mark"),"animateXMark");break;case"warning":v(u,"pulseWarning"),v(u.querySelector(".body"),"pulseWarningIns"),v(u.querySelector(".dot"),"pulseWarningIns")}}if(t.imageUrl){var f=n.querySelector(".icon.custom");f.style.backgroundImage="url("+t.imageUrl+")",S(f);var m=80,p=80;if(t.imageSize){var g=t.imageSize.split("x")[0],b=t.imageSize.split("x")[1];g&&b?(m=g,p=b,f.css({width:g+"px",height:b+"px"})):e.console.error("Parameter imageSize expects value with format WIDTHxHEIGHT, got "+t.imageSize)}f.setAttribute("style",f.getAttribute("style")+"width:"+m+"px; height:"+p+"px")}n.setAttribute("data-has-cancel-button",t.showCancelButton),t.showCancelButton?a.style.display="inline-block":C(a),t.cancelButtonText&&(a.innerHTML=w(t.cancelButtonText)),t.confirmButtonText&&(c.innerHTML=w(t.confirmButtonText)),c.style.backgroundColor=t.confirmButtonColor,i(c,t.confirmButtonColor),n.setAttribute("data-allow-ouside-click",t.allowOutsideClick);var h=t.doneFunction?!0:!1;n.setAttribute("data-has-done-function",h),n.setAttribute("data-timer",t.timer)}function o(e,t){e=String(e).replace(/[^0-9a-f]/gi,""),e.length<6&&(e=e[0]+e[0]+e[1]+e[1]+e[2]+e[2]),t=t||0;var n="#",o,r;for(r=0;3>r;r++)o=parseInt(e.substr(2*r,2),16),o=Math.round(Math.min(Math.max(0,o+o*t),255)).toString(16),n+=("00"+o).substr(o.length);return n}function r(e,t){for(var n in t)t.hasOwnProperty(n)&&(e[n]=t[n]);return e}function a(e){var t=/^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(e);return t?parseInt(t[1],16)+", "+parseInt(t[2],16)+", "+parseInt(t[3],16):null}function i(e,t){var n=a(t);e.style.boxShadow="0 0 2px rgba("+n+", 0.8), inset 0 0 0 1px rgba(0, 0, 0, 0.05)"}function c(){var e=y();T(p(),10),S(e),v(e,"showSweetAlert"),b(e,"hideSweetAlert"),I=t.activeElement;var n=e.querySelector("button.confirm");n.focus(),setTimeout(function(){v(e,"visible")},500);var o=e.getAttribute("data-timer");"null"!==o&&""!==o&&(e.timeout=setTimeout(function(){l()},o))}function l(){var n=y();E(p(),5),E(n,5),b(n,"showSweetAlert"),v(n,"hideSweetAlert"),b(n,"visible");var o=n.querySelector(".icon.success");b(o,"animate"),b(o.querySelector(".tip"),"animateSuccessTip"),b(o.querySelector(".long"),"animateSuccessLong");var r=n.querySelector(".icon.error");b(r,"animateErrorIcon"),b(r.querySelector(".x-mark"),"animateXMark");var a=n.querySelector(".icon.warning");b(a,"pulseWarning"),b(a.querySelector(".body"),"pulseWarningIns"),b(a.querySelector(".dot"),"pulseWarningIns"),e.onkeydown=M,t.onclick=A,I&&I.focus(),z=void 0,clearTimeout(n.timeout)}function s(){var e=y();e.style.marginTop=B(y())}var u=".sweet-alert",f=".sweet-overlay",d=["error","warning","info","success"],m={title:"",text:"",type:null,allowOutsideClick:!1,showCancelButton:!1,closeOnConfirm:!0,closeOnCancel:!0,confirmButtonText:"确定",confirmButtonColor:"#2196f3",cancelButtonText:"取消",imageUrl:null,imageSize:null,timer:null},y=function(){return t.querySelector(u)},p=function(){return t.querySelector(f)},g=function(e,t){return new RegExp(" "+t+" ").test(" "+e.className+" ")},v=function(e,t){g(e,t)||(e.className+=" "+t)},b=function(e,t){var n=" "+e.className.replace(/[\t\r\n]/g," ")+" ";if(g(e,t)){for(;n.indexOf(" "+t+" ")>=0;)n=n.replace(" "+t+" "," ");e.className=n.replace(/^\s+|\s+$/g,"")}},w=function(e){var n=t.createElement("div");return n.appendChild(t.createTextNode(e)),n.innerHTML},h=function(e){e.style.opacity="",e.style.display="block"},S=function(e){if(e&&!e.length)return h(e);for(var t=0;t<e.length;++t)h(e[t])},x=function(e){e.style.opacity="",e.style.display="none"},C=function(e){if(e&&!e.length)return x(e);for(var t=0;t<e.length;++t)x(e[t])},k=function(e,t){for(var n=t.parentNode;null!==n;){if(n===e)return!0;n=n.parentNode}return!1},B=function(e){e.style.left="-9999px",e.style.display="block";var t=e.clientHeight,n;return n="undefined"!=typeof getComputedStyle?parseInt(getComputedStyle(e).getPropertyValue("padding"),10):parseInt(e.currentStyle.padding),e.style.left="",e.style.display="none","-"+parseInt(t/2+n)+"px"},T=function(e,t){if(+e.style.opacity<1){t=t||16,e.style.opacity=0,e.style.display="block";var n=+new Date,o=function(){e.style.opacity=+e.style.opacity+(new Date-n)/100,n=+new Date,+e.style.opacity<1&&setTimeout(o,t)};o()}e.style.display="block"},E=function(e,t){t=t||16,e.style.opacity=1;var n=+new Date,o=function(){e.style.opacity=+e.style.opacity-(new Date-n)/100,n=+new Date,+e.style.opacity>0?setTimeout(o,t):e.style.display="none"};o()},q=function(n){if(MouseEvent){var o=new MouseEvent("click",{view:e,bubbles:!1,cancelable:!0});n.dispatchEvent(o)}else if(t.createEvent){var r=t.createEvent("MouseEvents");r.initEvent("click",!1,!1),n.dispatchEvent(r)}else t.createEventObject?n.fireEvent("onclick"):"function"==typeof n.onclick&&n.onclick()},O=function(t){"function"==typeof t.stopPropagation?(t.stopPropagation(),t.preventDefault()):e.event&&e.event.hasOwnProperty("cancelBubble")&&(e.event.cancelBubble=!0)},I,A,M,z;e.sweetAlertInitialize=function(){var e='<div class="sweet-overlay" tabIndex="-1"></div><div class="sweet-alert" tabIndex="-1"><div class="icon error"><span class="x-mark"><span class="line left"></span><span class="line right"></span></span></div><div class="icon warning"> <span class="body"></span> <span class="dot"></span> </div> <div class="icon info"></div> <div class="icon success"> <span class="line tip"></span> <span class="line long"></span> <div class="placeholder"></div> <div class="fix"></div> </div> <div class="icon custom"></div> <h2>Title</h2><p>Text</p><button class="cancel" tabIndex="2">取消</button><button class="confirm" tabIndex="1">确定</button></div>',n=t.createElement("div");n.innerHTML=e,t.body.appendChild(n)},e.sweetAlert=e.swal=function(){function a(t){var n=t||e.event,o=n.keyCode||n.which;if(-1!==[9,13,32,27].indexOf(o)){for(var r=n.target||n.srcElement,a=-1,c=0;c<S.length;c++)if(r===S[c]){a=c;break}9===o?(r=-1===a?w:a===S.length-1?S[0]:S[a+1],O(n),r.focus(),i(r,f.confirmButtonColor)):(r=13===o||32===o?-1===a?w:void 0:27!==o||h.hidden||"none"===h.style.display?void 0:h,void 0!==r&&q(r,n))}}function u(t){var n=t||e.event,o=n.target||n.srcElement,r=n.relatedTarget,a=g(d,"visible");if(a){var i=-1;if(null!==r){for(var c=0;c<S.length;c++)if(r===S[c]){i=c;break}-1===i&&o.focus()}else z=o}}if(void 0===arguments[0])return e.console.error("sweetAlert expects at least 1 attribute!"),!1;var f=r({},m);switch(typeof arguments[0]){case"string":f.title=arguments[0],f.text=arguments[1]||"",f.type=arguments[2]||"";break;case"object":if(void 0===arguments[0].title)return e.console.error('Missing "title" argument!'),!1;f.title=arguments[0].title,f.text=arguments[0].text||m.text,f.type=arguments[0].type||m.type,f.allowOutsideClick=arguments[0].allowOutsideClick||m.allowOutsideClick,f.showCancelButton=void 0!==arguments[0].showCancelButton?arguments[0].showCancelButton:m.showCancelButton,f.closeOnConfirm=void 0!==arguments[0].closeOnConfirm?arguments[0].closeOnConfirm:m.closeOnConfirm,f.closeOnCancel=void 0!==arguments[0].closeOnCancel?arguments[0].closeOnCancel:m.closeOnCancel,f.timer=arguments[0].timer||m.timer,f.confirmButtonText=m.showCancelButton?"Confirm":m.confirmButtonText,f.confirmButtonText=arguments[0].confirmButtonText||m.confirmButtonText,f.confirmButtonColor=arguments[0].confirmButtonColor||m.confirmButtonColor,f.cancelButtonText=arguments[0].cancelButtonText||m.cancelButtonText,f.imageUrl=arguments[0].imageUrl||m.imageUrl,f.imageSize=arguments[0].imageSize||m.imageSize,f.doneFunction=arguments[1]||null;break;default:return e.console.error('Unexpected type of argument! Expected "string" or "object", got '+typeof arguments[0]),!1}n(f),s(),c();for(var d=y(),p=function(t){var n=t||e.event,r=n.target||n.srcElement,a="confirm"===r.className,i=g(d,"visible"),c=f.doneFunction&&"true"===d.getAttribute("data-has-done-function");switch(n.type){case"mouseover":a&&(r.style.backgroundColor=o(f.confirmButtonColor,-.04));break;case"mouseout":a&&(r.style.backgroundColor=f.confirmButtonColor);break;case"mousedown":a&&(r.style.backgroundColor=o(f.confirmButtonColor,-.14));break;case"mouseup":a&&(r.style.backgroundColor=o(f.confirmButtonColor,-.04));break;case"focus":var s=d.querySelector("button.confirm"),u=d.querySelector("button.cancel");a?u.style.boxShadow="none":s.style.boxShadow="none";break;case"click":if(a&&c&&i)f.doneFunction(!0),f.closeOnConfirm&&l();else if(c&&i){var m=String(f.doneFunction).replace(/\s/g,""),y="function("===m.substring(0,9)&&")"!==m.substring(9,10);y&&f.doneFunction(!1),f.closeOnCancel&&l()}else l()}},v=d.querySelectorAll("button"),b=0;b<v.length;b++)v[b].onclick=p,v[b].onmouseover=p,v[b].onmouseout=p,v[b].onmousedown=p,v[b].onfocus=p;A=t.onclick,t.onclick=function(t){var n=t||e.event,o=n.target||n.srcElement,r=d===o,a=k(d,o),i=g(d,"visible"),c="true"===d.getAttribute("data-allow-ouside-click");!r&&!a&&i&&c&&l()};var w=d.querySelector("button.confirm"),h=d.querySelector("button.cancel"),S=d.querySelectorAll("button:not([type=hidden])");M=e.onkeydown,e.onkeydown=a,w.onblur=u,h.onblur=u,e.onfocus=function(){e.setTimeout(function(){void 0!==z&&(z.focus(),z=void 0)},0)}},e.swal.setDefaults=function(e){if(!e)throw new Error("userParams is required");if("object"!=typeof e)throw new Error("userParams has to be a object");r(m,e)},function(){"complete"===t.readyState||"interactive"===t.readyState&&t.body?e.sweetAlertInitialize():t.addEventListener?t.addEventListener("DOMContentLoaded",function n(){t.removeEventListener("DOMContentLoaded",arguments.callee,!1),e.sweetAlertInitialize()},!1):t.attachEvent&&t.attachEvent("onreadystatechange",function(){"complete"===t.readyState&&(t.detachEvent("onreadystatechange",arguments.callee),e.sweetAlertInitialize())})}()}(window,document);