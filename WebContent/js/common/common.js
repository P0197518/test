function onCancel(e) {
	CloseWindow("cancel");
}

function CloseWindow(action) {
	if (window.CloseOwnerWindow)
		return window.CloseOwnerWindow(action);
	else
		window.close();
}

function openPop(url, title, size, widthParam, heightParam) {
	var w = window.screen.width;
	var h = Math.ceil(window.screen.height / 1.5);
	var width = 1100;
	var height = h;
	// Small 1 middle2 big 3
	if (size === 1) {
		width = widthParam <= 460 ? widthParam : 460;
		height = heightParam <= 460 ? heightParam : 460;
	} else if (size === 2) {
		width = widthParam <= 850 ? widthParam : 850;
		height = heightParam <= height ? heightParam : height;

	} else if (size === 3) {
		width = widthParam <= 1100 ? widthParam : 1100;
		height = heightParam <= height ? heightParam : height;
	}
	var popWindow = {
		url : url,
		title : title,
		width : width,
		height : height,
		onload : function() {
		},
		ondestroy : function(action) {
		}
	};
	mini.open(popWindow);
}

String.prototype.PadLeft = function(len, charStr) {
	var s = this + '';
	return new Array(len - s.length + 1).join(charStr, '') + s;
}

String.prototype.PadRight = function(len, charStr) {
	var s = this + '';
	return s + new Array(len - s.length + 1).join(charStr, '');
}

String.prototype.replaceAll = function(f, e) {
	var reg = new RegExp(f, "g");
	return this.replace(reg, e);
}

function isEmptyNumber(source) {
	if (source === undefined || source === "" || source === "0" || source === 0
			|| source === null) {
		return true;
	} else {
		return false;
	}
}

$(function() {
	if ($("#resourcecd").length == 0) {
		return;
	}
	var data = {};
	data.resourcecd = $("#resourcecd").val();
	doAjax("user/getUserPageButtons", data, function(obj) {
		if (obj.status == "SUCCESS") {
			for (var i = 0; i < obj.data.length; i++) {
				$("#" + obj.data[i].elementid).each(function() {
					$(this).removeClass("autoAuth");
				});
			}
		}
	});
});

function concatDownloadUrl(url, data, sortField, sortOrder) {
	var urlData = window.encodeURI(mini.encode(data));
	var condationStr = "";
	if (sortField != undefined) {
		condationStr = condationStr + "&sortField=" + sortField;
	}
	if (sortOrder != undefined) {
		condationStr = condationStr + "&sortOrder=" + sortOrder;
	}
	return (concatUrl(bootPATH, url) + "?submitData=" + urlData + condationStr)
}

function downloadExcel(url, data, sortField, sortOrder) {
	var targetUrl = concatDownloadUrl(url, data, sortField, sortOrder);
	window.open(targetUrl, (new Date()).valueOf());
}

function downloadPdf(url, data, sortField, sortOrder) {
	var targetUrl = concatDownloadUrl(url, data, sortField, sortOrder);
	window.open(targetUrl);
}

function downloadPdf2(url, data, sortField, sortOrder) {
	var targetUrl = concatDownloadUrl(url, data, sortField, sortOrder);

	var tmpA = document.createElement("a");
	tmpA.setAttribute("href", targetUrl);
	tmpA.setAttribute("target", "_blank");
	document.body.appendChild(tmpA);
	tmpA.click();
}