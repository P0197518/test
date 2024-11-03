let wkf = "该功能暂未开放，敬请期待~";
let base = "http://localhost:8083";
base = "";

/**
 * 封装的ajax get请求
 * @param url 请求url
 * @param params 请求参数
 * @param success 成功回调函数
 * @param error 失败回调函数
 * @param async 是否异步
 */
function ajaxGet(url, params, success, error, async = true) {
	$.ajax({
		type: "GET",
		url: base + url,
		data: params,
		cache: false,
		async: async,
		dataType: "json",
		processData: true,
		success: success,
		error: error
	});
}

/**
 * 封装的ajax post请求
 * @param url 请求url
 * @param params 请求参数
 * @param success 成功回调函数
 * @param error 失败回调函数
 * @param async 是否异步
 */
function ajaxPost(url, params, success, error, async = true) {
	$.ajax({
		type: "POST",
		url: base + url,
		data: params,
		async: async,
		cache: false,
		dataType: "json",
		processData: true,
		success: success,
		error: error
	});
}

/**
 * 提交FormData的Ajax POST请求
 * @param url 请求路径
 * @param data 请求参数
 * @param success 成功回调
 * @param error 失败回调
 */
function postForm(url, data, success, error) {
	$.ajax({
		url: base + url,
		data: data,
		cache: false,
		async: true,
		type: "POST",
		dataType: "json",
		processData: false,
		contentType: false,
		success: success,
		error: error
	});
}

/**
 * 错误回调函数
 * @param res
 */
let error = (res) => {
	let response = res.responseJSON;

	// 请求有响应
	if (res && response) {
		let status = res.status;

		if (status) {
			let message;

			if (status === 404) { // 404 not found
				if (response.path) {
					message = "路径" + response.path + "不存在。";
				} else {
					message = response.message;
				}
			} else {
				message = response.message;
			}

			alertMsg(message, "error");
			console.log("响应状态码：" + status + ", 响应消息：" + message);
		} else {
			console.log("请求没有响应状态码~");
		}
	} else {
		console.log("请求无响应~");
	}
}

/**
 * 右下角弹出消息提示
 * @param message 提示消息
 * @param type 消息类型：slide/show/fade
 */
function showMsg(message, type = "slide") {
	$.messager.show({
		title: "消息",
		msg: message,
		timeout: 3000,
		showType: type
	});
}

/*
 * 弹出提示
 * @param message 提示消息
 * @param type 提示类型：warning/error/info/question
 */
function alertMsg(message, type = "info") {
	$.messager.alert("提示", message, type);
}

/**
 * 重置文件上传组件的值
 * @param selector 组件的选择器
 */
function resetValue(selector) {
	$(selector).filebox("initValue", null);
}

/**
 * 功能未开放
 */
function unOpen() {
	alertMsg(wkf);
}

/**
 * 未选择记录
 * @param message
 * @param type
 */
function unselected(message = "请选择一条记录！", type = "warning") {
	alertMsg(message, type);
}

/**
 * 文件上传
 * @param _obj 文件上传选择框对象
 * @param id 数据的ID
 * @param url 文件上传的请求路径
 */
function fileUpload(_obj, id, url) {
	let file = $(_obj).context.ownerDocument.activeElement.files[0];
	let form = new FormData();

	form.append("id", id);
	form.append("file", file);

	postForm(url, form, function (result) {
		let image = result.data;

		$("#image").val(image);
		$("#img").attr("src", image);
	}, error);
}

/**
 * 验证表单
 * @param selector 选择器
 * @param func 表单验证通过后执行的操作
 */
function checkForm(selector, func) {
	let $form = $(selector);
	let bool = $form.form("validate");

	if (!bool) {
		alertMsg("请填写正确的表单项", "warning");
	} else if (selector === "#upload_form" && !$("#image").val()) {
		alertMsg("请上传图片~");
	} else {
		func();
	}
}

/**
 * 将数组转化为json格式的数据
 * @param array 数组
 */
function getJsonData(array) {
	let jsonData = [];

	for (let i = 0; i < array.length; i ++) {
		let elem = {
			value: i + "",
			text: array[i]
		};

		jsonData.push(elem);
	}

	return {
		code: 200,
		success: true,
		data: jsonData
	};
}

/*
 * 菜单显示控制
 */
let columnMenu;

/**
 * 创建表格的右键菜单
 * @param selector 表格dom对象的选择器
 */
function createColumnMenu(selector) {
	let datagrid = $(selector);
	let checked = "icon-ok";
	let unchecked = "";

	columnMenu = $("<div/>").appendTo("body");

	columnMenu.menu({
		onClick: function(item) {
			if (item.iconCls === checked) {
				datagrid.datagrid("hideColumn", item.name);

				columnMenu.menu("setIcon", {
					target: item.target,
					iconCls: unchecked
				});
			} else {
				datagrid.datagrid("showColumn", item.name);

				columnMenu.menu("setIcon", {
					target: item.target,
					iconCls: checked
				});
			}

			datagrid.datagrid("load");
		}
	});

	let fields = datagrid.datagrid("getColumnFields");

	for(let i = 0; i< fields.length; i++){
		let field = fields[i];
		let column = datagrid.datagrid("getColumnOption", field);

		columnMenu.menu("appendItem", {
			text: column.title,
			name: field,
			iconCls: column.hidden ? unchecked : checked
		});
	}
}