// 販売先選択
function customChoose(okFunction) {
	var popupParam = openCommonPopupParam("/commonSelect/customIndex", "客户选择");
	popupParam.width = 600;
	popupParam.height = 600;
	popupParam.ondestroy = function(action) {
		if (action != "" && action != "cancle" && action != "close") {
			okFunction(action);
		}
	};
	mini.open(popupParam);
}
// 仕入れ先選択
function supplierChoose(okFunction) {
	var popupParam = openCommonPopupParam("/commonSelect/supplierIndex",
			"供应商选择");
	popupParam.width = 600;
	popupParam.height = 600;
	popupParam.ondestroy = function(action) {
		if (action != "" && action != "cancle" && action != "close") {
			okFunction(action);
		}
	};
	mini.open(popupParam);
}