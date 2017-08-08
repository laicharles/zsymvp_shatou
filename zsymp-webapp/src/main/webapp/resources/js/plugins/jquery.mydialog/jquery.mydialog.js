;(function ($){
	$.fn.extend({
		'mydialog': function (options){
			defaults = {
				overlay: true, 				// 是否显示遮罩、模态: boolean
				winType: 'alert',			// 窗口类型：alert、confirm、prompt
				// width: 400, 				// 对话框宽度: number
				// height: 300, 			// 对话框高度: number
				skinClass: '', 				// 对话框皮肤、样式主题: String
				title: '', 					// 对话框标题: String
				content: '', 				// 对话框内容: String
				hasCloseBtn: true, 			// 是否显示右上角交叉按钮: boolean
				text4AlertBtn: '确定', 		// Alert框“确定”按钮的文本: String
				text4ConfirmBtn: '确定', 	// Confirm框“确定”按钮的文本: String
				text4CancelBtn: '取消', 		// “取消”按钮的文本: String
				handler4AlertBtn: null, 	// 点击Alert框“确定”按钮时执行的方法: function
				handler4ConfirmBtn: null, 	// 点击Confirm框“确定”按钮时执行的方法: function
				handler4CancelBtn: null 	// 点击“取消”按钮时执行的方法: function
			};

			options = $.extend(defaults, options);

			return this.each(function (){
				var _dg = {
					overlay: null,
					box: null,
					closeBtn: null
				};

				(function render(container) {
					renderUI();
					bindUI();
					syncUI();
					$(container || document.body).append(_dg.box);
				})();

				function renderUI(){
					var footerContent = '';
					switch(options.winType){
						case 'alert':
						footerContent = '<div class="mydialog-cell"><a id="mydialog-btn-alert" href="javascript:;">' + options.text4AlertBtn + '</a></div>';
						break;
						case 'confirm':
						footerContent = '<div class="mydialog-cell"><a id="mydialog-btn-confirm" href="javascript:;">' + options.text4ConfirmBtn + '</a></div>' +
						'<div class="mydialog-cell"><a id="mydialog-btn-cancel" href="javascript:;">' + options.text4CancelBtn + '</a></div>';
						break;
					}

					_dg.box = $('<div class="mydialog">' +
						'<div class="mydialog-title">' + options.title + '</div>' +
						'<div class="mydialog-content"> ' + options.content + ' </div>' +
						'<div class="mydialog-handler">' + footerContent + '</div>' +
						'</div>');

					if(options.overlay){
						_dg.overlay = $('<div class="mydialog-overlay"></div>');
						$('body').append(_dg.overlay);
					}

					if(options.hasCloseBtn){
						_dg.closeBtn = $('<a class="mydialog-x" href="javascript:;">×</a>');
						_dg.box.append(_dg.closeBtn);
					}

					$('body').append(_dg.box);
				}

				function bindUI(){
					$(window).on('resize', function (){
						syncUI();
					});

					$('body').on('click', '.mydialog-overlay', function (){
						destroy();
					});
					
					_dg.box.on('click', '.mydialog-x', function (){
						destroy();
					}).on('click', '#mydialog-btn-alert', function (){
						_dg.box.trigger('alert');
						destroy();
					}).on('click', '#mydialog-btn-confirm', function (){
						_dg.box.trigger('confirm');
						destroy();
					}).on('click', '#mydialog-btn-cancel', function (){
						_dg.box.trigger('cancel');
						destroy();
					});

					if(options.handler4AlertBtn) _dg.box.on('alert', options.handler4AlertBtn);
					if(options.handler4ConfirmBtn) _dg.box.on('confirm', options.handler4ConfirmBtn);
					if(options.handler4CancelBtn) _dg.box.on('cancel', options.handler4CancelBtn);
				}

				function syncUI(){
					if(options.skinClass){
						_dg.box.addClass(options.skinClass);
					}
					
					_dg.box.each(function (){
						$(this).css({
							'left': (window.innerWidth - this.offsetWidth) / 2 + 'px',
							'top': (window.innerHeight - this.offsetHeight) / 2 + 'px',
						});
					});
				}

				function destroy(){
					destructor();
					_dg.box.off(); // 把监听的事件取消掉
					_dg.box.remove();
				}

				function destructor(){
					_dg.overlay && _dg.overlay.remove();
				}
			});
		}
	});
})(jQuery);