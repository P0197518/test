﻿var localObj = window.location;
var contextPath = localObj.pathname.split("/")[1];
var basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath;
var server_context = basePath;

mini.VTypes["moneyErrorText"] = "请输入正确金额。";
mini.VTypes["money"] = function(v) {
	if (v == null || v == "") {
		return true;
	}
	var re = /^(\-|\+)?\d+(\.\d{1,4})?$/;
	if (re.test(v)) {
		return true;
	}
	return false;
}
mini.VTypes["positiveIntegerErrorText"] = "请输入正整数。";
mini.VTypes["positiveInteger"] = function(v) {
	if (v == null || v == "") {
		return true;
	}
	var re = /^[1-9]{1}[0-9]*$/;
	if (re.test(v)) {
		return true;
	}
	return false;
}

mini.VTypes["halfErrorText"] = "请输入{0}位以内的半角。";
mini.VTypes["half"] = function(A, $) {

	if (mini.isNull(A) || A === "") {
		return true;
	}
	var _ = parseInt($);
	if (!A || isNaN(_)) {
		return true;
	}
	if (getHalfLength(A) <= _) {
		return true;
	} else {
		return false
	}
}

mini.VTypes["halfonlyErrorText"] = "请输入半角。";
mini.VTypes["halfonly"] = function(A) {

	if (mini.isNull(A) || A === "") {
		return true;
	}
	return checkOnlyHalf(A);
}

mini.VTypes["codeErrorText"] = "请输入半角英数字和横线。";
mini.VTypes["code"] = function(A) {

	if (mini.isNull(A) || A === "") {
		return true;
	}
	var regs = "^[A-Za-z0-9-\/]+$";
	var regus = new RegExp(regs);
	return regus.test(A);
}

function checkOnlyHalf(strtmp) {
	for (var i = 0; i < strtmp.length; i++) {
		if (strtmp.charCodeAt(i) > 128) {
			return false;
		}
	}
	return true;
}

function getHalfLength(strtmp) {
	var totalLength = 0;
	for (var i = 0; i < strtmp.length; i++) {
		if (strtmp.charCodeAt(i) > 128) {
			totalLength = totalLength + 2;
		} else {
			totalLength = totalLength + 1;
		}
	}
	return totalLength;
}

function checkCount(v) {
	if (v == null || v == "") {
		return true;
	}
	var re = /^\d+(\.\d{1,2})?$/;
	if (re.test(v))
		return true;
	return false;
}

function checkDateArea(startDate, endDate) {
	if (startDate == "" || endDate == "") {
		// TODO
		businessMessage.alert("E0022", "2");
		return false;
	}
	var number = 0;
	var yearToMonth = (endDate.getFullYear() - startDate.getFullYear()) * 12;
	number += yearToMonth;
	monthToMonth = endDate.getMonth() - startDate.getMonth();
	number += monthToMonth;
	if (number > 24 || number < 0) {
		// TODO
		businessMessage.alert("E0022", "2");
		return false;
	} else {
		return true;
	}
}

function checkSearchDateArea(startDate, endDate) {
	if (startDate == "" || endDate == "") {
		// TODO
		businessMessage.alert("E0065", "1");
		return false;
	}
	var number = 0;
	var yearToMonth = (endDate.getFullYear() - startDate.getFullYear()) * 12;
	number += yearToMonth;
	monthToMonth = endDate.getMonth() - startDate.getMonth();
	number += monthToMonth;
	if (number > 12 || number < 0) {
		// TODO
		businessMessage.alert("E0065", "1");
		return false;
	} else {
		return true;
	}
}

function checkDateAreaOneYear(startDate, endDate) {
	if (startDate == "" || endDate == "") {
		// TODO
		businessMessage.alert("E0022", "1");
		return false;
	}
	var number = 0;
	var yearToMonth = (endDate.getFullYear() - startDate.getFullYear()) * 12;
	number += yearToMonth;
	monthToMonth = endDate.getMonth() - startDate.getMonth();
	number += monthToMonth;
	if (number > 12 || number < 0) {
		// TODO
		businessMessage.alert("E0022", "1");
		return false;
	} else {
		return true;
	}
}

function monthDiff(d1, d2) {
	var months;
	months = (d2.getFullYear() - d1.getFullYear()) * 12;
	months -= d1.getMonth();
	months += d2.getMonth();
	return months;
}