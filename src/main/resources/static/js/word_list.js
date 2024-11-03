let requestUrl;
let height = 666;
let list = [15, 50, 200];

function addHandler() {
	requestUrl = "/word/insert";

	$("#type").combobox("setValue", 1);

	$("#word_dialog").dialog("open");
}

function editHandler() {
	let rowData = $("#word_list").datagrid("getSelected");

	if (rowData) {
		requestUrl = "/word/updateById";

		$("#id").val(rowData.id);
		$("#name").textbox("setValue", rowData.name);
		$("#note").textbox("setValue", rowData.note);
		$("#type").combobox("setValue", rowData.type);

		$("#word_dialog").dialog("open");
	} else {
		alertMsg("请选择要修改的记录！", "warning");
	}
}

function deleteHandler() {
	let rowData = $("#word_list").datagrid("getSelected");

	if (rowData) {
		$.messager.confirm("提示", "是否确认删除？", function(bool) {
			if(bool) {
				ajaxPost("/word/deleteById", {
					id: rowData.id
				}, function(response) {
					showMsg(response.message);

					$("#word_list").datagrid("reload");
				}, error);
			}
		});
	} else {
		alertMsg("请选择要删除的记录！", "warning");
	}
}

$(document).ready(function() {

	$("#name").textbox({
		width: 150,
		required: true
	});

	$("#type").combobox({
		url: "/word_type/selectAll",
		valueField: "id",
		textField: "name",
		width: 150,
		required: true,
		panelHeight: "auto"
	});

	$("#note").textbox({
		width: 366,
		height: 100,
		required: true,
		multiline: true
	});

	$("#word_dialog").dialog({
		title: " ",
		width: 500,
		height: 300,
		closed: true,
		closable: true,
		draggable: false,
		buttons: [{
			iconCls: "icon-ok",
			text: "确认",
			handler: function() {
				let selector = "#word_form";

				checkForm(selector, function () {
					let data = $(selector).serialize();

					ajaxPost(requestUrl, data, function(response) {
						showMsg(response.message);

						$(selector).form("clear");
						$("#word_dialog").dialog("close");
						$("#word_list").datagrid("reload");
					}, error);
				});
			}
		}, {
			iconCls: "icon-cancel",
			text: "取消",
			handler: function() {
				$("#word_dialog").dialog("close");
				$("#word_form").form("clear");
			}
		}]
	});

	let datagrid = $("#word_list").datagrid({
		url: "/word/selectByPage",
		title: "词语列表",
		height: height,
		rownumbers: true,
		pagination: true,
		pageList: list,
		pageSize: list[0],
		remoteFilter: true,
		clientPaging: false,
		onHeaderContextMenu: function(e) {
			e.preventDefault();

			if (!columnMenu){
				createColumnMenu("#word_list");
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
			{field: "id", title: "编号", align: "center", hidden: true},
			{field: "name", title: "词语", align: "center", width: 50},
			{field: "note", title: "含义", align: "center", width: 100
				, formatter: function(value) {
					return "<div class='ell' title='" + value + "'>" + value + "</div>";
				}
			}
		]]
	});

	datagrid.datagrid("enableFilter", [{
		field: "name",
		type: "textbox",
		op: ["equal", "contains"]
	}, {
		field: "note",
		type: "textbox",
		op: ["equal", "contains"]
	}]);

});