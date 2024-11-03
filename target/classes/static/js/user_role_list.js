let requestUrl;
let height = 432;
let list = [10, 50, 200];

function addHandler() {
	let userId = $("#user_id").combobox("getValue");

	if(userId) {
		$("#userId").combobox("setValue", userId);
	}
	
	$("#role_dialog").dialog("open");

	requestUrl = "/user_role/insert";
}

function editHandler() {
	let rowData = $("#user_role_list").datagrid("getSelected");

	if (rowData) {
		requestUrl = "/user_role/updateById";

		$("#id").val(rowData.id);
		$("#roleId").combobox("setValue", rowData.roleId);
		$("#userId").combobox("setValue", rowData.userId);

		$("#role_dialog").dialog("open");
	} else {
		alertMsg("请选择要修改的记录！", "warning");
	}
}

function deleteHandler(id) {
	$.messager.confirm("提示", "是否确认删除？", function(bool) {
		if(bool) {
			ajaxPost("/user_role/deleteById", {
				id: id
			}, function(response) {
				showMsg(response.message);

				$("#user_role_list").datagrid("reload");
			}, error);
		}
	});
}

$(document).ready(function() {
	// 用户
	$("#user_id").combobox({
		url: "/user/selectAll",
		width: 150,
		valueField: "id",
		textField: "name",
		panelHeight: "auto",
		prompt: "--选择权限--"
	});

	// 角色
	$("#role_id").combobox({
		url: "/role/selectAll",
		width: 150,
		valueField: "id",
		textField: "name",
		panelHeight: "auto",
		prompt: "--选择角色--"
	});

	// 搜索按钮
	$("#search").linkbutton({
		iconCls: "icon-search"
	}).click(function() {
		let roleId = $("#role_id").combobox("getValue");
		let userId = $("#user_id").combobox("getValue");
		
		$("#user_role_list").datagrid("load", {
			roleId: roleId,
			userId: userId
		});
	});

	// 清空按钮
	$("#clear").linkbutton({
		iconCls: "icon-delete"
	}).click(function() {
		$("#search_form").form("clear");
	});

	$("#userId").combobox({
		url: "/user/selectAll",
		valueField: "id",
		textField: "name",
		width: 150,
		required: true,
		panelHeight: "auto"
	});

	$("#roleId").combobox({
		url: "/role/selectAll",
		valueField: "id",
		textField: "name",
		width: 150,
		required: true,
		panelHeight: "auto"
	});

	$("#role_dialog").dialog({
		title: "用户角色信息",
		width: 500,
		height: 300,
		closed: true,
		closable: true,
		draggable: false,
		buttons: [{
			iconCls: "icon-save",
			text: "保存",
			handler: function() {
				let selector = "#role_form";

				checkForm(selector, function () {
					let data = $(selector).serialize();

					ajaxPost(requestUrl, data, function(response) {
						showMsg(response.message);

						$(selector).form("clear");
						$("#role_dialog").dialog("close");
						$("#user_role_list").datagrid("reload");
					}, error);
				});
			}
		}, {
			iconCls: "icon-cancel",
			text: "取消",
			handler: function() {
				$("#role_dialog").dialog("close");
				$("#role_form").form("clear");
			}
		}]
	});

	$("#user_role_list").datagrid({
		url: "/user_role/selectByPage",
		striped: true,
		height: height,
		fitColumns: true,
		rownumbers: true,
		singleSelect: true,
		pagination: true,
		pageList: list,
		pageSize: list[0],
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
		}],
		columns: [[
			{field: "userId", title: "用户", align: "center", width: 100
				, formatter: function(value, rowData, rowIndex) {
					ajaxGet("/user/selectById", {
						id: value
					}, function (res) {
						$("#user_" + rowIndex).html(res.data.username);
					}, error);

					return "<div id='user_" + rowIndex + "'></div>";
				}
			},
			{field: "roleId", title: "角色", align: "center", width: 100
				, formatter: function(value, rowData, rowIndex) {
					ajaxGet("/role/selectById", {
						id: value
					}, function (res) {
						$("#role_" + rowIndex).html(res.data.name);
					}, error);

					return "<div id='role_" + rowIndex + "'></div>";
				}
			},
			{field: "right", title: "操作", align:"center"
				, formatter: function(value, rowData, rowIndex) {
					return "<a href='javascript:void(0);' onclick='deleteHandler(" + rowData.id + ")'>"
						+ "<img alt='' title='删除' src='/css/themes/icons/delete.png' /></a>";
				}
			}
		]]
	});

});