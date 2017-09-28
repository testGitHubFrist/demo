var	sw = screen.width, vw = 0, vh = 0, pageSize = 4, pageNo = 1, totalPage = 1;

if(sw > 640){
	vw = 640;
	vh = 320;
}else{
	vw = sw;
	vh = parseInt(320 * sw / 640);
}

var locationSearch = location.search;
//var locationSearch = '?videoId=7ujn3scved';
var locationSearchSub = locationSearch.substring(1,locationSearch.length);
var overlayURL = __baseURL+"/video/videoPlayer?"+getQueryStringByName("v");
var vardivIframe=document.getElementById("videoPlayer");
vardivIframe.src=overlayURL;
vardivIframe.width = vw;
vardivIframe.height = vh;



function getQueryStringByName(name) {

    var result = location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));

    if (result == null || result.length < 1) {

        return "";

    }

    return result[1];
}