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
			this.messages['IC0001'] = "数据保存成功。"; // 添加和更新成功情况下使用
			this.messages['IC0002'] = "数据删除成功。"; // 删除成功情况下使用
			this.messages['IC0003'] = "恢复数据成功。"; // 恢复成功情况下使用
			this.messages['IC0004'] = "占用取消成功。"; // 取消占用情况下使用
			this.messages['IC0005'] = "入库成功。"; //
			this.messages['IC0006'] = "数据导出成功。"; //
			this.messages['IC0007'] = "月次成本计算执行中，请稍候刷新页面确认执行结果。"; //
			this.messages['IC0008'] = "数据导入成功。"; //
			this.messages['IC0009'] = "生产性计算开始执行..."; 

			this.messages['EC0001'] = "{0}不能为空，请输入该项目。";
			this.messages['EC0002'] = "{0}输入长度不能大于{0}。";
			this.messages['EC0003'] = "{0}请输入半角数字。";
			this.messages['EC0004'] = "{0}请输入半角英数字。";
			this.messages['EC0005'] = "{0}请输入正确的时间格式。";
			this.messages['EC0006'] = "{0}请输入正整数。";
			this.messages['EC0007'] = "{0}请输入正数。";
			this.messages['EC0008'] = "{0}请输入实数。";
			this.messages['EC0009'] = "{0}请输入{1}和{2}之间的数值。";
			this.messages['EC0010'] = "数据保存失败，请重试。";
			this.messages['EC0011'] = "{0}请输入固定长度为{1}的数据。";
			this.messages['EC0015'] = "文件上传失败，亲重试。";
			this.messages['EC0016'] = "用户名或密码不存在，请重新输入。";
			this.messages['EC0017'] = "导入的数据不存在，请确认。";
			this.messages['EC0018'] = "导入的数据失败，请确认。";
			
			this.messages['EC0019'] = "请输入正确的数据。";
			this.messages['EC0020'] = "导入的数据不正确，请修改导入数据。";
			this.messages['EC0090'] = "系统发生错误，请联系管理员。";
			this.messages['EC0091'] = "操作权限验证失败，请联系管理员。";
			this.messages['EC0092'] = "登录超时，请重新登录系统。";
			this.messages['EC0093'] = "请指定要导入的文件。";
			this.messages['EC0094'] = "数据验证发生未知错误，请联系管理员。";
			this.messages['EC0095'] = "驳回失败，请重试。";
			this.messages['EC0096'] = "没有可删除的数据";
			this.messages['EC0097'] = "材质的可用在库数量不足";
			this.messages['EC0098'] = "计划出货产品还没有生成LotNo，不能作成计划，请确认。";
			this.messages['EC0099'] = "此指令书号为子指令书号，不能继续添加。";
			
			this.messages['EC0100'] = "考勤数据没有导入，请导入考勤数据后执行计算。";
			this.messages['EC0101'] = "工作人员没有正确设置，请设置工作人员。";
			this.messages['EC0102'] = "该部门没有设置取数规则，请联系管理员设置取数规则。";
			this.messages['EC0103'] = "当前部门、月份的生产性正在执行，不能重复执行。";
			this.messages['EC0104'] = "部门不存在";
			this.messages['EC0105'] = "是否间接输入错误";
			this.messages['EC0106'] = "请选择导出年份。";
			this.messages['EC0107'] = "请选择导出月份。";
			this.messages['EC0108'] = "同一月份下，相同人员只能导入一次，请确认。";
			this.messages['EC0109'] = "该部门当月日报没有导入，请导入日报数据后执行计算。";
			this.messages['EC0110'] = "品名:{0}不存在，请确认。";


			
			this.messages['E00001'] = "{0}已经存在，请重新输入。"; // 后台更新失败情况下使用
			this.messages['E00002'] = "请选择要处理的数据。"; // 需要选择画面的一条数据，但是没有选择时使用
			this.messages['E00003'] = "该数据已经被其他人修改，请刷新数据后重试。";
			this.messages['E00004'] = "该数据不存在，请重试。";
			this.messages['E00006'] = "输入数据不正确，请根据提示修改输入数据。"; // 输入的数据不正确，请参照提示修改数据
			this.messages['E00007'] = "订单已经作成出货计划，或者已经发货，不能修改。";
			this.messages['E00008'] = "请输入{0}。";
			this.messages['E00009'] = "输入的{0}相同，请重新输入。";
			this.messages['E00013'] = "{0}不存在，请重新输入。";
			this.messages['E00011'] = "有未知的指令书号，请重新确认。";
			this.messages['E00012'] = "该订单的产品已经添加，不能重复添加，请确认。";
			this.messages['E00014'] = "已将经开始生产或生产完成，不能修改。";
			this.messages['E00015'] = "应该采购数不足，不能加入采购。";
			this.messages['E00016'] = "该订单号下无此品番，请重新选择。";
			this.messages['E00017'] = "请选择是完成品的数据";
			this.messages['E00018'] = "此订单号为草稿，请重新输入";
			this.messages['E00019'] = "只能选择一个默认账号,请重新选择";
			this.messages['E00020'] = "输入的安排数和必要数不相同，请重新输入。";
			this.messages['E00021'] = "该条明细已经开始入货，不能删除。";
			this.messages['E00022'] = "银行账户已删除,请重新选择默认账户";
			this.messages['E00023'] = "被修改的数量不能小于已入库数量，请重新确认。";
			this.messages['E00024'] = "所属部门与本部门相同，请重新选择。";
			this.messages['E00025'] = "{0}已有相同物料加入列表，请重新选择。";
			this.messages['E00026'] = "{0}指令书号下无此品番，请重新输入。";
			this.messages['E00028'] = "可用数量不足，请重新输入占用数量。";
			this.messages['E00029'] = "您选择的不是完成品，无法打印出货箱单。";
			this.messages['E00030'] = "您选择的不是入库记录，无法打印入库单。";
			this.messages['E00031'] = "{0}的可用在库数量不足,请确认";
			this.messages['E00032'] = "请输入数字。";
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
			this.messages['E00044'] = "请选择导入年月。";
			this.messages['E00045'] = "是否间接输入错误，请确认。";
			this.messages['E00046'] = "不能对相同数据进行设置，请确认";
			this.messages['E00047'] = "比较部门不能为空，请确认";
			this.messages['E00048'] = "比较年月不能为空，请确认";
			this.messages['E00049'] = "生产性计算执行中，请等待执行结束。";
			this.messages['E00050'] = "生产性计算执行出错，请点击【计算错误查看】按钮，查看错误内容。";
			this.messages['E00051'] = "当前部门无法进行比较，请确认";

			this.messages['Q00001'] = "确认要删除数据吗？"; // 是否删除数据？
			this.messages['Q00002'] = "确认要保存数据吗？"; // 是否保存数据？
			this.messages['Q00003'] = "确认要还原数据吗？"; // 是否恢复数据？
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
			this.messages['Q00017'] = "{0}变化，将会清空{1}， 是否继续？";
			this.messages['Q00018'] = "入库数量大于计划数量， 是否继续？";
			this.messages['Q00019'] = "计划出库数量大于订单未出货数量， 是否继续？";
			this.messages['Q00021'] = "本次操作即将执行指定月次的成本统计，是否确定？";
			this.messages['Q00022'] = "入库数量大于计划采购数,是否继续？";
			this.messages['Q00023'] = "生产数量加上不良数量大于安排数， 是否继续？";
			this.messages['Q00024'] = "入库成功，是否继续打印入库单？";
			this.messages['Q00025'] = "入出库数量大于委外订单数量，是否继续？";
			this.messages['Q00026'] = "出库成功，是否继续打印出库单？";
			this.messages['Q00027'] = "LotNo已存在，是否继续保存？";
			
			this.messages['Q00028'] = "当前部门、月份的生产性已经执行，确认要再次执行吗？执行后上次执行结果将不会保留。";
			this.messages['Q00029'] = "确定要执行计算吗？";

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
			mini.alert(businessMessage.get(messageId, messageArgs), "信息提示",
					function(action) {
						if (action == "ok") {
							if (okFunction != undefined && okFunction != null) {
								okFunction();
							}
						}
					});
		} else if (messageId.indexOf("W") == 0 || messageId.indexOf("Q") == 0) {
			mini.confirm(businessMessage.get(messageId, messageArgs), "确认消息",
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
			mini.alert(businessMessage.get(messageId, messageArgs), "错误消息",
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