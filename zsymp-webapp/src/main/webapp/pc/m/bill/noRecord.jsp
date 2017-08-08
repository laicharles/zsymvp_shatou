<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../m/base/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>${info}</title>
	<%@ include file="../../m/base/cssbase.jsp"%>
</head>
<body id="page-notOpen" class="hint-pages">
	<div class="container">
		<div class="hint-wrapper">
			<br><br>
			<h3>${info}</h3>
			<br><br><br>
			<img src="<c:url value="/resources/images/icon-norecord.png"/>">
		</div>
	</div>
</body>
</html>
