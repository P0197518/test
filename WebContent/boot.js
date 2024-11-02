__CreateJSPath = function(js) {
    var scripts = document.getElementsByTagName("script");
    var path = "";
    for (var i = 0, l = scripts.length; i < l; i++) {
        var src = scripts[i].src;
        if (src.indexOf(js) != -1) {
            var ss = src.split(js);
            path = ss[0];
            break;
        }
    }
    var href = location.href;
    href = href.split("#")[0];
    href = href.split("?")[0];
    var ss = href.split("/");
    ss.length = ss.length - 1;
    href = ss.join("/");
    if (path.indexOf("https:") == -1 && path.indexOf("http:") == -1 && path.indexOf("file:") == -1 && path.indexOf("\/") != 0) {
        path = href + "/" + path;
    }
    return path;
}

var bootPATH = __CreateJSPath("boot.js");

// debugger
mini_debugger = false;

document.write('<link href="' + bootPATH + 'css/assets/css/bootstrap.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'css/components/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css" />');

document.write('<link href="' + bootPATH + 'css/assets/css/ace-fonts.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'css/assets/css/ace.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'css/assets/css/ace-skins.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'css/assets/css/ace-rtl.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'css/common.css" rel="stylesheet" type="text/css" />');
// miniui
document.write('<script src="' + bootPATH + 'js/jquery-1.11.2.min.js" type="text/javascript"></script>');

document.write('<script src="' + bootPATH + 'js/aceui/aceui.js" type="text/javascript" ></script>');
document.write('<script src="' + bootPATH + 'js/aceui/locale/ja_JP.js" type="text/javascript" ></script>');
document.write('<script src="' + bootPATH + 'js/common/jsoncommon.js" type="text/javascript" ></script>');
document.write('<script src="' + bootPATH + 'js/common/webcommon.js" type="text/javascript" ></script>');
document.write('<script src="' + bootPATH + 'js/common/popwindow.js" type="text/javascript" ></script>');
document.write('<script src="' + bootPATH + 'js/common/common.js" type="text/javascript" ></script>');
document.write('<script src="' + bootPATH + 'js/common/CalculateUtil.js" type="text/javascript" ></script>');
document.write('<script src="' + bootPATH + 'js/business/commonCheck.js" type="text/javascript" ></script>');
document.write('<script src="' + bootPATH + 'js/business/message.js" type="text/javascript" ></script>');

document.write('<link href="' + bootPATH + 'css/aceui/themes/default/aceui.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'css/aceui/themes/icons.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'css/aceui/themes/metro/skin.css" rel="stylesheet" type="text/css" />');


// //////////////////////////////////////////////////////////////////////////////////////
function getCookie(sName) {
    var aCookie = document.cookie.split("; ");
    var lastMatch = null;
    for (var i = 0; i < aCookie.length; i++) {
        var aCrumb = aCookie[i].split("=");
        if (sName == aCrumb[0]) {
            lastMatch = aCrumb;
        }
    }
    if (lastMatch) {
        var v = lastMatch[1];
        if (v === undefined)
            return v;
        return unescape(v);
    }
    return null;
}