let requestUrl;
let height = 600;
let list = [15, 50, 200];

function addHandler() {
	let roleId = $("#role_id").combobox("getValue");

	if(roleId) {
		$("#roleId").combobox("setValue", roleId);
	}

	requestUrl = "/role_menu/insert";

	$("#menu_dialog").dialog("open");
}

function editHandler() {
	let rowData = $("#role_menu_list").datagrid("getSelected");

	if (rowData) {
		requestUrl = "/role_menu/updateById";

		$("#id").val(rowData.id);
		$("#roleId").combobox("setValue", rowData.roleId);
		$("#menuId").combobox("setValue", rowData.menuId);

		$("#menu_dialog").dialog("open");
	} else {
		alertMsg("请选择要修改的记录！", "info");
	}
}

function deleteHandler(id) {
	$.messager.confirm("提示", "是否确认删除？", function(bool) {
		if(bool) {
			ajaxPost("/role_menu/deleteById", {
				id: id
			}, function(response) {
				showMsg(response.message);

				$("#role_menu_list").datagrid("reload");
			}, error);
		}
	});
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

	// 菜单
	$("#menu_id").combobox({
		url: "/menu/selectAll",
		valueField: "id",
		textField: "name",
		width: 150,
		panelHeight: "auto",
		prompt: "--选择菜单--"
	});

	// 搜索按钮
	$("#search").linkbutton({
		iconCls: "icon-search"
	}).click(function() {
		$("#role_menu_list").datagrid('load', {
			roleId: $("#role_id").combobox("getValue"),
			menuId: $("#menu_id").combobox("getValue")
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

	// 菜单
	$("#menuId").combobox({
		url: "/menu/selectAll",
		valueField: "id",
		textField: "name",
		width: 150,
		required: true,
		panelHeight: "auto"
	});

	$("#menu_dialog").dialog({
		title: "角色菜单",
		width: 500,
		height: 300,
		closed: true,
		closable: true,
		draggable: false,
		buttons: [{
			iconCls: "icon-save",
			text: "保存",
			handler: function() {
				let selector = "#menu_form";

				checkForm(selector, function () {
					let data = $(selector).serialize();

					ajaxPost(requestUrl, data, function(response) {
						showMsg(response.message);

						$(selector).form("clear");
						$("#menu_dialog").dialog("close");
						$("#role_menu_list").datagrid("reload");
					}, error);
				});
			}
		}, {
			iconCls: "icon-cancel",
			text: "取消",
			handler: function() {
				$("#menu_dialog").dialog("close");
				$("#menu_form").form("clear");
			}
		}]
	});

	$("#role_menu_list").datagrid({
		url: "/role_menu/selectByPage",
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
			{field: "menuId", title: "菜单", align: "center", width: 100
				, formatter: function(value, rowData, rowIndex) {
					ajaxGet("/menu/selectById", {
						id: value
					}, function (res) {
						$("#menu_" + rowIndex).html(res.data.name);
					}, error);

					return "<div id='menu_" + rowIndex + "'></div>";
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