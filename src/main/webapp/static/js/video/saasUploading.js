/**
 * Created by baikaishui on 2017/6/15.\
 */
var oSaasListUserName=nilo.getById('saasListUserName');//用户名;
oSaasListUserName.innerHTML=nilo.getCookie('nickName');
//alert(nilo.getCookie('nickName'));

var oSaasListQuit=nilo.getById("saasListQuit")
oSaasListQuit.onclick=function () {
    nilo.removeCookie('ticket');
    nilo.removeCookie('nickName');
    window.location=__baseURL;
}

var oVideoUploadingSubmit=nilo.getById("videoUploadingSubmit");
var oVideoUploadingForm=nilo.getById("videoUploadingForm");
var oVideoUploadingFile=nilo.getById("videoUploadingFile");
var oVideoUploadingURLForm = nilo.getById("videoUploadingURLForm");
var oVideoUploadingURL = nilo.getById("videoUploadingURL");
oVideoUploadingSubmit.onclick=function () {
    var actionUrl = __baseURL + "/video/upload?ticket="+nilo.getCookie("ticket");
    var filename=oVideoUploadingFile.value;
    var index1=filename.lastIndexOf(".");
    var index2=filename.length;
    var postf=filename.substring(index1,index2);
    if(oVideoUploadingURL.value.length > 0 || postf==".mp4" || postf==".avi"){
        oVideoUploadingForm.action = actionUrl;
        oVideoUploadingForm.submit();
        // submit后回调(应根据实际返回情况处理)
        // setTimeout(function(){
        // 	submitCallback();
        // },3000);

        nilo.getById('showLoading').style.display = 'block';
        showLoading("showLoading");
    } else {
        swal("请上传视频格式文件，或输入视频网址");
    }

};

// function submitCallback(){
// 	nilo.getById('showLoading').style.display = 'none';
// }

function changeUpload(obj){
    var oLocalUploadDiv = nilo.getById("localUploadDiv");
    var oWebUploadDiv = nilo.getById("webUploadDiv");
    var oVideoFileName = nilo.getById("videoFileName");
    if(obj.value == 1) {
        oLocalUploadDiv.style.display = 'none';
        oWebUploadDiv.style.display = '';
        oVideoFileName.value = "";
        if (oVideoUploadingFile.outerHTML) { // for IE, Opera, Safari, Chrome
            oVideoUploadingFile.outerHTML = oVideoUploadingFile.outerHTML;
        } else { // FF(包括3.5)
            oVideoUploadingFile.value = "";
        }
    } else {
        oLocalUploadDiv.style.display = '';
        oWebUploadDiv.style.display = 'none';
        oVideoUploadingURL.value = '';
    }
}


function handleFile() {
    var fileName = nilo.getById("videoUploadingFile");
    var showFileName = nilo.getById("videoFileName");
    showFileName.value = fileName.value;
}

/**
 * 跳转视频列表
 */
var oVideoList=nilo.getById('videoList');
oVideoList.onclick=function () { 
	var ticket=nilo.getCookie('ticket');
	window.location=__baseURL+'/video/videolist?page=1&ticket='+ticket;
}
