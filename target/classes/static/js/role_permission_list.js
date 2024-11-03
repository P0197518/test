let requestUrl;
let height = 600;
let insertIds = [];
let deleteIds = [];
let list = [15, 50, 200];

function addHandler() {
	let roleId = $("#role_id").combobox("getValue");

	if(roleId) {
		$("#roleId").combobox("setValue", roleId);
	}

	requestUrl = "/role_permission/insert";

	$("#permission_dialog").dialog("open");
}

function editHandler() {
	let rowData = $("#permission_list").datagrid("getSelected");

	if (rowData) {
		requestUrl = "/role_permission/updateById";

		$("#id").val(rowData.id);
		$("#roleId").combobox("setValue", rowData.roleId);
		$("#permissionId").combobox("setValue", rowData.permissionId);

		$("#permission_dialog").dialog("open");
	} else {
		alertMsg("请选择要修改的记录！", "warning");
	}
}

function deleteHandler(id) {
	$.messager.confirm("提示", "是否确认删除？", function(bool) {
		if(bool) {
			ajaxPost("/role_permission/deleteById", {
				id: id
			}, function(response) {
				showMsg(response.message);

				$("#permission_list").datagrid("reload");
			}, error);
		}
	});
}

function initHandler() {
	ajaxPost("/role_permission/init", {}, function(response) {
		alertMsg(response.message);

		$("#permission_list").datagrid("reload");
	}, error);
}

$(document).ready(function() {
	// 角色
	$("#role_id").combobox({
		url: "/role/selectAll",
		valueField: "id",
		textField: "name",
		width: 150,
		panelHeight: "auto",
		prompt: "--选择角色--"
	});

	// 权限
	$("#permission_id").combobox({
		url: "/permission/selectAll",
		valueField: "id",
		textField: "name",
		width: 150,
		panelHeight: "auto",
		prompt: "--选择权限--"
	});

	// 搜索按钮
	$("#search").linkbutton({
		iconCls: "icon-search"
	}).click(function() {
		let roleId = $("#role_id").combobox("getValue")
		let permissionId = $("#permission_id").combobox("getValue")

		$("#permission_list").datagrid("load", {
			roleId: roleId,
			permissionId: permissionId
		});
	});

	// 清空按钮
	$("#clear").linkbutton({
		iconCls: "icon-delete"
	}).click(function() {
		$("#search_form").form("clear");
	});

	// 角色
	$("#roleId").combobox({
		url: "/role/selectAll",
		valueField: "id",
		textField: "name",
		width: 150,
		required: true,
		panelHeight: "auto"
	});

	// 权限
	$("#permissionId").combobox({
		url: "/permission/selectAll",
		valueField: "id",
		textField: "name",
		width: 150,
		required: true,
		panelHeight: "auto"
	});

	$("#permission_dialog").dialog({
		title: "角色权限",
		width: 500,
		height: 300,
		closed: true,
		closable: true,
		draggable: false,
		buttons: [{
			iconCls: "icon-save",
			text: "保存",
			handler: function() {
				let selector = "#permission_form";

				checkForm(selector, function () {
					let data = $(selector).serialize();

					ajaxPost(requestUrl, data, function(response) {
						showMsg(response.message);

						$(selector).form("clear");
						$("#permission_dialog").dialog("close");
						$("#permission_list").datagrid("reload");
					}, error);
				});
			}
		}, {
			iconCls: "icon-cancel",
			text: "取消",
			handler: function() {
				$("#permission_dialog").dialog("close");
				$("#permission_form").form("clear");
			}
		}]
	});

	$("#permission_list").datagrid({
		url: "/role_permission/selectByPage",
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
		}, "-", {
			iconCls: "icon-reload",
			text: "初始化",
			handler: function() {
				initHandler()
			}
		}],
		columns: [[
			{field: "roleId", title: "角色", align: "center", width: 100
				, formatter: function(value, rowData, rowIndex) {
					ajaxGet("/role/selectById", {
						id: value
					}, function (resp) {
						$("#role_" + rowIndex).html(resp.data.name);
					}, error);

					return "<div id='role_" + rowIndex + "'></div>";
				}
			},
			{field: "permissionId", title: "权限", align: "center", width: 100
				, formatter: function(value, rowData, rowIndex) {
					ajaxGet("/permission/selectById", {
						id: value
					}, function (resp) {
						$("#permission_" + rowIndex).html(resp.data.name);
					}, error);

					return "<div id='permission_" + rowIndex + "'></div>";
				}
			},
			{field: "right", title: "操作", align:"center"
				, formatter: function(value, rowData, rowIndex) {
					return "<a href='javascript:void(0);' onclick='deleteHandler(" + rowData.id + ")'>"
						+ "<img title='删除' src='/css/themes/icons/delete.png' /></a>";
				}
			}
		]]
	});

});