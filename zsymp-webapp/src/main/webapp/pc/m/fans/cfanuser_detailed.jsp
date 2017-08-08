<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"  %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>用户绑定详细信息</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="format-detection" content="telephone=no" />  
	<meta name="format-detection" content="email=no" />  
	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/form.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/footer.css"/>" rel="stylesheet">
	<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
</head>
<body id="page-lxxx" class="page-base-form">
	<div class="container">
		<div class="mytooltip-container"></div>
		
		<div class="top-info" style="background-color: #6DBCE7;">
			<div class="account-number">
				<label>户号</label>
				<p>${dateiledBindUser.hno}</p>
			</div>
			<div class="account-name">
				<label>用户名</label>
				<p>${dateiledBindUser.name}</p>
			</div>
			<span class="binded-tip">已绑定</span>
		</div>
		
		<form id="messageform" class="base-form">
			<div class="form-row">
				<div class="input-row">
					<label>家庭地址</label>
					<input type="text" id="contactAddr" value="${dateiledBindUser.addr }" readonly="readonly">
				</div>
			</div>
			<div class="form-row">
				<div class="input-row">
					<label>水表口径</label>
					<input type="text" id="occupation" value="${dateiledBindUser.wide }" readonly="readonly">
				</div>
			</div>
			<div class="form-row">
				<div class="input-row">
					<label>表型</label>
					<input type="text" id="manu" value="${dateiledBindUser.manu }" readonly="readonly">
				</div>
			</div>
			<div class="form-row">
				<div class="input-row">
					<label>外码</label>
					<input type="text" id="wm" value="${dateiledBindUser.wm }" readonly="readonly">
				</div>
			</div>
			<div class="form-row">
				<div class="input-row">
					<label>安装日期</label>
					<input type="text" id="pdate" value="${dateiledBindUser.pdate }" readonly="readonly">
				</div>
			</div>
			<div class="form-row">
				<div class="input-row">
					<label>用水性质</label>
					<input type="text" id="usort" value="${dateiledBindUser.usort }" readonly="readonly">
				</div>
			</div>
		</form>
		
		<div class="padding-handler">
			<a href="javascript:history.go(-1);"><button id="returnBtn" type="button" class="btn btn-lg btn-base col-xs-12 f-cb">返回</button></a>
		</div>
	</div>
	<div class="second-bg"></div>
	<!-- footer -->
	<%-- <%@ include file="../base/footer.jsp"%> --%>
</body>
</html>
