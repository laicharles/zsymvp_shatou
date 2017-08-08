var zsy_editor;
		var contentdata;
		KindEditor.ready(function(K) {
			zsy_editor = K.create('textarea[name="KindEditor"]', {
				filterMode: false,
				resizeType : 1,
				allowPreviewEmoticons : true,
				uploadJson : contextPath+'/cus/uploadImg.do?_csrf='+csrf_token,
//                 uploadJson : '${pageContext.request.contextPath}/resources/js/editor/jsp/upload_json.jsp',
// 				fileManagerJson : '${pageContext.request.contextPath}/resources/js/editor/jsp/file_manager_json.jsp',
				urlType:'domain',
				allowImageUpload : true,
                allowFileManager : true,
				autoHeightMode : true,
				afterCreate : function() {
					this.loadPlugin('autoheight');
				},
				afterBlur : function(){ 
					this.sync(); 
				},
				items : [
					'source', '|', 'undo', 'redo', '|','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
					'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
					'insertunorderedlist', '|', 'emoticons', 'image', 'link']
	//			    items:[
	//			           'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
	//			           'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
	//			           'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
	//			           'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
	//			           'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
	//			           'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
	//			           'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
	//			           'anchor', 'link', 'unlink', '|', 'about'
	//			   ]
	//				allowFileManager : true
			});
		});