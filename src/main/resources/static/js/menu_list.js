let requestUrl;
let editingId;
let data = {};
let typeArray = ["父菜单", "子菜单"];
let pages = [15, 30, 50, 100, 200, 500];

/**
 * 添加
 */
function insert() {
	requestUrl = "/menu/insert";

	ajaxPost(requestUrl, {
		type: 1,
		name: "xxx",
		url: "/html/xxx_list.html",
		icon: "icon-script"
	}, function (response) {
		showMsg(response.message);

		$("#menu_list").datagrid("reload");
	}, error);
}

/**
 * 修改
 */
function edit() {
	let datagrid = $("#menu_list");

	if (editingId != null) {
		datagrid.datagrid("select", editingId);
	} else {
		let row = datagrid.datagrid("getSelected");

		if (row) {
			requestUrl = "/menu/updateById";
			editingId = datagrid.datagrid("getRowIndex", row.id);

			datagrid.datagrid("beginEdit", editingId);
		}
	}
}

/**
 * 保存
 */
function save() {
	if (editingId != null) {
		let datagrid = $("#menu_list");

		// 只有结束编辑才能获取到最新的值
		datagrid.datagrid("endEdit", editingId);

		ajaxPost(requestUrl, data, function (resp) {
			editingId = null;

			showMsg(resp.message);
			datagrid.datagrid("reload");
		}, error);
	}
}

/**
 * 取消
 */
function cancel() {
	if (editingId != null) {
		$("#menu_list").datagrid("cancelEdit", editingId);

		editingId = null;
	}
}

function deleteHandler() {
	let rowData = $("#menu_list").datagrid("getSelected");

	if (rowData) {
		$.messager.confirm("提示", "是否确认删除？", function(bool) {
			if(bool) {
				ajaxPost("/menu/deleteById", {
					id: rowData.id
				}, function(response) {
					showMsg(response.message);

					$("#menu_list").datagrid("reload");
				}, error);
			}
		});
	} else {
		alertMsg("请选择要删除的记录！", "warning");
	}
}

$(document).ready(function() {

	let datagrid = $("#menu_list").datagrid({
		url: "/menu/selectByPage",
		title: "菜单列表",
		method: "get",
		height: 666,
		rownumbers: true,
		pagination: true,
		pageList: pages,
		pageSize: pages[0],
		remoteFilter: true,
		clientPaging: false,
		onAfterEdit: function (index, row, changes) {
			data = {
				id: row.id,
				type: row.type,
				parentId: row.parentId,
				url: changes.url ? changes.url : row.url,
				name: changes.name ? changes.name : row.name,
				icon: changes.icon ? changes.icon : row.icon
			};
		},
		toolbar: [{
			iconCls: "icon-add",
			text: "添加",
			handler: function() {
				insert();
			}
		}, "-", {
			iconCls: "icon-edit",
			text: "修改",
			handler: function() {
				edit();
			}
		}, "-", {
			iconCls: "icon-save",
			text: "保存",
			handler: function() {
				save();
			}
		}, "-", {
			iconCls: "icon-cancel",
			text: "取消",
			handler: function() {
				cancel();
			}
		}, "-", {
			iconCls: "icon-delete",
			text: "删除",
			handler: function() {
				deleteHandler();
			}
		}],
		columns:[[
			{title: "菜单编号", field: "id", hidden: true},
			{title: "菜单名称", field: "name", align: "center", width: 100, editor: "textbox"},
			{title: "图标样式", field: "icon", align: "center", width: 100, editor: "textbox"},
			{title: "菜单类型", field: "type", width: 100, align: "center"
				, formatter: function(value) {
					return "<div>" + typeArray[value] + "</div>";
				}, editor: {
					type: "combobox",
					options: {
						panelHeight: "auto",
						data: getJsonData(typeArray)
					}
				}
			},
			{title: "父级菜单", field: "parentId", width: 100, align: "center"
				, formatter: function(value, rowData, rowIndex) {
					if (value) {
						ajaxGet("/menu/selectById", {
							id: value
						}, function (resp) {
							let data = resp.data;

							$("#parent_" + rowIndex).html(data.name);
						}, error);

						return "<a id='parent_" + rowIndex + "' class='ell'></a>";
					} else {
						return "<div>/</div>";
					}
				},
				editor: {
					type: "combobox",
					options: {
						url: "/menu/selectDirectory",
						valueField: "id",
						textField: "name",
						panelHeight: "auto"
					}
				}
			},
			{title: "页面地址", field: "url", align: "center", editor: "textbox", width: 200}
		]]
	});

	datagrid.datagrid("enableFilter", [{
		field: "name",
		type: "textbox",
		op: ["equal", "contains"]
	}, {
		field: "type",
		type: "combobox",
		op: ["equal"],
		options: {
			panelHeight: "auto",
			data: getJsonData(typeArray)
		}
	}, {
		field: "parentId",
		type: "combobox",
		op: ["equal"],
		options: {
			url: "/menu/selectDirectory",
			valueField: "id",
			textField: "name",
			panelHeight: "auto"
		}
	}]);

});