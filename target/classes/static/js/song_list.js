let data = {};
let editingId;
let requestUrl;
let form = new FormData();
let pageList = [15, 30, 100, 500, 1000];

function addHandler() {
	requestUrl = "/song/insert";

	ajaxPost(requestUrl, {
		name: "*****",
		singer: "*****",
		note: ""
	}, function (resp) {
		$.messager.show({
			title: "系统消息",
			timeout: 5000,
			showType: "slide",
			msg: resp.message,
		});

		$("#song_list").datagrid("reload");
	}, error);
}

function editHandler() {
	let datagrid = $("#song_list");

	if (editingId != null && editingId !== "") {
		datagrid.datagrid("selectRow", editingId);
	} else {
		let row = datagrid.datagrid("getSelected");
		
		if (row) {
			// 获取行索引，这个索引从0开始
			let rowIndex = datagrid.datagrid("getRowIndex", row);
			
			editingId = rowIndex;
			requestUrl = "/song/updateById";

			datagrid.datagrid("beginEdit", rowIndex);
		}
	}
}

function saveHandler() {
	if (editingId != null && editingId !== "") {
		// 只有结束编辑才能获取到最新的值
		$("#song_list").datagrid("endEdit", editingId);
	
		$.post(requestUrl, data, function (res) {
			if(res.code === 200) {
				$.messager.show({
					title: "系统消息",
					timeout: 5000,
					showType: "slide",
					msg: res.message,
				});
				
				editingId = "";
			} else {
				$.messager.alert("系统提示", res.message, "error");
			}
		}, "json");
	}
}

function cancelHandler() {
	// editingId != null条件防止刷新页面带来的问题
	if (editingId != null && editingId !== "") {
		$("#song_list").datagrid("cancelEdit", editingId);
	
		editingId = "";
	}
}

function importHandler() {
	requestUrl = "/song/import";

	$("#file-name").empty();
	$("#file-size").empty();

	$("#import_dialog").dialog("open");
}

function exportHandler() {
	location.href = "/song/export";
}

function deleteHandler() {
	let rowData = $("#song_list").datagrid("getSelected");
	
	if (rowData) {
		$.messager.confirm("系统确认", "删除后数据无法恢复，是否确认删除？", function(bool) {
			if (bool) {
				ajaxPost("/song/deleteById/" + rowData.id, {}, function(res) {
					$.messager.show({
						title: "系统消息",
						timeout: 5000,
						showType: "slide",
						msg: res.message,
					});

					$("#song_list").datagrid("reload");
				}, error);
			}
		});
	} else {
		$.messager.alert("系统提示", "请选择要删除的数据！", "warning");
	}
}

$(document).ready(function() {

	$("#select_file").filebox({
		buttonText: "选择文件",
		width: 200,
		required: true,
		onChange: function() {
			let file = $(this).context.ownerDocument.activeElement.files[0];

			form.append("file", file);

			$("#file-name").html(file.name);
			$("#file-size").html((file.size / 1024).toFixed(1) + "KB");
		}
	})

	$("#import_dialog").dialog({
		title: "数据导入",
		modal: true,
		closed: true,
		closable: true,
		draggable: false,
		buttons: [{
			iconCls: "icon-ok",
			text: "导入",
			handler: function() {
				let bool = $("#import_form").form("validate");

				if (bool) {
					$.ajax({
						url: requestUrl,
						data: form,
						cache: false,
						async: true,
						type: "POST",
						dataType: "json",
						processData: false,
						contentType: false,
						success: function (response) {
							$.messager.show({
								title: "系统消息",
								timeout: 5000,
								showType: "slide",
								msg: response.message,
							});

							$("#import_dialog").dialog("close");
							$("#song_list").datagrid("reload");
						},
						error: function (resp) {
							// 请求有响应
							if (resp && resp.responseJSON) {
								let response = resp.responseJSON;
								let status = resp.status;

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

									$.messager.alert("系统提示", message, "error");
									console.log("响应状态码：" + status + ", 响应消息：" + message);
								} else {
									console.log("请求没有响应状态码~");
								}
							} else {
								console.log("请求无响应~");
							}
						}
					});
				} else {
					$.messager.alert("系统提示", "请选择文件", "warning");
				}
			}
		}, {
			iconCls: "icon-cancel",
			text: "取消",
			handler: function() {
				$("#select_file").filebox("initValue", null);
				$("#import_dialog").dialog("close");
				form.delete("file");
			}
		}]
	});

	let datagrid = $("#song_list").datagrid({
		url: "/song/selectByPage",
		title: "歌曲列表",
		height: 666,
		rownumbers: true,
		remoteFilter: true,
		clientPaging: false,
		pagination: true,
		pageList: pageList,
		pageSize: pageList[0],
		loadFilter: function(resp) {
			if (resp.code === 200) {
				return resp.data;
			} else {
				return null;
			}
		},
		onAfterEdit: function (rowIndex, rowData, changes) { // 结束行内编辑事件
			console.log(changes);

			data = {
				id: rowData.id,
				name: changes.name != null ? changes.name : rowData.name,
				note: changes.note != null ? changes.note : rowData.note,
				singer: changes.singer != null ? changes.singer : rowData.singer
			};
		},
		onHeaderContextMenu: function(e) {
			e.preventDefault();

			if (!columnMenu){
				createColumnMenu("#song_list");
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
				editHandler();
			},
		}, "-", {
			iconCls: "icon-save",
			text: "保存",
			handler: function() {
				saveHandler();
			}
		}, "-", {
			iconCls: "icon-cancel",
			text: "取消",
			handler: function() {
				cancelHandler();
			}
		}, "-", {
			iconCls: "icon-upload",
			text: "导入",
			handler: function() {
				importHandler();
			}
		}, "-", {
			iconCls: "icon-download",
			text: "导出",
			handler: function() {
				exportHandler();
			}
		}, "-", {
			iconCls: "icon-delete",
			text: "删除",
			handler: function() {
				deleteHandler();
			}
		}],
		columns: [[
			{field: "id", title: "编号", hidden: true},
			{field: "name", title: "歌曲名", width: 200, align: "center", editor: "textbox"},
			{field: "singer", title: "歌手", width: 200, align: "center", editor: "textbox"},
			{field: "note", title: "歌曲信息", width: 200, align: "center", editor: "textbox"},
			{field: "lastUpdateTime", title: "最后一次修改时间", width: 100, align: "center", sortable: true}
		]]
	});
	
	datagrid.datagrid("enableFilter", [{
		field: "name",
		type: "textbox",
		op: ["equal", "contains"]
	}, {
		field: "singer",
		type: "textbox",
		op: ["equal", "contains"],
	}, {
		field: "note",
		type: "textbox",
		op: ["equal", "contains"]
	}]);

});