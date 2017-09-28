function wxShare(videoId) {
    var vTitle, vDesc, imgUrl;
    var url = location.href.split('#')[0];//url不能写死
    $.ajax({
        url : __baseURL + "/wechat/wechatParam",
        data:{url:url, videoId:videoId},
        type : "get",
        async : false,
        dataType : "json",
        success : function(data) {
            console.log(data);
            vTitle = data.vTitle;
            vDesc = data.vDesc;
            imgUrl = data.imgUrl;
            wx.config({
                debug: false,//生产环境需要关闭debug模式
                appId: data.appid,//appId通过微信服务号后台查看
                timestamp: data.timestamp,//生成签名的时间戳
                nonceStr: data.nonceStr,//生成签名的随机字符串
                signature: data.signature,//签名
                jsApiList: [//需要调用的JS接口列表
                    'checkJsApi',//判断当前客户端版本是否支持指定JS接口
                    'onMenuShareAppMessage',//分享给好友
                    'onMenuShareTimeline'//分享到朋友圈
                ]
            });
        },
        error: function(xhr, status, error) {
            // alert(status);
            // alert(xhr.responseText);
        }
    });


    wx.ready(function () {
        var link = window.location.href;
        //分享朋友圈
        wx.onMenuShareTimeline({
            title: vTitle,
            link: link,
            imgUrl: imgUrl,// 自定义图标
            success: function (res) {
                // alert('shared success');
            },
            cancel: function (res) {
                //alert('shared cancle');
            }
        });
        //分享给好友
        wx.onMenuShareAppMessage({
            title: vTitle, // 分享标题
            desc: vDesc, // 分享描述
            link: link, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: imgUrl, // 自定义图标
            type: 'link', // 分享类型,music、video或link，不填默认为link
            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function () {
                // alert('shared success');
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
        wx.error(function (res) {
            // alert(res.errMsg);
        });
    });
}