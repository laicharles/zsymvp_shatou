/* 移动端分页插件 */

;
(function () {
 	$.fn.extend({
 		'pagination': function (options) {
 			defaults = {
 				url: '', 					// 请求地址: String
 				page: 1, 					// 当前页: Number
				rows: 20,					// 每页条数: Number
				expand: {},					// 发送到服务器的数据
				loadMoreBtn: '', 			// 列表底部按钮（选择器形式）: String
				btnStatusTextList: [		// 不同状态的加载按钮对应的文本: [String, String, String, Sting]
					'没有数据',
					'点击加载更多',
					'加载中...',
					'没有更多数据'
				],		
				doWhenNoData: null,			// 没有数据时要做的事情: function
				jointHTML: function (rs) {	// 拼接字符串: function
					// return HTML String
				},
				doAfterJointing: null,		// 每次拼接字符串后的回调函数: function
				doAfterLoadingAll: null		// 加载完所有数据后的回调函数: function
			};

			options = $.extend(defaults, options);

			return this.each(function () {
				/* 变量 */
				var URL = options.url;
				var page = options.page;
				var rows = options.rows;
				var expand = options.expand;
				if (4 === options.btnStatusTextList.length) {
					var btnStatusTextList = options.btnStatusTextList;
				}

				/* 方法 */
				var doWhenNoData = options.doWhenNoData;
				var jointHTML = options.jointHTML;
				var doAfterJointing = options.doAfterJointing;
				var doAfterLoadingAll = options.doAfterLoadingAll;

				/* JS对象转jQuery对象 */
				var $targetList = $(this);
				var $loadMoreBtn = $(options.loadMoreBtn);

				/* 辅助标记 */
				var btn_status = 0;
				var flag = 0; // 首次加载：0，非首次加载：1
				

				(function () {
					bindUI();
					$loadMoreBtn.click();
				})();


				function bindUI() {
					$(document).on('scroll', function () {
						var wScrollY = window.scrollY; // 当前滚动条位置
						var wInnerH = window.innerHeight; // 设备窗口的高度（不会变）
						var bScrollH = document.body.scrollHeight; // 滚动条总高度

						if (wScrollY + wInnerH >= bScrollH) { // 滑动到页面底部，自动模拟点击
							$loadMoreBtn.click();
						}
					});
					

					$loadMoreBtn.on('click', function () {
						if (2 === btn_status) { // 避免网速慢的时候人为多次点击导致多次加载
							return;
						}

						var $this = $(this);
						setBtnStatus(2); // 加载中...

						$.post(URL, $.extend(expand, {page: page, rows: rows}), function (rs, status) {
							// var rs = JSON.parse(rs);

							if (null == rs || 0 === rs.length) { // 没有数据
								if (0 === flag) { // 首次加载
									$this.off();
									setBtnStatus(0); // 没有数据
							
									if (doWhenNoData) {
										doWhenNoData();
									}
								} else { // 不是首次加载
									$this.off();
									setBtnStatus(3); // 加载完毕，没有更多数据

									if (doAfterLoadingAll) {
										doAfterLoadingAll();
									}
								}

								$this.unbind('click');
							} else { // 加载成功
								if (0 === flag) { // 首次加载
									flag = 1;
								}

								var html = jointHTML(rs); // 拼接
								$targetList.append(html);
								page++;
								setBtnStatus(1); // 点击加载更多
								
								if (doAfterJointing) {
									doAfterJointing();
								}
							}
						});
					});
				}


				/**
				 * 修改按钮状态和文字
				 * @param {Number} status[0: 没有数据, 1: 点击加载更多, 2: 加载中..., 3: 没有更多数据]
				 */
				 function setBtnStatus(status) {
				 	btn_status = status;
				 	$loadMoreBtn.html(btnStatusTextList[status]);
				 }
			});
		}
	});
 })(jQuery);
