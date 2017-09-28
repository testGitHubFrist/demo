;(function() {
    if (!-[1, ]) {
        return 0;
    };
    var html = document.getElementsByTagName('html')[0];
    var script = document.getElementsByTagName('script')[0];
    var psd_w = script.getAttribute('fu-psd');
    var _min = script.getAttribute('fu-min');
    var _max = script.getAttribute('fu-max');
    var full = script.getAttribute('fu-full');
    var win = window;
    var doc = document;
    var dpr = window.devicePixelRatio || 1;
    var screen = win.screen;
    var ratio = Math.min(screen.width, screen.height) / Math.max(screen.width, screen.height);
    win.FreeUi = win.FreeUi || {};
    FreeUi['Rem'] = _rem;

    function _rem(psd_w, _min, _max, full) {
        var win = window;
        var psd_w = Number(psd_w) || 640;
        var win_w = html.getBoundingClientRect().width;
        if (!full) {
            var angle = window.screen.orientation ? window.screen.orientation.angle : 0;
            var orientation = win.orientation || angle || 0;
            if (orientation == 90 || orientation == -90) {
                win_w = win_w * ratio;
            };

        };
        var size = 100 / (psd_w / win_w);
        var _min = Number(_min) || 50;
        var _max = Number(_max) || 100;
        size = size >= _max ? _max : size;
        size = size <= _min ? _min : size;
        if(html.style.fontSize != size+'px'){
            html.style.fontSize = size + 'px';
        };
        return size;
    };
    var size = _rem(psd_w, _min, _max, full);
    var _t = setTimeout(function() {
        _rem(psd_w, _min, _max, full);
    }, 300);

    var ua= navigator.userAgent;
    var is_orientation = Boolean('orientation' in win) && Boolean(ua.match(/iPhone|iPod|Android|ios|iPad|Windows Phone/));
    var event = is_orientation ? 'orientationchange' : 'resize';
    var time = is_orientation ? 300 : 100;
    if (!is_orientation || full) {
        win.addEventListener(event, function() {
            clearTimeout(_t);
            var _t = setTimeout(function() {
                _rem(psd_w, _min, _max, full);
            }, time)
        }, false);
    };
    win.addEventListener('pageshow', function() {
        _rem(psd_w, _min, _max, full);

    }, false);
    if ("complete" === doc.readyState) {
        _rem(psd_w, _min, _max, full);
    };
    doc.addEventListener("DOMContentLoaded", function() {
        _rem(psd_w, _min, _max, full);
    }, false);
})();