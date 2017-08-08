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
<link href="<c:url value="/resources/css/footer.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/js/plugins/jquery.mydialog/mydialog.css"/>" rel="stylesheet" type="text/css" media="screen">
<link href="<c:url value="/resources/js/plugins/jquery.mytooltip/mytooltip.css"/>" rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
<style>
* {
    -webkit-box-sizing: border-box;
       -moz-box-sizing: border-box;
            box-sizing: border-box;
}

:before,
:after {
    -webkit-box-sizing: border-box;
       -moz-box-sizing: border-box;
            box-sizing: border-box;
}

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
	width:27%;
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

.handler {
	/* 仅仅是一个不带padding的容器 */
}

.padding-handler {
	padding: 0 15px;
}

/* 组合 */
.handler-group {
	display: -webkit-box;
	display: box;
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
	font-size: 17px!important;
	line-height: 27px;
}

.btn-base {
	width: 85%;
	padding-right: 10px;
	padding-left: 10px;
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
		<div class="mytooltip-container"></div>
		<form:form action="${pageContext.request.contextPath}/m/cfanuser_checkBindUser.do" modelAttribute="CFanUserForm" method="POST">
		<input type="hidden" value="${token}" name="token">
		<input type="hidden" value="${openID}" name="fan.openID">
		<div class="subimt-div">
			<div class="div-rows">
				<div class="div-col-label" >
					<label for="userAccount"><span style="color:red;">*</span>户号</label>
				</div>
				<div class="div-col-content">
 					<input type="text" name="userAccount" placeholder="请输入收费卡前九位" id="userAccount" size=32"> 
<%-- 					<img src="<c:url value="/resources/images/m/icon02.png"/>"> --%>
				</div>
			</div>
			<%-- <div class="div-rows">
				<div class="div-col-label" >
					<label for="userName">户名</label>
				</div>
				<div class="div-col-content">
 					<input type="text" name="userName" placeholder="登记的用水户名" id="userName" size=32"> 
					<img src="<c:url value="/resources/images/m/icon02.png"/>"> 
				</div>
			</div> --%>
			
			<div class="div-rows">
				<div class="div-col-label" >
					<label for="mobile">手机号码</label>
				</div>
				<div class="div-col-content">
					<input type="text" name="mobile" placeholder="手机号码" id="mobile" size=11/>
				</div>
			</div>
			<div class="div-rows">
				<div class="div-col-label" >
					<label for="remarks">备注</label>
				</div>
				<div class="div-col-content">
 					<input type="text" name="remarks" placeholder="区分其他绑定户" id="remarks" size=32"> 
				</div>
			</div>	
		</div>
		<div style="text-align:center;"><div style="width:300px;margin:0 auto;color:grey;">(温馨提示：请输入收费卡前九位)</div></div>
		<div class="handler-group">
			<div class="cell">
				<button type="button" id="saveBtn" class="btn btn-block btn-lg btn-base">绑定</button>  
			</div>
			<c:if test="${fanUserForm.id!=''&&fanUserForm.id!=null}">
			<div class="cell">
				<button type="button" id="delBindBtn" class="btn btn-block btn-lg btn-base">删除绑定</button>
			</div>
			</c:if>
		</div>
		</form:form>
	</div>
	<%-- <%@ include file="../base/footer.jsp"%> --%>
<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap/plugins/jquery.form/3.51/jquery.form.js"/>"></script>
<script src="<c:url value="/resources/js/valField.js"/>" ></script>
<script src="<c:url value="/resources/js/plugins/jquery.mydialog/jquery.mydialog.js"/>"></script>
<script src="<c:url value="/resources/js/plugins/jquery.mytooltip/jquery.mytooltip.js"/>"></script>
<script>
$(function() {
	
	//保存
	$("#saveBtn").click(function(){
		if(checkMsg($(this))==true){
			$("#saveBtn").attr("disabled",true);
			$("#CFanUserForm").ajaxSubmit(function(fanUserForm){
				if(fanUserForm.status=="SUCCESS"){
					$(this).mydialog({
						overlay: true,
						winType: 'confirm',      //对话框类型 
						skinClass: 'responsive',     //对话框皮肤
						title: '确认绑定？',         //对话框标题 
						content: '<div><p style="font-size: 16px;text-align:center;">'+"户号："+fanUserForm.userAccount+"<br/>号码："+fanUserForm.mobile+'</p></div>',       //对话框内容 
						hasCloseBtn: false,             //是否显示右上角交叉按钮 
						text4AlertBtn: '关闭',         //弹出窗中按钮的文本 
						handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
							console.log('alert');
						},
						text4ConfirmBtn: '确定',
						handler4ConfirmBtn: function () {       //点击Alert框“确定”时执行的方法 
							$.ajax({
								url: '${pageContext.request.getContextPath()}/m/cfanuser_saveBindUser.do',
								dataType: 'json',
								async: true,
								data: {
									'userAccount': fanUserForm.userAccount,
									'userName': fanUserForm.userName,
									'contactAddr': fanUserForm.contactAddr,
									'mobile': fanUserForm.mobile,
									'token': '${token}',
									'openID': '${openID}',
									'remarks': fanUserForm.remarks
								},
								type: 'POST',   // 请求方式
							    contentType: 'application/x-www-form-urlencoded; charset=utf-8',
							    success: function (msg) {
							        // 请求成功时处理
							        if (msg.result) {
							        	$(this).mydialog({
											overlay: true,
											winType: 'alert',      //对话框类型 
											skinClass: 'responsive',     //对话框皮肤
											title: '温馨提示',         //对话框标题 
											content: '<div><p style="font-size: 16px;text-align:center;line-height:50px;height:50px;">绑定成功！</p></div>',       //对话框内容 
											hasCloseBtn: false,             //是否显示右上角交叉按钮 
											text4AlertBtn: '关闭',         //弹出窗中按钮的文本 
											handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
												console.log('alert');
									        	window.location.href="${pageContext.request.contextPath}/m/cfanuser_bindList.do?openID=${openID}&token=${token}";//当前页面打开新页面
											}
										});
							        } else{
							        	$(this).mydialog({
											overlay: true,
											winType: 'alert',      //对话框类型 
											skinClass: 'responsive',     //对话框皮肤
											title: '温馨提示',         //对话框标题 
											content: '<div><p style="font-size: 16px;text-align:center;line-height:50px;height:50px;">绑定失败，请稍后再试！</p></div>',       //对话框内容 
											hasCloseBtn: false,             //是否显示右上角交叉按钮 
											text4AlertBtn: '关闭',         //弹出窗中按钮的文本 
											handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
												console.log('alert');
											}
										});
							        }
							    }, 
							    complete: function () {
							        // 请求完成的处理
							    }
							});
						},
					});
				}else if(fanUserForm.status=="OVER"){
					$(this).mydialog({
						overlay: true,
						winType: 'alert',      //对话框类型 
						skinClass: 'responsive',     //对话框皮肤
						title: '温馨提示',         //对话框标题 
						content: '<div><p style="font-size: 16px;text-align:center;line-height:50px;height:50px;">超过允许的绑定户数！</p></div>',       //对话框内容 
						hasCloseBtn: false,             //是否显示右上角交叉按钮 
						text4AlertBtn: '关闭',         //弹出窗中按钮的文本 
						handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
							console.log('alert');
						}
					});
				}else if(fanUserForm.status=="REPEAT"){
					$(this).mydialog({
						overlay: true,
						winType: 'alert',      //对话框类型 
						skinClass: 'responsive',     //对话框皮肤
						title: '温馨提示',         //对话框标题 
						content: '<div><p style="font-size: 16px;text-align:center;line-height:50px;height:50px;">请勿重复绑定！</p></div>',       //对话框内容 
						hasCloseBtn: false,             //是否显示右上角交叉按钮 
						text4AlertBtn: '关闭',         //弹出窗中按钮的文本 
						handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
							console.log('alert');
						}
					});
				}else{
					$(this).mydialog({
						overlay: true,
						winType: 'alert',      //对话框类型 
						skinClass: 'responsive',     //对话框皮肤
						title: '温馨提示',         //对话框标题 
						content: '<div><p style="font-size: 16px;text-align:center;line-height:50px;height:50px;">绑定失败，请稍后再试！</p></div>',       //对话框内容 
						hasCloseBtn: false,             //是否显示右上角交叉按钮 
						text4AlertBtn: '关闭',         //弹出窗中按钮的文本 
						handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
							console.log('alert');
						}
					});
				}
				$('#saveBtn').attr('disabled', false);
			});
		}
	});
	
	$("#returnBtn").click(function(){
		window.location.href="${pageContext.request.contextPath}/m/cfanuser_bindList.do?openID=${openID}&token=${token}";
	});
});

function checkMsg(obj){
	var pass =  true;
	$("#CFanUserForm").children().filter("div").children().filter("div").children().filter("div").children().children().filter("input:text").each(function(){
		var idValue = this.id.replace("\.","\\\."); 
		if($("#"+idValue).attr("size")!=undefined && $.trim($("#"+idValue).val())!=''){
				if( chklength($("#"+idValue),$("#"+idValue).attr("size"))==false){
					text = $(this).parent().parent().parent().children().children().filter("label").text(); 
			    	alert(text+"超出最大字符数，最大只允许输入"+$("#"+idValue).attr("size")+"字符（一个中文占两个字节）！");
					this.focus(); 
					pass = false;
    				return false; 
				}
		}
	});
	if(pass == false ) return false;
	var userAccount = $.trim($("#userAccount").val());
	if(userAccount == ""){
		obj.mydialog({
			overlay: true,
			winType: 'alert',      //对话框类型 
			skinClass: 'responsive',     //对话框皮肤
			title: '温馨提示',         //对话框标题 
			content: '<div><p style="font-size: 16px;text-align:center;line-height:50px;height:50px;">户号不能为空！</p></div>',       //对话框内容 
			hasCloseBtn: false,             //是否显示右上角交叉按钮 
			text4AlertBtn: '关闭',         //弹出窗中按钮的文本 
			handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
				console.log('alert');
				$("#userAccount").focus();
			}
		});
		return false;
	}
	var mobile = $.trim($("#mobile").val());
	/* if(mobile == ""){
		obj.mydialog({
			overlay: true,
			winType: 'alert',      //对话框类型 
			skinClass: 'responsive',     //对话框皮肤
			title: '温馨提示',         //对话框标题 
			content: '<div><p style="font-size: 16px;text-align:center;line-height:50px;height:50px;">电话号码不能为空！</p></div>',       //对话框内容 
			hasCloseBtn: false,             //是否显示右上角交叉按钮 
			text4AlertBtn: '关闭',         //弹出窗中按钮的文本 
			handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
				console.log('alert');
				$("#userName").focus();
			}
		});
		return false;
	} */
	var regex = /^1[3|4|5|8][0-9]\d{4,8}$/;
	if(mobile != "" && !regex.test(mobile)){
		obj.mydialog({
			overlay: true,
			winType: 'alert',      //对话框类型 
			skinClass: 'responsive',     //对话框皮肤
			title: '温馨提示',         //对话框标题 
			content: '<div><p style="font-size: 16px;text-align:center;line-height:50px;height:50px;">请输入正确的电话号码</p></div>',       //对话框内容 
			hasCloseBtn: false,             //是否显示右上角交叉按钮 
			text4AlertBtn: '关闭',         //弹出窗中按钮的文本 
			handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
				console.log('alert');
				$("#userName").focus();
			}
		});
		return false;
	}
	
	var remarks = $.trim($("#remarks").val());
	if(remarks.length >= 200){
		obj.mydialog({
			overlay: true,
			winType: 'alert',      //对话框类型 
			skinClass: 'responsive',     //对话框皮肤
			title: '温馨提示',         //对话框标题 
			content: '<div><p style="font-size: 16px;text-align:center;line-height:50px;height:50px;">备注超过最大长度</p></div>',       //对话框内容 
			hasCloseBtn: false,             //是否显示右上角交叉按钮 
			text4AlertBtn: '关闭',         //弹出窗中按钮的文本 
			handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
				console.log('alert');
				$("#remarks").focus();
			}
		});
		return false;
	}
	return true;
}
</script>
</body>
</html>