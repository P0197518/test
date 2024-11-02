// 日付と時刻のフォーマット部品。

$.formatDateSelf = function(date, pattern) {
	if (date == null) {
		return "";
	}
	if (pattern == null || pattern == undefined) {
		pattern = "yyyy/MM/dd HH:mm:ss";
	}
	this.getDate = function(date) {
		if (typeof (date) != "Date") {
			var formatedDateStr = date;
			if (date.length == 8) {
				var pattern = /(\d{4})(\d{2})(\d{2})/;
				formatedDateStr = date.replace(pattern, '$1/$2/$3');
			}

			date = new Date(formatedDateStr);
		}
		return date;
	};

	this.patternParts = /^(yy(yy)?|M(M(M(M)?)?)?|d(d)?|EEE(E)?|a|H(H)?|h(h)?|m(m)?|s(s)?|S)/;
	this.monthNames = [ 'January', 'February', 'March', 'April', 'May', 'June',
			'July', 'August', 'September', 'October', 'November', 'December' ];

	this.dayNames = [ 'Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday',
			'Friday', 'Saturday' ];

	this.patternValue = {
		yy : function(date) {
			return this.toFixedWidth(date.getFullYear(), 2);
		},
		yyyy : function(date) {
			return date.getFullYear().toString();
		},
		MMMM : function(date) {
			return this.monthNames[date.getMonth()];
		},
		MMM : function(date) {
			return this.monthNames[date.getMonth()].substr(0, 3);
		},
		MM : function(date) {
			return this.toFixedWidth(date.getMonth() + 1, 2);
		},
		M : function(date) {
			return date.getMonth() + 1;
		},
		dd : function(date) {
			return this.toFixedWidth(date.getDate(), 2);
		},
		d : function(date) {
			return date.getDate();
		},
		EEEE : function(date) {
			return this.dayNames[date.getDay()];
		},
		EEE : function(date) {
			return this.dayNames[date.getDay()].substr(0, 3);
		},
		HH : function(date) {
			return this.toFixedWidth(date.getHours(), 2);
		},
		H : function(date) {
			return date.getHours();
		},
		hh : function(date) {
			var hours = date.getHours();
			return this.toFixedWidth(hours > 12 ? hours - 12 : hours, 2);
		},
		h : function(date) {
			return date.getHours() % 12;
		},
		mm : function(date) {
			return this.toFixedWidth(date.getMinutes(), 2);
		},
		m : function(date) {
			return date.getMinutes();
		},
		ss : function(date) {
			return this.toFixedWidth(date.getSeconds(), 2);
		},
		s : function(date) {
			return date.getSeconds();
		},
		S : function(date) {
			return this.toFixedWidth(date.getMilliseconds(), 3);
		},
		a : function(date) {
			return date.getHours() < 12 ? 'AM' : 'PM';
		}
	};

	this.toFixedWidth = function(value, length, fill) {
		var result = (value || '').toString();
		fill = fill || '0';
		var padding = length - result.length;
		if (padding < 0) {
			result = result.substr(-padding);
		} else {
			for (var n = 0; n < padding; n++)
				result = fill + result;
		}
		return result;
	};

	date = this.getDate(date);

	var result = [];
	while (pattern.length > 0) {
		this.patternParts.lastIndex = 0;
		var matched = this.patternParts.exec(pattern);
		if (matched) {
			result.push(this.patternValue[matched[0]].call(this, date));
			pattern = pattern.slice(matched[0].length);
		} else {
			result.push(pattern.charAt(0));
			pattern = pattern.slice(1);
		}
	}
	return result.join('');
};
$.formatDateSelf.defaultFormat = "yyyy/MM/dd HH:mm:ss";

$.formatMoneyAutoDigit = function(source) {
	if (source == null) {
		return "";
	}
	source = source + "";
	var digit = 0;
	if (source.split(".").length > 1) {
		var temp = source.split(".")[1].replace(/(0+)$/g, "");
		digit = temp.length;
	}
	return $.formatMoney(source, digit);
}
$.formatMoney = function(source, digit) {
	if (source == null || source == "") {
		return "";
	}
	if (digit == null || digit == undefined) {
		digit = 0;
	} else {
		digit = digit >= 0 && digit <= 20 ? digit : 2;
	}

	source = parseFloat((source + "").replace(/[^\d\.-]/g, "")).toFixed(digit)
			+ "";
	var numberR = source.split(".")[0].split("").reverse(), digitR = source
			.split(".")[1];

	var result = "";

	for (i = 0; i < numberR.length; i++) {
		result += numberR[i]
				+ ((i + 1) % 3 == 0 && (i + 1) != numberR.length ? "," : "");
	}
	if (digitR == undefined || digitR == "") {
		result = result.split("").reverse().join("");
	} else {
		result = result.split("").reverse().join("") + "." + digitR;
	}

	return result.replace("-,", "-");
}