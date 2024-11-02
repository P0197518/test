function productnoChange() {
	search();
}

// 加入出库按钮
function addAll() {
	if (grid.getSelecteds().length == 0) {
		businessMessage.alert("E00002");
		return;
	}
	var ids = [];
	for (var i = 0; i < grid.getSelecteds().length; i++) {
		for (var j = 0; j < grid2.getData().length; j++) {
			if (grid.getSelecteds()[i].orderListNo == grid2.getData()[j].orderListNo
					&& grid.getSelecteds()[i].productNo == grid2.getData()[j].productNo) {
				businessMessage.alert("E00012");
				return;
			}

			if (grid.getSelecteds()[i].ofHeadOffice != grid2.getData()[j].ofHeadOffice
					|| (grid.getSelecteds()[i].ofHeadOffice == false
							&& grid2.getData()[j].ofHeadOffice == false && grid
							.getSelecteds()[i].customNo != grid2.getData()[j].customNo)) {
				businessMessage.alert("E00034");
				return;
			}
		}

		for (var subI = 0; subI < grid.getSelecteds().length; subI++) {
			if (grid.getSelecteds()[i].ofHeadOffice != grid.getSelecteds()[subI].ofHeadOffice
					|| (grid.getSelecteds()[i].ofHeadOffice == false
							&& grid.getSelecteds()[subI].ofHeadOffice == false && grid
							.getSelecteds()[i].customNo != grid.getSelecteds()[subI].customNo)) {
				businessMessage.alert("E00034");
				return;
			}
		}

		var item = JSON.parse(JSON.stringify(grid.getSelecteds()[i]));
		ids.push(item);
	}

	grid2.addRows(ids);

	claculateTotal();
}
// 删除按钮
function removes() {
	var items = grid2.getSelecteds();
	if (items.length > 0) {
		grid2.removeRows(items);
		claculateTotal();
	}
}

// 查询订单明细操作
function search() {
	
	var searchcondation = form.getData();
	grid.load(getPagerInfo("grid1", searchcondation));
	/*doAjax("/P0105OutPlan/getOrderlist", form.getData(), function(obj) {
		grid.setData(obj.data);
	});*/
}

// 保存操作
function save(status) {
	form.validate();
	grid2.validate();
	if (form.isValid() == false || grid2.isValid() == false) {
		businessMessage.alert("E00006");
		return;
	}

	var listData = grid2.getData();
	if (grid2.getData().length == 0) {
		businessMessage.alert("EC0001", "待出货订单列表");
		return;
	}
	for (var i = 0; i < grid2.getData().length; i++) {
		// 去除计划数为空的行
		if (grid2.getData()[i].planCount == 0
				|| grid2.getData()[i].planCount == ''
				|| grid2.getData()[i].planCount == null) {
			listData.remove(grid2.getData()[i]);
		}
	}

	/*
	 * for (var i = 0; i < listData.length; i++) { // lotNo验证 if
	 * (grid2.getData()[i].lotNo == null || grid2.getData()[i].lotNo == "") {
	 * businessMessage.alert("EC0098"); return; } }
	 */
	for (var i = 0; i < listData.length; i++) {
		// 数量检查
		var sum = 0;
		for (var j = 0; j < listData.length; j++) {
			if (listData[j].orderListNo == listData[i].orderListNo) {
				sum = calMoney.floatAdd(listData[j].planCount, sum);
			}
		}

		if (sum > listData[i].planCount) {
			businessMessage.alert("Q00019", null, function() {
				oksave(status, listData);
			}, function() {
				return;
			})
			return;
		}
	}
	oksave(status, listData);
}
function oksave(status, listData) {
	businessMessage.alert("Q00002", null, function() {
		var data = form.getData();
		data.outPlanStatus = status;
		data.outPlanList = listData;
		if (listData[0].ofHeadOffice) {
			data.customNo = mini.get("headCustomNo").getValue();
		} else {
			data.customNo = listData[0].customNo;
		}

		doAjax("/P0105OutPlan/save", data, function(obj) {
			businessMessage.alert("IC0001", null, function() {
				if (mini.get("mode").getValue() == "update") {
					CloseWindow("ok");
				} else {
					grid2.clearRows();
					// grid.deselectAll();
					search();
				}
			});
		});
	});
}

// 计算小计
// 列表输入值变化处理
grid2.on("cellcommitedit", function(e) {
	if (e.oldValue == e.value) {
		return;
	}

	if (e.field == 'planCount') {
		rowInputChange(e);
	}
});

// 行数据变化时，重新计算金额
function rowInputChange(e) {
	var rowData = grid.getData()[e.rowIndex];

	var updateData = {
		planCount : e.value
	};
	updateData.totalMoneyF = calMoney.floatMul(e.value, e.record.orderPriceF);
	updateData.totalMoney = calMoney.floatMul(e.value, e.record.orderPrice);

	grid2.updateRow(grid2.getData()[e.rowIndex], updateData);
	claculateTotal();
}

// 计算合计
function claculateTotal() {
	var planCount = 0;
	var totalMoneyF = 0;
	var totalMoney = 0;
	for (var j = 0; j < grid2.getData().length; j++) {
		planCount = calMoney.floatAdd(grid2.getData()[j].planCount, planCount);
		totalMoney = calMoney.floatAdd(grid2.getData()[j].totalMoney,
				totalMoney);
		totalMoneyF = calMoney.floatAdd(grid2.getData()[j].totalMoneyF,
				totalMoneyF);
	}
	mini.get("planCount").setValue(planCount);
	mini.get("totalMoneyF").setValue(totalMoneyF);
	mini.get("totalMoney").setValue(totalMoney);
}