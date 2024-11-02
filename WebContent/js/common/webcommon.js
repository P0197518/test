function getPagerInfo(gridid, target) {

	if (mini.get(gridid).showPager == true) {
		mini.get(gridid).setPageIndex(0);

		if (mini.get(gridid).pageSize == undefined
				|| mini.get(gridid).pageSize == 0) {
			target.pageSize = 20;
		} else {
			target.pageSize = mini.get(gridid).pageSize;
		}
	}
	target.sortField = mini.get(gridid).sortField;

	if (mini.get(gridid).sortOrder == "") {
		target.sortOrder = "asc";
	} else {
		target.sortOrder = mini.get(gridid).sortOrder;
	}

	return target;
}

function doAjax(url, data, callback) {
	var ajaxParam = {};
	ajaxParam.url = concatUrl(bootPATH, url);

	if (data != null && data != undefined) {
		ajaxParam.data = mini.encode(data);
	}

	ajaxParam.type = "post";
	// TODO
	ajaxParam.timeout = 600000;
	ajaxParam.dataType = "json";
	ajaxParam.contentType = 'application/json;charset=utf-8';

	ajaxParam.success = function(text) {
		if (typeof (text) == "string" && text.indexOf("<html>") == 0) {
			businessMessage.alert("EC0090");
			return;
		}

		var jsonData;
		if (isJson(text)) {
			jsonData = text;
		} else {
			jsonData = JSON.parse(text);
		}
		if (jsonData.status == "CHECKERROR") {
			showError(jsonData);
		} else if (jsonData.status == "ERROR") {
			businessMessage.alert(jsonData.text, jsonData.args);
			return;
		} else if (jsonData.status == "EXCEPTION") {
			toNextView("/exceptionpage");
			return;
		}

		if (callback != undefined) {
			callback(mini.decode(text));
		}
	};

	ajaxParam.error = function(jqXHR, textStatus, errorThrown) {
		console.log(jqXHR.responseText);
		businessMessage.alert("EC0090");
	}

	ajaxParam.complete = function() {
		mini.unmask(document.body);
	}

	mini.mask({
		el : document.body,
		cls : 'mini-mask-loading',
		html : '処理中、しばらくお待ちください...'
	});

	$.ajax(ajaxParam);
}

// Json対象を判定する
function isJson(obj) {
	var isjson = typeof (obj) == "object"
			&& Object.prototype.toString.call(obj).toLowerCase() == "[object object]"
			&& !obj.length;
	return isjson;
}

// チェックエラーを画面に反映する
function showError(data) {
	if (data.checkMessages == null || data.checkMessages.length == 0) {
		return;
	}
	// 一件目のメッセージ内容を表示する
	var messageContent = "";
	// 全部のエラー項目のLabelの色を赤い色に変更する。
	for (var i = 0; i < data.checkMessages.length; i++) {

		messageContent = messageContent
				+ "<br />"
				+ businessMessage.get(data.checkMessages[i].code,
						data.checkMessages[i].args);
	}

	businessMessage.alert(messageContent);
}

function toNextPage(view, toParam) {
	var urlTarget = concatUrl(bootPATH, view);
	if (toParam != null && toParam != undefined) {
		urlTarget = urlTarget + "?toParam="
				+ encodeURIComponent(JSON.stringify(toParam));
	}
	if (window.parent != null && window.parent.window.frames['myFrame'] != null) {
		window.parent.window.location = urlTarget;
	}
	window.location = urlTarget;
}

function toInnerNextPage(view, toParam) {
	var sendData = {};
	sendData.view = view;
	sendData.sendParam = JSON.stringify(toParam);
	toNextPage("/innermain/index", sendData);
}

function concatUrl(path, view) {
	if (view.indexOf(path) > -1) {
		return view;
	}
	var returnPath = path;
	if (path.lastIndexOf("/") != path.length - 1) {
		returnPath = path + "/"
	}
	if (view.indexOf("/") == 0) {
		returnPath = returnPath + view.substring(1);
	} else {
		returnPath = returnPath + view;
	}
	return returnPath;
}

function openCommonPopupParam(url, title) {
	return {
		url : concatUrl(bootPATH, url),
		title : title,
		width : 1100,
		height : 600
	};
}

function formatCustomno(e) {
	return formatNo(e, 4);
}

function formatNo(e, maxLength) {
	var str = e.sender.value;
	if (str == "") {
		return false;
	}
	str = str.PadLeft(maxLength, "0");
	mini.get(e.sender.name).setValue(str);
	return false;
}

function autojancode(e) {
	if (e.sender.value != "" && e.sender.value.length >= 4) {
		var param = {};
		param.jancode = e.sender.value;
		doAjax("/common/getProducts", param, function(obj) {
			if (obj.status == "SUCCESS") {
				mini.get(e.sender.name).setData(obj.data);
			}
		});
	}
}

function disableAllControl() {
	var controls = mini.getChildControls(document.body);
	for (var i = 0; i < controls.length; i++) {
		controls[i].setEnabled(false);
	}
}

// 获取json，然后后台进行保存
function saveSearchCondation(backFunction) {
	if ($("#searchCondation").length == 0) {
		return false;
	}

	if ($("#resourcecd").length == 0) {
		return false;
	}

	var searchcondation = getJsonObject("searchCondation");

	doAjax("/common/saveSearchCondation", {
		gamenid : $("#resourcecd").val(),
		jsonnnaiyo : JSON.stringify(searchcondation)
	}, function() {
		if (backFunction != null && backFunction != undefined) {
			backFunction();
		}
	});
	return null;
}

function getSearchCondation(backFunction) {
	if ($("#searchCondation").length == 0) {
		return false;
	}

	if ($("#resourcecd").length == 0) {
		return false;
	}

	doAjax("/common/getSearchCondation", {
		gamenid : $("#resourcecd").val()
	},
			function(response) {
				if ($("#searchCondation").length == 0) {
					return false;
				}

				var form = new mini.Form("#searchCondation");

				var jsonData = mini.decode(response.data.jsonnnaiyo);
				if (jsonData != null) {
					form.setData(jsonData);
					if (mini.get("jancode") != null
							|| mini.get("jancode") != undefined) {
						mini.get("jancode").value = jsonData.jancode;
						mini.get("jancode").setText(mini.get("jancode").value);
					}
				}

				if (backFunction != null && backFunction != undefined) {
					backFunction(response);
				}
			});
}