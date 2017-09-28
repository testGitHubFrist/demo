/**
 * Created by baikaishui on 2015/7/6.
 */
var nilo={}||"";
var __baseURL = "http://localhost:8080/";
var __baseVideoWidth =1000;
var __baseVideoHeight = 520;

var nilo= {
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
    setCookie:function (name, value, iDay)
    {
        var oDate=new Date();

        oDate.setDate(oDate.getDate()+iDay);

        document.cookie=name+'='+value+';expires='+oDate;
    },
    //获取cookie
    getCookie:function (name)
    {
        var arr=document.cookie.split('; ');
        var i=0;

        for(i=0;i<arr.length;i++)
        {
            var arr2=arr[i].split('=');

            if(arr2[0]==name)
            {
                return arr2[1];
            }
        }

        return '';
    },
    //删除cookie
    removeCookie:function (name)
    {
        this.setCookie(name, '1', -1);
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

