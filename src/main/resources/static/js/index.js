let tabs;
let loginPage = "/login.html";

let insertIds = [];
let deleteIds = [];
let hideIds = [];
let displayIds = [];

/**
 * 往localStorage中添加指定的tab
 * @param tab
 */
function addToTabs(tab) {
	if (!tabs) {
		tabs = [];
	}

	// 数组不存在才添加
	let exists = tabs.some(function (item) {
		return tab.title === item.title;
	});
	if (!exists) {
		tabs.push(tab);
	}

	setCurrent(tab.title);
	storage("tabs", JSON.stringify(tabs));
}

/**
 * 从localStorage中删除指定标题的tab
 * @param title
 */
function deleteFromTabs(title) {
	tabs = tabs.filter(function (item) {
		return item.title !== title;
	});

	storage("tabs", JSON.stringify(tabs));
}

/**
 * 获取所有打开的tab
 */
function getTabs() {
	let tabs = getStorage("tabs");

	return JSON.parse(tabs);
}

/**
 * 获取当前选中的tab
 */
function getCurrent() {
	return getStorage("currTab");
}

/**
 * 设置当前选中的tab
 */
function setCurrent(title) {
	storage("currTab", title);
}

/**
 * 初始化左侧菜单
 */
function InitLeftMenu() {
	$("#nav").accordion({
		animate: true
	});

	ajaxGet("/menu/listMenus", {}, function(response) {
		$.each(response.data, function(i, node) {
			let menus = "<ul>";

			$.each(node.menus, function(j, child) {
				menus += "<li>";
				menus += 	"<div>";
				menus += 		"<a ref='" + child.id + "' href='javascript:void(0)' rel='" + child.url + "' >";
				menus += 			"<span class='icon " + child.icon + "' >&nbsp;</span>";
				menus += 			"<span class='nav'>" + child.name + "</span>";
				menus += 		"</a>";
				menus += 	"</div>";
				menus += "</li>";
			});

			menus += "</ul>";

			$("#nav").accordion("add", {
				title: node.name,
				content: menus,
				iconCls: "icon " + node.icon
			});

			//选中第一个
			let panels = $("#nav").accordion("panels");
			let tab = panels[0].panel("options").title;

			$("#nav").accordion("select", tab);
		});

		$(".easyui-accordion li a").on("click", function() {
			let url = $(this).attr("rel");
			let tabTitle = $(this).children(".nav").text();
			let icon = $(this).children(".icon").attr("class");

			addTab(tabTitle, url, icon);

			$(".easyui-accordion li div").removeClass("selected");
			$(this).parent().addClass("selected");
		}).hover(function() {
			$(this).parent().addClass("hover");
		}, function() {
			$(this).parent().removeClass("hover");
		});
	}, error);
}

function addTab(title, url, icon = "") {
	let exists = $("#tabs").tabs("exists", title);

	if (!exists) {
		$("#tabs").tabs("add", {
			title: title,
			icon: icon,
			closable: true,
			content: createFrame(url)
		});

		addToTabs({
			title: title,
			url: url
		});
	} else {
		$("#tabs").tabs("select", title);
		$("#mm-tabupdate").click();
	}

	tabClose();
}

function createFrame(url) {
	return "<iframe style='width:100%;height:100%;' scrolling='auto' frameborder='0'  src='" + url + "'></iframe>";
}

function tabClose() {
	$("#tabs ul li .tabs-inner").dblclick(function() { // 双击关闭TAB选项卡
		let currTab = $(this).children(".tabs-closable").text();

		$("#tabs").tabs("close", currTab);

		deleteFromTabs(currTab);
	}).bind("contextmenu", function(e) { // 为选项卡绑定右键
		e.preventDefault();

		$("#mm").menu("show", {
			left: e.pageX,
			top: e.pageY
		});

		let title = $(this).children(".tabs-closable").text();

		setCurrent(title);

		$("#tabs").tabs("select", title);
	});
}

/**
 * 绑定右键菜单事件
 */
function tabCloseEven() {
	// 刷新
	$("#mm-tabupdate").click(function() {
		let _tabs = $("#tabs");
		let currTab = _tabs.tabs("getSelected");
		let url = $(currTab.panel("options").content).attr("src");

		_tabs.tabs("update", {
			tab: currTab,
			options: {
				content: createFrame(url)
			}
		})
	});

	// 关闭当前
	$("#mm-tabclose").click(function() {
		let title = getCurrent();

		deleteFromTabs(title);
		$("#tabs").tabs("close", title);
	});

	// 全部关闭
	$("#mm-tabcloseall").click(function() {
		$(".tabs-inner > span.tabs-closable").each(function(i, n) {
			let title = $(n).text();

			deleteFromTabs(title);
			$("#tabs").tabs("close", title);
		});
	});

	// 关闭除当前之外的TAB
	$("#mm-tabcloseother").click(function() {
		$("#mm-tabcloseright").click();
		$("#mm-tabcloseleft").click();
	});

	// 关闭当前左侧的TAB
	$("#mm-tabcloseleft").click(function() {
		let prevAll = $(".tabs-selected").prevAll();

		if(prevAll.length > 0) {
			prevAll.each(function(i, n) {
				let title = $("span.tabs-inner:eq(0) span.tabs-closable", $(n)).text();

				deleteFromTabs(title);
				$("#tabs").tabs("close", title);
			});
		}
	});

	// 关闭当前右侧的TAB
	$("#mm-tabcloseright").click(function() {
		let nextAll = $(".tabs-selected").nextAll();

		if(nextAll.length > 0) {
			nextAll.each(function(i, n) {
				let title = $("span.tabs-inner:eq(0) span.tabs-closable", $(n)).text();

				deleteFromTabs(title);
				$("#tabs").tabs("close", title);
			});
		}
	});
}

/**
 * 设置排序
 */
function setSort() {
	$.messager.prompt("设置菜单排序", "请输入排序号", function(pxh) {
		if (pxh){
			let data = $("#menu_tree").tree("getSelected");

			ajaxPost("/user_menu/update", {
				menuId: data.id,
				pxh: pxh
			}, function(res) {
				showMsg(res.message);

				$("#menu_tree").tree("reload");
			}, error);
		}
	});
}

/**
 * 保存菜单树的修改
 */
function saveChange() {
	/**
	 * 保存权限树的修改
	 */
	let row = $("#role_list").datalist("getSelected");

	if (row) {
		if (insertIds.length > 0 || deleteIds.length > 0) {
			let data = new FormData();

			data.append("roleId", row.id);

			if (insertIds.length > 0) {
				data.append("insertIds", insertIds);
			}
			if (deleteIds.length > 0) {
				data.append("deleteIds", deleteIds);
			}

			postForm("/role_permission/distribute", data, function (resp) {
				insertIds = [];
				deleteIds = [];

				showMsg(resp.message);

				$("#permission_tree").tree("reload");
			}, error);
		}
	}

	if (displayIds.length > 0 || hideIds.length > 0) {
		let data = new FormData();

		if (displayIds.length > 0) {
			data.append("displayIds", displayIds);
		}
		if (hideIds.length > 0) {
			data.append("hideIds", hideIds);
		}

		postForm("/user_menu/control", data, function (resp) {
			hideIds = [];
			displayIds = [];

			showMsg(resp.message);

			$("#menu_tree").tree("reload");
		}, error);
	}

	$("#system_window").dialog("close");
}

/**
 * 清理前端缓存
 */
function clearCache() {
	// ...
	localStorage.clear();
}

/**
 * 折叠全部
 * @param selector 选择器
 */
function collapseAll(selector) {
	$(selector).tree("collapseAll");
}

/**
 * 展开全部
 * @param selector 选择器
 */
function expandAll(selector) {
	$(selector).tree("expandAll");
}

$(function() {
	InitLeftMenu();
	tabClose();
	tabCloseEven();

	let tabs_ = $("#tabs");

	tabs_.tabs("add", {
		title: "欢迎使用",
		icon: "",
		closable: false,
		content: createFrame("/main.html")
	});

	tabs = getTabs();

	ajaxGet("/user/getLogin", {}, function(resp) {
		let data = resp.data;

		$("#loginUser").html(data.name);
	}, error);

	$("#more").menubutton({
		menu: "#menu"
	});

	// 系统设置
	$("#sys_set").on("click", function() {
		ajaxGet("/user/getLogin", {},function(resp) {
			let data = resp.data;

			$("#username").textbox("setValue", data.username);
		}, error);

		$("#role_list").datalist("reload");
		$("#permission_tree").tree("reload");

		$("#system_window").dialog("open");
	});

	// 清理缓存
	$("#clean").on("click", function() {
		ajaxPost("/cache/clean", {},function(response) {
			showMsg(response.message);

			// 清理前端缓存
			clearCache();
		}, error);
	});

	// 安全退出
	$("#exit").click(function() {
		$.messager.confirm("系统提示", "您确定要退出本次登录吗?", function(bool) {
			if (bool) {
				ajaxPost("/user/logout", {}, function() {
					location.href = loginPage;
				}, error);
			}
		});
	});

	// 接口文档
	$("#doc").click(function() {
		location.href = "/doc.html";
	});

	// 初始化权限
	$("#init").click(function() {
		ajaxPost("/permission/resources", {}, function(res) {
			showMsg(res.message);
		}, error);
	});

	/*system_window*/
	$("#sys_tabs").tabs({
		fit: true,
		pill: true,
		narrow: false,
		border: false,
		closable: false,
		justified: true,
		showHeader: true
	});

	// 角色列表
	$("#role_list").datalist({
		url: "/role/selectAll",
		idField: "id",
		valueField: "id",
		textField: "name",
		width: 148,
		height: 361,
		checkbox: true,
		textFormatter: function(value, rowData, rowIndex) {
			return "<div title='" + rowData.description + "'>" + rowData.name + "</div>";
		},
		onSelect: function(rowIndex, rowData) {
			if(rowData) {
				$("#permission_tree").tree({
					url: "/role_permission/listTree?roleId=" + rowData.id
				});
			}
		}
	});

	$("#permission_tree").tree({
		url: "/role_permission/listTree",
		dnd: true,
		animate: true,
		checkbox: true,
		onContextMenu: function(e, node) {
			e.preventDefault();

			$("#permission_tree").tree("select", node.target);

			$("#menu-permission_tree").menu("show", {
				left: e.pageX,
				top: e.pageY
			});
		},
		onCheck: function (node, checked) {
			let children = node.children;

			// 父节点点击复选框
			if (children) {
				if (checked) {
					for (let i = 0; i < children.length; i++) {
						insertIds.push(children[i].id);
					}
				} else {
					for (let i = 0; i < children.length; i++) {
						deleteIds.push(children[i].id);
					}
				}
			} else {
				if (checked) {
					insertIds.push(node.id);
				} else {
					deleteIds.push(node.id);
				}
			}
		}
	});

	$("#menu_tree").tree({
		url: "/menu/listTree",
		method: "get",
		lines: true,
		animate: true,
		checkbox: true,
		onlyLeafCheck: false,
		formatter:function(node) {
			return node.text + "[" + node.pxh + "]";
		},
		onContextMenu: function(e, node) {
			e.preventDefault();

			$("#menu_tree").tree("select", node.target);

			$("#menu-menu_tree").menu("show", {
				left: e.pageX,
				top: e.pageY
			});
		},
		onCheck: function (node, checked) {
			let children = node.children;

			// 父节点点击复选框
			if (children) {
				if (checked) {
					for (let i = 0; i < children.length; i++) {
						displayIds.push(children[i].id);
					}
				} else {
					for (let i = 0; i < children.length; i++) {
						hideIds.push(children[i].id);
					}
				}
			} else {
				if (checked) {
					displayIds.push(node.id);
				} else {
					hideIds.push(node.id);
				}
			}
		}
	});

	$("#system_window").dialog({
		title: "系统设置",
		modal: true,
		closed: true,
		closable: true,
		draggable: false,
		buttons: [{
			iconCls: "icon-ok",
			text: "确定",
			handler: function() {
				saveChange();
			}
		}]
	});

	/**
	 * password_form
	 */
	// 用户名
	$("#username").textbox({
		width: 150,
		editable: false
	});

	// 旧密码
	$("#oldPass").passwordbox({
		width: 150,
		required: true,
		inputEvents: $.extend({}, $.fn.passwordbox.defaults.inputEvents, {
			keypress: function(e) {
				let char = String.fromCharCode(e.which);

				$("#viewer").html(char).fadeIn(200, function() {
					$(this).fadeOut();
				});
			}
		})
	});

	// 新密码
	$("#password").passwordbox({
		width: 150,
		required: true,
		inputEvents: $.extend({}, $.fn.passwordbox.defaults.inputEvents, {
			keypress: function(e) {
				let char = String.fromCharCode(e.which);

				$("#viewer").html(char).fadeIn(200, function() {
					$(this).fadeOut();
				});
			}
		})
	});

	// 确认密码
	$("#rePass").passwordbox({
		width: 150,
		required: true,
		inputEvents: $.extend({}, $.fn.passwordbox.defaults.inputEvents, {
			keypress: function(e) {
				let char = String.fromCharCode(e.which);

				$("#viewer").html(char).fadeIn(200, function() {
					$(this).fadeOut();
				});
			}
		})
	});

	$("#password_form").form({
		url: "/user/updatePassword",
		onSubmit: function() {
			let password = $("#password").passwordbox("getValue");
			let rePass = $("#rePass").passwordbox("getValue");

			if (rePass !== password) {
				alertMsg("两次输入的密码不一致，请重新输入！", "warning");

				return false;
			} else {
				let bool = $("#password_form").form("validate");

				if (!bool) {
					alertMsg("请输入正确的表单项", "warning");
				}

				return bool;
			}
		},
		success: function(response) {
			showMsg(response.message);

			location.href = loginPage;
		}
	});

	tabs_.tabs({
		onSelect: function(title) {
			setCurrent(title);

			let currTab = tabs_.tabs("getTab", title);
			let iframe = $(currTab.panel("options").content);
			let src = iframe.attr("src");

			if (src) {
				tabs_.tabs("update", {
					tab: currTab,
					options: {
						content: createFrame(src)
					}
				});
			}
		},
		onClose: function(title) {
			deleteFromTabs(title);
		}
	});

	// 还原tab
	let currTab = getCurrent();

	if (tabs && tabs.length > 0) {
		for (let i = 0; i < tabs.length; i++) {
			let tab = tabs[i];

			addTab(tab.title, tab.url);
		}

		tabs_.tabs("select", currTab);
	}

});