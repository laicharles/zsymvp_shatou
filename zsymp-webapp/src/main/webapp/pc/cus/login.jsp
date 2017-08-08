<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../base/base.jsp"%>
<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie ie9" lang="zh-CN" class="no-js"> <![endif]-->
<!--[if !(IE)]><!-->
<html lang="zh-CN" class="no-js">
<!--<![endif]-->
<head>
	<meta charset="UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
	<title>汕头自来水微信服务系统</title>
	<link href="<c:url value="/resources/css/normalize.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/base.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/login_n_reg.css"/>" rel="stylesheet">
	
	<link rel="shortcut icon" href="<c:url value="/resources/js/bootstrap/img/shantou.ico"/>" />
	
	<style>
		html {
			background: none;	
		}
		
		#login {
			margin-top: 20px;
		}
	</style>
</head>
<body id="biz-login-body" class="biz-page">
	<div class="page-wrapper">
		<header>
			<div class="logo-wrapper">
				<a href="#" class="zsy-logo" style="width: 100%;text-align: center;">
					<img alt="szgs-logo" style="width: 400px;" src="<c:url value="/resources/images/stgs-logo.png"/>" alt=" 汕头供水微信服务系统 " />
				</a>
			</div>
		</header>
		<!-- /.header -->
		<div class="main">
			<div class="login-box-wrapper">
				<div class="login-box">
					<div class="box-header">
						<h2>登录</h2>
					</div>
					<!-- /.box-header -->
					<div class="box-content">
						<p id="error-message"></p>
						<form:form id="loginForm" method="post" action="${pageContext.request.contextPath}/cus/j_spring_security_check.do">
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><i class="ui-icon ui-icon-user"></i></span>
									<input type="text" id="username" name="loginUser" class="form-control" placeholder="用户名" />
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><i class="ui-icon ui-icon-password"></i></span>
									<input type="password" id="password" name="loginPassword" class="form-control" placeholder="密码" />
								</div>
							</div>
							<div class="form-group">
								<div class="input-group verify verify-text">
									<span class="input-group-addon"><i class="ui-icon ui-icon-edit"></i></span>
									<input type="text" id="verify" name="checkCode" class="form-control" placeholder="验证码">
								</div>
								<img class="verify verify-img" id="rc" src="${pageContext.request.contextPath}/checkCode" onclick="changeCode()" title="看不清？单击换一张图片">
							</div>
							<button id="login" class="my-btn-submit btn-blue" type="button">登录</button>
						</form:form>
					</div>
					<!-- /.box-content -->
				</div>
			</div>
		</div>
		<!-- /.main -->
	</div>
	<!-- FOOTER -->
	<footer>
		<p class="copyright">Copyright 2016 <em> 汕头市自来水总公司 All Rights Reserved<br/>粤ICP备17045245号-1</a></p>
	</footer>
	
	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	<script>
	document.onkeydown = function (e) {
	    var event = e || window.event;  
	    var code = event.keyCode || event.which || event.charCode;
	    if (code == 13) {
	        login();
	    }
	}
	
	$(document).ready(function () {
	    $('#username').focus();
	});
	
	function changeCode() { 
		var time = new Date();
	 	$('#rc').attr('src', '${pageContext.request.contextPath}/checkCode?a=' + time);
	}
	
	function checkCode(code) {
		var result = 0;
		$.ajax({
			url: '${pageContext.request.contextPath}/checkCode.do?sCode=' + code,
			dataType: 'json',
			async: false,
			success:function (data) {
				result = data;
			}
		});
		
		return result;
	}
	
	$('#login').click(function () {
		login();
	});
	
	function login() {
		var $username = $('#username');
		var $password = $('#password');
		var $verify = $('#verify');
	
		var username = $username.val();
		var password = $password.val();
		var verify = $verify.val();
		
		if('' === username) {
			setMessage('用户名不能为空');
			$username.focus();
			return;
		}
		
		if ('' === password) {
			setMessage('密码不能为空');
			$password.focus();
			return;
		}
		
		if ('' === verify) {
			setMessage('验证码不能为空');
			$verify.focus();
			return;
		}
		
		if (1 != checkCode(verify)) {
			setMessage('验证码不正确');
			$verify.text('').focus();
			changeCode();
			return;
		}

		$('#loginForm').submit();
	}
	
	var $errorMsg = $('#error-message');
	function setMessage(str) {
		$errorMsg.text('');
		$errorMsg.text(str);
	}
	
	</script>
</body>
</html>