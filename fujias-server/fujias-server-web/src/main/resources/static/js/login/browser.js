function isBrowserErr(){
	var userAgent = navigator.userAgent.toLowerCase();
	//alert(userAgent);
	if (userAgent.match(/(iPhone|iPod|Android|ios|iPad)/i)){//手机默认正确。
		return false;
	}
	//opera>11
	if(userAgent.indexOf("opr")>=1 ){
		//var versionName = userAgent.match(/opr\/([\d.]+)/)[0];
		var version = userAgent.match(/opr\/([\d.]+)/)[1];
		var t=version.split(".")
		if(t&&t.length>2){
			version=t[0]+"."+t[1];
		}
		if(parseFloat(version)>=10.6){
			return false;
		};

	}else if(userAgent.indexOf("opera")>=1){
		var version = userAgent.match(/opera\/([\d.]+)/)[1];
		var t=version.split(".")
		if(t&&t.length>2){
			version=t[0]+"."+t[1];
		}
		if(parseFloat(version)>=10.6){//11.1//10.6
			return false;//alert("版本太低："+version);	
		};
	}else if(userAgent.indexOf("chrome")>=1){
		var version = userAgent.match(/chrome\/([\d.]+)/)[1];
		var t=version.split(".")
		if(t&&t.length>2){
			version=t[0]+"."+t[1];
		}
		if(parseFloat(version)>=10){
			return false;
		};
	}else if(userAgent.indexOf("firefox")>=1){
		//var versionName = userAgent.match(/firefox\/([\d.]+)/)[0];
		var version = userAgent.match(/firefox\/([\d.]+)/)[1];
		var t=version.split(".")
		if(t&&t.length>2){
			version=t[0]+"."+t[1];
		}
		if(parseFloat(version)>=3.6){//火狐3.6
			return false;
		};
	}else if(userAgent.indexOf("safari")>=1){
		//var versionName = userAgent.match(/version\/([\d.]+)/)[0];
		var version = userAgent.match(/version\/([\d.]+)/)[1];
		var t=version.split(".")
		if(t&&t.length>2){
			version=t[0]+"."+t[1];
		}
		if(parseFloat(version)>=3.1){//4版本的safari支持html5(3.1)
			return false;
		};
	}else if(userAgent.indexOf("msie")>=1){
		var version = userAgent.match(/msie ([\d.]+)/)[1];
		var t=version.split(".")
		if(t&&t.length>2){
			version=t[0]+"."+t[1];
		}
		if(parseFloat(version)>=9){
			//alert(version);
			return false ;
		};
	}
	//判断浏览内核是否合适
	//alert(isTrident(userAgent));
	if(isTrident(userAgent)){//ie11以上处理以及引用ie内核的
		var version = userAgent.match(/trident\/([\d.]+)/)[1];
		var t=version.split(".")
		if(t&&t.length>2){
			version=t[0]+"."+t[1];
		}
		if(parseFloat(version)>=5){
			return false ;
		};
	}
	return true;
}

function getBrowserInfo(){
	var userAgent = navigator.userAgent.toLowerCase();
	if (userAgent.match(/(iPhone|iPod|Android|ios|iPad)/i)){//手机默认正确。
		return "手机设备";
	}
	//opera>11
	if(userAgent.indexOf("opr")>=1 ){
		return "opera浏览器,版本"+userAgent.match(/opr\/([\d.]+)/)[0];//+",请升级至10.6版本以上(包括10.6)";
	}else if(userAgent.indexOf("opera")>=1){
		return "opera浏览器,版本"+userAgent.match(/opr\/([\d.]+)/)[0];//+",请升级至10.6版本以上(包括10.6)";
	}else if(userAgent.indexOf("chrome")>=1){
		return "谷歌chrom浏览器或内核,版本"+userAgent.match(/chrome\/([\d.]+)/)[0];//+",请升级至10版本以上(包括10)";
	}else if(userAgent.indexOf("firefox")>=1){
		return "火狐firfox浏览器,版本"+userAgent.match(/firefox\/([\d.]+)/)[0];//+",请升级至3.6版本以上(包括3.6)";
	}else if(userAgent.indexOf("safari")>=1){
		return "苹果safari浏览器或内核,版本"+userAgent.match(/version\/([\d.]+)/)[0];//+",请升级至3.1版本以上(包括3.1)";
	}else if(userAgent.indexOf("msie")>=1){
		return "微软ie浏览器,版本"+userAgent.match(/msie ([\d.]+)/)[0];//+",请升级至9版本以上(包括9)，最好升级10以上";
	}
	if(isTrident(userAgent)){//ie11以上处理以及引用ie内核的
		return "微软ie浏览器或内核,版本11以上";
	}
	return "版本不详";
}

//Trident内核的常见浏览器有：IE6、IE7、IE8（Trident 4.0）、IE9（Trident 5.0）、IE10（Trident 6.0）； IE11( Trident7.0)  Spartan 很可能不是 IE12， Trident内核 
//WebKit（跨平台）常见的WebKit内核的浏览器：Apple Safari (Win/Mac/iPhone/iPad)、Symbian手机浏览器、Android 默认浏览器
//KHTML（Linux）。常见的KHTML内核的浏览器：Konqueror。
//Gecko（跨平台）常见的Gecko内核的浏览器：Mozilla Firefox、Mozilla SeaMonkey、Epiphany（早期版本）、Flock（早期版本）、K-Meleon。
//Presto（跨平台）常见的Presto内核的浏览器：Opera。是Opera 7.0及以后版本的内核(11.1 对应内核2.8.99)
//Chromium（跨平台）常见的Chromium内核的浏览器：Google Chrome、Chromium、SRWare Iron、Comodo Dragon。
//Trident/Gecko/WebKit/Chromium

//判断是否为chrome内核浏览器
function isChromium(userAgent) {
    var chromium = "mozilla/&&applewebkit/&&chrome/&&safari/".split("&&");
    for (var i = 0; i < chromium.length; i++)
        if (userAgent.indexOf(chromium[i]) < 0)
            return false;
    return true;
}

// 判断是否为webkit内核浏览器
function isWebkit(userAgent) {
    if (userAgent.indexOf("applewebkit/") < 0)
        return false;
    return true;
}

//判断是否为Trident内核浏览器
function isTrident(userAgent) {
    if (userAgent.indexOf("trident") < 0)
        return false;
    return true;
}

//百度浏览器使用：Chromium 
//360安全浏览器 内核chrome，ie
//QQ浏览器 ie
//猎豹安全浏览器 chrome	
//搜狗浏览器chrome
//遨游云浏览器：chrome
//UC浏览器 9.8.5.442 （最新版）safari 4

//百度浏览器、360安全浏览器、猎豹安全浏览器 、搜狗浏览器、遨游云浏览器、UC浏览器请升级至最新版本。腾讯QQ浏览器升级保持本地IE浏览器至ie9以上。