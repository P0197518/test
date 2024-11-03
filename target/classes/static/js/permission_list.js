let requestUrl;
let height = 600;
let list = [15, 50, 200];
let types = ["父级权限", "子级权限"];
let images = [{
	name: "不允许",
	icon: "/css/themes/icons/cancel.png"
}, {
	name: "允许",
	icon: "/css/themes/icons/ok.png"
}];

function addHandler() {
	let parentId = $("#parent_id").combobox("getValue");

	if(parentId) {
		$("#parentId").combobox("setValue", parentId);
	}

	requestUrl = "/permission/insert";

	$("#permission_dialog").dialog("open");
}

function editHandler() {
	let rowData = $("#permission_list").datagrid("getSelected");

	if (rowData) {
		requestUrl = "/permission/updateById";

		$("#id").textbox("setValue", rowData.id);
		$("#name").textbox("setValue", rowData.name);
		$("#type").combobox("setValue", rowData.type);
		$("#url").textbox("setValue", rowData.url);
		$("#parentId").combobox("setValue", rowData.parentId);

		$("#permission_dialog").dialog("open");
	} else {
		alertMsg("请选择要修改的记录！", "warning");
	}
}

function deleteHandler() {
	let rowData = $("#permission_list").datagrid("getSelected");

	if (rowData) {
		$.messager.confirm("提示", "是否确认删除？", function(bool) {
			if(bool) {
				ajaxPost("/permission/deleteById", {
					id: rowData.id
				}, function(response) {
					showMsg(response.message);

					$("#permission_list").datagrid("reload");
				}, error);
			}
		});
	} else {
		alertMsg("请选择要删除的记录！", "warning");
	}
}

$(document).ready(function() {

	// 类型
	$("#type_").combobox({
		width: 150,
		panelHeight: "auto",
		prompt: "--选择类型--",
		data: getJsonData(types)
	});

	$("#name_").textbox({
		width: 150,
	    prompt: "输入权限名"
	});

	// 父级权限
	$("#parent_id").combobox({
		url: "/permission/selectByType?type=0",
		valueField: "id",
		textField: "name",
		width: 150,
		panelHeight: "auto",
	    prompt: "--选择父级权限--"
	});

	// 搜索按钮
	$("#search").linkbutton({
		iconCls: "icon-search",
		text: "搜索"
	}).click(function() {
		let name = $("#name_").textbox("getValue");
		let type = $("#type_").combobox("getValue");
		let parentId = $("#parent_id").combobox("getValue");

		$("#permission_list").datagrid("load", {
			name: name,
			type: type,
			parentId: parentId
		});
	});

	// 清空按钮
	$("#clear").linkbutton({
		iconCls: "icon-delete",
		text: "清空"
	}).click(function() {
		$("#search_form").form("clear");
	});

	$("#id").textbox({
		width: 370,
		required: true
	});

	$("#type").combobox({
		width: 150,
		required: true,
		panelHeight: "auto",
		data: getJsonData(types)
	});

	$("#name").textbox({
		width: 150,
		required: true
	});

	$("#parentId").combobox({
		url: "/permission/selectByType?type=0",
		valueField: "id",
		textField: "name",
		width: 150,
		panelHeight: "auto"
	});

	$("#url").textbox({
		width: 370,
		required: true
	});

	// 菜单信息对话框
	$("#permission_dialog").dialog({
		title: "系统权限",
		closed: true,
		closable: true,
		draggable: false,
		buttons: [{
			iconCls: "icon-ok",
			text: "确认",
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
		url: "/permission/selectByPage",
		height: height,
		rownumbers: true,
		pagination: true,
		pageList: list,
		pageSize: list[0],
		onHeaderContextMenu: function(e){
			e.preventDefault();

			if (!columnMenu){
				createColumnMenu("#permission_list");
			}
			columnMenu.menu("show", {
				left: e.pageX,
				top: e.pageY
			});
		},
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
			text: "删除",
			handler: function() {
				deleteHandler();
			}
		}],
		columns: [[
			{field: "id", title: "编号", align: "center", width: 320, hidden: true
				, formatter: function(value) {
					return "<div class='ell' title='" + value + "'>" + value + "</div>";
				}
			},
			{field: "name", title: "权限名称", align: "center", width: 100},
			{field: "value", title: "权限值", align: "center", width: 100},
			{field: "type", title: "类型", align: "center", width: 50
				, formatter: function(value) {
					return "<div>" + types[value] + "</div>";
				}
			},
			{field: "method", title: "请求方式", align: "center", width: 50
				, formatter: function(value) {
					let methods = ["get", "post"];

					return "<div>" + methods[value] + "</div>";
				}
			},
			{field: "url", title: "请求路径", align: "center", width: 100
				, formatter: function(value) {
					return "<div class='ell' title='" + value + "'>" + value + "</div>";
				}
			},
			{field: "anonymity", title: "是否允许匿名访问", align: "center", width: 100
				, formatter: function(value) {
					if (value != null) {
						let image = images[value];

						return "<img alt='' src='" + image.icon + "' title='" + image.name + "' />";
					} else {
						return "<span>/</span>";
					}
				}
			},
			{field: "parentId", title: "父级权限", align: "center", width: 100
				, formatter: function(value, rowData, rowIndex) {
					if (value) {
						ajaxGet("/permission/selectById", {
							id: value
						}, function (res) {
							$("#parent_" + rowIndex).html(res.data.name);
						}, error);

						return "<div id='parent_" + rowIndex + "' class='ell'></div>";
					} else {
						return "<span>/</span>";
					}
				}
			}
		]]
	});

});