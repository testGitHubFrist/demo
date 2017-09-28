/**
 * Created by baikaishui on 2017/5/19.
 */


/*
var vm = new Vue({
    el: "#vuesss",
    data: {
        a:1,
        frameList:[],
        returnCode:0,
        videoFrameRate:0
    },
    mounted: function () {
        this.$nextTick(function () {
            this.loadingState = true;
            this.queryAddress();
        });
    },
    computed: {
        aDouble: function () {
            return this.videoFrameRate
        }
    },
    methods: {
        queryAddress: function () {
            var _self = this;
            this.$http.get("./js/abc.txt").then(function (response) {
                // var res = response.data;
                _self.data=eval("(" + response.data +")");
                _self.returnCode=_self.data.returnCode//返回代码（0 成功 ; 1 失败;2没有数据
                _self.msg=_self.data.msg//提示语（有就显示，没有就不显示）默认空字符串
                _self.videoFrameRate=_self.data.videoFrameRate//帧率
                _self.frameList=_self.data.frameList//frame集合
                //_self.frameList=_self.data.frameList//frame集合
                //_this.list = res;
                Vue.prototype.fn = "fn";

                // console.log(_self.data.frameList.length)

            })
        },
    }
});*/
