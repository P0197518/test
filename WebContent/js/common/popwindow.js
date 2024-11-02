// 販売先選択
function customChoose(okFunction) {
	var popupParam = openCommonPopupParam("/commonSelect/customIndex",
    "販売先選択");
	popupParam.width = 600;
	popupParam.height = 600;
	popupParam.ondestroy = function(action) {
        if (action!="" && action != "cancle" && action != "close") {
        	okFunction(action);
        }
    };
    mini.open(popupParam);
}
// 仕入れ先選択
function supplierChoose(okFunction) {
	var popupParam = openCommonPopupParam("/commonSelect/supplierIndex",
    "仕入先選択");
	popupParam.width = 600;
	popupParam.height = 600;
	popupParam.ondestroy = function(action) {
        if (action!="" && action != "cancle" && action != "close") {
        	okFunction(action);
        }
    };
    mini.open(popupParam);
}