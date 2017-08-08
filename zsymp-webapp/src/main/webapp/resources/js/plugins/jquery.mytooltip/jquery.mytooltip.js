/*
 * ------------------------------
 *  提示条插件
 * ------------------------------
 */

;
(function ($) {
	var MyToolTip = {
		'popupMessage': function(message, type, callback) {
			$('.mytooltip-container').mytooltip({
				message: message,
				type: type
			});
			
			if (callback) {
				setTimeout(callback, 2000);
			}
		},
		
		/* 把string转化为dom对象 */
		'_parseToDOM': function (str) {
			var div = document.createElement('div');
			if (typeof str == 'string') {
				div.innerHTML = str;
			}
			return div.childNodes;
		},
		
		/* 移除元素 */
		'_removeDOM' : function (o) {
			o.remove();
		}
	};

	$.fn.extend({
		'mytooltip': function (options) {
			options = $.extend({
				message: '', // 提示框内容
				type: 'info', // 提示类型：绿success，蓝info，黄warning，红danger
			}, options);

			var obj = $(MyToolTip._parseToDOM('<div class="alert alert-' + options.type + ' top-general-alert">'
					+ '<span>' + options.message + '</span></div>'));
			this.append(obj);
			
			// 方法一
			var mytimer = setTimeout(function () {
				
				obj.fadeOut(4000, function () {
					obj.timer = setTimeout(function () {
						MyToolTip._removeDOM(obj);
						clearTimeout(obj.timer);
					}, 4000);
				});
				
				clearTimeout(mytimer);
			}, 2000);

			return this;
		}
	});

	$.MyToolTip = MyToolTip;
})(jQuery);
