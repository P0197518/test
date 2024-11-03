let requestUrl;
let height = 432;

function addHandler() {
	requestUrl = "/role/insert";
	
	$("#role_dialog").dialog("open");
}

function editHandler() {
	let rowData = $("#role_list").datagrid("getSelected");
	
	if (rowData) {
		requestUrl = "/role/updateById";

		$("#id").val(rowData.id);
		$("#name").textbox("setValue", rowData.name);
		$("#sort").numberspinner("setValue", rowData.sort);
		$("#description").textbox("setValue", rowData.description);

		$("#role_dialog").dialog("open");
	} else {
		alertMsg("请选择要修改的记录！", "warning");
	}
}

function deleteHandler(id) {
	$.messager.confirm("提示", "是否确认删除？", function(b) {
		if(b) {
			ajaxPost("/role/deleteById", {
				id: id
			}, function(response) {
				showMsg(response.message);

				$("#menu_list").datagrid("reload");
			}, error);
		}
	});
}

$(document).ready(function() {
	$("#name").textbox({
		width: 150,
        required: true
	});
	
	$("#sort").numberspinner({
		min: 0,
		width: 150,
		required: true
	});
	
	$("#description").textbox({
		width: 360,
		height: 100,
		required: true,
		multiline: true
	});
	
	$("#role_dialog").dialog({
		title: "角色信息",
		width: 500,
		height: 300,
		closed: true,
		closable: true,
		draggable: false,
		buttons: [{
			iconCls: "icon-ok",
			text: "确认",
			handler: function() {
				let selector = "#role_form";

				checkForm(selector, function () {
					let data = $(selector).serialize();

					ajaxPost(requestUrl, data, function(response) {
						showMsg(response.message);

						$(selector).form("clear");
						$("#role_dialog").dialog("close");
						$("#role_list").datagrid("reload");
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
	
	// 菜单数据表格
	$("#role_list").datagrid({
		url: "/role/selectByPage",
		striped: true,
		height: height,
		fitColumns: true,
		rownumbers: true,
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
		}],
		columns: [[
			// {field: "id", title: "编号", align: "center"},
			{field: "name", title: "角色名", align: "center", width: 50},
			{field: "description", title: "描述", align: "center", width: 200
				, formatter: function(value) {
					return "<div class='ell'>" + value + "</div>";
				}
			},
			{field: "sort", title: "排序", align: "center", width: 50},
			{field: "right", title: "操作", align:"center"
				, formatter: function(value, rowData, rowIndex) {
					return "<a href='javascript:void(0);' onclick='deleteHandler(" + rowData.id + ")'>"
						+ "<img alt='' title='删除' src='/css/themes/icons/delete.png' /></a>";
				}
			}
		]]
	});

});