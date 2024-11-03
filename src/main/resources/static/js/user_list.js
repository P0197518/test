let requestUrl;
let height = 432;
let array = ["禁用", "启用"];

function addHandler() {
	requestUrl = "/user/insert";
	
	$("#user_dialog").dialog("open");
}

function editHandler() {
	let rowData = $("#user_list").datagrid("getSelected");
	
	if (rowData) {
		requestUrl = "/user/updateById";

		$("#id").val(rowData.id);
		$("#name").textbox("setValue", rowData.name);
		$("#phone").numberbox("setValue", rowData.phone);
		$("#gender").combobox("setValue", rowData.gender);
		$("#enable").combobox("setValue", rowData.enable);
		$("#username").textbox("setValue", rowData.username);

		$("#user_dialog").dialog("open");
	} else {
		alertMsg("请选择要修改的记录！", "warning");
	}
}

function deleteHandler() {
	let rowData = $("#user_list").datagrid("getSelected");
	
	if (rowData) {
		$.messager.confirm("提示", "是否确认删除？", function(b) {
			if(b) {
				ajaxPost("/user/deleteById", {
					id: rowData.id
				}, function(response) {
					showMsg(response.message);

					$("#user_list").datagrid("reload");
				}, error);
			}
		});
	} else {
		alertMsg("请选择要删除的记录！", "warning");
	}
}

$(document).ready(function() {
	// 姓名
	$("#name").textbox({
		width: 150,
        required: true
	});
	
	// 手机号
	$("#phone").numberbox({
		width: 150,
		required: true
	});

	// 性别
	$("#gender").combobox({
		url: "/gender/selectAll",
		valueField: "id",
		textField: "name",
		width: 150,
		required: true,
		panelHeight: "auto"
	});

	// 用户名
	$("#username").textbox({
		width: 150,
		required: true
	});

	// 是否启用
	$("#enable").combobox({
		width: 150,
		required: true,
		panelHeight: "auto",
		data: getJsonData(array)
	});
	
	// 菜单信息对话框
	$("#user_dialog").dialog({
		title: "菜单信息",
		width: 500,
		height: 300,
		closed: true,
		closable: true,
		draggable: false,
		buttons: [{
			iconCls: "icon-ok",
			text: "确认",
			handler: function() {
				let selector = "#user_form";

				checkForm(selector, function () {
					let data = $(selector).serialize();

					ajaxPost(requestUrl, data, function(response) {
						showMsg(response.message);

						$(selector).form("clear");
						$("#user_dialog").dialog("close");
						$("#user_list").datagrid("reload");
					}, error);
				});
			}
		}, {
			iconCls: "icon-cancel",
			text: "取消",
			handler: function() {
				$("#user_dialog").dialog("close");
				$("#user_form").form("clear");
			}
		}]
	});
	
	// 菜单数据表格
	$("#user_list").datagrid({
		url: "/user/selectByPage",
		striped: true,
		height: height,
		fitColumns: true,
		singleSelect: true,
		pagination: true,
		pageList: pageList,
		pageSize: pageList[0],
		toolbar: [{
			iconCls: "icon-add",
			text: "添加",
			handler: function() {
				addHandler();
			}
		}, "-", {
			iconCls: "icon-edit",
			text: "修改",
			handler: function() {
				editHandler()
			}
		}, "-", {
			iconCls: "icon-delete",
			text: '删除',
			handler: function() {
				deleteHandler();
			}
		}],
		columns: [[
			{field: "name", title: "姓名", align: "center", width: 50},
			{field: "gender", title: "性别", align: "center", width: 50
				, formatter: function(value, rowData, rowIndex) {
					ajaxGet("/gender/selectById", {
						id: value
					}, function(resp) {
						let data = resp.data;

						$("#gender_" + rowIndex).attr("src", data.image)
							.attr("title", data.name);
					}, error);

					return "<img alt='' height='" + size + "' id='gender_" + rowIndex + "' />";
				}
			},
			{field: "username", title: "用户名", align: "center", width: 50},
			{field: "phone", title: "手机号", align: "center", width: 100},
			{field: "enable", title: "是否启用", align: "center"
				, formatter: function(value) {
					return "<div>" + array[value] + "</div>";
				}
			}
		]]
	});

});