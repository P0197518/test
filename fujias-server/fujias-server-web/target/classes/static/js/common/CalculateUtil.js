function CalculateUtil(digits, methods) {

	if (digits == undefined) {
		this.digits = 0;
	} else {
		this.digits = digits;
	}
	this.strandDigits = 10;
	if (methods == undefined) {
		this.methods = "round";
	} else if ("1" == methods) {
		this.methods = "ceil";
	} else if ("2" == methods) {
		this.methods = "round";
	} else if ("3" == methods) {
		this.methods = "floor";
	}

	// 加
	this.floatAdd = function floatAdd(arg1, arg2) {
		return this.floatAddWithDigits(arg1, arg2, this.digits);
	};

	this.floatAddWithDigits = function floatAddWithDigits(arg1, arg2, digits) {
		var r1, r2, m;
		try {
			r1 = arg1.toString().split(".")[1].length
		} catch (e) {
			r1 = 0
		}
		try {
			r2 = arg2.toString().split(".")[1].length
		} catch (e) {
			r2 = 0
		}
		m = Math.pow(10, Math.max(r1, r2));
		result = (arg1 * m + arg2 * m) / m;
		return Math.round(result * Math.pow(10, digits)) / Math.pow(10, digits);
	};

	// 减
	this.floatSub = function floatSub(arg1, arg2) {
		return this.floatSubWithDigits(arg1, arg2, this.digits);
	};
	this.floatSubWithDigits = function floatSubWithDigits(arg1, arg2, digits) {
		var r1, r2, m, n;
		try {
			r1 = arg1.toString().split(".")[1].length
		} catch (e) {
			r1 = 0
		}
		try {
			r2 = arg2.toString().split(".")[1].length
		} catch (e) {
			r2 = 0
		}
		m = Math.pow(10, Math.max(r1, r2));
		result = (arg1 * m - arg2 * m) / m;
		return Math.round(result * Math.pow(10, digits)) / Math.pow(10, digits);
	};

	// 乘
	this.floatMul = function floatMul(arg1, arg2) {
		return this.floatMulWithDigits(arg1, arg2, this.digits);
	};
	this.floatMulWithDigits = function floatMulWithDigits(arg1, arg2, digits) {
		var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
		try {
			m += s1.split(".")[1].length
		} catch (e) {
		}
		try {
			m += s2.split(".")[1].length
		} catch (e) {
		}
		result = Number(s1.replace(".", "")) * Number(s2.replace(".", ""))
				/ Math.pow(10, m);
		return Math.round(result * Math.pow(10, digits)) / Math.pow(10, digits);
	};

	// 除
	this.floatDiv = function floatDiv(arg1, arg2) {
		return this.floatDivWithDigits(arg1, arg2, this.digits);
	};
	this.floatDivWithDigits = function floatDivWithDigits(arg1, arg2, digits) {
		var t1 = 0, t2 = 0, r1, r2;
		try {
			t1 = arg1.toString().split(".")[1].length
		} catch (e) {
		}
		try {
			t2 = arg2.toString().split(".")[1].length
		} catch (e) {
		}
		r1 = Number(arg1.toString().replace(".", ""));
		r2 = Number(arg2.toString().replace(".", ""));
		result = (r1 / r2) * Math.pow(10, t2 - t1);
		return Math.round(result * Math.pow(10, digits)) / Math.pow(10, digits);
	};

	// 根据税前计算税后
	this.noTaxToWithTax = function noTaxToWithTax(noTax, tax) {
		return this.noTaxToWithTaxWithDigits(noTax, tax, this.digits);
	}

	this.noTaxToWithTaxWithDigits = function noTaxToWithTaxWithDigits(noTax,
			tax, digits) {
		var m = 0, s1 = noTax.toString(), s2 = this.floatAddWithDigits(1, tax,
				4).toString();
		try {
			m += s1.split(".")[1].length
		} catch (e) {
		}
		try {
			m += s2.split(".")[1].length
		} catch (e) {
		}
		result = Number(s1) * Number(s2);
		if (this.methods == "ceil") {
			return Math.ceil(result * Math.pow(10, digits))
					/ Math.pow(10, digits);
		} else if (this.methods == "floor") {
			return Math.floor(result * Math.pow(10, digits))
					/ Math.pow(10, digits);
		} else {
			return Math.round(result * Math.pow(10, digits))
					/ Math.pow(10, digits);
		}
	}

	// 根据税后计算税前
	this.withTaxToNoTax = function withTaxToNoTax(withTax, tax) {
		return this.withTaxToNoTaxWithDigits(withTax, tax, this.digits);
	}

	this.withTaxToNoTaxWithDigits = function withTaxToNoTaxWithDigits(withTax,
			tax, digits) {
		var m = 0, s1 = withTax.toString(), s2 = this.floatAddWithDigits(1,
				tax, 4).toString();

		result = Number(s1) / Number(s2);
		if (this.methods == "ceil") {
			return Math.ceil(result * Math.pow(10, digits))
					/ Math.pow(10, digits);
		} else if (this.methods == "floor") {
			return Math.floor(result * Math.pow(10, digits))
					/ Math.pow(10, digits);
		} else {
			return Math.round(result * Math.pow(10, digits))
					/ Math.pow(10, digits);
		}
	}

	this.isNumberEmpty = function(source) {
		if (source == undefined || source == null || source === ""
				|| source === "0" || source == 0) {
			return true;
		} else {
			return false;
		}
	}

	this.emptyToZero = function(source) {
		if (source == undefined || source == null || source === "") {
			return 0;
		} else {
			return source;
		}
	}

	this.isStringEmpty = function(source) {
		if (source == undefined || source == null || source === "") {
			return true;
		} else {
			return false;
		}
	}
}