(function($) {
	function getPluginName(target) {
		if ($(target).data("treegrid")) {
			return "treegrid";
		} else {
			return "datagrid";
		}
	}

	/*
	 * datagrid
	 */
	let autoSizeColumn = $.fn.datagrid.methods.autoSizeColumn;
	let loadDataMethod = $.fn.datagrid.methods.loadData;
	let appendMethod = $.fn.datagrid.methods.appendRow;
	let deleteMethod = $.fn.datagrid.methods.deleteRow;
	
	$.extend($.fn.datagrid.methods, {
		autoSizeColumn: function(jq, field) {
			return jq.each(function() {
				let fc = $(this).datagrid("getPanel").find(".datagrid-header .datagrid-filter-c");
				// fc.hide();

				fc.css({
					width: "1px",
					height: 0
				});
				
				autoSizeColumn.call($.fn.datagrid.methods, $(this), field);
				
				// fc.show();
				fc.css({
					width: "",
					height: ""
				});
				
				resizeFilter(this, field);
			});
		},
		loadData: function(jq, data) {
			jq.each(function() {
				$.data(this, "datagrid").filterSource = null;
			});
			
			return loadDataMethod.call($.fn.datagrid.methods, jq, data);
		},
		appendRow: function(jq, row) {
			let result = appendMethod.call($.fn.datagrid.methods, jq, row);

			jq.each(function() {
				let state = $(this).data("datagrid");
				
				if (state.filterSource) {
					state.filterSource.total ++;
					
					if (state.filterSource.rows !== state.data.rows) {
						state.filterSource.rows.push(row);
					}
				}
			});
			
			return result;
		},
		deleteRow: function(jq, index) {
			jq.each(function() {
				let state = $(this).data("datagrid");
				let opts = state.options;
				
				if (state.filterSource && opts.idField) {
					if (state.filterSource.rows === state.data.rows) {
						state.filterSource.total--;
					} else {
						for (let i = 0; i < state.filterSource.rows.length; i++) {
							let row = state.filterSource.rows[i];
							
							if (row[opts.idField] === state.data.rows[index][opts.idField]) {
								state.filterSource.rows.splice(i, 1);
								state.filterSource.total --;
								
								break;
							}
						}
					}
				}
			});
			
			return deleteMethod.call($.fn.datagrid.methods, jq, index);
		}
	});

	/*
	 * treegrid
	 */
	let loadDataMethod2 = $.fn.treegrid.methods.loadData;
	let appendMethod2 = $.fn.treegrid.methods.append;
	let insertMethod2 = $.fn.treegrid.methods.insert;
	let removeMethod2 = $.fn.treegrid.methods.remove;
	
	$.extend($.fn.treegrid.methods, {
		loadData: function(jq, data) {
			jq.each(function() {
				$.data(this, "treegrid").filterSource = null;
			});
			
			return loadDataMethod2.call($.fn.treegrid.methods, jq, data);
		},
		append: function(jq, param) {
			return jq.each(function() {
				let state = $(this).data("treegrid");
				let opts = state.options;
				
				if (opts.oldLoadFilter) {
					let rows = translateTreeData(this, param.data, param.parent);
					
					state.filterSource.total += rows.length;
					state.filterSource.rows = state.filterSource.rows.concat(rows);
					
					$(this).treegrid("loadData", state.filterSource)
				} else {
					appendMethod2($(this), param);
				}
			});
		},
		insert: function(jq, param) {
			return jq.each(function() {
				let state = $(this).data("treegrid");
				let opts = state.options;
				
				if (opts.oldLoadFilter) {
					let index = getNodeIndex(param.before || param.after);
					let pid = index >= 0 ? state.filterSource.rows[index]._parentId : null;
					let rows = translateTreeData(this, [param.data], pid);
					let newRows = state.filterSource.rows.splice(0, index >= 0 ? (param.before ?
						index : index + 1) : (state.filterSource.rows.length));
						
					newRows = newRows.concat(rows);
					newRows = newRows.concat(state.filterSource.rows);
					state.filterSource.total += rows.length;
					state.filterSource.rows = newRows;
					
					$(this).treegrid("loadData", state.filterSource);

					function getNodeIndex(id) {
						let rows = state.filterSource.rows;
						
						for (let i = 0; i < rows.length; i++) {
							if (rows[i][opts.idField] === id) {
								return i;
							}
						}
						
						return -1;
					}
				} else {
					insertMethod2($(this), param);
				}
			});
		},
		remove: function(jq, id) {
			jq.each(function() {
				let state = $(this).data("treegrid");
				
				if (state.filterSource) {
					let opts = state.options;
					let rows = state.filterSource.rows;
					
					for (let i = 0; i < rows.length; i++) {
						if (rows[i][opts.idField] === id) {
							rows.splice(i, 1);
							state.filterSource.total --;
							
							break;
						}
					}
				}
			});
			
			return removeMethod2(jq, id);
		}
	});

	let extendedOptions = {
		filterMenuIconCls: "icon-ok",
		filterBtnIconCls: "icon-filter",
		filterBtnPosition: "right",
		filterPosition: "bottom",
		remoteFilter: false,
		clientPaging: true,
		showFilterBar: true,
		filterDelay: 400,
		filterRules: [],
		// specify whether the filtered records need to match ALL or ANY of the applied filters
		filterMatchingType: "all", // possible values: 'all','any'
		filterIncludingChild: false,
		// filterCache: {},
		filterMatcher: function(data) {
			let name = getPluginName(this);
			let dg = $(this);
			let state = $.data(this, name);
			let opts = state.options;
			
			if (opts.filterRules.length) {
				let rows = [];
				
				if (name === "treegrid") {
					let rr = {};
					
					$.map(data.rows, function(row) {
						if (isMatch(row, row[opts.idField])) {
							rr[row[opts.idField]] = row;
							let prow = getRow(data.rows, row._parentId);
							
							while (prow) {
								rr[prow[opts.idField]] = prow;
								prow = getRow(data.rows, prow._parentId);
							}
							
							if (opts.filterIncludingChild) {
								let cc = getAllChildRows(data.rows, row[opts.idField]);
								
								$.map(cc, function(row) {
									rr[row[opts.idField]] = row;
								});
							}
						}
					});
					
					for (let id in rr) {
						rows.push(rr[id]);
					}
				} else {
					for (let i = 0; i < data.rows.length; i++) {
						let row = data.rows[i];
						
						if (isMatch(row, i)) {
							rows.push(row);
						}
					}
				}
				
				data = {
					total: data.total - (data.rows.length - rows.length),
					rows: rows
				};
			}
			
			return data;

			function isMatch(row, index) {
				if (opts.val === $.fn.combogrid.defaults.val) {
					opts.val = extendedOptions.val;
				}

				let rules = opts.filterRules;
				
				if (!rules.length) {
					return true;
				}
				
				for (let i = 0; i < rules.length; i++) {
					let rule = rules[i];

					// let source = row[rule.field];
					// let col = dg.datagrid('getColumnOption', rule.field);
					// if (col && col.formatter){
					// 	source = col.formatter(row[rule.field], row, index);
					// }

					let col = dg.datagrid('getColumnOption', rule.field);
					let formattedValue = (col && col.formatter) ? col.formatter(row[rule.field], row,
						index) : undefined;
					let source = opts.val.call(dg[0], row, rule.field, formattedValue);

					if (source === undefined) {
						source = '';
					}
					
					let op = opts.operators[rule.op];
					let matched = op.isMatch(source, rule.value);
					
					if (opts.filterMatchingType === "any") {
						if (matched) {
							return true;
						}
					} else {
						if (!matched) {
							return false;
						}
					}
				}
				
				return opts.filterMatchingType === "all";
			}

			function getRow(rows, id) {
				for (let i = 0; i < rows.length; i++) {
					let row = rows[i];
					
					if (row[opts.idField] === id) {
						return row;
					}
				}
				
				return null;
			}

			function getAllChildRows(rows, id) {
				let cc = getChildRows(rows, id);
				let stack = $.extend(true, [], cc);
				
				while (stack.length) {
					let row = stack.shift();
					let c2 = getChildRows(rows, row[opts.idField]);
					
					cc = cc.concat(c2);
					stack = stack.concat(c2);
				}
				
				return cc;
			}

			function getChildRows(rows, id) {
				let cc = [];
				
				for (let i = 0; i < rows.length; i++) {
					let row = rows[i];
					
					if (row._parentId === id) {
						cc.push(row);
					}
				}
				
				return cc;
			}
		},
		defaultFilterType: "text",
		defaultFilterOperator: "contains",
		defaultFilterOptions: {
			onInit: function(target) {
				let name = getPluginName(target);
				let opts = $(target)[name]("options");
				let filterOpts = this.filterOptions;
				let field = $(this).attr("name");
				let input = $(this);
				
				if (input.data("textbox")) {
					input = input.textbox("textbox");
				}
				
				input.unbind(".filter").bind("keydown.filter", function(e) {
					let t = $(this);
					
					if (this.timer) {
						clearTimeout(this.timer);
					}

					// 回车
					if (e.keyCode === 13) {
						_doFilter();
					} else if (opts.filterDelay) {
						this.timer = setTimeout(function() {
							_doFilter();
						}, opts.filterDelay);
					}
				});

				function _doFilter() {
					let rule = $(target)[name]("getFilterRule", field);
					let value = input.val();
					
					if (value !== "") {
						if ((rule && rule.value !== value) || !rule) {
							let op = rule ? rule.op : (filterOpts ? filterOpts.defaultFilterOperator || opts
								.defaultFilterOperator : opts.defaultFilterOperator);
								
							$(target)[name]("addFilterRule", {
								field: field,
								// op: opts.defaultFilterOperator,
								op: op,
								value: value
							});
							
							$(target)[name]("doFilter");
						}
					} else {
						if (rule) {
							$(target)[name]("removeFilterRule", field);
							$(target)[name]("doFilter");
						}
					}
				}
			}
		},
		filterStringify: function(data) {
			return JSON.stringify(data);
		},
		// the function to retrieve the field value of a row to match the filter rule
		val: function(row, field, formattedValue) {
			return formattedValue || row[field];
		},
		onClickMenu: function(item, button) {}
	};
	
	$.extend($.fn.datagrid.defaults, extendedOptions);
	$.extend($.fn.treegrid.defaults, extendedOptions);

	// filter types
	$.fn.datagrid.defaults.filters = $.extend({}, $.fn.datagrid.defaults.editors, {
		label: {
			init: function(container, options) {
				return $("<span></span>").appendTo(container);
			},
			getValue: function(target) {
				return $(target).html();
			},
			setValue: function(target, value) {
				$(target).html(value);
			},
			resize: function(target, width) {
				$(target)._outerWidth(width)._outerHeight(22);
			}
		}
	});
	
	$.fn.treegrid.defaults.filters = $.fn.datagrid.defaults.filters;

	// filter operators
	$.fn.datagrid.defaults.operators = {
		nofilter: {
			text: "无"
		},
		contains: {
			text: "包含",
			isMatch: function(source, value) {
				source = String(source);
				value = String(value);
				
				return source.toLowerCase().indexOf(value.toLowerCase()) >= 0;
			}
		},
		equal: {
			text: "等于",
			isMatch: function(source, value) {
				return source === value;
			}
		},
		notequal: {
			text: "不等于",
			isMatch: function(source, value) {
				return source !== value;
			}
		},
		beginwith: {
			text: "开头",
			isMatch: function(source, value) {
				source = String(source);
				value = String(value);
				
				return source.toLowerCase().indexOf(value.toLowerCase()) === 0;
			}
		},
		endwith: {
			text: "结尾",
			isMatch: function(source, value) {
				source = String(source);
				value = String(value);
				
				return source.toLowerCase().indexOf(value.toLowerCase(), source.length - value.length) !== -1;
			}
		},
		less: {
			text: "小于",
			isMatch: function(source, value) {
				return source < value;
			}
		},
		lessorequal: {
			text: "小于或等于",
			isMatch: function(source, value) {
				return source <= value;
			}
		},
		greater: {
			text: "大于",
			isMatch: function(source, value) {
				return source > value;
			}
		},
		greaterorequal: {
			text: "大于或等于",
			isMatch: function(source, value) {
				return source >= value;
			}
		}
	};
	
	$.fn.treegrid.defaults.operators = $.fn.datagrid.defaults.operators;

	function resizeFilter(target, field) {
		let toFixColumnSize = false;
		let dg = $(target);
		let header = dg.datagrid("getPanel").find("div.datagrid-header");
		let tr = header.find(".datagrid-header-row:not(.datagrid-filter-row)");
		let ff = field ? header.find(".datagrid-filter[name='" + field + "']") : header.find(".datagrid-filter");
		
		ff.each(function() {
			let name = $(this).attr("name");
			let col = dg.datagrid("getColumnOption", name);
			let cc = $(this).closest("div.datagrid-filter-c");
			let btn = cc.find("a.datagrid-filter-btn");
			let cell = tr.find("td[field='" + name + "'] .datagrid-cell");
			let cellWidth = cell._outerWidth();
			
			if (cellWidth !== _getContentWidth(cc)) {
				this.filter.resize(this, cellWidth - btn._outerWidth());
			}
			
			if (cc.width() > col.boxWidth + col.deltaWidth - 1) {
				col.boxWidth = cc.width() - col.deltaWidth + 1;
				col.width = col.boxWidth + col.deltaWidth;

				toFixColumnSize = true;
			}
		});
		
		if (toFixColumnSize) {
			$(target).datagrid("fixColumnSize");
		}

		function _getContentWidth(cc) {
			let w = 0;
			
			$(cc).children(':visible').each(function() {
				w += $(this)._outerWidth();
			});
			
			return w;
		}
	}

	function getFilterComponent(target, field) {
		let header = $(target).datagrid("getPanel").find('div.datagrid-header');
		
		return header.find("tr.datagrid-filter-row td[field='" + field + "'] .datagrid-filter");
	}

	/**
	 * get filter rule index, return -1 if not found.
	 */
	function getRuleIndex(target, field) {
		let name = getPluginName(target);
		let rules = $(target)[name]('options').filterRules;
		
		for (let i = 0; i < rules.length; i++) {
			if (rules[i].field === field) {
				return i;
			}
		}
		
		return -1;
	}

	function getFilterRule(target, field) {
		let name = getPluginName(target);
		let index = getRuleIndex(target, field);
		let rules = $(target)[name]("options").filterRules;

		if (index >= 0) {
			return rules[index];
		} else {
			return null;
		}
	}

	function addFilterRule(target, param) {
		let name = getPluginName(target);
		let opts = $(target)[name]("options");
		let rules = opts.filterRules;

		if (param.op === "nofilter") {
			removeFilterRule(target, param.field);
		} else {
			let index = getRuleIndex(target, param.field);
			
			if (index >= 0) {
				$.extend(rules[index], param);
			} else {
				rules.push(param);
			}
		}

		let input = getFilterComponent(target, param.field);
		
		if (input.length) {
			if (param.op !== "nofilter") {
				let value = input.val();
				
				if (input.data('textbox')) {
					value = input.textbox('getText');
				}
				
				if (value !== param.value) {
					input[0].filter.setValue(input, param.value);
				}
			}
			
			let menu = input[0].menu;
			
			if (menu) {
				menu.find("." + opts.filterMenuIconCls).removeClass(opts.filterMenuIconCls);
				
				let item = menu.menu("findItem", opts.operators[param.op]["text"]);
				
				menu.menu("setIcon", {
					target: item.target,
					iconCls: opts.filterMenuIconCls
				});
			}
		}
	}

	function removeFilterRule(target, field) {
		let datagrid = $(target);
		let name = getPluginName(target);
		let opts = datagrid[name]("options");
		
		if (field) {
			let index = getRuleIndex(target, field);
			
			if (index >= 0) {
				opts.filterRules.splice(index, 1);
			}
			
			_clear([field]);
		} else {
			opts.filterRules = [];
			
			let fields = datagrid.datagrid("getColumnFields", true)
				.concat(datagrid.datagrid("getColumnFields"));
			
			_clear(fields);
		}

		function _clear(fields) {
			for (let i = 0; i < fields.length; i++) {
				let input = getFilterComponent(target, fields[i]);
				
				if (input.length) {
					input[0].filter.setValue(input, "");
					
					let menu = input[0].menu;
					
					if (menu) {
						menu.find("." + opts.filterMenuIconCls).removeClass(opts.filterMenuIconCls);
					}
				}
			}
		}
	}

	function doFilter(target) {
		let name = getPluginName(target);
		let state = $.data(target, name);
		let opts = state.options;
		
		if (opts.remoteFilter) {
			$(target)[name]("load");
		} else {
			if (opts.view.type === "scrollview" && state.data.firstRows && state.data.firstRows.length) {
				state.data.rows = state.data.firstRows;
			}
			
			$(target)[name]("getPager").pagination("refresh", {
				pageNumber: 1
			});
			
			$(target)[name]("options").pageNumber = 1;
			$(target)[name]("loadData", state.filterSource || state.data);
		}
	}

	function translateTreeData(target, children, pid) {
		let opts = $(target).treegrid("options");
		
		if (!children || !children.length) {
			return []
		}
		
		let rows = [];
		
		$.map(children, function(item) {
			item._parentId = pid;
			rows.push(item);
			rows = rows.concat(translateTreeData(target, item.children, item[opts.idField]));
		});
		
		$.map(rows, function(row) {
			row.children = undefined;
		});
		
		return rows;
	}

	function myLoadFilter(data, parentId) {
		let target = this;
		let name = getPluginName(target);
		let state = $.data(target, name);
		let opts = state.options;
		
		if (name === "datagrid" && $.isArray(data)) {
			data = {
				total: data.length,
				rows: data
			};
		} else if (name === "treegrid" && $.isArray(data)) {
			let rows = translateTreeData(target, data, parentId);
			
			data = {
				total: rows.length,
				rows: rows
			}
		}
		
		if (!opts.remoteFilter || opts.clientPaging) {
			if (!state.filterSource) {
				state.filterSource = data;
			} else {
				if (!opts.isSorting) {
					if (name === "datagrid") {
						state.filterSource = data;
					} else {
						state.filterSource.total += data.length;
						state.filterSource.rows = state.filterSource.rows.concat(data.rows);
						
						if (parentId) {
							return opts.filterMatcher.call(target, data);
						}
					}
				} else {
					opts.isSorting = undefined;
				}
			}
			
			if (!opts.remoteSort && opts.sortName) {
				let names = opts.sortName.split(",");
				let orders = opts.sortOrder.split(",");
				let dg = $(target);
				
				state.filterSource.rows.sort(function(r1, r2) {
					let r = 0;
					
					for (let i = 0; i < names.length; i++) {
						let sn = names[i];
						let so = orders[i];
						let col = dg.datagrid("getColumnOption", sn);
						let sortFunc = col.sorter || function(a, b) {
							return a === b ? 0 : (a > b ? 1 : -1);
						};
						
						r = sortFunc(r1[sn], r2[sn]) * (so === "asc" ? 1 : -1);
						
						if (r !== 0) {
							return r;
						}
					}
					
					return r;
				});
			}
			
			data = opts.filterMatcher.call(target, {
				total: state.filterSource.total,
				rows: state.filterSource.rows,
				footer: state.filterSource.footer || []
			});
		}
		
		if (opts.pagination && opts.clientPaging) {
			let datagrid = $(target);
			let pager = datagrid[name]("getPager");
			
			pager.pagination({
				onSelectPage: function(pageNum, pageSize) {
					opts.pageNumber = pageNum;
					opts.pageSize = pageSize;
					
					pager.pagination("refresh", {
						pageNumber: pageNum,
						pageSize: pageSize
					});
					
					// dg[name]('loadData', state.filterSource);
					if (opts.clientPaging) {
						datagrid[name]("loadData", state.filterSource);
					} else {
						datagrid[name]("reload");
					}
				},
				onBeforeRefresh: function() {
					datagrid[name]("reload");
					return false;
				}
			});
			
			if (name === "datagrid") {
				let pd = getPageData(data.rows);
				
				opts.pageNumber = pd.pageNumber;
				data.rows = pd.rows;
			} else {
				let topRows = [];
				let childRows = [];
				
				$.map(data.rows, function(row) {
					row._parentId ? childRows.push(row) : topRows.push(row);
				});
				
				data.total = topRows.length;
				
				let pd = getPageData(topRows);
				
				opts.pageNumber = pd.pageNumber;
				data.rows = pd.rows.concat(childRows);
			}
		}
		
		$.map(data.rows, function(row) {
			row.children = undefined;
		});
		
		return data;

		function getPageData(dataRows) {
			let rows = [];
			let page = opts.pageNumber;
			
			while (page > 0) {
				let start = (page - 1) * parseInt(opts.pageSize);
				let end = start + parseInt(opts.pageSize);
				rows = dataRows.slice(start, end);
				
				if (rows.length) {
					break;
				}
				
				page --;
			}
			
			return {
				pageNumber: page > 0 ? page : 1,
				rows: rows
			};
		}
	}

	function init(target, filters) {
		filters = filters || [];
		
		let name = getPluginName(target);
		let state = $.data(target, name);
		let opts = state.options;
		
		if (!opts.filterRules.length) {
			opts.filterRules = [];
		}
		
		opts.filterCache = opts.filterCache || {};
		
		let dgOpts = $.data(target, "datagrid").options;
		let onResize = dgOpts.onResize;
		
		dgOpts.onResize = function(width, height) {
			resizeFilter(target);
			onResize.call(this, width, height);
		}
		
		let onBeforeSortColumn = dgOpts.onBeforeSortColumn;
		
		dgOpts.onBeforeSortColumn = function(sort, order) {
			let result = onBeforeSortColumn.call(this, sort, order);
			
			if (result !== false) {
				opts.isSorting = true;
			}
			
			return result;
		};

		let onResizeColumn = opts.onResizeColumn;
		
		opts.onResizeColumn = function(field, width) {
			let fc = $(this).datagrid("getPanel").find(".datagrid-header .datagrid-filter-c");
			let focusOne = fc.find(".datagrid-filter:focus");
			
			// fc.hide();
			fc.css({
				width: "1px",
				height: 0
			});
			
			$(target).datagrid("fitColumns");
			
			if (opts.fitColumns) {
				resizeFilter(target);
			} else {
				resizeFilter(target, field);
			}
			
			// fc.show();
			fc.css({
				width: "",
				height: ""
			});
			
			focusOne.blur().focus();
			onResizeColumn.call(target, field, width);
		};
		
		let onBeforeLoad = opts.onBeforeLoad;
		
		opts.onBeforeLoad = function(param1, param2) {
			if (param1) {
				param1.filterRules = opts.filterStringify(opts.filterRules);
			}
			
			if (param2) {
				param2.filterRules = opts.filterStringify(opts.filterRules);
			}
			
			let result = onBeforeLoad.call(this, param1, param2);
			
			if (result !== false && opts.url) {
				if (name === "datagrid") {
					state.filterSource = null;
				} else if (name === "treegrid" && state.filterSource) {
					if (param1) {
						let id = param1[opts.idField]; // the id of the expanding row
						let rows = state.filterSource.rows || [];
						
						for (let i = 0; i < rows.length; i++) {
							if (id === rows[i]._parentId) { // the expanding row has children
								return false;
							}
						}
					} else {
						state.filterSource = null;
					}
				}
			}
			return result;
		};
		
		if ((opts.view.type === "detailview" || opts.view.type === "scrollview") && opts.frozenColumns && opts.frozenColumns.length) {
			let onBeforeRender = opts.view.onBeforeRender;
			
			opts.view.onBeforeRender = function(target) {
				onBeforeRender.call(opts.view, target);
				
				if (!opts.detailviewFilterInited) {
					opts.detailviewFilterInited = true;
					
					let fields = $(target).datagrid("getColumnFields", true);
					
					if (opts.rownumbers) {
						fields.unshift("_");
					}
					
					let tr = $(target).data("datagrid").dc.header1.find(".datagrid-filter-row");
					
					if (tr.length < fields.length) {
						let index = $.inArray("_expander", fields);
						
						if (index >= 0) {
							let td = tr.children().eq(index);
							
							if (td.length) {
								$("<td class='_expander'></td>").insertBefore(td);
							} else {
								$("<td class='_expander'></td>").appendTo(tr);
							}
						}
					}

				}
			}
		}

		// opts.loadFilter = myLoadFilter;
		opts.loadFilter = function(data, parentId) {
			let d = opts.oldLoadFilter.call(this, data, parentId);

			return myLoadFilter.call(this, d, parentId);
		};
		
		state.dc.view2.children(".datagrid-header").unbind(".filter").bind("focusin.filter", function(e) {
			let header = $(this);
			
			setTimeout(function() {
				state.dc.body2._scrollLeft(header._scrollLeft());
			}, 0);
		});

		initCss();
		createFilter(true);
		createFilter();
		
		if (opts.fitColumns) {
			setTimeout(function() {
				resizeFilter(target);
			}, 0);
		}

		$.map(opts.filterRules, function(rule) {
			addFilterRule(target, rule);
		});

		function initCss() {
			if (!$("#datagrid-filter-style").length) {
				$('head').append(
					'<style id="datagrid-filter-style">' +
					'a.datagrid-filter-btn{display:inline-block;width:22px;height:100%;margin:0;vertical-align:middle;cursor:pointer;opacity:0.6;filter:alpha(opacity=60);}' +
					'a:hover.datagrid-filter-btn{opacity:1;filter:alpha(opacity=100);}' +
					'.datagrid-filter-row .textbox,.datagrid-filter-row .textbox .textbox-text{-moz-border-radius:0;-webkit-border-radius:0;border-radius:0;}' +
					'.datagrid-filter-row input{margin:0;-moz-border-radius:0;-webkit-border-radius:0;border-radius:0;}' +
					'.datagrid-filter-c{overflow:hidden}' +
					'.datagrid-filter-cache{position:absolute;width:10px;height:10px;left:-99999px;}' +
					'</style>'
				);
			}
		}

		/**
		 * create filter component
		 */
		function createFilter(frozen) {
			let dc = state.dc;
			let fields = $(target).datagrid("getColumnFields", frozen);
			
			if (frozen && opts.rownumbers) {
				fields.unshift('_');
			}
			
			let table = (frozen ? dc.header1 : dc.header2).find("table.datagrid-htable");

			// clear the old filter component
			table.find(".datagrid-filter").each(function() {
				if (this.filter.destroy) {
					this.filter.destroy(this);
				}
				if (this.menu) {
					$(this.menu).menu("destroy");
				}
			});
			
			table.find("tr.datagrid-filter-row").remove();

			let tr = $("<tr class='datagrid-header-row datagrid-filter-row'></tr>");
			
			if (opts.filterPosition === "bottom") {
				tr.appendTo(table.find("tbody"));
			} else {
				tr.prependTo(table.find("tbody"));
			}
			if (!opts.showFilterBar) {
				tr.hide();
			}

			for (let i = 0; i < fields.length; i++) {
				let field = fields[i];
				let col = $(target).datagrid("getColumnOption", field);
				let td = $('<td></td>').attr('field', field).appendTo(tr);
				
				if (col && col.hidden) {
					td.hide();
				}
				if (field === "_") {
					continue;
				}
				if (col && (col.checkbox || col.expander)) {
					continue;
				}

				let fopts = getFilter(field);
				
				if (fopts) {
					$(target)[name]("destroyFilter", field); // destroy the old filter component
				} else {
					fopts = $.extend({}, {
						field: field,
						type: opts.defaultFilterType,
						options: opts.defaultFilterOptions
					});
				}

				let div = opts.filterCache[field];
				
				if (!div) {
					div = $('<div class="datagrid-filter-c"></div>').appendTo(td);
					let filter = opts.filters[fopts.type];
					let input = filter.init(div, $.extend({
						height: opts.editorHeight
					}, fopts.options || {}));
					
					input.addClass("datagrid-filter").attr("name", field);
					input[0].filter = filter;
					input[0].filterOptions = fopts;
					input[0].menu = createFilterButton(div, fopts.op);
					
					if (fopts.op && fopts.op.length) {
						if (fopts.options && fopts.options.onInit) {
							fopts.options.onInit.call(input[0], target);
						} else if (fopts.defaultFilterOperator) {
							opts.defaultFilterOptions.onInit.call(input[0], target);
						}
					} else {
						opts.defaultFilterOptions.onInit.call(input[0], target);
					}
					// if (fopts.options){
					// 	if (fopts.options.onInit){
					// 		fopts.options.onInit.call(input[0], target);
					// 	}
					// } else {
					// 	opts.defaultFilterOptions.onInit.call(input[0], target);
					// }
					
					opts.filterCache[field] = div;
					
					resizeFilter(target, field);
				} else {
					div.appendTo(td);
				}
			}
		}

		function createFilterButton(container, operators) {
			if (!operators) {
				return null;
			}

			let btn = $('<a class="datagrid-filter-btn">&nbsp;</a>').addClass(opts.filterBtnIconCls);
			
			btn.css("height", opts.editorHeight);
			
			if (opts.filterBtnPosition === "right") {
				btn.appendTo(container);
			} else {
				btn.prependTo(container);
			}

			let menu = $("<div></div>").appendTo("body");
			
			$.map(["nofilter"].concat(operators), function(item) {
				let op = opts.operators[item];
				
				if (op) {
					$('<div></div>').attr("name", item).html(op.text).appendTo(menu);
				}
			});
			
			menu.menu({
				alignTo: btn,
				onClick: function(item) {
					let btn = $(this).menu("options").alignTo;
					let td = btn.closest("td[field]");
					let field = td.attr("field");
					let input = td.find(".datagrid-filter");
					let value = input[0].filter.getValue(input);

					if (opts.onClickMenu.call(target, item, btn, field) === false) {
						return;
					}

					addFilterRule(target, {
						field: field,
						op: item.name,
						value: value
					});

					doFilter(target);
				}
			});

			btn[0].menu = menu;
			btn.bind("click", {
				menu: menu
			}, function(e) {
				$(this.menu).menu("show");

				return false;
			});
			
			return menu;
		}

		function getFilter(field) {
			for (let i = 0; i < filters.length; i++) {
				let filter = filters[i];
				
				if (filter.field === field) {
					return filter;
				}
			}
			
			return null;
		}
	}

	$.extend($.fn.datagrid.methods, {
		isFilterEnabled: function(jq) {
			let name = getPluginName(jq[0]);
			let opts = $.data(jq[0], name).options;
			
			return !!opts.oldLoadFilter;
		},
		enableFilter: function(jq, filters) {
			return jq.each(function() {
				let name = getPluginName(this);
				let opts = $.data(this, name).options;
				
				if (opts.oldLoadFilter) {
					if (filters) {
						$(this)[name]("disableFilter");
					} else {
						return;
					}
				}
				
				opts.oldLoadFilter = opts.loadFilter;
				
				init(this, filters);
				$(this)[name]('resize');
				
				if (opts.filterRules.length) {
					if (opts.remoteFilter) {
						doFilter(this);
					} else if (opts.data) {
						doFilter(this);
					}
				}
			});
		},
		disableFilter: function(jq) {
			return jq.each(function() {
				let name = getPluginName(this);
				let state = $.data(this, name);
				let opts = state.options;
				
				if (!opts.oldLoadFilter) {
					return;
				}
				
				let dc = $(this).data("datagrid").dc;
				let div = dc.view.children(".datagrid-filter-cache");
				
				if (!div.length) {
					div = $("<div class='datagrid-filter-cache'></div>").appendTo(dc.view);
				}
				
				for (let field in opts.filterCache) {
					$(opts.filterCache[field]).appendTo(div);
				}
				
				let data = state.data;
				
				if (state.filterSource) {
					data = state.filterSource;
					
					$.map(data.rows, function(row) {
						row.children = undefined;
					});
				}
				
				dc.header1.add(dc.header2).find("tr.datagrid-filter-row").remove();
				opts.loadFilter = opts.oldLoadFilter || undefined;
				opts.oldLoadFilter = null;
				
				$(this)[name]("resize");
				$(this)[name]("loadData", data);

				// $(this)[name]({
				// 	data: data,
				// 	loadFilter: (opts.oldLoadFilter||undefined),
				// 	oldLoadFilter: null
				// });
			});
		},
		destroyFilter: function(jq, field) {
			return jq.each(function() {
				let name = getPluginName(this);
				let state = $.data(this, name);
				let opts = state.options;
				
				if (field) {
					_destroy(field);
				} else {
					for (let f in opts.filterCache) {
						_destroy(f);
					}
					
					$(this).datagrid("getPanel").find(".datagrid-header .datagrid-filter-row").remove();
					$(this).data("datagrid").dc.view.children(".datagrid-filter-cache").remove();
					
					opts.filterCache = {};
					
					$(this)[name]("resize");
					$(this)[name]("disableFilter");
				}

				function _destroy(field) {
					let c = $(opts.filterCache[field]);
					let input = c.find(".datagrid-filter");
					
					if (input.length) {
						let filter = input[0].filter;
						
						if (filter.destroy) {
							filter.destroy(input[0]);
						}
					}
					
					c.find(".datagrid-filter-btn").each(function() {
						$(this.menu).menu("destroy");
					});
					
					c.remove();
					
					opts.filterCache[field] = undefined;
				}
			});
		},
		getFilterRule: function(jq, field) {
			return getFilterRule(jq[0], field);
		},
		addFilterRule: function(jq, param) {
			return jq.each(function() {
				addFilterRule(this, param);
			});
		},
		removeFilterRule: function(jq, field) {
			return jq.each(function() {
				removeFilterRule(this, field);
			});
		},
		doFilter: function(jq) {
			return jq.each(function() {
				doFilter(this);
			});
		},
		getFilterComponent: function(jq, field) {
			return getFilterComponent(jq[0], field);
		},
		resizeFilter: function(jq, field) {
			return jq.each(function() {
				resizeFilter(this, field);
			});
		}
	});
})(jQuery);