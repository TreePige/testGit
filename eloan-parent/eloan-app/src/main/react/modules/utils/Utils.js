import reqwest from 'reqwest';
import {
	Modal
} from 'antd';
(function (win) {
	Array.prototype.indexOf = function (val) {
		for (var i = 0; i < this.length; i++) {
			if (this[i] == val) return i;
		}
		return -1;
	};
	Array.prototype.remove = function (val) {
		var index = this.indexOf(val);
		if (index > -1) {
			this.splice(index, 1);
		}
	};



	function jumpLoginPage() {
		localStorage.clear();
		location.reload()
	}

	function error() {
		Modal.error({
			title: "系统忙，请稍后重试"
		});
	}
	var Cookies = {
		set: function (name, value) {
			var argv = arguments;
			var argc = arguments.length;
			var expires = (argc > 2) ? argv[2] : null;
			var path = (argc > 3) ? argv[3] : '/';
			var domain = (argc > 4) ? argv[4] : null;
			var secure = (argc > 5) ? argv[5] : false;
			document.cookie = name + "=" + escape(value) +
				((expires == null) ? "" : ("; expires=" + expires.toGMTString())) +
				((path == null) ? "" : ("; path=" + path)) +
				((domain == null) ? "" : ("; domain=" + domain)) +
				((secure == true) ? "; secure" : "");
		},
		get: function (name) {
			var arg = name + "=";
			var alen = arg.length;
			var clen = document.cookie.length;
			var i = 0;
			var j = 0;
			while (i < clen) {
				j = i + alen;
				if (document.cookie.substring(i, j) == arg)
					return Cookies.getCookieVal(j);
				i = document.cookie.indexOf(" ", i) + 1;
				if (i == 0)
					break;
			}
			return null;
		},
		clear: function (name) {
			if (Cookies.get(name)) {
				document.cookie = name + "=" +
					"; expires=Thu, 01-Jan-70 00:00:01 GMT";
			}
		},
		getCookieVal: function (offset) {
			var endstr = document.cookie.indexOf(";", offset);
			if (endstr == -1) {
				endstr = document.cookie.length;
			}
			return unescape(document.cookie.substring(offset, endstr));
		}
	};
	var NavLine = {
		_isInView:function (el, target) {
			var container = target;
			var winH = container.clientHeight,
				scrollTop = container.scrollTop,
				scrollBottom = scrollTop + winH,
				elTop = el.offsetTop,
				elBottom = elTop + el.offsetHeight - 20;

			return (elTop < scrollBottom) && (elBottom > scrollTop);
		},
		_handleSpy:function (navLineData,e) {
			var items = Object.keys(navLineData).map((key, i) => {
				return navLineData[key].substring(1);
			});
			var targetItems = items.map(function (item) {
				return document.getElementById(item);
			});
			var hasInViewAlready = false;
			targetItems.forEach((el, index) => {
				if (!hasInViewAlready) {
					if (NavLine._isInView(el, e.target)) {
						this.refs.NavLine.handleClickItem('#' + items[index]);
						hasInViewAlready = true;
					}
				}
			});
		}
	};
	var DateFormat=  {
		formatTen(num) {
			return num > 9 ? (num + "") : ("0" + num);
		},

		formatDate(date) {
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			var hour = date.getHours();
			var minute = date.getMinutes();
			var second = date.getSeconds();
			return year + "-" + this.formatTen(month) + "-" + this.formatTen(day);
		},
		formatTime (format, value) {
			var o = {
			"M+": value.getMonth() + 1,
			"d+": value.getDate(),
			"h+": value.getHours(),
			"m+": value.getMinutes(),
			"s+": value.getSeconds(),
			"q+": Math.floor((value.getMonth() + 3) / 3),
			"S": value.getMilliseconds()
			}
			if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (value.getFullYear() + "").substr(4 - RegExp.$1.length));
			for(var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1,
				RegExp.$1.length == 1 ? o[k] :
					("00" + o[k]).substr(("" + o[k]).length));
			}
			}
			return format;
		},
	};
	var Utils = {
		transfer(arry) {
			arry.forEach(function (element) {
				element["label"] = element["text"];
				delete element.text;
				element["value"] = element["id"];
				element["key"] = element["id"];
				delete element.id;
				if (element.children) {
					this.transfer(element.children)
				}
			}, this);
		},
		getData: function (obj) {
			reqwest({
				url: obj.url,
				contentType: 'application/json;charset=UTF-8',
				method: 'get',
				data: obj.data,
				headers: {
					Token: localStorage.Token || null
				},
				type: 'json',
				success: function (result) {
					if (result.code == 200 || result.code == 400) {
						obj.callback(result);
					} else {
						Modal.error({
							title: result.msg,
							onOk: () => {
								if (result.code == "800") {
									jumpLoginPage();
								}
							}
						});
					}

				},
				error: function (err) {
					error();
				}
			});
		},
		sendForm: function (obj) {
			reqwest({
				url: obj.url,
				method: obj.method || 'post',
				data: obj.data,
				headers: {
					Token: localStorage.Token || null
				},
				processData: false,
				contentType: false,
				success: function (result) {
					if (result.code == 200 || result.code == 400) {
						obj.callback(result);
					} else {
						Modal.error({
							title: result.msg,
							onOk: () => {
								if (result.code == "800") {
									jumpLoginPage();
								}
							}
						});
					}
				},
				error: function (err) {
					error();
				}
			});
		},
		//发送请求
		sendAjax: function (obj) {
			reqwest({
				url: obj.url,
				contentType: obj.contentType || 'application/json;charset=UTF-8',
				method: obj.method || 'post',
				data: obj.data,
				type: 'json',
				success: function (result) {
					if (result.code == 200 || result.code == 400) {
						obj.callback(result);
					} else {
						Modal.error({
							title: result.msg,
							onOk: () => {
								if (result.code == "800") {
									jumpLoginPage();
								}
							}
						});
					}

				},
				error: function (err) {
					error();
				}
			});
		},
		ajaxData: function (obj) {
			reqwest({
				url: obj.url,
				method: obj.method || 'post',
				data: obj.data,
				success: function (result) {
					if (result.code == 200 || result.code == 400) {
						obj.callback(result);
					} else {
						Modal.error({
							title: result.msg,
							onOk: () => {
								if (result.code == "800") {
									jumpLoginPage();
								}
							}
						});
					}

				},
				error: function (err) {
					error();
				}
			});
		},
		//加了按钮loading状态的提交
		ajaxSubmit: function (obj) {
			var me =obj.me;
			me.setState({loading:true});
			reqwest({
				url: obj.url,
				method: obj.method || 'post',
				data: obj.data,
				success: function (result) {
					me.setState({loading:false});
					if (result.code == 200 || result.code == 400) {
						obj.callback(result);
					} else {
						Modal.error({
							title: result.msg,
							onOk: () => {
								if (result.code == "800") {
									jumpLoginPage();
								}
							}
						});
					}

				},
				error: function (err) {
					error();
				}
			});
		},
		//本地存储 key,val
		localSaveJsonStorage: function (key, val) {
			var data = {
				val: val
			};
			var str = JSON.stringify(data);
			localStorage.setItem(key, str);
		},

		//提取本地存储数据
		localLoadJsonStorage: function (key) {
			var str = localStorage.getItem(key);
			var data = JSON.parse(str);
			return data.val;
		}

	}

	win.Utils = Utils;
	win.Cookies = Cookies;
	win.NavLineUtils = NavLine;
	win.DateFormat = DateFormat;
})(window)