function BusinessMessage() {
	this.init;
	this.messages;
};
BusinessMessage.prototype = {
	get : function(id, args) {
		if (!this.init) {
			this.messages = new Array();
			// 这里可以使用Java代码的方法读取服务端已经缓存好的消息提示语
			// 或者可以静态设置一些提示语
			this.messages['IC0001'] = "Data saved successfully."; // 添加和更新成功情况下使用
			this.messages['IC0002'] = "Data deleted successfully."; // 删除成功情况下使用
			this.messages['IC0003'] = "恢复数据成功。"; // 恢复成功情况下使用
			this.messages['IC0004'] = "占用取消成功。"; // 取消占用情况下使用
			this.messages['IC0005'] = "入库成功。"; //
			this.messages['IC0006'] = "数据导出成功。"; //
			this.messages['IC0007'] = "月次成本计算执行中，请稍候刷新页面确认执行结果。"; //

			this.messages['EC0001'] = "{0} cannot be empty, please enter the item.";
			this.messages['EC0002'] = "The input length of {0} cannot be greater than {1}.";
			this.messages['EC0003'] = "{0} please enter a half width number.";
			this.messages['EC0004'] = "{0} please enter a half width number.";
			this.messages['EC0005'] = "{0} please enter the correct time format.";
			this.messages['EC0006'] = "{0} please enter a positive integer.";
			this.messages['EC0007'] = "{0} please enter a positive number.";
			this.messages['EC0008'] = "{0} please enter a real number.";
			this.messages['EC0009'] = "{0} please enter a number between {1} and {2}.";
			this.messages['EC0010'] = "Data saving failed. Please try again.";
			this.messages['EC0011'] = "{0} please enter data with a fixed length of {1}.";
			this.messages['EC0015'] = "文件上传失败，亲重试。";
			this.messages['EC0016'] = "User name or password does not exist, please re-enter.";
			this.messages['EC0017'] = "导入的数据不存在，请确认。";
			this.messages['EC0019'] = "Please enter the correct data.";
			this.messages['EC0090'] = "System error, please contact the administrator.";
			this.messages['EC0091'] = "Operation permission verification failed, please contact the administrator.";
			this.messages['EC0092'] = "Login timeout, please login again.";
			this.messages['EC0093'] = "请指定要导入的文件。";
			this.messages['EC0094'] = "Unknown error occurred in data validation, please contact the administrator.";
			this.messages['EC0095'] = "驳回失败，请重试。";
			this.messages['EC0096'] = "There is no data to delete.";
			this.messages['EC0097'] = "材质的可用在库数量不足";
			this.messages['EC0098'] = "计划出货产品还没有生成LotNo，不能作成计划，请确认。";
			this.messages['EC0099'] = "此指令书号为子指令书号，不能继续添加。";

			this.messages['E00001'] = "{0} already exists, please re-enter.";
			this.messages['E00002'] = "Please select the data to process.";
			this.messages['E00003'] = "The data has been modified by someone else. Please refresh the data and try again.";
			this.messages['E00004'] = "The data does not exist, please try again.";
			this.messages['E00006'] = "The input data is incorrect. Please modify the input data according to the prompt.";// 输入的数据不正确，请参照提示修改数据
			this.messages['E00007'] = "订单已经作成出货计划，或者已经发货，不能修改。";
			this.messages['E00008'] = "Please enter {0}.";
			this.messages['E00009'] = "Input {0} is the same, please re-enter.";
			this.messages['E00013'] = "{0} does not exist, please re-enter.";
			this.messages['E00011'] = "有未知的指令书号，请重新确认。";
			this.messages['E00012'] = "该订单的产品已经添加，不能重复添加，请确认。";
			this.messages['E00014'] = "已将经开始生产或生产完成，不能修改。";
			this.messages['E00015'] = "应该采购数不足，不能加入采购。";
			this.messages['E00016'] = "该订单号下无此品番，请重新选择。";
			this.messages['E00017'] = "请选择是完成品的数据";
			this.messages['E00018'] = "此订单号为草稿，请重新输入";
			this.messages['E00019'] = "Only one default account can be selected. Please select again.";
			this.messages['E00020'] = "输入的安排数和必要数不相同，请重新输入。";
			this.messages['E00021'] = "该条明细已经开始入货，不能删除。";
			this.messages['E00022'] = "The bank account has been deleted, please re select the default account.";
			this.messages['E00023'] = "被修改的数量不能小于已入库数量，请重新确认。";
			this.messages['E00024'] = "Your department is the same as ours, please select again.";
			this.messages['E00025'] = "{0}已有相同物料加入列表，请重新选择。";
			this.messages['E00026'] = "{0}指令书号下无此品番，请重新输入。";
			this.messages['E00028'] = "可用数量不足，请重新输入占用数量。";
			this.messages['E00029'] = "您选择的不是完成品，无法打印出货箱单。";
			this.messages['E00030'] = "您选择的不是入库记录，无法打印入库单。";
			this.messages['E00031'] = "{0}的可用在库数量不足,请确认";
			this.messages['E00032'] = "Please enter a number.";
			this.messages['E00033'] = "当前指令书已经做成采购订单或委外订单，若要继续删除请先删除对应的采购订单或委外订单。";
			this.messages['E00034'] = "不同客户产品不能加入同一出货计划，请确认。";
			this.messages['E00035'] = "物料中当前产品单重没有录入，请先录入单重再入库。";
			this.messages['E00036'] = "该采购的产品已经添加，不能重复添加，请确认。";
			this.messages['E00037'] = "该指令书领料数量大于占用数量，不能拆分指令书，请确认。";
			this.messages['E00038'] = "子指令书的安排数大于等于父指令书的安排数，不能作成子指令书，请确认。";
			this.messages['E00039'] = "占用数量大于订单数，请确认。";
			this.messages['E00040'] = "请先完善该物料的BOM管理";
			this.messages['E00041'] = "完成品入库数量不能大于领料数量，请确认。";
			this.messages['E00042'] = "该订单的产品已经部分发货，不能取消占用，请确认。";
			this.messages['E00043'] = "采番数量超出可采番范围，不能继续采番，请确认。";

			this.messages['Q00001'] = "Are you sure you want to delete the data?";
			this.messages['Q00002'] = "Are you sure you want to save the data?";
			this.messages['Q00003'] = "Are you sure you want to restore the data?"; // 是否恢复数据？
			this.messages['Q00004'] = "确认要设置出库完成吗？"; // 是否恢复数据？
			this.messages['Q00005'] = "确认要设置入库完成吗？";
			this.messages['Q00006'] = "确认要打印合同吗？";
			this.messages['Q00007'] = "本次出库数量已超出计划数量，是否继续？";
			this.messages['Q00008'] = "确定盘点完成吗？";
			this.messages['Q00009'] = "新生成的LotNo与指令书中的LotNo不一致，是否继续？";
			this.messages['Q00010'] = "确定要打印盘点清单吗？";
			this.messages['Q00011'] = "确定要取消占用吗？";
			this.messages['Q00012'] = "订单可能已经作成出货计划，或者已经发货，确定删除吗？";
			this.messages['Q00013'] = "本次采购数量已超出应该采购数量，是否继续？";
			this.messages['Q00014'] = "本次委外数量已超出应该委外数量，是否继续？";
			this.messages['Q00015'] = "确定要打印采购单吗？";
			this.messages['Q00016'] = "确定要打印委外加工单吗？";
			this.messages['Q00017'] = "Change of {0} will clear {1}. Do you want to continue?";
			this.messages['Q00018'] = "入库数量大于计划数量， 是否继续？";
			this.messages['Q00019'] = "计划出库数量大于订单未出货数量， 是否继续？";
			this.messages['Q00021'] = "本次操作即将执行指定月次的成本统计，是否确定？";
			this.messages['Q00022'] = "入库数量大于计划采购数,是否继续？";
			this.messages['Q00023'] = "生产数量加上不良数量大于安排数， 是否继续？";
			this.messages['Q00024'] = "入库成功，是否继续打印入库单？";
			this.messages['Q00025'] = "入出库数量大于委外订单数量，是否继续？";
			this.messages['Q00026'] = "出库成功，是否继续打印出库单？";
			this.messages['Q00027'] = "LotNo已存在，是否继续保存？";

			this.init = true;
		}
		var message = this.messages[id];
		if (!message && message !== "") {
			return id;
		}
		if (args) {
			if (typeof args == "object" && args.length) {
				for (var i = 0; i < args.length; i++) {
					var pattern = new RegExp("\\{" + i + "\\}", "g");
					message = message.replace(pattern, args[i]);
				}
			} else if (typeof args == "String") {
				var pattern = new RegExp("\\{0\\}", "g");
				message = message.replace(pattern, args);
			} else {
				message = message.replace(/\{0\}/g, args);
			}
		}
		return message;
	},
	alert : function(messageId, messageArgs, okFunction, cancleFunction) {
		if (messageId.indexOf("I") == 0) {
			mini.alert(businessMessage.get(messageId, messageArgs), "Information Tips",
					function(action) {
						if (action == "ok") {
							if (okFunction != undefined && okFunction != null) {
								okFunction();
							}
						}
					});
		} else if (messageId.indexOf("W") == 0 || messageId.indexOf("Q") == 0) {
			mini.confirm(businessMessage.get(messageId, messageArgs), "Confirm Message",
					function(action) {
						if (action == "ok") {
							if (okFunction != undefined && okFunction != null) {
								okFunction();
							}
						} else if (cancleFunction != undefined
								&& cancleFunction != null) {
							cancleFunction();
						}
					});
		} else {
			mini.alert(businessMessage.get(messageId, messageArgs), "Warn message",
					function(action) {
						if (action == "ok") {
							if (okFunction != undefined && okFunction != null) {
								okFunction();
							}
						}
					});
		}
	}
};
var businessMessage = new BusinessMessage();