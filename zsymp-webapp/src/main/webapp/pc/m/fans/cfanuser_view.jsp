<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"  %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>信息绑定</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no" />  
<meta name="format-detection" content="email=no" />  
<link href="<c:url value="/resources/js/m/m_style.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/normalize.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/base.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/list.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/footer.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/js/plugins/jquery.mydialog/mydialog.css"/>" rel="stylesheet" type="text/css" media="screen">
<link href="<c:url value="/resources/js/plugins/jquery.mytooltip/mytooltip.css"/>" rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
<style>
html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn, em, img, ins, kbd, q, s, samp, small, strike,  sub, sup, tt, var, b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas, details, embed,  figure, figcaption, footer, header, hgroup,  menu, nav, output, ruby, section, summary, time, mark, audio, video{margin:0; padding:0; border:0; font-size:100%; font:inherit; vertical-align:baseline}
body {
	background: #f0f0f0;
}
#wx-wrap-bind{
	background-color:#f0f0f0;
	padding:10px 0 10px 0;
	-webkit-appearance: none;
}

#wx-wrap-bind .subimt-div{
	background-color:white;
	border-top:2px solid lightgray;
	border-bottom:2px solid lightgray;
	margin:0 auto 10px auto;
	max-width:600px; 
}

#wx-wrap-bind .div-rows{
	width:90%;
	border-bottom:1px solid lightgray;
	margin:10px auto;
	font-size:16px;
	height:40px;
	/*line-height: 40px;*/
	vertical-align: middle;
	color:#141414;
}

#wx-wrap-bind .div-rows:last-child{
	border:none;
}

#wx-wrap-bind .div-col-label{
	float:left;
	width:23%;
	text-align: right;
	padding-right:10px;
	border-right:1px solid #dddddd;
	height:30px;
	margin:4px;
}

#wx-wrap-bind .div-col-label label{
	height:30px; 
	line-height:30px;
	/*margin-bottom:3px;*/
}

#wx-wrap-bind .div-col-content{
	margin:4px;
}

#wx-wrap-bind .div-col-content input{
	height:24px;
	font-size:16px;
	border:none;
	margin-top:4px;
	width:50%;
	color:#868686; 
	float:left;
}

#wx-wrap-bind .div-col-content img{
	margin-top:10px;
	width:20px;
	height:20px;
	float:right;
}

#wx-wrap-bind .div-col-content textarea{
	height:80px;
	font-size:16px;
	border:none;
	margin-top:4px;
	width:60%;
	color:#868686; 
}

#wx-wrap-bind .btn-div input,button{
	width:60%;
	border-radius:10px;
	background-color:#009ff0; 
	color:white;
	font-size:20px;
	padding:10px 0;
	max-width:400px;
	border:2px solid lightgray;
	-webkit-appearance: none;
}

#wx-wrap-bind .btn-div{
	text-align:center;
}

/* 组合 */
.handler-group {
	display: -webkit-box;
	display: box;
	overflow: hidden;
}

.handler-group .cell {
	-webkit-box-flex: 1;
	        box-flex: 1;
	width: 100%;
	/* margin-right: -2%;
	margin-left: -2%; */
	text-align: center;
}

.btn-lg {
	font-size: 14px!important;
	line-height: 27px;
}

.btn-base {
	width: 74%;
	padding:6%;
	border: none;
	border-radius: 5px;
	color: #fefefe!important;
	background-image: -webkit-linear-gradient(#3cb8f7, #009ff0);
}

.btn-base:active {
	background: #0097e8;
}

.btn-base[disabled] {
	background: #ccc;
}
</style>
</head>
<body>
	<div id="wx-wrap-bind">
		<form id="fanUserFormData" method="post" >
			<div class="subimt-div">
				<input type="hidden" value="${fanUserForm.id }" name="id"/>
				<div class="div-rows">
					<div class="div-col-label" >
						<label for="userAccount">户号</label>
					</div>
					<div class="div-col-content">
	 					<input type="text" value="${fanUserForm.userAccount }" readonly="readonly"> 
					</div>
				</div>
				<div class="div-rows">
					<div class="div-col-label" >
						<label for="userName">电话</label>
					</div>
					<div class="div-col-content">
	 					<input type="text" value="${fanUserForm.mobile }" readonly="readonly"> 
					</div>
				</div>
				<%-- <div class="div-rows">
					<div class="div-col-label" >
						<label for="mobile">手机号码</label>
					</div>
					<div class="div-col-content">
						<input type="text" value="${fanUserForm.mobile }" name="mobile" id="mobile">
					</div>
				</div>
				<div class="div-rows">
					<div class="div-col-label" >
						<label for="contactAddr">用水地址</label>
					</div>
					<div class="div-col-content">
						<input type="text" value="${fanUserForm.contactAddr }" name="contactAddr" id="contactAddr">
					</div>
				</div> --%>
	 			<div class="div-rows">
					<div class="div-col-label" >
	 					<label for="remarks">备注</label> 
					</div>
					<div class="div-col-content">
	 					<input type="text" value="${fanUserForm.remarks }" name="remarks" id="remarks"> 
					</div> 
				</div>	
			</div>
		</form>
		<div class="handler-group">
			<div class="cell" style="margin-left: 4%;">
				<button type="button" id="delBindBtn" class="btn btn-block btn-lg btn-base">删除绑定</button>
			</div>
			<div class="cell">
				<button type="button" id="saveBindBtn" class="btn btn-block btn-lg btn-base">绑定信息</button>
			</div>
			<div class="cell">
				<button type="button" id="detailedBtn" class="btn btn-block btn-lg btn-base">详细信息</button>
			</div>
			<div class="cell">
				<button type="button" id="returnBtn" class="btn btn-block btn-lg btn-base">返回</button>
			</div>
		</div>
	</div>
	<%-- <%@ include file="../base/footer.jsp"%> --%>
	
<script src="<c:url value="/resources/js/bootstrap/plugins/jquery.form/3.51/jquery.form.js"/>"></script>
<script src="<c:url value="/resources/js/valField.js"/>" ></script>
<script src="<c:url value="/resources/js/plugins/jquery.mydialog/jquery.mydialog.js"/>"></script>
<script src="<c:url value="/resources/js/plugins/jquery.mytooltip/jquery.mytooltip.js"/>"></script>
<script>
$(function() {
	
	$("#delBindBtn").click(function(){
		$(this).mydialog({
			overlay: true,
			winType: 'confirm',      //对话框类型 
			skinClass: 'responsive',     //对话框皮肤
			title: '刪除綁定',         //对话框标题 
			content: '确定删除绑定吗？',       //对话框内容 
			hasCloseBtn: false,             //是否显示右上角交叉按钮 
			text4CancelBtn: '取消',         //弹出窗中按钮的文本 
			handler4CancelBtn: function () {       //点击Alert框“确定”时执行的方法 
				console.log('alert');
			},
			text4ConfirmBtn: '确定',
			handler4ConfirmBtn: function () {       //点击Alert框“确定”时执行的方法 
				$.post(
	          			"${pageContext.request.contextPath}/m/cfanuser_del.do",
	          			{"id":"${fanUserForm.id}"},
	          			function(msg){
	          				if(msg.result==true){
	          					$(this).mydialog({
	          						overlay: true,
	          						winType: 'alert',       //对话框类型 
	          						skinClass: 'responsive',     //对话框皮肤
	          						title: '溫馨提示',         //对话框标题 
	          						content: '刪除成功',       //对话框内容 
	          						hasCloseBtn: false,             //是否显示右上角交叉按钮 
	          						text4AlertBtn: '确定',
	          						handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
	          							window.location.href="${pageContext.request.contextPath}/m/cfanuser_bindList.do?openID=${fanUserForm.fan.openID}&token=${fanUserForm.token}";
	          						},
	          					});
	      		            }else{
	      		            	$(this).mydialog({
	          						overlay: true,
	          						winType: 'alert',       //对话框类型 
	          						skinClass: 'responsive',     //对话框皮肤
	          						title: '溫馨提示',         //对话框标题 
	          						content: '删除失败，请稍后再试！',       //对话框内容 
	          						hasCloseBtn: false,             //是否显示右上角交叉按钮 
	          						text4AlertBtn: '确定',
	          						handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
	          						},
	          					});
	      		            }
	          			}
	       		); 
			},
		});
	});
	
	$("#saveBindBtn").click(function(){
		//校验电话号码 
		/* var mobileReg = /^(1[3-9]\d{9}$)/;
		var mobile = $("#mobile").val();
		var flag1 = false;
		if(mobile != null && mobile.length > 0 && !mobile.indexOf("*") > 0){
			if(!mobileReg.test(mobile)) 
			{ 
				$(this).mydialog({
					overlay: true,
					winType: 'alert',       //对话框类型 
					skinClass: 'responsive',     //对话框皮肤
					title: '溫馨提示',         //对话框标题 
					content: '请输入有效的手机号码！',       //对话框内容 
					hasCloseBtn: false,             //是否显示右上角交叉按钮 
					text4AlertBtn: '确定',
					handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
						flag1 = false;
					},
				});
			}else{
				flag1 = true;
			} 
		}else{
			flag1 = true;
		} */
		
		//校验备注
		var flag = false;
		if($("#remarks").val().length >= 200){
			$(this).mydialog({
				overlay: true,
				winType: 'alert',       //对话框类型 
				skinClass: 'responsive',     //对话框皮肤
				title: '溫馨提示',         //对话框标题 
				content: '备注超过最大长度',       //对话框内容 
				hasCloseBtn: false,             //是否显示右上角交叉按钮 
				text4AlertBtn: '确定',
				handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
					flag = false;
				},
			});
		}else{
			flag = true;
		}
		
		if(flag){
			$(this).mydialog({
				overlay: true,
				winType: 'confirm',      //对话框类型 
				skinClass: 'responsive',     //对话框皮肤
				title: '保存信息',         //对话框标题 
				content: '确定要保存信息吗？',       //对话框内容 
				hasCloseBtn: false,             //是否显示右上角交叉按钮 
				text4CancelBtn: '取消',         //弹出窗中按钮的文本 
				handler4CancelBtn: function () {       //点击Alert框“确定”时执行的方法 
					console.log('alert');
				},
				text4ConfirmBtn: '确定',
				handler4ConfirmBtn: function () {       //点击Alert框“确定”时执行的方法 
					/* if($('#mobile').val().indexOf("*") > 0){
						$('#mobile').removeAttr('name');
					} 
					if($('#contactAddr').val().indexOf("*")>0){
						$('#contactAddr').removeAttr('name');
					}*/
					var fanUserFormData = $('#fanUserFormData').serialize();
					console.log(fanUserFormData);
					$.post(
		          			"${pageContext.request.contextPath}/m/cfanuser_save.do",
		          			fanUserFormData,
		          			function(msg){
		          				if(msg.result==true){
		          					$(this).mydialog({
		          						overlay: true,
		          						winType: 'alert',       //对话框类型 
		          						skinClass: 'responsive',     //对话框皮肤
		          						title: '溫馨提示',         //对话框标题 
		          						content: '保存成功',       //对话框内容 
		          						hasCloseBtn: false,             //是否显示右上角交叉按钮 
		          						text4AlertBtn: '确定',
		          						handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
		          							window.location.href="${pageContext.request.contextPath}/m/cfanuser_bindList.do?openID=${fanUserForm.fan.openID}&token=${fanUserForm.token}";
		          							//window.location.href="${pageContext.request.contextPath}/m/cfanuser_viewBindUser.do?id=${fanUserForm.id}";
		          						},
		          					});
		      		            }else{
		      		            	$(this).mydialog({
		          						overlay: true,
		          						winType: 'alert',       //对话框类型 
		          						skinClass: 'responsive',     //对话框皮肤
		          						title: '溫馨提示',         //对话框标题 
		          						content: '保存失败，请稍后再试！',       //对话框内容 
		          						hasCloseBtn: false,             //是否显示右上角交叉按钮 
		          						text4AlertBtn: '确定',
		          						handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
		          						},
		          					});
		      		            }
		          			}
		       		); 
				},
			});
		}
	});
	
	$('#detailedBtn').click(function(){
		window.location.href="${pageContext.request.contextPath}/m/cfanuser_detailed.do?userAccount=${fanUserForm.userAccount }";
	});
	
	$("#returnBtn").click(function(){
		window.location.href="${pageContext.request.contextPath}/m/cfanuser_bindList.do?openID=${fanUserForm.fan.openID}&token=${fanUserForm.token}";
	});
});
</script>
</body>
</html>