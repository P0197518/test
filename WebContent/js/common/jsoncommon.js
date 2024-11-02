// 画面データをJSON文字列に変換して返却する
function getJsonString(panelId){
	return mini.encode(getJsonObject(panelId));
}
// 画面数据转换成json对象
function getJsonObject(panelId){
	var jsonObject = {};
	var panels = panelId.split(",");
	var allowInputTypes = new Array("checkbox","radio","text","hidden");
	panels.forEach(function(e){
		$("#"+e +" input[json-ignore!='true']").each(
				function(){
					var inputType = $(this).attr("type");
					if($.inArray(inputType, allowInputTypes) == -1){
						return;
					}
					if(inputType != "checkbox" && $(this).attr("name")!=undefined){
						if($(this).attr("id").indexOf("$")>=0){
							eval("jsonObject."+$(this).attr("name")+ '= mini.get("'+$(this).attr("name")+'").getValue();');
						} else{
							eval("jsonObject."+$(this).attr("name")+ '= $(this).val();');
						}
						
					}
		});
		var checkNames = new Array();
		var index = 0;
		$("#"+e +" input[type='checkbox'][json-ignore!='true']").each(
			function(){
				var name = $(this).attr("name");
				if($.inArray(name, checkNames) > -1){
					return;
				}
				checkNames[index] = name;
				index = index+1;
				
				var arrData = new Array();
				var nameIndex = 0;
				$("#"+ e +" input[name='"+ name +"']").each(
					function(){
						if(!$(this).is(':checked')){
							return;
						}
						arrData[nameIndex] = $(this).val();
						nameIndex = nameIndex +1;
					});
				if(arrData.length > 0 && $(this).attr("name")!=undefined){
					eval("jsonObject."+$(this).attr("name")+ "= arrData;");
				}
		});
		$("#"+e +" select[json-ignore!='true']").each(
				function(){
					if($(this).attr("name")!=undefined){
						eval("jsonObject."+$(this).attr("name")+ "= $(this).val();");
					}
				}
		);
		$("#"+e +" textarea[json-ignore!='true']").each(
			function(){
				if($(this).attr("name")!=undefined){
				eval("jsonObject."+$(this).attr("name")+ "= $(this).val();");
				}
		});
	});
	
	return jsonObject;
}

// 检查datagrid是否为空白行
function checkDataEmptyRow(row){
	for(var item in row){
		if(item.indexOf("_") != 0){
			if(row[item]!=""){
				return false;
			}
		}
	}
	return true;
}

// 获取不是空白的行
function getNoEmptyRows(rows){
	var resultRows = [];
	for(var index=0;index<rows.length;index++){
		if (!checkDataEmptyRow(rows[index])){
			resultRows.push(rows[index]);
		}
	}
	return resultRows;
}

// JSONデータを画面に設定する
function setFormData(jsonData) {
	if (jsonData == null || jsonData == undefined) {
		return;
	}
	var allowInputTypes = new Array("text", "hidden", "date", "time", "password");

	for ( var item in jsonData) {
		$("[name='" + item + "']")
				.each(
						function() {
							var inputType = $(this).attr("type");
							if ($(this).is("input")) {
								if ($.inArray(inputType, allowInputTypes) != -1) {
									if (inputType == "date") {
										$(this).val(
												$.formatDate(jsonData[item],
														"yyyy-MM-dd"));
									} else if (inputType == "time") {
										if (jsonData[item].length != 4) {
											$(this).val("");
										} else {
											var formatTime = jsonData[item].substring(0,2)
											+ ":"
											+ jsonData[item].substring(2,jsonData[item].length)
											$(this).val(formatTime);
										}
									} else {
										showFormatData(this, jsonData[item]);
									}
								} else if (inputType == "checkbox") {
									if ($
											.inArray($(this).val(),
													jsonData[item]) != -1) {
										$(this)[0].checked = true;
									} else {
										$(this)[0].checked = false;
									}
								} else if (inputType == "radio") {
									if ($(this).val() == jsonData[item]) {
										$(this)[0].checked = true;
									} else {
										$(this)[0].checked = false;
									}
								}
							} else if ($(this).is("select")) {
								$(this).val(jsonData[item]);
							} else if ($(this).is("textarea")) {
								showFormatData(this, jsonData[item]);
							} else {
								showFormatData(this, jsonData[item]);
							}
						});
	}
}

// NULLを空白に変換する
function nullToEmpty(source) {
	if (source == null) {
		return "";
	} else {
		return source;
	}
}

// 設定属性により、データをフォーマットして画面に表示する
function showFormatData(target, data) {

	var formInputTypes = new Array("INPUT", "SELECT", "TEXTAREA");
	var tagName = $(target)[0].tagName;

	var formInputFlag = false;
	if ($.inArray(tagName, formInputTypes) > -1) {
		formInputFlag = true;
	}

	var dataResult = "";
	var dataFormat = $(target).attr("date-format");
	var moneyFormat = $(target).attr("money-format");
	if (data == null) {
		dataResult = "";
	} else if (dataFormat != undefined) {
		dataResult = $.formatDate(data, dataFormat);
	} else if (moneyFormat != undefined) {
		dataResult = $.formatMoney(data);
	} else {
		dataResult = data;
	}
	if (formInputFlag) {
		$(target).val(dataResult);
	} else {
		$(target).text(dataResult);
	}

}
